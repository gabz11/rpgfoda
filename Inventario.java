package Foda;

import java.util.ArrayList;

public class Inventario {
    ArrayList<ItemConsumivel> consumivels;
    ArrayList<ItemArma> slot_arma;
    ArrayList<ItemArmadura> slot_armadura;
    int max_consumiveis;

    public Inventario()
    {
        this.consumivels = new ArrayList<>();
        this.slot_arma = new ArrayList<>();
        this.slot_armadura = new ArrayList<>();
        this.max_consumiveis = 4;
    }

    public ArrayList<ItemConsumivel> getConsumivels() {
        return consumivels;
    }

    public ArrayList<ItemArma> getSlot_arma() {
        return slot_arma;
    }

    public ArrayList<ItemArmadura> getSlot_armadura() {
        return slot_armadura;
    }

    public int getMax_consumiveis() {
        return max_consumiveis;
    }

    public void setMax_consumiveis(int max_consumiveis) {
        this.max_consumiveis = max_consumiveis;
    }

    void DadosArmaEquipada()
    {
        if(getSlot_arma().size() == 0)
        {
            System.out.print("| Arma: N/A");
        }
        else {
            System.out.printf("| Arma: %s | ATK(Base): %s | ATR: '%s'", slot_arma.get(0).getNome(),
                    slot_arma.get(0).getAtaque(), slot_arma.get(0).getTipo());
        }
    }
    void DadosArmaduraEquipada()
    {
        if(getSlot_armadura().size() == 0)
        {
            System.out.print("| Armadura: N/A");
        }
        else
        {
            System.out.printf("| Armadura: %s | DEF: %s",slot_armadura.get(0).getNome(),
                    slot_armadura.get(0).getDefesa());
        }
    }
    void TotalConsumiveis()
    {
        System.out.printf("| N° Consumiveis %s/%s",getConsumivels().size(),getMax_consumiveis());
    }



}

