import java.util.List;

public class Radiologo extends PersonalMedico {
    private List<String> equiposCertificados;
    private double tarifaPorEstudio;

    public Radiologo(String id, String nombre, String dep, int anios, double salarioBase,
                     List<String> equipos, double tarifa) throws ExcepcionDatosInvalidos {
        super(id, nombre, dep, anios, salarioBase);
        this.equiposCertificados = equipos;
        this.tarifaPorEstudio = tarifa;
    }

    @Override
    public double calcularSalario() {
        int factor = (equiposCertificados == null) ? 0 : equiposCertificados.size();
        return getSalarioBase() + (factor * tarifaPorEstudio * 0.5);
    }
}
