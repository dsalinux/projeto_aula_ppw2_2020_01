package br.edu.iftm.ecomerce.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class IndexBean implements Serializable {
    
    @Inject
    private MensagemBean mensagemBean;
    
    private String mensagem = "Funcionou....";

    public String getMensagem() {
        return mensagemBean.mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}
