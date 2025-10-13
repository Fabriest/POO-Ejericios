public class Enfermero extends PersonalMedico {
    private String turno; // "DIURNO" o "NOCTURNO"
    private String nivelCertificacion;

    public Enfermero(String id, String nombre, String dep, int anios, double salarioBase,
                     String turno, String nivelCertificacion) throws ExcepcionDatosInvalidos {
        super(id, nombre, dep, anios, salarioBase);
        this.turno = (turno == null) ? "DIURNO" : turno.toUpperCase();
        this.nivelCertificacion = nivelCertificacion;
    }

    @Override
    public double calcularSalario() {
        double bono = "NOCTURNO".equalsIgnoreCase(turno) ? 300.0 : 0.0;
        return getSalarioBase() + bono;
    }

    public String getTurno() { return turno; }
}
