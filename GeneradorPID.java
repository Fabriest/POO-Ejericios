import java.util.concurrent.atomic.AtomicInteger;

public final class GeneradorPID {
    private static final GeneradorPID INSTANCIA = new GeneradorPID();
    private final AtomicInteger siguientePid = new AtomicInteger(1);

    private GeneradorPID() { }

    public static GeneradorPID getInstancia() {
        return INSTANCIA;
    }

    public int siguiente() {
        return siguientePid.getAndIncrement();
    }
}
