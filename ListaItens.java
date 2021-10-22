package Foda;

import java.util.ArrayList;

public class ListaItens {
    public ArrayList<ItemConsumivel> lista_consumiveis = new ArrayList<>();
    public ArrayList<ItemArma> lista_armas = new ArrayList<>();
    public ArrayList<ItemArmadura> lista_armaduras = new ArrayList<>();


    public ArrayList<ItemConsumivel> getLista_consumiveis() {
        return lista_consumiveis;
    }

    public ArrayList<ItemArma> getLista_armas() {
        return lista_armas;
    }

    public ArrayList<ItemArmadura> getLista_armaduras() {
        return lista_armaduras;
    }

    public void setLista_consumiveis(ArrayList<ItemConsumivel> listaitens_p) {
        this.lista_consumiveis = listaitens_p;
    }
    public void setLista_armas(ArrayList<ItemArma> lista_armas) {
        this.lista_armas = lista_armas;
    }
    public void setLista_armaduras(ArrayList<ItemArmadura> lista_armaduras) {
        this.lista_armaduras = lista_armaduras;
    }

    void TotalConsumiveis()
    {
        if(lista_consumiveis.size() == 0){
            System.out.println("Não há consumiveis cadastrados");
        }
        else {
            System.out.println("Total consumiveis:"+lista_consumiveis.size());
            for(ItemConsumivel x: lista_consumiveis) {
                System.out.println();
                System.out.printf(" | Nome: %s |  Preço: %s",
                        x.getNome(),x.getValor());
                        x.DescreverAtributosItem();
                System.out.println();
            }
        }
    }

    void TotalArmas()
    {
        if(lista_armas.size() == 0){
            System.out.println("Não há armas cadastradas");
        }
        else {
            System.out.println("Total armas:"+lista_armas.size());
            for(ItemArma x: lista_armas) {
                System.out.println();
                System.out.printf(" | Nome: %s | Preço: %s | ATK: %s",x.getNome(),x.getValor(),x.getAtaque());
                x.DescreverAtributosArma();
                System.out.println();
            }
        }
    }

    void TotalArmaduras()
    {
        if(lista_armaduras.size() == 0){
            System.out.println("Não há armaduras cadastradas.");
        }
        else {
            System.out.println("Total armaduras:"+lista_armaduras.size());
            for (ItemArmadura x: lista_armaduras) {
                System.out.println();
                System.out.printf("Nome: %s | Preço: %s | DEF: %s", x.getNome(),x.getValor(),x.getDefesa());
                x.DescreverAtributosArmadura();
            }
            System.out.println();
        }
    }

    boolean ValidarItens()
    {
        return getLista_armaduras().size() != 0 && getLista_armas().size() != 0 && getLista_consumiveis().size() !=0;
    }
}
