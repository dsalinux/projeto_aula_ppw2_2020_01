/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.dao;

import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author PICHAU
 */
public class GenericDAO<E, ID>  implements Serializable{
    
    private EntityManager em;
    
    private EntityManager criarEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("br.edu.iftm.ecommerce.jpa");
        EntityManager em = emf.createEntityManager();
        return em;
    }
    
    public E salvar (E entidade){
        
        getEntityManager().getTransaction().begin();
        entidade = getEntityManager().merge(entidade); //Pode deixar só o merge, já insere e atualiza.
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
        return entidade;
    }
    
    public void remover (ID id){
        getEntityManager().getTransaction().begin();
        E entidade = getEntityManager().find(getEntityClass(), id);
        //em.persist(usuario);
        getEntityManager().remove(entidade); //Pode deixar só o merge, já insere e atualiza.
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
    
    }
    
    public E buscarPorId(ID id){
        EntityManager em = criarEntityManager();
        return em.find(getEntityClass(), id);
    }
    
    public ArrayList<E> listar(){
        ArrayList<E> entidades = (ArrayList<E>) (ArrayList<Usuario>) getEntityManager().createQuery("from "+getEntityClass().getName()).getResultList();
        getEntityManager().close();
        return entidades;
    }
    
    public Class<E> getEntityClass(){
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
