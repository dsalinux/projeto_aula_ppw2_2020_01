package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.dao.UsuarioDAO;
import br.edu.iftm.ecommerce.entity.Usuario;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements CrudLogic<Usuario>{
  
  private ArrayList<Usuario> usuarios = new ArrayList<>();
  private int id = 1;
  
  @Inject
  private UsuarioDAO dao;
  
  @Override
  public Usuario salvar(Usuario usuario)throws ErroSistemaException, ErroNegocioException{
      if("".equals(usuario.getNome())){
          throw new ErroNegocioException("Nome do usuário é obrigatório");
      }
      usuario = dao.salvar(usuario);
      return usuario;
  }
  
  
  @Override
  public void deletar(Usuario usuario) throws ErroSistemaException, ErroNegocioException{
     dao.remover(usuario.getId());
  }
  
  @Override
  public List<Usuario> buscar(Usuario entidade) throws ErroSistemaException, ErroNegocioException{
    return dao.listar();
  }


  
}
