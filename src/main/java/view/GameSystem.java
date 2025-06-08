package view;

import controller.GameController;
import model.inimigo.Inimigo;
import model.Missao;

import java.util.List;
import java.util.Scanner;

public class GameSystem {

    private final Scanner scanner;
    private final GameController controller;

    public GameSystem() {
        scanner = new Scanner(System.in);
        controller = new GameController();
    }

    public void exibirLogo() {
        System.out.println(" ____  ____  ____  ____  ____  ____  ____  ____  ____ ____  ____  ____  ____  ____  ____  ____  ____  ____ ____  ____  ____  ____  ____  ____  ____  ____  ____ ____ ____");
        System.out.println();
        System.out.println("           Seja Bem - vindo ao Faroeste!!!             ");
        System.out.println();
        System.out.println("|/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/| |/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/| |/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/| |/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/\\||/\\/|");
        System.out.println();
        System.out.println("                 ______                                     ");
        System.out.println("                |  <>  |                                    ");
        System.out.println("         _______|______|______                              ");
        System.out.println("                  |  |                                      ");
        System.out.println("                  |  |____| |        ____                   ");
        System.out.println("                  |  |______|     __|____|__                ");
        System.out.println("            | |___|  |                ||                    ");
        System.out.println("            |_____|  |             |__||                    ");
        System.out.println("                  |  |                ||__|                 ");
        System.out.println("                  |  |                ||                    ");
        System.out.println();
    }

    public void iniciarJogo() {
        exibirLogo();
        escolherClasse();

        List<Missao> fases = controller.getFases();

        for (Missao fase : fases) {
            System.out.println("\n=== Nova Fase ===");
            fase.mostrarInfo();

            for (Inimigo inimigo : fase.getInimigos()) {
                System.out.println("\nUm inimigo apareceu: " + inimigo.getNome());
                combate(inimigo);

                if (controller.jogadorDerrotado()) {
                    System.out.println("\nQue pena! Você foi derrotado! Fim de jogo.");
                    return;
                }
            }

            System.out.println("\nVocê venceu todos os inimigos desta fase!");
        }

        System.out.println("\nParabéns! Você completou todas as fases!");
    }

    private void escolherClasse() {
        System.out.println("Escolha sua classe:");
        System.out.println("1 - Cowboy");
        System.out.println("2 - Fora da Lei");

        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir \n

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        controller.criarPersonagem(nome, escolha);
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

            boolean acaoValida = controller.executarCombate(inimigo, acao);
            if (!acaoValida) continue;

            if (controller.inimigoDerrotado(inimigo)) {
                System.out.println("\nVocê derrotou " + inimigo.getNome() + "!");
                emCombate = false;
                continue;
            }

            System.out.println("\n--- Turno do inimigo ---");
            controller.inimigoAtaca(inimigo);

            if (controller.jogadorDerrotado()) {
                System.out.println("\nVocê foi derrotado!");
                emCombate = false;
            }
        }
    }
}
