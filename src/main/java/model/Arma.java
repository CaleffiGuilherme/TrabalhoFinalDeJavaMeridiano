package model;

public class Arma {
    private String nome;
    private int bonusAtaque;
    private int bonusPrecisao;

    public Arma(String nome, int bonusAtaque, int bonusPrecisao) {
        this.nome = nome;
        this.bonusAtaque = bonusAtaque;
        this.bonusPrecisao = bonusPrecisao;
    }

    // Getters
    public String getNome() { return nome; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusPrecisao() { return bonusPrecisao; }

    // Setters
    public void setNome(String nome) { this.nome = nome;}
    public void setBonusAtaque(int bonusAtaque) { this.bonusAtaque = bonusAtaque; }
    public void setBonusPrecisao (int bonusPrecisao) { this.bonusPrecisao = bonusPrecisao; }

    @Override
    public String toString() {
        return nome + " (+" + bonusAtaque + " ATK, +" + bonusPrecisao + " PRC)";
    }
}
