package model.player;

import model.Atacavel;

public class Cowboy extends Personagem {

    public Cowboy(String nome) {
        super(nome);
        this.vidaMaxima = 100;
        this.vidaAtual = 100;
        this.ataque = 25;
        this.defesa = 15;
        this.esquiva = 20;
        this.precisao = 80;
        this.arma = "Revólver";

        this.habilidades[0] = "Disparo Rápido";
        this.habilidades[1] = "Tiro Duplo";
        this.habilidades[2] = "Golpe Final";
    }

    @Override
    public void usarHabilidade1(Atacavel inimigo) {
        System.out.println(this.nome + " usa " + habilidades[0] + "!");
        int dano = this.ataque + 5 - inimigo.getDefesa(); // bônus de dano
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
    }

    @Override
    public void usarHabilidade2(Atacavel inimigo) {
        System.out.println(this.nome + " usa " + habilidades[1] + "!");
        for (int i = 0; i < 2; i++) { // dois tiros consecutivos
            int dano = this.ataque - inimigo.getDefesa();
            if (dano < 0) dano = 0;
            inimigo.receberDano(dano);
        }
    }

    @Override
    public void usarHabilidade3(Atacavel inimigo) {
        System.out.println(this.nome + " usa " + habilidades[2] + "!");
        int dano = (this.ataque * 2) - inimigo.getDefesa(); // dano dobrado
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
    }
}
