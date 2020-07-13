package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.entity.Permissao;
import br.edu.iftm.ecommerce.logic.UsuarioLogic;
import br.edu.iftm.ecommerce.entity.Usuario;
import br.edu.iftm.ecommerce.logic.PermissaoLogic;
import br.edu.iftm.ecommerce.logic.ProdutoLogic;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter @Setter
public class UsuarioBean extends CrudBean<Usuario, UsuarioLogic> {

    @Inject
    private UsuarioLogic usuarioLogic;
    
    @Inject
    private PermissaoLogic permissaoLogic;

    public UsuarioBean() {
        super(Usuario.class);
    }

    @Override
    public UsuarioLogic getLogic() {
        return this.usuarioLogic;
    }

    public List<Permissao> getPermissoes() {
        try {
            return permissaoLogic.buscar(null);
        } catch (ErroSistemaException ex) {
            addMensagemFatal(ex.getMessage());
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
        }
        return new ArrayList<>();
    }
    
}
