import java.util.*;

public class Main {
    public static void main(String[] args) {
        Vista vista = new VistaConsola();

        Jugador jugador = new Guerrero("Itadori");
        try {
            jugador.getInventario().agregarItem(new PocionVida(1));
            jugador.getInventario().agregarItem(new PocionFuerza(1));
            jugador.getInventario().agregarItem(new PocionMortal(1));
        } catch (Exception e) {
            vista.mostrarMensaje("Error al agregar ítems: " + e.getMessage());
        }

        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(new Toji());
        enemigos.add(new Sukuna());

        Batalla batalla = new Batalla(jugador, enemigos);
        ControladorBatalla controlador = new ControladorBatalla(batalla, vista);

        while (controlador.ejecutarTurno()) {
            vista.mostrarMensaje("\n--- Estado Actual ---");
            vista.mostrarMensaje(batalla.getJugador().toString());
            for (Enemigo e : batalla.getEnemigos()) {
                vista.mostrarMensaje(e.toString());
            }
            vista.mostrarMensaje("----------------------");
        }

        vista.mostrarMensaje("¡La batalla ha terminado!");
    }
    
}
