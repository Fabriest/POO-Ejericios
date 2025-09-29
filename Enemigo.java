abstract class Enemigo extends Combatiente {
    protected HabilidadEspecial habilidadPrincipal;
    protected boolean esJefe;

    public Enemigo(String nombre, int vidaMaxima, int poderAtaque, HabilidadEspecial habilidad, boolean esJefe) {
        super(nombre, vidaMaxima, poderAtaque);
        this.habilidadPrincipal = habilidad;
        this.esJefe = esJefe;
    }

    @Override
    public void tomarTurno(Batalla batalla, Vista vista) {
        String resultado = habilidadPrincipal.aplicar(this, batalla.getJugador(), batalla);
        vista.mostrarMensaje(resultado);
    }
}
