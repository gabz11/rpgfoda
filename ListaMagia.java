package Foda;

import java.util.ArrayList;

public class ListaMagia {
    public ArrayList<Magia> magias = new ArrayList<>();

    public ArrayList<Magia> getMagias() {
        return magias;
    }

    void setMagias(ArrayList<Magia> magias) {this.magias = magias;}

    void TotalMagias() {
        if (magias.size() == 0) {
            System.out.println("Não há magias cadastradas");
        } else {
            System.out.println("Total mágias:" + magias.size());
            for (Magia x : magias) {
                System.out.println();
                System.out.printf(" | Nome: '%s' |  ATK: %s | Preço: %s",
                        x.getNome(), x.getAtaque(), x.getValor());
                x.DescreverAtributosMagia();
                System.out.println();
            }
        }
    }
    boolean ValidarMagias()
    {
        return magias.size() != 0;
    }

}
