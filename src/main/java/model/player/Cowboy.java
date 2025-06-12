package model.player;

import model.Arma;
import model.Atacavel;

public class Cowboy extends Personagem {
    public Cowboy(String nome) {
        super(nome);
        this.vidaAtual = 40;
        this.vidaMaxima = 40;
        this.ataque = 15;
        this.defesa = 15;
        this.esquiva = 35;
        this.precisao = 60;
        this.arma = new Arma("Revolver de Vaqueiro", 5, 10);

        this.habilidades[0] = "Disparo Rápido";
        this.habilidades[1] = "Tiro Duplo";
        this.habilidades[2] = "Golpe Final";
    }

    double chance = Math.random() * 100;

    public void atacar(Atacavel alvo) {
        double chance = Math.random() * 100;

        if (chance <= this.precisao) {
            int dano = this.ataque;

            if (Math.random() < 0.1) {
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
        int dano = 21 - alvo.getDefesa();
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidade2(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[1] + "!");
        int dano = 25 - alvo.getDefesa();
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidade3(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[2] + "!");
        int dano = 30 - alvo.getDefesa();
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + " ativa 'Olho da Morte'!");
        this.precisao += 10;
        if (this.precisao > 100) this.precisao = 100;
    }
}
