package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioLogic implements Serializable{
  
  private List<Usuario> usuarios = new ArrayList<>();
  private int id = 1;
  
  public void salvar(Usuario usuario){
    usuario.setId(id++);
    usuarios.add(usuario);
  }
  public void deletar(Usuario usuario) {
    usuarios.remove(usuario);
  }
  public List<Usuario> listar(){
    return usuarios;
  }
  
}
