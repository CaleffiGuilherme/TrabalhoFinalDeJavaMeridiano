package model.inimigo;

import model.player.Personagem;

public class Inimigo {
    protected String nome;
    protected int vidaAtual;
    protected int vidaMaxima;
    protected int ataque;
    protected int defesa;
    protected int precisao;
    protected String arma;

    public Inimigo(String nome, int vidaAtual, int vidaMaxima, int ataque, int defesa, int precisao, String arma) {
        this.nome = nome;
        this.vidaAtual = vidaAtual;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
        this.precisao = precisao;
        this.arma = arma;
    }

    public void atacar(Inimigo personagem){
        int dano = this.ataque - personagem.getDefesa();
        if (dano < 0) dano = 0;
        personagem.receberDano(dano);
        System.out.println(this.nome + " atacou " + personagem.getNome() + " inferindo " + dano + " de dano.");
    }

    public void receberDano(int dano) {
        this.vidaAtual -= dano;
        if(this.vidaAtual <= 0) vidaAtual = 0;
    }

    public String getNome() { return nome; }
    public int getDefesa() { return defesa; }

}
