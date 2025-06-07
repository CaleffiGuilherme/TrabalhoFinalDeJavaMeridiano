package model;

import model.player.Personagem;
import model.Efeito;

public class Consumivel {
    private String nome;
    private String descricao;
    private int quantidade;
    private Efeito efeito;
    private int valor;

    public Consumivel(String nome, String descricao, int quantidade, Efeito efeito, int valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.efeito = efeito;
        this.valor = valor;
    }

    public void usar(Personagem alvo) {
        if (quantidade <= 0) {
            System.out.println("Item " + nome + " esgotado!");
            return;
        }

        quantidade--;

        switch (efeito) {
            case CURA:
                alvo.aumentarVida(valor);
                System.out.println(alvo.getNome() + " usou " + nome + " e recuperou " + valor + " de vida!");
                break;

            case AUMENTO_ATAQUE:
                alvo.aumentarAtaque(valor);
                System.out.println(alvo.getNome() + " usou " + nome + " e aumentou o ataque em " + valor + "!");
                break;

            case AUMENTO_DEFESA:
                alvo.aumentarDefesa(valor);
                System.out.println(alvo.getNome() + " usou " + nome + " e aumentou a defesa em " + valor + "!");
                break;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
