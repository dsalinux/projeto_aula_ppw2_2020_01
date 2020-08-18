/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.CategoriaDAO;
import br.edu.iftm.ecommerce.entity.Categoria;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.List;
import javax.inject.Inject;
import br.edu.iftm.ecommerce.util.Transacao;

/**
 *
 * @author danilo
 */
public class CategoriaLogic implements CrudLogic<Categoria>{

    @Inject
    private CategoriaDAO dao;
    
    @Override
    @Transacao
    public Categoria salvar(Categoria entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.salvar(entidade);
    }

    @Override
    @Transacao
    public void deletar(Categoria entidade) throws ErroSistemaException, ErroNegocioException {
        dao.remover(entidade.getId());
    }

    @Override
    public List<Categoria> buscar(Categoria entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.listar();
    }

    @Override
    public Categoria buscarPorId(Categoria entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.buscarPorId(entidade.getId());
    }
    
}
