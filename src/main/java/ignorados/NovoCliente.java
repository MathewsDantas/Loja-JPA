package ignorados;

import modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NovoCliente {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
        EntityManager em = emf.createEntityManager();

        Cliente novoCliente = new Cliente("Test3","123.456.112-13","EMAIL2");

        em.getTransaction().begin();
        em.persist(novoCliente);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
