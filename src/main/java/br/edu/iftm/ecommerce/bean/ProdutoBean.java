/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.entity.Produto;
import br.edu.iftm.ecommerce.logic.CrudLogic;
import br.edu.iftm.ecommerce.logic.ProdutoLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ProdutoBean extends CrudBean<Produto, ProdutoLogic>{

    @Inject
    private ProdutoLogic logic;
    
    public ProdutoBean() {
        super(Produto.class);
    }

    
    @Override
    public ProdutoLogic getLogic() {
        return this.logic;
    }
    
}
