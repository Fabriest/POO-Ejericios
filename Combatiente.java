abstract class Combatiente {
    protected String nombre;
    protected int vidaMaxima;
    protected int vidaActual;
    protected int poderAtaque;

    public Combatiente(String nombre, int vidaMaxima, int poderAtaque) {//Constructor
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre inválido");
        if (vidaMaxima <= 0) throw new IllegalArgumentException("Vida máxima debe ser positiva");
        if (poderAtaque < 0) throw new IllegalArgumentException("Poder de ataque no puede ser negativo");

        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.poderAtaque = poderAtaque;
    }

    public boolean estaVivo() { return vidaActual > 0; }

    public void atacar(Combatiente objetivo) throws ObjetivoInvalidoException, CombatienteMuertoException {//Ataca a otro combatiente
        if (objetivo == null) throw new ObjetivoInvalidoException("Objetivo nulo");
        if (!objetivo.estaVivo()) throw new CombatienteMuertoException("El objetivo ya está muerto");
        objetivo.recibirDanio(poderAtaque);
    }

    public void recibirDanio(int cantidad) {//Recibe daño
        if (cantidad < 0) throw new IllegalArgumentException("El daño no puede ser negativo");
        vidaActual -= cantidad;
        if (vidaActual < 0) vidaActual = 0;
    }

    public void curar(int cantidad) {//Cura vida
        if (cantidad < 0) throw new IllegalArgumentException("Curación negativa no permitida");
        vidaActual += cantidad;
        if (vidaActual > vidaMaxima) vidaActual = vidaMaxima;
    }

    public abstract void tomarTurno(Batalla batalla, Vista vista) throws BatallaTerminadaException;

    @Override
    public String toString() {
        return nombre + " [HP: " + vidaActual + "/" + vidaMaxima + "]";
    }
}
