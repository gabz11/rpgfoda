package Foda;

public class MonstroNormal extends Monstro{
    public String tipo; // Tipo do monstro, FOR | INT | PER, deve servir para calculos

    public String atk_nome; // Nome do ataque do monstro
    public int forca;
    public int agilidade;
    public int sorte;
    public int inteligencia;
    public int percepcao;
    public MonstroNormal(String nome, String lore, String tipo, String atk_nome,
                         int forc, int agi, int sor, int inte, int per)
    {
        this.nome = nome;
        this.descricao = lore;
        this.tipo = tipo;
        this.atk_nome = atk_nome;
        this.forca = forc;
        this.agilidade = agi;
        this.sorte = sor;
        this.inteligencia = inte;
        this.percepcao = per;
    }
}
