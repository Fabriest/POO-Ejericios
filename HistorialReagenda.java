import java.time.LocalDateTime;

public class HistorialReagenda {
    private final LocalDateTime fechaAnterior;
    private final LocalDateTime nuevaFecha;
    private final String motivo;
    private final LocalDateTime fechaCambio;

    public HistorialReagenda(LocalDateTime anterior, LocalDateTime nueva, String motivo) {
        this.fechaAnterior = anterior;
        this.nuevaFecha = nueva;
        this.motivo = motivo;
        this.fechaCambio = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("De: %s -> A: %s | Motivo: %s | En: %s",
                fechaAnterior, nuevaFecha, motivo, fechaCambio);
    }
}
