import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class PersonalMedico {
    private final String idEmpleado;
    private String nombreCompleto;
    private String departamento;
    private int aniosExperiencia;
    private double salarioBase;
    private boolean activo = true;

    public PersonalMedico(String idEmpleado, String nombreCompleto, String departamento,
                          int aniosExperiencia, double salarioBase) throws ExcepcionDatosInvalidos {
        if (idEmpleado == null || idEmpleado.trim().isEmpty())
            throw new ExcepcionDatosInvalidos("El ID del empleado no puede estar vacío.");
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty())
            throw new ExcepcionDatosInvalidos("El nombre no puede estar vacío.");
        if (salarioBase < 0)
            throw new ExcepcionDatosInvalidos("El salario base no puede ser negativo.");
        if (aniosExperiencia < 0)
            throw new ExcepcionDatosInvalidos("Los años de experiencia no pueden ser negativos.");

        this.idEmpleado = idEmpleado.trim();
        this.nombreCompleto = nombreCompleto.trim();
        this.departamento = departamento;
        this.aniosExperiencia = aniosExperiencia;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();

    /**
     * Disponibilidad general; en diseño real debe consultar calendario.
     * Aquí devuelve true si está activo.
     */
    public boolean estaDisponible(LocalDateTime inicio, Duration duracion) {
        return activo;
    }

    // Getters / Setters
    public String getIdEmpleado() { return idEmpleado; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getDepartamento() { return departamento; }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public double getSalarioBase() { return salarioBase; }
    public boolean isActivo() { return activo; }

    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setAniosExperiencia(int anios) { this.aniosExperiencia = anios; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", nombreCompleto, idEmpleado, departamento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalMedico)) return false;
        PersonalMedico that = (PersonalMedico) o;
        return idEmpleado.equals(that.idEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpleado);
    }
}
