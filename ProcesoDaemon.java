import java.time.Duration;

public class ProcesoDaemon extends Proceso {
    private Duration intervalo;

    public ProcesoDaemon(int pid, String nombre, Duration intervalo) throws ExcepcionProcesoInvalido {
        super(pid, nombre);
        setIntervalo(intervalo);
    }

    public ProcesoDaemon(String nombre, Duration intervalo) throws ExcepcionProcesoInvalido {
        super(nombre);
        setIntervalo(intervalo);
    }

    public Duration getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Duration intervalo) throws ExcepcionProcesoInvalido {
        if (intervalo == null || intervalo.isNegative()) {
            throw new ExcepcionProcesoInvalido("Intervalo inválido");
        }
        this.intervalo = intervalo;
    }

    @Override
    public void ejecutar() throws ExcepcionEjecucionProceso {
        try {
            setEstado(EstadoProceso.EJECUTANDO);
            setEstado(EstadoProceso.TERMINADO);
        } catch (Exception e) {
            setEstado(EstadoProceso.BLOQUEADO);
            throw new ExcepcionEjecucionProceso("Error al ejecutar ProcesoDaemon: " + e.getMessage(), e);
        }
    }
}
