import java.io.Serializable;

public class ConstantesSistema implements Serializable {
    public enum TipoCita {
        CONSULTA_GENERAL,
        CIRUGIA,
        TERAPIA,
        DIAGNOSTICO,
        OTRO
    }

    public enum EstadoCita {
        PROGRAMADA,
        CONFIRMADA,
        EN_PROGRESO,
        COMPLETADA,
        CANCELADA,
        REAGENDADA
    }
}
