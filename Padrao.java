package Foda;

import java.util.ArrayList;
import java.util.Collections;


public class Padrao {
    // Contem todos os itens,classes, monstros e eventos predefinidos.

    // Inicializa Monstros Normais
    ArrayList<MonstroNormal> MonstrosNormaisPadrao(){
        ArrayList<MonstroNormal> monstrospadroes = new ArrayList<>();

        MonstroNormal slime = new MonstroNormal("Slime",
                "Um slime resultado de uma reação com boro e radiação.","PER",12,"Cuspe",
                4,2,1,2,1,2);
        monstrospadroes.add(slime);

        MonstroNormal esqueleto = new MonstroNormal("Esqueleto","Um esqueleto reanimado.","FOR",10,"Soco",
                4,3,2,1,1,1);
        monstrospadroes.add(esqueleto);

        MonstroNormal pellizzauro_minion = new MonstroNormal("Mini Pellizzauro Rex","Um pequeno dinossauro" +
                " alienega minion do temivel Pellizzauro Rex...",
                "INT",14, "Bola côsmica de energia",3,1,2,2,3,2);
        monstrospadroes.add(pellizzauro_minion);

        MonstroNormal olho_voador = new MonstroNormal("Olho Voador","Um nojento olho voador...","PER",14,"Gosma",
                2,3,2,1,1,3);
        monstrospadroes.add(olho_voador);

        MonstroNormal tio_bebado = new MonstroNormal("Tiozinho Bêbado","Um tiozinho do bar bêbado.","FOR",12,"Punho Bêbado",
                5,3,1,2,1,1);
        monstrospadroes.add(tio_bebado);
        return monstrospadroes;
    }
    // Inicializa Classes Padrão
    ArrayList<Classe> ClassesPadrao(){
        ArrayList<Classe> classespadroes = new ArrayList<>();
        Classe entregadorciborgue = new Classe("CiberEntregador",
                "Um simples entregador de lanches, já é acostumado com infortuna e é " +
                        "capacitado para diversos perigos.",
                60,10, 4,4,1,2,3);
        classespadroes.add(entregadorciborgue);

        Classe frank = new Classe("Discipulo Frankiano",
                "Um seguidor fiel da escola Frankista que se dedica as várias artes de invocação matemática.",
                40,30, 1,1,3,4,2);
        classespadroes.add(frank);
        Classe detetive = new Classe("Detetive prodígio",
                "Um dos aprendizes do conhecido detetive, agora aposentado, Giovanni Marchetti.",
                50,20,2,2,2,3,4);
        classespadroes.add(detetive);

        Classe plebeu = new Classe("Plebeu Estranho",
                "Um plebeu perdido de um reino que está prestes a acabar por uma profécia.", 60,
                25,2,2,2,2,2);
        classespadroes.add(plebeu);
        return classespadroes;
    }
    // Inicializa Chefões Padrão
    ArrayList<MonstroChefe> ChefoesPadrao(){
        ArrayList<MonstroChefe> chefoespadrao = new ArrayList<>();
        MonstroChefe darkfrank = new MonstroChefe("Dark Frank","Uma temivel versão corrompida " +
                "do maior matemático de todos os tempos.","INT", 25,
                "Calculo proibido",5, 2,30,50,4,0);
        chefoespadrao.add(darkfrank);

        MonstroChefe hulk_agiota = new MonstroChefe("Hulk Agiota","Hulk agiota de um multiverso.","FOR",
                30,"Cobrar", 4,3,2,2,1,1);
        chefoespadrao.add(hulk_agiota);
        MonstroChefe arco_mago = new MonstroChefe("Arco-Mago",
                "Um poderoso mago esqueleto que carrega um grimório","INT",
                20,"Grimório",10,1,2,1,4,1);
        chefoespadrao.add(arco_mago);

        return chefoespadrao;
    }
    // Inicializa Periciveis Padrão
    ArrayList<ItemConsumivel> ConsumiveisPadrao(){
        ArrayList<ItemConsumivel> consumiveispadrao = new ArrayList<>();

        ItemConsumivel redbull = new ItemConsumivel("Red Bull","Te deixa elétrico.",50,
                0,0, 1,2, 0,1,2, true,"TODOS");
        consumiveispadrao.add(redbull);

        ItemConsumivel maco_cigarro = new ItemConsumivel("Maço de cigarro",
                "Essa porcaria te mata, mas da pra vender por um bom preço.",
                80,-10,-5,0,0,2,0,0,
                true,"TODOS");
        consumiveispadrao.add(maco_cigarro);

        ItemConsumivel miojo = new ItemConsumivel("Miojo da Turma da mônica",
                "Um saboroso miojo.",50,15,5,0,0,
                0,0,0,true,"TODOS");
        consumiveispadrao.add(miojo);

        ItemConsumivel orragic_ocam = new ItemConsumivel("orragic ed oçaM",
                ".oçerp mob mu rop rednev arp ad oãn euq anep ,mob otium é orragic ed oçam essE",
                20,10,5,0,0,-2,
                0,0,false,"MONSTROS");
        consumiveispadrao.add(orragic_ocam);
        ItemConsumivel agua = new ItemConsumivel("Água",
                "100% das pessoas que bebem água morrem.",150,
                20,10,0,1,0,0,0,true,"TODOS");
        consumiveispadrao.add(agua);
        ItemConsumivel corote_chocolate = new ItemConsumivel("Corote de chocolate",
                "Tem sabor de barra e consome 1/2 do figado",
                5,-5,20,0,0,0,-3,
                -4,true,"MONSTROS");
        consumiveispadrao.add(corote_chocolate);

        return consumiveispadrao;
    }

    ArrayList<ItemArma> ArmasPadrao(){
        ArrayList<ItemArma> lista_armas = new ArrayList<>();

        ItemArma espadaferro = new ItemArma("Espada Ferro","Espada da wish.com","FOR",2,
                10, 0,0, 0,0,0, true,"TODOS");
        lista_armas.add(espadaferro);

        ItemArma sabre = new ItemArma("Sabre de Luz(Sem pilha)",
                "Uma sabre de luz de plástico rígido, está sem pilha...",
                "FOR", 4,30,0,0,0,0,0,
                false,"TODOS");
        lista_armas.add(sabre);

        ItemArma vassoura = new ItemArma("Vassoura",
                "É uma vassoura de palha, só de olhar a rinite ataca.",
                "FOR",6,15,
                0,0,0,0,-1,false,"MONSTROS");
        lista_armas.add(vassoura);

        ItemArma raquete = new ItemArma("Raquete elétrica","Uma raquete para mosquitos ou o irmão caçula.",
                "FOR",10,150,0,0,0,0,0,false,
                "CHEFES");

        lista_armas.add(raquete);

        ItemArma yoyo = new ItemArma("Yoyo","Um pesado e descolado yoyo amarelo, lembra dos tempos bons!",
                "FOR",12,800,0,0,2,0,0,
                false,"CHEFES");
        lista_armas.add(yoyo);

        ItemArma arremasador_cuspe = new ItemArma("Arremesador de cuspe",
                "\"O mais novo esforço da engenharia da FrankIndustries .Inc, o lançador quântico" +
                        " promete arremesar a sáliva com extrema precisão e violência…\"" +
                        " Fabricado em Taiwan *aham* China.","PER",7,40,
                0,0,0,0,0,true,"TODOS");
        lista_armas.add(arremasador_cuspe);

        ItemArma lanca_ovo = new ItemArma("Lança-ovo","Um aparato por engenheiros de gambiarras",
                "PER",9,80,0,0,0,0,0,
                true,"MONSTROS");
        lista_armas.add(lanca_ovo);

        ItemArma pistola_agua = new ItemArma("Pistola de água(água)",
                "Uma pistola de água carregada com água, a pressão de seu jato realmente causa um dano. ",
                "PER",9,150,0,0,0,0,0,
                false,"TODOS");
        lista_armas.add(pistola_agua);

        ItemArma boomerang = new ItemArma("Boomerang","Um boomerang, *woosh*","PER",12,
                250, 0,0,0,0,0,false,"MONSTROS");
        lista_armas.add(boomerang);

        ItemArma pistola_xixi = new ItemArma("Pistola de água(xixi)",
                "Uma pistola de água carregada com xixi, a acidez do xixi" +
                        " proporcionada por uma dieta exclusiva de energéticos e miojo pode causar" +
                        " queimaduras até de quarto grau!","PER",22,1000,
                0,0,0,0,0,false,"CHEFES");
        lista_armas.add(pistola_xixi);

        return lista_armas;
    }

    ArrayList<ItemArmadura> ArmadurasPadrao(){
        ArrayList<ItemArmadura> lista_armaduras = new ArrayList<>();

        ItemArmadura caixadepapelao = new ItemArmadura("Caixa de papelão",
                "Esconde seu rosto feio, mas dificulta a visão.", 10,4,0, 0,
                0,0,-1,true,"TODOS");
        lista_armaduras.add(caixadepapelao);
        ItemArmadura pochete = new ItemArmadura("Pochete","Muito portátil.",
                15,2,0,0,0,0,0,false,"TODOS");
        lista_armaduras.add(pochete);

        ItemArmadura armadura_diamante = new ItemArmadura("Armadura de diamante",
                "Te deixa lerdo como uma tartaruga",
                10,10,
                -1,-4,0,0,-2,false,"MONSTROS");
        lista_armaduras.add(armadura_diamante);

        ItemArmadura camiseta_designer = new ItemArmadura("Camiseta SUPREME",
                "Pode vender por um alto valor, fora isso não aumenta atributos",
                200,0,0,0,0,0,0,false,"TODOS");
        lista_armaduras.add(camiseta_designer);

        ItemArmadura jaqueta = new ItemArmadura("Jaqueta","Uma jaqueta confortavel",
                100,3,0,0,0,0,0,false,"CHEFES");
        lista_armaduras.add(jaqueta);

        ItemArmadura jaqueta_drip = new ItemArmadura("Jaqueta com Drip",
                "Impossivel ninguém pode ter o d....",1000,0,
                0,0,2,0,0,false,"CHEFES");
        lista_armaduras.add(jaqueta_drip);
        return lista_armaduras;
    }

    ArrayList<Magia> MagiasPadrao() {
        ArrayList<Magia> magias = new ArrayList<>();

        Magia cinetica = new Magia("Energia cinêtica","Negócio de física ou sei la",200,4,6,0,
                false,"TODOS");
        magias.add(cinetica);
        Magia cura = new Magia("Cura","Todo RPG precisa de uma magia de cura ou não é um RPG...",
                250,0,5,5,true,"TODOS");
        magias.add(cura);
        Magia cringe = new Magia("\"Ui ui.. Cringe!\"",
                "Ao acusar o oponente de ser cringe, consome vida proporcional ao ataque.",
                500,5,10,5,false,"CHEFES");
        magias.add(cringe);
        Magia troll = new Magia("Trollface",
                "Invoca o clássico meme de qualidade humoristica top,regenarando vida",750,
                0,10,12,false,"CHEFES");
        magias.add(troll);
        Magia trollge = new Magia("Trollge","É͝͞L͏͘̕͢E҉̵͜͞ ̢̢͞V̢̕͞E̴̡̡͠M̧͜͟͝͝",
                1200,15,8,-4,false,"CHEFES");
        magias.add(trollge);
        return magias;
    }
    ArrayList<Evento> EventosPadrao() {
        ArrayList<Evento> eventos = new ArrayList<>();

        Evento evento1 = new Evento("Evento 1.","HORARIO NOME_JOGADOR vaga em " +
                "busca de suplementos, e se encontra em " +
                "LUGAR que aparenta estar sem sinal de vida, de repente, NOME_JOGADOR escuta suspiros de uma voz bem" +
                " próxima que parece não saber de sua presença, NOME_JOGADOR deve ir até a voz?",
                "NOME_JOGADOR decide ir até a voz e se depara com uma pessoa sentada... Ela está " +
                        "bebendo um litrão de guaraná " +
                        "Franquista™ cantando com entusiasmo a letra de NOME_MUSICA. Ela " +
                        "vê NOME_JOGADOR e diz \"Oi, me chamo " +
                        "NOME_PESSOA, lugar bacana né?\" então se levanta vai até " +
                        "NOME_PESSOA da o resto do litrão e uns πcoins e apenas diz " +
                        "\"FRASE\" e sai correndo...",
                "NOME_JOGADOR mal se vira para ir até a voz, quando de repente é atingido por " +
                        "OBJETO e cai de dor, tempo depois na sua pouca consciência um andarilho passa e pega a" +
                        " carteira de NOME_JOGADOR e olha com nojo... Procede pegar uns πcoins da carteira de " +
                        "NOME_JOGADOR e diz \"Que carteira feia a sua...\" e sai de lá.",
                "NOME_JOGADOR decide evitar um possível perigo e ignora a voz, sua busca por " +
                        "suplementos acaba em nada e NOME_JOGADOR procede com sua jornada. ",
                "NOME_JOGADOR vai até a voz.","NOME_JOGADOR ignora.",
                20,20, 15,15,0,
                0 ,0,0, 0,0,
                1,1,0,0, 0,
                0);
        eventos.add(evento1);

        Evento ednaldo = new Evento("Evento 2.",
                "NOME_JOGADOR se dá a oportunidade de descanso e pega um busão para ir" +
                " para sua próxima jornada, horas passando sentado aborrecido NOME_JOGADOR " +
                        "decide escutar uns hits " +
                "pelo FrankFy, de repente começa a tocar uma poderosa canção ‘Banido Desbanido’  por Ednaldo Pereira," +
                " NOME_JOGADOR deve continuar escutando?",
                "NOME_JOGADOR reflete sobre seu " +
                        "futuro de modo otimista e se sente mais confiante, a " +
                        "música leva a NOME_JOGADOR a um transe , ele escuta as palavras \"...desbanido. " +
                        "..desbanido. DESBANIDO!\" que ressoam em sua cabeça como um hino de vitória! " +
                        "NOME_JOGADOR se sente muito capaz!",
                "NOME_JOGADOR reflete pessimistamente " +
                        "sobre seu futuro, se sentindo cada vez mais no " +
                        "fundo do poço, NOME_JOGADOR escuta as palavras \"" +
                        "...Banido Banido BANIDO!\" como se fosse uma" +
                        " maldição cacofônica provindas de Deus em si, " +
                        "NOME_JOGADOR para de escutar com receio de " +
                        "sentir cada vez mais incapaz...",
                "NOME_JOGADOR por uma mistura de forte emoção e receio acaba parando a música e " +
                        "decide dormir o resto do trajeto.",
                "Continuar.","Parar.",0,0,
                0,0,0,0,1,1,
                0,0,0,0,1,1,
                1,1);
        eventos.add(ednaldo);
        return eventos;
    }
    void Aleatoriedade(Aleatorio aleatorio)
    {

        String[] nomes_pessoas = {"Anacleto","Alice","Bob","Camila","Carlinho","Zé","João","Choris","Frank","Dianho",
                "Gabriel Monteiro","Nando Moura"};
        Collections.addAll(aleatorio.getNome_pessoas(), nomes_pessoas);

        String[] horarios = {"de manhã","de tarde","de noite","de madrugada"};
        Collections.addAll(aleatorio.getHorarios(),horarios);

        String[] lugares = {"um hotel","um shopping","uma área militar","uma fazenda","uma escola", "um beco",
                "um clube de piscina", "um zoológico de dinossauros","um estágio de futebol","um matagal"};
        Collections.addAll(aleatorio.getLugares(),lugares);

        String[] frases = {"Deus é bom!","Você perdeu o Jogo!","Caramba, você é feio em.",
                "Não esqueça de se inscrever no canal e ativar o sininho.","Esqueci que tenho um compromisso.",
                "Vou jogar um Half-Life 3 agora.","Caramba, vai estreiar o anime do Mecha Danillo.",
                "Tem coisa que não faz sentido né?"};
        Collections.addAll(aleatorio.getFrases(),frases);

        String[] objetos_aleatorios = {"uma bala","um tijolo","um ovo de dinossauro",
                "um boomerang","uma cadeira","um vaso","um pote de flor","uma patente","uma garrafa"};
        Collections.addAll(aleatorio.getObjetos(),objetos_aleatorios);

        String[] nomes_musicas = {"God is Good","Rap do among us","Amor de chocolate",
                "Abertura do Mecha Danilo"};
        Collections.addAll(aleatorio.getNomes_musicas(),nomes_musicas);

        String[] nomes_galo = {"Zeca","Frajola","Piu piu","Bandeirantes","Patriota","Bastião","Zé","Alcatraz",
                "Camargo","Sebastião","Babalu","Ciro","Cido","Ivan","Acarajê","Clarão","Saraiva","Severino"};
        Collections.addAll(aleatorio.getNomes_galo(),nomes_galo);

        String[] apelidos_galo = {"Temivel","Sonegador de Impostos","Ditador","Bravo","Rei","Conquistador","Imperador"
        ,"Destruidor de mundos","Agiota","Fascista","Jedi","Galo Samurai","Podre","Peida fedido","Bico feroz","Demonio",
                "Monstro","Avassalador","Cantor","Galo Ninja","Boxeador","Juiz"};
        Collections.addAll(aleatorio.getApelidos_galo(),apelidos_galo);
    }
}
