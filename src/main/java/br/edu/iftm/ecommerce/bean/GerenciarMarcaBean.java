package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.entity.Marca;
import br.edu.iftm.ecommerce.logic.MarcaLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class GerenciarMarcaBean extends CrudBean<Marca, MarcaLogic> {

    @Inject
    private MarcaLogic logic;

    public GerenciarMarcaBean() {
        super(Marca.class);
    }

    @Override
    public MarcaLogic getLogic() {
        return logic;
    }

}
