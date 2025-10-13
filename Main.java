import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GestorHospital gestor = new GestorHospital();
        VistaConsola vista = new VistaConsola();

        // Crear médicos predefinidos
        try {
            MedicoGeneral m1 = new MedicoGeneral("M001", "Juan Pérez", "General", 5, 5000, 20, 100);
            Cirujano m2 = new Cirujano("M002", "Ana Gómez", "Cirugía", 8, 8000,
                    Arrays.asList("Appendicectomía", "Cesárea"), 40, 200, 500);
            Enfermero e1 = new Enfermero("E001", "Carlos Ruiz", "Enfermería", 3, 2500, "DIURNO", "Nivel II");
            Radiologo r1 = new Radiologo("R001", "Luisa Herrera", "Radiología", 6, 4500,
                    Arrays.asList("RX", "Ultrasonido"), 300);
            Farmaceutico f1 = new Farmaceutico("F001", "María Díaz", "Farmacia", 4, 3500, 100, true);

            gestor.agregarTrabajador(m1);
            gestor.agregarTrabajador(m2);
            gestor.agregarTrabajador(e1);
            gestor.agregarTrabajador(r1);
            gestor.agregarTrabajador(f1);

            // Crear algunas citas de ejemplo
            ServicioAgenda sa = gestor.getServicioAgenda();
            Cita c1 = new Cita("C001", "Paciente A", m1,
                    LocalDateTime.now().plusHours(2), Duration.ofMinutes(30), ConstantesSistema.TipoCita.CONSULTA_GENERAL);
            sa.programarCita(c1);

            Cita c2 = new Cita("C002", "Paciente B", m2,
                    LocalDateTime.now().plusDays(1).withHour(9).withMinute(0), Duration.ofMinutes(120), ConstantesSistema.TipoCita.CIRUGIA);
            sa.programarCita(c2);

        } catch (ExcepcionDatosInvalidos | ExcepcionConflictoAgenda ex) {
            System.err.println("Error inicial: " + ex.getMessage());
            return;
        }

        // Menú interactivo 
        Scanner sc = new Scanner(System.in);
        boolean ejecutando = true;
        while (ejecutando) {
            try {
                vista.mostrarMenuPrincipal();
                String opt = sc.nextLine().trim();
                switch (opt) {
                    case "1":
                        vista.mostrarTrabajadores(gestor.listarTrabajadoresComoLista());
                        break;
                    case "2":
                        System.out.print("Ingrese ID del trabajador: ");
                        String id = sc.nextLine().trim();
                        PersonalMedico p = gestor.buscarTrabajador(id);
                        if (p == null) {
                            vista.mostrarError("Trabajador no encontrado.");
                        } else {
                            List<Cita> citas = gestor.getServicioAgenda().obtenerCitasDe(p);
                            vista.mostrarAgenda(p, citas);
                        }
                        break;
                    case "3":
                        try {
                            System.out.print("ID nueva cita: ");
                            String idCita = sc.nextLine().trim();
                            System.out.print("Nombre paciente: ");
                            String paciente = sc.nextLine().trim();
                            System.out.print("ID del trabajador (o ENTER para asignar automático): ");
                            String idTrab = sc.nextLine().trim();
                            PersonalMedico seleccionado = null;
                            if (!idTrab.isEmpty()) seleccionado = gestor.buscarTrabajador(idTrab);
                            if (seleccionado == null) {
                                // asignar el primer trabajador disponible
                                List<PersonalMedico> lista = gestor.listarTrabajadoresComoLista();
                                if (lista.isEmpty()) {
                                    vista.mostrarError("No hay trabajadores registrados.");
                                    break;
                                }
                                seleccionado = lista.get(0);
                                vista.mostrarMensaje("Se asignó automáticamente a: " + seleccionado.getIdEmpleado());
                            }
                            System.out.print("Fecha y hora inicio (AAAA-MM-DD HH:MM): ");
                            String fechaStr = sc.nextLine().trim();
                            LocalDateTime inicio = LocalDateTime.parse(fechaStr.replace(" ", "T"));
                            System.out.print("Duración en minutos: ");
                            long minutos = Long.parseLong(sc.nextLine().trim());
                            Duration dur = Duration.ofMinutes(minutos);
                            System.out.print("Tipo cita (1=CONSULTA_GENERAL,2=CIRUGIA,3=TERAPIA,4=DIAGNOSTICO,5=OTRO): ");
                            String tipoOpt = sc.nextLine().trim();
                            ConstantesSistema.TipoCita tipo = ConstantesSistema.TipoCita.OTRO;
                            switch (tipoOpt) {
                                case "1": tipo = ConstantesSistema.TipoCita.CONSULTA_GENERAL; break;
                                case "2": tipo = ConstantesSistema.TipoCita.CIRUGIA; break;
                                case "3": tipo = ConstantesSistema.TipoCita.TERAPIA; break;
                                case "4": tipo = ConstantesSistema.TipoCita.DIAGNOSTICO; break;
                                default: tipo = ConstantesSistema.TipoCita.OTRO; break;
                            }
                            Cita nueva = new Cita(idCita, paciente, seleccionado, inicio, dur, tipo);
                            gestor.getServicioAgenda().programarCita(nueva);
                            vista.mostrarMensaje("Cita programada correctamente: " + nueva.getIdCita());
                        } catch (ExcepcionDatosInvalidos | ExcepcionConflictoAgenda ex) {
                            vista.mostrarError(ex.getMessage());
                        } catch (Exception ex) {
                            vista.mostrarError("Error al crear la cita. Formato inválido u otro error: " + ex.getMessage());
                        }
                        break;
                    case "4":
                        try {
                            System.out.print("ID de la cita a reagendar: ");
                            String idCita = sc.nextLine().trim();
                            // buscar la cita en calendario
                            Cita encontrada = null;
                            PersonalMedico dueño = null;
                            for (PersonalMedico pm : gestor.listarTrabajadoresComoLista()) {
                                List<Cita> citas = gestor.getServicioAgenda().obtenerCitasDe(pm);
                                for (Cita c : citas) {
                                    if (c.getIdCita().equalsIgnoreCase(idCita)) {
                                        encontrada = c;
                                        dueño = pm;
                                        break;
                                    }
                                }
                                if (encontrada != null) break;
                            }
                            if (encontrada == null) {
                                vista.mostrarError("Cita no encontrada.");
                                break;
                            }
                            // intentar reagendar automáticamente
                            boolean ok = gestor.getServicioAgenda().autoReagendar(encontrada, gestor.listarTrabajadoresComoLista());
                            if (ok) {
                                vista.mostrarMensaje("La cita fue reagendada automáticamente: " + encontrada.getIdCita());
                            } else {
                                vista.mostrarError("No fue posible reagendar automáticamente dentro del periodo permitido.");
                            }
                        } catch (Exception ex) {
                            vista.mostrarError("Error al reagendar: " + ex.getMessage());
                        }
                        break;
                    case "5":
                        double total = gestor.generarReporteNomina();
                        vista.mostrarMensaje(String.format("Nómina total (estimada): Q%.2f", total));
                        break;
                    case "6":
                        ejecutando = false;
                        vista.mostrarMensaje("Saliendo. ¡Hasta luego!");
                        break;
                    default:
                        vista.mostrarError("Opción no válida. Intente de nuevo.");
                        break;
                }
            } catch (Exception ex) {
                vista.mostrarError("Error inesperado: " + ex.getMessage());
            }
        }
        sc.close();
    }
}
