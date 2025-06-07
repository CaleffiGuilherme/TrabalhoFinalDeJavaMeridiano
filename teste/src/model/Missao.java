package model;

import java.util.ArrayList;
import java.util.List;

import model.inimigo.Inimigo;

public class Missao {
    private String nome;
    private String descricao;
    private List<Inimigo> inimigos;
    private List<Consumivel> itens;

    public Missao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.inimigos = new ArrayList<>();
        this.itens = new ArrayList<>();
    }

    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.add(inimigo);
    }

    public void adicionarItem(Consumivel item) {
        itens.add(item);
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public List<Consumivel> getItens() {
        return itens;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void mostrarInfo() {
        System.out.println("Você está em: " + nome);
        System.out.println(descricao);

        System.out.println("\nInimigos aqui:");
        for (Inimigo inimigo : inimigos) {
            System.out.println("- " + inimigo.getNome());
        }

        System.out.println("\nItens encontrados:");
        for (Consumivel item : itens) {
            System.out.println("- " + item.getNome() + " (x" + item.getQuantidade() + ")");
        }
    }
}
