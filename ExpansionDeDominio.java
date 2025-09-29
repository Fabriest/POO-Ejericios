class ExpansionDeDominio extends HabilidadBase {
    public ExpansionDeDominio() {
        super("Expansión de Dominio", "Ataque inesquivable", 50);
    }

    @Override
    public String aplicar(Combatiente usuario, Combatiente objetivo, Batalla batalla) {
        objetivo.recibirDanio(poder);
        return usuario.nombre + " usa " + nombre + " causando " + poder + " de daño.";
    }
}
