abstract class Jugador extends Combatiente {//Clase abstracta Jugador que hereda de Combatiente
    protected Inventario inventario;
    protected String rol;

    public Jugador(String nombre, int vidaMaxima, int poderAtaque, String rol, int capacidadInventario) {
        super(nombre, vidaMaxima, poderAtaque);
        this.inventario = new Inventario(capacidadInventario);
        this.rol = rol;
    }

    public void usarItem(Item item, Combatiente objetivo) throws ItemAgotadoException, ObjetivoInvalidoException {//Usa un item del inventario
        inventario.usarItem(item.getNombre(), this, objetivo);
    }

    public Inventario getInventario() {
        return inventario;
    }
}
