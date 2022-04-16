package ignorados;

import modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoverCliente {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
        EntityManager em = emf.createEntityManager();

        Cliente cliente = em.find(Cliente.class,3L);

        if (cliente != null){
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }

        em.close();
        emf.close();
    }
}
