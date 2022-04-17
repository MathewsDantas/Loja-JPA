package infra;

import modelo.VendaItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO <T>{

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
    private Class<T> classe;
    private EntityManager em;

    public DAO(){
        this(null);
    }

    public DAO(Class<T> classe) {
        this.classe = classe;
        em = emf.createEntityManager();
    }

    public DAO<T> abrirTransition() {
        em.getTransaction().begin();
        return this;
    }

    public DAO<T> fecharTransition() {
        em.getTransaction().commit();
        return this;
    }

    public DAO<T> incluirTransition(T entidade) {
        em.persist(entidade);
        return this;
    }

    public DAO<T> incluirAtomico(T entidade){
        return this.abrirTransition().incluirTransition(entidade).fecharTransition();
    }

    public T obterPorId(Object id){
        return em.find(classe,id);
    }

    public List<T> obterLimite(int qtde, int deslocamento){
        if (classe == null){
            throw new UnsupportedOperationException("Classe nula.");
        }
        // nome da classe
        String jpql = "select e from " +classe.getName()+ " e";
        TypedQuery<T> query = em.createQuery(jpql,classe);
        query.setMaxResults(qtde);
        query.setFirstResult(deslocamento);
        return query.getResultList();
    }

    public List<T> obterTodos() {
        String jpql = "select e from " + classe.getName()+ " e";
        TypedQuery<T> query = em.createQuery(jpql,classe);
        return query.getResultList();
    }

    public void ExcluirId(Long id){
        T enti = obterPorId(id);
        abrirTransition();
        em.remove(enti);
        fecharTransition();
    }

    public void AtualizarId(T enti){
        em.merge(enti);
        fecharTransition();
    }

    public void fechar(){
        em.close();
    }
}
