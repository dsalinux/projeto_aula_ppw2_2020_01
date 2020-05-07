/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



/**
 *
 * @author PICHAU
 */
public class JSFUtil implements Serializable{
    
    public void addMensagem(FacesMessage.Severity severity, String sumario, String detalhe){
        FacesMessage msg = new FacesMessage
        (severity,sumario , detalhe);
    
        FacesContext.getCurrentInstance().addMessage(null, msg);
    
    }
    
    public void addMensagemInfo(String sumario, String detalhe){
        addMensagem(FacesMessage.SEVERITY_INFO, sumario, detalhe);
    }
    public void addMensagemInfo(String detalhe){
        addMensagem(FacesMessage.SEVERITY_INFO, "Info", detalhe);
    }
    public void addMensagemAviso(String sumario, String detalhe){
        addMensagem(FacesMessage.SEVERITY_WARN, sumario, detalhe);
    }
    public void addMensagemAviso(String detalhe){
        addMensagem(FacesMessage.SEVERITY_WARN, "Aviso", detalhe);
    }
    public void addMensagemErro(String sumario, String detalhe){
        addMensagem(FacesMessage.SEVERITY_ERROR, sumario, detalhe);
    }
    public void addMensagemErro(String detalhe){
        addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", detalhe);
    }
    public void addMensagemFatal(String sumario, String detalhe){
        addMensagem(FacesMessage.SEVERITY_ERROR, sumario, detalhe);
    }
    public void addMensagemFatal(String detalhe){
        addMensagem(FacesMessage.SEVERITY_ERROR, "Fatal", detalhe);
    }
}
