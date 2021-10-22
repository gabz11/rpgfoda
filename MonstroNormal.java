package Foda;

public class MonstroNormal{
    public String nome;
    public String descricao;
    public String tipo; // Tipo do monstro, FOR | INT | PER, deve servir para calculos

    public String atk_nome; // Nome do ataque do monstro
    public int atk_dano; // Dano do ataque base do monstro  vai ser calculado por seu atributo do tipo

    public int vitalidade;


    // Atributos dos monstros
    public int forca;
    public int agilidade;
    public int sorte;
    public int inteligencia;
    public int percepcao;

    public MonstroNormal(String nome, String lore, String tipo, int vitalidade, String atk_nome,
                         int atk_dano, int forca, int agilidade, int sorte, int inteligencia, int percepcao)
    {
        this.nome = nome;
        this.descricao = lore;
        this.tipo = tipo;
        this.vitalidade = vitalidade;
        this.atk_nome = atk_nome;
        this.atk_dano = atk_dano;
        this.forca = forca;
        this.agilidade = agilidade;
        this.sorte = sorte;
        this.inteligencia = inteligencia;
        this.percepcao = percepcao;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}

    public  void setDescricao(String descricao) {this.descricao = descricao;}

    public String getTipo() {return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getAtk_nome() { return atk_nome; }

    public void setAtk_nome(String atk_nome) { this.atk_nome = atk_nome; }

    public int getAtk_dano() {return atk_dano;}

    public void setAtk_dano(int atk_dano) {this.atk_dano = atk_dano;}

    public int getVitalidade() {return vitalidade;}

    public void setVitalidade(int vitalidade) {this.vitalidade = vitalidade;}

    public int getForca() { return forca; }

    public void setForca(int forca) { this.forca = forca; }

    public int getAgilidade() { return agilidade; }

    public void setAgilidade(int agilidade) { this.agilidade = agilidade; }

    public int getSorte() { return sorte; }

    public void setSorte(int sorte) { this.sorte = sorte; }

    public int getInteligencia() { return inteligencia; }

    public void setInteligencia(int inteligencia) { this.inteligencia = inteligencia; }

    public int getPercepcao() { return percepcao; }

    public void setPercepcao(int percepcao) { this.percepcao = percepcao; }

    void AtributosMonstro()
    {
        System.out.println();
        System.out.printf("FOR: %s | AGI : %s | SOR: %s | INT: %s | PER: %s",
                forca,agilidade,sorte,inteligencia,percepcao);
        System.out.println();
    }
}
