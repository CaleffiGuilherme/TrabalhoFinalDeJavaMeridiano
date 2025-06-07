package model;

public interface Atacavel {
    void atacar(Atacavel inimigo);

    void receberDano(int dado);
    String getNome();
    int getDefesa();


    void usarHabilidadeEspecial(Atacavel alvo);
}