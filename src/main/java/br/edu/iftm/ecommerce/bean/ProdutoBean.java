package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.entity.Produto;
import br.edu.iftm.ecommerce.logic.ProdutoLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter @Setter
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
