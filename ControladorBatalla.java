class ControladorBatalla {//Clase ControladorBatalla que maneja los turnos de la batalla
    private final Batalla batalla;
    private final Vista vista;

    public ControladorBatalla(Batalla batalla, Vista vista) {
        this.batalla = batalla;
        this.vista = vista;
    }

    public boolean ejecutarTurno() {
        try {
            batalla.getJugador().tomarTurno(batalla, vista);

            for (Enemigo enemigo : batalla.getEnemigos()) {//Cada enemigo toma su turno si está vivo
                if (enemigo.estaVivo()) {
                    String resultado = enemigo.habilidadPrincipal.aplicar(enemigo, batalla.getJugador(), batalla);
                    vista.mostrarMensaje(resultado);
                }
            }
        } catch (BatallaTerminadaException e) {
            batalla.finalizar();
            return false;
        }
        return !batalla.estaTerminada();
    }
}
