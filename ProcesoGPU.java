public class ProcesoGPU extends Proceso {
    private int memoriaVRAM;

    public ProcesoGPU(int pid, String nombre, int memoriaVRAM) throws ExcepcionProcesoInvalido {
        super(pid, nombre);
        setMemoriaVRAM(memoriaVRAM);
    }

    public ProcesoGPU(String nombre, int memoriaVRAM) throws ExcepcionProcesoInvalido {
        super(nombre);
        setMemoriaVRAM(memoriaVRAM);
    }

    public int getMemoriaVRAM() {
        return memoriaVRAM;
    }

    public void setMemoriaVRAM(int memoriaVRAM) throws ExcepcionProcesoInvalido {
        if (memoriaVRAM <= 0) throw new ExcepcionProcesoInvalido("memoriaVRAM debe ser > 0");
        this.memoriaVRAM = memoriaVRAM;
    }

    @Override
    public void ejecutar() throws ExcepcionEjecucionProceso {
        try {
            setEstado(EstadoProceso.EJECUTANDO);
            setEstado(EstadoProceso.TERMINADO);
        } catch (Exception e) {
            setEstado(EstadoProceso.BLOQUEADO);
            throw new ExcepcionEjecucionProceso("Error al ejecutar ProcesoGPU: " + e.getMessage(), e);
        }
    }
}
