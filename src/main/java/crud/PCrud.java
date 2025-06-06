package crud;

import model.player.Personagem;
import java.util.ArrayList;
import java.util.Scanner;

public class PCrud {
    private ArrayList<Personagem> personagens = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\nCRUD - Personagem\n");
            System.out.println("1. Criar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Deletar");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 ->  criar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> deletar();
            }
        } while (opcao != 0);
    }

    public void criar() {
        System.out.println("Nome do personagem: ");
        String nome = scanner.nextLine();

        System.out.println("Vida máxima: ");
        int vidaMaxima = scanner.nextInt();

        System.out.println("Ataque: ");
        int ataque = scanner.nextInt();

        System.out.println("Defesa: ");
        int defesa = scanner.nextInt();

        System.out.println("Esquiva: ");
        int esquiva = scanner.nextInt();

        System.out.println("Precisão: ");
        int precisao = scanner.nextInt();
        scanner.nextLine();

        String[] habilidades = new String[3];
        for (int i = 0; i < habilidades.length; i++) {
            habilidades[i] = scanner.nextLine();
        }

    }

    public void listar() {
        if(personagens.isEmpty()) {
            System.out.println("Nenhum personagem foi encontrado.");
            return;
        }
        for (int i = 0; i < personagens.size(); i++) {
            Personagem p = personagens.get(i);
            System.out.println(i + " - " + p.getNome() + " | Vida: " + p.getVidaAtual() + "/" + p.getVidaMaxima() + " | Ataque: " + p.getAtaque() + " | Defesa: " + p.getDefesa() + " | Precisao: " + p.getPrecisao() + " | Esquiva: " + p.getEsquiva() + " | Precisao: " + p.getPrecisao());
        }
    }

    public void atualizar() {
        listar();
        System.out.println("Digite o índice do personagem para atualizar: ");
        int i = scanner.nextInt();
        scanner.nextLine();

        if (i < 0 && i <  personagens.size()) {
            Personagem p = personagens.get(i);

            System.out.println("Novo nome: ");
            p.setNome(scanner.nextLine());

            System.out.println("Nova Vida máxima: ");
            p.setVidaMaxima(scanner.nextInt());

            System.out.println("Novo Ataque: ");
            p.setAtaque(scanner.nextInt());

            System.out.println("Nova Defesa: ");
            p.setDefesa(scanner.nextInt());

            System.out.println("Nova Esquiva: ");
            p.setEsquiva(scanner.nextInt());

            System.out.println("Nova Precisão: ");
            p.setPrecisao(scanner.nextInt());
            scanner.nextLine();

            String[] novasHabilidades = new String[3];
            System.out.println("Novas habilidades:");
            for (int j = 0; j < 3; j++) {
                System.out.print("Habilidade " + (j + 1) + ": ");
                novasHabilidades[j] = scanner.nextLine();
            }
            p.setHabilidades(novasHabilidades);

        }
    }
        
    public void deletar() {
        listar();
        System.out.println("Digite o índice do personagem para deletar: ");
        int i = scanner.nextInt();
        if(i >= 0 && i < personagens.size()) {
            personagens.remove(i);
            System.out.println("Deletado com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

}
