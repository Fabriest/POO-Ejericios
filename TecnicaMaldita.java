class TecnicaMaldita extends HabilidadBase {
    public TecnicaMaldita() {
        super("Técnica Maldita", "Ataque dirigido", 30);
    }

    @Override
    public String aplicar(Combatiente usuario, Combatiente objetivo, Batalla batalla) {
        objetivo.recibirDanio(poder);
        return usuario.nombre + " lanza " + nombre + " contra " + objetivo.nombre + " causando " + poder + " de daño.";
    }
}
