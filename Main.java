package Foda;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Padrao padrao = new Padrao();

        ConstrutorPartida construtor;

        // Instancia a lista de objetos
        // Nesse caso instancia uma lista de classes
        ListaClasse classes = new ListaClasse();
        // E na inicialização, define as classes elaboradas no padrão
        classes.setClasses(padrao.ClassesPadrao());

        ListaMonstro monstros = new ListaMonstro();
        monstros.setMonstros(padrao.MonstrosNormaisPadrao());
        monstros.setChefes(padrao.ChefoesPadrao());

        ListaItens itens = new ListaItens();
        // inicia os itens, a lista de itens contem os 3 tipos possiveis de itens
        itens.setLista_consumiveis(padrao.ConsumiveisPadrao());
        itens.setLista_armas(padrao.ArmasPadrao());
        itens.setLista_armaduras(padrao.ArmadurasPadrao());

        ListaMagia magias = new ListaMagia();
        magias.setMagias(padrao.MagiasPadrao());

        ListaEventos eventos = new ListaEventos();
        eventos.setEventos(padrao.EventosPadrao());

        Aleatorio aleatoriedade = new Aleatorio();
        padrao.Aleatoriedade(aleatoriedade);

        Jogador jogador;
        System.out.print("R.P.G F.O.D.A");
        System.out.println();
        while (true) { // Sempre verdadeiro
            menu.OpcoesMenu();
            construtor = menu.Menu_P(padrao, classes, monstros, itens, magias, aleatoriedade, eventos);
            if (construtor != null && classes.ValidarClasses() && itens.ValidarItens() && eventos.ValidarEventos() &&
                    magias.ValidarMagias()) {
                Cenario jogo = new Cenario();
                jogador = jogo.CriarPersonagem(classes.getClasses());
                jogo.Partida(jogador, construtor,monstros,eventos,magias,aleatoriedade,itens);
            }
            if( !classes.ValidarClasses() || !itens.ValidarItens() || !eventos.ValidarEventos() ||
                    !magias.ValidarMagias())
            {
                // Verifica a integridade, cada lista necessita ter pelo menos 1 objeto dentro dela
                // ou o jogo não ira funcionar.
                System.out.println("Erro: O jogo não pode funcionar se não tiver com " +
                        "pelo menos 1 objeto em cada lista");
                System.out.println("Classes Carregadas:");
                System.out.printf("\nITENS: %s", itens.ValidarItens());
                System.out.printf("\nALEATORIEDADE: %s",aleatoriedade.ValidarStrings());
                System.out.printf("\nMAGIAS: %s",magias.ValidarMagias());
                System.out.printf("\nCLASSES: %s",classes.ValidarClasses());
                System.out.printf("\nMONSTROS: %s",monstros.ValidarInimigos());
                System.out.println();
            }
        }
    }
}
