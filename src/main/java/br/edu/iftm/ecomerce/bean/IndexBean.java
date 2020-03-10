package br.edu.iftm.ecomerce.bean;

import br.edu.iftm.ecommerce.entity.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class IndexBean implements Serializable {
    
    @Inject
    private MensagemBean mensagemBean;
    
    private String mensagem = "Funcionou estamos no JAVA....";

    public String getMensagem() {
        return mensagemBean != null?mensagemBean.mensagem:"Erro";
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}
