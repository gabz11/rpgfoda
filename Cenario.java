package Foda;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cenario {
    Random rand = new Random();
    Scanner entrada = new Scanner(System.in);

    // LOOP DO GAMEPLAY
    void Partida(Jogador jogador, ConstrutorPartida partida, ListaMonstro monstros, ListaEventos eventos,
                 ListaMagia magias, Aleatorio aleatoriedade, ListaItens itens)
    {

        //##########################
        // Valores Globais Padrão ##
        //##########################
        int minimo_monstros_nivel = 3;
        int variacao_monstros_nivel = 1;
        int escalador_universal = 0; // escala 1 padrao, cada 5 aumenta
        int niveis_passados = 0;
        // inicializa cond de derrota
        boolean jogador_vivo = jogador.getHp_atual() > 0;
        // booleano para modo
        boolean aposentou;


        // se o modo for normal o jogador já começa com aposenta true, pois dependera no tanto de niveis
        aposentou = partida.getModo().equals("NORMAL");
        ItensInicio(jogador, itens, magias);
        InicioAventura(jogador); // Mensagem de inicio
        // Loop principal do jogo.

        while ((partida.getN_niveis() > 0 || !aposentou) && jogador_vivo)
        {
            if (niveis_passados >=5) {
                escalador_universal +=1;
                niveis_passados = 0;
            }
            jogador_vivo = jogador.getHp_atual() > 0;
            if(partida.getN_niveis() > 0 && jogador_vivo)
            {
            System.out.println("Niveis restantes: "+ partida.getN_niveis());
            }
            // Evento opcional antes do nivel.
            if(jogador_vivo)
            {
            FazerEvento(jogador, eventos.getEventos(), aleatoriedade);
            }
            // Nivel que o jogador combate os monstros
            jogador_vivo = jogador.getHp_atual() > 0;
            if(jogador_vivo)
            {
                Nivel(jogador, monstros.getMonstros(), itens, magias,
                        variacao_monstros_nivel, minimo_monstros_nivel, escalador_universal);
            }
            // Ultima parte do nivel
            jogador_vivo = jogador.getHp_atual() > 0;
            if(jogador_vivo)
            {
                // Luta com o chefão
                BATALHACHEFE(jogador, monstros.getChefes(), itens, magias, escalador_universal);
            }
            jogador_vivo = jogador.getHp_atual() > 0;
            if(jogador_vivo)
            {
                // FIM DE UM NIVEL
                // MODO NORMAL AVANÇA UM NIVEL
                niveis_passados += 1;
                if(partida.getModo().equals("NORMAL")) {
                    partida.setN_niveis(partida.getN_niveis() - 1);
                }
                System.out.printf("%s sobreviveu a fase!",jogador.getNome());
                System.out.println();
                Pausa();
                if(partida.getN_niveis() > 0 || partida.getModo().equals("CONQUISTA")) {
                    LevelUp(jogador);
                    Hub(jogador, itens, magias, aleatoriedade, escalador_universal);
                    if(partida.getModo().equals("CONQUISTA"))
                    {
                        // Modo conquista
                        aposentou = Aposentar(jogador);
                    }
                }
                // MODO CONQUISTA AQ
            }
        }
        // GAME OVER
        jogador_vivo = jogador.getHp_atual() > 0;
        if(jogador_vivo && partida.getModo().equals("NORMAL"))
        {
        System.out.println("Fim do jogo!");
        jogador.Metricas();
        Pausa();
        }
        else if(jogador_vivo && partida.getModo().equals("CONQUISTA"))
        {
            Final(jogador);
        }
        else
        {
            System.out.println();
            System.out.printf("Descanse em paz %s...",jogador.getNome());
            jogador.Metricas();
            Pausa();
            System.out.println();
        }
    }

    // FASE NIVEL EM SI
    void Nivel(Jogador jogador, ArrayList<MonstroNormal> monstros, ListaItens itens, ListaMagia magias,
                  int variacao_monstros,int minimo_monstros, int escalador_universal)
    {
        int numero_inimigos = NumeroMonstros(variacao_monstros,minimo_monstros, escalador_universal);

        ArrayList<MonstroNormal> monstros_nivel = SpawnMonstrosNormais(numero_inimigos, monstros, escalador_universal);

        boolean morte_jogador = jogador.getHp_atual() <= 0;
        // Loop do nivel
        while (monstros_nivel.size() > 0 && !morte_jogador)// Nivel acaba quando jogador derrotar todos os monstros ou morrer
        {
            System.out.println();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            NivelExibirMonstros(monstros_nivel);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            String jogada = RetornaStr("I - Inventário. | S - Status. | M - Magias. | Z - Arma | X - Armadura");
            if(jogada.equals("i") || jogada.equals("I"))
            {
                AbrirInventario(jogador);
            }
            else if(jogada.equals("s") || jogada.equals("S"))
            {
                EstadoJogador(jogador);
                Pausa();
            }
            else if(jogada.equals("m") || jogada.equals("M"))
            {
                ABRIRMAGIASMENU(jogador);
            }
            else if(jogada.equals("z") || jogada.equals("Z"))
            {
                VerArma(jogador);
            }
            else if(jogada.equals("x") || jogada.equals("X"))
            {
                VerArmadura(jogador);
            }
            else
            {
                int id_monstro = MonstroEscolhido(monstros_nivel, jogada);
                if(id_monstro < 0)
                {
                    System.out.println();
                }
                else
                {
                    BATALHA(jogador,monstros_nivel.get(id_monstro), itens, magias, escalador_universal);
                    morte_jogador = jogador.getHp_atual() <= 0;
                    if(!morte_jogador)
                    {
                        monstros_nivel.remove(id_monstro);
                    }
                }
            }
        }
    }
    // Gera o final do jogador no modo conquista
    void Final(Jogador jogador)
    {
        System.out.printf("Um F.O.D.A conhecido como %s foi na Zona dos F.O.D.A para poder buscar sua fortuna...",
                jogador.getNome());
        System.out.println();
        Pausa();
        String fim_pt_1;
        if(jogador.getFases_passada() <= 1)
        {
            fim_pt_1 = "NOME_JOGADOR decidiu amarelar logo após sua primeira aventura e foi embora para casa por " +
                    "ser covarde, NOME_JOGADOR virou piada entre os F.O.D.A e seu nome virou um apelido " +
                    "pejorativo para os F.O.D.A que são uns covardes...";
        }
        else if (jogador.getFases_passada() <= 5)
        {
            fim_pt_1 = "NOME_JOGADOR ficou um curto período na zona e mal evidenciou as atrocidades da Zona F.O.D.A, " +
                    "NOME_JOGADOR teve que enfrentar perigos, mas no fim mal cumpriu a sua fortuna...";
        }
        else if(jogador.getFases_passada() <=15)
        {
            fim_pt_1 = "NOME_JOGADOR sobreviveu diversos perigos na Zona " +
                    "F.O.D.A, muito mais que uns que querem ser um F.O.D.A " +
                    "mas não tem noção dos perigos que lhe aguardam e acabam virando uns cagões ou adubo pra " +
                    "dinossauro, entre os F.O.D.A NOME_JOGADOR não teve um renome particularmente grande, " +
                    "mas certamente chegou mais longe que muitos gostariam....";
        }
        else if(jogador.getFases_passada() <= 30)
        {
            fim_pt_1 = "NOME_JOGADOR enfrentou diversas anomalias e perigos, chegando várias vezes a temer possível " +
                    "morte, entretanto conseguiu sempre sair com a melhor, lá teve " +
                    "muitas experiencias e boa convivência com outros F.O.D.A em suas estadias no Hub, " +
                    "NOME_JOGADOR pode ter renunciado seu título de F.O.D.A mas muitos de lá " +
                    "lembram que é um F.O.D.A veterano....";
        }
        else
        {
            fim_pt_1 = "Entre a figura dos F.O.D.A a de NOME_JOGADOR " +
                    "virou lenda, sua proficiência no campo de batalha nunca " +
                    "foi vista antes, muita das anomalias causadas pelo incidente da Frank Industries " +
                    "foram exterminados por NOME_JOGADOR, " +
                    "causando um grande impacto no próprio ecossistema da Zona F.O.D.A, muitos F.O.D.A " +
                    "idolatram NOME_JOGADOR...";
        }
        fim_pt_1 = ColocaNomeJogador(jogador.getNome(),fim_pt_1);
        fim_pt_1 = printBonito(fim_pt_1);
        System.out.println(fim_pt_1);
        Pausa();
        System.out.println();
        String fim_pt_2;
        if(jogador.getMoedas() <= 0)
        {
            fim_pt_2 = "NOME_JOGADOR voltou quebrado de suas aventuras, nem um misero πCoin em sua possessão... " +
                    "seu futuro é incerto....";
        }
        else if(jogador.getMoedas() <= 300)
        {
            fim_pt_2 = "NOME_JOGADOR pegou uma mixaria de πCoins, pelo menos pode pegar um Frank " +
                    "Eats por uma semana quando voltar pra casa.";
        }
        else if(jogador.getMoedas() <= 1000)
        {
            fim_pt_2 = "NOME_JOGADOR acumulou muito pouco πCoins em função dos perigos que teve que presenciar, " +
                    "entretanto fica feliz que tem o suficiente para pedir pizzas todo dia por um ano!";
        }
        else if(jogador.getMoedas() <= 2000)
        {
            fim_pt_2 = "NOME_JOGADOR acumulou uma boa quantia de πCoins, com sua pequena fortuna NOME_JOGADOR foi " +
                    "sagaz e investiu na bolsa e consegue viver confortavelmente com uma renda passiva.";
        }
        else
        {
            fim_pt_2 = "NOME_JOGADOR conquistou uma fortuna em " +
                    "suas aventuras, os πCoins que obteve de suas expedições " +
                    "e apostas de rinha de galo serviram uma boa quantia, NOME_JOGADOR abriu uma pizzaria que teve " +
                    "muito sucesso por um tempo até um dia a " +
                    "Frank Industries ter interesse e proporcionar uma oferta que " +
                    "NOME_JOGADOR não pode recusar, NOME_JOGADOR agora vive uma vida tranquila. A Frank " +
                    "Industries mudou o nome da pizzaria para Frank Hut, e desenvolveu um protótipo para novos " +
                    "meios de logística, um ciborgue altamente capacitado para enfrentar os perigos de entregas de " +
                    "aplicativo, o primeiro modelo a funcionar, unidade 1-D codinome “Danilo”, " +
                    "será o primeiro entregador ciborgue posto em responsabilidade das entregas....";
        }
        fim_pt_2 = ColocaNomeJogador(jogador.getNome(), fim_pt_2);
        fim_pt_2 = printBonito(fim_pt_2);
        System.out.println(fim_pt_2);
        Pausa();
        System.out.println("Fim de Jogo!");
        jogador.Metricas();
        System.out.printf("PONTUAÇÃO: %s",jogador.Pontuacao());
        Pausa();
    }
    // Faz o evento
    void FazerEvento(Jogador jogador, ArrayList<Evento> eventos, Aleatorio aleatoriedade)
    {
        if(jogador.hp_atual <= 0)
        {
            System.out.println();
            System.out.printf("%s faleceu.",jogador.getNome());
        }
        else {
            int sorteio_evento = rand.nextInt(2);
            if (sorteio_evento == 0) {
                System.out.printf("Não acontece nada com %s até seu destino...",jogador.getNome());
                System.out.println();
                Pausa();
            }
            else {
                Evento evento = eventos.get(rand.nextInt(eventos.size()));
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"); // 64 char
                String texto_evento = evento.getProposta_evento();
                texto_evento = ColocaNomeJogador(jogador.getNome(), texto_evento);
                texto_evento = aleatoriedade.Aleatorizador(texto_evento);
                texto_evento = printBonito(texto_evento);
                String aceitar = ColocaNomeJogador(jogador.getNome(), evento.getAceitar());
                aceitar = aleatoriedade.Aleatorizador(aceitar);
                String recusar = ColocaNomeJogador(jogador.getNome(), evento.getRecusar());
                recusar = aleatoriedade.Aleatorizador(recusar);
                System.out.println(texto_evento);
                System.out.println();
                System.out.printf("S - %s", aceitar);
                System.out.println();
                System.out.printf("N - %s", recusar);
                System.out.println();
                boolean escolha = RetornaEscolha();
                if (!escolha) {
                    String ignorou = evento.getTexto_resultado_ignorar();
                    ignorou = ColocaNomeJogador(jogador.getNome(), ignorou);
                    ignorou = aleatoriedade.Aleatorizador(ignorou);
                    ignorou = printBonito(ignorou);
                    System.out.println(ignorou);
                    Pausa();
                }
                else {
                    int sorteio_resultado = rand.nextInt(2);
                    if (sorteio_resultado == 0) // Resultado ruim
                    {
                        // Atribui o texto a uma string para parsear por dados de narrativa
                        // ColocarNomeJogador = Troca 'NOME_JOGADO' pelo nome do jogador
                        String resultado_ruim = ColocaNomeJogador(jogador.getNome(),evento.getTexto_resultado_ruim());
                        // Aleatorizador = substituir flags por strings dinamicas
                        resultado_ruim = aleatoriedade.Aleatorizador(resultado_ruim);
                        // Aplica a quebra de linha conforme o numero de caracteres
                        resultado_ruim = printBonito(resultado_ruim);
                        System.out.println(resultado_ruim);
                        // Chama os atributos afetados pelo resultado negativo
                        evento.ResultadosNegativos(jogador.getNome());
                        jogador.setHp_atual(jogador.getHp_atual() - evento.getPerder_vitalidade());
                        PerderMana(jogador, evento.getPerder_mana());
                        EventoDiminuirAtributos(jogador,evento.getPerder_forca(), evento.getPerder_agilidade(),
                                evento.getPerder_sorte(), evento.getPerder_inteligencia(),evento.getPerder_percepcao(),
                                evento.getPerder_moedas());
                        Pausa();
                    }
                    else
                    {
                        // Resultado Bom
                        // Atribui o texte uma string para parser
                        // ColocarNomeJogador = Troca 'NOME_JOGADO' pelo nome do jogador
                        String resultado_bom = ColocaNomeJogador(jogador.getNome(), evento.getTexto_resultado_bom());
                        // Aleatorizador = substituir flags por strings dinamicas
                        resultado_bom = aleatoriedade.Aleatorizador(resultado_bom);
                        // Aplica a quebra de linha
                        resultado_bom = printBonito(resultado_bom);

                        System.out.println(resultado_bom);

                        // Mostra os atributos afetados pelo resultado positivo
                        evento.ResultadosPositivos(jogador.getNome());

                        // Aplica os efeitos
                        RestauraVidaJogador(evento.getGanhar_vitalidade(), jogador);
                        RestauraManaJogador(evento.getGanhar_mana(), jogador);
                        EventoAumentoAtributos(jogador, evento.getGanhar_forca(), evento.getGanhar_agilidade(),
                                evento.getGanhar_sorte(),evento.getGanhar_inteligencia(),evento.getGanhar_percepcao(),
                                evento.getGanhar_moedas());
                        Pausa();
                    }
                }
            }
        }
    }
    // Aumenta o level do jogador e da a opção de aumentar atributo
    void LevelUp(Jogador jogador)
    {
        jogador.setLevel(jogador.getLevel() + 1);
        int pontos_atr = 1;
        int aumento_hp_mp = 3;
        if(jogador.getLevel() <= 5)
        {
            pontos_atr = 2;
        }
        String aumento;
        boolean aum_1 = true;
        System.out.println();
        System.out.printf("%s avançou para o level %s!",jogador.getNome(),jogador.getLevel());
        System.out.println();
        while (aum_1)
        {
            System.out.printf("1. Aumentar vitalidade máxima. +%s HP.",aumento_hp_mp);
            System.out.println();
            System.out.printf("2. Aumentar mana máxima. +%s MP.",aumento_hp_mp);
            System.out.println();
            System.out.println("3. Aumentar capacidade de consumiveis.");
            aumento = RetornaStr("Selecione seu beneficio.");
            switch (aumento) {
                case "1":
                    System.out.println("HP máximo aumentado");
                    jogador.getCla_jogador().setHp_jogador(jogador.getCla_jogador().getHp_jogador() + aumento_hp_mp);
                    aum_1 = false;
                    break;
                case "2":
                    System.out.println("MP máximo aumentado!");
                    jogador.getCla_jogador().setMp_jogador(jogador.getCla_jogador().getMp_jogador() + aumento_hp_mp);
                    aum_1 = false;
                    break;
                case "3":
                    System.out.println("Slots consumiveis aumentado!");
                    jogador.getInv_jogador().setMax_consumiveis(jogador.getInv_jogador().getMax_consumiveis() + 1);
                    aum_1 = false;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
        while (pontos_atr > 0)
        {
            System.out.printf("Pontos restantes: %s",pontos_atr);
            System.out.println();
            System.out.printf("1. FOR: %s | 2. AGI: %s | 3. SOR: %s | 4. INT: %s | 5. PER: %s",
                    jogador.getCla_jogador().getForca(),jogador.getCla_jogador().getAgilidade(),
                    jogador.getCla_jogador().getSorte(),jogador.getCla_jogador().getInteligencia(),
                    jogador.getCla_jogador().getPercepcao());
            System.out.println();
            aumento = RetornaStr("Selecione um atributo para aumentar 1 ponto");
            switch (aumento)
            {
                case "1":
                    jogador.getCla_jogador().setForca(jogador.getCla_jogador().getForca() + 1);
                    pontos_atr -= 1;
                    break;
                case "2":
                    jogador.getCla_jogador().setAgilidade(jogador.getCla_jogador().getAgilidade() + 1);
                    pontos_atr -=1;
                    break;
                case "3":
                    jogador.getCla_jogador().setSorte(jogador.getCla_jogador().getSorte() + 1);
                    pontos_atr -=1;
                    break;
                case "4":
                    jogador.getCla_jogador().setInteligencia(jogador.getCla_jogador().getInteligencia() + 1);
                    pontos_atr -=1;
                    break;
                case "5":
                    jogador.getCla_jogador().setPercepcao(jogador.getCla_jogador().getPercepcao() + 1);
                    pontos_atr -=1;
                    break;
                default:
                    System.out.println("Escolha inválida.");
                    break;
            }
        }
    }
    // Hub é a comunidade q tem mercadores e lugares de aposta
    void Hub(Jogador jogador, ListaItens itens, ListaMagia magias,Aleatorio aleatorio, int escalador_universal)
    {
        String escolha_2;
        boolean escolhendo;
        boolean continuar_aventura = false;
        int aposta;
        int max_brigas_galo = 3; // maximo de brigas de galo por evento.
        ArrayList<ItemConsumivel> itens_mercado_aberto = new ArrayList<>();
        for (int x = 0; x < 5;x++)
        {
            itens_mercado_aberto.add(
                    itens.getLista_consumiveis().get(rand.nextInt(itens.getLista_consumiveis().size())));
        }
        ArrayList<ItemArma> armas_mercado_negro =  new ArrayList<>();
        for (int x = 0; x < 5;x++)
        {
            armas_mercado_negro.add(itens.getLista_armas().get(rand.nextInt(itens.getLista_armas().size())));
        }
        ArrayList<Magia> magias_cibermago = new ArrayList<>();
        for (int x = 0; x < 5;x++)
        {
            magias_cibermago.add(magias.getMagias().get(rand.nextInt(magias.getMagias().size())));
        }
        ArrayList<ItemArmadura> armaduras_bazar = new ArrayList<>();
        for (int x = 0; x < 5;x++)
        {
            armaduras_bazar.add(itens.getLista_armaduras().get(rand.nextInt(itens.getLista_armaduras().size())));
        }
        while (!continuar_aventura)
        {
            String hub = "NOME_JOGADOR chega no Hub, a comunidade local dos F.O.D.A, NOME_JOGADOR " +
                    "pode fazer compras no FrankMercado™ local, visitar o Mercado Negro para se proteger, " +
                    "armar ou aprender cibermagias, também pode tentar ganhar " +
                    "πCoins apostando em rinhas de galo ou pedra,papel e tesoura.";
            hub = ColocaNomeJogador(jogador.getNome(), hub);
            hub = printBonito(hub);
            System.out.println(hub);
            System.out.println("\n\n1. Ir ao FrankMercado™.\n2. Ir ao Mercado Negro.\n3. Ir a Casa de Apostas." +
                    "\n\nC. Continuar Aventura | V. Ver πCoins.");
            System.out.println();
            String perg_1 = ColocaNomeJogador(jogador.getNome(),"O que NOME_JOGADOR deve fazer?");
            String perg_2 = ColocaNomeJogador(jogador.getNome(),"No que NOME_JOGADOR deve participar?");
            String escolha = RetornaStr(perg_1);
            switch (escolha)
            {
                case "1": // MERCADO ABERTO = COMPRAR CONSUMIVEIS OU VENDER CONSUMIVEIS
                    System.out.println();
                    escolhendo = true;
                    while (escolhendo)
                    {
                        System.out.println("\n1. Comprar.\n2. Vender.\n\nS. Voltar | V. Ver πCoins.");
                        System.out.println();
                        escolha_2 = RetornaStr(perg_1);
                        switch (escolha_2)
                        {
                            case "1":
                                ComprarItemConsumivel(jogador,itens_mercado_aberto,escalador_universal);
                                break;
                            case "2":
                                VenderItemConsumivel(jogador);
                                break;
                            case "s":
                            case "S":
                                System.out.println();
                                escolhendo = false;
                                break;
                            case "v":
                            case "V":
                                jogador.VerDinheiro();
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }
                    break;
                case "2": // MERCADO NEGRO = APRIMORAR ARMAS & COMPRAR ARMAS
                    System.out.println("\n1. Procurar Mercador de armas.\n2. Procurar Ciber-mago." +
                            "\n3. Visitar Tecelagem.\n\nS. Voltar | V. Ver πCoins.");
                    escolhendo = true;
                    while (escolhendo)
                    {
                        escolha_2 = RetornaStr(perg_1);
                        switch (escolha_2)
                        {
                            case "1":
                                MercadorArmas(jogador,armas_mercado_negro,escalador_universal);
                                escolhendo = false;
                                break;
                            case "2":
                                CiberMago(jogador,magias_cibermago,escalador_universal);
                                escolhendo = false;
                                break;
                            case "3":
                                Tecelagem(jogador,armaduras_bazar,escalador_universal);
                                escolhendo = false;
                                break;
                            case "s":
                            case "S":
                                escolhendo = false;
                                break;
                            case "v":
                            case "V":
                                jogador.VerDinheiro();
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }
                    break;
                case "3":
                    System.out.println();
                    escolhendo = true;
                    while (escolhendo)
                    {
                        System.out.println("1. Pedra, Papel e Tesoura.\n2. Rinha de Galo." +
                                "\n\nS. Voltar. | V. Ver πCoins.");
                        System.out.println();
                        escolha_2 = RetornaStr(perg_2);
                        switch (escolha_2)
                        {
                            case "1":
                                // Pedra, Papel E Tesoura
                                if (jogador.JogadorPobre())
                                {
                                    System.out.printf("%s está sem grana...",jogador.getNome());
                                    System.out.println();
                                }
                                else
                                {
                                    aposta = Apostar(jogador, "Pedra, Papel e Tesoura (1:2)");
                                    if (aposta > 0) {
                                        PedraPapelTesoura(jogador, aleatorio, aposta);

                                    }
                                }
                                break;
                            case "2":
                                // RINHA DE GALO
                                if (jogador.JogadorPobre())
                                {
                                    System.out.printf("%s está sem grana...",jogador.getNome());
                                    System.out.println();
                                }
                                else if(max_brigas_galo <= 0)
                                {
                                    System.out.println("Acabaram as brigas de galo por hoje...");
                                    System.out.println();
                                }
                                else
                                {
                                    aposta = Apostar(jogador, "Rinha de Galo(1:4)");
                                    if(aposta > 0)
                                    {
                                        RinhaDeGalo(jogador,aleatorio,aposta);
                                        max_brigas_galo -= 1;
                                    }
                                }
                                break;
                            case "s":
                            case "S":
                                escolhendo = false;
                                break;
                            case "v":
                            case "V":
                                jogador.VerDinheiro();
                                System.out.println();
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                System.out.println();
                                break;
                        }
                    }
                    break;
                case "c":
                case "C":
                    System.out.println("Próximo nível!");
                    continuar_aventura = true;
                    break;
                case "v":
                case "V":
                    jogador.VerDinheiro();
                    System.out.println();
            }
        }
    }
    // O "Mercado Negro" onde compra armas e magias e tambem as aprimoras
    void MercadorArmas(Jogador jogador, ArrayList<ItemArma> armas_mercador, int escalador)
    {
        boolean escolher = true;
        while (escolher)
        {
            System.out.println("\n1. Comprar Armas.\n2. Aprimorar arma.\n3. Vender Arma." +
                    "\n\nS. Voltar. | V. Ver πCoins.");
            String escolha = RetornaStr("Escolha uma opção.");
            switch (escolha)
            {
                case "1":
                    ComprarItemArmaMercador(jogador, armas_mercador, escalador);
                    break;
                case "2":
                    UpgradeItemArmaMercador(jogador,escalador);
                    break;
                case "3":
                    VenderItemArmaMercador(jogador, escalador);
                    break;
                case "s":
                case "S":
                    escolher = false;
                    break;
                case "v":
                case "V":
                    jogador.VerDinheiro();
                    System.out.println();
                    break;
            }
        }
    }
    // Mercador de magias, pode aprender magias ou aprimora-las
    void CiberMago(Jogador jogador, ArrayList<Magia> magias_mercador, int escalador)
    {
        boolean escolher = true;
        while (escolher)
        {
            System.out.println("\n1. Aprender Magias.\n2. Aprimorar magia.\n\nS. Voltar. | V. Ver πCoins.");
            String escolha = RetornaStr("Escolha uma opção.");
            switch (escolha)
            {
                case "1":
                    ComprarMagiaMercador(jogador,magias_mercador,escalador);
                    break;
                case "2":
                    AprimorarMagiaMercador(jogador, escalador);
                    break;
                case "s":
                case "S":
                    escolher = false;
                    break;
                case "v":
                case "V":
                    jogador.VerDinheiro();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
    void Tecelagem(Jogador jogador, ArrayList<ItemArmadura> armaduras_mercador, int escalador)
    {
        boolean escolher = true;
        while (escolher)
        {
            System.out.println("\n1. Comprar Armaduras.\n2. Aprimorar armadura.\n3. Vender Armadura" +
                    "\n\nS. Voltar. | V. Ver πCoins.");
            String escolha = RetornaStr("Escolha uma opção.");
            switch (escolha)
            {
                case "1":
                    ComprarArmaduraMercador(jogador, armaduras_mercador, escalador);
                    break;
                case "2":
                    UpgradeArmaduraMercador(jogador,escalador);
                    break;
                case "3":
                    VenderArmaduraMercador(jogador, escalador);
                    break;
                case "s":
                case "S":
                    escolher = false;
                    break;
                case "v":
                case "V":
                    jogador.VerDinheiro();
                    System.out.println();
                    break;
            }
        }
    }
    void ComprarArmaduraMercador(Jogador jogador, ArrayList<ItemArmadura> armaduras_mercador, int escalador)
    {
        boolean comprar = true;
        while (comprar){
            if(jogador.getInv_jogador().getSlot_armadura().size() == 1)
            {
                System.out.printf("%s já tem uma armadura...",jogador.getNome());
                System.out.println();
                comprar = false;
            }
            else if(armaduras_mercador.size() <= 0)
            {
                System.out.println("Acabaram as ofertas...");
                comprar = false;
            }
            else
            {
                if (escalador <= 0)
                {
                    escalador += 1;
                }
                int cont = 0;
                System.out.println("##########################################################################");
                for (ItemArmadura x : armaduras_mercador)
                {
                    System.out.printf("%s - %s ",cont,x.getNome());
                    x.DescreverAtributosModificador();
                    System.out.println();
                    cont +=1;
                }
                System.out.println("##########################################################################");
                String id_consumivel = RetornaStr("Escolha uma armadura para comprar ou S para voltar.");
                if (id_consumivel.equals("s") || id_consumivel.equals("S"))
                {
                    comprar = false;
                }
                int id_item_int = ArmaduraEscolhido(armaduras_mercador, id_consumivel);
                if (id_item_int < 0)
                {
                    System.out.println("Seleção inválida");
                }
                else
                {
                    int preco_bruto = armaduras_mercador.get(id_item_int).getValor();
                    int preco_total = preco_bruto * escalador;
                    boolean confirmar_compra = true;
                    while (confirmar_compra)
                    {

                        System.out.println();
                        System.out.printf("Comprar %s por %s πCoin(s)?",armaduras_mercador.get(id_item_int).getNome(),
                                preco_total);
                        System.out.println();
                        boolean escolha = RetornaEscolha();
                        if (!escolha)
                        {
                            confirmar_compra = false;
                        }
                        else
                        {
                            if((jogador.getMoedas() - preco_total) < 0)
                            {
                                System.out.printf("%s não tem %s πCoin(s)...",jogador.getNome(), preco_total);
                                System.out.println();
                                confirmar_compra = false;
                            }
                            else
                            {
                                System.out.printf("%s comprou %s!",jogador.getNome(),
                                        armaduras_mercador.get(id_item_int).getNome());
                                System.out.println();
                                jogador.getInv_jogador().getSlot_armadura().add(armaduras_mercador.get(id_item_int));
                                armaduras_mercador.remove(id_item_int);
                                jogador.setMoedas(jogador.getMoedas() - preco_total);
                                confirmar_compra = false;
                                comprar = false;
                            }
                        }
                    }
                }
            }
        }
    }
    void UpgradeArmaduraMercador(Jogador jogador, int escalador)
    {
        if (escalador <= 0)
        {
            escalador = 1;
        }
        boolean mod_armadura = true;
        boolean confirmar;
        while (mod_armadura)
        {
            if(jogador.getInv_jogador().getSlot_armadura().size() == 0)
            {
                System.out.printf("%s não tem uma arma...",jogador.getNome());
                mod_armadura = false;
            }
            else
            {
                System.out.println("\n1. Incrementar defesa (+2 DEF)\n2. Encantar armaduras. (+1 ATR)" +
                        "\n\nS. Voltar | V. Ver πCoins.");
                String escolha = RetornaStr("Selecione uma opção.");
                switch (escolha)
                {
                    case "1":
                        int preco_def = 200 * escalador;
                        System.out.printf("Aprimorar defesa de %s por %s πCoins.?",
                                jogador.getInv_jogador().getSlot_armadura().get(0).getNome(),preco_def);
                        confirmar = RetornaEscolha();
                        if(confirmar)
                        {
                            if(jogador.getMoedas() < preco_def)
                            {
                                System.out.printf("%s não tem dinheiro para o aprimoramento.",jogador.getNome());
                                System.out.println();
                            }
                            else{
                                int def_armadura = jogador.getInv_jogador().getSlot_armadura().get(0).getDefesa();
                                jogador.getInv_jogador().getSlot_armadura().get(0).setDefesa(def_armadura+2);
                                def_armadura = jogador.getInv_jogador().getSlot_armadura().get(0).getDefesa();
                                System.out.println();
                                System.out.printf("%s agora tem %s de defesa.",
                                        jogador.getInv_jogador().getSlot_armadura().get(0).getNome(),def_armadura);
                                System.out.println();
                            }
                        }
                        break;
                    case "2":
                        int preco_upgrade = 500 * escalador;
                        System.out.printf("Atributos de %s",
                                jogador.getInv_jogador().getSlot_armadura().get(0).getNome());
                        System.out.println();
                        jogador.getInv_jogador().getSlot_armadura().get(0).DescreverAtributosModificador();
                        System.out.println();
                        System.out.printf("Aumentar um atributo de %s por %s πCoins?",
                                jogador.getInv_jogador().getSlot_armadura().get(0).getNome(),preco_upgrade);
                        confirmar = RetornaEscolha();
                        if(confirmar)
                        {
                            if(jogador.getMoedas() < preco_upgrade)
                            {
                                System.out.printf("%s não tem dinheiro para o encantamento.",jogador.getNome());
                                System.out.println();
                            }
                            else
                            {
                                jogador.setMoedas(jogador.getMoedas() - preco_upgrade);
                                boolean upgrade = true;
                                while (upgrade) {
                                    int atr;
                                    System.out.println("1. FOR | 2. AGI | 3. SOR | 4. INT | 5. PER");
                                    String aumento = RetornaStr("Selecione um atributo");
                                    switch (aumento)
                                    {
                                        case "1":
                                            atr = jogador.getInv_jogador().getSlot_armadura().get(0).getForca();
                                            jogador.getInv_jogador().getSlot_armadura().get(0).setForca(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "2":
                                            atr = jogador.getInv_jogador().getSlot_armadura().get(0).getAgilidade();
                                            jogador.getInv_jogador().getSlot_armadura().get(0).setAgilidade(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "3":
                                            atr = jogador.getInv_jogador().getSlot_armadura().get(0).getSorte();
                                            jogador.getInv_jogador().getSlot_armadura().get(0).setSorte(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "4":
                                            atr = jogador.getInv_jogador().getSlot_armadura().get(0).getInteligencia();
                                            jogador.getInv_jogador().getSlot_armadura().get(0).setInteligencia(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "5":
                                            atr = jogador.getInv_jogador().getSlot_armadura().get(0).getPercepcao();
                                            jogador.getInv_jogador().getSlot_armadura().get(0).setPercepcao(atr + 1);
                                            upgrade = false;
                                            break;
                                        default:
                                            System.out.println("Escolha inválida.");
                                            break;
                                    }

                                }
                            }
                        }
                        break;
                    case "s":
                    case "S":
                        mod_armadura = false;
                        break;
                    case "v":
                    case "V":
                        jogador.VerDinheiro();
                        break;
                }
            }
        }
    }
    void VenderArmaduraMercador(Jogador jogador, int escalador)
    {

        if (jogador.getInv_jogador().getSlot_armadura().size() <= 0)
        {
            System.out.printf("%s não tem armadura para vender.",jogador.getNome());
            System.out.println();
        }
        else
        {
            if(escalador == 0)
            {
                escalador =1;
            }
            int preco_base = jogador.getInv_jogador().getSlot_armadura().get(0).getValor();
            int sorte_jogador = jogador.getCla_jogador().getSorte();
            int preco_total = (preco_base/2 * sorte_jogador)*escalador;
            System.out.printf("Vender %s por %s πCoins?",jogador.getInv_jogador().getSlot_armadura().get(0).getNome(),
                    preco_total);
            System.out.println();
            boolean vender = RetornaEscolha();
            if(vender)
            {

                jogador.getInv_jogador().getSlot_armadura().remove(0);
                jogador.setMoedas(jogador.getMoedas() + preco_total);
                System.out.printf("%s ganhou %s πCoins.",jogador.getNome(),preco_total);
                System.out.println();
                jogador.VerDinheiro();
            }
        }
    }
    // Recebe as magias geradas na visita no Hub e as coloca pra compra
    void ComprarMagiaMercador(Jogador jogador, ArrayList<Magia> magias_mercador, int escalador)
    {
        boolean comprando = true;
        while (comprando) {
            int cont = 0;
            System.out.println("#####################################################################");
            for (Magia x : magias_mercador) {
                System.out.printf("%s - %s ", cont, x.getNome());
                x.DescreverAtributosMagia();
                System.out.println();
                cont += 1;
            }
            System.out.println("#####################################################################");
            String id_magia = RetornaStr("Escolha uma magia ou Tecle S para Voltar.");
            if (id_magia.equals("s") || id_magia.equals("S")) {
                comprando = false;
            }
            else
            {
                int id_magia_int = MagiaEscolhida(magias_mercador,id_magia);
                if(id_magia_int < 0)
                {
                    System.out.println("Seleção inválida.");
                }
                else {
                    boolean possui_magia = false;
                    for (Magia x : jogador.getMagias_aprendidas()) {
                        if (x.getNome().equals(magias_mercador.get(id_magia_int).getNome())) {
                            possui_magia = true;
                            break;
                        }
                    }
                    if (possui_magia) {
                        System.out.printf("%s já sabe %s.", jogador.getNome(),
                                magias_mercador.get(id_magia_int).getNome());
                        System.out.println();
                    }
                    else {
                        if (escalador == 0) {
                            escalador = 1;
                        }
                        int preco_bruto = magias_mercador.get(id_magia_int).getValor();
                        int preco_total = preco_bruto * escalador;
                        System.out.printf("Aprender %s por %s πCoin(s)?", magias_mercador.get(id_magia_int).getNome(),
                                preco_total);
                        System.out.println();
                        boolean confirmar = RetornaEscolha();
                        if (confirmar) {


                            if (jogador.getMoedas() < preco_total) {
                                System.out.printf("%s não tem %s πCoin(s)...", jogador.getNome(), preco_total);
                                System.out.println();
                            } else {
                                jogador.setMoedas(jogador.getMoedas() - preco_total);
                                jogador.VerDinheiro();
                                jogador.getMagias_aprendidas().add(magias_mercador.get(id_magia_int));
                                System.out.printf("%s aprendeu %s!", jogador.getNome(),
                                        magias_mercador.get(id_magia_int).getNome());
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }
    // O jogador escolhe uma magia pra aprimorar e pega
    void AprimorarMagiaMercador(Jogador jogador, int escalador)
    {
        int atk;
        int cura;
        int preco;
        if(jogador.getMagias_aprendidas().size() <= 0)
        {
            System.out.printf("%s não conhece nenhuma magia...",jogador.getNome());
        }
        else {
            boolean confirma;
            boolean comprando = true;
            while (comprando) {
                int cont = 0;
                System.out.println("#####################################################################");
                for (Magia x : jogador.getMagias_aprendidas()) {
                    System.out.printf("%s - %s", cont, x.getNome());
                    x.DescreverAtributosMagia();
                    System.out.println();
                    cont += 1;
                }
                System.out.println("#######################################################################");
                String id_magia_str = RetornaStr("Escolha uma magia para aprimorar ou Tecle S para voltar");
                if (id_magia_str.equals("s") || id_magia_str.equals("S")) {
                    comprando = false;
                }
                else
                {
                    if(escalador == 0)
                    {
                        escalador = 1;
                    }
                    int id_magia = MagiaEscolhida(jogador.getMagias_aprendidas(), id_magia_str);
                    if(id_magia < 0)
                    {
                        System.out.println("Seleção inválida.");
                    }
                    else
                    {
                        if (jogador.getMagias_aprendidas().get(id_magia).getAtaque() > 0 &&
                                jogador.getMagias_aprendidas().get(id_magia).getVitalidade() > 0) {
                            preco = 500 * escalador;
                            System.out.printf("Aprimorar cura e ataque de %s por %sπCoin(s)? (+2 ATK) (+2 HP)",
                                    jogador.getMagias_aprendidas().get(id_magia).getNome(), preco);
                            System.out.println();
                            confirma = RetornaEscolha();
                            if (confirma) {
                                if (jogador.getMoedas() < preco) {
                                    System.out.printf("%s não tem %s πCoin(s)...", jogador.getNome(), preco);
                                    System.out.println();
                                } else {
                                    jogador.setMoedas(jogador.getMoedas() - preco);
                                    System.out.printf("%s aprimorou %s", jogador.getNome(),
                                            jogador.getMagias_aprendidas().get(id_magia).getNome());
                                    System.out.println();
                                    atk = jogador.getMagias_aprendidas().get(id_magia).getAtaque();
                                    cura = jogador.getMagias_aprendidas().get(id_magia).getVitalidade();
                                    jogador.getMagias_aprendidas().get(id_magia).setAtaque(atk + 2);
                                    jogador.getMagias_aprendidas().get(id_magia).setVitalidade(cura + 2);
                                }
                            }
                        } else {
                            preco = 250 * escalador;
                            if (jogador.getMagias_aprendidas().get(id_magia).getAtaque() > 0) {
                                System.out.printf("Aprimorar ataque de %s por %sπCoin(s)? (+2 ATK)",
                                        jogador.getMagias_aprendidas().get(id_magia).getNome(), preco);
                                System.out.println();
                            }
                            else {
                                System.out.printf("Aprimorar cura de %s por %sπCoin(s)? (+2 HP)",
                                        jogador.getMagias_aprendidas().get(id_magia).getNome(), preco);
                                System.out.println();

                            }
                            confirma = RetornaEscolha();
                            if (confirma) {
                                if (jogador.getMoedas() < preco) {
                                    System.out.printf("%s não tem %s πCoin(s)...", jogador.getNome(), preco);
                                    System.out.println();
                                } else {
                                    preco = 300 * escalador;
                                    jogador.setMoedas(jogador.getMoedas() - preco);
                                    System.out.printf("%s aprimorou %s!", jogador.getNome(),
                                            jogador.getMagias_aprendidas().get(id_magia).getNome());
                                    System.out.println();
                                    atk = jogador.getMagias_aprendidas().get(id_magia).getAtaque();
                                    cura = jogador.getMagias_aprendidas().get(id_magia).getVitalidade();
                                    if (jogador.getMagias_aprendidas().get(id_magia).getAtaque() > 0) {
                                        jogador.getMagias_aprendidas().get(id_magia).setAtaque(atk + 2);
                                    } else {
                                        jogador.getMagias_aprendidas().get(id_magia).setVitalidade(cura + 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    // Retorna a aposta do jogador
    int Apostar(Jogador jogador, String prompt_aposta)
    {
        // Retorna um valor que serve como aposta
        String aposta;
        int aposta_inteiro;
        System.out.println(prompt_aposta);
        while (true) {
            System.out.print("Coloque sua aposta ou S para voltar\n>> ");
            aposta = entrada.nextLine();
            if(aposta.equals("s") || aposta.equals("S"))
            {
                return 0; // retorna aposta nula
            }
            try {
                aposta_inteiro = Integer.parseInt(aposta);
                if(aposta_inteiro <= 0) { // n pode apostar nada ou negativo
                    System.out.println("Seleção inválida.");
                }
                else {
                    if(aposta_inteiro > jogador.getMoedas())
                    { // Verifica se a quantia apostada n é maior do q tem
                        System.out.printf("%s não pode apostar o que ele não tem...",jogador.getNome());
                        System.out.println();
                    }
                    else {
                        System.out.printf("Confirmar a aposta de %s de seu %s πCoin(s)?",
                                aposta_inteiro, jogador.getMoedas());
                        boolean confirma = RetornaEscolha();
                        if (!confirma)
                        {
                            return 0;
                        }
                        else {
                        return aposta_inteiro;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Seleção invalida.");
            }
        }
    }
    // Mini game de pedra papel e tesoura
    void PedraPapelTesoura(Jogador jogador, Aleatorio aleatorio, int aposta)
    {
        // Tem q tirar o melhor de 3
        int rodadas = 3;
        String nome_jogador = jogador.getNome();
        String nome_npc = aleatorio.getNome_pessoas().get(rand.nextInt(aleatorio.getNome_pessoas().size()));
        int vitorias_jogador = 0;
        int vitorias_npc = 0;
        while (rodadas > 0 && vitorias_jogador < 2 && vitorias_npc < 2)
        {
            System.out.println("################################");
            System.out.println();
            System.out.printf("%s:%s    -   %s:%s",nome_jogador.toUpperCase(),vitorias_jogador,
                    nome_npc.toUpperCase(),vitorias_npc);
            System.out.println();
            System.out.println("################################");
            System.out.println("1. Pedra - 2. Papel - 3. Tesoura");
            String jogada_jogador = "Nada";
            String jogada_npc;
            boolean jogar = true;
            while (jogar)
            {
                // Jogada inicial
                String escolha = RetornaStr("O que você quer jogar?");
                switch (escolha) {
                    case "1":
                        jogada_jogador = "Pedra";
                        break;
                    case "2":
                        jogada_jogador = "Papel";
                        break;
                    case "3":
                        jogada_jogador = "Tesoura";
                        break;
                    default:
                        break;
                }
                if(jogada_jogador.equals("Nada"))
                {
                    System.out.println("Seleção inválida...");
                }
                else
                {
                    jogar = false;
                }
            }
            System.out.printf("%s jogou %s.",nome_jogador, jogada_jogador);
            System.out.println();
            int roll = rand.nextInt(3);
            if(roll == 0)
            {
                jogada_npc = "Pedra";
            }
            else if(roll == 1)
            {
                jogada_npc = "Papel";
            }
            else
            {
                jogada_npc = "Tesoura";
            }
            switch (jogada_jogador)
            {
                case "Pedra":
                    switch (jogada_npc)
                    {
                        case "Pedra":
                            System.out.println("Ambos empataram...");
                            break;
                        case "Papel":
                            System.out.printf("%s jogou %s e ganhou...", nome_npc, jogada_npc);
                            System.out.println();
                            vitorias_npc +=1;
                            rodadas -=1;
                            break;
                        case "Tesoura":
                            System.out.printf("%s jogou %s e ganhou!", nome_jogador,jogada_jogador);
                            System.out.println();
                            vitorias_jogador += 1;
                            rodadas -= 1;
                            break;
                    }
                    break;
                case "Papel":
                    switch (jogada_npc)
                    {
                        case "Pedra":
                            System.out.printf("%s jogou %s e ganhou!", nome_jogador,jogada_jogador);
                            System.out.println();
                            vitorias_jogador += 1;
                            rodadas -= 1;
                            break;
                        case "Papel":
                            System.out.println("Ambos empataram...");
                            break;
                        case "Tesoura":
                            System.out.printf("%s jogou %s e ganhou...", nome_npc,jogada_npc);
                            System.out.println();
                            vitorias_npc +=1;
                            rodadas -=1;
                            break;
                    }
                    break;
                case "Tesoura":
                    switch (jogada_npc)
                    {
                        case "Pedra":
                            System.out.printf("%s jogou %s e ganhou...", nome_npc,jogada_npc);
                            System.out.println();
                            vitorias_npc +=1;
                            rodadas -=1;
                            break;
                        case "Papel":
                            System.out.printf("%s jogou %s e ganhou!", nome_jogador,jogada_jogador);
                            System.out.println();
                            vitorias_jogador += 1;
                            rodadas -= 1;
                            break;
                        case "Tesoura":
                            System.out.println("Ambos empataram...");
                            break;
                    }
                    break;
            }
        }
        if (vitorias_jogador < vitorias_npc)
        {
            System.out.printf("%s perdeu o jogo...",nome_jogador);
            System.out.println();
            System.out.printf("%s perdeu %s πCoin(s) da aposta...",nome_jogador, aposta);
            System.out.println();
            jogador.setMoedas(jogador.getMoedas()-aposta);
            jogador.VerDinheiro();
            Pausa();
        }
        else
        {
            System.out.printf("%s venceu!", nome_jogador);
            System.out.println();
            System.out.printf("%s ganhou %s πCoin(s) da aposta!",nome_jogador, aposta);
            System.out.println();
            jogador.setMoedas(jogador.getMoedas()+(aposta*2));
            jogador.VerDinheiro();
            Pausa();
        }
    }
    // Mini game de rinha de galo
    void RinhaDeGalo(Jogador jogador, Aleatorio aleatorio, int aposta)
    {
        String galo_1_nome = aleatorio.GaloAleatorio();
        int galo_1_hp = 5;
        String galo_2_nome = aleatorio.GaloAleatorio();
        int galo_2_hp = 5;
        int galo_apostado = 0;
        int galo_vencedor;
        System.out.println();
        System.out.printf("%s vs %s",galo_1_nome,galo_2_nome);
        System.out.println();
        boolean escolha = true;
        while (escolha)
        {
            String prompt = "Qual galo deve NOME_JOGADOR escolher?";
            prompt = ColocaNomeJogador(jogador.getNome(), prompt);
            System.out.println("1. "+galo_1_nome);
            System.out.println("2. "+galo_2_nome);
            String escolha_galo = RetornaStr(prompt);
            switch (escolha_galo)
            {
                case "1":
                    galo_apostado = 1; // galo 1
                    escolha = false;
                    break;
                case "2":
                    galo_apostado = 2; // galo 2
                    escolha = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        System.out.println();
        if(galo_apostado == 1)
        {
            System.out.printf("%s apostou em %s!", jogador.getNome(), galo_1_nome);
            System.out.println();
        }
        else{
            System.out.printf("%s apostou em %s!", jogador.getNome(), galo_2_nome);
            System.out.println();
        }
        Pausa();
        while (galo_1_hp > 0 && galo_2_hp > 0)
        {
            int roll = rand.nextInt(2);
            switch (roll)
            {
                case 0:
                    System.out.printf("%s acerta %s!!",galo_1_nome,galo_2_nome);
                    System.out.println();
                    galo_2_hp -= 1;
                    Pausa();
                    break;
                case 1:
                    System.out.printf("%s acerta %s!!",galo_2_nome,galo_1_nome);
                    System.out.println();
                    galo_1_hp -= 1;
                    Pausa();
                    break;
            }
        }
        if(galo_1_hp <= 0)
        {
            System.out.printf("%s morreu violentamente.",galo_1_nome);
            System.out.println();
            System.out.printf("%s venceu!",galo_2_nome);
            Pausa();
            galo_vencedor = 2;
        }
        else
        {
            System.out.printf("%s morreu violentamente.",galo_2_nome);
            System.out.println();
            System.out.printf("%s venceu!",galo_1_nome);
            System.out.println();
            Pausa();
            galo_vencedor = 1;
        }
        if(galo_vencedor != galo_apostado)
        {
            System.out.printf("%s perdeu a aposta",jogador.getNome());
            System.out.println();
            jogador.setMoedas(jogador.getMoedas() - aposta);
            jogador.VerDinheiro();
            Pausa();
        }
        else
        {
            System.out.printf("%s ganhou a aposta!",jogador.getNome());
            System.out.println();
            jogador.setMoedas(jogador.getMoedas() + (aposta*4));
            jogador.VerDinheiro();
            Pausa();
        }
    }
    // Aposentar pra gerar o final do modo conquista
    boolean Aposentar(Jogador jogador)
    {
        jogador.Metricas();
        System.out.println("Deseja encerra o jogo e gerar um final?");
        return RetornaEscolha();
    }
    // Recebe as armas geradas na visita ao hub e as coloca pra o jogador comprar, quando compra ela sai do menu
    void ComprarItemArmaMercador(Jogador jogador, ArrayList<ItemArma> armas_mercador, int escalador)
    {
        boolean comprar = true;
        while (comprar){
            if(jogador.getInv_jogador().getSlot_arma().size() == 1)
            {
                System.out.printf("%s já tem uma arma...",jogador.getNome());
                System.out.println();
                comprar = false;
            }
            else if(armas_mercador.size() <= 0)
            {
                System.out.println("Acabaram as ofertas...");
                comprar = false;
            }
            else
            {
                if (escalador <= 0)
                {
                    escalador += 1;
                }
                int cont = 0;
                System.out.println("#####################################################################");
                for (ItemArma x : armas_mercador)
                {
                    System.out.printf("%s - %s ",cont,x.getNome());
                    x.DescreverAtributosModificador();
                    System.out.println();
                    cont +=1;
                }
                System.out.println("######################################################################");
                String id_consumivel = RetornaStr("Escolha uma arma para comprar ou S para voltar.");
                if (id_consumivel.equals("s") || id_consumivel.equals("S"))
                {
                    comprar = false;
                }
                int id_item_int = ArmaEscolhido(armas_mercador, id_consumivel);
                if (id_item_int < 0)
                {
                    System.out.println("Seleção inválida");
                }
                else
                {
                    int preco_bruto = armas_mercador.get(id_item_int).getValor();
                    int preco_total = preco_bruto * escalador;
                    boolean confirmar_compra = true;
                    while (confirmar_compra)
                    {

                        System.out.println();
                        System.out.printf("Comprar %s por %s πCoin(s)?",armas_mercador.get(id_item_int).getNome(),
                                preco_total);
                        System.out.println();
                        boolean escolha = RetornaEscolha();
                        if (!escolha)
                        {
                            confirmar_compra = false;
                        }
                        else
                        {
                            if((jogador.getMoedas() - preco_total) < 0)
                            {
                                System.out.printf("%s não tem %s πCoin(s)...",jogador.getNome(), preco_total);
                                System.out.println();
                                confirmar_compra = false;
                            }
                            else
                            {
                                System.out.printf("%s comprou %s!",jogador.getNome(),
                                        armas_mercador.get(id_item_int).getNome());
                                System.out.println();
                                jogador.getInv_jogador().getSlot_arma().add(armas_mercador.get(id_item_int));
                                armas_mercador.remove(id_item_int);
                                jogador.setMoedas(jogador.getMoedas() - preco_total);
                                confirmar_compra = false;
                                comprar = false;
                            }
                        }
                    }
                }
            }
        }
    }
    // Fazer encantamento de arma ou do ataque
    void UpgradeItemArmaMercador(Jogador jogador, int escalador)
    {
        if (escalador <= 0)
        {
            escalador = 1;
        }
        boolean mod_arma = true;
        boolean confirmar;
        while (mod_arma)
        {
            if(jogador.getInv_jogador().getSlot_arma().size() == 0)
            {
                System.out.printf("%s não tem uma arma...",jogador.getNome());
                Pausa();
                mod_arma = false;
            }
            else
            {
                System.out.println("\n1. Incrementar ataque (+2 ATK)\n2. Encantar arma. (+1 ATR)" +
                        "\n\nS. Voltar | V. Ver πCoins.");
                String escolha = RetornaStr("Selecione uma opção.");
                switch (escolha)
                {
                    case "1":
                        int preco_dano = 200 * escalador;
                        System.out.printf("Aprimorar ataque de %s por %s πCoins.?",
                                jogador.getInv_jogador().getSlot_arma().get(0).getNome(),preco_dano);
                        confirmar = RetornaEscolha();
                        if(confirmar)
                        {
                            if(jogador.getMoedas() < preco_dano)
                            {
                                System.out.printf("%s não tem dinheiro para o aprimoramento.",jogador.getNome());
                                System.out.println();
                                Pausa();
                            }
                            else{
                            int atk_arma = jogador.getInv_jogador().getSlot_arma().get(0).getAtaque();
                            jogador.getInv_jogador().getSlot_arma().get(0).setAtaque(atk_arma+2);
                            atk_arma = jogador.getInv_jogador().getSlot_arma().get(0).getAtaque();
                            System.out.println();
                            System.out.printf("%s agora causa %s de dano.",
                                    jogador.getInv_jogador().getSlot_arma().get(0).getNome(),atk_arma);
                            System.out.println();
                            Pausa();
                            }
                        }
                        break;
                    case "2":
                        int preco_upgrade = 500 * escalador;
                        System.out.printf("Atributos de %s",jogador.getInv_jogador().getSlot_arma().get(0).getNome());
                        System.out.println();
                        jogador.getInv_jogador().getSlot_arma().get(0).DescreverAtributosModificador();
                        System.out.println();
                        System.out.printf("Aumentar um atributo de %s por %s πCoins?",
                                jogador.getInv_jogador().getSlot_arma().get(0).getNome(),preco_upgrade);
                        confirmar = RetornaEscolha();
                        if(confirmar)
                        {
                            if(jogador.getMoedas() < preco_upgrade)
                            {
                                System.out.printf("%s não tem dinheiro para o encantamento.",jogador.getNome());
                                System.out.println();
                                Pausa();
                            }
                            else
                            {
                                jogador.setMoedas(jogador.getMoedas() - preco_upgrade);
                                boolean upgrade = true;
                                while (upgrade) {
                                    int atr;
                                    System.out.println("1. FOR | 2. AGI | 3. SOR | 4. INT | 5. PER");
                                    String aumento = RetornaStr("Selecione um atributo");
                                    switch (aumento)
                                    {
                                        case "1":
                                            atr = jogador.getInv_jogador().getSlot_arma().get(0).getForca();
                                            jogador.getInv_jogador().getSlot_arma().get(0).setForca(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "2":
                                            atr = jogador.getInv_jogador().getSlot_arma().get(0).getAgilidade();
                                            jogador.getInv_jogador().getSlot_arma().get(0).setAgilidade(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "3":
                                            atr = jogador.getInv_jogador().getSlot_arma().get(0).getSorte();
                                            jogador.getInv_jogador().getSlot_arma().get(0).setSorte(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "4":
                                            atr = jogador.getInv_jogador().getSlot_arma().get(0).getInteligencia();
                                            jogador.getInv_jogador().getSlot_arma().get(0).setInteligencia(atr + 1);
                                            upgrade = false;
                                            break;
                                        case "5":
                                            atr = jogador.getInv_jogador().getSlot_arma().get(0).getPercepcao();
                                            jogador.getInv_jogador().getSlot_arma().get(0).setPercepcao(atr + 1);
                                            upgrade = false;
                                            break;
                                        default:
                                            System.out.println("Escolha inválida.");
                                            break;
                                    }

                                }
                            }
                        }
                        break;
                    case "s":
                    case "S":
                        mod_arma = false;
                        break;
                    case "v":
                    case "V":
                        jogador.VerDinheiro();
                        break;
                }
            }
        }
    }
    // Vender arma para mercador
    void VenderItemArmaMercador(Jogador jogador, int escalador)
    {

        if (jogador.getInv_jogador().getSlot_arma().size() <= 0)
        {
            System.out.printf("%s não tem arma para vender.",jogador.getNome());
            System.out.println();
            Pausa();
        }
        else
        {
            if(escalador == 0)
            {
                escalador =1;
            }
            int preco_base = jogador.getInv_jogador().getSlot_arma().get(0).getValor();
            int sorte_jogador = jogador.getCla_jogador().getSorte();
            int preco_total = (preco_base/2 * sorte_jogador)*escalador;
            System.out.printf("Vender %s por %s πCoins?",jogador.getInv_jogador().getSlot_arma().get(0).getNome(),
                    preco_total);
            System.out.println();
            boolean vender = RetornaEscolha();
            if(vender)
            {

                jogador.getInv_jogador().getSlot_arma().remove(0);
                jogador.setMoedas(jogador.getMoedas() + preco_total);
                System.out.printf("%s ganhou %s πCoins.",jogador.getNome(),preco_total);
                System.out.println();
                jogador.VerDinheiro();
                Pausa();
            }
        }
    }
    // #######################################################
    // NIVEL FORA DO COMBATE
    // Inventário
    // ######################
    // Abir inventario de consumiveis
    void AbrirInventario(Jogador jogador)
    {
        boolean morte_jogador = jogador.getHp_atual() <= 0;
        if(jogador.getInv_jogador().getConsumivels().size() <= 0)
        {
            System.out.println("Não há items no seu inventário.");
            Pausa();
        }
        else {
            boolean escolha_item = true;
            while (escolha_item && !morte_jogador)
            {
                if(jogador.getInv_jogador().getConsumivels().size()  <= 0)
                {
                    escolha_item = false;
                }
                else {
                    int cont = 0;
                    System.out.println("################################################################");
                    for(ItemConsumivel x: jogador.getInv_jogador().getConsumivels())
                    {
                        System.out.printf("%s - %s ",cont,x.getNome());
                        x.DescreverAtributosItem();
                        System.out.println();
                        cont +=1;
                    }
                    System.out.println("################################################################");
                    String id_consumivel = RetornaStr("Escolha um item ou tecle S para voltar.");
                    if (id_consumivel.equals("s") || id_consumivel.equals("S")) {
                        escolha_item = false;
                    }
                    else {
                        int id_item_int = ConsumivelEscolhido(jogador.getInv_jogador().getConsumivels(), id_consumivel);
                        if (id_item_int < 0) {
                            System.out.println("Seleção inválida");
                        }
                        else {
                            boolean confirmar = true;
                            while (confirmar)
                            {
                                System.out.println();
                                System.out.printf("%s:",
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                System.out.println();
                                String descricao = printBonito(
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getDescricao());
                                System.out.println(descricao);
                                System.out.println();
                                System.out.printf("Consumir %s?",
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                boolean escolha = RetornaEscolha();
                                if (!escolha)
                                {
                                    confirmar = false;
                                }
                                else {
                                System.out.println();
                                System.out.printf("%s usou %s!", jogador.getNome(),
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                System.out.println();
                                Pausa();
                                // Usa o item e aplica os efeitos no jogador
                                UsarItem(jogador.getInv_jogador().getConsumivels().get(id_item_int), jogador);
                                // Depois deleta o consumivel do inventario
                                jogador.getInv_jogador().getConsumivels().remove(id_item_int);
                                morte_jogador = jogador.getHp_atual() <= 0;
                                confirmar = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    void ABRIRMAGIASMENU(Jogador jogador)
    {
        if (jogador.getMagias_aprendidas().size() <= 0)
        {
            System.out.println();
            System.out.printf("%s não conhece nenhuma magia.", jogador.getNome());
            System.out.println();
            Pausa();
        }
        else
        {
            boolean escolha_magia = true;
            while (escolha_magia)
            {
                int cont = 0;
                System.out.println("################################################################");
                for (Magia x : jogador.getMagias_aprendidas())
                {
                    System.out.printf("%s - %s ", cont, x.getNome());
                    x.DescreverAtributosMagia();
                    System.out.println();
                    cont += 1;
                }
                System.out.println("################################################################");
                String id_magia = RetornaStr("Escolha uma magia ou tecle S para voltar.");
                if(id_magia.equals("s") || id_magia.equals("S"))
                {
                    escolha_magia = false;
                }
                else
                {
                    int id_magia_int = MagiaEscolhida(jogador.getMagias_aprendidas(), id_magia);
                    if(id_magia_int < 0)
                    {
                        System.out.println("Opção inválida");
                    }
                    else {
                        if (jogador.getMagias_aprendidas().get(id_magia_int).getVitalidade() <= 0)
                        {
                            System.out.println();
                            System.out.printf("A magia %s não é uma magia de cura...",
                                    jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                            System.out.println();
                            Pausa();
                        }
                        else {
                            if (jogador.getMp_atual() <
                                    jogador.getMagias_aprendidas().get(id_magia_int).getCusto_mana())
                            {
                                System.out.println();
                                System.out.printf("%s não possui MP para usar %s...", jogador.getNome(),
                                        jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                                System.out.println();
                                Pausa();
                            }
                            else {
                                System.out.printf("%s",jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                                System.out.println();
                                System.out.println("Descrição:");
                                String descricao = printBonito(
                                        jogador.getMagias_aprendidas().get(id_magia_int).getDescricao());
                                System.out.println(descricao);
                                System.out.println();
                                jogador.getMagias_aprendidas().get(id_magia_int).DescreverAtributosMagia();
                                System.out.println();
                                System.out.printf("Deseja usar %s?",
                                        jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                                System.out.println();
                                boolean usar_magia = RetornaEscolha();
                                if (usar_magia &&
                                        jogador.getHp_atual() != jogador.getCla_jogador().getHp_jogador())
                                {
                                    int restaurou_hp = jogador.getHp_atual();
                                    RestauraVidaJogador(jogador.getMagias_aprendidas().get(id_magia_int).getVitalidade(),
                                            jogador);
                                    restaurou_hp = jogador.getHp_atual() - restaurou_hp;
                                    int perdeu_mp = jogador.getMp_atual();
                                    PerderMana(jogador, jogador.getMagias_aprendidas().get(id_magia_int).getCusto_mana());
                                    perdeu_mp = jogador.getMp_atual() - perdeu_mp;
                                    System.out.println();
                                    System.out.printf("%s restaurou %s HP por %s MP!",
                                            jogador.getMagias_aprendidas().get(id_magia_int).getNome(), restaurou_hp, perdeu_mp);
                                    System.out.println();
                                    Pausa();
                                }
                                else if (!usar_magia)
                                {
                                    System.out.println();
                                }
                                else
                                {
                                    System.out.println("Sua vida já esta cheia...");
                                    System.out.println();
                                    Pausa();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    void VerArma(Jogador jogador)
    {
        if(jogador.getInv_jogador().getSlot_arma().size() == 0)
        {
            System.out.printf("%s não tem um arma equipada.",jogador.getNome());
        }
        else
        {
            System.out.printf("%s",jogador.getInv_jogador().getSlot_arma().get(0).getNome());
            String desc = jogador.getInv_jogador().getSlot_arma().get(0).getDescricao();
            desc = printBonito(desc);
            System.out.println();
            System.out.println(desc);
            System.out.println();
            jogador.getInv_jogador().getSlot_arma().get(0).DescreverAtributosModificador();
            System.out.println();
            String descartar = RetornaStr("ENTER - Continuar | D - Descartar");
            if (descartar.equals("d") || descartar.equals("D"))
            {
                System.out.printf("%s descartou %s",jogador.getNome(),
                        jogador.getInv_jogador().getSlot_arma().get(0).getNome());
                System.out.println();
                jogador.getInv_jogador().getSlot_arma().remove(0);
            }
        }
    }
    void VerArmadura(Jogador jogador)
    {
        if(jogador.getInv_jogador().getSlot_armadura().size() == 0)
        {
            System.out.printf("%s não tem um armadura equipada.",jogador.getNome());
        }
        else
        {
            System.out.printf("%s",jogador.getInv_jogador().getSlot_armadura().get(0).getNome());
            String desc = jogador.getInv_jogador().getSlot_armadura().get(0).getDescricao();
            desc = printBonito(desc);
            System.out.println();
            System.out.println(desc);
            System.out.println();
            jogador.getInv_jogador().getSlot_armadura().get(0).DescreverAtributosModificador();
            System.out.println();
            String descartar = RetornaStr("ENTER - Continuar | D - Descartar");
            if (descartar.equals("d") || descartar.equals("D"))
            {
                System.out.printf("%s descartou %s",jogador.getNome(),
                        jogador.getInv_jogador().getSlot_armadura().get(0).getNome());
                System.out.println();
                jogador.getInv_jogador().getSlot_armadura().remove(0);
            }
        }
    }

    void AbrirInventarioCombate(Jogador jogador)
    {
        if(jogador.getInv_jogador().getConsumivels().size() <= 0)
        {
            System.out.println("Não há items no seu inventário.");
            Pausa();
        }
        else {
            boolean escolha_item = true;
            while (escolha_item)
            {
                if(jogador.getInv_jogador().getConsumivels().size()  <= 0)
                {
                    escolha_item = false;
                }
                else {
                    int cont = 0;
                    System.out.println("################################################################");
                    for(ItemConsumivel x: jogador.getInv_jogador().getConsumivels())
                    {
                        System.out.printf("%s - %s",cont,x.getNome());
                        x.DescreverAtributosItem();
                        System.out.println();
                        cont +=1;
                    }
                    System.out.println("################################################################");
                    String id_consumivel = RetornaStr("Escolha um item ou tecle S para voltar.");
                    if (id_consumivel.equals("s") || id_consumivel.equals("S")) {
                        escolha_item = false;
                    }
                    else {
                        int id_item_int = ConsumivelEscolhido(jogador.getInv_jogador().getConsumivels(), id_consumivel);
                        System.out.println(id_item_int);
                        if (id_item_int < 0) {
                            System.out.println("Seleção inválida");
                            Pausa();
                        }
                        else {
                            boolean confirmar = true;
                            while (confirmar)
                            {
                                System.out.println();
                                System.out.printf("%s:",
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                System.out.println();
                                String descricao = printBonito(
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getDescricao());
                                System.out.println(descricao);
                                System.out.println();
                                System.out.printf("Consumir %s?",
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                boolean escolha = RetornaEscolha();
                                if (!escolha)
                                {
                                    confirmar = false;
                                }
                                else {
                                    System.out.println();
                                    System.out.printf("%s usou %s!", jogador.getNome(),
                                            jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                    System.out.println();
                                    Pausa();
                                    // Usa o item e aplica os efeitos no jogador
                                    UsarItem(jogador.getInv_jogador().getConsumivels().get(id_item_int), jogador);
                                    // Depois deleta o consumivel do inventario
                                    jogador.getInv_jogador().getConsumivels().remove(id_item_int);
                                    confirmar = false;
                                    escolha_item = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void DescartarItemConsumivel(Jogador jogador)
    {
        if(jogador.getInv_jogador().getConsumivels().size() <= 0)
        {
            System.out.println("Não há items no seu inventário.");
            Pausa();
        }
        else {
            boolean escolha_item = true;
            while (escolha_item)
            {
                if(jogador.getInv_jogador().getConsumivels().size()  <= 0)
                {
                    escolha_item = false;
                }
                else {
                    int cont = 0;
                    System.out.println("################################################################");
                    for(ItemConsumivel x: jogador.getInv_jogador().getConsumivels())
                    {
                        System.out.printf("%s - %s",cont,x.getNome());
                        x.DescreverAtributosItem();
                        System.out.println();
                        cont +=1;
                    }
                    System.out.println("################################################################");
                    String id_consumivel = RetornaStr("Escolha um item.");
                    int id_item_int = ConsumivelEscolhido(jogador.getInv_jogador().getConsumivels(), id_consumivel);
                    System.out.println(id_item_int);
                    if (id_item_int < 0) {
                        System.out.println("Seleção inválida");
                    }
                    else {
                        boolean confirmar = true;
                        while (confirmar)
                        {
                            System.out.println();
                            System.out.printf("Descartar %s?",
                                    jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                            boolean escolha = RetornaEscolha();
                            if (!escolha)
                            {
                                confirmar = false;
                            }
                            else {
                                System.out.println();
                                System.out.printf("%s descartou! %s!", jogador.getNome(),
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                System.out.println();
                                Pausa();
                                // DESCARTA
                                jogador.getInv_jogador().getConsumivels().remove(id_item_int);
                                confirmar = false;
                                escolha_item = false;
                            }
                        }
                    }
                }
            }
        }
    }

    void ComprarItemConsumivel(Jogador jogador, ArrayList<ItemConsumivel> consumiveis_venda, int escalador)
    {
        boolean comprar = true;
        while (comprar){
            if (escalador <= 0)
            {
                escalador += 1;
            }
            if(jogador.getInv_jogador().getConsumivels().size() == jogador.getInv_jogador().getMax_consumiveis())
            {
                System.out.printf("%s está com o inventário cheio...",jogador.getNome());
                System.out.println();
                Pausa();
                comprar = false;
            }
            else if(consumiveis_venda.size() <= 0)
            {
                System.out.println("Acabaram as ofertas...");
                Pausa();
                comprar = false;
            }
            else
            {
                int cont = 0;
                System.out.println("################################################################");
                for (ItemConsumivel x : consumiveis_venda)
                {
                    System.out.printf("%s - %s ",cont,x.getNome());
                    x.DescreverAtributosItem();
                    System.out.println();
                    cont +=1;
                }
                System.out.println("################################################################");
                String id_consumivel = RetornaStr("Escolha um item para comprar ou S para voltar.");
                if (id_consumivel.equals("s") || id_consumivel.equals("S"))
                {
                    comprar = false;
                }
                int id_item_int = ConsumivelEscolhido(consumiveis_venda, id_consumivel);
                if (id_item_int < 0)
                {
                    System.out.println("Seleção inválida");
                }
                else
                {
                    int preco_bruto = consumiveis_venda.get(id_item_int).getValor();
                    int preco_total;
                    preco_total = (preco_bruto) * escalador;
                    boolean confirmar_compra = true;
                    while (confirmar_compra)
                    {

                        System.out.println();
                        System.out.printf("Comprar %s por %s πCoin(s)?",consumiveis_venda.get(id_item_int).getNome(),
                                preco_total);
                        System.out.println();
                        boolean escolha = RetornaEscolha();
                        if (!escolha)
                        {
                            confirmar_compra = false;
                        }
                        else
                        {
                            if((jogador.getMoedas() - preco_total) < 0)
                            {
                                System.out.printf("%s não tem %s πCoin(s)...",jogador.getNome(), preco_total);
                                System.out.println();
                                confirmar_compra = false;
                            }
                            else
                            {
                                System.out.printf("%s comprou %s!",jogador.getNome(),
                                       consumiveis_venda.get(id_item_int).getNome());
                                System.out.println();
                                jogador.getInv_jogador().getConsumivels().add(consumiveis_venda.get(id_item_int));
                                consumiveis_venda.remove(id_item_int);
                                jogador.setMoedas(jogador.getMoedas() - preco_total);
                                confirmar_compra = false;
                            }
                        }
                    }
                }
            }
        }
    }

    void VenderItemConsumivel(Jogador jogador)
    {
        if(jogador.getInv_jogador().getConsumivels().size() <= 0)
        {
            System.out.println("Não há items no seu inventário.");
            Pausa();
        }
        else {
            boolean escolha_item = true;
            while (escolha_item)
            {
                if(jogador.getInv_jogador().getConsumivels().size()  <= 0)
                {
                    escolha_item = false;
                }
                else {
                    int cont = 0;
                    System.out.println("################################################################");
                    for(ItemConsumivel x: jogador.getInv_jogador().getConsumivels())
                    {
                        System.out.printf("%s - %s",cont,x.getNome());
                        x.DescreverAtributosItem();
                        System.out.println();
                        cont +=1;
                    }
                    System.out.println("################################################################");
                    String id_consumivel = RetornaStr("Escolha um item para vender ou S para Voltar.");
                    if(id_consumivel.equals("s") || id_consumivel.equals("S"))
                    {
                        escolha_item = false;
                    }
                    int id_item_int = ConsumivelEscolhido(jogador.getInv_jogador().getConsumivels(), id_consumivel);
                    if (id_item_int < 0) {
                        System.out.println("Seleção inválida");
                    }
                    else {
                        boolean confirmar = true;
                        while (confirmar)
                        {
                            int preco_bruto = jogador.getInv_jogador().getConsumivels().get(id_item_int).getValor();
                            int preco_total = preco_bruto/4 * jogador.getCla_jogador().getSorte();
                            System.out.println();
                            System.out.printf("Vender %s por %s πCoin(s)?",
                                    jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome(), preco_total);
                            boolean escolha = RetornaEscolha();
                            if (!escolha)
                            {
                                confirmar = false;
                            }
                            else {
                                System.out.println();
                                System.out.printf("%s vendeu %s!", jogador.getNome(),
                                        jogador.getInv_jogador().getConsumivels().get(id_item_int).getNome());
                                System.out.println();
                                jogador.setMoedas(jogador.getMoedas() + preco_total);
                                jogador.VerDinheiro();
                                Pausa();
                                // DELETA
                                jogador.getInv_jogador().getConsumivels().remove(id_item_int);
                                confirmar = false;
                                escolha_item = false;
                            }
                        }
                    }
                }
            }
        }
    }

    void PegarConsumivel(Jogador jogador, ItemConsumivel drop_consumivel)
    {
        if(jogador.getInv_jogador().getConsumivels().size() >= jogador.getInv_jogador().getMax_consumiveis())
        {
            System.out.printf("%s achou %s.",jogador.getNome(),drop_consumivel.getNome());
            String desc = printBonito(drop_consumivel.getDescricao());
            System.out.println();
            System.out.println();
            drop_consumivel.DescreverAtributosItem();
            System.out.println();
            System.out.println(desc);
            Pausa();
            System.out.printf("%s está com o inventário cheio.",jogador.getNome());
            System.out.println();
            Pausa();
            int cont = 0;
            System.out.println("################################################################");
            for(ItemConsumivel x: jogador.getInv_jogador().getConsumivels())
            {
                System.out.printf("%s - %s",cont,x.getNome());
                System.out.println();
                cont +=1;
            }
            System.out.println("################################################################");
            System.out.printf("Deseja descartar um item para pegar %s?",drop_consumivel.getNome());
            boolean descartar = RetornaEscolha();
            if(!descartar)
            {
                System.out.println();
                System.out.printf("%s não pegou %s.",jogador.getNome(),drop_consumivel.getNome());
                Pausa();
            }
            else
            {
                DescartarItemConsumivel(jogador);
                jogador.getInv_jogador().getConsumivels().add(drop_consumivel);
            }
        }
        else
        {
            System.out.printf("%s achou %s.",jogador.getNome(),drop_consumivel.getNome());
            String desc = printBonito(drop_consumivel.getDescricao());
            System.out.println();
            System.out.println();
            drop_consumivel.DescreverAtributosItem();
            System.out.println();
            System.out.println();
            System.out.println(desc);
            System.out.printf("Pegar %s?",drop_consumivel.getNome());
            boolean pegar = RetornaEscolha();
            if(pegar)
            {
                System.out.println();
                System.out.printf("%s pegou %s!",jogador.getNome(),drop_consumivel.getNome());
                jogador.getInv_jogador().getConsumivels().add(drop_consumivel);
                Pausa();
            }
            else
            {
                System.out.println();
                System.out.printf("%s não pegou %s.",jogador.getNome(),drop_consumivel.getNome());
                Pausa();
            }
        }
    }

    void PegarArma(Jogador jogador, ItemArma drop_arma)
    {
        if(jogador.getInv_jogador().getSlot_arma().size() >= 1)
        {
            System.out.printf("%s achou %s.",jogador.getNome(),drop_arma.getNome());
            String desc = printBonito(drop_arma.getDescricao());
            System.out.println();
            System.out.println();
            drop_arma.DescreverAtributosModificador();
            System.out.println();
            System.out.println(desc);
            Pausa();
            System.out.printf("%s já tem uma arma equipada.",jogador.getNome());
            System.out.println();
            System.out.printf("Deseja descartar %s por essa arma?",
                    jogador.getInv_jogador().getSlot_arma().get(0).getNome());
            System.out.println();
            boolean descartar = RetornaEscolha();
            if(!descartar)
            {
                System.out.println();
                System.out.printf("%s não pegou %s.",jogador.getNome(),drop_arma.getNome());
                System.out.println();
                Pausa();
            }
            else
            {
                System.out.println();
                System.out.printf("%s descartou %s por %s!", jogador.getNome(),
                        jogador.getInv_jogador().getSlot_arma().get(0).getNome(), drop_arma.getNome());
                System.out.println();
                Pausa();
                jogador.getInv_jogador().getSlot_arma().remove(0);
                jogador.getInv_jogador().getSlot_arma().add(drop_arma);
            }
        }
        else
        {
            System.out.printf("%s achou %s.",jogador.getNome(),drop_arma.getNome());
            String desc = printBonito(drop_arma.getDescricao());
            System.out.println();
            drop_arma.DescreverAtributosModificador();
            System.out.println();
            System.out.println(desc);
            System.out.printf("Pegar %s?",drop_arma.getNome());
            boolean pegar = RetornaEscolha();
            if(pegar) {
                System.out.println();
                System.out.printf("%s pegou %s!", jogador.getNome(), drop_arma.getNome());
                System.out.println();
                Pausa();
                jogador.getInv_jogador().getSlot_arma().add(drop_arma);
            }
            else
            {
                System.out.println();
                System.out.printf("%s não pegou %s.",jogador.getNome(),drop_arma.getNome());
                System.out.println();
                Pausa();
            }
        }
    }
    void PegarArmadura(Jogador jogador, ItemArmadura drop_armadura)
    {
        if(jogador.getInv_jogador().getSlot_armadura().size() >= 1)
        {
            System.out.printf("%s achou %s.",jogador.getNome(),drop_armadura.getNome());
            String desc = printBonito(drop_armadura.getDescricao());
            System.out.println();
            System.out.println();
            drop_armadura.DescreverAtributosModificador();
            System.out.println();
            System.out.println(desc);
            Pausa();
            System.out.printf("%s já tem uma armadura equipada.",jogador.getNome());
            System.out.println();
            System.out.printf("Deseja descartar %s por essa armadura",
                    jogador.getInv_jogador().getSlot_armadura().get(0).getNome());
            boolean descartar = RetornaEscolha();
            if(!descartar)
            {
                System.out.println();
                System.out.printf("%s não pegou %s.",jogador.getNome(),drop_armadura.getNome());
                System.out.println();
                Pausa();
            }
            else
            {
                System.out.println();
                System.out.printf("%s descartou %s por %s!", jogador.getNome(),
                        jogador.getInv_jogador().getSlot_armadura().get(0).getNome(), drop_armadura.getNome());
                System.out.println();
                Pausa();
                jogador.getInv_jogador().getSlot_armadura().remove(0);
                jogador.getInv_jogador().getSlot_armadura().add(drop_armadura);
            }
        }
        else
        {
            System.out.printf("%s achou %s.",jogador.getNome(),drop_armadura.getNome());
            String desc = printBonito(drop_armadura.getDescricao());
            System.out.println();
            drop_armadura.DescreverAtributosModificador();
            System.out.println();
            Pausa();
            System.out.println();
            System.out.println(desc);
            System.out.printf("Pegar %s?",drop_armadura.getNome());
            boolean pegar = RetornaEscolha();
            if(pegar)
            {
            System.out.println();
            System.out.printf("%s pegou %s!",jogador.getNome(),drop_armadura.getNome());
            System.out.println();
            Pausa();
            jogador.getInv_jogador().getSlot_armadura().add(drop_armadura);
            }
            else
            {
                System.out.println();
                System.out.printf("%s não pegou %s.",jogador.getNome(),drop_armadura.getNome());
                System.out.println();
                Pausa();
            }
        }
    }

    void PegarMagia(Jogador jogador, Magia drop_magia)
    {
        if (jogador.getMagias_aprendidas().size() <= 0)
        {
            System.out.println();
            System.out.printf("%s aprendeu %s!",jogador.getNome(),drop_magia.getNome());
            System.out.println();
            Pausa();
            jogador.getMagias_aprendidas().add(drop_magia);
        }
        else
        {
            boolean tem_magia = false;
            int mutar_cura = 0;
            int mutar_atk = 0;
            for (Magia x: jogador.getMagias_aprendidas())
            {
                if (x.getNome().equals(drop_magia.getNome())) {
                    tem_magia = true;
                    if(x.getVitalidade() != 0)
                    {
                        mutar_cura = 2;
                        int vit_original = x.getVitalidade();
                        x.setVitalidade(vit_original +mutar_cura);
                    }
                    if(x.getAtaque() != 0)
                    {
                        mutar_atk = 2;
                        int atk_original = x.getAtaque();
                        x.setAtaque(atk_original + mutar_atk);
                    }
                    break;
                }
            }
            if(tem_magia)
            {   // Verifica o que a magia afeta e incrementa o ataque
                System.out.printf("%s parece estar evoluindo...",drop_magia.getNome());
                System.out.println();
                Pausa();
                if(mutar_atk > 0)
                {
                    System.out.printf("%s ganhou %s de Ataque!",drop_magia.getNome(), mutar_atk);
                    System.out.println();
                    Pausa();
                }
                if(mutar_cura > 0)
                {
                    System.out.printf("%s ganhou %s HP de cura!",drop_magia.getNome(), mutar_cura);
                    System.out.println();
                    Pausa();
                }
            }
            else
            {
                jogador.getMagias_aprendidas().add(drop_magia);
            }
        }
    }

    // ##############################
    // COMBATE GERAL | ESCOLHA MAGIA INVENTARIO ATAQUE | CANCELAR BUFF DO JOGADOR
    void BATALHA(Jogador jogador, MonstroNormal monstro, ListaItens itens, ListaMagia magias, int escalador)
    {
        int dano_causado;
        int duracao_buff = 2; // O buff do jogador depleta em 2 turnos no combate

        int armadura = 0; // defesa da armadura
        if(jogador.getInv_jogador().getSlot_armadura().size() == 1)
        { // verifica se tem armadura
            armadura = jogador.getInv_jogador().getSlot_armadura().get(0).getDefesa();
        }
        int hp_monstro = monstro.getVitalidade();
        // DEFINE QUEM COMEÇA.
        // Quem tiver a maior agilidade começa com o ataque
        // Caso a agilidade seja igual, usa RNG

        boolean turno_jogador = PrimeiroAtaque(jogador, monstro); // Ve quem começa conforme nivel de agilidade.
        boolean turno_monstro; // No loop o jogadr esta primeiro mas o que define quem vai é a agilidade.

        System.out.println();
        System.out.println();

        while (jogador.getHp_atual() > 0 && hp_monstro > 0)
        {
            // Vez jogador
            if(turno_jogador)
            {
                System.out.printf("Turno de %s.",jogador.getNome());
                System.out.println();
                Pausa();
            }
            while(turno_jogador)
            {
                EstadoCombateMonstroNormal(jogador,monstro, hp_monstro);
                String jogada = RetornaStr("A - Atacar | M - Magia | I - Inventario");
                // ATAQUE
                boolean atacar;
                if(jogada.equals("a") || jogada.equals("A"))
                {
                    // ve se tem arma
                    if(jogador.getInv_jogador().getSlot_arma().size() == 1){
                        System.out.println(jogador.getInv_jogador().getSlot_arma().get(0).getNome());
                        System.out.println();
                        String desc_arma = jogador.getInv_jogador().getSlot_arma().get(0).getDescricao();
                        desc_arma = printBonito(desc_arma);
                        System.out.println(desc_arma);
                        jogador.getInv_jogador().getSlot_arma().get(0).DescreverAtributosModificador();
                        System.out.println();
                        System.out.printf("Atacar com %s?",jogador.getInv_jogador().getSlot_arma().get(0).getNome());
                        System.out.println();
                        atacar = RetornaEscolha();
                    }
                    else
                    {
                        System.out.println("Atacar com os punhos?");
                        atacar = RetornaEscolha();
                    }
                    if(atacar) {
                        dano_causado = hp_monstro;
                        System.out.println();
                        System.out.printf("%s ataca!", jogador.getNome());
                        System.out.println();
                        int dano_bruto = ATK_JOG_BRUTO(jogador); // ATAQUE
                        int dano_total = ATK_JOG_CRITICO_EVASAO(jogador, monstro, dano_bruto);
                        hp_monstro -= dano_total;
                        dano_causado -= hp_monstro;
                        if (dano_causado > 0) {
                            System.out.println();
                            System.out.printf("%s perdeu %s HP.", monstro.getNome(), dano_causado);
                            System.out.println();
                            Pausa();
                        } else {
                            System.out.println();
                            System.out.printf("%s não perdeu vida.", monstro.getNome());
                            System.out.println();
                            Pausa();
                        }
                        turno_jogador = false;
                        duracao_buff -= 1;
                        DepletaBuff(jogador, duracao_buff);
                    }
                }
                else if(jogada.equals("m") || jogada.equals("M"))
                {
                    // MAGIA
                    if(jogador.getMagias_aprendidas().size() <= 0)
                    {
                        System.out.println();
                        System.out.printf("%s não conhece nenhuma magia.",jogador.getNome());
                        System.out.println();
                        Pausa();
                    }
                    else
                    {
                        Magia magia_combate = ABRIRMAGIACOMBATE(jogador);
                        if(magia_combate != null)
                        {
                            if(magia_combate.getAtaque() > 0) { // Verifica se a magia causa dano
                                dano_causado = hp_monstro;
                                int dano_bruto_magia = ATK_JOG_MAGIA_BRUTO(jogador, magia_combate);
                                int dano_total_magia = ATK_JOG_MAGIA_CRITICO_EVASAO(jogador, monstro, dano_bruto_magia);
                                hp_monstro -= dano_total_magia;
                                dano_causado -= hp_monstro;
                                if (dano_causado > 0) {
                                    System.out.println();
                                    System.out.printf("%s perdeu %s HP.", monstro.getNome(), dano_causado);
                                    System.out.println();
                                    Pausa();
                                } else {
                                    System.out.println();
                                    System.out.printf("%s não perdeu vida.", monstro.getNome());
                                    System.out.println();
                                    Pausa();
                                }
                            }
                            turno_jogador = false;
                            duracao_buff -= 1;
                            DepletaBuff(jogador, duracao_buff);
                        }
                    }
                }
                // Inventario
                else if(jogada.equals("i") || jogada.equals("I"))
                {
                    int checar_itens = jogador.getInv_jogador().getConsumivels().size();
                    AbrirInventarioCombate(jogador);
                    if(checar_itens != jogador.getInv_jogador().getConsumivels().size())
                    {
                        turno_jogador = false;
                        duracao_buff = 2;
                    }
                }
                else {
                    System.out.println("Comando Inválido");
                }
                // ATK JOGADOR
                // SE ATACAR DEVE PERDER O BUFF
                // SE USAR ITEM DEVE PERDER O BUFF DPS DO TURNO DO MONSTRO
                // VER TBM SE O JOGADOR N SE MATA COM UM ITEM Q TIRA  VIDA NA HORA DO CONSUMO
                // VERI
            }
            // Se o jogador morrer skipa turno do monstro
            turno_monstro = jogador.getHp_atual() > 0 && hp_monstro > 0;
            if(turno_monstro)
            {
                System.out.printf("Turno de %s",monstro.getNome());
                System.out.println();
                Pausa();
            }
            // TURNO MONSTRO
            while (turno_monstro)
            {
                if(jogador.getInv_jogador().getSlot_armadura().size() == 1)
                { // verifica se tem armadura
                    armadura = jogador.getInv_jogador().getSlot_armadura().get(0).getDefesa();
                }
                dano_causado = jogador.getHp_atual();
                int dano_bruto_monstro = ATK_MONSTRO_BRUTO(jogador,monstro);
                int dano_total_monstro = ATK_MONSTRO_CRITICO_EVASAO(jogador,monstro, dano_bruto_monstro) - armadura;
                if(dano_total_monstro < 0)
                {
                    dano_total_monstro = 0;
                }
                jogador.setHp_atual(jogador.getHp_atual()-dano_total_monstro);
                turno_monstro = false;
                dano_causado -= jogador.getHp_atual();
                if(dano_causado > 0)
                {
                    System.out.println();
                    System.out.printf("%s perdeu %s HP.",jogador.getNome(),dano_causado);
                    System.out.println();
                    Pausa();
                }
                else
                {
                    System.out.println();
                    System.out.printf("%s não perdeu vida.",jogador.getNome());
                    System.out.println();
                    Pausa();
                }
                turno_jogador = true;
                duracao_buff -= 1;
                DepletaBuff(jogador, duracao_buff);
            }
        }
        // Ver quem ganhou
        if(jogador.getHp_atual() <= 0) // Se o jogador perde
        {
            System.out.println();
            System.out.printf("%s faleceu para %s",jogador.getNome(),monstro.getNome());
            System.out.println();
            Pausa();
        }
        else // Se o monstro é derrotado
        {
            System.out.println();
            System.out.printf("%s morreu!", monstro.getNome());
            System.out.println();
            Pausa();
            ChanceDrop(jogador, itens, magias, escalador);
            DropPiCoinsMonstro(jogador,monstro,escalador);
            DepletaBuff(jogador,0);
            int monstros_abatidos = jogador.getMonstros_abatidos(); // adiciona pra metrica
            jogador.setMonstros_abatidos(monstros_abatidos + 1);
            Pausa();
        }
    }
    void BATALHACHEFE(Jogador jogador, ArrayList<MonstroChefe> chefes, ListaItens itens, ListaMagia magias,
                      int escalador)
    {
        int armadura = 0;
        if(jogador.getInv_jogador().getSlot_armadura().size() == 1)
        {
            armadura = jogador.getInv_jogador().getSlot_armadura().get(0).getDefesa();
        }
        int dano_causado;
        int duracao_buff = 2; // O buff do jogador depleta em 2 turnos no combate
        MonstroChefe chefe = chefes.get(rand.nextInt(chefes.size()));
        chefe.setForca(chefe.getForca()+escalador);
        chefe.setAgilidade(chefe.getAgilidade()+escalador);
        chefe.setSorte(chefe.getSorte()+escalador);
        chefe.setInteligencia(chefe.getInteligencia()+escalador);
        chefe.setPercepcao(chefe.getPercepcao()+escalador);
        chefe.setVitalidade(chefe.getVitalidade()+(12*escalador));
        chefe.setAtk_dano(chefe.getAtk_dano() +((chefe.getAtk_dano()/2)*escalador));
        int hp_chefe = chefe.getVitalidade();
        boolean turno_jogador = PrimeiroAtaque(jogador, chefe); // Ve quem começa conforme nivel de agilidade.
        boolean turno_chefe; // No loop o jogado esta primeiro mas o que define quem vai é a agilidade.

        System.out.println();
        System.out.println();

        while (jogador.getHp_atual() > 0 && hp_chefe > 0)
        {
            // Vez jogador
            if(turno_jogador)
            {
                System.out.printf("Turno de %s.",jogador.getNome());
                System.out.println();
                Pausa();
            }
            while(turno_jogador)
            {
                EstadoCombateMonstroNormal(jogador,chefe, hp_chefe);
                String jogada = RetornaStr("A - Atacar | M - Magia | I - Inventario");
                boolean atacar;
                // ATAQUE
                if(jogada.equals("a") || jogada.equals("A"))
                {
                    // ve se tem arma
                    if(jogador.getInv_jogador().getSlot_arma().size() == 1){
                        System.out.println(jogador.getInv_jogador().getSlot_arma().get(0).getNome());
                        System.out.println();
                        String desc_arma = jogador.getInv_jogador().getSlot_arma().get(0).getDescricao();
                        desc_arma = printBonito(desc_arma);
                        System.out.println(desc_arma);
                        jogador.getInv_jogador().getSlot_arma().get(0).DescreverAtributosModificador();
                        System.out.println();
                        System.out.printf("Atacar com %s?",jogador.getInv_jogador().getSlot_arma().get(0).getNome());
                        System.out.println();
                        atacar = RetornaEscolha();
                    }
                    else
                    {
                        System.out.println("Atacar com os punhos?");
                        atacar = RetornaEscolha();
                    }
                    if(atacar) {
                        dano_causado = hp_chefe;
                        System.out.println();
                        System.out.printf("%s ataca!", jogador.getNome());
                        System.out.println();
                        Pausa();
                        int dano_bruto = ATK_JOG_BRUTO(jogador); // ATAQUE
                        int dano_total = ATK_JOG_CRITICO_EVASAO(jogador, chefe, dano_bruto);
                        hp_chefe -= dano_total;
                        dano_causado -= hp_chefe;
                        if (dano_causado > 0) {
                            System.out.println();
                            System.out.printf("%s perdeu %s HP.", chefe.getNome(), dano_causado);
                            System.out.println();
                            Pausa();
                        } else {
                            System.out.println();
                            System.out.printf("%s não perdeu vida.", chefe.getNome());
                            System.out.println();
                            Pausa();
                        }
                        turno_jogador = false;
                        duracao_buff -= 1;
                        DepletaBuff(jogador, duracao_buff);
                    }
                }
                else if(jogada.equals("m") || jogada.equals("M"))
                {
                    // MAGIA
                    if(jogador.getMagias_aprendidas().size() <= 0)
                    {
                        System.out.println();
                        System.out.printf("%s não conhece nenhuma magia.",jogador.getNome());
                        System.out.println();
                        Pausa();
                    }
                    else
                    {
                        Magia magia_combate = ABRIRMAGIACOMBATE(jogador);
                        if(magia_combate != null)
                        {
                            if(magia_combate.getAtaque() > 0) { // verifica se é magia de cura ou ataque
                                dano_causado = hp_chefe;
                                int dano_bruto_magia = ATK_JOG_MAGIA_BRUTO(jogador, magia_combate);
                                int dano_total_magia = ATK_JOG_MAGIA_CRITICO_EVASAO(jogador, chefe, dano_bruto_magia);
                                hp_chefe -= dano_total_magia;
                                dano_causado -= hp_chefe;
                                if (dano_causado > 0) {
                                    System.out.println();
                                    System.out.printf("%s perdeu %s HP.", chefe.getNome(), dano_causado);
                                    System.out.println();
                                    Pausa();
                                }
                                else {
                                    System.out.println();
                                    System.out.printf("%s não perdeu vida.", chefe.getNome());
                                    System.out.println();
                                    Pausa();
                                }
                            }
                            turno_jogador = false;
                            duracao_buff -= 1;
                            DepletaBuff(jogador, duracao_buff);
                        }
                    }
                }
                // Inventario
                else if(jogada.equals("i") || jogada.equals("I"))
                {
                    int checar_itens = jogador.getInv_jogador().getConsumivels().size();
                    AbrirInventarioCombate(jogador);
                    if(checar_itens != jogador.getInv_jogador().getConsumivels().size())
                    {
                        turno_jogador = false;
                        duracao_buff = 2;
                    }
                }
                else {
                    System.out.println("Comando Inválido");
                }
            }
            // Se o jogador morrer skipa turno do monstro
            turno_chefe = jogador.getHp_atual() > 0 && hp_chefe > 0;
            if(turno_chefe)
            {
                System.out.printf("Turno de %s.",chefe.getNome());
                System.out.println();
                Pausa();
            }
            // TURNO MONSTRO
            while (turno_chefe)
            {
                dano_causado = jogador.getHp_atual();
                int dano_bruto_monstro = ATK_MONSTRO_BRUTO(jogador,chefe);
                int dano_total_monstro = ATK_MONSTRO_CRITICO_EVASAO(jogador,chefe, dano_bruto_monstro) - armadura;
                if (dano_total_monstro < 0)
                {
                    dano_total_monstro = 0;
                }
                jogador.setHp_atual(jogador.getHp_atual()-dano_total_monstro);
                turno_chefe = false;
                dano_causado -= jogador.getHp_atual();
                if(dano_causado > 0)
                {
                    System.out.println();
                    System.out.printf("%s perdeu %s HP.",jogador.getNome(),dano_causado);
                    System.out.println();
                    Pausa();
                }
                else
                {
                    System.out.println();
                    System.out.printf("%s não perdeu vida.",jogador.getNome());
                    System.out.println();
                    Pausa();
                }
                turno_jogador = true;
                duracao_buff -= 1;
                DepletaBuff(jogador, duracao_buff);
            }
        }
        // Ver quem ganhou
        if(jogador.getHp_atual() <= 0) // Se o jogador perde
        {
            System.out.println();
            System.out.printf("%s faleceu para %s",jogador.getNome(),chefe.getNome());
            System.out.println();
            Pausa();
        }
        else // Se o chefe é derrotado
        {
            System.out.println();
            System.out.printf("%s morreu!", chefe.getNome());
            System.out.println();
            Pausa();
            ChanceDropChefe(jogador, itens, magias, escalador);
            DropPiCoinsMonstro(jogador,chefe,escalador);
            int fases_passadas = jogador.getFases_passada(); // chefes abatidos | niveis passados
            jogador.setFases_passada(fases_passadas+1);
            DepletaBuff(jogador,0);
        }
    }
    // Escolha de Magia no combate
    Magia ABRIRMAGIACOMBATE(Jogador jogador)
    {
        int perdeu_mp;
        while (true)
        {
            int cont = 0;
            System.out.println("################################################################");
            for (Magia x : jogador.getMagias_aprendidas())
            {
                System.out.printf("%s - %s ", cont, x.getNome());
                x.DescreverAtributosMagia();
                System.out.println();
                cont += 1;
            }
            System.out.println("################################################################");
            String id_magia = RetornaStr("Escolha uma magia ou Tecle S para Voltar.");
            if(id_magia.equals("s") || id_magia.equals("S"))
            {
                return null;
            }
            else
            {
                int id_magia_int = MagiaEscolhida(jogador.getMagias_aprendidas(), id_magia);
                if (id_magia_int < 0)
                {
                    System.out.println("Seleção inválida");
                }
                else
                {
                    if(jogador.getMp_atual() < jogador.getMagias_aprendidas().get(id_magia_int).getCusto_mana())
                    {
                        System.out.println();
                        System.out.printf("%s não possui MP para usar %s...",jogador.getNome(),
                                jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                        System.out.println();
                        Pausa();
                    }
                    else
                    {
                        System.out.printf("%s",jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                        System.out.println();
                        System.out.println("Descrição:");
                        String descricao = printBonito(jogador.getMagias_aprendidas().get(id_magia_int).getDescricao());
                        System.out.println(descricao);
                        System.out.println();
                        jogador.getMagias_aprendidas().get(id_magia_int).DescreverAtributosMagia();
                        System.out.println();
                        System.out.printf("Deseja usar %s?",
                                jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                        System.out.println();
                        boolean usar_magia = RetornaEscolha();
                        if (usar_magia) {
                            if (jogador.getMagias_aprendidas().get(id_magia_int).getVitalidade() != 0) {
                                if (jogador.getMagias_aprendidas().get(id_magia_int).getVitalidade() < 0) {
                                    int dif_hp = jogador.getHp_atual();
                                    RestauraVidaJogador(jogador.getMagias_aprendidas().get(id_magia_int).getVitalidade(),
                                            jogador);
                                    dif_hp = jogador.getHp_atual() - dif_hp;
                                    perdeu_mp = jogador.getMp_atual();
                                    PerderMana(jogador, jogador.getMagias_aprendidas().get(id_magia_int).getCusto_mana());
                                    perdeu_mp = jogador.getMp_atual() - perdeu_mp;
                                    System.out.println();
                                    System.out.printf("%s perdeu %s HP por usar %s...",
                                            jogador.getNome(), dif_hp, jogador.getMagias_aprendidas().get(id_magia_int).getNome());
                                    System.out.println();
                                    System.out.printf("%s consumiu %s MP!",
                                            jogador.getMagias_aprendidas().get(id_magia_int).getNome(), perdeu_mp);
                                    System.out.println();
                                    return jogador.getMagias_aprendidas().get(id_magia_int);
                                }
                                else
                                {
                                    int restaurou_hp = jogador.getHp_atual();
                                    RestauraVidaJogador(jogador.getMagias_aprendidas().get(id_magia_int).getVitalidade(),
                                            jogador);
                                    restaurou_hp = jogador.getHp_atual() - restaurou_hp;
                                    perdeu_mp = jogador.getMp_atual();
                                    PerderMana(jogador, jogador.getMagias_aprendidas().get(id_magia_int).getCusto_mana());
                                    perdeu_mp = jogador.getMp_atual() - perdeu_mp;
                                    System.out.println();
                                    System.out.printf("%s restaurou %s HP!",
                                            jogador.getMagias_aprendidas().get(id_magia_int).getNome(), restaurou_hp);
                                    System.out.println();
                                    System.out.println();
                                    System.out.printf("%s consumiu %s MP!",
                                            jogador.getMagias_aprendidas().get(id_magia_int).getNome(), perdeu_mp);
                                    System.out.println();
                                    return jogador.getMagias_aprendidas().get(id_magia_int);
                                }
                            }
                            else
                            {
                                perdeu_mp = jogador.getMp_atual();
                                PerderMana(jogador, jogador.getMagias_aprendidas().get(id_magia_int).getCusto_mana());
                                perdeu_mp = jogador.getMp_atual() - perdeu_mp;
                                System.out.println();
                                System.out.printf("%s consumiu %s MP!",
                                        jogador.getMagias_aprendidas().get(id_magia_int).getNome(), perdeu_mp);
                                System.out.println();
                                return jogador.getMagias_aprendidas().get(id_magia_int);
                            }
                        }
                        else
                        {
                            return null;
                        }
                    }
                }
            }
        }
    }
    // Cancela os buff do jogador
    void DepletaBuff(Jogador jogador,int contador_buff)
    {
        if((jogador.getBuff_for() != 0 || jogador.getBuff_agi() != 0 || jogador.getBuff_sor() != 0 ||
                jogador.getBuff_int() != 0 || jogador.getBuff_per() !=0) && contador_buff <=0)
        {
            System.out.println();
            System.out.printf("%s sente o efeito dos consumiveis acabar...",jogador.getNome());
            System.out.println();
            jogador.setBuff_for(0);
            jogador.setBuff_agi(0);
            jogador.setBuff_sor(0);
            jogador.setBuff_int(0);
            jogador.setBuff_per(0);
        }

    }
    //###################
    //Drops dos MONSTROS NORMAIS
    //
    void ChanceDrop(Jogador jogador, ListaItens itens, ListaMagia magias, int escalador)
    {
        // gera um numero de 0-9 (1 em 10)
        // 5 de 10 são consumiveis
        // 2 de 10 são armas
        // 2 de 10 são armaduras
        // 1 de 10 são magias
        // para ver a chance de loot
        int chance = rand.nextInt(10);
        if(chance < 5)
        {
            ItemConsumivel drop_consumivel = DropConsumivelMonstro(itens.getLista_consumiveis());
            PegarConsumivel(jogador, drop_consumivel);
        }
        else if(chance < 7)
        {
            ItemArma drop_arma = DropArmaMonstro(itens.getLista_armas());
            if (drop_arma.getTipo().equals("FOR"))
            {
                // Variação de drops arma
                int atr = rand.nextInt(2);
                int roll = rand.nextInt(2);
                if(roll == 0)
                {
                    drop_arma.setForca(drop_arma.getForca()-atr);
                    if(drop_arma.getForca() <= 0)
                    {
                        drop_arma.setForca(0);
                    }
                }
                else
                {
                    drop_arma.setForca(drop_arma.getForca()+atr);
                }
            }
            else // Variação de arma per
            {
                // Variação de drops arma
                int atr = rand.nextInt(2);
                int roll = rand.nextInt(2);
                if(roll == 0)
                {
                    drop_arma.setPercepcao(drop_arma.getPercepcao()-atr);
                    if(drop_arma.getForca() <= 0)
                    {
                        drop_arma.setPercepcao(0);
                    }
                }
                else
                {
                    drop_arma.setPercepcao(drop_arma.getPercepcao()+atr);
                }
            }
            drop_arma.setAtaque(drop_arma.getAtaque() + (3 * escalador));
            PegarArma(jogador, drop_arma);
        }
        else if(chance < 9)
        {
            ItemArmadura drop_armadura = DropArmaduraMonstro(itens.getLista_armaduras());
            drop_armadura.setDefesa(drop_armadura.getDefesa() + (3 * escalador));
            PegarArmadura(jogador, drop_armadura);
        }
        else
        {
            Magia drop_magia = DropMagiaMonstro(magias.getMagias());
            PegarMagia(jogador,drop_magia);
        }
    }
    ItemConsumivel DropConsumivelMonstro(ArrayList<ItemConsumivel> consumivels)
    {
        ArrayList<ItemConsumivel> drop_monstro = new ArrayList<>();
        for(ItemConsumivel x: consumivels)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("MONSTROS"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }
    ItemArma DropArmaMonstro(ArrayList<ItemArma> armas)
    {
        ArrayList<ItemArma> drop_monstro = new ArrayList<>();
        for(ItemArma x: armas)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("MONSTROS"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }
    ItemArmadura DropArmaduraMonstro(ArrayList<ItemArmadura> armaduras)
    {
        ArrayList<ItemArmadura> drop_monstro = new ArrayList<>();
        for(ItemArmadura x: armaduras)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("MONSTROS"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }
    Magia DropMagiaMonstro(ArrayList<Magia> magias)
    {
        ArrayList<Magia> drop_monstro = new ArrayList<>();
        for(Magia x: magias)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("MONSTROS"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }
    // ######################################################
    // DROPS DE CHEFE
    void ChanceDropChefe(Jogador jogador, ListaItens itens, ListaMagia magias, int escalador)
    {
        // 2 de 10 = consumiveis
        // 3 de 10 = armas
        // 3 de 10 = armaduras
        // 2 de 10 = magias
        // gera um numero de 0-9 (1 em 10)
        // para ver a chance de loot
        int chance = rand.nextInt(10);
        if(chance < 2)
        {
            ItemConsumivel drop_consumivel = DropConsumivelChefe(itens.getLista_consumiveis());
            PegarConsumivel(jogador, drop_consumivel);
        }
        else if(chance < 5)
        {
            ItemArma drop_arma = DropArmaChefe(itens.getLista_armas());
            if (drop_arma.getTipo().equals("FOR"))
            {
                // Variação de drops arma
                int atr = rand.nextInt(2);
                int roll = rand.nextInt(2);
                if(roll == 0)
                {
                    drop_arma.setForca(drop_arma.getForca()-atr);
                    if(drop_arma.getForca() <= 0)
                    {
                        drop_arma.setForca(0);
                    }
                }
                else
                {
                    drop_arma.setForca(drop_arma.getForca()+atr);
                }
            }
            else // Variação de arma per
            {
                // Variação de drops arma
                int atr = rand.nextInt(2);
                int roll = rand.nextInt(2);
                if(roll == 0)
                {
                    drop_arma.setPercepcao(drop_arma.getPercepcao()-atr);
                    if(drop_arma.getForca() <= 0)
                    {
                        drop_arma.setPercepcao(0);
                    }
                }
                else
                {
                    drop_arma.setPercepcao(drop_arma.getPercepcao()+atr);
                }
            }
            drop_arma.setAtaque(drop_arma.getAtaque() + (3 * escalador));
            PegarArma(jogador, drop_arma);
        }
        else if(chance < 8)
        {
            ItemArmadura drop_armadura = DropArmaduraChefe(itens.getLista_armaduras());
            drop_armadura.setDefesa(drop_armadura.getDefesa() + (3 * escalador));
            PegarArmadura(jogador, drop_armadura);
        }
        else
        {
            Magia drop_magia = DropMagiaChefe(magias.getMagias());
            PegarMagia(jogador,drop_magia);
        }
    }
    ItemConsumivel DropConsumivelChefe(ArrayList<ItemConsumivel> consumivels)
    {
        ArrayList<ItemConsumivel> drop_monstro = new ArrayList<>();
        for(ItemConsumivel x: consumivels)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("CHEFES"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }

    ItemArma DropArmaChefe(ArrayList<ItemArma> armas)
    {
        ArrayList<ItemArma> drop_monstro = new ArrayList<>();
        for(ItemArma x: armas)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("CHEFES"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }

    ItemArmadura DropArmaduraChefe(ArrayList<ItemArmadura> armaduras)
    {
        ArrayList<ItemArmadura> drop_monstro = new ArrayList<>();
        for(ItemArmadura x: armaduras)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("CHEFES"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }

    Magia DropMagiaChefe(ArrayList<Magia> magias)
    {
        ArrayList<Magia> drop_monstro = new ArrayList<>();
        for(Magia x: magias)
        {
            if(x.getDropavelPor().equals("TODOS") || x.getDropavelPor().equals("CHEFES"))
            {
                drop_monstro.add(x);
            }
        }
        return drop_monstro.get(rand.nextInt(drop_monstro.size()));
    }

    void DropPiCoinsMonstro(Jogador jogador, MonstroNormal monstro, int escalador)
    {
        if (escalador <= 0)
        {
            escalador = 1;
        }
        int pi_coins_atual;
        int pi_coins_achados;
        int sorte_total_jogador = jogador.SorteTotalJogador();
        int roll;
        if(sorte_total_jogador < monstro.getSorte())
        {
            roll = rand.nextInt(2);
            if(roll == 0)
            {
                System.out.println();
                System.out.printf("%s não achou πCoins nos restos de %s...",jogador.getNome(),monstro.getNome());
                System.out.println();
            }
            else
            {
                pi_coins_atual = jogador.getMoedas();
                pi_coins_achados = rand.nextInt((5*escalador)+1) + escalador; // 1 min | 5 max e vai escalando
                jogador.setMoedas(pi_coins_atual+pi_coins_achados);
                System.out.println();
                System.out.printf("%s achou %s πCoin(s) nos restos de %s.",jogador.getNome(),pi_coins_achados,
                        monstro.getNome());
                System.out.println();
            }
        }
        else if(sorte_total_jogador == monstro.getSorte())
        {
            pi_coins_atual = jogador.getMoedas();
            pi_coins_achados = rand.nextInt((25*escalador)+1) + (5*escalador); // 5 min | 30 max conforme escala
            jogador.setMoedas(pi_coins_atual+pi_coins_achados);
            System.out.println();
            System.out.printf("%s achou %s πCoin(s) nos restos de %s.",jogador.getNome(),pi_coins_achados,
                    monstro.getNome());
            System.out.println();
        }
        else
        {
            roll = rand.nextInt(2);
            if(roll == 0)
            {
                pi_coins_atual = jogador.getMoedas();
                pi_coins_achados = rand.nextInt((65*escalador)+1) +(10*escalador); // 10 min // 75 máximo
                jogador.setMoedas(pi_coins_atual+pi_coins_achados);
                System.out.println();
                System.out.printf("%s achou %s πCoin nos restos de %s.",
                        jogador.getNome(),pi_coins_achados, monstro.getNome());
                System.out.println();
            }
            else{
            pi_coins_atual = jogador.getMoedas();
            pi_coins_achados = rand.nextInt((75*escalador)+1) +(25*escalador); // 25 min // 100 maximo
            jogador.setMoedas(pi_coins_atual+pi_coins_achados);
            System.out.println();
            System.out.printf("%s achou %s πCoin nos restos de %s!!",
                    jogador.getNome(),pi_coins_achados, monstro.getNome());
            System.out.println();
            }
        }
    }
    //#################################################################
    // CALCULO DOS ATAQUES
    int ATK_JOG_BRUTO(Jogador jogador)
    {
        int ataque;
        int total_forca_jogador = jogador.ForcaTotalJogador();
        int total_percepcao_jogador = jogador.PercepcaoTotalJogador();
        if(jogador.getInv_jogador().getSlot_arma().size() <= 0)
        {
            // Caso jogador não tem arma
            // Sera considero o tipo do ataque fisico.
            System.out.printf("%s tem que usar seus punhos...",jogador.getNome());
            System.out.println();
            Pausa();
            ataque = total_forca_jogador - (jogador.getCla_jogador().getForca()/2);
            if (ataque <= 0)
            {
                ataque = 1;
                return ataque;
            }
            else
            {
                return ataque;
            }
        }
        else
        {
            int dano_arma = jogador.getInv_jogador().getSlot_arma().get(0).getAtaque();
            // caso esteja armado
            // TIPO
            // FOR
            if(jogador.getInv_jogador().getSlot_arma().get(0).getTipo().equals("FOR"))
            {
                ataque = dano_arma + ((dano_arma/2)*(total_forca_jogador/2));
                return ataque;
            }
            // TIPO
            // PER
            else
            {
                int roll = rand.nextInt(2);
                if(roll == 0) {
                    //Roll ruim
                    System.out.println();
                    System.out.printf("%s poderia ter mirado melhor...",jogador.getNome());
                    System.out.println();
                    Pausa();
                    ataque = dano_arma + total_percepcao_jogador/2;
                    return ataque;
                }
                else
                {
                    // Roll bom
                    System.out.println();
                    System.out.printf("%s mirou bem!",jogador.getNome());
                    System.out.println();
                    Pausa();
                    ataque = dano_arma * total_percepcao_jogador/2;
                    // Caso o jogador tenha per = 1, ele zera o dano, caso acontece de ser 0 ou menor ele da o dano
                    // base da arma
                    if(ataque <= 0) { ataque = dano_arma; }
                    return ataque;
                }
            }
        }
    }

    int ATK_JOG_CRITICO_EVASAO(Jogador jogador, MonstroNormal monstro, int dano_bruto_jogador)
    {
        int roll;
        int roll_2;
        int agilidade_total_jogador = jogador.AgilidadeTotalJogador();
        int sorte_total_jogador = jogador.SorteTotalJogador();
        // EVASÃO DO MONSTRO
        if(agilidade_total_jogador <= monstro.getAgilidade())
        {
            roll = rand.nextInt(2);
            if(roll == 0)
            {
                System.out.println();
                System.out.printf("%s acertou %s!",jogador.getNome(),monstro.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_jogador;
            }
            else
            {
                if(sorte_total_jogador >= monstro.getSorte())
                {
                System.out.println();
                System.out.printf("%s mal conseguiu acertar %s...",jogador.getNome(),monstro.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_jogador / 2;
                }
                else
                {
                    roll_2 = rand.nextInt(2);
                    if(roll_2 == 0)
                    {
                        System.out.println();
                        System.out.printf("%s mal conseguiu acertar %s...",jogador.getNome(),monstro.getNome());
                        System.out.println();
                        Pausa();
                        return dano_bruto_jogador / 2;
                    }
                    else
                    {
                        System.out.println();
                        System.out.printf("%s esquivou o ataque de %s!",monstro.getNome(),jogador.getNome());
                        System.out.println();
                        Pausa();
                        return 0;
                    }
                }
            }
        }
        // CRITICO
        else
        {
            if (sorte_total_jogador <= monstro.getSorte())
            {
                System.out.println();
                System.out.printf("%s acertou %s!",jogador.getNome(),monstro.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_jogador;
            }
            else
            {
                roll = rand.nextInt(2);
                if(roll == 0)
                {
                    System.out.println();
                    System.out.printf("%s acertou %s!",jogador.getNome(),monstro.getNome());
                    System.out.println();
                    Pausa();
                    return dano_bruto_jogador;
                }
                else
                {
                    System.out.println();
                    System.out.printf("%s acertou %s fatalmente! ",jogador.getNome(),monstro.getNome());
                    System.out.println();
                    Pausa();
                    return dano_bruto_jogador * 2;
                }
            }
        }
    }

    int ATK_JOG_MAGIA_BRUTO(Jogador jogador, Magia magia_de_ataque)
    {
        int ataque;
        int total_int_jog = jogador.IntTotalJogador();
        int dano_base = magia_de_ataque.getAtaque();
        ataque = dano_base + (dano_base*((total_int_jog/3)+1));
        return ataque;
    }

    int ATK_JOG_MAGIA_CRITICO_EVASAO(Jogador jogador, MonstroNormal monstro, int dano_bruto_magia_jogador)
    {
        int roll;
        int agilidade_total_jogador = jogador.AgilidadeTotalJogador();
        int sorte_total_jogador = jogador.SorteTotalJogador();
        // EVASÃO DO MONSTRO
        if(agilidade_total_jogador <= monstro.getAgilidade())
        {
            roll = rand.nextInt(2);
            if(roll == 0)
            {
                System.out.println();
                System.out.printf("%s acertou %s!",jogador.getNome(),monstro.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_magia_jogador;
            }
            else
            {
                System.out.println();
                System.out.printf("%s mal conseguiu acertar %s...",jogador.getNome(),monstro.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_magia_jogador / 2;
            }
        }
        // CRITICO
        else
        {
            if (sorte_total_jogador <= monstro.getSorte())
            {
                System.out.println();
                System.out.printf("%s acertou %s!",jogador.getNome(),monstro.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_magia_jogador;
            }
            else
            {
                roll = rand.nextInt(2);
                if(roll == 0)
                {
                    System.out.println();
                    System.out.printf("%s acertou %s!",jogador.getNome(),monstro.getNome());
                    System.out.println();
                    Pausa();
                    return dano_bruto_magia_jogador;
                }
                else
                {
                    System.out.println();
                    System.out.printf("%s acertou %s fatalmente! ",jogador.getNome(),monstro.getNome());
                    System.out.println();
                    Pausa();
                    return dano_bruto_magia_jogador * 2;
                }
            }
        }
    }

    int ATK_MONSTRO_BRUTO(Jogador jogador, MonstroNormal monstro)
    {
        int atk_bruto = monstro.getAtk_dano();
        String tipo_monstro = monstro.getTipo();
        System.out.println();
        System.out.printf("%s usa \"%s\".",monstro.getNome(),monstro.getAtk_nome());
        System.out.println();
        Pausa();
        if(tipo_monstro.equals("FOR"))
        {
            int forca_total_jogador = jogador.ForcaTotalJogador();
            if(monstro.getForca() <= forca_total_jogador)
            {
                return atk_bruto;
            }
            else
            {
                System.out.println();
                System.out.printf("%s tem muita Força!",monstro.getNome());
                System.out.println();
                Pausa();
                atk_bruto = atk_bruto + (atk_bruto/2 + monstro.getForca()/2);
                return atk_bruto;
            }
        }
        else if (tipo_monstro.equals("INT"))
        {
            int inteligencia_total_jogador = jogador.IntTotalJogador();
            if(monstro.getInteligencia() <= inteligencia_total_jogador)
            {
                return atk_bruto;
            }
            else
            {
                System.out.println();
                System.out.printf("%s tem muita Inteligência!",monstro.getNome());
                System.out.println();
                Pausa();
                atk_bruto = atk_bruto + (atk_bruto/2 + monstro.getInteligencia()/2);
                return atk_bruto;
            }
        }
        else
        {
            int percepcao_total_jogador = jogador.PercepcaoTotalJogador();
            if(monstro.getPercepcao() <= percepcao_total_jogador)
            {
                return atk_bruto;
            }
            else
            {
                System.out.println();
                System.out.printf("%s tem muita Percepção!",monstro.getNome());
                System.out.println();
                Pausa();
                atk_bruto = atk_bruto + (atk_bruto/2 + monstro.getPercepcao()/2);
                return atk_bruto;
            }
        }
    }

    int ATK_MONSTRO_CRITICO_EVASAO(Jogador jogador, MonstroNormal monstro, int dano_bruto_monstro)
    {
        int roll;
        int roll_2;
        int agilidade_total_jogador = jogador.AgilidadeTotalJogador();
        int sorte_total_jogador = jogador.SorteTotalJogador();
        // EVASÃO DO JOGADOR
        if(monstro.getAgilidade() <= agilidade_total_jogador)
        {
            roll = rand.nextInt(2);
            if(roll == 0)
            {
                System.out.println();
                System.out.printf("%s acertou %s!",monstro.getNome(),jogador.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_monstro;
            }
            else
            {
                if(monstro.getSorte() >= sorte_total_jogador)
                {
                    System.out.println();
                    System.out.printf("%s mal conseguiu acertar %s...",monstro.getNome(),jogador.getNome());
                    System.out.println();
                    Pausa();
                    return dano_bruto_monstro / 2;
                }
                else
                {
                    // Roll pra esquivar completamente, só em casa do monstro ter agilidade inferior/igual a o jogador
                    // e se o jogador tiver sorte superior
                    roll_2 = rand.nextInt(2);
                    if(roll_2 == 0)
                    {
                        System.out.println();
                        System.out.printf("%s mal conseguiu acertar %s...",monstro.getNome(),jogador.getNome());
                        System.out.println();
                        Pausa();
                        return dano_bruto_monstro / 2;
                    }
                    else {
                        System.out.println();
                        System.out.printf("%s esquivou o ataque de %s!", jogador.getNome(), monstro.getNome());
                        System.out.println();
                        Pausa();
                        return 0;
                    }
                }
            }
        }
        // CRITICO
        else
        {
            if (monstro.getSorte() <= sorte_total_jogador)
            {
                System.out.println();
                System.out.printf("%s acertou %s!",monstro.getNome(),jogador.getNome());
                System.out.println();
                Pausa();
                return dano_bruto_monstro;
            }
            else
            {
                roll = rand.nextInt(2);
                if(roll == 0)
                {
                    System.out.println();
                    System.out.printf("%s acertou %s!",monstro.getNome(),jogador.getNome());
                    System.out.println();
                    Pausa();
                    return dano_bruto_monstro;
                }
                else
                {
                    System.out.println();
                    System.out.printf("%s acertou %s fatalmente! ",monstro.getNome(),jogador.getNome());
                    System.out.println();
                    Pausa();
                    return dano_bruto_monstro + (dano_bruto_monstro + dano_bruto_monstro/2);
                }
            }
        }
    }

    // #########################################################
    // COMBATE | INTERAÇÕES ETC | SPAWN | NUMERO DE INIMIGOS EM UM NIVEL

    int NumeroMonstros(int variacao, int minimo_inimigos, int escalador)
    {
        // Gera o número de inimigos de um level com certa variação
        //

        int var_monstros = rand.nextInt(variacao + 1 + escalador/2);
        int roll = rand.nextInt(3);
        if(roll == 0) // q sai menos inimigos
        {
            return (minimo_inimigos + escalador) - var_monstros;
        }
        else if(roll == 1) // roll q adc inimigos
        {
            return  (minimo_inimigos + escalador) + var_monstros;
        }
        else // roll numero normal
        {
            return (minimo_inimigos + escalador);
        }

    }

    boolean PrimeiroAtaque(Jogador jogador, MonstroNormal monstro)
    {
        boolean jogador_comeca = false;
        int agilidade_total_jogador = jogador.AgilidadeTotalJogador();
        int agilidade_monstro = monstro.getAgilidade();
        if(agilidade_total_jogador == agilidade_monstro) // Se agilidade do jogador e do monstro forem iguais = RNG
        {
            int rng_turno = rand.nextInt(2);
            switch (rng_turno)
            {
                case 0:
                    System.out.println();
                    System.out.printf("%s tem o primeiro turno!",monstro.getNome());
                    System.out.println();
                    Pausa();
                    jogador_comeca = false;
                    break;
                case 1:
                    System.out.println();
                    System.out.printf("%s tem o primeiro turno!",jogador.getNome());
                    System.out.println();
                    Pausa();
                    jogador_comeca = true;
                    break;
            }
        }
        else if(agilidade_total_jogador > agilidade_monstro) // Se jogador for mais agil ele começa
        {
            System.out.println();
            System.out.printf("%s tem o primeiro turno!",jogador.getNome());
            System.out.println();
            Pausa();
            jogador_comeca = true;
        }
        else{ // Se monstro for mais agil monstro começa op
            System.out.println();
            System.out.printf("%s tem o primeiro turno!",monstro.getNome());
            System.out.println();
            Pausa();
            jogador_comeca = false;
        }
        return jogador_comeca;
    }

    void UsarItem(ItemConsumivel consumivel, Jogador jogador)
    {
        // Restaura vit e mana
        RestauraVidaJogador(consumivel.getVitalidade(), jogador);
        RestauraManaJogador(consumivel.getMana(),jogador);
        // Aplica os buffs temporarios no jogador, no combate, depois de 1 turno eles devem perecer.
        jogador.setBuff_for(jogador.getBuff_for() + consumivel.getForca());
        jogador.setBuff_agi(jogador.getBuff_agi() + consumivel.getAgilidade());
        jogador.setBuff_sor(jogador.getBuff_sor() + consumivel.getSorte());
        jogador.setBuff_int(jogador.getBuff_int() + consumivel.getInteligencia());
        jogador.setBuff_per(jogador.getBuff_per() + consumivel.getPercepcao());
    }

    int MagiaEscolhida(ArrayList<Magia> magias, String id_str)
    {
        try
        {
            int id_magia = Integer.parseInt(id_str);
            if(id_magia <= (magias.size() - 1)) // Tamanho da lista - 1 para transformar em indice
            {
                return id_magia;
            }
            else
            {
                System.out.println("Seleção de magia inválida.");
                return -1;
            }
        }
        catch (Exception e)
        {
            System.out.println("Opção inválida");
            return -1;
        }
    }

    int MonstroEscolhido(ArrayList<MonstroNormal> monstros_nivel, String id_str)
    {
        try
        {
            int id_monstro = Integer.parseInt(id_str);
            if(id_monstro <= (monstros_nivel.size() - 1)) // Tamanho da lista - 1 para transformar em indice
            {
                return id_monstro;
            }
            else
            {
                System.out.println("Seleção de monstro inválida.");
                Pausa();
                return -1;
            }
        }
        catch (Exception e)
        {
            System.out.println("Opção inválida");
            Pausa();
            return -1;
        }
    }

    int ConsumivelEscolhido(ArrayList<ItemConsumivel> consumivels, String id_str)
    {
        try
        {
            int id_item = Integer.parseInt(id_str);
            if(id_item <= (consumivels.size() - 1)) // Tamanho da lista - 1 para transformar em indice
            {
                return id_item;
            }
            else
            {
                System.out.println("Seleção de item inválida.");
                return -1;
            }
        }
        catch (Exception e)
        {
            System.out.println("Opção inválida");
            return -1;
        }
    }

    int ArmaEscolhido(ArrayList<ItemArma> armas, String id_str)
    {
        try
        {
            int id_item = Integer.parseInt(id_str);
            if(id_item <= (armas.size() - 1)) // Tamanho da lista - 1 para transformar em indice
            {
                return id_item;
            }
            else
            {
                System.out.println("Seleção de item inválida.");
                Pausa();
                return -1;
            }
        }
        catch (Exception e)
        {
            System.out.println("Opção inválida");
            Pausa();
            return -1;
        }
    }

    int ArmaduraEscolhido(ArrayList<ItemArmadura> armaduras, String id_str)
    {
        try
        {
            int id_item = Integer.parseInt(id_str);
            if(id_item <= (armaduras.size() - 1)) // Tamanho da lista - 1 para transformar em indice
            {
                return id_item;
            }
            else
            {
                System.out.println("Seleção de item inválida.");
                Pausa();
                return -1;
            }
        }
        catch (Exception e)
        {
            System.out.println("Opção inválida");
            Pausa();
            return -1;
        }
    }

    ArrayList<MonstroNormal> SpawnMonstrosNormais(int numero_inimigos, ArrayList<MonstroNormal> lista_monstros,
                                                  int escalador_universal)
    {
        ArrayList<MonstroNormal> monstros_nivel = new ArrayList<>();
        for(int x = 0; x < numero_inimigos; x++)
        {
            monstros_nivel.add(lista_monstros.get(rand.nextInt(lista_monstros.size())));
        }
        for(MonstroNormal x: monstros_nivel)
        {
            x.setForca(x.getForca() + escalador_universal);
            x.setAgilidade(x.getAgilidade() + escalador_universal);
            x.setSorte(x.getSorte() + escalador_universal);
            x.setInteligencia(x.getInteligencia() + escalador_universal);
            x.setPercepcao(x.getPercepcao() + escalador_universal);
            x.setVitalidade(x.getVitalidade() + (4*escalador_universal));
            x.setAtk_dano(x.getAtk_dano() + ((x.getAtk_dano()/2)*escalador_universal));
        }
        return monstros_nivel;
    }
    // ##############################
    // EVENTOS
    void RestauraVidaJogador(int hp_restaura,Jogador jogador)
    {
        if (jogador.getHp_atual() + hp_restaura > jogador.getCla_jogador().getHp_jogador())
        {
            jogador.setHp_atual(jogador.getCla_jogador().getHp_jogador());
        }
        else
        {
            jogador.setHp_atual(jogador.getHp_atual() + hp_restaura);
        }
    }
    void RestauraManaJogador(int mp_restaura, Jogador jogador)
    {
        if(jogador.getMp_atual() + mp_restaura > jogador.getCla_jogador().getMp_jogador())
        {
            jogador.setMp_atual(jogador.getCla_jogador().getMp_jogador());
        }
        else
        {
            jogador.setMp_atual(jogador.getMp_atual() + mp_restaura);
        }
    }
    void EventoAumentoAtributos(Jogador jogador,int forca, int agilidade, int sorte, int inteligencia, int percepcao,
                                int moedas)
    {
        // Aumenta as moedas
        jogador.setMoedas(jogador.getMoedas() + moedas);

        // Aumento o F.A.S.I.P
        jogador.getCla_jogador().setForca(jogador.getCla_jogador().getForca() + forca);
        jogador.getCla_jogador().setAgilidade(jogador.getCla_jogador().getAgilidade() + agilidade);
        jogador.getCla_jogador().setSorte(jogador.getCla_jogador().getSorte() + sorte);
        jogador.getCla_jogador().setInteligencia(jogador.getCla_jogador().getInteligencia() + inteligencia);
        jogador.getCla_jogador().setPercepcao(jogador.getCla_jogador().getPercepcao() + percepcao);
    }
    void EventoDiminuirAtributos(Jogador jogador,int forca, int agilidade, int sorte, int inteligencia, int percepcao,
                                 int moedas)
    {
        // Reduz o dinheiro
        jogador.setMoedas(jogador.getMoedas() - moedas);
        if(jogador.getMoedas() < 0) {jogador.setMoedas(0);}

        // Reduz o F.A.S.I.P
        jogador.getCla_jogador().setForca(jogador.getCla_jogador().getForca() - forca);
        jogador.getCla_jogador().setAgilidade(jogador.getCla_jogador().getAgilidade() - agilidade);
        jogador.getCla_jogador().setSorte(jogador.getCla_jogador().getSorte() - sorte);
        jogador.getCla_jogador().setInteligencia(jogador.getCla_jogador().getInteligencia() - inteligencia);
        jogador.getCla_jogador().setPercepcao(jogador.getCla_jogador().getPercepcao() - percepcao);
        // Depois verifica se o resultado é igual o menor que 0, caso for, deixa 1 pois os atributos
        // não devem serem nulos ou negativos.
        if (jogador.getCla_jogador().getForca() <= 0)   {jogador.getCla_jogador().setForca(1);}
        if (jogador.getCla_jogador().getAgilidade() <= 0)   {jogador.getCla_jogador().setAgilidade(1);}
        if (jogador.getCla_jogador().getSorte() <= 0)   {jogador.getCla_jogador().setSorte(1);}
        if (jogador.getCla_jogador().getInteligencia() <= 0)    {jogador.getCla_jogador().setInteligencia(1);}
        if (jogador.getCla_jogador().getPercepcao() <= 0)   {jogador.getCla_jogador().setPercepcao(1);}
    }

    void PerderMana(Jogador jogador, int mana)
    {
        if(jogador.getMp_atual() - mana < 0){
            jogador.setMp_atual(0);
        }
        else
        {
            jogador.setMp_atual(jogador.getMp_atual() - mana);
        }
    }

    // #######################
    // Criação de Personagem
    Jogador CriarPersonagem(ArrayList<Classe> classes)
    {
        Jogador jogador;

        while (true)
        {
            String nome = RetornaStr("Qual o seu nome, F.O.D.A?");
            boolean escolha_classe = true;
            while (escolha_classe) {
                int cont = 0;
                for (Classe x : classes) {
                    System.out.println();
                    System.out.printf("%s - '%s'", cont, x.getCla_nome());
                    cont += 1;
                }
                System.out.println();
                String classe = RetornaStr("Qual sua ocupação?");
                try
                {
                    int id = Integer.parseInt(classe);
                    try
                    {
                        System.out.println();
                        boolean confirmar = true;
                        while (confirmar)
                        {
                            System.out.printf("Nome: %s",nome);
                            System.out.println();
                            System.out.printf("Ofício: %s", classes.get(id).getCla_nome());
                            System.out.println();
                            System.out.printf("Descrição: %s", classes.get(id).getCla_lore());
                            System.out.println();
                            System.out.printf("HP: %s | MP: %s",classes.get(id).getHp_jogador(),
                                    classes.get(id).getMp_jogador());
                            System.out.println();
                            classes.get(id).DescreverAtributosModificador();
                            System.out.println();
                            String selecionar = RetornaStr("Confirmar Personagem? (S/N)");
                            switch (selecionar)
                            {
                                case "s":
                                case "S":
                                    jogador = new Jogador(nome, classes.get(id));
                                    return jogador;
                                case "n":
                                case "N":
                                    confirmar = false;
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        }
                        escolha_classe = false;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Não há uma classe com esse número.");
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Por favor selecione uma ocupação.");
                }
            }
        }
    }
    // Da u, item aleatório pro jogar no inicio
    void ItensInicio(Jogador jogador, ListaItens itens, ListaMagia magias)
    {
        ArrayList<ItemArma> armas_de_inicio = new ArrayList<>();
        ArrayList<ItemArmadura> armaduras_de_inicio = new ArrayList<>();
        ArrayList<ItemConsumivel> itens_de_inicio = new ArrayList<>();
        ArrayList<Magia> magias_inicio = new ArrayList<>();
        for (ItemArma x: itens.getLista_armas())
        {
            if (x.getPodeIniciar())
            {
                armas_de_inicio.add(x);
            }
        }
        for (ItemArmadura x: itens.getLista_armaduras())
        {
            if (x.getPodeIniciar())
            {
                armaduras_de_inicio.add(x);
            }
        }
        for(ItemConsumivel x:itens.getLista_consumiveis())
        {
            if(x.getPodeIniciar())
            {
                itens_de_inicio.add(x);
            }
        }
        for(Magia x: magias.getMagias())
        {
            if(x.isMagiadeInicio())
            {
                magias_inicio.add(x);
            }
        }
        if(magias_inicio.size() > 0 && armaduras_de_inicio.size() > 0 && itens_de_inicio.size() > 0 &&
                armas_de_inicio.size() > 0) {
            System.out.println("Deseja receber um item de inicio de jogo?");
            boolean escolha = RetornaEscolha();
            if (escolha) {
                int roll = rand.nextInt(4);
                if (roll == 0) // arma
                {
                    ItemArma arma = armas_de_inicio.get(rand.nextInt(armas_de_inicio.size()));
                    jogador.getInv_jogador().getSlot_arma().add(arma);
                    System.out.printf("%s recebeu %s.", jogador.getNome(), arma.getNome());
                    System.out.println();
                } else if (roll == 1) {
                    ItemArmadura armadura = armaduras_de_inicio.get(rand.nextInt(armaduras_de_inicio.size()));
                    jogador.getInv_jogador().getSlot_armadura().add(armadura);
                    System.out.printf("%s recebeu %s.", jogador.getNome(), armadura.getNome());
                    System.out.println();
                } else if (roll == 2) {
                    ItemConsumivel item = itens_de_inicio.get(rand.nextInt(itens_de_inicio.size()));
                    jogador.getInv_jogador().getConsumivels().add(item);
                    System.out.printf("%s recebeu %s.", jogador.getNome(), item.getNome());
                    System.out.println();
                } else {
                    Magia magia = magias_inicio.get(rand.nextInt(magias_inicio.size()));
                    jogador.getMagias_aprendidas().add(magia);
                    System.out.printf("%s aprendeu %s.", jogador.getNome(), magia.getNome());
                    System.out.println();
                }
            }
        }
        else{
            System.out.println("Itens de início desabilitado.");
            System.out.println();
        }
    }
    // #####################################
    // INTERFACE | MENSAGENS & MENUS
    void InicioAventura(Jogador jogador)
    {
        System.out.println();
        System.out.printf("\"Tenha uma boa jornada %s, e não esqueça de ser um F.O.D.A...\"",jogador.getNome());
        System.out.println();
        Pausa();
    }
    void NivelExibirMonstros(ArrayList<MonstroNormal> monstros)
    {
        System.out.printf("Monstros vivos: %s", monstros.size());
        System.out.println();
        int cont = 0;
        for(MonstroNormal x:monstros)
        {
            System.out.printf("%s - %s - HP:%s ", cont, x.getNome(), x.getVitalidade());
            System.out.println();
            cont += 1;
        }
    }
    void EstadoCombateMonstroNormal(Jogador jogador,MonstroNormal monstro, int hp_monstro)
    {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("%s | HP: %s/%s | MP: %s/%s",jogador.getNome(),jogador.getHp_atual(),
                jogador.getCla_jogador().getHp_jogador(),
                jogador.getMp_atual(),jogador.getCla_jogador().getMp_jogador());
        System.out.println();
        System.out.printf("FOR: %s | AGI: %s | SOR: %s | INT: %s | PER: %s",
                jogador.ForcaTotalJogador(),jogador.AgilidadeTotalJogador(),jogador.SorteTotalJogador(),
                jogador.IntTotalJogador(),jogador.PercepcaoTotalJogador());
        System.out.println();
        System.out.printf("%s | HP: %s/%s | TIPO: %s",monstro.getNome(),hp_monstro,monstro.getVitalidade(),
                monstro.getTipo());
        monstro.AtributosMonstro();
        String descricao_monstro = printBonito(monstro.getDescricao());
        System.out.print("\"");
        System.out.print(descricao_monstro);
        System.out.print("\"");
        System.out.println();
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    void EstadoJogador(Jogador jogador)
    {
        System.out.println();
        System.out.println("################################################################");
        System.out.printf("| Nome: %s",jogador.getNome());
        System.out.println();
        System.out.printf("| πcoins: %s", jogador.getMoedas());
        System.out.println();
        System.out.printf("| Classe: %s | Level: %s", jogador.getCla_jogador().getCla_nome(),jogador.getLevel());
        System.out.println();
        jogador.getInv_jogador().DadosArmaEquipada();
        System.out.println();
        jogador.getInv_jogador().DadosArmaduraEquipada();
        System.out.println();
        jogador.getInv_jogador().TotalConsumiveis();
        System.out.println();
        System.out.printf("| HP: %s/%s  | MP: %s/%s",
                jogador.getHp_atual(),jogador.getCla_jogador().getHp_jogador(),jogador.getMp_atual(),
                jogador.getCla_jogador().getMp_jogador());
        ExibirAtributos(jogador);
        System.out.println();
        System.out.println("################################################################");
    }
    void ExibirAtributos(Jogador jogador)
    {
        System.out.println();
        System.out.printf("| FOR: %s",jogador.getCla_jogador().getForca());
        int for_buff = jogador.ForcaTotalJogador() - jogador.getCla_jogador().getForca(); //
        if(for_buff  != 0)
        {
            if(for_buff < 0)
            {
                System.out.printf("(%s) ",for_buff);
            }
            else
            {
                System.out.printf("(+%s) ",for_buff);
            }
        }
        System.out.printf("| AGI: %s",jogador.getCla_jogador().getAgilidade());
        int agi_buff = jogador.AgilidadeTotalJogador() - jogador.getCla_jogador().getAgilidade(); //
        if(agi_buff  != 0)
        {
            if(agi_buff < 0)
            {
                System.out.printf("(%s) ",agi_buff);
            }
            else
            {
                System.out.printf("(+%s) ",agi_buff);
            }
        }
        System.out.printf("| SOR: %s",jogador.getCla_jogador().getSorte());
        int sor_buff = jogador.SorteTotalJogador() - jogador.getCla_jogador().getSorte(); //
        if(sor_buff  != 0)
        {
            if(sor_buff < 0)
            {
                System.out.printf("(%s) ",sor_buff);
            }
            else
            {
                System.out.printf("(+%s) ",sor_buff);
            }
        }
        System.out.printf("| INT: %s",jogador.getCla_jogador().getInteligencia());
        int int_buff = jogador.IntTotalJogador() - jogador.getCla_jogador().getInteligencia(); //
        if(int_buff  != 0)
        {
            if(int_buff < 0)
            {
                System.out.printf("(%s) ",int_buff);
            }
            else
            {
                System.out.printf("(+%s) ",int_buff);
            }
        }
        System.out.printf("| PER: %s",jogador.getCla_jogador().getPercepcao());
        int per_buff = jogador.PercepcaoTotalJogador() - jogador.getCla_jogador().getPercepcao();  //
        if(per_buff  != 0)
        {
            if(per_buff < 0)
            {
                System.out.printf("(%s) ",per_buff);
            }
            else
            {
                System.out.printf("(+%s) ",per_buff);
            }
        }

    }
    // ################################################
    // RETORNA DADOS
    // Processamento de Dados
    String ColocaNomeJogador(String nome_jogador, String texto)
    {
     texto = texto.replace("NOME_JOGADOR",nome_jogador);
     return texto;
    }
    String RetornaStr(String prompt)
    {
        String texto;
        System.out.println(prompt);
        System.out.print(">> ");
        texto = entrada.nextLine();
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
    String printBonito(String texto)
    {
        texto = texto.replaceAll("(.{64})", "$1\n");
        return texto;
    }
    void Pausa()
    {
        System.out.println("(TECLE ENTER)");
        System.out.print(">> ");
        entrada.nextLine();
    }
}
