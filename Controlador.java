
public class Controlador {
    private final RepositorioProcesos repo;
    private final Planificador planificador;
    private final VistaConsola vista;

    public Controlador(RepositorioProcesos repo, Planificador planificador, VistaConsola vista) {
        this.repo = repo;
        this.planificador = planificador;
        this.vista = vista;
    }

    public String registrarProceso(Proceso p) {
        try {
            repo.agregar(p);
            return "Proceso registrado: " + p.toString();
        } catch (ExcepcionProceso e) {
            return "Error al registrar proceso: " + e.getMessage();
        }
    }

    public String eliminarProceso(int pid) {
        try {
            Proceso eliminado = repo.eliminar(pid);
            return "Proceso eliminado: " + eliminado.toString();
        } catch (ExcepcionProcesoNoEncontrado e) {
            return "Error al eliminar: " + e.getMessage();
        }
    }

    public String listarProcesos() {
        return vista.mostrarLista(repo.listar());
    }

    public String ejecutarTodos() {
        return vista.mostrarEjecucion(planificador.ejecutarTodos());
    }

    public String ejecutarProceso(int pid) {
        return vista.mostrarEjecucion(planificador.ejecutarUno(pid));
    }

    public String buscarProceso(int pid) {
        try {
            Proceso p = repo.buscar(pid);
            return "Proceso encontrado: " + p.toString();
        } catch (ExcepcionProcesoNoEncontrado e) {
            return "Error en búsqueda: " + e.getMessage();
        }
    }
}
