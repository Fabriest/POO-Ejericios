class ExpansionDeDominio extends HabilidadBase {//Clase para la expansión de dominio
    public ExpansionDeDominio() {
        super("Expansión de Dominio", "Ataque inesquivable", 50);
    }

    @Override
    public String aplicar(Combatiente usuario, Combatiente objetivo, Batalla batalla) {//Implementa la lógica de la habilidad
        objetivo.recibirDanio(poder);
        return usuario.nombre + " usa " + nombre + " causando " + poder + " de daño.";
    }
}
