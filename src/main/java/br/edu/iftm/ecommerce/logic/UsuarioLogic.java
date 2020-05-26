package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.UsuarioDAO;
import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements CrudLogic<Usuario> {
   
    @Inject
    private UsuarioDAO dao;

    @Override
    public Usuario salvar(Usuario usuario) {
        usuario = dao.salvar(usuario);
        return usuario;
    }

    @Override
    public void deletar(Usuario usuario) {
        dao.remover(usuario.getId());
    }

    @Override
    public List<Usuario> buscar(Usuario entidade) {
        return dao.listar();
    }

}
