// Excepciones personalizadas para el sistema de batalla
class ObjetivoInvalidoException extends Exception {
    public ObjetivoInvalidoException(String msg) { super(msg); }
}

class CombatienteMuertoException extends Exception {
    public CombatienteMuertoException(String msg) { super(msg); }
}

class BatallaTerminadaException extends Exception {
    public BatallaTerminadaException(String msg) { super(msg); }
}

class ItemAgotadoException extends Exception {
    public ItemAgotadoException(String msg) { super(msg); }
}

class InventarioLlenoException extends Exception {
    public InventarioLlenoException(String msg) { super(msg); }
}
