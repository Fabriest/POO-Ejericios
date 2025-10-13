public class MedicoGeneral extends PersonalMedico {
    private int capacidadPorDia;
    private double tarifaConsulta;

    public MedicoGeneral(String id, String nombre, String dep, int anios, double salarioBase,
                         int capacidad, double tarifa) throws ExcepcionDatosInvalidos {
        super(id, nombre, dep, anios, salarioBase);
        if (capacidad < 0) throw new ExcepcionDatosInvalidos("Capacidad por día inválida.");
        if (tarifa < 0) throw new ExcepcionDatosInvalidos("Tarifa inválida.");
        this.capacidadPorDia = capacidad;
        this.tarifaConsulta = tarifa;
    }

    @Override
    public double calcularSalario() {
        // Ejemplo simple: salario base + bono por capacidad
        return getSalarioBase() + (capacidadPorDia * tarifaConsulta * 0.1);
    }

    public int getCapacidadPorDia() { return capacidadPorDia; }
    public double getTarifaConsulta() { return tarifaConsulta; }
}
