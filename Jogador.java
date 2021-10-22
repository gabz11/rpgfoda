package Foda;

import java.util.ArrayList;

public class Jogador {
    public String nome;

    public Classe cla_jogador; // Classe contem todos os parametros
    public Inventario inv_jogador;
    public ArrayList<Magia> magias_aprendidas;


    public int hp_atual;
    public int mp_atual;
    public int moedas;
    public int level;

    // buffs de item
    public int buff_for;
    public int buff_agi;
    public int buff_sor;
    public int buff_int;
    public int buff_per;

    // metricas
    public int monstros_abatidos;
    public int fases_passada;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Classe getCla_jogador() {
        return cla_jogador;
    }


    public Inventario getInv_jogador() {
        return inv_jogador;
    }

    public int getHp_atual() {return hp_atual;}

    public void setHp_atual(int hp_atual) {this.hp_atual = hp_atual;}

    public int getMp_atual() {return mp_atual;}

    public void setMp_atual(int mp_atual) {this.mp_atual = mp_atual;}

    public int getMoedas() {return  moedas;}

    public void setMoedas(int moedas){this.moedas = moedas;}

    public int getLevel() {return  level;}

    public void setLevel(int level) {this.level = level;}

    public int getBuff_for() {
        return buff_for;
    }

    public void setBuff_for(int buff_for) {
        this.buff_for = buff_for;
    }

    public int getBuff_agi() {
        return buff_agi;
    }

    public void setBuff_agi(int buff_agi) {
        this.buff_agi = buff_agi;
    }

    public int getBuff_sor() {
        return buff_sor;
    }

    public void setBuff_sor(int buff_sor) {
        this.buff_sor = buff_sor;
    }

    public int getBuff_int() {
        return buff_int;
    }

    public void setBuff_int(int buff_int) {
        this.buff_int = buff_int;
    }

    public int getBuff_per() {
        return buff_per;
    }

    public void setBuff_per(int buff_per) {
        this.buff_per = buff_per;
    }

    public ArrayList<Magia> getMagias_aprendidas() {
        return magias_aprendidas;
    }

    public int getMonstros_abatidos() {
        return monstros_abatidos;
    }

    public void setMonstros_abatidos(int monstros_abatidos) {
        this.monstros_abatidos = monstros_abatidos;
    }

    public int getFases_passada() {
        return fases_passada;
    }

    public void setFases_passada(int fases_passada) {
        this.fases_passada = fases_passada;
    }

    public Jogador(String nome, Classe cla_jogador)
    {
        this.nome = nome;
        this.cla_jogador = cla_jogador;
        this.inv_jogador = new Inventario();
        this.hp_atual = cla_jogador.getHp_jogador();
        this.mp_atual = cla_jogador.getMp_jogador();
        this.moedas = 0;
        this.level = 1;
        this.buff_for = 0;
        this.buff_agi = 0;
        this.buff_sor = 0;
        this.buff_int = 0;
        this.buff_int = 0;
        this.magias_aprendidas = new ArrayList<>();
        this.monstros_abatidos = 0;
        this.fases_passada = 0;
    }


    boolean JogadorPobre()
    {
        return getMoedas() <= 0;
    }

    void VerDinheiro()
    {
        if (getMoedas() == 0)
        {
            System.out.printf("%s está pobre...",getNome());
            System.out.println();
        }
        else if(getMoedas() == 1)
        {
            System.out.printf("%s tem 1 πCoin.",getNome());
            System.out.println();
        }
        else
        {
            System.out.printf("%s está com %s πCoins.",getNome(),getMoedas());
            System.out.println();
        }
    }

    // Obtem
    int ForcaTotalJogador()
    {
        // Calculo agilidade: AGILIDADE DO JOGADOR + BUFF TEMP DE ITEM + BUFF DE ARMA + BUFF DE ARMADURA
        int forca_jogador = getCla_jogador().getForca();
        int forca_item = getBuff_for();
        int forca_arma;
        int forca_armadura;
        int forca_total;
        if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() <=0) // Sem arma | Sem Armadura
        {
            forca_total = forca_jogador + forca_item;
            return forca_total;
        }
        else if(getInv_jogador().getSlot_arma().size() > 0 &&
                getInv_jogador().getSlot_armadura().size() <= 0) // Tem arma | N tem armadura
        {
            forca_arma = getInv_jogador().getSlot_arma().get(0).getForca(); //
            forca_total = forca_jogador + forca_item + forca_arma;
            return forca_total;
        }
        else if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() > 0) // Sem arma | Tem armadura
        {
            forca_armadura = getInv_jogador().getSlot_armadura().get(0).getForca(); //
            forca_total = forca_jogador + forca_item + forca_armadura;
            return forca_total;
        }
        else {
            forca_arma = getInv_jogador().getSlot_arma().get(0).getForca(); //
            forca_armadura = getInv_jogador().getSlot_armadura().get(0).getForca(); //
            forca_total = forca_jogador + forca_item + forca_arma + forca_armadura;
            return forca_total;
        }
    }

    int AgilidadeTotalJogador()
    {
        // Calculo agilidade: AGILIDADE DO JOGADOR + BUFF TEMP DE ITEM + BUFF DE ARMA + BUFF DE ARMADURA
        int agilidade_jogador = getCla_jogador().getAgilidade();
        int agilidade_item = getBuff_agi();
        int agilidade_arma;
        int agilidade_armadura;
        int agilidade_total;
        if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() <=0) // Sem arma | Sem Armadura
        {
            agilidade_total = agilidade_jogador + agilidade_item;
            return agilidade_total;
        }
        else if(getInv_jogador().getSlot_arma().size() > 0 &&
                getInv_jogador().getSlot_armadura().size() <= 0) // Tem arma | N tem armadura
        {
            agilidade_arma = getInv_jogador().getSlot_arma().get(0).getAgilidade(); //
            agilidade_total = agilidade_jogador + agilidade_item + agilidade_arma;
            return agilidade_total;
        }
        else if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() > 0) // Sem arma | Tem armadura
        {
            agilidade_armadura = getInv_jogador().getSlot_armadura().get(0).getAgilidade(); //
            agilidade_total = agilidade_jogador + agilidade_item + agilidade_armadura;
            return agilidade_total;
        }
        else {
            agilidade_arma = getInv_jogador().getSlot_arma().get(0).getAgilidade(); //
            agilidade_armadura = getInv_jogador().getSlot_armadura().get(0).getAgilidade(); //
            agilidade_total = agilidade_jogador + agilidade_item + agilidade_arma + agilidade_armadura;
            return agilidade_total;
        }
    }

    int SorteTotalJogador()
    {
        // Calculo sorte: AGILIDADE DO JOGADOR + BUFF TEMP DE ITEM + BUFF DE ARMA + BUFF DE ARMADURA
        int sorte_jogador = getCla_jogador().getSorte();
        int sorte_item = getBuff_sor();
        int sorte_arma;
        int sorte_armadura;
        int sorte_total;
        if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() <=0) // Sem arma | Sem Armadura
        {
            sorte_total = sorte_jogador + sorte_item;
            return sorte_total;
        }
        else if(getInv_jogador().getSlot_arma().size() > 0 &&
                getInv_jogador().getSlot_armadura().size() <= 0) // Tem arma | N tem armadura
        {
            sorte_arma = getInv_jogador().getSlot_arma().get(0).getSorte(); //
            sorte_total = sorte_jogador + sorte_item + sorte_arma;
            return sorte_total;
        }
        else if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() > 0) // Sem arma | Tem armadura
        {
            sorte_armadura = getInv_jogador().getSlot_armadura().get(0).getSorte(); //
            sorte_total = sorte_jogador + sorte_item + sorte_armadura;
            return sorte_total;
        }
        else {
            sorte_arma = getInv_jogador().getSlot_arma().get(0).getSorte(); //
            sorte_armadura = getInv_jogador().getSlot_armadura().get(0).getSorte(); //
            sorte_total = sorte_jogador + sorte_item + sorte_arma + sorte_armadura;
            return sorte_total;
        }
    }

    int IntTotalJogador()
    {
        // Calculo sorte: AGILIDADE DO JOGADOR + BUFF TEMP DE ITEM + BUFF DE ARMA + BUFF DE ARMADURA
        int int_jogador = getCla_jogador().getInteligencia();
        int int_item = getBuff_int();
        int int_arma;
        int int_armadura;
        int int_total;
        if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() <=0) // Sem arma | Sem Armadura
        {
            int_total = int_jogador + int_item;
            return int_total;
        }
        else if(getInv_jogador().getSlot_arma().size() > 0 &&
                getInv_jogador().getSlot_armadura().size() <= 0) // Tem arma | N tem armadura
        {
            int_arma = getInv_jogador().getSlot_arma().get(0).getInteligencia(); //
            int_total = int_jogador + int_item + int_arma;
            return int_total;
        }
        else if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() > 0) // Sem arma | Tem armadura
        {
            int_armadura = getInv_jogador().getSlot_armadura().get(0).getInteligencia(); //
            int_total = int_jogador + int_item + int_armadura;
            return int_total;
        }
        else {
            int_arma = getInv_jogador().getSlot_arma().get(0).getInteligencia(); //
            int_armadura = getInv_jogador().getSlot_armadura().get(0).getInteligencia(); //
            int_total = int_jogador + int_item + int_arma + int_armadura;
            return int_total;
        }
    }

    int PercepcaoTotalJogador()
    {
        // Calculo agilidade: AGILIDADE DO JOGADOR + BUFF TEMP DE ITEM + BUFF DE ARMA + BUFF DE ARMADURA
        int percepcao_jogador = getCla_jogador().getPercepcao();
        int percepcao_item = getBuff_per();
        int percepcao_arma;
        int percepcao_armadura;
        int percepcao_total;
        if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() <=0) // Sem arma | Sem Armadura
        {
            percepcao_total = percepcao_jogador + percepcao_item;
            return percepcao_total;
        }
        else if(getInv_jogador().getSlot_arma().size() > 0 &&
                getInv_jogador().getSlot_armadura().size() <= 0) // Tem arma | N tem armadura
        {
            percepcao_arma = getInv_jogador().getSlot_arma().get(0).getPercepcao(); //
            percepcao_total = percepcao_jogador + percepcao_item + percepcao_arma;
            return percepcao_total;
        }
        else if(getInv_jogador().getSlot_arma().size() <= 0 &&
                getInv_jogador().getSlot_armadura().size() > 0) // Sem arma | Tem armadura
        {
            percepcao_armadura = getInv_jogador().getSlot_armadura().get(0).getPercepcao(); //
            percepcao_total = percepcao_jogador + percepcao_item + percepcao_armadura;
            return percepcao_total;
        }
        else {
            percepcao_arma = getInv_jogador().getSlot_arma().get(0).getPercepcao(); //
            percepcao_armadura = getInv_jogador().getSlot_armadura().get(0).getPercepcao(); //
            percepcao_total = percepcao_jogador + percepcao_item + percepcao_arma + percepcao_armadura;
            return percepcao_total;
        }
    }

    void Metricas()
    {
        System.out.println();
        System.out.printf("\nNome: %s | Classe: %s",getNome(),getCla_jogador().getCla_nome());
        System.out.printf("\n%s πCoins.",getMoedas());
        System.out.printf("\n%s Inimigos abatidos.",getMonstros_abatidos());
        System.out.printf("\n%s Chefes abatidos/Fases completadas.",getFases_passada());
        System.out.println();
    }

    int Pontuacao()
    {
        return getMoedas() + (getFases_passada() * 250) ;
    }



}

