package main;

import model.player.Cowboy;
import model.player.ForaDaLei;
import model.inimigo.Pistoleiro;
import model.Atacavel;
import view.GameSystem;

public class Main {
    public static void main(String[] args) {

        GameSystem game = new GameSystem();
        game.iniciarJogo();
    }
}
