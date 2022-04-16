package ignorados;

import infra.DAO;
import modelo.Produto;

public class NovoProduto {

    public static void main(String[] args) {

        Produto produto = new Produto("Lapis 2",5.90, 1L);

        DAO<Produto> dao = new DAO<>(Produto.class);
        dao.abrirTransition().incluirTransition(produto).fecharTransition();
        dao.fechar();
    }
}
