import java.util.*;

class Batalla {//Clase Batalla que maneja la lógica de la batalla
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private boolean terminada;

    public Batalla(Jugador jugador, List<Enemigo> enemigos) {//Constructor
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.terminada = false;
    }

    public Jugador getJugador() { return jugador; }//Devuelve el jugador
    public List<Enemigo> getEnemigos() { return enemigos; }//Devuelve la lista de enemigos

    public boolean estaTerminada() {//Verifica si la batalla ha terminado
        if (!jugador.estaVivo()) return true;
        if (enemigos.stream().noneMatch(Enemigo::estaVivo)) return true;
        return terminada;
    }

    public void finalizar() { terminada = true; }
}
