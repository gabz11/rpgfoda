package Foda;

import java.util.ArrayList;

public class ListaClasse {
    public ArrayList<Classe> classes = new ArrayList<>();
    void TotalClasses() {
        if(classes.size() == 0) {
            System.out.println("Não há classes cadastradas.");
        }
        else {
            System.out.println("Total de classes:"+classes.size());
            for (Classe x : classes) {
                System.out.println();
                System.out.printf("Nome: %s\nDescrição: %s\nHP: %s\nMP: %s", x.cla_nome, x.cla_lore, x.getHp_jogador(),
                        x.getMp_jogador());
                System.out.println();
                x.DescreverAtributosModificador();
                System.out.println();
            }
            System.out.println();
        }
    }

    public ArrayList<Classe> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classe> classes) {
        this.classes = classes;
    }

    boolean ValidarClasses()
    {
        return classes.size() != 0;
    }


}
