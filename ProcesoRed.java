public class ProcesoRed extends Proceso {
    private int puerto;

    public ProcesoRed(int pid, String nombre, int puerto) throws ExcepcionProcesoInvalido {
        super(pid, nombre);
        setPuerto(puerto);
    }

    public ProcesoRed(String nombre, int puerto) throws ExcepcionProcesoInvalido {
        super(nombre);
        setPuerto(puerto);
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) throws ExcepcionProcesoInvalido {
        if (puerto <= 0 || puerto > 65535) throw new ExcepcionProcesoInvalido("Puerto inválido");
        this.puerto = puerto;
    }

    @Override
    public void ejecutar() throws ExcepcionEjecucionProceso {
        try {
            setEstado(EstadoProceso.EJECUTANDO);
            setEstado(EstadoProceso.TERMINADO);
        } catch (Exception e) {
            setEstado(EstadoProceso.BLOQUEADO);
            throw new ExcepcionEjecucionProceso("Error al ejecutar ProcesoRed: " + e.getMessage(), e);
        }
    }
}
