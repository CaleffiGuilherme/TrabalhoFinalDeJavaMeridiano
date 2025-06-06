package main;

import crud.ACrud;
import crud.PCrud;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PCrud pcrud = new PCrud();
        ACrud acrud = new ACrud();

        int opcao;
        do {
            System.out.println("\nMenu Principal\n");
            System.out.println("1 - Gerenciar Personagem");
            System.out.println("2 - Gerenciar Armas");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> pcrud.menu();
                case 2 -> acrud.menu();
            }
        } while (opcao != 0);

    }
}
