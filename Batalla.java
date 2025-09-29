import java.util.*;

class Batalla {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private boolean terminada;

    public Batalla(Jugador jugador, List<Enemigo> enemigos) {
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.terminada = false;
    }

    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return enemigos; }

    public boolean estaTerminada() {
        if (!jugador.estaVivo()) return true;
        if (enemigos.stream().noneMatch(Enemigo::estaVivo)) return true;
        return terminada;
    }

    public void finalizar() { terminada = true; }
}
