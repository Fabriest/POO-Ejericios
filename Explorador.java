import java.util.*;
class Explorador extends Jugador {
    public Explorador(String nombre) {
        super(nombre, 100, 15, "Explorador", 10);
    }

    @Override
    public void tomarTurno(Batalla batalla, Vista vista) {
        String[] acciones = {"Atacar", "Usar ítem", "Pasar turno"};
        int opcion = vista.pedirAccionJugador(acciones);

        try {
            switch (opcion) {
                case 0 -> {
                    List<Combatiente> enemigos = new ArrayList<>(batalla.getEnemigos());
                    int idx = vista.elegirObjetivo(enemigos);
                    atacar(batalla.getEnemigos().get(idx));
                    vista.mostrarMensaje(nombre + " ataca a " + batalla.getEnemigos().get(idx).nombre);
                }
                case 1 -> {
                    if (inventario.getItems().isEmpty()) {
                        vista.mostrarMensaje("Inventario vacío.");
                    } else {
                        Item item = inventario.getItems().values().iterator().next();
                        usarItem(item, this);
                        vista.mostrarMensaje(nombre + " usa " + item.getNombre());
                    }
                }
                case 2 -> {vista.mostrarMensaje(nombre + " pasa su turno.");
                }

                
            }
        } catch (Exception e) {
            vista.mostrarMensaje("Error en turno de Explorador: " + e.getMessage());
        }
    }
}
