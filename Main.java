package Foda;


public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        ConstrutorJogo construtor;
        ListaClasse classes;

        ListaMonstro monstros;
        ListaChefe chefes;
        while (true) { // Sempre verdadeiro
            menu.OpcoesMenu();
            System.out.print(">> ");
            construtor = menu.Menu_P();
            if (construtor != null) {
                System.out.printf("%s %s", construtor.modo, construtor.n_niveis);
            }
            else {
                System.out.println("Vazio");
            }
        }
    }
}