package br.edu.iftm.ecommerce.logic;

import java.io.Serializable;
import java.util.List;

public interface CrudLogic<E> extends Serializable {
    
    public E salvar(E entidade);
    public void deletar(E entidade);
    public List<E> buscar(E entidade);
    
}
