package Foda;

public class ItemConsumivel {
    // Items consumiveis
    public String nome; // Nome do item
    public String descricao; // Descrição do item

    public int valor; // valor de piCoins q o item vale, por padrão o preço base vai ser variado nos metodos

    public int vitalidade; // Quanto de vitalidade o item restaura ou tira
    public int mana; // Quanto de mana o item restaura ou tira
    public int forca; // Quanto de buff de força o item restaura ou tira
    public int agilidade; // Qto de buff de agilidade o item restaura ou tira
    public int sorte; // qto de buff de sorte o item restaura ou tira
    public int inteligencia;  // qto de buff de sorte o item restaura ou tira
    public int percepcao; // qto de buff de percepção o item restaura ou tira

    public boolean podeIniciar; // caso possa escolher o item no começo do jogo

    public String dropavelPor; // Dropavel por TODOS | CHEFES | MONSTROS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValor() {return valor;}

    public void setValor(int valor) {this.valor = valor;}

    public int getVitalidade() {
        return vitalidade;
    }

    public void setVitalidade(int vitalidade) {
        this.vitalidade = vitalidade;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getSorte() {
        return sorte;
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getPercepcao() {
        return percepcao;
    }

    public void setPercepcao(int percepcao) {
        this.percepcao = percepcao;
    }

    public boolean getPodeIniciar() { return podeIniciar;}

    public void setPodeIniciar(boolean podeIniciar) {this.podeIniciar = podeIniciar;}

    public String getDropavelPor() {return dropavelPor;}

    public void setDropavelPor(String dropavelPor) {this.dropavelPor = dropavelPor;}

    public ItemConsumivel(String nome, String descricao, int valor, int vitalidade, int mana,
                          int forca, int agilidade, int sorte, int inteligencia, int percepcao, boolean podeIniciar,String dropavelPor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.vitalidade = vitalidade;
        this.mana = mana;
        this.forca = forca;
        this.agilidade = agilidade;
        this.sorte = sorte;
        this.inteligencia = inteligencia;
        this.percepcao = percepcao;
        this.podeIniciar = podeIniciar;
        this.dropavelPor = dropavelPor;
    }
    void DescreverAtributosModificador()
    {
        System.out.printf("HP: %s | MP: %s | FOR: %s | AGI: %s | SOR: %s | INT: %s | PER: %s",
                getVitalidade(),getMana(),getForca(),getAgilidade(),getSorte(),getInteligencia(),getPercepcao());
    }
    void DescreverAtributosItem(){
         if(vitalidade != 0)
         {
            if(vitalidade < 0)
            {
                System.out.printf(" | HP: %s", vitalidade);
            }
            else
            {
                System.out.printf(" | HP: +%s", vitalidade);
            }
         }
         if(mana != 0)
         {
            if(mana < 0)
            {
                System.out.printf(" | MP: %s", mana);
            }
            else
            {
                System.out.printf(" | MP: +%s", mana);
            }
         }
         if(forca != 0)
         {
             if(forca < 0)
             {
                 System.out.printf(" | FOR: %s", forca);
             }
             else
             {
                 System.out.printf(" | FOR: +%s", forca);
             }
         }
         if(agilidade != 0)
         {
             if(agilidade < 0)
             {
                 System.out.printf(" | AGI: %s", agilidade);
             }
             else
             {
                 System.out.printf(" | AGI: +%s", agilidade);
             }
         }
         if(sorte != 0)
         {
             if(sorte < 0)
             {
                 System.out.printf(" | SOR: %s", sorte);
             }
             else
             {
                 System.out.printf(" | SOR: +%s", sorte);
             }
         }
         if(inteligencia != 0)
         {
             if(inteligencia < 0)
             {
                 System.out.printf(" | INT: %s", inteligencia);
             }
             else
             {
                 System.out.printf(" | INT: +%s", inteligencia);
             }
         }
         if(percepcao != 0)
         {
             if(percepcao < 0)
             {
                 System.out.printf(" | PER: %s", percepcao);
             }
             else
             {
                 System.out.printf(" | PER: +%s", percepcao);
             }
         }
    }
}
