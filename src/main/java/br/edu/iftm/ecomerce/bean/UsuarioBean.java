package br.edu.iftm.ecomerce.bean;

import br.edu.iftm.ecomerce.logic.UsuarioLogic;
import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter
@Setter
public class UsuarioBean implements Serializable {

  @Inject
  private UsuarioLogic usuarioLogic;
  private Usuario usuario = new Usuario();
  private List<Usuario> usuarios = new ArrayList<>();

  public void adicionar() {
    usuarioLogic.salvar(usuario);
    usuario = new Usuario();
  }

  public void deletar(Usuario usuario){
    usuarioLogic.deletar(usuario);
  }
  
  public void listar() {
    usuarios = usuarioLogic.listar();
  }

}
