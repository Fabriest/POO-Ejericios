public class ExcepcionProceso extends Exception {
    public ExcepcionProceso(String msg) {
        super(msg);
    }

    public ExcepcionProceso(String msg, Throwable cause) {
        super(msg, cause);
    }
}
