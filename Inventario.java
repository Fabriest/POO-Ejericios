import java.util.*;

class Inventario {//Clase Inventario que maneja los ítems del jugador
    private Map<String, Item> items;
    private int capacidad;

    public Inventario(int capacidad) {//Constructor
        this.items = new HashMap<>();
        this.capacidad = capacidad;
    }

    public void agregarItem(Item item) throws InventarioLlenoException {//Agrega un ítem al inventario
        if (items.size() >= capacidad) throw new InventarioLlenoException("Inventario lleno");
        items.put(item.getNombre(), item);
    }

    public void usarItem(String nombre, Jugador usuario, Combatiente objetivo) throws ItemAgotadoException, ObjetivoInvalidoException {//Usa un ítem del inventario
        Item item = items.get(nombre);
        if (item == null) throw new ObjetivoInvalidoException("El ítem no existe en el inventario.");
        item.usar(usuario, objetivo);
    }

    public Map<String, Item> getItems() { return items; }
}
