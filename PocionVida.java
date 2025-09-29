class PocionVida extends Item {
    public PocionVida(int cantidad) {
        super("Poción de Vida", cantidad, true, 40);
    }

    @Override
    protected void aplicarEfecto(Combatiente usuario, Combatiente objetivo) {
        objetivo.curar(poder);
    }
}
