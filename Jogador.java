package Foda;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {
    public String nome;
    public Classe cla_jogador; // Classe contem todos os parametros
    public Inventario inv_jogador;
    void CriarPersonagem(ArrayList<Classe> classes) {
        while (true) {
            if(classes.size() <= 0) {
                System.out.println("Não há classes cadastradas, por favor crie uma classe ou restaure padrões");
            }
            else {
                String prompt;
                String nome;
                Scanner entrada = new Scanner(System.in);
                while (true) {
                    boolean ConfirmaNome = true;
                    while (ConfirmaNome) {
                        System.out.println("Qual seu nome, aventureiro?");
                        System.out.println(">> ");
                        nome = entrada.nextLine();
                        System.out.printf("\nSeu nome é '%s'? \n(S - Confirma)", nome);
                        prompt = entrada.nextLine();
                        switch (prompt) {
                            default:
                                ;
                            case"s":
                            case"S":
                                this.nome = nome;
                                ConfirmaNome = false;
                        }
                    }
                    System.out.println("Escolha sua classe.");
                    for (Classe classe : classes) {
                        System.out.printf("%sNome:%s\nDescrição%s", classe.cla_nome, classe.cla_lore);
                    }

                }
            }
        }
    }
}
