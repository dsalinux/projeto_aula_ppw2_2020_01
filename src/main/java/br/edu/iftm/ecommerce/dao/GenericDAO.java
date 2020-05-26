package br.edu.iftm.ecommerce.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDAO<E, ID> implements Serializable{
    
    private EntityManager em;
    
    private EntityManager criarEntityManager(){
        EntityManagerFactory emf = 
                Persistence.createEntityManagerFactory("br.edu.iftm.ecommerce.jpa");
        EntityManager em = emf.createEntityManager();
        return em;
    }
    
    public E salvar(E entidade){
        getEntityManager().getTransaction().begin();
        entidade = getEntityManager().merge(entidade);
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
        return entidade;
    }
    
    public void remover(ID id){
        getEntityManager().getTransaction().begin();
        E entidade = getEntityManager().find(getEntityClass(), id);
        getEntityManager().remove(entidade);
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
    }
    
    public E buscarPorId(ID id){
        EntityManager em = criarEntityManager();
        return em.find(getEntityClass(), id);
    }
    
    public List<E> listar(){
        List<E> entidades = getEntityManager().createQuery("from "+getEntityClass().getName()).getResultList();
        getEntityManager().close();
        return entidades;
    }
    
    public Class<E> getEntityClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        
        return (Class<E>) paramType.getActualTypeArguments()[0];        
    }
    
    public EntityManager getEntityManager(){
        if(this.em == null || !this.em.isOpen()){
            this.em = criarEntityManager();
        }
        return em;
    }

}
