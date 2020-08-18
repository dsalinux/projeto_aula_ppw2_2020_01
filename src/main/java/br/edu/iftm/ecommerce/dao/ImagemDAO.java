package br.edu.iftm.ecommerce.dao;

import br.edu.iftm.ecommerce.entity.Imagem;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;

public class ImagemDAO extends GenericDAO<Imagem, Integer>{

    @Override
    public Imagem salvar(Imagem entidade) throws ErroSistemaException {
        entidade = super.salvar(entidade);
        return entidade;
    }
    
    
}
