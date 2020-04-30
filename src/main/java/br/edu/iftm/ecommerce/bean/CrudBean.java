package br.edu.iftm.ecommerce.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrudBean<E> implements Serializable  {
    
    private E entidade;
    private List<E> entidades;
    private Class<E> classeEntidade;
    
    private StatusDaTela statusDaTela = StatusDaTela.BUSCA;

    public CrudBean(Class<E> classeEntidade){
        this.classeEntidade = classeEntidade;
    }
    
    private enum StatusDaTela {
        INSERCAO,
        EDICAO,
        BUSCA
    }
    
    public void novo(){
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
        
    }
    
    public void editar(E entidade){
        this.entidade = entidade;
        statusDaTela = StatusDaTela.EDICAO;
    }
    
    public void deletar(E entidade){
        
    }
    
    public void buscar(){
        if(!statusDaTela.equals(StatusDaTela.BUSCA)){
            statusDaTela = StatusDaTela.BUSCA;
            return;
        }
    }
    
    
    
}
