package br.edu.iftm.ecommerce.dao;

import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDAO<E, ID> implements Serializable {

    private EntityManager em;

    private EntityManager criarEntityManager() throws ErroSistemaException{
        try {
            EntityManagerFactory emf
                    = Persistence.createEntityManagerFactory("br.edu.iftm.ecommerce.jpa");
            EntityManager em = emf.createEntityManager();
            return em;
        } catch (Exception ex) {
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        }
    }

    public E salvar(E entidade) throws ErroSistemaException {
        try {
            getEntityManager().getTransaction().begin();
            entidade = getEntityManager().merge(entidade);
            getEntityManager().getTransaction().commit();
            return entidade;
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
            getEntityManager().close();
        }
    }

    public void remover(ID id) throws ErroSistemaException {
        try {
            getEntityManager().getTransaction().begin();
            E entidade = getEntityManager().find(getEntityClass(), id);
            getEntityManager().remove(entidade);
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
            getEntityManager().close();
        }
    }

    public E buscarPorId(ID id) throws ErroSistemaException {
        try {
            EntityManager em = criarEntityManager();
            return em.find(getEntityClass(), id);
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
            getEntityManager().close();
        }
        
    }

    public List<E> listar() throws ErroSistemaException {
        try {
            List<E> entidades = getEntityManager().createQuery("from " + getEntityClass().getName()).getResultList();
            getEntityManager().close();
            return entidades;
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
            getEntityManager().close();
        }
    }

    public Class<E> getEntityClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;

        return (Class<E>) paramType.getActualTypeArguments()[0];
    }

    public EntityManager getEntityManager() throws ErroSistemaException{
        if (this.em == null || !this.em.isOpen()) {
            this.em = criarEntityManager();
        }
        return em;
    }

}
