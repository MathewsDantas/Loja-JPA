package ignorados;

import infra.DAO;
import modelo.Produto;

import java.util.List;

public class ObterProduto {
    public static void main(String[] args) {

        DAO<Produto> dao = new DAO<>(Produto.class);

        List<Produto> produtos = dao.obterLimite(2,0);

        for (Produto p: produtos) {
            System.out.println(p);
        }

        dao.fechar();
    }
}
