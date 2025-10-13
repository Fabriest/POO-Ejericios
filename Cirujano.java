import java.util.List;

public class Cirujano extends PersonalMedico {
    private List<String> tiposOperacion;
    private int horasDisponibles;
    private double tarifaHoraCirugia;
    private double bonoRiesgo;

    public Cirujano(String id, String nombre, String dep, int anios, double salarioBase,
                    List<String> tiposOperacion, int horas, double tarifa, double bono)
            throws ExcepcionDatosInvalidos {
        super(id, nombre, dep, anios, salarioBase);
        if (horas < 0) throw new ExcepcionDatosInvalidos("Horas inválidas.");
        this.tiposOperacion = tiposOperacion;
        this.horasDisponibles = horas;
        this.tarifaHoraCirugia = tarifa;
        this.bonoRiesgo = bono;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + (horasDisponibles * tarifaHoraCirugia) + bonoRiesgo;
    }

    public List<String> getTiposOperacion() { return tiposOperacion; }
}
