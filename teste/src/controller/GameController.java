package controller;


import java.util.ArrayList;
import java.util.List;
import model.player.Personagem;
import model.player.Cowboy;
import model.player.ForaDaLei;
import model.inimigo.Inimigo;
import model.Missao;
import model.Consumivel;
import model.Efeito;

public class GameController {

    private Personagem player;
    private List<Missao> fases;

    public GameController() {
        fases = new ArrayList<>();
        inicializarFases();
    }

    public void criarPersonagem(String nome, int escolhaClasse) {
        if (escolhaClasse == 1) {
            player = new Cowboy(nome);
        } else {
            player = new ForaDaLei(nome);
        }
    }

    public Personagem getPlayer() {
        return player;
    }

    public List<Missao> getFases() {
        return fases;
    }

    public boolean executarCombate(Inimigo inimigo, int escolha) {
        switch (escolha) {
            case 1 -> player.atacar(inimigo);
            case 2 -> player.usarHabilidade1(inimigo);
            case 3 -> player.usarHabilidade2(inimigo);
            case 4 -> player.usarHabilidade3(inimigo);
            default -> {
                System.out.println("Ação inválida!");
                return false;
            }
        }
        return true;
    }

    public void inimigoAtaca(Inimigo inimigo) {
        inimigo.atacar(player);
    }

    public boolean jogadorDerrotado() {
        return player.getVidaAtual() <= 0;
    }

    public boolean inimigoDerrotado(Inimigo inimigo) {
        return inimigo.getVidaAtual() <= 0;
    }

    private void inicializarFases() {
        Missao saloon = new Missao("Saloon Abandonado", "Um velho bar cheio de teias de aranha.");
        saloon.adicionarInimigo(new model.inimigo.Pistoleiro("Pistoleiro bêbado"));
        saloon.adicionarItem(new Consumivel("Whisky", "Recupera 20 de vida", 1, Efeito.CURA, 20));

        Missao mina = new Missao("Mina Escura", "Uma mina subterrânea, o ar é pesado.");
        mina.adicionarInimigo(new model.inimigo.Pistoleiro("Pistoleiro sombrio"));
        mina.adicionarInimigo(new model.inimigo.Pistoleiro("Pistoleiro das cavernas"));
        mina.adicionarItem(new Consumivel("Dinamite", "Aumenta ataque em 10", 1, Efeito.AUMENTO_ATAQUE, 10));

        fases.add(saloon);
        fases.add(mina);
    }


}
