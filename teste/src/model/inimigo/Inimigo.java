package model.inimigo;

import model.Atacavel;
import model.Especial;

public class Inimigo implements Atacavel, Especial {
    protected String nome;
    protected int vidaAtual;
    protected int vidaMaxima;
    protected int ataque;
    protected int defesa;
    protected int precisao;
    protected String arma;

    protected String[] habilidades = new String[3];
    protected String habilidadeEspecial = "";

    public Inimigo(String nome, int vidaAtual, int vidaMaxima, int ataque, int defesa, int precisao, String arma) {
        this.nome = nome;
        this.vidaAtual = vidaAtual;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
        this.precisao = precisao;
        this.arma = arma;
    }

    public void atacar(Atacavel alvo) {
        int dano = this.ataque - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        System.out.println(this.nome + " atacou " + alvo.getNome() + " causando " + dano + " de dano.");
    }

    @Override
    public void receberDano(int dano) {
        this.vidaAtual -= dano;
        if (vidaAtual < 0) {
            vidaAtual = 0;
            System.out.println(this.nome + " recebeu " + this.vidaAtual);
        }
        System.out.println(this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.vidaAtual);
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public int getDefesa() { return defesa; }

    @Override
    public void usarHabilidade1(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[0] + "!");
        atacar(alvo);
    }

    @Override
    public void usarHabilidade2(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[1] + "!");
        atacar(alvo);
    }

    @Override
    public void usarHabilidade3(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[2] + "!");
        atacar(alvo);
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidadeEspecial + "!");
        atacar(alvo);
    }

    public int getVidaAtual() {
        return vidaAtual;
    }
}
