package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioLogic implements CrudLogic<Usuario>{
  
  private List<Usuario> usuarios = new ArrayList<>();
  private int id = 1;
  
  @Override
  public Usuario salvar(Usuario usuario){
    usuario.setId(id++);
    usuarios.add(usuario);
      return null;
  }
  
  
  @Override
  public void deletar(Usuario usuario) {
    usuarios.remove(usuario);
  }
  
  @Override
  public List<Usuario> buscar(Usuario entidade){
    return usuarios;
  }


  
}
