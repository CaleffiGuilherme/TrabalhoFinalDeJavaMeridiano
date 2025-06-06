package crud;

import model.Arma;
import java.util.ArrayList;
import java.util.Scanner;

public class ACrud {
    private ArrayList<Arma> armas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\nCRUD - ARMA");
            System.out.println("1 - Criar");
            System.out.println("2 - Lista");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> deletar();
            }
        } while(opcao != 0);
    }

    private void criar() {
        System.out.print("Nome da arma: ");
        String nome = scanner.nextLine();
        System.out.print("Bônus de ataque: ");
        int ataque = scanner.nextInt();
        System.out.print("Bônus de precisão: ");
        int precisao = scanner.nextInt();
        armas.add(new Arma(nome, ataque, precisao));
        System.out.println("Arma criada.");
    }

    private void listar() {
        if (armas.isEmpty()) {
            System.out.println("Nenhuma arma criada.");
            return;
        }
        for (int i = 0; i < armas.size(); i++) {
            System.out.println(i + " - " + armas.get(i));
        }
    }

    private void atualizar() {
        listar();
        System.out.println("Digite o índice da arma a atualizar: ");
        int i = scanner.nextInt();
        scanner.nextLine();
        if (i >= 0 && i < armas.size()) {
            System.out.println("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.println("Novo bônus de ataque: ");
            int ataque = scanner.nextInt();
            System.out.println("Novo bônus de precisão: ");
            int precisao = scanner.nextInt();
            Arma arma = armas.get(i);
            arma.setNome(nome);
            arma.setBonusAtaque(ataque);
            arma.setBonusPrecisao(precisao);
            System.out.println("Atualizado com sucesso.");
        } else{
            System.out.println("Índice inválido.");
        }
    }

    private void deletar() {
        listar();
        System.out.println("Digite o índice da arma a deletar: ");
        int i = scanner.nextInt();
        if (i >= 0 && i < armas.size()) {
            armas.remove(i);
            System.out.println("Deletado com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

}
