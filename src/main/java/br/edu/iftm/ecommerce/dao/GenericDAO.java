package br.edu.iftm.ecommerce.dao;

import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject; 
import javax.persistence.EntityManager;

public class GenericDAO<E, ID> implements Serializable {

//    @UserDatabase
    @Inject
    private EntityManager em;

    public E salvar(E entidade) throws ErroSistemaException {
        try {
//            getEntityManager().getTransaction().begin();
            entidade = em.merge(entidade);
//            getEntityManager().getTransaction().commit();
            return entidade;
        } catch (Exception ex) {
//            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
//            getEntityManager().close();
        }
    }

    public void remover(ID id) throws ErroSistemaException {
        try {
//            getEntityManager().getTransaction().begin();
            E entidade = em.find(getEntityClass(), id);
            em.remove(entidade);
//            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
//            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
//            getEntityManager().close();
        }
    }

    public E buscarPorId(ID id) throws ErroSistemaException {
        try {
            return em.find(getEntityClass(), id);
        } catch (Exception ex) {
//            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
//            getEntityManager().close();
        }
        
    }

    public List<E> listar() throws ErroSistemaException {
        try {
            List<E> entidades = em.createQuery("from " + getEntityClass().getName()).getResultList();
//            getEntityManager().close();
            return entidades;
        } catch (Exception ex) {
//            getEntityManager().getTransaction().rollback();
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        } finally {
//            getEntityManager().close();
        }
    }

    public Class<E> getEntityClass() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<E>) paramType.getActualTypeArguments()[0];
//            return genericClass;
    }

}
