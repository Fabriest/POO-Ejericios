class PocionMortal extends Item {//Clase PocionMortal que hereda de Item
    public PocionMortal(int cantidad) {
        super("Poción de instakill", cantidad, true, 1000);
    }

    @Override
    protected void aplicarEfecto(Combatiente usuario, Combatiente objetivo) {//Implementa la lógica del efecto
        objetivo.recibirDanio(poder);//Daño letal (De ejecuta sobre el propio jugador D:)
    }
}
