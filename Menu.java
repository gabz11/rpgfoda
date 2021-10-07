package Foda;
import java.util.Scanner;
import java.util.Random;

public class Menu { // Menu principal para configurações, e inicialização de jogo
    public Scanner entrada = new Scanner(System.in); // Entrada
    Random aleatorio = new Random();
    int num_niveis;

    // RETORNA MODO DE JOGO E NIVEIS P/CONSTRUTOR
    ConstrutorJogo Menu_P() {
        // Retorna O Modo e número de niveis
        String prompt;
        prompt = entrada.nextLine();
        ConstrutorJogo construtor = new ConstrutorJogo();
        switch (prompt) {
            case "s":
            case "S":
                System.out.println("Adeus!");
                System.exit(0);
            case "1":
                String escolha;
                boolean flagJogar = true;
                while (flagJogar) {
                    OpcoesJogar();
                    escolha = entrada.nextLine();
                    switch (escolha) {
                        case "1": // MENU MODO NORMAL
                            String escolha_normal;
                            boolean flagNormal = true;
                            while (flagNormal) {
                                OpcoesNormal();
                                escolha_normal = entrada.nextLine();
                                switch (escolha_normal) {
                                    case "s":
                                    case "S":
                                        flagNormal = false;
                                        break;
                                    case "1":
                                        construtor.setModo("NORMAL");
                                        construtor.setN_niveis(5);
                                        return construtor;
                                    case "2":
                                        construtor.setModo("NORMAL");
                                        construtor.setN_niveis(15);
                                        return construtor;
                                    case "3":
                                        construtor.setModo("NORMAL");
                                        construtor.setN_niveis(30);
                                        return construtor;
                                    case "4":
                                        while(true){
                                        System.out.print("Número de niveis >>");
                                        escolha_normal = entrada.nextLine().strip();
                                        try {
                                            num_niveis = Integer.parseInt(escolha_normal);
                                            construtor.setModo("NORMAL");
                                            construtor.setN_niveis(num_niveis);
                                            return construtor;
                                        }
                                        catch (Exception e) {
                                            System.out.println("Coloque um número, tente novamente!");
                                        }
                                        }
                                    case "5":
                                        construtor.setModo("NORMAL");
                                        construtor.setN_niveis(aleatorio.nextInt(99));
                                        return construtor;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                            break;
                        case "2":
                            System.out.println("Modo conquista");
                            construtor.setModo("CONQUISTA");
                            return construtor;
                        case "3":
                            System.out.println("História");
                            break;
                        case "s":
                        case "S":
                            flagJogar = false;
                            break;
                        default:
                            System.out.println("Opção inválida...");
                            break;
                    }
                }
                break;
            case "2":
                String menu_edicao;
                boolean flagEditar = true;
                while (flagEditar) {
                    OpcoesEditar();
                    menu_edicao = entrada.nextLine();
                    switch(menu_edicao) {

                        case "1":
                        System.out.println("Itens");
                        break;

                        case "2":
                        System.out.println("Classes");
                        break;

                        case "3":
                        System.out.println("Monstros");
                        break;

                        case "4":
                        System.out.println("Niveis");
                        break;

                        case "5":
                        System.out.println("Eventos");
                        break;

                        case "6":
                        flagEditar = false;
                        break;

                        default:
                        System.out.println("Opção inválida");
                        break;
                    }
                }
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        return null;
    }
    // INTERFACE
    //#######################################
    //  MENU INICIAL
    //#######################################
    void OpcoesMenu() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Jogar");
        System.out.println("2. Menu edição");
        System.out.println("S. Sair");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    //  MENU JOGAR
    //##########################################
    void OpcoesJogar() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Normal");
        System.out.println("2. Modo Conquista");
        System.out.println("3. História");
        System.out.println("S. Voltar");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU MODO NORMAL
    //###########################################
    void OpcoesNormal() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Escolha a duração de sua jornada.");
        System.out.println("1. Aventura curta(5 Niveis)");
        System.out.println("2. Aventura média(15 Niveis)");
        System.out.println("3. Aventura longa(30 Niveis)");
        System.out.println("4. Aventura própria");
        System.out.println("5. RNGJesus escolhe seu destino");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDIÇÃO
    //#############################################
    void OpcoesEditar() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Itens");
        System.out.println("2. Classes");
        System.out.println("3. Monstros");
        System.out.println("4. Niveis.");
        System.out.println("5. Eventos");
        System.out.println("S. Voltar");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}