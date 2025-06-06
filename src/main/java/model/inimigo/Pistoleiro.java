package model.inimigo;

import model.Atacavel;

public class Pistoleiro extends Inimigo{
    public Pistoleiro(String nome) {
        super(nome, 80, 80, 20, 10, 75, "Revólver Lemat");
        this.habilidades[0] =  "Mão Firme";
        this.habilidades[1] = "Postura Ofensiva";
        this.habilidades[2] = "Defesa Tática";
        this.habilidadeEspecial = "Tiro Perfeito";
    }

    @Override
    public void usarHabilidade1 (Atacavel alvo) {
        System.out.println(this.nome + " fica com sua " + habilidades[0] + "!");
        this.precisao += 30;
    }

    @Override
    public void usarHabilidade2 (Atacavel alvo) {
        System.out.println(this.nome + " assume uma " + habilidades[1] + "!");
        this.ataque += 15;
    }

    @Override
    public void usarHabilidade3 (Atacavel alvo) {
        System.out.println(this.nome + " coloca sua " + habilidades[2]);
        this.defesa += 10;
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + "Tiro perfeito!");
        int dano = this.ataque * 2 + alvo.getDefesa();
        alvo.receberDano(dano);
    }
}
