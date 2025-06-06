package model.player;

import model.Atacavel;
import model.Arma;

public class ForaDaLei extends Personagem {
    public ForaDaLei(String nome) {
        super(nome);
        this.vidaAtual = 30;
        this.vidaMaxima = 30;
        this.ataque = 25;
        this.defesa = 10;
        this.esquiva = 25;
        this.precisao = 80;
        this.arma = new Arma ("Escopeta de Cano Duplo", 10, 5);
        this.habilidades[0] = "Chuva de Disparos";
        this.habilidades[1] = "Explosoão de chumbo";
        this.habilidades[2] = "Tiro mortal";

    }

    double chance = Math.random() * 100;

    public void atacar(Atacavel alvo) {
        if (chance <= this.precisao) {
            int dano = this.ataque;

            if (Math.random() <= this.precisao) {
                dano *= 2;
                System.out.println("CRÍTICO!!");
            }

            dano -= alvo.getDefesa();
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
            System.out.println(this.nome + " acertou causando " + dano + " de dano!");
        } else {
            System.out.println(this.nome + " errou o ataque!");
        }
    }

    @Override
    public void usarHabilidade1(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[0] + "!");
        int dano = 31 - alvo.getDefesa();
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidade2(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[1] + "!");
        int dano = 35 - alvo.getDefesa();
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidade3(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[2] + "!");
        int dano = 40 - alvo.getDefesa();
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + " ativa 'Olho da Morte'!");
        this.precisao += 20;
        if (this.precisao > 100) this.precisao = 100;
    }
}
