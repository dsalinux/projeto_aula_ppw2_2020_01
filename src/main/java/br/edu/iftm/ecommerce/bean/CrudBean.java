/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CrudBean<E, L extends CrudLogic<E>> extends JSFUtil {

    private E entidade;
    private List<E> entidades;
    private StatusDaTela statusDaTela = StatusDaTela.BUSCA;
    private Class<E> classeEntidade;

    public CrudBean(Class<E> classeEntidade) {
        this.classeEntidade = classeEntidade;
    }

    private enum StatusDaTela {
        INSERCAO,
        EDICAO,
        BUSCA

    }

    public void novo() {
        try {
            this.entidade = classeEntidade.newInstance();
            statusDaTela = StatusDaTela.INSERCAO;
        } catch (InstantiationException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            getLogic().salvar(entidade);
            if (entidades.contains(entidade)) {
                entidades.add(0, entidade);
            } // ao adicionar esse trecho a mensagem salvo com sucesso sumiu
            addMensagemInfo("Salvo com sucesso!");
            statusDaTela = StatusDaTela.BUSCA;
        } catch (ErroSistemaException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            addMensagemFatal(ex.getMessage());
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editar(E entidade) {
        this.entidade = entidade;
        statusDaTela = StatusDaTela.EDICAO;
    }

    public void deletar(E entidade) {
        try {
            getLogic().deletar(entidade);
            getEntidades().remove(entidade);
            addMensagemInfo("Removido com Sucesso!"); // mensagem não aparece e o objeto só some da lista ao clicar em buscar
        } catch (ErroSistemaException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            addMensagemFatal(ex.getMessage());
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscar() {
        try {
            if (!statusDaTela.equals(StatusDaTela.BUSCA)) {
                statusDaTela = StatusDaTela.BUSCA;
                return;
            }
            this.entidade = (E) getLogic().buscar(null);
        } catch (ErroSistemaException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            addMensagemFatal(ex.getMessage());
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public abstract L getLogic();

}
