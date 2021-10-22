package Foda;

import java.util.ArrayList;

public class ListaMonstro {
    public ArrayList<MonstroNormal> monstros = new ArrayList<>();
    public ArrayList<MonstroChefe> chefes = new ArrayList<>();
    void TotalMonstrosNormais() {
        if(monstros.size() == 0) {
            System.out.println("Não há monstros cadastrados.");
        }
        else {
            System.out.println("Total de monstros: "+monstros.size());
            for (MonstroNormal monstro : monstros) {
                System.out.println();
                System.out.printf("Nome: %s\nDescrição: %s\nTipo: %s", monstro.nome, monstro.descricao, monstro.tipo);
                monstro.AtributosMonstro();
                System.out.println();
            }
            System.out.println();
        }
    }

    public ArrayList<MonstroNormal> getMonstros() {
        return monstros;
    }

    public ArrayList<MonstroChefe> getChefes() {
        return chefes;
    }

    void TotalChefes(){
        if(chefes.size() == 0) {
            System.out.println("Não há monstros cadastrados.");
        }
        else {
            System.out.println("Total de monstros:"+monstros.size());
            for (MonstroChefe chefe : chefes) {
                System.out.printf("Nome: %s\nDescrição:%s", chefe.nome, chefe.descricao);
                System.out.println();
            }
            System.out.println();
        }
    }
    boolean ValidarInimigos()
    {
        return getMonstros().size() != 0 && getChefes().size() != 0;
    }


    public void setMonstros(ArrayList<MonstroNormal> monstros) { this.monstros = monstros; }
    public void setChefes(ArrayList<MonstroChefe> chefes) {this.chefes = chefes;}

}
