package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.ProdutoDAO;
import br.edu.iftm.ecommerce.entity.Produto;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import br.edu.iftm.ecommerce.util.lib.Assert;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import br.edu.iftm.ecommerce.util.Transacao;

public class ProdutoLogic implements CrudLogic<Produto>{

    @Inject
    private ProdutoDAO dao;
    
    @Override
    @Transacao
    public Produto salvar(Produto entidade) throws ErroSistemaException, ErroNegocioException {
        if(Assert.isStringNullOrEmpty(entidade.getNome())){
            throw new ErroNegocioException("Nome do produto é obrigatório.");
        }
        if(Assert.isStringNullOrEmpty(entidade.getDescricao())){
            throw new ErroNegocioException("Descrição do produto é obrigatória.");
        }
        if(Assert.isStringNullOrEmpty(entidade.getDetalhes())){
            throw new ErroNegocioException("Detalhes do produto é obrigatório.");
        }
        if(Assert.isValueNullOrZero(entidade.getValor())){
            throw new ErroNegocioException("Valor do produto é obrigatório e deve ser maior que zero.");
        }
        if(Assert.isNull(entidade.getCategoria())){
            throw new ErroNegocioException("Categoria do produto é obrigatório");
        }
        if(Assert.isNull(entidade.getMarca())){
            throw new ErroNegocioException("Marca do produto é obrigatório");
        }
        if(entidade.getDataCriacao() == null) {
            entidade.setDataCriacao(new Date());
        }
        return dao.salvar(entidade);
    }

    @Override
    @Transacao
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
