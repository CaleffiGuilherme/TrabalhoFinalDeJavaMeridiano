package model.inimigo;

import model.Atacavel;
import model.Habilidade;

public abstract class Inimigo implements Atacavel, Habilidade {
    protected String nome;
    protected int vidaAtual;
    protected int vidaMaxima;
    protected int ataque;
    protected int defesa;
    protected int precisao;
    protected String arma;

    protected String[] habilidades = new  String[3];
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

    // Foi atribuido override pois era isso ou tornar a classe abstrata
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

    public void usarHabilidade1(Atacavel alvo) {
        System.out.println(this.nome + " tenta usar " + (habilidades[0] != null ? habilidades[0] : "Habilidade1") + "!");
        atacar(alvo);
    }

    public void usarHabilidade2(Atacavel alvo) {
        System.out.println(this.nome + " tenta usar " + (habilidades[1] != null ? habilidades[1] : "Habilidade2") + "!");
        atacar(alvo);
    }

    public void usarHabilidade3(Atacavel alvo) {
        System.out.println(this.nome + " tenta usar " + (habilidades[2] != null ? habilidades[2] : "Habilidade3") + "!");
        atacar(alvo);
    }



    @Override
    public String getNome() { return nome; }
    @Override
    public int getDefesa() { return defesa; }
    public int getVidaAtual() { return vidaAtual; }
    @Override
    public int getPrecisao() {return precisao;}

}
