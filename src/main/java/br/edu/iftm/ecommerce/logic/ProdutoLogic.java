/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.ProdutoDAO;
import br.edu.iftm.ecommerce.entity.Produto;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author PICHAU
 */
public class ProdutoLogic implements CrudLogic<Produto>{

    @Inject
    private ProdutoDAO dao;
    
    @Override
    public Produto salvar(Produto entidade) {
        return dao.salvar(entidade);
    }

    @Override
    public void deletar(Produto entidade) {
        dao.remover(entidade.getId());
    }

    @Override
    public List<Produto> buscar(Produto entidade) {
        return dao.listar();
    }
    
}
