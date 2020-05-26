package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CrudBean<E, L extends CrudLogic<E>> extends JSFUtil{
    
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
            this.entidade = classeEntidade.getDeclaredConstructor().newInstance();
            statusDaTela = StatusDaTela.INSERCAO;
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar(){
        entidade = getLogic().salvar(this.entidade);
        if(!entidades.contains(entidade)){
            entidades.add(0, entidade);
        }
        addMensagemInfo("Salvo com sucesso.");
        statusDaTela = StatusDaTela.BUSCA;
    }
    
    public void editar(E entidade){
        this.entidade = entidade;
        statusDaTela = StatusDaTela.EDICAO;
    }
    
    public void deletar(E entidade){
        getLogic().deletar(entidade);
        getEntidades().remove(entidade);
        addMensagemInfo("Removido com sucesso.");
    }
    
    public void buscar(){
        if(!statusDaTela.equals(StatusDaTela.BUSCA)){
            statusDaTela = StatusDaTela.BUSCA;
            return;
        }
        this.entidades = getLogic().buscar(null);
    }
    
    public abstract L getLogic();
    
}
