package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.UsuarioLogic;
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
public class UsuarioBean extends CrudBean<Usuario>{

  @Inject
  private UsuarioLogic usuarioLogic;

    public UsuarioBean() {
        super(Usuario.class);
    }
  
}
