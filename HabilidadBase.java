abstract class HabilidadBase implements HabilidadEspecial {
    protected String nombre;
    protected String descripcion;
    protected int poder;

    public HabilidadBase(String nombre, String descripcion, int poder) {//Constructor
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.poder = poder;
    }

    public String getNombre() { return nombre; }
}
