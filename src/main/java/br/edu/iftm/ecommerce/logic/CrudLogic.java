/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.logic;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author PICHAU
 */
public interface CrudLogic<E> extends Serializable{
    
    public E salvar (E entidade);
    public void deletar (E entidade);
    public List<E> buscar(E entidade);
    
}
