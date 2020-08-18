/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.MarcaDAO;
import br.edu.iftm.ecommerce.entity.Marca;
import br.edu.iftm.ecommerce.util.Transacao;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author danilo
 */
public class MarcaLogic implements CrudLogic<Marca> {

    @Inject
    private MarcaDAO dao;

    @Override
    @Transacao
    public Marca salvar(Marca entidade) throws ErroSistemaException, ErroNegocioException {
        if (entidade.getNome() == null || "".equals(entidade.getNome().trim())) {
            throw new ErroNegocioException("Por favor informe o nome da marca.");
        }
        return dao.salvar(entidade);
    }

    @Override
    @Transacao
    public void deletar(Marca entidade) throws ErroSistemaException, ErroNegocioException {
        entidade = dao.buscarPorId(entidade.getId());
        if (entidade.getProdutos() != null && entidade.getProdutos().size() > 0) {
            String produtoPlural = entidade.getProdutos().size() > 1 ? " produto " : " produtos ";
            throw new ErroNegocioException("A marca " + entidade.getNome() + " possuí " + entidade.getProdutos().size() + produtoPlural + " associados a marca e não pode ser excluído.");
        }
        dao.remover(entidade.getId());
    }

    @Override
    public List<Marca> buscar(Marca entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.listar();
    }

    @Override
    public Marca buscarPorId(Marca entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.buscarPorId(entidade.getId());
    }

}
