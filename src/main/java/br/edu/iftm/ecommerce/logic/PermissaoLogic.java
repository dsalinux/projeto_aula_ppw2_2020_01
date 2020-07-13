package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.PermissaoDAO;
import br.edu.iftm.ecommerce.entity.Permissao;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.List;
import javax.inject.Inject;

public class PermissaoLogic implements CrudLogic<Permissao>{

    @Inject
    private PermissaoDAO dao;
    
    @Override
    public Permissao salvar(Permissao entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.salvar(entidade);
    }

    @Override
    public void deletar(Permissao entidade) throws ErroSistemaException, ErroNegocioException {
        dao.remover(entidade.getId());
    }

    @Override
    public List<Permissao> buscar(Permissao entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.listar();
    }

    @Override
    public Permissao buscarPorId(Permissao entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.buscarPorId(entidade.getId());
    }
    
    
}
