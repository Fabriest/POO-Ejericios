class ArmaMaldita extends HabilidadBase {
    public ArmaMaldita() {
        super("Arma Maldita", "Ataque físico potenciado", 20);
    }

    @Override
    public String aplicar(Combatiente usuario, Combatiente objetivo, Batalla batalla) {
        objetivo.recibirDanio(usuario.poderAtaque + poder);
        return usuario.nombre + " golpea con " + nombre + " causando " + (usuario.poderAtaque + poder) + " de daño.";
    }
}
