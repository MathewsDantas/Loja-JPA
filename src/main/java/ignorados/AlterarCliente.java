package ignorados;

import modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlterarCliente {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Cliente cliente = em.find(Cliente.class, 2L);
        cliente.setNome("Alt1");
        em.merge(cliente);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
