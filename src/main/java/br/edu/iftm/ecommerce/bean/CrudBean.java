/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CrudBean<E, L extends CrudLogic<E>> extends JSFUtil{
    
    private E entidade;
    private List<E> entidades;
    private StatusDaTela statusDaTela = StatusDaTela.BUSCA;
    private Class<E> classeEntidade;
    
    public CrudBean(Class<E> classeEntidade) {
        this.classeEntidade = classeEntidade;
    }
    
    private enum StatusDaTela{
        INSERCAO, 
        EDICAO, 
        BUSCA
        
    }
    
    public void novo (){
        try {
            this.entidade = classeEntidade.newInstance();
            statusDaTela = StatusDaTela.INSERCAO;
        } catch (InstantiationException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar(){
        getLogic().salvar(entidade);
        addMensagemInfo
        ("Salvo com sucesso!");
    
    }
    
    public void editar(E entidade){
        this.entidade = entidade;
        statusDaTela = StatusDaTela.EDICAO;
    }
    
    public void deletar(E entidade){
        getLogic().deletar(entidade);
    
    }
    
    public void buscar(){
        if(!statusDaTela.equals(StatusDaTela.BUSCA)){
            statusDaTela = StatusDaTela.BUSCA;
            return;
        }
        this.entidade = (E) getLogic().buscar(null);
    
    }
    
    public abstract L getLogic();
    
}
