import infra.DAO;
import modelo.Cliente;
import modelo.Produto;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainClass {

    private static Cliente clienteLogin = null;

    public static void main(String[] args) {
        System.out.println("====== TESTE JPA ======");
        int op = 0 ;
        int perfil = 0;
        do {
            if (perfil == 0){
                op = 0;
                perfil = menuPerfil();
            }

            if (perfil == 2) {
                op = menuFuncionario();
                switch (op) {
                    case 1 -> inserirProduto();
                    case 2 -> listarProduto();
                    case 3 -> excluirProduto();
                    case 4 -> atualizarProduto();
                    case 5 -> listarCliente();
                    case 99 -> perfil = 0;
                }
            }

            if (perfil == 1 && clienteLogin == null){
                op = menuClienteLogin();
                switch (op){
                    case 1 -> ClienteLogin();
                    case 2 -> ClienteCadastro();
                    case 99 -> perfil = 0;
                }
            }
            if (perfil == 1 && clienteLogin != null){
                op = menuCliente();
                switch (op){
                    case 1 -> inserirCarrinho();
                    case 99 -> ClienteLogout();
                }
            }
        }
        while (op != 0);
        System.out.println("-> Finalizado. ");
    }

    public static int menuFuncionario(){
        System.out.println("------ MENU FUNCIONARIO ------");
        System.out.println("1 - Inserir Produto.");
        System.out.println("2 - Listar Produto.");
        System.out.println("3 - Excluir Produto.");
        System.out.println("4 - Atualizar Produto.");
        System.out.println("5 - Listar Clientes.");
        System.out.println("0 - Finalizar.");
        System.out.println("99 - Voltar.");
        System.out.println("Informe a opcao: ");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    public static int menuCliente(){
        System.out.println("------ MENU CLIENTE ------");
        System.out.println("1 - Comprar.");
        System.out.println("2 - Visualizar carrinho.");
        System.out.println("3 - Finalizar compra.");
        System.out.println("99 - Voltar.");
        System.out.println("0 - Finalizar.");
        System.out.println("Informe a opcao: ");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    public static int menuClienteLogin(){
        System.out.println("------ MENU CLIENTE ------");
        System.out.println("1 - Login.");
        System.out.println("2 - Cadastrar.");
        System.out.println("99 - Voltar.");
        System.out.println("0 - Finalizar.");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    public static void ClienteLogin(){
        System.out.println("------ Login do Cliente ------");
        // listar os clientes
        // informar o id do cliente
        // clientelogin = recebe o cliente.
    }

    public static void ClienteLogout(){
        System.out.println("Logout Cliente.");
        clienteLogin = null;
    }

    public static int menuPerfil(){
        System.out.println("------ MENU PERFIL ------");
        System.out.println("1 - Cliente.");
        System.out.println("2 - Funcionario.");
        System.out.println("0 - Finalizar.");
        System.out.println("Escolha o perfil: ");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }
// ------------ Funcionario ------------------------------
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

    public static void listarCliente(){
        System.out.println("--> Listando clientes: ");
        DAO<Cliente> dao = new DAO<>(Cliente.class);
        List<Cliente> clientes = dao.obterTodos();
        for (Cliente c: clientes){
            System.out.println(c);
        }
    }
//------------- Cliente ----------------------------------
    public static void inserirCarrinho(){
    }

    public static void ClienteCadastro(){
        System.out.println("--> Cadastro Cliente: ");

        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe seu nome: ");
        String nome = entrada.nextLine();
        System.out.println("Informe seu CPF: ");
        String cpf = entrada.nextLine();
        System.out.println("Informe seu Email: ");
        String email = entrada.nextLine();
        Cliente novoCliente = new Cliente(nome,cpf,email);
        DAO<Cliente> dao = new DAO<>(Cliente.class);
        dao.incluirAtomico(novoCliente);
    }
}
