import infra.DAO;
import modelo.Produto;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("====== TESTE JPA ======");
        int op;
        do {
            op = menu();
            switch (op){
                case 1 -> inserirProduto();
                case 2 -> listarProduto();
                case 3 -> excluirProduto();
                case 4 -> atualizarProduto();
            }
        }
        while (op != 0);
        System.out.println("-> Finalizado. ");
    }

    public static int menu(){
        System.out.println("------ MENU ------");
        System.out.println("1 - Inserir Produto.");
        System.out.println("2 - Listar Produto.");
        System.out.println("3 - Excluir Produto.");
        System.out.println("4 - Atualizar Produto.");
        System.out.println("0 - Finalizar.");
        System.out.println("Informe a opcao: ");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    public static void inserirProduto(){
        System.out.println("--> Inserindo produto: ");

        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o nome do produto: ");
        String nome = entrada.nextLine();
        System.out.println("Informe o preco do produto: ");
        Double preco = entrada.nextDouble();
        entrada.nextLine();
        System.out.println("Informe a qtd do produto: ");
        Long qtd = entrada.nextLong();
        Produto produto = new Produto(nome,preco,qtd);

        DAO<Produto> dao = new DAO<>(Produto.class);
        dao.incluirAtomico(produto);
        dao.fechar();
    }

    public static void listarProduto(){
        System.out.println("--> Listando produtos: ");
        DAO<Produto> dao = new DAO<>(Produto.class);
        List<Produto> produtos = dao.obterTodos();
        for (Produto p: produtos){
            System.out.println(p);
        }
    }

    public static void excluirProduto(){

    }

    public static void atualizarProduto(){

    }
}
