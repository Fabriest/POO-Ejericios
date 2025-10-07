public class ProcesoCPU extends Proceso {
    private int ciclosCPU;

    public ProcesoCPU(int pid, String nombre, int ciclosCPU) throws ExcepcionProcesoInvalido {
        super(pid, nombre);
        setCiclosCPU(ciclosCPU);
    }

    public ProcesoCPU(String nombre, int ciclosCPU) throws ExcepcionProcesoInvalido {
        super(nombre);
        setCiclosCPU(ciclosCPU);
    }

    public int getCiclosCPU() {
        return ciclosCPU;
    }

    public void setCiclosCPU(int ciclosCPU) throws ExcepcionProcesoInvalido {
        if (ciclosCPU <= 0) throw new ExcepcionProcesoInvalido("ciclosCPU debe ser > 0");
        this.ciclosCPU = ciclosCPU;
    }

    @Override
    public void ejecutar() throws ExcepcionEjecucionProceso {
        try {
            setEstado(EstadoProceso.EJECUTANDO);
            setEstado(EstadoProceso.TERMINADO);
        } catch (Exception e) {
            setEstado(EstadoProceso.BLOQUEADO);
            throw new ExcepcionEjecucionProceso("Error al ejecutar ProcesoCPU: " + e.getMessage(), e);
        }
    }
}
