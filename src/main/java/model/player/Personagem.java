package model.player;

import model.Atacavel;

public abstract class Personagem implements Atacavel {
        protected String nome;
        protected int vidaAtual;
        protected int vidaMaxima;
        protected int ataque;
        protected int defesa;
        protected int esquiva;
        protected int precisao;
        protected String arma;

        public Personagem(String nome, int vidaAtual, int vidaMaxima, int ataque, int defesa, int esquiva, int precisao, String arma) {
                this.nome = nome;
                this.vidaAtual = vidaAtual;
                this.vidaMaxima = vidaMaxima;
                this.ataque = ataque;
                this.defesa = defesa;
                this.esquiva = esquiva;
                this.precisao = precisao;
                this.arma = arma;
        }

        // Mudei para override para seguir o padrão de inimigo
        @Override
        public void atacar(Atacavel alvo) {
                int dano = this.ataque - alvo.getDefesa();
                if (dano < 0) dano = 0;
                alvo.receberDano(dano);
                System.out.println(this.nome + " atacou " + alvo.getNome() + " de dano.");
        }

        @Override
        public void receberDano(int dano) {
                this.vidaAtual -= dano;
                if(this.vidaAtual <= 0) vidaAtual = 0;
                System.out.println(this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.vidaAtual);
        }

        @Override
        public String getNome() { return nome; }
        @Override
        public int getDefesa() { return defesa; }
}