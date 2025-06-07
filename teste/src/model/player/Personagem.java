package model.player;

import model.Atacavel;
import model.Especial;

public abstract class Personagem implements Atacavel, Especial {
    protected String nome;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int ataque;
    protected int defesa;
    protected int esquiva;
    protected int precisao;
    protected String arma;

    protected String[] habilidades = new String[3];

    public Personagem(String nome) {
        this.nome = nome;
    }

    @Override
    public void atacar(Atacavel inimigo) {
        System.out.println(this.nome + " tenta atacar " + inimigo.getNome() + " com " + this.arma);

        double chance = Math.random() * 100;

        if (chance <= this.precisao) {
            int dano = this.ataque - inimigo.getDefesa();
            if (dano < 0) dano = 0;
            inimigo.receberDano(dano);
            System.out.println(this.nome + " acertou causando " + dano + " de dano!");
        } else {
            System.out.println(this.nome + " errou o ataque!");
        }
    }

    @Override
    public void receberDano(int dano) {
        int danoFinal = Math.max(dano, 0);
        this.vidaAtual -= danoFinal;
        if (this.vidaAtual < 0) {
            this.vidaAtual = 0;
        }
        System.out.println(this.nome + " recebeu " + danoFinal + " de dano. Vida atual: " + this.vidaAtual);
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public int getDefesa() { return defesa; }

    public String[] getHabilidades() {
        return habilidades;
    }

    // Implementações padrão das habilidades especiais - podem ser sobrescritas
    @Override
    public void usarHabilidade1(Atacavel inimigo) {
        System.out.println(this.nome + " tenta usar " + (habilidades[0] != null ? habilidades[0] : "Habilidade1") + "!");
        atacar(inimigo);
    }

    @Override
    public void usarHabilidade2(Atacavel inimigo) {
        System.out.println(this.nome + " tenta usar " + (habilidades[1] != null ? habilidades[1] : "Habilidade2") + "!");
        atacar(inimigo);
    }

    @Override
    public void usarHabilidade3(Atacavel inimigo) {
        System.out.println(this.nome + " tenta usar " + (habilidades[2] != null ? habilidades[2] : "Habilidade3") + "!");
        atacar(inimigo);
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel inimigo) {
        System.out.println(this.nome + " tenta usar habilidade especial!");
        // implementação vazia padrão, subclasses podem sobrescrever
    }

    public void aumentarVida(int valor) {
        this.vidaAtual += valor;
        if (this.vidaAtual > this.vidaMaxima) {
            this.vidaAtual = this.vidaMaxima;
        }
    }

    public void aumentarAtaque(int valor) {
        this.ataque += valor;
    }

    public void aumentarDefesa(int valor) {
        this.defesa += valor;
    }

    public int getVidaAtual() {
    return vidaAtual;
    }
}
