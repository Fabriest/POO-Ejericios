import java.util.List;

public class ServicioNomina {
    public double calcularNomina(List<PersonalMedico> personal) {
        if (personal == null || personal.isEmpty()) return 0.0;
        return personal.stream().mapToDouble(PersonalMedico::calcularSalario).sum();
    }
}
