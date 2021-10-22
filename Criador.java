package Foda;

import java.util.Scanner;
import java.util.ArrayList;

public class Criador {
    Scanner entrada = new Scanner(System.in);

    //#################################
    // Criar Item Consumivel
    void CriarItemConsumivel(ArrayList<ItemConsumivel> consumiveis)
    {
        String nome = RetornaStr("Qual o nome do item?");
        String descricao = RetornaStr("Qual a descrição do item?");
        int valor = RetornaIntPositivo("Qual o preço base do item?");
        int vitalidade = RetornaInt("Quanto afeta a vitalidade?");
        int mana = RetornaInt("Quanto afeta o mana?");
        int forca = RetornaInt("Quanto afeta a força?");
        int agilidade = RetornaInt("Quanto afeta a agilidade?");
        int sorte = RetornaInt("Quanto afeta a sorte?");
        int inteligencia = RetornaInt("Quanto afeta a inteligência?");
        int percepcao = RetornaInt("Quanto afeta a percepção?");
        boolean podeIniciar = RetornaBool("Pode escolher no inicio do jogo?");
        String dropavelPor = DropavelPor();
        ItemConsumivel proprio = new ItemConsumivel(nome,descricao,valor,vitalidade,mana,forca,agilidade,
                sorte,inteligencia,percepcao, podeIniciar,dropavelPor);
        consumiveis.add(proprio);
    }

    //#################################
    // Modificar Item Consumivel
    void ModificarItemConsumivel(ArrayList<ItemConsumivel> consumiveis)
    {
        String prompt;
        String prompt_mod;
        String dado;
        String id_str;
        int cont;
        int id_int;
        int valor;
        boolean dado_bol;


        boolean modificar = true;
        while (modificar) {
        if(consumiveis.size() == 0)
        {
            System.out.println("Não há itens cadastrados...");
            modificar = false;
        }
        else {
            // Contador se torna o indice dos itens.
            cont = 0;
            for (ItemConsumivel x : consumiveis) {
                System.out.println();
                System.out.printf("%s - %s", cont, x.getNome());
                cont += 1;
            }
            System.out.println();
            id_str = RetornaStr("Qual item você quer modificar? (S para voltar)");
            if(id_str.equals("s") || id_str.equals("S"))
            {
                modificar = false;
            }
            else {
                try {
                    id_int = Integer.parseInt(id_str);
                    try {
                        boolean flag = true;
                        while (flag)
                        {
                            System.out.println();
                            System.out.printf("Nome: %s",consumiveis.get(id_int).getNome());
                            System.out.println();
                            System.out.printf("Descrição: %s",consumiveis.get(id_int).getDescricao());
                            System.out.println();
                            System.out.printf("Preço: %s", consumiveis.get(id_int).getValor());
                            System.out.println();
                            System.out.printf("Item de inicio: %s", consumiveis.get(id_int).getPodeIniciar());
                            System.out.println();
                            System.out.printf("Dropavel por: %s", consumiveis.get(id_int).getDropavelPor());
                            System.out.println();
                            consumiveis.get(id_int).DescreverAtributosModificador();
                            System.out.println();
                            System.out.println("\n1. Nome\n2. Descrição\n3. HP | 4. MP\n5. Valor\n6. FOR | 7. AGI | 8. SOR"+
                                    " | 9. INT | 10. PER\n11. Item de inicio.\n12. Dropavel por\nS. Terminar edição");
                            System.out.println();

                            prompt = RetornaStr("O que você quer modificar?");
                            switch (prompt)
                            {
                                case "1":
                                    prompt_mod = "Digite um novo nome para "+consumiveis.get(id_int).getNome();
                                    dado = RetornaStr(prompt_mod);
                                    consumiveis.get(id_int).setNome(dado);
                                    break;
                                case "2":
                                    dado = RetornaStr("Escreva uma nova descrição.");
                                    consumiveis.get(id_int).setDescricao(dado);
                                    break;
                                case "3":
                                    valor = RetornaInt("Digite um novo valor para a vitalidade.");
                                    consumiveis.get(id_int).setVitalidade(valor);
                                    break;
                                case "4":
                                    valor = RetornaInt("Digite um novo valor para o mana.");
                                    consumiveis.get(id_int).setMana(valor);
                                    break;
                                case "5":
                                    valor = RetornaIntPositivo("Digite um novo preço.");
                                    consumiveis.get(id_int).setValor(valor);
                                    break;
                                case "6":
                                    valor = RetornaInt("Digite um novo valor para Força.");
                                    consumiveis.get(id_int).setForca(valor);
                                    break;
                                case "7":
                                    valor = RetornaInt("Digite um novo valor para Agilidade.");
                                    consumiveis.get(id_int).setAgilidade(valor);
                                    break;
                                case "8":
                                    valor = RetornaInt("Digite um novo valor para Sorte");
                                    consumiveis.get(id_int).setSorte(valor);
                                    break;
                                case "9":
                                    valor = RetornaInt("Digite um novo valor para Inteligência");
                                    consumiveis.get(id_int).setInteligencia(valor);
                                    break;
                                case "10":
                                    valor = RetornaInt("Digite um novo valor para Percepção");
                                    consumiveis.get(id_int).setPercepcao(valor);
                                    break;
                                case "11":
                                    dado_bol = RetornaBool("Item de inicio?");
                                    consumiveis.get(id_int).setPodeIniciar(dado_bol);
                                    break;
                                case "12":
                                    dado = DropavelPor();
                                    consumiveis.get(id_int).setDropavelPor(dado);
                                    break;
                                case "s":
                                case "S":
                                    flag = false;
                                    break;
                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Erro:Esse item não existe.");
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Erro: Opção inválida.");
                }
            }
            }
        }
    }

    //#################################
    // Remover Item Consumivel
    void RemoverItemConsumivel(ArrayList<ItemConsumivel> consumiveis)
    {
        String confirma;
        String str_id;
        int id_int;
        int cont;

        boolean modificar = true; // Deixa verdadeiro o flag de modificaão
        while (modificar){
            // Verifica se a lista de consumiveis esta vázia ou n.
            if(consumiveis.size() == 0)
            {
                System.out.println("Não há itens cadastrados...");
                modificar = false;
            }
            else{
                cont = 0;
                for(ItemConsumivel x:consumiveis)
                {
                    System.out.println();
                    System.out.printf("%s - %s",cont, x.getNome());
                    cont += 1;
                }
                System.out.println();
                str_id = RetornaStr("Qual item você quer deletar? (S para Voltar)");
                if(str_id.equals("s") || str_id.equals("S"))
                {
                    modificar = false;
                }
                else
                {
                    try {
                        id_int = Integer.parseInt(str_id);
                        System.out.printf("Você está prestes a deletar '%s'", consumiveis.get(id_int).getNome());
                        System.out.println();
                        boolean confirmar = true;
                        while (confirmar) {
                            confirma = RetornaStr("Deletar?(S/N)");
                            switch (confirma)
                            {
                                case "s":
                                case "S":
                                    consumiveis.remove(id_int);
                                    System.out.println("Item removido com sucesso.");
                                    confirmar = false;
                                    break;
                                case "n":
                                case "N":
                                    confirmar = false;
                                    break;
                                default:
                                    System.out.println("Opção inválida...");
                                    break;
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Erro: Id inválido, tente novamente.");
                    }
                }
            }
        }
    }

    //#################################
    // Criar Nova Arma
    void CriarItemArma(ArrayList<ItemArma> armas)
    {
        String nome = RetornaStr("Qual o nome da arma?");
        String descricao = RetornaStr("Descreva a arma.");
        String tipo = RetornaTipoArma();
        int ataque = RetornaIntPositivo("Qual o ataque base da arma?");
        int valor = RetornaIntPositivo("Qual o preço da arma");
        int forca = RetornaInt("Quanto afeta a força?");
        int agilidade = RetornaInt("Quanto afeta a agilidade?");
        int sorte = RetornaInt("Quanto afeta a sorte?");
        int inteligencia = RetornaInt("Quanto afeta a inteligência?");
        int percepcao = RetornaInt("Quanto afeta a percepção?");
        boolean podeIniciar = RetornaBool("Item de inicio?");
        String dropavelPor = DropavelPor();
        ItemArma propria = new ItemArma(nome,descricao,tipo,ataque,valor,forca,agilidade,sorte,inteligencia,percepcao,
                podeIniciar, dropavelPor);
        armas.add(propria);
    }

    //#################################
    // Modificar Item Arma
    void ModificarItemArma(ArrayList<ItemArma> armas)
    {
        int cont;
        int id_int;
        int valor;

        boolean modificar = true;
        while (modificar) {
            if(armas.size() == 0)
            {
                System.out.println("Não há armas cadastradas...");
                modificar = false;
            }
            else {
                String prompt;
                String prompt_mod;
                String dado;
                String id_str;
                boolean dado_bol;

                // Contador se torna o indice dos itens.
                cont = 0;
                for (ItemArma x : armas) {
                    System.out.println();
                    System.out.printf("%s - %s", cont, x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual arma você quer modificar? (S para voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {
                            boolean flag = true;
                            while (flag)
                            {
                                System.out.println();
                                System.out.printf("Nome: %s",armas.get(id_int).getNome());
                                System.out.println();
                                System.out.printf("Descrição: %s",armas.get(id_int).getDescricao());
                                System.out.println();
                                System.out.printf("Preço: %s", armas.get(id_int).getValor());
                                System.out.println();
                                System.out.printf("Tipo: %s", armas.get(id_int).getTipo());
                                System.out.println();
                                System.out.printf("Arma de inicio de jogo: %s", armas.get(id_int).getPodeIniciar());
                                System.out.println();
                                System.out.printf("Dropavel por %s", armas.get(id_int).getDropavelPor());
                                System.out.println();
                                armas.get(id_int).DescreverAtributosModificador();
                                System.out.println();
                                System.out.println("\n1. Nome\n2. Descrição\n3. Tipo | 4. ATK\n5. Valor\n6. FOR | 7. AGI | 8. SOR"+
                                        " | 9. INT | 10. PER\n11. Item de inicio\n12. Dropavel por\nS. Terminar Edição. ");
                                prompt = RetornaStr("O que você quer modificar?");
                                switch (prompt)
                                {
                                    case "1":
                                        prompt_mod = "Digite um novo nome para "+armas.get(id_int).getNome();
                                        dado = RetornaStr(prompt_mod);
                                        armas.get(id_int).setNome(dado);
                                        break;
                                    case "2":
                                        dado = RetornaStr("Escreva uma nova descrição.");
                                        armas.get(id_int).setDescricao(dado);
                                        break;
                                    case "3":
                                        dado = RetornaTipoArma();
                                        armas.get(id_int).setTipo(dado);
                                        break;
                                    case "4":
                                        valor = RetornaInt("Digite um novo valor para o ataque.");
                                        armas.get(id_int).setAtaque(valor);
                                        break;
                                    case "5":
                                        valor = RetornaIntPositivo("Digite um novo preço.");
                                        armas.get(id_int).setValor(valor);
                                        break;
                                    case "6":
                                        valor = RetornaInt("Digite um novo valor para Força.");
                                        armas.get(id_int).setForca(valor);
                                        break;
                                    case "7":
                                        valor = RetornaInt("Digite um novo valor para Agilidade.");
                                        armas.get(id_int).setAgilidade(valor);
                                        break;
                                    case "8":
                                        valor = RetornaInt("Digite um novo valor para Sorte");
                                        armas.get(id_int).setSorte(valor);
                                        break;
                                    case "9":
                                        valor = RetornaInt("Digite um novo valor para Inteligência");
                                        armas.get(id_int).setInteligencia(valor);
                                        break;
                                    case "10":
                                        valor = RetornaInt("Digite um novo valor para Percepção");
                                        armas.get(id_int).setPercepcao(valor);
                                        break;
                                    case "11":
                                        dado_bol = RetornaBool("Pode escolher essa arma no inicio do jogo?");
                                        armas.get(id_int).setPodeIniciar(dado_bol);
                                        break;
                                    case "12":
                                        dado = DropavelPor();
                                        armas.get(id_int).setDropavelPor(dado);
                                        break;
                                    case "s":
                                    case "S":
                                        flag = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                        break;
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Erro:Esse item não existe.");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Erro: Opção inválida.");
                    }
                }
            }
        }
    }

    //#################################
    // Remover Arma
    void RemoverItemArma(ArrayList<ItemArma> armas)
    {
        String id_str;
        int id_int;
        int cont;
        boolean modificar = true;
        while (modificar){
            if(armas.size() == 0)
            {
                System.out.println("Não há armas cadastradas..");
                modificar = false;
            }
            else
            {
                cont = 0;
                for(ItemArma x:armas)
                {
                    System.out.println();
                    System.out.printf("%s - %s",cont,x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual arma você gostaria de modificar?(S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                    {
                      modificar = false;
                    }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {

                            String confirma;

                            boolean confirmar = true;

                            System.out.printf("Você está prestes a deletar '%s'", armas.get(id_int).getNome());
                            System.out.println();
                            while (confirmar) {
                                confirma = RetornaStr("Deletar?(S/N)");
                                switch (confirma) {
                                    case "s":
                                    case "S":
                                        System.out.println("Entrada deletada");
                                        armas.remove(id_int);
                                        confirmar = false;
                                        break;
                                    case "n":
                                    case "N":
                                        confirmar = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um item com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }

    //######################################################
    // Criador de Armadura
    void CriarItemArmadura(ArrayList<ItemArmadura> armaduras)
    {
        String nome = RetornaStr("Qual o nome da armadura?");
        String descricao = RetornaStr("Descreva a armadura");
        int defesa = RetornaIntPositivo("Quanto de defesa?");
        int valor = RetornaIntPositivo("Qual o preço base da armadura?");
        int forca = RetornaInt("Quanto afeta a força?");
        int agilidade = RetornaInt("Quanto afeta a agilidade?");
        int sorte = RetornaInt("Quanto afeta a sorte?");
        int inteligencia = RetornaInt("Quanto afeta a inteligência?");
        int percepcao = RetornaInt("Quanto afeta a percepção?");
        boolean podeIniciar = RetornaBool("Armadura pode ser escolhida no inicio do jogo?");
        String dropavelPor = DropavelPor();
        ItemArmadura propria = new ItemArmadura(nome,descricao,defesa,valor,forca,agilidade,
                sorte,inteligencia,percepcao, podeIniciar,dropavelPor);
        armaduras.add(propria);
    }

    //#################################
    // Modificar Item Armadura
    void ModificarItemArmadura(ArrayList<ItemArmadura> armaduras)
    {
        String prompt;
        String prompt_mod;
        String dado;
        String id_str;
        boolean dado_bol;

        int cont;
        int id_int;
        int valor;

        boolean modificar = true;
        while (modificar) {
            if(armaduras.size() == 0)
            {
                System.out.println("Não há armaduras cadastradas...");
                modificar = false;
            }
            else {

                // Contador se torna o indice dos itens.
                cont = 0;
                for (ItemArmadura x : armaduras) {
                    System.out.println();
                    System.out.printf("%s - %s", cont, x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual armadura você quer modificar? (S para voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {
                            boolean flag = true;
                            while (flag)
                            {
                                System.out.println();
                                System.out.printf("Nome: %s",armaduras.get(id_int).getNome());
                                System.out.println();
                                System.out.printf("Descrição: %s",armaduras.get(id_int).getDescricao());
                                System.out.println();
                                System.out.printf("Preço: %s", armaduras.get(id_int).getValor());
                                System.out.println();
                                System.out.printf("Armadura de inicio de jogo: %s", armaduras.get(id_int).getPodeIniciar());
                                System.out.println();
                                System.out.printf("Dropavel por: %s",armaduras.get(id_int).getDropavelPor());
                                System.out.println();
                                armaduras.get(id_int).DescreverAtributosModificador();
                                System.out.println();
                                System.out.println("\n1. Nome\n2. Descrição\n3. DEF \n4. Valor\n5. FOR | 6. AGI | 7. SOR"+
                                        " | 8. INT | 9. PER\n10. Item de inicio.\n11. Dropavel Por\nS. Terminar Edição. ");
                                prompt = RetornaStr("O que você quer modificar?");
                                switch (prompt)
                                {
                                    case "1":
                                        prompt_mod = "Digite um novo nome para "+armaduras.get(id_int).getNome();
                                        dado = RetornaStr(prompt_mod);
                                        armaduras.get(id_int).setNome(dado);
                                        break;
                                    case "2":
                                        dado = RetornaStr("Escreva uma nova descrição.");
                                        armaduras.get(id_int).setDescricao(dado);
                                        break;
                                    case "3":
                                        valor = RetornaInt("Digite um novo valor para a defesa.");
                                        armaduras.get(id_int).setDefesa(valor);
                                        break;
                                    case "4":
                                        valor = RetornaIntPositivo("Digite um novo preço.");
                                        armaduras.get(id_int).setValor(valor);
                                        break;
                                    case "5":
                                        valor = RetornaInt("Digite um novo valor para Força.");
                                        armaduras.get(id_int).setForca(valor);
                                        break;
                                    case "6":
                                        valor = RetornaInt("Digite um novo valor para Agilidade.");
                                        armaduras.get(id_int).setAgilidade(valor);
                                        break;
                                    case "7":
                                        valor = RetornaInt("Digite um novo valor para Sorte");
                                        armaduras.get(id_int).setSorte(valor);
                                        break;
                                    case "8":
                                        valor = RetornaInt("Digite um novo valor para Inteligência");
                                        armaduras.get(id_int).setInteligencia(valor);
                                        break;
                                    case "9":
                                        valor = RetornaInt("Digite um novo valor para Percepção");
                                        armaduras.get(id_int).setPercepcao(valor);
                                        break;
                                    case "10":
                                        dado_bol = RetornaBool("Armadura pode ser escolhida no inicio do jogo?");
                                        armaduras.get(id_int).setPodeIniciar(dado_bol);
                                        break;
                                    case "11":
                                        dado = DropavelPor();
                                        armaduras.get(id_int).setDropavelPor(dado);
                                        break;
                                    case "s":
                                    case "S":
                                        flag = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                        break;
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Erro:Esse item não existe.");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Erro: Opção inválida.");
                    }
                }
            }
        }
    }
    //#################################
    // Remover Armadura Existente
    void RemoverItemArmadura(ArrayList<ItemArmadura> armaduras)
    {
        String id_str;
        int id_int;
        int cont;
        boolean modificar = true;
        while (modificar){
            if(armaduras.size() == 0)
            {
                System.out.println("Não há armaduras cadastradas..");
                modificar = false;
            }
            else
            {
                cont = 0;
                for(ItemArmadura x:armaduras)
                {
                    System.out.println();
                    System.out.printf("%s - %s",cont,x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual armadura você gostaria de modificar?(S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {

                            String confirma;

                            boolean confirmar = true;


                            System.out.printf("Você está prestes a deletar '%s'", armaduras.get(id_int).getNome());
                            System.out.println();
                            while (confirmar) {
                                confirma = RetornaStr("Deletar?(S/N)");
                                switch (confirma) {
                                    case "s":
                                    case "S":
                                        System.out.println("Entrada deletada");
                                        armaduras.remove(id_int);
                                        confirmar = false;
                                        break;
                                    case "n":
                                    case "N":
                                        confirmar = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um item com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }

    //###################
    // Criador de Classe
    void CriarClasse(ArrayList<Classe> classes)
    {
        String nome = RetornaStr("Qual o nome da classe?");
        String descricao = RetornaStr("Descreva a classe");
        int vitalidade = RetornaIntPositivo("Quanto de vida possui essa classe?");
        int mana = RetornaIntPositivo("Quanto de mana possui essa classe?");
        int forca = RetornaIntPositivo("Quanto de forca possui essa classe?");
        int agilidade = RetornaIntPositivo("Quanto de agilidade possui essa classe?");
        int sorte = RetornaIntPositivo("Quanto de sorte possui essa classe?");
        int inteligencia = RetornaIntPositivo("Quanto de inteligência possui essa classe?");
        int percepcao = RetornaIntPositivo("Quanto de percepção possui essa classe?");
        Classe propria = new Classe(nome,descricao,vitalidade,mana,forca,agilidade,sorte,inteligencia,percepcao);
        classes.add(propria);
    }

    //#################################
    // Modificar Classe
    void ModificarClasse(ArrayList<Classe> classes)
    {
        int cont;
        int id_int;
        int valor;

        boolean modificar = true;
        while (modificar) {
            if(classes.size() == 0)
            {
                System.out.println("Não há classes cadastradas...");
                modificar = false;
            }
            else {
                String prompt;
                String prompt_mod;
                String dado;
                String id_str;


                // Contador se torna o indice dos itens.
                cont = 0;
                for (Classe x : classes) {
                    System.out.println();
                    System.out.printf("%s - %s", cont, x.getCla_nome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual classe você quer modificar? (S para voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {
                            boolean flag = true;
                            while (flag)
                            {
                                System.out.println();
                                System.out.printf("Nome: %s",classes.get(id_int).getCla_nome());
                                System.out.println();
                                System.out.printf("Descrição: %s",classes.get(id_int).getCla_lore());
                                System.out.println();
                                System.out.printf("HP: %s | MP: %s",classes.get(id_int).getHp_jogador(),
                                        classes.get(id_int).getMp_jogador());
                                System.out.println();
                                classes.get(id_int).DescreverAtributosModificador();
                                System.out.println();
                                System.out.println("O que você quer modificar?");
                                System.out.println("\n1. Nome\n2. Descrição\n3. HP| 4. MP\n5. FOR | 6. AGI | 7. SOR"+
                                        " | 8. INT | 9. PER\nS. Terminar Edição. ");
                                System.out.print(">> ");
                                prompt = entrada.nextLine();
                                switch (prompt)
                                {
                                    case "1":
                                        prompt_mod = "Digite um novo nome para "+classes.get(id_int).getCla_nome();
                                        dado = RetornaStr(prompt_mod);
                                        classes.get(id_int).setCla_nome(dado);
                                        break;
                                    case "2":
                                        dado = RetornaStr("Escreva uma nova lore.");
                                        classes.get(id_int).setCla_lore(dado);
                                        break;
                                    case "3":
                                        valor = RetornaIntPositivo("Digite um novo valor para a vitalidade.");
                                        classes.get(id_int).setHp_jogador(valor);
                                        break;
                                    case "4":
                                        valor = RetornaIntPositivo("Digite um novo valor para o mana.");
                                        classes.get(id_int).setMp_jogador(valor);
                                        break;
                                    case "5":
                                        valor = RetornaIntPositivo("Digite um novo valor para Força.");
                                        classes.get(id_int).setForca(valor);
                                        break;
                                    case "6":
                                        valor = RetornaIntPositivo("Digite um novo valor para Agilidade.");
                                        classes.get(id_int).setAgilidade(valor);
                                        break;
                                    case "7":
                                        valor = RetornaIntPositivo("Digite um novo valor para Sorte");
                                        classes.get(id_int).setSorte(valor);
                                        break;
                                    case "8":
                                        valor = RetornaIntPositivo("Digite um novo valor para Inteligência");
                                        classes.get(id_int).setInteligencia(valor);
                                        break;
                                    case "9":
                                        valor = RetornaIntPositivo("Digite um novo valor para Percepção");
                                        classes.get(id_int).setPercepcao(valor);
                                        break;
                                    case "s":
                                    case "S":
                                        flag = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                        break;
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Erro:Esse item não existe.");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Erro: Opção inválida.");
                    }
                }
            }
        }
    }
    //#################################
    // Remover Classe
    void RemoverClasse(ArrayList<Classe> classes)
    {
        String id_str;
        int id_int;
        int cont;
        boolean modificar = true;
        while (modificar){
            if(classes.size() == 0)
            {
                System.out.println("Não há classes cadastradas..");
                modificar = false;
            }
            else
            {
                cont = 0;
                for(Classe x:classes)
                {
                    System.out.println();
                    System.out.printf("%s - %s",cont,x.getCla_nome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual classe você gostaria de modificar?(S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {

                            String confirma;

                            boolean confirmar = true;


                            System.out.printf("Você está prestes a deletar '%s'", classes.get(id_int).getCla_nome());
                            System.out.println();
                            while (confirmar) {
                                confirma = RetornaStr("Deletar?(S/N)");
                                switch (confirma) {
                                    case "s":
                                    case "S":
                                        System.out.println("Entrada deletada");
                                        classes.remove(id_int);
                                        confirmar = false;
                                        break;
                                    case "n":
                                    case "N":
                                        confirmar = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um item com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }

    //###########################
    // Criador de monstro normal
    void CriarMonstroNormal(ArrayList<MonstroNormal> monstros)
    {
        String nome = RetornaStr("Qual o nome do monstro?");
        String lore = RetornaStr("Qual a história do monstro?");
        String tipo_monstro = RetornaTipoMonstro("Qual o tipo do monstro?");
        int vitalidade = RetornaIntPositivo("Qual o HP do monstro?");
        String nome_ataque = RetornaStr("Qual o nome do ataque do monstro?");
        int dano_ataque = RetornaIntPositivo("Qual o dano base do monstro?");
        int forca = RetornaIntPositivo("Qual o atributo de força do monstro?");
        int agilidade = RetornaIntPositivo("Qual o atributo de agilidade do monstro?");
        int sorte = RetornaIntPositivo("Qual o atributo de sorte do monstro?");
        int inteligencia = RetornaIntPositivo("Qual o atributo de inteligência do monstro?");
        int percepcao = RetornaIntPositivo("Qual o atributo de percepção do monstro?");

        MonstroNormal proprio = new MonstroNormal(nome,lore,tipo_monstro, vitalidade,nome_ataque,
                dano_ataque,forca,agilidade,sorte,inteligencia,percepcao);
        monstros.add(proprio);
    }

    //#############################
    // Modificador de monstros normais
    void ModificarMonstroNormal(ArrayList<MonstroNormal> monstros)
    {
        String dado_str;
        String prompt;
        String id_str;
        int id_int;
        int dado_int;
        int cont;
        boolean modificar = true;
        while (modificar)
        {
            if(monstros.size() == 0)
            {
                System.out.println("Não há monstros cadastrados");
                modificar = false;
            }
            else
            {
                cont = 0;
                for(MonstroNormal x:monstros)
                {
                    System.out.println();
                    System.out.printf(" %s - %s", cont, x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual monstro você gostaria de modificar?(S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {
                            boolean flag = true;
                            while (flag) {
                                System.out.println();
                                System.out.printf("Nome: %s", monstros.get(id_int).getNome());
                                System.out.println();
                                System.out.printf("Descrição: %s", monstros.get(id_int).getDescricao());
                                System.out.println();
                                System.out.printf("HP: %s | ATK: %s", monstros.get(id_int).getVitalidade(),
                                        monstros.get(id_int).getAtk_dano());
                                System.out.println();
                                System.out.printf("Nome do ataque: %s",
                                        monstros.get(id_int).getAtk_nome());
                                monstros.get(id_int).AtributosMonstro();
                                System.out.println();
                                System.out.println("O que você quer modificar?");
                                System.out.println("\n1. Nome\n2. Descrição\n3. HP\n4. Nome ataque" +
                                        "\n5. Dano do ATK\n6. FOR | 7. AGI | 8. SOR | 9. INT" +
                                        " | 10. PER\nS. Terminar Edição. ");
                                prompt = RetornaStr("O que você quer modificar?");
                                switch (prompt) {
                                    case "1":
                                        prompt = "Digite um novo nome para " + monstros.get(id_int).getNome();
                                        dado_str = RetornaStr(prompt);
                                        monstros.get(id_int).setNome(dado_str);
                                        break;
                                    case "2":
                                        dado_str = RetornaStr("Escreva uma nova lore.");
                                        monstros.get(id_int).setDescricao(dado_str);
                                        break;
                                    case "3":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para a vitalidade.");
                                        monstros.get(id_int).setVitalidade(dado_int);
                                        break;
                                    case "4":
                                        dado_str = RetornaStr("Digite um novo nome para o ataque.");
                                        monstros.get(id_int).setAtk_nome(dado_str);
                                        break;
                                    case "5":
                                        dado_int = RetornaIntPositivo("Digite o dano base do monstro.");
                                        monstros.get(id_int).setAtk_dano(dado_int);
                                        break;
                                    case "6":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Força.");
                                        monstros.get(id_int).setForca(dado_int);
                                        break;
                                    case "7":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Agilidade.");
                                        monstros.get(id_int).setAgilidade(dado_int);
                                        break;
                                    case "8":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Sorte.");
                                        monstros.get(id_int).setSorte(dado_int);
                                        break;
                                    case "9":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Inteligência.");
                                        monstros.get(id_int).setInteligencia(dado_int);
                                        break;
                                    case "10":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Percepção");
                                        monstros.get(id_int).setPercepcao(dado_int);
                                        break;
                                    case "s":
                                    case "S":
                                        flag = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um monstro com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }
    // ##################
    // REMOVER MONSTRO NORMAL
    void RemoverMonstroNormal(ArrayList<MonstroNormal> monstros)
    {
        String id_str;
        int id_int;
        int cont;
        boolean modificar = true;
        while (modificar)
        {
            if(monstros.size() == 0)
            {
                System.out.println("Não há monstros cadastrados");
                modificar = false;
            }
            else
            {
                cont = 0;
                for(MonstroNormal x:monstros)
                {
                    System.out.println();
                    System.out.printf(" %s - %s", cont, x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual monstro você gostaria de modificar?(S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {

                            String confirma;

                            boolean confirmar = true;


                            System.out.printf("Você está prestes a deletar '%s'", monstros.get(id_int).getNome());
                            System.out.println();
                            while (confirmar) {
                                confirma = RetornaStr("Deletar?(S/N)");
                                switch (confirma) {
                                    case "s":
                                    case "S":
                                        System.out.println("Entrada deletada");
                                        monstros.remove(id_int);
                                        confirmar = false;
                                        break;
                                    case "n":
                                    case "N":
                                        confirmar = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um item com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }
    //###########################
    // Criador de monstro normal
    void CriarMonstroChefe(ArrayList<MonstroChefe> chefes)
    {
        String nome = RetornaStr("Qual o nome do monstro?");
        String lore = RetornaStr("Qual a história do monstro?");
        String tipo_monstro = RetornaTipoMonstro("Qual o tipo do chefe?");
        int vitalidade = RetornaIntPositivo("Qual o HP do monstro?");
        String nome_ataque = RetornaStr("Qual o nome do ataque do monstro?");
        int dano_ataque = RetornaIntPositivo("Qual o dano base do monstro?");
        int forca = RetornaIntPositivo("Qual o atributo de força do monstro?");
        int agilidade = RetornaIntPositivo("Qual o atributo de agilidade do monstro?");
        int sorte = RetornaIntPositivo("Qual o atributo de sorte do monstro?");
        int inteligencia = RetornaIntPositivo("Qual o atributo de inteligência do monstro?");
        int percepcao = RetornaIntPositivo("Qual o atributo de percepção do monstro?");

        MonstroChefe proprio = new MonstroChefe(nome,lore,tipo_monstro, vitalidade,nome_ataque,
                dano_ataque,forca,agilidade,sorte,inteligencia,percepcao);
        chefes.add(proprio);
    }

    //#############################
    // Modificador de monstros chefes
    void ModificarMonstroChefe(ArrayList<MonstroChefe> chefes)
    {
        String dado_str;
        String prompt;
        String id_str;
        int id_int;
        int dado_int;
        int cont;
        boolean modificar = true;
        while (modificar)
        {
            if(chefes.size() == 0)
            {
                System.out.println("Não há monstros cadastrados");
                modificar = false;
            }
            else
            {
                cont = 0;
                for(MonstroChefe x:chefes)
                {
                    System.out.println();
                    System.out.printf(" %s - %s", cont, x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual monstro você gostaria de modificar?(S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {
                            boolean flag = true;
                            while (flag) {
                                System.out.println();
                                System.out.printf("Nome: %s", chefes.get(id_int).getNome());
                                System.out.println();
                                System.out.printf("Descrição: %s", chefes.get(id_int).getDescricao());
                                System.out.println();
                                System.out.printf("HP: %s | ATK: %s", chefes.get(id_int).getVitalidade(),
                                        chefes.get(id_int).getAtk_dano());
                                System.out.println();
                                System.out.printf("Nome do ataque: %s",
                                        chefes.get(id_int).getAtk_nome());
                                chefes.get(id_int).AtributosMonstro();
                                System.out.println();
                                System.out.println("\n1. Nome\n2. Descrição\n3. HP\n4. Nome ataque" +
                                        "\n5. Dano do ATK\n6. FOR | 7. AGI | 8. SOR | 9. INT" +
                                        " | 10. PER\nS. Terminar Edição. ");
                                prompt = RetornaStr("O que você quer modificar?");
                                switch (prompt) {
                                    case "1":
                                        dado_str = RetornaStr("Digite um novo nome para "+ chefes.get(id_int).getNome());
                                        chefes.get(id_int).setNome(dado_str);
                                        break;
                                    case "2":
                                        dado_str = RetornaStr("Escreva uma nova lore.");
                                        chefes.get(id_int).setDescricao(dado_str);
                                        break;
                                    case "3":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para a vitalidade.");
                                        chefes.get(id_int).setVitalidade(dado_int);
                                        break;
                                    case "4":
                                        dado_str = RetornaStr("Digite um novo nome para o ataque.");
                                        chefes.get(id_int).setAtk_nome(dado_str);
                                        break;
                                    case "5":
                                        dado_int = RetornaIntPositivo("Digite o dano base do monstro.");
                                        chefes.get(id_int).setAtk_dano(dado_int);
                                        break;
                                    case "6":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Força.");
                                        chefes.get(id_int).setForca(dado_int);
                                        break;
                                    case "7":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Agilidade.");
                                        chefes.get(id_int).setAgilidade(dado_int);
                                        break;
                                    case "8":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Sorte.");
                                        chefes.get(id_int).setSorte(dado_int);
                                        break;
                                    case "9":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Inteligência.");
                                        chefes.get(id_int).setInteligencia(dado_int);
                                        break;
                                    case "10":
                                        dado_int = RetornaIntPositivo("Digite um novo valor para Percepção");
                                        chefes.get(id_int).setPercepcao(dado_int);
                                        break;
                                    case "s":
                                    case "S":
                                        flag = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um monstro com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }
    // ##################
    // REMOVER MONSTRO CHEFE
    void RemoverMonstroChefe(ArrayList<MonstroChefe> chefes)
    {
        String id_str;
        int id_int;
        int cont;
        boolean modificar = true;
        while (modificar)
        {
            if(chefes.size() == 0)
            {
                System.out.println("Não há monstros cadastrados");
                modificar = false;
            }
            else
            {
                cont = 0;
                for(MonstroChefe x:chefes)
                {
                    System.out.println();
                    System.out.printf(" %s - %s", cont, x.getNome());
                    cont += 1;
                }
                System.out.println();
                id_str = RetornaStr("Qual chefe você gostaria de remover?(S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else {
                    try {
                        id_int = Integer.parseInt(id_str);
                        try {

                            String confirma;

                            boolean confirmar = true;


                            System.out.printf("Você está prestes a deletar '%s'", chefes.get(id_int).getNome());
                            System.out.println();
                            while (confirmar) {
                                confirma = RetornaStr("Deletar?(S/N)");
                                switch (confirma) {
                                    case "s":
                                    case "S":
                                        System.out.println("Entrada deletada");
                                        chefes.remove(id_int);
                                        confirmar = false;
                                        break;
                                    case "n":
                                    case "N":
                                        confirmar = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um item com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }
    //######################################
    // Cria Magia
    void CriarMagia(ArrayList<Magia> magias)
    {
        String nome = RetornaStr("Qual o nome da magia?");
        String descricao = RetornaStr("Descreva a magia");
        int valor = RetornaIntPositivo("Qual o preço da magia?");
        int ataque = RetornaIntPositivo("Qual o dano base da magia?");
        int custo_mana = RetornaIntPositivo("Qual consome de mana a magia?");
        int vitalidade = RetornaInt("Quanto afeta a vitalidade?");
        boolean magiadeInicio = RetornaBool("Pode escolher essa magia no início do jogo?");
        String dropavelPor = DropavelPor();
        Magia propria = new Magia(nome,descricao,valor,ataque,custo_mana,vitalidade,magiadeInicio,dropavelPor);
        magias.add(propria);
    }
    // ###############################
    // Modificar Magia
    void ModificarMagia(ArrayList<Magia> magias)
    {
        String dado_str;
        int dado_int;
        boolean dado_bol;

        boolean modificar = true;
        while (modificar)
        {
            if (magias.size() == 0)
            {
                System.out.println("Não há magias cadastradas");
                modificar = false;
            }
            else
            {
                int cont = 0;
                for(Magia magia: magias)
                {
                    System.out.println();
                    System.out.println("Magias.");
                    System.out.printf("%s - %s",cont,magia.getNome());
                    cont += 1;
                }
                System.out.println();
                String id_str = RetornaStr("Qual magia você gostaria de modificar? (S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else
                {
                    try
                    {
                        int id_int = Integer.parseInt(id_str);
                        boolean mod_magia = true;
                        while (mod_magia)
                        {
                            System.out.println();
                            System.out.printf("Nome: %s", magias.get(id_int).getNome());
                            System.out.println();
                            System.out.printf("Descrição: %s", magias.get(id_int).getDescricao());
                            System.out.println();
                            System.out.printf("Magia de inicio: %s", magias.get(id_int).isMagiadeInicio());
                            System.out.println();
                            System.out.printf("Dropavel por: %s", magias.get(id_int).getDropavelPor());
                            System.out.println("1. Nome\n2. Descrição\n3. Valor\n4. ATK\n5.Custo Mana\n6. Vitalidade" +
                                    "\n7. Magia de inicio\n8. Dropavel por\nS. Terminar Edição.");
                            String prompt = RetornaStr("O que você gostaria de modificar?");
                            switch (prompt){
                                case "s":
                                case "S":
                                    mod_magia = false;
                                    break;
                                case "1":
                                    dado_str = RetornaStr("Digite um novo nome para '"+
                                            magias.get(id_int).getNome()+"'");
                                    magias.get(id_int).setNome(dado_str);
                                    break;
                                case "2":
                                    dado_str = RetornaStr("Digite uma nova descrição.");
                                    magias.get(id_int).setDescricao(dado_str);
                                    break;
                                case "3":
                                    dado_int = RetornaIntPositivo("Qual o preço base?");
                                    magias.get(id_int).setValor(dado_int);
                                    break;
                                case "4":
                                    dado_int = RetornaIntPositivo("Qual o dano base?");
                                    magias.get(id_int).setAtaque(dado_int);
                                    break;
                                case "5":
                                    dado_int = RetornaIntPositivo("Qual o custo de mana da magia?");
                                    magias.get(id_int).setCusto_mana(dado_int);
                                    break;
                                case "6":
                                    dado_int = RetornaInt("Quanto afeta a Vitalidade?");
                                    magias.get(id_int).setVitalidade(dado_int);
                                    break;
                                case "7":
                                    dado_bol = RetornaBool("Magia pode ser escolhida no inicio do jogo?");
                                    magias.get(id_int).setMagiadeInicio(dado_bol);
                                    break;
                                case "8":
                                    dado_str = DropavelPor();
                                    magias.get(id_int).setDropavelPor(dado_str);
                                    break;
                                default:
                                    System.out.println("Erro: Opção inválida.");
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Erro: Entre o indice do item.");
                    }
                }
            }
        }
    }
    // #############################################
    // Remover Magia
    void RemoverMagia(ArrayList<Magia> magias)
    {
        boolean modificar = true;
        while (modificar)
        {
            if (magias.size() == 0)
            {
                System.out.println("Não há magias cadastradas");
                modificar = false;
            }
            else
            {
                int cont = 0;
                for(Magia magia: magias)
                {
                    System.out.println();
                    System.out.println("Magias.");
                    System.out.printf("%s - %s",cont,magia.getNome());
                    cont += 1;
                }
                System.out.println();
                String id_str = RetornaStr("Qual magia você gostaria de remover? (S para Voltar)");
                if(id_str.equals("s") || id_str.equals("S"))
                {
                    modificar = false;
                }
                else
                {
                    try
                    {
                        int id_int = Integer.parseInt(id_str);
                        try {


                            String confirma;
                            boolean confirmar = true;


                            System.out.printf("Você está prestes a deletar '%s'", magias.get(id_int).getNome());
                            System.out.println();
                            while (confirmar) {
                                confirma = RetornaStr("Deletar?(S/N)");
                                switch (confirma) {
                                    case "s":
                                    case "S":
                                        System.out.println("Entrada deletada");
                                        magias.remove(id_int);
                                        confirmar = false;
                                        break;
                                    case "n":
                                    case "N":
                                        confirmar = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Erro: Não há entrada com esse indice.");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Erro: Entre o indice do item.");
                    }
                }
            }
        }
    }
    void CriarEvento(ArrayList<Evento> eventos)
    {
        System.out.println("Você pode usar identificadores para deixar o evento dinâmico.");
        System.out.println("NOME_JOGADOR(ex. 'Ciclano') | NOME_PESSOA(ex. 'Anacleto') | HORARIO (ex.'de manhã')");
        System.out.println("LUGAR(ex. 'uma escola/um hotel') | FRASE(ex. 'Tem coisa que não faz sentido né?') ");
        System.out.println("OBJETOS(Objetos ex. 'uma cadeira/um tijolo') | NOMES_MUSICA(ex. 'God is Good')");
        System.out.println();
        String nome_evento = RetornaStr("Coloque um nome identificador do Evento.");
        String proposta_evento = RetornaStr("Crie a situação do Evento.");
        String texto_resultado_bom = RetornaStr("Crie o final do evento caso o resultado seja favorecivel.");
        String texto_resultado_ruim = RetornaStr("Crie o final do evento caso o resultado seja negativo.");
        String texto_resultado_ignorar = RetornaStr("Crie o final do evento caso o jogador ignore o evento.");
        String aceitar = RetornaStr("Escreva a proposta de aceitar o Evento (S).");
        String recusar = RetornaStr("Escreva a proposta de recusar o Evento (N).");
        System.out.println("No Resultado Positivo...");
        int ganhar_moedas = RetornaIntPositivo("Quantas moedas o jogador deve ganhar.");
        int ganhar_vitalidade = RetornaIntPositivo("Quanto HP o jogador deve recuperar.");
        int ganhar_mana = RetornaIntPositivo("Quanto de MP o jogador deve recuperar.");
        int ganhar_forca = RetornaIntPositivo("Quanto de FOR deve incrementar.");
        int ganhar_agilidade = RetornaIntPositivo("Quanto de AGI deve incrementar.");
        int ganhar_sorte = RetornaIntPositivo("Quanto de SOR deve incrementar.");
        int ganhar_inteligencia = RetornaIntPositivo("Quanto de INT deve incrementar.");
        int ganhar_percepcao = RetornaIntPositivo("Quanto de PER deve incrementar.");
        System.out.println("No Resultados Negativo...");
        int perder_moedas = RetornaIntPositivo("Quantas moedas o jogador deve perder.");
        int perder_vitalidade = RetornaIntPositivo("Quanto de dano o jogador deve receber.");
        int perder_mana = RetornaIntPositivo("Quanto de mana o jogador gasta.");
        int perder_forca = RetornaIntPositivo("Quanto de FOR o jogador perde.");
        int perder_agilidade = RetornaIntPositivo("Quanto de AGI o jogador perde.");
        int perder_sorte = RetornaIntPositivo("Quanto de SOR o jogador perde.");
        int perder_inteligencia = RetornaIntPositivo("Quanto de INT o jogador perde.");
        int perder_percepcao = RetornaIntPositivo("Quanto de PER o jogador perde.");
        Evento prorio = new Evento(nome_evento,proposta_evento, texto_resultado_bom,
                texto_resultado_ruim,
                texto_resultado_ignorar, aceitar,recusar, ganhar_moedas,
        perder_moedas, ganhar_vitalidade, perder_vitalidade,  ganhar_mana, perder_mana,
         ganhar_forca,  perder_forca,  ganhar_agilidade,  perder_agilidade,  ganhar_sorte,
         perder_sorte,  ganhar_inteligencia,  perder_inteligencia,  ganhar_percepcao,
         perder_percepcao);
        System.out.println("Evento criado com sucesso.");
        eventos.add(prorio);
    }

    void ModificarEvento(ArrayList<Evento> eventos)
    {
        if (eventos.size() == 0)
        {
            System.out.println("Não há eventos cadastrados.");
        }
        else {
            int id_evento_int;
            int cont = 0;
            for (Evento x : eventos) {
                System.out.printf("%s - %s", cont, x.getNome_evento());
                System.out.println();
                cont += 1;
            }
            boolean modificar = true;
            while (modificar) {
                String id_evento = RetornaStr("Escolha um evento para Modificar ou S para voltar.");
                if (id_evento.equals("s") || id_evento.equals("S")) {
                    modificar = false;
                } else {
                    try {
                        id_evento_int = Integer.parseInt(id_evento);
                        try {

                            String opcao;
                            String dado;
                            int dado_int;
                            boolean modificar_evento = true;
                            while (modificar_evento) {
                                System.out.println();
                                System.out.printf("Titulo: %s", eventos.get(id_evento_int).getNome_evento());
                                System.out.println();
                                System.out.println("##########################################################");
                                System.out.println("História:");
                                System.out.println();
                                String proposta_evento = eventos.get(id_evento_int).getProposta_evento();
                                proposta_evento = printBonito(proposta_evento);
                                System.out.println(proposta_evento);
                                System.out.println("##########################################################");
                                System.out.println("Texto Resultado Positivo:");
                                System.out.println();
                                String texto_bom = eventos.get(id_evento_int).getTexto_resultado_bom();
                                texto_bom = printBonito(texto_bom);
                                System.out.println(texto_bom);
                                System.out.println("##########################################################");
                                System.out.println("Texto Resultado Ruim:");
                                System.out.println();
                                String texto_ruim = eventos.get(id_evento_int).getTexto_resultado_ruim();
                                texto_ruim = printBonito(texto_ruim);
                                System.out.println(texto_ruim);
                                System.out.println("##########################################################");
                                System.out.println("Texto Ignorar Evento:");
                                String ignorar_evento = eventos.get(id_evento_int).getTexto_resultado_ignorar();
                                ignorar_evento = printBonito(ignorar_evento);
                                System.out.println(ignorar_evento);
                                System.out.println("##########################################################");
                                System.out.printf("Prompt Aceitar: %s",eventos.get(id_evento_int).getAceitar());
                                System.out.println();
                                System.out.println("##########################################################");
                                System.out.printf("Prompt Recusar:%s",eventos.get(id_evento_int).getRecusar());
                                System.out.println();
                                System.out.println("##########################################################");
                                System.out.println("Resultados Positivos:");
                                eventos.get(id_evento_int).ExibirResultadosPositivos();
                                System.out.println("##########################################################");
                                System.out.println("Resultados Negativos:");
                                eventos.get(id_evento_int).ExibirResultadosNegativos();
                                System.out.println("1. Titulo\n2. História\n3. Prompt Aceitar\n4. Prompt Recusar" +
                                        "\n5. Texto Resultado Positivo\n6. Texto Resultado Negativo" +
                                        "\n7. Texto Ignorar Evento\n8. Valores do Resultado Positivo" +
                                        "\n9. Valores do Resultado Negativo\nS. Terminar Edição.");
                                opcao = RetornaStr("O que você quer modificar?");
                                switch (opcao)
                                {
                                    case "1":
                                        dado = RetornaStr("Coloque um titulo(OBS serve apenas " +
                                                "para identificar o evento na hora de edição).");
                                        eventos.get(id_evento_int).setNome_evento(dado);
                                        break;
                                    case "2":
                                        dado = RetornaStr("Escreva uma nova história");
                                        eventos.get(id_evento_int).setProposta_evento(dado);
                                        break;
                                    case "3":
                                        dado = RetornaStr("Escreva um prompt para opção de aceitar (S).");
                                        eventos.get(id_evento_int).setAceitar(dado);
                                        break;
                                    case "4":
                                        dado = RetornaStr("Escreva um prompt para opção de recusar (N).");
                                        eventos.get(id_evento_int).setRecusar(dado);
                                        break;
                                    case "5":
                                        dado = RetornaStr("Digite o texto do resultado positivo.");
                                        eventos.get(id_evento_int).setTexto_resultado_bom(dado);
                                        break;
                                    case "6":
                                        dado = RetornaStr("Digite o texto do resuldado negativo.");
                                        eventos.get(id_evento_int).setTexto_resultado_ruim(dado);
                                        break;
                                    case "7":
                                        dado = RetornaStr("Digite o texto quando o jogador ignora,");
                                        eventos.get(id_evento_int).setTexto_resultado_ignorar(dado);
                                        break;
                                    case "8":
                                        dado_int = RetornaIntPositivo("Moedas que ganha:");
                                        eventos.get(id_evento_int).setGanhar_moedas(dado_int);
                                        dado_int = RetornaIntPositivo("Vitalidade que recupera:");
                                        eventos.get(id_evento_int).setGanhar_vitalidade(dado_int);
                                        dado_int = RetornaIntPositivo("Mana que recupera:");
                                        eventos.get(id_evento_int).setGanhar_mana(dado_int);
                                        dado_int = RetornaIntPositivo("Forca que incrementa:");
                                        eventos.get(id_evento_int).setGanhar_forca(dado_int);
                                        dado_int = RetornaIntPositivo("Agilidade que incrementa:");
                                        eventos.get(id_evento_int).setGanhar_agilidade(dado_int);
                                        dado_int = RetornaIntPositivo("Sorte que incrementa:");
                                        eventos.get(id_evento_int).setGanhar_sorte(dado_int);
                                        dado_int = RetornaIntPositivo("Inteligência que incrementa:");
                                        eventos.get(id_evento_int).setGanhar_inteligencia(dado_int);
                                        dado_int = RetornaIntPositivo("Percepção que incrementa:");
                                        eventos.get(id_evento_int).setGanhar_percepcao(dado_int);
                                        break;
                                    case "9":
                                        dado_int = RetornaIntPositivo("Moedas que perde:");
                                        eventos.get(id_evento_int).setPerder_moedas(dado_int);
                                        dado_int = RetornaIntPositivo("Dano que recebe:");
                                        eventos.get(id_evento_int).setPerder_vitalidade(dado_int);
                                        dado_int = RetornaIntPositivo("Gasto de mana que recebe:");
                                        eventos.get(id_evento_int).setPerder_mana(dado_int);
                                        dado_int = RetornaIntPositivo("Forca que perde:");
                                        eventos.get(id_evento_int).setPerder_forca(dado_int);
                                        dado_int = RetornaIntPositivo("Agilidade que perde:");
                                        eventos.get(id_evento_int).setPerder_agilidade(dado_int);
                                        dado_int = RetornaIntPositivo("Sorte que perde:");
                                        eventos.get(id_evento_int).setPerder_sorte(dado_int);
                                        dado_int = RetornaIntPositivo("Inteligência que perde:");
                                        eventos.get(id_evento_int).setPerder_inteligencia(dado_int);
                                        dado_int = RetornaIntPositivo("Percepção que perde:");
                                        eventos.get(id_evento_int).setPerder_percepcao(dado_int);
                                        break;
                                    case "s":
                                    case "S":
                                        modificar_evento = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um item com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }

    void RemoverEvento(ArrayList<Evento> eventos)
    {
        if(eventos.size() == 0)
        {
            System.out.println("Não há eventos cadastrados");
        }
        else
        {
            int id_evento_int;
            int cont = 0;
            for (Evento x : eventos) {
                System.out.printf("%s - %s", cont, x.getNome_evento());
                System.out.println();
                cont += 1;
            }
            boolean deletar = true;
            while (deletar) {
                String id_evento = RetornaStr("Escolha um evento para Deletar ou S para voltar.");
                if (id_evento.equals("s") || id_evento.equals("S")) {
                    deletar = false;
                } else {
                    try {
                        id_evento_int = Integer.parseInt(id_evento);
                        try {

                            String confirma;

                            boolean confirmar = true;


                            System.out.printf("Você está prestes a deletar '%s'",
                                    eventos.get(id_evento_int).getNome_evento());
                            System.out.println();
                            while (confirmar) {
                                confirma = RetornaStr("Deletar?(S/N)");
                                switch (confirma) {
                                    case "s":
                                    case "S":
                                        System.out.println("Entrada deletada");
                                        eventos.remove(id_evento_int);
                                        confirmar = false;
                                        break;
                                    case "n":
                                    case "N":
                                        confirmar = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro: Não há um item com esse ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Insira um valor inteiro");
                    }
                }
            }
        }
    }

    void AdicionarNomeAleatorio(ArrayList<String> string_aleatorias)
    {
        String coisa = RetornaStr("Escreva o nome da coisa para adicionar");
        string_aleatorias.add(coisa);
        System.out.println("Entrada adicionada!");
        System.out.println();
    }
    void DeletarNomeAleatorio(ArrayList<String> strings_aleatorias)
    {
        if(strings_aleatorias.size() == 0)
        {
            System.out.println("Não há strings cadastradas nessa lista...");
        }
        else {
            boolean deletando = true;
            while (deletando) {
                if(strings_aleatorias.size() == 0)
                {
                    deletando = false;
                }
                else {
                    int cont = 0;
                    for (String x : strings_aleatorias) {
                        System.out.printf("%s - %s", cont, x);
                        System.out.println();
                        cont += 1;
                    }
                    String id_string_str = RetornaStr("Selecione uma String ou tecle S para voltar.");
                    if (id_string_str.equals("s") || id_string_str.equals("S")) {
                        deletando = false;
                    } else {
                        try {
                            int id_string = Integer.parseInt(id_string_str);
                            if (id_string <= strings_aleatorias.size() - 1) {
                                strings_aleatorias.remove(id_string);
                                System.out.println("String deletada.");
                                System.out.println();
                            } else {
                                System.out.println("Indice inválido.");
                            }
                        } catch (Exception e) {
                            System.out.println("Indice inválido.");
                        }
                    }
                }
            }
        }
    }


    // ##############################################
    // DADOS RETORNAVEIS
    // Retorna um inteiro. Para modificar valores númericos do RPG
    int RetornaInt(String prompt)
    {
        String dado;
        int inteiro;
        System.out.println(prompt);
        while (true) {
            System.out.print(">> ");
            dado = entrada.nextLine();

            try {
                inteiro = Integer.parseInt(dado);
                return inteiro;
            }
            catch (Exception e)
            {
                System.out.println("Erro: Por favor, insira um número inteiro.");
            }
        }
    }
    int RetornaIntPositivo(String prompt)
    {
        String dado;
        int inteiro;
        System.out.println(prompt);
        while (true) {
            System.out.print(">> ");
            dado = entrada.nextLine();
            try {
                inteiro = Integer.parseInt(dado);
                if(inteiro < 0) {
                    System.out.println("Esse valor não pode ser negativo...");
                }
                else {
                    return inteiro;
                }
            }
            catch (Exception e)
            {
                System.out.println("Erro: Por favor, insira um número inteiro.");
            }
        }
    }
    // Retorna String, para quando for modificar um dado to tipo String
    String RetornaStr(String prompt)
    {
        String texto;
        System.out.println(prompt);
        System.out.print(">> ");
        texto = entrada.nextLine();
        return texto;
    }
    String DropavelPor()
    {
        String dado;
        while (true) {
            System.out.println("Esse item é dropavel por qual tipo de inimigo?");
            System.out.println("1. TODOS | 2. MONSTROS | 3. CHEFES");
            System.out.print(">> ");
            dado = entrada.nextLine();
            switch (dado)
            {
                case "1":
                    return "TODOS";
                case "2":
                    return "MONSTROS";
                case "3":
                    return "CHEFES";
                default:
                    System.out.println("Erro: Opção inválida");
                    break;
            }
        }
    }
    // Retorna Tipo para Arma
    String RetornaTipoArma()
    {
        String dado;
        while (true) {
            System.out.println("Qual o tipo da arma?");
            System.out.println("1. FOR (Física)| 2. PER (Alcance)");
            System.out.print(">> ");
            dado = entrada.nextLine();
            switch (dado)
            {
                case "1":
                    return "FOR";
                case "2":
                    return "PER";
                default:
                    System.out.println("Escolha inválida, por favor escolha uma opção correta");
                    break;
            }
        }
    }
    // Retorna Tipo para Monstro
    String RetornaTipoMonstro(String prompt)
    {
        String dado;
        while (true) {
            System.out.println(prompt);
            System.out.println("1. FOR (Física)| 2. INT (Inteligência) | 3. PER (Percepção)");
            System.out.print(">> ");
            dado = entrada.nextLine();
            switch (dado)
            {
                case "1":
                    return "FOR";
                case "2":
                    return "INT";
                case "3":
                    return "PER";
                default:
                    System.out.println("Escolha inválida, por favor escolha uma opção correta");
                    break;
            }
        }
    }
    // Retorna Tipo Booleano
    boolean RetornaBool(String prompt)
    {
        String dado;
        while (true) {
            System.out.println(prompt);
            System.out.println();
            System.out.println("S - Verdadeiro / N - Falso");
            System.out.println();
            System.out.print(">> ");
            dado = entrada.nextLine();
            switch (dado){
                case "s":
                case "S":
                    return true;
                case "n":
                case "N":
                    return false;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    String printBonito(String texto)
    {
        texto = texto.replaceAll("(.{64})", "$1\n");
        return texto;
    }

}
