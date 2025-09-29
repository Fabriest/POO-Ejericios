import java.util.*;

public class Main {//Clase principal que inicia la batalla
    public static void main(String[] args) {
        Vista vista = new VistaConsola();

        Jugador jugador = new Guerrero("Itadori"); //Escoge a un jugador (en este caso como Guerrero)
         //Alternativamente: Jugador jugador = new Explorador("Itadori");
        try {
            jugador.getInventario().agregarItem(new PocionVida(1)); //Agrega cantidad de pociones de cada tipo
            jugador.getInventario().agregarItem(new PocionFuerza(1));
            jugador.getInventario().agregarItem(new PocionMortal(1));
        } catch (Exception e) {
            vista.mostrarMensaje("Error al agregar ítems: " + e.getMessage());
        }

        List<Enemigo> enemigos = new ArrayList<>(); //Crea la lista de enemigos
        enemigos.add(new Toji());
        enemigos.add(new Sukuna());

        Batalla batalla = new Batalla(jugador, enemigos);//Crea la batalla
        ControladorBatalla controlador = new ControladorBatalla(batalla, vista);

        while (controlador.ejecutarTurno()) {//Ejecuta los turnos hasta que la batalla termine
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
