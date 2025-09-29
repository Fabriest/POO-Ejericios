import java.util.*;
class Guerrero extends Jugador {//Clase Guerrero que hereda de Jugador
    public Guerrero(String nombre) {
        super(nombre, 150, 25, "Guerrero", 5);
    }

    @Override
    public void tomarTurno(Batalla batalla, Vista vista) {//Implementa la lógica del turno del Guerrero
        String[] acciones = {"Atacar", "Usar ítem", "Pasar turno"};
        int opcion = vista.pedirAccionJugador(acciones);

        try {//Maneja las acciones del jugador según la opción elegida
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
                case 2 -> vista.mostrarMensaje(nombre + " pasa su turno.");
            }
        } catch (Exception e) {
            vista.mostrarMensaje("Error en turno de Guerrero: " + e.getMessage());
        }
    }
}
