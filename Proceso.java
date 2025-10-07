import java.util.Objects;

public abstract class Proceso {
    private final int pid;
    private String nombre;
    private EstadoProceso estado;

    public Proceso(int pid, String nombre) throws ExcepcionProcesoInvalido {
        if (pid <= 0) throw new ExcepcionProcesoInvalido("PID debe ser > 0");
        validarNombre(nombre);
        this.pid = pid;
        this.nombre = nombre.trim();
        this.estado = EstadoProceso.NUEVO;
    }

    public Proceso(String nombre) throws ExcepcionProcesoInvalido {
        this(GeneradorPID.getInstancia().siguiente(), nombre);
    }

    private void validarNombre(String nombre) throws ExcepcionProcesoInvalido {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ExcepcionProcesoInvalido("Nombre inválido para el proceso");
        }
    }

    public int getPid() {
        return pid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ExcepcionProcesoInvalido {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public EstadoProceso getEstado() {
        return estado;
    }

    protected void setEstado(EstadoProceso estado) {
        this.estado = Objects.requireNonNull(estado, "estado no puede ser null");
    }

    public abstract void ejecutar() throws ExcepcionEjecucionProceso;

    @Override
    public String toString() {
        return String.format("PID=%d, nombre=%s, estado=%s, tipo=%s",
                pid, nombre, estado, this.getClass().getSimpleName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proceso)) return false;
        Proceso otro = (Proceso) o;
        return this.pid == otro.pid;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(pid);
    }
}
