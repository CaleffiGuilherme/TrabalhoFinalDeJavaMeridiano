package view;

import controller.GameController;
import crud.ACrud;
import crud.PCrud;
import model.Consumivel;
import model.inimigo.Inimigo;
import model.Missao;

import java.util.List;
import java.util.Scanner;

public class GameSystem {

    private final Scanner scanner;
    private final GameController controller;
    private final ACrud aCrud;
    private final PCrud pCrud;

    public GameSystem() {
        scanner = new Scanner(System.in);
        controller = new GameController();
        aCrud = new ACrud();
        pCrud = new PCrud();
    }

    private void pausar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void exibirLogo() {
        System.out.println("""
           __  _______.____  ._   __    ___   ___   
          /     \\ \\_   __/\\__   \\   \\__ \\ |   | /  _  \\   \\      \\  \\__  \\  
         /  \\ /  \\ |    _)  |       /   ||    |  \\|   |/  /\\  \\  /   |   \\  /   |   \\ 
        /    Y    \\|        \\ |    |   \\   ||    `   \\   /    |    \\/    |    \\/    |    \\
        \\_|_  /___  / |_|  /_/___  /_\\_|_  /\\_|_  /\\___  /
                \\/        \\/         \\/            \\/            \\/         \\/         \\/ 
        ▄▄▄▄RPG - FAROESTE▄▄▄▄
        """);
        pausar(1500);
    }

    public void iniciarJogo() {
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            exibirLogo();
            System.out.println("1 - Novo Jogo");
            System.out.println("2 - Carregar Jogo");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                escolherClasse();
            } else if (opcao == 2) {
                if (!controller.carregarJogo("save.txt")) {
                    System.out.println("Não foi possível carregar o jogo. Criando novo personagem...");
                    escolherClasse();
                }
            } else {
                System.out.println("Opção inválida! Iniciando novo jogo por padrão.");
                escolherClasse();
            }

            jogarMissao();

            System.out.print("\nGostaria de jogar novamente? (S/N): ");
            String escolhaRejogar = scanner.nextLine().trim().toUpperCase();
            jogarNovamente = escolhaRejogar.equals("S");

            if (jogarNovamente) {
                controller.resetGame();
            } else {
                System.out.println("\nObrigado por jogar! Até a próxima aventura no Velho Oeste.");
            }
        }
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
        pausar(800);
        System.out.println("\nPersonagem criado com sucesso!");
        pausar(1000);
        displayPlayerStats(controller.getPlayer());
    }

    private void jogarMissao() {
        boolean jogadorPerdeu = false;
        List<Missao> fases = controller.getFases();

        for (Missao fase : fases) {
            System.out.println("\n=== NOVA FASE ===");
            pausar(1000);
            fase.mostrarInfo();
            pausar(1000);

            for (Inimigo inimigo : fase.getInimigos()) {
                System.out.println("\nUm inimigo apareceu: " + inimigo.getNome());
                pausar(800);
                displayEnemyStats(inimigo);
                combate(inimigo);

                if (controller.jogadorDerrotado()) {
                    System.out.println("\nQue pena! Você foi derrotado! Fim de jogo.");
                    jogadorPerdeu = true;
                    break;
                }
            }

            if (jogadorPerdeu) break;

            System.out.println("\nVocê venceu todos os inimigos desta fase!");
            pausar(1200);
        }

        if (!jogadorPerdeu) {
            System.out.println("\nParabéns! Você completou todas as fases!");
            controller.salvarJogo("save.txt");
        }
    }

    private void combate(Inimigo inimigo) {
        boolean emCombate = true;

        while (emCombate) {
            boolean acaoExecutada = false;

            while (!acaoExecutada) {
                displayBattleStatus(controller.getPlayer(), inimigo);
                System.out.println("\nSua vez! Escolha a ação:");
                System.out.println("1 - Atacar");
                System.out.println("2 - Usar Habilidade");
                System.out.println("3 - Usar Consumível");
                System.out.println("4 - Menu Secreto");

                int acao = scanner.nextInt();
                scanner.nextLine();

                switch (acao) {
                    case 1 -> {
                        controller.executarCombate(inimigo, 1);
                        acaoExecutada = true;
                    }
                    case 2 -> acaoExecutada = escolherHabilidade(inimigo);
                    case 3 -> acaoExecutada = usarConsumivel();
                    case 4 -> menuSecreto();
                    default -> System.out.println("Ação inválida! Tente novamente.");
                }
            }

            if (controller.inimigoDerrotado(inimigo)) {
                System.out.println("\n");
                System.out.println("   ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄   ");
                System.out.println("   ███                                     ███   ");
                System.out.println("   ███  PARABÉNS, VOCÊ VENCEU O COMBATE!   ███   ");
                System.out.println("   ███ Você derrotou o " + inimigo.getNome() + "!  ███   ");
                System.out.println("   ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀   ");
                System.out.println("\n");
                emCombate = false;
                pausar(800);
                continue;
            }

            System.out.println("\n--- Turno do inimigo ---");
            pausar(800);
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

    private boolean escolherHabilidade(Inimigo inimigo) {
        System.out.println("\nEscolha uma habilidade:");
        System.out.println("1 - Habilidade 1");
        System.out.println("2 - Habilidade 2");
        System.out.println("3 - Habilidade 3");
        System.out.println("4 - Especial");
        System.out.println("0 - Voltar");

        int habilidade = scanner.nextInt();
        scanner.nextLine();

        if (habilidade == 0) return false;

        if (habilidade >= 1 && habilidade <= 4) {
            controller.executarCombate(inimigo, habilidade);
            return true;
        } else {
            System.out.println("Habilidade inválida!");
            return false;
        }
    }

    private boolean usarConsumivel() {
        List<Consumivel> consumiveis = controller.getConsumiveis();

        if (consumiveis.isEmpty()) {
            System.out.println("Você não possui consumíveis.");
            return false;
        }

        System.out.println("\n--- INVENTÁRIO DE CONSUMÍVEIS ---");
        for (int i = 0; i < consumiveis.size(); i++) {
            Consumivel c = consumiveis.get(i);
            System.out.println(i + " - " + c.getNome());
        }
        System.out.println("Digite o número do consumível que deseja usar (ou -1 para voltar): ");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == -1) return false;

        return controller.usarConsumivel(escolha);
    }

    private void menuSecreto() {
        int opcao;
        do {
            System.out.println("\n== MENU SECRETO ==");
            System.out.println("1 - CRUD de Armas");
            System.out.println("2 - CRUD de Personagens");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> aCrud.menu();
                case 2 -> pCrud.menu();
                case 0 -> System.out.println("Retornando ao combate...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void displayPlayerStats(model.player.Personagem player) {
        System.out.println("\n███████████████████████████████████████████████");
        System.out.println("█                   JOGADOR                   █");
        System.out.println("███████████████████████████████████████████████");

        if (player != null) {
            System.out.printf("█ Nome: %-37s █\n", player.getNome());
            System.out.printf("█ Vida: %-37d █\n", player.getVidaAtual());
            System.out.printf("█ Ataque: %-35d █\n", player.getAtaque());

            if (player instanceof model.player.ForaDaLei foraDaLei) {
                System.out.println("\n");
                System.out.println("  +------------------------------------------------+ ");
                System.out.println("  |    W A N T E D  --  D E A D  O R  A L I V E    | ");
                System.out.println("  |                                                | ");
                System.out.println("  |  --------------------------------------------  | ");
                System.out.println("  | |            F O R A  D A  L E I             | | ");
                System.out.println("  | |   A T I V I D A D E : Assassino em série   | | ");
                System.out.println("  | |   P R E C A U Ç Ã O : Armado e Perigoso    | | ");
                System.out.println("  |  --------------------------------------------  | ");
                System.out.println("  |     R E C O M P E N S A : $ 5.000.000.000      | ");
                System.out.println("  +------------------------------------------------+ ");
                System.out.println("\n");
            } else if (player instanceof model.player.Cowboy cowboy) {
                System.out.println("\n");
                System.out.println("  +------------------------------------------------+ ");
                System.out.println("  |    W A N T E D  --  D E A D  O R  A L I V E    | ");
                System.out.println("  |                                                | ");
                System.out.println("  |  --------------------------------------------  | ");
                System.out.println("  | |                C O W B O Y                 | | ");
                System.out.println("  | |   A T I V I D A D E : Lenda do Oeste       | | ");
                System.out.println("  | |   P R E C A U Ç Ã O : Olho da Morte        | | ");
                System.out.println("  |  --------------------------------------------  | ");
                System.out.println("  |     R E C O M P E N S A : $ 0.000.000.000      | ");
                System.out.println("  +------------------------------------------------+ ");
                System.out.println("\n");
            } else {
                System.out.println("█ Tipo: Indefinido (Player Genérico)          █");
            }
        } else {
            System.out.println("█ Erro: Personagem do jogador não encontrado! █");
        }
        System.out.println("███████████████████████████████████████████████");
    }

    private void displayEnemyStats(Inimigo enemy) {
        System.out.println("\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓                    INIMIGO                  ▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        if (enemy != null) {
            System.out.printf("▓ Nome: %-37s ▓\n", enemy.getNome());
            System.out.printf("▓ Vida: %-37d ▓\n", enemy.getVidaAtual());
            System.out.printf("▓ Ataque: %-35d ▓\n", enemy.getAtaque());

            if (enemy instanceof model.inimigo.Pistoleiro) {
                System.out.println("▓ Tipo: Pistoleiro                            ▓");
            } else if (enemy instanceof model.inimigo.Bandido) {
                System.out.println("▓ Tipo: Bandido                               ▓");
            } else if (enemy instanceof model.inimigo.Bebado) {
                System.out.println("▓ Tipo: Bêbado                                ▓");
            } else if (enemy instanceof model.inimigo.CacadorDeRecompensa) {
                System.out.println("▓ Tipo: Caçador de Recompensa                 ▓");
            } else if (enemy instanceof model.inimigo.boss.PeGrande) {
                System.out.println("▓ Tipo: BOSS - Pé Grande                      ▓");
            } else if (enemy instanceof model.inimigo.boss.Xerife) {
                System.out.println("▓ Tipo: BOSS - Xerife                         ▓");
            } else {
                System.out.println("▓ Tipo: Desconhecido (Inimigo Genérico)       ▓");
            }
        } else {
            System.out.println("▓ Erro: Inimigo não encontrado!                   ▓");
        }
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
    }

    private void displayBattleStatus(model.player.Personagem player, Inimigo enemy) {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║            STATUS DA BATALHA             ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf("   Jogador: %-15s Vida: %-10d \n", player.getNome(), player.getVidaAtual());
        System.out.printf("   Inimigo: %-13s Vida: %-10d    \n", enemy.getNome(), enemy.getVidaAtual());
        System.out.println("╚══════════════════════════════════════════╝");
    }

}