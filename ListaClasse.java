package Foda;

import java.util.ArrayList;

public class ListaClasse {
    public ArrayList<Classe> classes;
    void TotalClasses() {
        System.out.println(classes.size());
        if(classes.size() == 0) {
            System.out.println("Não há classes cadastradas.");
        }
        else {
            for (Classe x : classes) {
                System.out.printf("Nome: %s\nDescrição:%s", x.cla_nome, x.cla_lore);
            }
        }
    }
}
