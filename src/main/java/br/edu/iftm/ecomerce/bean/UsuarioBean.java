package br.edu.iftm.ecomerce.bean;

import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter @Setter
public class UsuarioBean implements Serializable {
    
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios = new ArrayList<>();
    
    public void adicionar(){
        usuarios.add(usuario);
        usuario = new Usuario();
    }
    
    public List<Usuario> listar(){
        return usuarios;
    }
    
}
