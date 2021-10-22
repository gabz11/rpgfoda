package Foda;

import java.util.ArrayList;

public class ListaEventos {
    ArrayList<Evento> eventos = new ArrayList<>();

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    void setEventos(ArrayList<Evento> eventos) {this.eventos = eventos;}

    boolean ValidarEventos()
    {
        return eventos.size() != 0;
    }

    void TotalEventos()
    {
        if (eventos.size() == 0)
        {
            System.out.println("Não há eventos cadastrados...");
        }
        else
        {
            System.out.println("Total de Eventos: "+getEventos().size());
        }
    }
}
