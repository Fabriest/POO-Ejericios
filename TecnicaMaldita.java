class TecnicaMaldita extends HabilidadBase {//Clase para la técnica maldita
    public TecnicaMaldita() {
        super("Técnica Maldita", "Ataque dirigido", 30);
    }

    @Override
    public String aplicar(Combatiente usuario, Combatiente objetivo, Batalla batalla) {//Implementa la lógica de la habilidad
        objetivo.recibirDanio(poder);
        return usuario.nombre + " lanza " + nombre + " contra " + objetivo.nombre + " causando " + poder + " de daño.";
    }
}
