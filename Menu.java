package Foda;
import java.util.Scanner;



public class Menu { // Menu principal para configurações, e inicialização de jogo
    public Scanner entrada = new Scanner(System.in); // Entrada
    Criador criador = new Criador();
    int num_niveis;

    // RETORNA MODO DE JOGO E NIVEIS P/CONSTRUTOR
    ConstrutorPartida Menu_P(Padrao padrao,ListaClasse classes, ListaMonstro monstros, ListaItens itens, ListaMagia magias,
                             Aleatorio aleatoriedade, ListaEventos eventos) {
        boolean flag1;
        boolean flag2;
        boolean flag3;
        // Retorna O Modo e número de niveis
        String prompt;
        System.out.print(">> ");
        prompt = entrada.nextLine();
        switch (prompt) {
            case "s":
            case "S":
                System.out.println("Adeus!");
                System.exit(0);
            case "1":
                // MENU PARA INICIAR JOGO
                String escolha;
                boolean flagJogar = true;
                while (flagJogar) {
                    OpcoesJogar();
                    System.out.print(">> ");
                    escolha = entrada.nextLine();
                    switch (escolha) {
                        // MENU PARA INICIAR JOGO - MODO NORMAL
                        case "1":
                            String escolha_normal;
                            boolean flagNormal = true;
                            while (flagNormal) {
                                OpcoesNormal();
                                System.out.print(">> ");
                                escolha_normal = entrada.nextLine();
                                switch (escolha_normal) {
                                    case "s":
                                    case "S":
                                        flagNormal = false;
                                        break;
                                    // NORMAL - 5 NIVEIS
                                    case "1":
                                        return new ConstrutorPartida("NORMAL",5);
                                    // NORMAL - 15 NIVEIS
                                    case "2":
                                        return new ConstrutorPartida("NORMAL",15);
                                    // NORMAL - 30 NIVEIS
                                    case "3":
                                        return new ConstrutorPartida("NORMAL",30);
                                    // NORMAL - X NIVEIS
                                    case "4":
                                        while(true){
                                        System.out.print("Número de niveis \n>> ");

                                        escolha_normal = entrada.nextLine().strip();
                                            try {
                                                num_niveis = Integer.parseInt(escolha_normal);
                                                return new ConstrutorPartida("NORMAL",num_niveis);
                                            }
                                            catch (Exception e) {
                                                System.out.println("Coloque um número, tente novamente!");
                                            }
                                        }
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                            break;
                        case "2":
                            // CONQUISTA (SEM FIM)
                            return new ConstrutorPartida("CONQUISTA",0);
                        case "3":
                            // EXIBE A HISTÓRIA
                            String historia = "207X, o Brasil está devastado. \n" +
                                    "No anarcocapitalista e decentralizado estado do Neo-Brasil, " +
                                    "o líder tecnológico de avanço mundial e conglomerado Frank Industries Inc. " +
                                    "também responsável pela mais valorizada criptomoeda de todos os tempos " +
                                    "π Coins acaba de conquistar o marco histórico da humanidade e " +
                                    "termina de finalizar sua primeira máquina do tempo movida a energia quântica.\n" +
                                    "Em uma certa área militarizada top secreta no estado do Acre, na primeira " +
                                    "ativação da máquina no tempo, " +
                                    "um dos antropologistas acidentalmente deixa resquícios do DNA in vitro de um " +
                                    "pterodáctilo alienígena conhecido como Pellizzauros Rex na " +
                                    "câmara de ativação da mecânica quântica resultando " +
                                    "em uma cascata de ressonância que causa um " +
                                    "enorme pulso eletromagnético no qual destruiu " +
                                    "todos os sistemas de comunicações e eletrônicos " +
                                    "no mundo(exceto os patenteados pela Frank Industries).\n" +
                                    "Para conter o incidente, a Frank Industries " +
                                    "deixou em quarentena o antigo estado brasileiro por " +
                                    "conta das anomalias temporais e espaciais causadas " +
                                    "nesse lugar, que agora é conhecido como a Zona dos F.O.D.A, " +
                                    "“F.O.D.A” “Fúteis. Otários. Desocupados. Amalucados.”  " +
                                    "são  denominadas as pessoas que entram na zona, que buscam entrar nessa " +
                                    "área perigosa por riqueza, aventura, ingenuidade, " +
                                    "satisfação pessoal ou simplesmente a falta do que fazer...\n" +
                                    " ... E você é um F.O.D.A.\n";
                            historia = printBonito(historia);
                            System.out.println(historia);
                            break;
                        case "s":
                        case "S":
                            // VOLTA PARA O MENU ANTERIOR
                            flagJogar = false;
                            break;
                        default:
                            // MENSAGEM PARA OPÇÃO INVÁLIDA
                            System.out.println("Opção inválida...");
                            break;
                    }
                }
                break;
            case "2":
                // MENU PARA EDIÇÃO
                String menu_edicao;
                flag1 = true;
                while (flag1) {
                    OpcoesEditar();
                    System.out.print(">> ");
                    menu_edicao = entrada.nextLine();
                    switch(menu_edicao) {
                        // EDIÇÃO PARA ITENS
                        case "1":
                            String menu_itens;
                            flag2 = true;
                            while (flag2) {
                                OpcoesEditarItens();
                                System.out.print(">> ");
                                menu_itens = entrada.nextLine();
                                switch (menu_itens)
                                {
                                    // EDIÇÃO ITENS - CONSUMIVEIS
                                    case "1":
                                        String menu_periciveis;
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            OpcoesEditarConsumiveis();
                                            System.out.print(">> ");
                                            menu_periciveis = entrada.nextLine();
                                            switch (menu_periciveis)
                                            {
                                                // EXIBE TODOS OS ITENS PERICIVEIS
                                                case "1":
                                                    itens.TotalConsumiveis();
                                                    break;
                                                // ADICIONA NOVO ITEM
                                                case "2":
                                                    criador.CriarItemConsumivel(itens.lista_consumiveis);
                                                    break;
                                                // MODIFICA ITEM EXISTENTE
                                                case "3":
                                                    criador.ModificarItemConsumivel(itens.lista_consumiveis);
                                                    break;
                                                // REMOVE ITEM EXISTENTE
                                                case "4":
                                                    criador.RemoverItemConsumivel(itens.lista_consumiveis);
                                                    break;
                                                // VOLTA PARA O MENU ANTERIOR
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                //  CASO OPÇÃO INVÀLIDA
                                                default:
                                                    System.out.println("Opção inválida...");
                                                    break;
                                            }
                                        }
                                        // #############
                                        // Break dps do switch case é necessario para fechar switch
                                        break;
                                    // MENU EDIÇÃO - ARMAS
                                    case "2":
                                        String menu_armas;
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            // Função para exibir interface
                                            OpcoesEditarArmas();
                                            System.out.print(">> ");
                                            menu_armas = entrada.nextLine();
                                            switch (menu_armas)
                                            {
                                                // EXIBE TODAS AS ARMAS
                                                case "1":
                                                    itens.TotalArmas();
                                                    break;
                                                // ADICIONAR UMA NOVA ARMA
                                                case "2":
                                                    criador.CriarItemArma(itens.lista_armas);
                                                    break;
                                                // MODIFICAR UMA ARMA EXISTENTE:
                                                case "3":
                                                    criador.ModificarItemArma(itens.lista_armas);
                                                    break;
                                                // DELETAR UMA ARMA EXISTENTE
                                                case "4":
                                                    criador.RemoverItemArma(itens.lista_armas);
                                                    break;
                                                // VOLTAR PARA O MENU ANTERIOR
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                // CASO OPÇÃO INVÁLIDA
                                                default:
                                                    System.out.println("Opção inválida...");
                                                    break;
                                            }
                                        }
                                        // BREAK NECESSARIO P/SWITCH
                                        break;
                                    case "3":
                                        // MENU EDIÇÂO ITEMS - ARMADURAS
                                        String menu_armaduras;
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            OpcoesEditarArmaduras();
                                            System.out.print(">>");
                                            menu_armaduras = entrada.nextLine();
                                            switch (menu_armaduras)
                                            {
                                                // EXIBE TODAS AS ARMADURAS
                                                case "1":
                                                    itens.TotalArmaduras();
                                                    break;
                                                // ADICIONA NOVA ARMADURA
                                                case "2":
                                                    criador.CriarItemArmadura(itens.lista_armaduras);
                                                    break;
                                                // MODIFICA ARMADURA EXISTENTE
                                                case "3":
                                                    criador.ModificarItemArmadura(itens.lista_armaduras);
                                                    break;
                                                // REMOVE ARMADURA EXISTENTE DA LISTA DE ARMADURAS
                                                case "4":
                                                    criador.RemoverItemArmadura(itens.lista_armaduras);
                                                    break;
                                                // VOLTAR AO MENU ANTERIOR
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                // CASO OPÇÃO INVÁLIDA
                                                default:
                                                    System.out.println("Opção inválida");
                                                    break;
                                            }
                                        }
                                        break;
                                    // VOLTA PARA O MENU ANTERIOR
                                    case "s":
                                    case "S":
                                        flag2 = false;
                                        break;
                                    // CASO OPÇÃO INVÁLIDA
                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }
                            }
                            // BREAK P/SWITCH CASe
                            break;
                        // MENU EDIÇÃO - CLASSES
                        case "2":
                            String menu_classes;
                            flag2 = true;
                            while (flag2)
                            {
                                //
                                OpcoesEditarClasses();
                                System.out.print(">> ");
                                menu_classes = entrada.nextLine();
                                switch (menu_classes)
                                {
                                    case "1":
                                        classes.TotalClasses();
                                        break;
                                    case "2":
                                        criador.CriarClasse(classes.classes);
                                        break;
                                    case "3":
                                        criador.ModificarClasse(classes.classes);
                                        break;
                                    case "4":
                                        criador.RemoverClasse(classes.classes);
                                        break;
                                    case "s":
                                    case "S":
                                        flag2 = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                }
                             }
                            break;

                        case "3":
                            String menu_monstros;
                            flag2 = true;
                            while (flag2)
                            {
                                OpcoesEditarMonstros();
                                System.out.print(">> ");
                                menu_monstros = entrada.nextLine();
                                switch (menu_monstros)
                                {
                                    case "1":
                                        // Menu monstros normais
                                        String menu_m_normais;
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            OpcoesEditarM_Normais();
                                            System.out.print(">> ");
                                            menu_m_normais = entrada.nextLine();
                                            switch (menu_m_normais)
                                            {
                                                case "1":
                                                    monstros.TotalMonstrosNormais();
                                                    break;
                                                case "2":
                                                    criador.CriarMonstroNormal(monstros.monstros);
                                                    break;
                                                case "3":
                                                    criador.ModificarMonstroNormal(monstros.monstros);
                                                    break;
                                                case "4":
                                                    criador.RemoverMonstroNormal(monstros.monstros);
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida");
                                                    break;
                                            }
                                        }
                                        break;

                                    case "2":
                                        String menu_m_chefoes;
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            OpcoesEditarM_Chefoes();
                                            System.out.print(">> ");
                                            menu_m_chefoes = entrada.nextLine();
                                            switch (menu_m_chefoes)
                                            {
                                                case "1":
                                                    monstros.TotalChefes();
                                                    break;
                                                case "2":
                                                    criador.CriarMonstroChefe(monstros.chefes);
                                                    break;
                                                case "3":
                                                    criador.ModificarMonstroChefe(monstros.chefes);
                                                    break;
                                                case "4":
                                                    criador.RemoverMonstroChefe(monstros.chefes);
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida");
                                                    break;
                                            }
                                        }
                                        break;

                                    case "s":
                                    case "S":
                                        flag2 = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                        break;
                                }
                            }
                            break;

                        case "4":
                            String menu_magia;
                            flag2 = true;
                            while (flag2)
                            {
                                OpcoesEditarMagias();
                                System.out.print(">> ");
                                menu_magia = entrada.nextLine();
                                switch (menu_magia)
                                {
                                    case "1":
                                        magias.TotalMagias();
                                        break;
                                    case "2":
                                        criador.CriarMagia(magias.magias);
                                        break;
                                    case "3":
                                        criador.ModificarMagia(magias.magias);
                                        break;
                                    case "4":
                                        criador.RemoverMagia(magias.magias);
                                        break;
                                    case "s":
                                    case "S":
                                        flag2 = false;
                                        break;
                                    default:
                                        System.out.println("Opção invalida.");
                                        break;
                                }
                            }
                            break;
                        case "5":
                            String menu_evento;
                            flag2 = true;
                            while (flag2)
                            {
                                OpcoesEditarEventos();
                                menu_evento = entrada.nextLine();
                                switch (menu_evento)
                                {
                                    case "1":
                                        eventos.TotalEventos();
                                        break;
                                    case "2":
                                        criador.CriarEvento(eventos.getEventos());
                                        break;
                                    case "3":
                                        criador.ModificarEvento(eventos.getEventos());
                                        break;
                                    case "4":
                                        criador.RemoverEvento(eventos.getEventos());
                                        break;
                                    case "s":
                                    case "S":
                                        flag2 = false;
                                        break;
                                    default:
                                        System.out.println("Opção invalida.");
                                        break;
                                }
                            }
                            break;
                        case "6":
                            String menu_aleatorio;
                            flag2 = true;
                            while (flag2)
                            {
                                OpcoesEditarAleatorio();
                                String menu_do_menu;
                                menu_aleatorio = entrada.nextLine();
                                switch (menu_aleatorio)
                                {
                                    case "1":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Nome Pessoa.\n2. Deletar Nome." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getNome_pessoas());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getNome_pessoas());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "2":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Horário.\n2. Deletar Horário." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getHorarios());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getHorarios());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "3":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Lugar.\n2. Deletar Lugar." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getLugares());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getLugares());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "4":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Frase.\n2. Deletar Frase." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getFrases());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getFrases());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "5":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Objeto.\n2. Deletar Objeto." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getObjetos());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getObjetos());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "6":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Nome Música.\n2. Deletar Nome Música." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getNomes_musicas());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getNomes_musicas());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "7":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Nome Galo.\n2. Deletar Nome Galo." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getNomes_galo());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getNomes_galo());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "8":
                                        flag3 = true;
                                        while (flag3)
                                        {
                                            System.out.println("1. Adicionar Apelido Galo.\n2. Deletar Apelido Galo." +
                                                    "\nS. Voltar");
                                            menu_do_menu = entrada.nextLine();
                                            switch (menu_do_menu)
                                            {
                                                case "1":
                                                    criador.AdicionarNomeAleatorio(aleatoriedade.getApelidos_galo());
                                                    break;
                                                case "2":
                                                    criador.DeletarNomeAleatorio(aleatoriedade.getApelidos_galo());
                                                    break;
                                                case "s":
                                                case "S":
                                                    flag3 = false;
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "s":
                                    case "S":
                                        flag2 = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                            }
                            break;
                        case "7":
                            System.out.println("Restaurar valores iniciais?(Todas as modificações serão perdidas.)");
                            boolean restaurar_padrao = RetornaEscolha();
                            if(restaurar_padrao)
                            {
                                monstros.setMonstros(padrao.MonstrosNormaisPadrao());
                                monstros.setChefes(padrao.ChefoesPadrao());
                                magias.setMagias(padrao.MagiasPadrao());
                                eventos.setEventos(padrao.EventosPadrao());
                                classes.setClasses(padrao.ClassesPadrao());
                                itens.setLista_armas(padrao.ArmasPadrao());
                                itens.setLista_armaduras(padrao.ArmadurasPadrao());
                                itens.setLista_consumiveis(padrao.ConsumiveisPadrao());
                                aleatoriedade.getNome_pessoas().clear();
                                aleatoriedade.getHorarios().clear();
                                aleatoriedade.getLugares().clear();
                                aleatoriedade.getFrases().clear();
                                aleatoriedade.getNomes_musicas().clear();
                                aleatoriedade.getNomes_galo().clear();
                                aleatoriedade.getApelidos_galo().clear();
                                padrao.Aleatoriedade(aleatoriedade);
                                System.out.println("Valores padrões restaurados.");
                            }
                            break;
                        case "s":
                        case "S":
                            flag1 = false;
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

    //#######################################
    // INTERFACE DAS OPÇÕES.
    //#######################################
    //  MENU INICIAL
    //#######################################
    void OpcoesMenu() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Jogar.");
        System.out.println("2. Menu edição.");
        System.out.println("S. Sair.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    //#######################################
    //  MENU JOGAR
    //#######################################
    void OpcoesJogar() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Normal.");
        System.out.println("2. Modo Conquista.");
        System.out.println("3. História.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU MODO NORMAL
    //#######################################
    void OpcoesNormal() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Escolha a duração de sua jornada.");
        System.out.println("1. Aventura curta(5 Niveis)");
        System.out.println("2. Aventura média(15 Niveis)");
        System.out.println("3. Aventura longa(30 Niveis)");
        System.out.println("4. Aventura própria");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDIÇÃO
    //#######################################
    void OpcoesEditar() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Itens.");
        System.out.println("2. Classes.");
        System.out.println("3. Monstros.");
        System.out.println("4. Mágias.");
        System.out.println("5. Eventos.");
        System.out.println("6. Aleatoriedade.");
        System.out.println("7. Reiniciar valores padrões.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR CLASSES
    //#######################################
    void OpcoesEditarClasses()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir classes existentes.");
        System.out.println("2. Adicionar nova classe.");
        System.out.println("3. Modificar classe existente.");
        System.out.println("4. Deletar classe existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR MONTROS/CHEFES
    //#######################################
    void OpcoesEditarMonstros()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Monstros normais.");
        System.out.println("2. Chefões.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR MONSTROS NORMAIS
    //#######################################
    void OpcoesEditarM_Normais()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir monstros existentes.");
        System.out.println("2. Adicionar novo monstro.");
        System.out.println("3. Modificar monstro existente.");
        System.out.println("4. Deletar monstro existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR CHEFOES
    //#######################################
    void OpcoesEditarM_Chefoes()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir chefões existentes.");
        System.out.println("2. Adicionar novo chefão.");
        System.out.println("3. Modificar chefão existente.");
        System.out.println("4. Deletar chefão existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR ITENS - GERAL
    //#######################################
    void OpcoesEditarItens()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Itens consumiveis.");
        System.out.println("2. Armas.");
        System.out.println("3. Armaduras.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR ITENS - Consumiveis
    //#######################################
    void OpcoesEditarConsumiveis()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir itens consumiveis existentes.");
        System.out.println("2. Adicionar novo item consumivel.");
        System.out.println("3. Modificar item consumivel existente.");
        System.out.println("4. Deletar item consumivel existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR ITENS - ARMAS
    //#######################################
    void OpcoesEditarArmas()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir armas existentes.");
        System.out.println("2. Adicionar nova arma.");
        System.out.println("3. Modificar arma existente.");
        System.out.println("4. Deletar arma existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR ITENS - ARMADURAS
    //#######################################
    void OpcoesEditarArmaduras()
    {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir armaduras existentes.");
        System.out.println("2. Adicionar nova armadura.");
        System.out.println("3. Modificar armadura existente.");
        System.out.println("4. Deletar armadura existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR MAGIAS
    //###################################
    void OpcoesEditarMagias() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir magias existentes.");
        System.out.println("2. Adicionar nova magia.");
        System.out.println("3. Modificar magia existente.");
        System.out.println("4. Deletar magia existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR EVENTOS
    void OpcoesEditarEventos() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Exibir eventos existentes.");
        System.out.println("2. Criar novo evento.");
        System.out.println("3. Modificar evento existente");
        System.out.println("4. Deletar evento existente.");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    // MENU EDITAR STRINGS ALEATÓRIAS
    void OpcoesEditarAleatorio() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("1. Nomes Pessoa. (ex. 'Anacleto')");
        System.out.println("2. Horario. (ex. 'de manhã'/'de tarde')");
        System.out.println("3. Lugar. (ex. 'um hotel'/'uma escola')");
        System.out.println("4. Frase. (ex. 'Tem coisa que não faz sentido né?')");
        System.out.println("5. Objeto. (ex. 'uma patente'/'um tijolo')");
        System.out.println("6. Nome Música. (ex. 'God is Good'/'Amor de chocolate')");
        System.out.println("7. Nome de galo. (ex. 'Zeca'/'Bastião'");
        System.out.println("8. Apelidos de galo, (ex. Nome Galo o 'Temivel')");
        System.out.println("S. Voltar.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    String printBonito(String texto)
    {
        texto = texto.replaceAll("(.{64})", "$1\n");
        return texto;
    }
    boolean RetornaEscolha()
    {
        String dado;
        while (true) {
            System.out.println();
            System.out.print("(S/N)\n>> ");
            dado = entrada.nextLine();
            switch (dado){
                case "s":
                case "S":
                    return true;
                case "n":
                case "N":
                    return false;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
