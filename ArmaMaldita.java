class ArmaMaldita extends HabilidadBase {//Clase para el arma maldita
    public ArmaMaldita() {
        super("Arma Maldita", "Ataque físico potenciado", 20);
    }

    @Override
    public String aplicar(Combatiente usuario, Combatiente objetivo, Batalla batalla) {//Implementa la lógica de la habilidad
        objetivo.recibirDanio(usuario.poderAtaque + poder);
        return usuario.nombre + " golpea con " + nombre + " causando " + (usuario.poderAtaque + poder) + " de daño.";
    }
}
