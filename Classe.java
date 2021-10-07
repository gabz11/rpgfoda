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
}
