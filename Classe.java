package Foda;



public class Classe {
    // Descrição
    public String cla_nome; // Titulo classe
    public String cla_lore; // Lore classe

    // Nivel
    public int level = 1; // Toda classe começa no nível 1.

    // Vida | Magia
    public int hp_jogador; // HP
    public int mp_jogador; // MP


    //Atributos
    public int forca;
    public int agilidade;
    public int sorte;
    public int inteligencia;
    public int percepcao;



    public String getCla_nome() {
        return cla_nome;
    }

    public void setCla_nome(String cla_nome) {
        this.cla_nome = cla_nome;
    }

    public String getCla_lore() {
        return cla_lore;
    }

    public void setCla_lore(String cla_lore) {
        this.cla_lore = cla_lore;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp_jogador() {
        return hp_jogador;
    }

    public void setHp_jogador(int hp_jogador) {
        this.hp_jogador = hp_jogador;
    }

    public int getMp_jogador() {
        return mp_jogador;
    }

    public void setMp_jogador(int mp_jogador) {
        this.mp_jogador = mp_jogador;
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

    public Classe(String cla_nome, String cla_lore, int hp_jogador, int mp_jogador, int forca, int agilidade, int sorte,
                  int inteligencia, int percepcao) {
        this.cla_nome = cla_nome;
        this.cla_lore = cla_lore;
        this.hp_jogador = hp_jogador;
        this.mp_jogador = mp_jogador;
        this.forca = forca;
        this.agilidade = agilidade;
        this.sorte = sorte;
        this.inteligencia = inteligencia;
        this.percepcao = percepcao;
    }
    void DescreverAtributosModificador()
    {
        System.out.printf("FOR: %s | AGI: %s | SOR: %s | INT: %s | PER: %s",
                getForca(),getAgilidade(),getSorte(),getInteligencia(),getPercepcao());
    }
}
