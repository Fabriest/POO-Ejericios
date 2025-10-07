public class ProcesoIO extends Proceso {
    private String dispositivo;

    public ProcesoIO(int pid, String nombre, String dispositivo) throws ExcepcionProcesoInvalido {
        super(pid, nombre);
        setDispositivo(dispositivo);
    }

    public ProcesoIO(String nombre, String dispositivo) throws ExcepcionProcesoInvalido {
        super(nombre);
        setDispositivo(dispositivo);
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) throws ExcepcionProcesoInvalido {
        if (dispositivo == null || dispositivo.trim().isEmpty()) {
            throw new ExcepcionProcesoInvalido("Dispositivo inválido");
        }
        this.dispositivo = dispositivo.trim();
    }

    @Override
    public void ejecutar() throws ExcepcionEjecucionProceso {
        try {
            setEstado(EstadoProceso.EJECUTANDO);
            setEstado(EstadoProceso.TERMINADO);
        } catch (Exception e) {
            setEstado(EstadoProceso.BLOQUEADO);
            throw new ExcepcionEjecucionProceso("Error al ejecutar ProcesoIO: " + e.getMessage(), e);
        }
    }
}
