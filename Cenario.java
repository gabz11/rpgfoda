package Foda;

import java.util.ArrayList;

public class Cenario {
    // Partida em si
    public String modoJogo; // NORMAL | CONQUISTA
    public int n_niveis; // Numero de niveis caso for normal
    public Jogador jogador; // Jogador
    public ListaMonstro monstros; // monstros q serão utilizados
    public ListaChefe chefes; // lista de chefes q serão utilizados
    public ListaClasse classes; //

    public Cenario(String modoJogo, int n_niveis, Jogador jogador, ListaMonstro monstros) {
        // NORMAL
        this.modoJogo = modoJogo;
        this.n_niveis = n_niveis;
        this.jogador = jogador;
        this.monstros = monstros;

    }
    public Cenario(String modoJogo, Jogador jogador, ListaMonstro monstros) {
        // CONQUISTA
        this.modoJogo = modoJogo;
        this.jogador = jogador;
        this.monstros = monstros;
    }
}
