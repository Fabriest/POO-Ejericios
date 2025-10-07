import java.util.List;

public class VistaConsola {
    public String mostrarLista(List<Proceso> lista) {
        if (lista == null || lista.isEmpty()) {
            return "Lista vacía de procesos.";
        }
        StringBuilder sb = new StringBuilder("Procesos registrados:\n");
        for (Proceso p : lista) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public String mostrarEjecucion(String reporte) {
        if (reporte == null || reporte.isEmpty()) {
            return "No hay reporte de ejecución.";
        }
        return reporte;
    }
}
