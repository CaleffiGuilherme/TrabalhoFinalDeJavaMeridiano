package crud;

import model.player.Cowboy;
import model.player.ForaDaLei;
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
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 ->  criar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> deletar();
                case 0 -> sair();
            }
        } while (opcao != 0);
    }

    public void criar() {
        System.out.println("Nome do personagem: ");
        String nome = scanner.nextLine();

        System.out.println("\n1 - Cowboy\n");
        System.out.println("2 - Fora da Lei");

        if (scanner.nextLine().equals("1")) {
            Personagem novoPersonagem = new Cowboy(nome);
            personagens.add(novoPersonagem);
            System.out.println("Personagem criado com sucesso!");
        } else {
            Personagem novoPersonagem = new ForaDaLei(nome);
            personagens.add(novoPersonagem);
            System.out.println("Personagem criado com sucesso!");
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

    public void sair() {
        System.out.println("Saindo do menu...");
    }

}
