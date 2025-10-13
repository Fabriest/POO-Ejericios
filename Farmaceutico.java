public class Farmaceutico extends PersonalMedico {
    private int limiteRecetasDia;
    private boolean licenciaControlados;

    public Farmaceutico(String id, String nombre, String dep, int anios, double salarioBase,
                        int limite, boolean licencia) throws ExcepcionDatosInvalidos {
        super(id, nombre, dep, anios, salarioBase);
        this.limiteRecetasDia = Math.max(0, limite);
        this.licenciaControlados = licencia;
    }

    @Override
    public double calcularSalario() {
        double bono = licenciaControlados ? 150.0 : 0.0;
        return getSalarioBase() + bono;
    }
}
