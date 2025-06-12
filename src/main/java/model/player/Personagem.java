package model.player;

import model.Arma;
import model.Atacavel;
import model.Habilidade;

public abstract class Personagem implements Atacavel, Habilidade {
        protected String nome;
        protected int vidaAtual;
        protected int vidaMaxima;
        protected int ataque;
        protected int defesa;
        protected int esquiva;
        protected int precisao;
        protected Arma arma;

        protected String[] habilidades = new String[3];

        public Personagem(String nome) {
                this.nome = nome;
        }

        // Mudei para override para seguir o padrão de inimigo
        @Override
        public void atacar(Atacavel alvo) {
                double chance = Math.random() * 100;
                // deixa a batalha mais real e adiciona condição de erro
                if (chance <= this.precisao) {
                        int dano = this.ataque - alvo.getDefesa();
                        if(dano < 0) dano = 0;
                        alvo.receberDano(dano);
                } else {
                        System.out.println(this.nome + "errou o ataque!");
                }
        }

        @Override
        public void receberDano(int dano) {
                this.vidaAtual -= dano;
                if(this.vidaAtual <= 0) vidaAtual = 0;
                System.out.println(this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.vidaAtual);
        }

        //Implementação das habilidades
        @Override
        public void usarHabilidade1 (Atacavel alvo) {
                System.out.println(this.nome + " tenta usar " + (habilidades[0] != null ? habilidades[0] : "Habilidade1") + "!");
                atacar(alvo);
        }

        @Override
        public void usarHabilidade2 (Atacavel alvo) {
                System.out.println(this.nome + " tenta usar " + (habilidades[1] != null ? habilidades[1] : "Habilidade2") + "!");
                atacar(alvo);
        }

        @Override
        public void usarHabilidade3 (Atacavel alvo) {
                System.out.println(this.nome + " tenta usar " + (habilidades[2] != null ? habilidades[2] : "Habilidade3") + "!");
                atacar(alvo);
        }

        @Override
        public void usarHabilidadeEspecial (Atacavel alvo) {
                System.out.println(this.nome + " tenta usar habilidade especial!");
                // vazio, pois, necessita que uma subclasse o reescreva
        }

        public void aumentarVida(int valor) {
                this.vidaAtual += valor;
                if(this.vidaAtual > this.vidaMaxima) this.vidaAtual = this.vidaMaxima;
        }

        public void aumentarAtaque(int valor) {
                this.ataque += valor;
        }

        public void aumentarDefesa(int valor) {
                this.defesa += valor;
        }

        // Getters
        @Override
        public String getNome() { return nome; }
        public int getVidaAtual() { return vidaAtual; }
        public int getVidaMaxima() { return vidaMaxima; }
        public int getAtaque() { return ataque; }
        public int getEsquiva() { return esquiva; }
        public int getPrecisao() { return precisao; }
        public Arma getArma() { return arma; }
        @Override
        public int getDefesa() { return defesa; }
        public String[] getHabilidades(){return habilidades;}

        // Setters

        public void setNome(String nome) {this.nome = nome; }

        public void setVidaAtual(int vidaAtual) {this.vidaAtual = vidaAtual; }

        public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }

        public void setAtaque(int ataque) { this.ataque = ataque; }

        public void setDefesa(int defesa) { this.defesa = defesa; }

        public void setEsquiva(int esquiva) { this.esquiva = esquiva; }

        public void setPrecisao(int precisao) { this.precisao = precisao; }

        public void setHabilidades(String[] habilidades) { this.habilidades = habilidades; }

        public void setArma(Arma arma){this.arma = arma;}
}