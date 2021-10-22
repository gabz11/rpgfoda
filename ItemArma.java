package Foda;

public class ItemArma {
    public String nome;
    public String descricao;
    public String tipo; // Tipo da arma, FOR ou PER.
    // ataque base do item para calculo
    public int ataque;

    // valor da arma
    public int valor;

    // Buffs em questão da arma
    public int forca;
    public int agilidade;
    public int sorte;
    public int inteligencia;
    public int percepcao;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAtaque() {return ataque;}

    public void setAtaque(int ataque) {this.ataque = ataque;}

    public int getValor() {return valor;}

    public void setValor(int valor) {this.valor = valor;}

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

    public boolean getPodeIniciar() {return podeIniciar;}

    public void setPodeIniciar(boolean podeIniciar) {this.podeIniciar = podeIniciar;}

    public String getDropavelPor() {return dropavelPor;}

    public void setDropavelPor(String dropavelPor) {this.dropavelPor = dropavelPor;}

    public ItemArma(String nome, String descricao, String tipo, int ataque, int valor, int forca, int agilidade,
                    int sorte, int inteligencia, int percepcao, boolean podeIniciar, String dropavelPor){
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.ataque = ataque;
        this.valor = valor;
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
        System.out.printf("ATK: %s | FOR: %s | AGI: %s | SOR: %s | INT: %s | PER: %s",
                getAtaque(),getForca(),getAgilidade(),getSorte(),getInteligencia(),getPercepcao());
    }
    void DescreverAtributosArma(){
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
