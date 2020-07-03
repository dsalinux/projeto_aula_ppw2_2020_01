package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.List;

public interface CrudLogic<E> extends Serializable {
    
    public E salvar(E entidade) throws ErroSistemaException, ErroNegocioException;
    public void deletar(E entidade) throws ErroSistemaException, ErroNegocioException;
    public List<E> buscar(E entidade) throws ErroSistemaException, ErroNegocioException;
    
}
