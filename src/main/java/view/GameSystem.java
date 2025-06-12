package view;

import controller.GameController;
import model.player.Cowboy;
import model.player.ForaDaLei;
import model.inimigo.Inimigo;
import model.inimigo.Bandido;
import model.inimigo.Bebado;
import model.inimigo.Pistoleiro;
import model.inimigo.CacadorDeRecompensa;
import model.inimigo.boss.PeGrande;
import model.inimigo.boss.Xerife;
import model.Missao;
import model.player.Personagem;

import java.util.List;
import java.util.Scanner;

public class GameSystem {

    private final Scanner scanner;
    private final GameController controller;

    public GameSystem() {
        scanner = new Scanner(System.in);
        controller = new GameController(this);
    }

    public void exibirLogo() {
        // Sua arte ASCII do logo
        System.out.println(" ____  ____  ____  ____  ____  ____  ____  ____  ____ ____  ____  ____  ____  ____  ____  ____  ____  ____ ____  ____  ____  ____  ____  ____  ____  ____  ____ ____ ____");
        System.out.println();
        System.out.println("           Seja Bem-vindo ao Faroeste!!!             ");
        System.out.println();
        System.out.println("|/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/| |/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/| |/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/| |/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/|");
        System.out.println();
        System.out.println("                 ______                                     ");
        System.out.println("                |  <>  |                                    ");
        System.out.println("         _______|______|______                              ");
        System.out.println("                  |  |                                      ");
        System.out.println("                  |  |____| |         ____                   ");
        System.out.println("                  |  |______|      __|____|__                ");
        System.out.println("            | |___|  |                 ||                    ");
        System.out.println("            |_____|  |              |__||                    ");
        System.out.println("                  |  |                 ||__|                 ");
        System.out.println("                  |  |                 ||                    ");
        System.out.println();
    }

    public void iniciarJogo() {
        boolean jogarNovamente = true;
        while (jogarNovamente) {
            exibirLogo();
            escolherClasse();

            boolean jogadorPerdeuNaRodada = false;

            List<Missao> fases = controller.getFases();

            for (Missao fase : fases) {
                System.out.println("\n=== Nova Fase ===");
                fase.mostrarInfo();

                for (Inimigo inimigo : fase.getInimigos()) {
                    System.out.println("\nUm inimigo apareceu: " + inimigo.getNome());
                    displayEnemyStats(inimigo);
                    combate(inimigo);

                    if (controller.jogadorDerrotado()) {
                        System.out.println("\nQue pena! Você foi derrotado! Fim de jogo.");
                        jogadorPerdeuNaRodada = true;
                        break;
                    }
                }
                if (jogadorPerdeuNaRodada) {
                    break;
                }
                System.out.println("\nVocê venceu todos os inimigos desta fase!");
            }

            if (!jogadorPerdeuNaRodada) {
                System.out.println("\nParabéns! Você completou todas as fases!");
            }

            System.out.print("\nGostaria de jogar novamente? (S/N): ");
            String escolhaRejogar = scanner.nextLine().trim().toUpperCase();

            if (escolhaRejogar.equals("S")) {
                controller.resetGame();
            } else {
                jogarNovamente = false;
                System.out.println("\nObrigado por jogar! Até a próxima aventura no Velho Oeste.");
            }
        }
        scanner.close();
    }
    public void displayPlayerStats(Personagem player) {//não reconhce o Personagem
        System.out.println("\n███████████████████████████████████████████████");
        System.out.println("█                   JUSTICEIRO                █");
        System.out.println("███████████████████████████████████████████████");

        if (player != null) {
            System.out.printf("█ Nome: %-37s █\n", player.getNome());
            System.out.printf("█ Vida: %-37d █\n", player.getVidaAtual());
            System.out.printf("█ Ataque: %-35d █\n", player.getAtaque());

            // Bloco para atributos específicos de ForaDaLei ou Cowboy
            if (player instanceof ForaDaLei) {
                ForaDaLei foraDaLei = (ForaDaLei) player;
                System.out.println("\n");
                System.out.println("  +------------------------------------------------+ ");
                System.out.println("  |    W A N T E D  --  D E A D  O R  A L I V E    | ");
                System.out.println("  |                                                | ");
                System.out.println("  |  --------------------------------------------  | ");
                System.out.println("  | |            F O R A  D A  L E I             | | ");
                System.out.println("  | |   A T I V I D A D E : Assassino em série   | | ");
                System.out.println("  | |   P R E C A U Ç Â O : Armado e Perigoso    | | ");
                System.out.println("  |  --------------------------------------------  | ");
                System.out.println("  |     R E C O M P E N S A : $ 5.000.000.000      | ");
                System.out.println("  +------------------------------------------------+ ");
                System.out.println("\n");
            } else if (player instanceof Cowboy) {
                Cowboy cowboy = (Cowboy) player;
                System.out.println("█ Tipo: Cowboy                                █");
                System.out.printf("█ Precisão: %-33s █\n", cowboy.getPrecisao());
            } else {
                System.out.println("█ Tipo: Indefinido (Player Genérico)  █");
            }
        } else {
            System.out.println("█ Erro: Personagem do jogador não encontrado! █");
        }
        System.out.println("███████████████████████████████████████████████");
    }

    public void displayEnemyStats(Inimigo enemy) {
        System.out.println("\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓                    INIMIGO                  ▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        if (enemy != null) {
            System.out.printf("▓ Nome: %-37s ▓\n", enemy.getNome());
            System.out.printf("▓ Vida: %-37d ▓\n", enemy.getVidaAtual());
            System.out.printf("▓ Ataque: %-35d ▓\n", enemy.getAtaque());

            // Bloco para atributos específicos de tipos de inimigos ou Bosses
            if (enemy instanceof Pistoleiro) {
                System.out.println("▓ Tipo: Pistoleiro                            ▓");
            } else if (enemy instanceof Bandido) {
                System.out.println("▓ Tipo: Bandido                               ▓");
            } else if (enemy instanceof Bebado) {
                System.out.println("▓ Tipo: Bêbado                                ▓");
            } else if (enemy instanceof CacadorDeRecompensa) {
                System.out.println("▓ Tipo: Caçador de Recompensa                 ▓");
            } else if (enemy instanceof PeGrande) {
                System.out.println("▓ Tipo: BOSS - Pé Grande                      ▓");
            } else if (enemy instanceof Xerife) {
                System.out.println("▓ Tipo: BOSS - Xerife                         ▓");
            } else {
                System.out.println("▓ Tipo: Desconhecido (Inimigo Genérico)       ▓");
            }
        } else {
            System.out.println("▓ Erro: Inimigo não encontrado!                   ▓");
        }
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
    }

    public void displayBattleStatus(Personagem player, Inimigo enemy) {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║            STATUS DA BATALHA             ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf("   Justiceiro: %-15s Vida: %-10d \n", player.getNome(), player.getVidaAtual());
        System.out.printf("   Inimigo: %-13s Vida: %-10d    \n", enemy.getNome(), enemy.getVidaAtual());
        System.out.println("╚══════════════════════════════════════════╝");
    }

    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) { /* Ignora o erro e continua */ }
    }

    private void escolherClasse() {
        System.out.println("Escolha sua classe:");
        System.out.println("1 - Cowboy");
        System.out.println("2 - Fora da Lei");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        controller.criarPersonagem(nome, escolha);

        displayPlayerStats(controller.getPlayer());
    }


    private void combate(Inimigo inimigo) {
        boolean emCombate = true;

        while (emCombate) {

            System.out.println("\nSua vez! Escolha a ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Usar Habilidade 1");
            System.out.println("3 - Usar Habilidade 2");
            System.out.println("4 - Usar Habilidade 3");

            int acao = scanner.nextInt();
            scanner.nextLine();

            boolean acaoValida = controller.executarCombate(inimigo, acao);
            if (!acaoValida) continue;

            if (controller.inimigoDerrotado(inimigo)) {
                System.out.println("\n");
                System.out.println("   ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄   ");
                System.out.println("   ███                                     ███   ");
                System.out.println("   ███  PARABÉNS, VOCÊ VENCEU O COMBATE!   ███   ");
                System.out.println("   ███ Você derrotou o " + inimigo.getNome() + "!  ███   ");
                System.out.println("   ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀   ");
                System.out.println("\n");
                emCombate = false;
                continue;
            }

            System.out.println("\n--- Turno do inimigo ---");
            controller.inimigoAtaca(inimigo);

            if (controller.jogadorDerrotado()) {
                System.out.println("\nVocê foi derrotado!");
                System.out.println("\n");
                System.out.println("   .------------------------------------.   ");
                System.out.println("   |   O Velho Oeste te engoliu...      |   ");
                System.out.println("   |       Mas a lenda pode recomeçar!  |   ");
                System.out.println("   '------------------------------------'   ");
                System.out.println("\n");
                emCombate = false;
            }
        }
    }
}