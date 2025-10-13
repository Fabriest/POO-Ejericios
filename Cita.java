import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cita {
    private final String idCita;
    private final String nombrePaciente;
    private PersonalMedico trabajadorAsignado;
    private LocalDateTime fechaHoraInicio;
    private Duration duracion;
    private ConstantesSistema.TipoCita tipo;
    private ConstantesSistema.EstadoCita estado;
    private final List<HistorialReagenda> historial;

    public Cita(String idCita, String nombrePaciente, PersonalMedico trabajador,
                LocalDateTime fecha, Duration duracion, ConstantesSistema.TipoCita tipo)
            throws ExcepcionDatosInvalidos {
        if (idCita == null || idCita.trim().isEmpty())
            throw new ExcepcionDatosInvalidos("ID de cita inválido.");
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty())
            throw new ExcepcionDatosInvalidos("Nombre de paciente inválido.");
        if (fecha == null)
            throw new ExcepcionDatosInvalidos("Fecha inválida.");
        if (duracion == null || duracion.isNegative() || duracion.isZero())
            throw new ExcepcionDatosInvalidos("Duración inválida.");
        this.idCita = idCita;
        this.nombrePaciente = nombrePaciente;
        this.trabajadorAsignado = trabajador;
        this.fechaHoraInicio = fecha;
        this.duracion = duracion;
        this.tipo = (tipo == null) ? ConstantesSistema.TipoCita.OTRO : tipo;
        this.estado = ConstantesSistema.EstadoCita.PROGRAMADA;
        this.historial = new ArrayList<>();
    }

    public String getIdCita() { return idCita; }
    public String getNombrePaciente() { return nombrePaciente; }
    public PersonalMedico getTrabajadorAsignado() { return trabajadorAsignado; }
    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public Duration getDuracion() { return duracion; }
    public ConstantesSistema.TipoCita getTipo() { return tipo; }
    public ConstantesSistema.EstadoCita getEstado() { return estado; }
    public List<HistorialReagenda> getHistorial() { return historial; }

    public void setTrabajadorAsignado(PersonalMedico trabajador) {
        this.trabajadorAsignado = trabajador;
    }

    public void reagendar(LocalDateTime nuevaFecha, String motivo)
            throws ExcepcionConflictoAgenda, ExcepcionDatosInvalidos {
        if (nuevaFecha == null) throw new ExcepcionDatosInvalidos("Fecha nueva inválida.");
        if (nuevaFecha.isBefore(LocalDateTime.now()))
            throw new ExcepcionDatosInvalidos("No se puede reagendar al pasado.");

        historial.add(new HistorialReagenda(this.fechaHoraInicio, nuevaFecha, motivo));
        this.fechaHoraInicio = nuevaFecha;
        this.estado = ConstantesSistema.EstadoCita.REAGENDADA;
    }

    public void cambiarEstado(ConstantesSistema.EstadoCita nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return String.format("Cita[%s] Paciente: %s | Medico: %s | Inicio: %s | Dur: %s | Estado: %s",
                idCita, nombrePaciente,
                (trabajadorAsignado == null ? "Sin asignar" : trabajadorAsignado.getIdEmpleado()),
                fechaHoraInicio, duracion, estado);
    }
}
