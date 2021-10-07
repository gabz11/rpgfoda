package Foda;

import java.util.ArrayList;

public class ListaMonstro {
    public ArrayList<MonstroNormal> monstros;
    void TotalMonstros() {
        System.out.println(monstros.size());
        if(monstros.size() == 0) {
            System.out.println("Não há monstros cadastrados.");
        }
        else {
            for (Monstro monstro : monstros) {
                System.out.printf("Nome: %s\nDescrição:%s", monstro.nome, monstro.descricao);
            }
        }
    }
}
