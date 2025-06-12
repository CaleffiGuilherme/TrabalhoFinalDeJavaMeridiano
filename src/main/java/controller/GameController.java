package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.player.Personagem;
import model.player.Cowboy;
import model.player.ForaDaLei;
import model.inimigo.Inimigo;
import model.Missao;
import model.Consumivel;
import database.FileManager;
import model.fase.MissaoFactory;

public class GameController {

    private static final String SAVE_PATH = "src/main/java/database/save/save.txt";

    private Personagem player;
    private List<Missao> fases;
    private List<Consumivel> inventarioConsumiveis = new ArrayList<>();

    public GameController() {
        fases = new ArrayList<>();
        inicializarFases();
    }

    // INVENTÁRIO DE CONSUMÍVEIS
    public List<Consumivel> getConsumiveis() {
        return inventarioConsumiveis;
    }

    public void adicionarConsumivel(Consumivel consumivel) {
        inventarioConsumiveis.add(consumivel);
    }

    public boolean usarConsumivel(int index) {
        if (index < 0 || index >= inventarioConsumiveis.size()) {
            System.out.println("Consumível inválido.");
            return false;
        }

        Consumivel c = inventarioConsumiveis.get(index);
        c.usar(player);
        inventarioConsumiveis.remove(index);
        System.out.println("Você usou: " + c.getNome());
        return true;
    }

    public void salvarJogo(String file) {
        if (player == null) {
            System.out.println("Nenhum personagem foi criado ainda.");
            return;
        }

        StringBuilder dados = new StringBuilder();
        dados.append("Nome: ").append(player.getNome()).append("\n");
        dados.append("Classe: ").append(player.getClass().getSimpleName()).append("\n");
        dados.append("Vida Atual: ").append(player.getVidaAtual()).append("\n");
        dados.append("Missões:\n");

        for (Missao missao : fases) {
            dados.append("- ").append(missao.getNome()).append(": ").append(missao.getDescricao()).append("\n");
        }

        try {
            FileManager.salvarDados(SAVE_PATH, dados.toString());
            System.out.println("Jogo salvo com sucesso em: " + SAVE_PATH);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    public boolean carregarJogo(String file) {
        try {
            File arquivo = new File(SAVE_PATH);
            if (!arquivo.exists()) {
                System.err.println("Arquivo de salvamento não encontrado: " + SAVE_PATH);
                return false;
            }
            String conteudo = FileManager.carregarDados(SAVE_PATH);
            String[] linhas = conteudo.split("\n");

            String nome = linhas[0].split(": ")[1];
            String classe = linhas[1].split(": ")[1];
            int vida = Integer.parseInt(linhas[2].split(": ")[1]);

            if (classe.equals("Cowboy")) {
                player = new Cowboy(nome);
            } else {
                player = new ForaDaLei(nome);
            }
            player.setVidaAtual(vida);

            this.fases = MissaoFactory.criarMissoesPadrao();

            System.out.println("Jogo carregado com sucesso!");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao carregar o jogo: " + e.getMessage());
            return false;
        }
    }

    public void criarPersonagem(String nome, int escolhaClasse) {
        if (escolhaClasse == 1) {
            player = new Cowboy(nome);
        } else {
            player = new ForaDaLei(nome);
        }

        salvarJogo(SAVE_PATH);
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
        this.fases = MissaoFactory.criarMissoesPadrao();
    }

    public void resetGame() {
        this.player = null;
        this.fases = new ArrayList<>();
        this.inventarioConsumiveis.clear();
        inicializarFases();
        System.out.println("\nO jogo foi reiniciado. Prepare-se para uma nova aventura!");
    }
}