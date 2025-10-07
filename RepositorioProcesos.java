import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RepositorioProcesos {
    private final Map<Integer, Proceso> procesos = new ConcurrentHashMap<>();

    public void agregar(Proceso p) throws ExcepcionPidDuplicado, ExcepcionProcesoInvalido {
        if (p == null) throw new ExcepcionProcesoInvalido("Proceso nulo");
        int pid = p.getPid();
        if (procesos.putIfAbsent(pid, p) != null) {
            throw new ExcepcionPidDuplicado("PID ya existe: " + pid);
        }
    }

    public Proceso eliminar(int pid) throws ExcepcionProcesoNoEncontrado {
        Proceso removed = procesos.remove(pid);
        if (removed == null) throw new ExcepcionProcesoNoEncontrado("No existe proceso con PID " + pid);
        return removed;
    }

    public Proceso buscar(int pid) throws ExcepcionProcesoNoEncontrado {
        Proceso p = procesos.get(pid);
        if (p == null) throw new ExcepcionProcesoNoEncontrado("No existe proceso con PID " + pid);
        return p;
    }

    public List<Proceso> listar() {
        return Collections.unmodifiableList(new ArrayList<>(procesos.values()));
    }
}
