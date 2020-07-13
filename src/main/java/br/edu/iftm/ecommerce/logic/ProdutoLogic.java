package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.ProdutoDAO;
import br.edu.iftm.ecommerce.entity.Produto;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.List;
import javax.inject.Inject;

public class ProdutoLogic implements CrudLogic<Produto>{

    @Inject
    private ProdutoDAO dao;
    
    @Override
    public Produto salvar(Produto entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.salvar(entidade);
    }

    @Override
    public void deletar(Produto entidade) throws ErroSistemaException, ErroNegocioException {
        dao.remover(entidade.getId());
    }

    @Override
    public List<Produto> buscar(Produto entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.listar();
    }

    @Override
    public Produto buscarPorId(Produto entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.buscarPorId(entidade.getId());
    }
    
}
