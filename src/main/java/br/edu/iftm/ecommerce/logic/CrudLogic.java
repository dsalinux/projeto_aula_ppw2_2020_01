/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author PICHAU
 */
public interface CrudLogic<E> extends Serializable{
    
    public E salvar (E entidade) throws ErroSistemaException, ErroNegocioException;
    public void deletar (E entidade) throws ErroSistemaException, ErroNegocioException;
    public List<E> buscar(E entidade) throws ErroSistemaException, ErroNegocioException;
    
}
