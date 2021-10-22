package Foda;



public class Evento {


    String nome_evento; // nome de identificador

    String proposta_evento; // A narrativa do evento
    String texto_resultado_bom; // Resultado caso o resultado seja favoravel
    String texto_resultado_ruim; // Resultado caso o resultado seja ruim
    String texto_resultado_ignorar; // Texto que mostra quando u usário ignora.


    String aceitar; // Texto para participar do evento.
    String recusar; // Texto para recusar do evento.

    int ganhar_moedas; //  Quanto o jogador ganha em um resultado positivo
    int perder_moedas; // Quanto o jogador perde em um resultado negativo


    int ganhar_vitalidade; // Quanto o jogador restaura de vida em um resultado positivo
    int perder_vitalidade; // Quanto o jogador perde de vida em um resultado negativo (OBS: pode resultar em morte)

    int ganhar_mana; // Quanto o jogador restaura de mana em um resultado positivo
    int perder_mana; // Quanto o jogadr perde de mana em um resultado desfavoravel


    int ganhar_forca; // Quanto ganha de força permanentemente em um resultado positivo
    int perder_forca; // Quanto perde de força  permanentemente em um resultado negativo
    int ganhar_agilidade; // Quanto ganha de agilidade permanentemente em um resultado positivo
    int perder_agilidade; // Quanto perde de agilidade permanentemente em um resultado negativo
    int ganhar_sorte; // Quanto ganha de sorte permanentemente em um resultado positivo
    int perder_sorte; // Quanto perde de sorte permanentemente em um resultado negativo
    int ganhar_inteligencia; // qto ganha de int perma
    int perder_inteligencia; // qto perde de int perma
    int ganhar_percepcao; // qto perde de per perma
    int perder_percepcao; // qto perde de per perma


    public Evento(String nome_evento,String proposta_evento, String texto_resultado_bom,
                  String texto_resultado_ruim, String
                          texto_resultado_ignorar, String aceitar, String recusar, int ganhar_moedas,
                  int perder_moedas, int ganhar_vitalidade, int perder_vitalidade, int ganhar_mana, int perder_mana,
                  int ganhar_forca, int perder_forca, int ganhar_agilidade, int perder_agilidade, int ganhar_sorte,
                  int perder_sorte, int ganhar_inteligencia, int perder_inteligencia, int ganhar_percepcao,
                  int perder_percepcao) {
        this.nome_evento = nome_evento;
        this.proposta_evento = proposta_evento;
        this.texto_resultado_bom = texto_resultado_bom;
        this.texto_resultado_ruim = texto_resultado_ruim;
        this.texto_resultado_ignorar = texto_resultado_ignorar;
        this.aceitar = aceitar;
        this.recusar = recusar;
        this.ganhar_moedas = ganhar_moedas;
        this.perder_moedas = perder_moedas;
        this.ganhar_vitalidade = ganhar_vitalidade;
        this.perder_vitalidade = perder_vitalidade;
        this.ganhar_mana = ganhar_mana;
        this.perder_mana = perder_mana;
        this.ganhar_forca = ganhar_forca;
        this.perder_forca = perder_forca;
        this.ganhar_agilidade = ganhar_agilidade;
        this.perder_agilidade = perder_agilidade;
        this.ganhar_sorte = ganhar_sorte;
        this.perder_sorte = perder_sorte;
        this.ganhar_inteligencia = ganhar_inteligencia;
        this.perder_inteligencia = perder_inteligencia;
        this.ganhar_percepcao = ganhar_percepcao;
        this.perder_percepcao = perder_percepcao;
    }


    void ResultadosPositivos(String nome_jogador)
    {
        if(ganhar_moedas > 0)
        {
            if(ganhar_moedas == 1)
            {
            System.out.printf("%s ganhou %s πCoin!",nome_jogador,ganhar_moedas);
            }
            else
            {
                System.out.printf("%s ganhou %s πCoins!",nome_jogador,ganhar_moedas);
            }
            System.out.println();
        }
        if(ganhar_vitalidade > 0)
        {
            System.out.printf("%s restaurou %s de vida!",nome_jogador, ganhar_vitalidade);
            System.out.println();
        }
        if(ganhar_mana > 0)
        {
            System.out.printf("%s restaurou %s de mana!", nome_jogador, ganhar_mana);
            System.out.println();
        }
        if(ganhar_forca > 0)
        {
            System.out.printf("%s se sente forte igual uma égua! +%s em Força.",nome_jogador, ganhar_forca);
            System.out.println();
        }
        if(ganhar_agilidade > 0)
        {
            System.out.printf("%s se sente agil como uma chita! +%s em Agilidade.",nome_jogador, ganhar_agilidade);
            System.out.println();
        }
        if(ganhar_sorte > 0)
        {
            System.out.printf("%s sente que pode ganhar na loteria! +%s em Sorte.",nome_jogador, ganhar_sorte);
            System.out.println();
        }
        if(ganhar_inteligencia > 0)
        {
            System.out.printf("%s sente que tem um cérebro enorme! +%s em Inteligência.", nome_jogador,
                    ganhar_inteligencia);
            System.out.println();
        }
        if(ganhar_percepcao > 0)
        {
            System.out.printf("%s se sente como uma coruja! +%s em Percepção.",nome_jogador,ganhar_percepcao);
            System.out.println();
        }
    }
    void ResultadosNegativos(String nome_jogador)
    {
        if(perder_moedas > 0)
        {
            if (perder_moedas == 1)
            {
                System.out.printf("%s perdeu %s πCoin...",nome_jogador,perder_moedas);
            }
            else
            {
                System.out.printf("%s perdeu %s πCoins...",nome_jogador,perder_moedas);
            }
            System.out.println();
        }
        if(perder_vitalidade > 0)
        {
            System.out.printf("%s perdeu %s de vida...",nome_jogador, perder_vitalidade);
            System.out.println();
        }
        if(perder_mana > 0)
        {
            System.out.printf("%s perdeu %s de mana...",nome_jogador, perder_mana);
            System.out.println();
        }
        if(perder_forca > 0)
        {
            System.out.printf("%s sente que precisa comer brocolis. -%s em Força...",nome_jogador, perder_forca);
            System.out.println();
        }
        if(perder_agilidade > 0)
        {
            System.out.printf("%s se sente agil como um bebado. -%s em Agilidade...",nome_jogador, perder_agilidade);
            System.out.println();
        }
        if(perder_sorte > 0)
        {
            System.out.printf("%s sente que o mundo conspira contra sua existência. -%s em Sorte...",nome_jogador,
                    perder_sorte);
            System.out.println();
        }
        if(perder_inteligencia > 0)
        {
            System.out.printf("%s sente que perdeu uns neuronios. -%s em Inteligência...", nome_jogador,
                    perder_inteligencia);
            System.out.println();
        }
        if(perder_percepcao > 0)
        {
            System.out.printf("%s se sente perdido. -%s em Percepção...",nome_jogador,perder_percepcao);
            System.out.println();
        }
    }


    public String getNome_evento() {
        return nome_evento;
    }

    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    public String getProposta_evento() {
        return proposta_evento;
    }

    public void setProposta_evento(String proposta_evento) {
        this.proposta_evento = proposta_evento;
    }

    public String getTexto_resultado_bom() {
        return texto_resultado_bom;
    }

    public void setTexto_resultado_bom(String texto_resultado_bom) {
        this.texto_resultado_bom = texto_resultado_bom;
    }

    public String getTexto_resultado_ruim() {
        return texto_resultado_ruim;
    }

    public void setTexto_resultado_ruim(String texto_resultado_ruim) { this.texto_resultado_ruim = texto_resultado_ruim; }

    public String getTexto_resultado_ignorar() {return texto_resultado_ignorar;}

    public void setTexto_resultado_ignorar(String texto_resultado_ruim) {this.texto_resultado_ignorar = texto_resultado_ruim;}

    public String getAceitar() {
        return aceitar;
    }

    public void setAceitar(String aceitar) {
        this.aceitar = aceitar;
    }

    public String getRecusar() {
        return recusar;
    }

    public void setRecusar(String recusar) {
        this.recusar = recusar;
    }

    public int getGanhar_moedas() {
        return ganhar_moedas;
    }

    public void setGanhar_moedas(int ganhar_moedas) {
        this.ganhar_moedas = ganhar_moedas;
    }

    public int getPerder_moedas() {
        return perder_moedas;
    }

    public void setPerder_moedas(int perder_moedas) {
        this.perder_moedas = perder_moedas;
    }

    public int getGanhar_vitalidade() {
        return ganhar_vitalidade;
    }

    public void setGanhar_vitalidade(int ganhar_vitalidade) {
        this.ganhar_vitalidade = ganhar_vitalidade;
    }

    public int getPerder_vitalidade() {
        return perder_vitalidade;
    }

    public void setPerder_vitalidade(int perder_vitalidade) {
        this.perder_vitalidade = perder_vitalidade;
    }

    public int getGanhar_mana() { return ganhar_mana; }

    public void setGanhar_mana(int ganhar_mana) { this.ganhar_mana = ganhar_mana; }

    public int getPerder_mana() { return perder_mana; }

    public void setPerder_mana(int perder_mana) { this.perder_mana = perder_mana; }

    public int getGanhar_forca() {
        return ganhar_forca;
    }

    public void setGanhar_forca(int ganhar_forca) {
        this.ganhar_forca = ganhar_forca;
    }

    public int getPerder_forca() {
        return perder_forca;
    }

    public void setPerder_forca(int perder_forca) {
        this.perder_forca = perder_forca;
    }

    public int getGanhar_agilidade() {
        return ganhar_agilidade;
    }

    public void setGanhar_agilidade(int ganhar_agilidade) {
        this.ganhar_agilidade = ganhar_agilidade;
    }

    public int getPerder_agilidade() {
        return perder_agilidade;
    }

    public void setPerder_agilidade(int perder_agilidade) {
        this.perder_agilidade = perder_agilidade;
    }

    public int getGanhar_sorte() {
        return ganhar_sorte;
    }

    public void setGanhar_sorte(int ganhar_sorte) {
        this.ganhar_sorte = ganhar_sorte;
    }

    public int getPerder_sorte() {
        return perder_sorte;
    }

    public void setPerder_sorte(int perder_sorte) {
        this.perder_sorte = perder_sorte;
    }

    public int getGanhar_inteligencia() {
        return ganhar_inteligencia;
    }

    public void setGanhar_inteligencia(int ganhar_inteligencia) {
        this.ganhar_inteligencia = ganhar_inteligencia;
    }

    public int getPerder_inteligencia() {
        return perder_inteligencia;
    }

    public void setPerder_inteligencia(int perder_inteligencia) {
        this.perder_inteligencia = perder_inteligencia;
    }

    public int getGanhar_percepcao() {
        return ganhar_percepcao;
    }

    public void setGanhar_percepcao(int ganhar_percepcao) {
        this.ganhar_percepcao = ganhar_percepcao;
    }

    public int getPerder_percepcao() {
        return perder_percepcao;
    }

    public void setPerder_percepcao(int perder_percepcao) {
        this.perder_percepcao = perder_percepcao;
    }

    void ExibirResultadosPositivos()
    {
        System.out.printf("Moedas: %s | HP: %s | MP: %s | FOR: %s | AGI: %s | SOR: %s | INT: %s | PER: %s",getGanhar_moedas(),
                getGanhar_vitalidade(),getGanhar_mana(),getGanhar_forca(),getGanhar_agilidade(),getGanhar_sorte(),
                getGanhar_inteligencia(),getGanhar_percepcao());
        System.out.println();
    }
    void ExibirResultadosNegativos()
    {
        System.out.printf("Moedas: %s | HP: %s | MP: %s | FOR: %s | AGI: %s | SOR: %s | INT: %s | PER: %s",getPerder_moedas(),
                getPerder_vitalidade(),getPerder_mana(),getPerder_forca(),getPerder_agilidade(),getPerder_sorte(),
                getPerder_inteligencia(),getPerder_percepcao());
        System.out.println();
    }
}