package br.edu.iftm.ecommerce.dao;

import br.edu.iftm.ecommerce.entity.Usuario;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import org.hibernate.Hibernate;

/**
 * DAO - Data Access Object
 * ORM - Object Relational Model
 * @author danilo
 */
public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

    @Override
    public Usuario buscarPorId(Integer id) throws ErroSistemaException {
        Usuario usuario = super.buscarPorId(id);
        Hibernate.initialize(usuario.getPermissoes());
        return usuario;
    }
   
}
