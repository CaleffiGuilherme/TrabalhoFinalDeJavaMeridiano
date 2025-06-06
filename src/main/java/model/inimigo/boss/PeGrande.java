package model.inimigo.boss;

import model.Atacavel;
import model.inimigo.Inimigo;

public class PeGrande extends Inimigo {
    public PeGrande(String nome) {
        super(nome, 80, 80, 20, 10, 75, "Mãos");
        this.habilidades[0] = "Pele Grossa";
        this.habilidades[1] = "Foco Selvagem";
        this.habilidades[2] = "Despertar";
        this.habilidadeEspecial = "Alma Primitiva";
    }

    @Override
    public void usarHabilidade1(Atacavel alvo) {
        System.out.println(this.nome + " deixa sua " + habilidades[0] + "!");
        this.defesa += 20;
    }

    @Override
    public void usarHabilidade2(Atacavel alvo) {
        System.out.println(this.nome + " usa seu " + habilidades[1] + "!");
        this.precisao += 15;
    }

    @Override
    public void usarHabilidade3(Atacavel alvo) {
        System.out.println(this.nome + " permite sua " + habilidades[2] + "!");
        this.precisao += 15;
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + "alcança sua arma primitiva!");
        this.ataque *= 2;
        this.defesa *= 2;
        this.precisao *= 2;
        int dano = this.ataque * 2 + alvo.getDefesa();
        alvo.receberDano(dano);
    }
}
