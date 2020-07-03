package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
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
    public void testar() {
        long time = Calendar.getInstance().getTimeInMillis()+2000;
        long agora = Calendar.getInstance().getTimeInMillis();
        while(time > agora) {
            agora = Calendar.getInstance().getTimeInMillis();
        }
    }
    public void novo(){
        try {
            testar();
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
        try {
            testar();
            entidade = getLogic().salvar(this.entidade);
            if(!entidades.contains(entidade)){
                entidades.add(0, entidade);
            }
            addMensagemInfo("Salvo com sucesso.");
            statusDaTela = StatusDaTela.BUSCA;
        } catch (ErroSistemaException ex) {
            addMensagemFatal(ex.getMessage());
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
        }
    }
    
    public void editar(E entidade){
        testar();
        this.entidade = entidade;
        statusDaTela = StatusDaTela.EDICAO;
    }
    
    public void deletar(E entidade){
        try {
            testar();
            getLogic().deletar(entidade);
            getEntidades().remove(entidade);
            addMensagemInfo("Removido com sucesso.");
        } catch (ErroSistemaException ex) {
            addMensagemFatal(ex.getMessage());
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
        }
    }
    
    public void buscar(){
        try {
            testar();
            if(!statusDaTela.equals(StatusDaTela.BUSCA)){
                statusDaTela = StatusDaTela.BUSCA;
                return;
            }
            this.entidades = getLogic().buscar(null);
        } catch (ErroSistemaException ex) {
            addMensagemFatal(ex.getMessage());
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
        }
    }
    
    public abstract L getLogic();
    
}
