package Foda;

import java.util.ArrayList;
import java.util.Random;

public class Aleatorio {
    Random rand = new Random();

    public ArrayList<String> getNome_pessoas() {
        return nome_pessoas;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public ArrayList<String> getLugares() {
        return lugares;
    }

    public ArrayList<String> getFrases() {
        return frases;
    }

    public ArrayList<String> getObjetos() {
        return objetos;
    }

    public ArrayList<String> getNomes_musicas() {
        return nomes_musicas;
    }

    public ArrayList<String> getNomes_galo() {
        return nomes_galo;
    }

    public ArrayList<String> getApelidos_galo() {
        return apelidos_galo;
    }

    ArrayList<String> nome_pessoas = new ArrayList<>(); // NOME_PESSOA
    ArrayList<String> horarios = new ArrayList<>(); // HORARIO
    ArrayList<String> lugares = new ArrayList<>(); // LUGAR
    ArrayList<String> frases = new ArrayList<>(); // FRASE
    ArrayList<String> objetos = new ArrayList<>(); // OBJETO
    ArrayList<String> nomes_musicas = new ArrayList<>(); // NOME_MUSICA

    ArrayList<String> nomes_galo = new ArrayList<>(); // NOMES GALO
    ArrayList<String> apelidos_galo = new ArrayList<>(); // APELIDOS GALO



    String Aleatorizador(String texto) {
        texto = texto.replace("NOME_PESSOA", nome_pessoas.get(rand.nextInt(nome_pessoas.size())));
        texto = texto.replace("HORARIO", horarios.get(rand.nextInt(horarios.size())));
        texto = texto.replace("LUGAR", lugares.get(rand.nextInt(lugares.size())));
        texto = texto.replace("FRASE", frases.get(rand.nextInt(frases.size())));
        texto = texto.replace("OBJETO", objetos.get(rand.nextInt(objetos.size())));
        texto = texto.replace("NOME_MUSICA", nomes_musicas.get(rand.nextInt(nomes_musicas.size())));
        return texto;
    }

    String GaloAleatorio()
    {
        String nome_galo = "NOME_GALO O \"APELIDO\"";
        nome_galo = nome_galo.replace("NOME_GALO",nomes_galo.get(rand.nextInt(nomes_galo.size())));
        nome_galo = nome_galo.replace("APELIDO",apelidos_galo.get(rand.nextInt(apelidos_galo.size())));
        return nome_galo;
    }
    boolean ValidarStrings()
    {
        return nome_pessoas.size() != 0 && horarios.size() != 0 && lugares.size() != 0 && frases.size() != 0
                && objetos.size() != 0 && nomes_musicas.size() != 0 && nomes_galo.size() != 0
                && apelidos_galo.size() != 0;
    }
}