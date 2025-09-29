import java.util.List;
//Interfaz para la vista de consola
public interface Vista {
    void mostrarMensaje(String mensaje);
    int pedirAccionJugador(String[] opciones);
    int elegirObjetivo(List<Combatiente> objetivos);
    
}
