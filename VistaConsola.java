import java.util.*;

public class VistaConsola implements Vista {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public int pedirAccionJugador(String[] opciones) {
        mostrarMensaje("Elige una acción:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        int eleccion;
        do {
            System.out.print("Opción: ");
            eleccion = scanner.nextInt() - 1;
        } while (eleccion < 0 || eleccion >= opciones.length);
        return eleccion;
    }

    @Override
    public int elegirObjetivo(List<Combatiente> objetivos) {
        mostrarMensaje("Elige un objetivo:");
        for (int i = 0; i < objetivos.size(); i++) {
            System.out.println((i + 1) + ". " + objetivos.get(i));
        }
        int eleccion;
        do {
            System.out.print("Objetivo: ");
            eleccion = scanner.nextInt() - 1;
        } while (eleccion < 0 || eleccion >= objetivos.size());
        return eleccion;
    }
}
