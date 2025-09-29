class PocionVida extends Item {//Clase PocionVida que hereda de Item
    public PocionVida(int cantidad) {
        super("Poción de Vida", cantidad, true, 40);
    }

    @Override
    protected void aplicarEfecto(Combatiente usuario, Combatiente objetivo) {//Implementa la lógica del efecto
        objetivo.curar(poder);
    }
}
