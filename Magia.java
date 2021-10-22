package Foda;

public class Magia {
    public String nome; // Nome da mágia
    public String descricao; // Descrição da mágia

    public int valor; // Preço da mágia

    public int ataque; // Ataque base da mágia
    public int custo_mana; // Custo de mana da magia

    public int vitalidade; // Quanto Restaura ou perde de vitalidade com uso


    public boolean magiadeInicio; // Se pode escolher no inicio a mágia
    public String dropavelPor; // TODOS | MONSTROS | CHEFES


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

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getCusto_mana() {
        return custo_mana;
    }

    public void setCusto_mana(int custo_mana) {
        this.custo_mana = custo_mana;
    }

    public int getVitalidade() {
        return vitalidade;
    }

    public void setVitalidade(int vitalidade) {
        this.vitalidade = vitalidade;
    }

    public boolean isMagiadeInicio() { return magiadeInicio; }

    public void setMagiadeInicio(boolean magiadeInicio) { this.magiadeInicio = magiadeInicio;
    }

    public String getDropavelPor() { return dropavelPor; }

    public void setDropavelPor(String dropavelPor) { this.dropavelPor = dropavelPor; }

    public Magia(String nome, String descricao, int valor,int ataque, int custo_mana, int vitalidade,
                 boolean magiadeInicio, String dropavelPor)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.ataque = ataque;
        this.custo_mana = custo_mana;
        this.vitalidade = vitalidade;
        this.magiadeInicio = magiadeInicio;
        this.dropavelPor = dropavelPor;
    }
    void DescreverAtributosMagia(){
        if(ataque != 0)
        {
            System.out.printf(" | ATK: %s", ataque);
        }
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
        System.out.printf(" | Custo MP: %s", custo_mana);
    }
}
