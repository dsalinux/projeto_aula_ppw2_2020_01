package br.edu.iftm.ecomerce.bean;

import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class IndexBean implements Serializable {
    
    private MensagemBean mensagemBean;
    
    private String mensagem = "Funcionou estamos no JAVA....";

    public String getMensagem() {
        Usuario usuario = new Usuario();
        usuario.setNome("Danilo");
        return usuario.getNome();
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}
