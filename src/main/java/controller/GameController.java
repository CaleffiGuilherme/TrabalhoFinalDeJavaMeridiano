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



}
