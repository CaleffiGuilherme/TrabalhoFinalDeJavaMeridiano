package controller;


import java.util.ArrayList;
import java.util.List;

import model.player.Personagem;
import model.player.Cowboy;
import model.player.ForaDaLei;
import model.inimigo.Inimigo;
import model.inimigo.Pistoleiro;
import model.inimigo.Bandido;
import model.Missao;
import model.Consumivel;
import model.Efeito;
import view.GameSystem;

public class GameController {

    private Personagem player;
    private List<Missao> fases;
    private GameSystem gameSystem;

    public GameController(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
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
        if (this.player != null && inimigo != null) {
            gameSystem.displayBattleStatus(this.player, inimigo);
        }
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
        if (this.player != null && inimigo != null) {
            gameSystem.displayBattleStatus(this.player, inimigo);
        }
        return true;
    }

    public void inimigoAtaca(Inimigo inimigo) {
        inimigo.atacar(player);
        gameSystem.displayBattleStatus(this.player, inimigo);
    }

    public boolean jogadorDerrotado() {
        return this.player != null && this.player.getVidaAtual() <= 0;
    }

    public boolean inimigoDerrotado(Inimigo inimigo) {
        return inimigo != null && inimigo.getVidaAtual() <= 0;
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

    //exibi os atributos de um player e um inimigo especifico

    //novo métod0 para reiniciar o jogo
    public void resetGame() {
        this.player = null;
        this.fases = new ArrayList<>();
        inicializarFases();
        System.out.println("\nO jogo foi reiniciado. Prepare-se para uma nova aventura!");
    }



}
