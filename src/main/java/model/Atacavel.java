package model;

public interface Atacavel {
    void atacar(Atacavel inimigo);
    void receberDano(int dano);
    String getNome();
    int getDefesa();
    int getPrecisao();
}