import java.util.List;

public class Planificador {
    private final RepositorioProcesos repo;

    public Planificador(RepositorioProcesos repo) {
        this.repo = repo;
    }

    public String ejecutarTodos() {
        StringBuilder reporte = new StringBuilder();
        List<Proceso> lista = repo.listar();
        if (lista.isEmpty()) {
            return "No hay procesos para ejecutar.\n";
        }
        for (Proceso p : lista) {
            try {
                p.ejecutar();
                reporte.append("Éxito: ").append(p.toString()).append("\n");
            } catch (ExcepcionEjecucionProceso e) {
                reporte.append("Error ejecutando PID ").append(p.getPid()).append(": ").append(e.getMessage()).append("\n");
            }
        }
        return reporte.toString();
    }

    public String ejecutarUno(int pid) {
        try {
            Proceso p = repo.buscar(pid);
            p.ejecutar();
            return "Éxito: " + p.toString();
        } catch (ExcepcionProceso e) {
            return "Error: " + e.getMessage();
        }
    }
}
