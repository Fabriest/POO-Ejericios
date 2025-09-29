abstract class Item {
    protected String nombre;
    protected int cantidad;
    protected boolean consumible;
    protected int poder;

    public Item(String nombre, int cantidad, boolean consumible, int poder) {//Constructor
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre de ítem inválido");
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser positiva");
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.consumible = consumible;
        this.poder = poder;
    }

    public String getNombre() { return nombre; }

    public void usar(Combatiente usuario, Combatiente objetivo) throws ItemAgotadoException, ObjetivoInvalidoException {//Usa el ítem
        if (cantidad <= 0) throw new ItemAgotadoException("El ítem ya no está disponible.");
        aplicarEfecto(usuario, objetivo);
        if (consumible) cantidad--;
    }

    protected abstract void aplicarEfecto(Combatiente usuario, Combatiente objetivo);
}
