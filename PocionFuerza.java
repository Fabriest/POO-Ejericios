class PocionFuerza extends Item {
    public PocionFuerza(int cantidad) {
        super("Poción de Fuerza", cantidad, true, 15);
    }

    @Override
    protected void aplicarEfecto(Combatiente usuario, Combatiente objetivo) {
        objetivo.poderAtaque += poder;
        System.out.println(usuario.nombre + " gana +" + poder + " de ataque temporal.");
    }
}
