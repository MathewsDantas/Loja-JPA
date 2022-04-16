package ignorados;

import modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ObterCliente {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
        EntityManager em = emf.createEntityManager();

        //listando apenas 1
        Cliente cliente = em.find(Cliente.class,4L);//1L..2L..10L -> o L é devido ao tipo long.
        System.out.println(cliente.getNome());

        // -------------------------------------------------
        //listando varios clientes
        String jpql = "select c from Cliente c";
        TypedQuery<Cliente> query = em.createQuery(jpql,Cliente.class);
        query.setMaxResults(5);//limita a quantidade de resultado.

        List<Cliente> clientes = query.getResultList();
        for (Cliente c: clientes){
            System.out.println(c);
        }
        // -------------------------------------------------
        em.close();
        emf.close();
    }
}
