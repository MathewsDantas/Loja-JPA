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
        System.out.println("--> Excluindo produto: ");
        DAO<Produto> dao = new DAO<>(Produto.class);
        listarProduto();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Insira o Id para excluir: ");
        long id = entrada.nextLong();

        dao.ExcluirId(id);
    }

    public static void atualizarProduto(){
        System.out.println("--> Atualizando produto: ");
        DAO<Produto> dao = new DAO<>(Produto.class);
        listarProduto();

        Scanner entrada = new Scanner(System.in);
        System.out.println("Insira o Id para atualizar: ");
        long id = entrada.nextLong();
        entrada.nextLine();
        Produto produto = dao.obterPorId(id);
        dao.abrirTransition();
        System.out.println("Informe o nome do produto a ser atualizado: ");
        String nome = entrada.nextLine();
        produto.setNome(nome);

        System.out.println("Informe o preco do produto a ser atualizado: ");
        Double preco = entrada.nextDouble();
        produto.setPreco(preco);

        System.out.println("Informe a qtd do produto a ser atualizado: ");
        Long qtd = entrada.nextLong();
        produto.setQtd(qtd);

        dao.AtualizarId(produto);
    }
}
