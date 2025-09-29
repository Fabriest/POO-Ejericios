abstract class Enemigo extends Combatiente {//Clase abstracta Enemigo que hereda de Combatiente
    protected HabilidadEspecial habilidadPrincipal;
    protected boolean esJefe;

    public Enemigo(String nombre, int vidaMaxima, int poderAtaque, HabilidadEspecial habilidad, boolean esJefe) {//Constructor
        super(nombre, vidaMaxima, poderAtaque);
        this.habilidadPrincipal = habilidad;
        this.esJefe = esJefe;
    }

    @Override
    public void tomarTurno(Batalla batalla, Vista vista) {//Implementa la lógica del turno del Enemigo
        String resultado = habilidadPrincipal.aplicar(this, batalla.getJugador(), batalla);
        vista.mostrarMensaje(resultado);
    }
}
