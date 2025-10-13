import java.util.*;

public class GestorHospital {
    private final Map<String, PersonalMedico> trabajadores = new LinkedHashMap<>();
    private final ServicioAgenda servicioAgenda = new ServicioAgenda();
    private final ServicioNomina servicioNomina = new ServicioNomina();

    public void agregarTrabajador(PersonalMedico p) {
        if (p == null) return;
        trabajadores.put(p.getIdEmpleado(), p);
    }

    public PersonalMedico buscarTrabajador(String id) {
        if (id == null) return null;
        return trabajadores.get(id);
    }

    public Collection<PersonalMedico> listarTrabajadores() {
        return Collections.unmodifiableCollection(trabajadores.values());
    }

    public double generarReporteNomina() {
        return servicioNomina.calcularNomina(new ArrayList<>(trabajadores.values()));
    }

    public ServicioAgenda getServicioAgenda() { return servicioAgenda; }

    public List<PersonalMedico> listarTrabajadoresComoLista() {
        return new ArrayList<>(trabajadores.values());
    }
}
