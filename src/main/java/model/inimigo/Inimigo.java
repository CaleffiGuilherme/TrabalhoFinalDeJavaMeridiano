package model.inimigo;

import model.Atacavel;
import model.player.Personagem;

public class Inimigo implements Atacavel {
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
   }

    @Override
    public String getNome() { return nome; }
    @Override
    public int getDefesa() { return defesa; }

}
