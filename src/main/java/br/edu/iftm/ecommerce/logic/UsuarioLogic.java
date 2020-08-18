package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.UsuarioDAO;
import br.edu.iftm.ecommerce.entity.Usuario;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import br.edu.iftm.ecommerce.util.Transacao;

public class UsuarioLogic implements CrudLogic<Usuario> {
   
    @Inject
    private UsuarioDAO dao;

    @Override
    @Transacao
    public Usuario salvar(Usuario usuario) throws ErroSistemaException, ErroNegocioException {
        if("".equals(usuario.getNome())){
           throw new ErroNegocioException("Nome do usuário é obrigatório.");
        }
        if(usuario.getDataCadastro() == null && usuario.getId() == null) {
            usuario.setDataCadastro(new Date());
        }
        usuario = dao.salvar(usuario);
        return usuario;
    }

    @Override
    @Transacao
    public void deletar(Usuario usuario) throws ErroSistemaException, ErroNegocioException {
        dao.remover(usuario.getId());
    }

    @Override
    public List<Usuario> buscar(Usuario entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.listar();
    }

    @Override
    public Usuario buscarPorId(Usuario entidade) throws ErroSistemaException, ErroNegocioException {
        return dao.buscarPorId(entidade.getId());
    }
    
    

}
