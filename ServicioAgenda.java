import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ServicioAgenda {
    private final Map<PersonalMedico, TreeMap<LocalDateTime, Cita>> calendario = new HashMap<>();
    private final int LOOK_AHEAD_DAYS = 14;
    private final Duration STEP = Duration.ofMinutes(15);

    /**
     * Programar cita en el calendario del trabajador asignado.
     */
    public void programarCita(Cita cita) throws ExcepcionConflictoAgenda, ExcepcionDatosInvalidos {
        if (cita == null) throw new ExcepcionDatosInvalidos("La cita no puede ser nula.");
        PersonalMedico medico = cita.getTrabajadorAsignado();
        if (medico == null) throw new ExcepcionDatosInvalidos("La cita debe tener un trabajador asignado.");

        calendario.putIfAbsent(medico, new TreeMap<>());
        TreeMap<LocalDateTime, Cita> agenda = calendario.get(medico);
        if (tieneConflicto(medico, cita.getFechaHoraInicio(), cita.getDuracion())) {
            throw new ExcepcionConflictoAgenda("Conflicto de horario para el trabajador " + medico.getIdEmpleado());
        }
        agenda.put(cita.getFechaHoraInicio(), cita);
    }

    /**
     * Revisa si existe conflicto para un trabajador en un intervalo.
     */
    public boolean tieneConflicto(PersonalMedico trabajador, LocalDateTime inicio, Duration duracion) {
        TreeMap<LocalDateTime, Cita> mapa = calendario.get(trabajador);
        if (mapa == null) return false;
        LocalDateTime fin = inicio.plus(duracion);

        // Entrada anterior más cercana
        Map.Entry<LocalDateTime, Cita> anterior = mapa.floorEntry(inicio);
        if (anterior != null) {
            LocalDateTime inicioAnterior = anterior.getKey();
            LocalDateTime finAnterior = inicioAnterior.plus(anterior.getValue().getDuracion());
            if (!finAnterior.isBefore(inicio)) return true; // se solapa
        }

        // Entrada siguiente más cercana
        Map.Entry<LocalDateTime, Cita> siguiente = mapa.ceilingEntry(inicio);
        if (siguiente != null) {
            LocalDateTime inicioSiguiente = siguiente.getKey();
            if (!inicioSiguiente.isAfter(fin)) return true; // se solapa o toca
        }
        return false;
    }

    /**
     * Intento de reagendamiento automático:
     * 1) intenta mismo trabajador buscando el siguiente slot disponible dentro de LOOK_AHEAD_DAYS.
     * 2) si falla, intenta con otros trabajadores de la lista dada (misma especialidad no implementada aquí),
     *    para simplificar intenta con cualquier trabajador disponible recibido en la lista.
     * Retorna true si se reagendó (y actualiza la cita), false si no fue posible.
     */
    public boolean autoReagendar(Cita cita, List<PersonalMedico> candidatos) {
        if (cita == null) return false;

        PersonalMedico original = cita.getTrabajadorAsignado();
        Duration dur = cita.getDuracion();
        LocalDateTime inicioBusqueda = cita.getFechaHoraInicio().plusMinutes(30); // empezar 30 minutos después

        // 1) intentar en el mismo trabajador
        if (original != null) {
            LocalDateTime slot = buscarSiguienteSlot(original, inicioBusqueda, dur, LOOK_AHEAD_DAYS);
            if (slot != null) {
                try {
                    // remover la cita anterior si existía en calendario
                    removerCita(original, cita);
                    cita.reagendar(slot, "Reagendado automáticamente - mismo trabajador");
                    programarCita(cita);
                    return true;
                } catch (Exception e) {
                    // continuar a buscar otras opciones
                }
            }
        }

        // 2) intentar con otros candidatos
        for (PersonalMedico candidato : candidatos) {
            if (candidato.equals(original)) continue;
            LocalDateTime slot = buscarSiguienteSlot(candidato, inicioBusqueda, dur, LOOK_AHEAD_DAYS);
            if (slot != null) {
                try {
                    if (original != null) removerCita(original, cita);
                    cita.setTrabajadorAsignado(candidato);
                    cita.reagendar(slot, "Reagendado automáticamente - otro trabajador");
                    programarCita(cita);
                    return true;
                } catch (Exception e) {
                    // inténtalo con el siguiente candidato
                }
            }
        }
        return false;
    }

    /**
     * Busca el siguiente slot libre para un trabajador.
     */
    private LocalDateTime buscarSiguienteSlot(PersonalMedico trabajador, LocalDateTime desde,
                                              Duration duracion, int lookAheadDays) {
        if (!trabajador.estaDisponible(desde, duracion)) return null;
        calendario.putIfAbsent(trabajador, new TreeMap<>());
        TreeMap<LocalDateTime, Cita> mapa = calendario.get(trabajador);
        LocalDateTime fechaLimite = desde.plusDays(lookAheadDays);

        LocalDateTime intento = desde;
        for (LocalDateTime t = intento; !t.isAfter(fechaLimite); t = t.plus(STEP)) {
            if (!t.isBefore(LocalDateTime.now())) {
                if (!tieneConflicto(trabajador, t, duracion)) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Remueve una cita del calendario si existe (busca por referencia).
     */
    public void removerCita(PersonalMedico trabajador, Cita cita) {
        if (trabajador == null || cita == null) return;
        TreeMap<LocalDateTime, Cita> mapa = calendario.get(trabajador);
        if (mapa == null) return;
        // buscar la clave que tenga esa cita (comparación por id)
        LocalDateTime clave = null;
        for (Map.Entry<LocalDateTime, Cita> e : mapa.entrySet()) {
            if (e.getValue().getIdCita().equals(cita.getIdCita())) {
                clave = e.getKey();
                break;
            }
        }
        if (clave != null) mapa.remove(clave);
    }

    /**
     * Obtener las citas de un trabajador
     */
    public List<Cita> obtenerCitasDe(PersonalMedico trabajador) {
        TreeMap<LocalDateTime, Cita> mapa = calendario.get(trabajador);
        if (mapa == null) return Collections.emptyList();
        return new ArrayList<>(mapa.values());
    }

    /**
     * Obtener el mapa completo (solo lectura)
     */
    public Map<PersonalMedico, TreeMap<LocalDateTime, Cita>> getCalendario() {
        return Collections.unmodifiableMap(calendario);
    }
}
