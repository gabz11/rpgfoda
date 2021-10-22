package Foda;

public class ConstrutorPartida {

    // Fornece os modo do jogo e o numero de niveis
    String modo;
    int n_niveis;



    String getModo() { return modo; }

    int getN_niveis() { return n_niveis; }

    void setN_niveis(int n_niveis) { this.n_niveis = n_niveis; }

    public ConstrutorPartida(String modo, int n_niveis)
    {
        this.modo = modo;
        this.n_niveis = n_niveis;
    }
}
