class PocionMortal extends Item {
    public PocionMortal(int cantidad) {
        super("Poción de instakill", cantidad, true, 1000);
    }

    @Override
    protected void aplicarEfecto(Combatiente usuario, Combatiente objetivo) {
        objetivo.recibirDanio(poder);
    }
}
