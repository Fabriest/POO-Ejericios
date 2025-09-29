import java.util.List;

public interface Vista {
    void mostrarMensaje(String mensaje);
    int pedirAccionJugador(String[] opciones);
    int elegirObjetivo(List<Combatiente> objetivos);
    
}
