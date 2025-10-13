import java.time.format.DateTimeFormatter;
import java.util.List;

public class VistaConsola {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA HOSPITALARIO ===");
        System.out.println("1. Listar trabajadores");
        System.out.println("2. Ver agenda de un trabajador");
        System.out.println("3. Programar nueva cita");
        System.out.println("4. Reagendar cita (automático)");
        System.out.println("5. Mostrar nómina total");
        System.out.println("6. Salir");
        System.out.print("Seleccione opción: ");
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarError(String msg) {
        System.err.println("Error: " + msg);
    }

    public void mostrarTrabajadores(List<PersonalMedico> lista) {
        System.out.println("\n--- Trabajadores registrados ---");
        for (PersonalMedico p : lista) {
            System.out.println(p.getIdEmpleado() + " - " + p.getNombreCompleto() + " - " + p.getDepartamento());
        }
    }

    public void mostrarAgenda(PersonalMedico p, List<Cita> citas) {
        System.out.println("\nAgenda de: " + p);
        if (citas.isEmpty()) {
            System.out.println("No hay citas programadas.");
            return;
        }
        for (Cita c : citas) {
            System.out.println(String.format("ID:%s | Paciente:%s | Inicio:%s | Dur:%s | Estado:%s",
                    c.getIdCita(), c.getNombrePaciente(), c.getFechaHoraInicio().format(dtf),
                    c.getDuracion(), c.getEstado()));
        }
    }
}
