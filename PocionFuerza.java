class PocionFuerza extends Item {//Clase PocionFuerza que hereda de Item
    public PocionFuerza(int cantidad) {
        super("Poción de Fuerza", cantidad, true, 15);
    }

    @Override
    protected void aplicarEfecto(Combatiente usuario, Combatiente objetivo) {//Implementa la lógica del efecto
        objetivo.poderAtaque += poder;
        System.out.println(usuario.nombre + " gana +" + poder + " de ataque temporal.");
    }
}
