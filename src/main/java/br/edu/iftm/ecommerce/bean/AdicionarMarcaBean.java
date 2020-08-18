package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.entity.Marca;
import br.edu.iftm.ecommerce.logic.MarcaLogic;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SessionScoped// Coloquei escopo de request assim não mantem dados no servidor
@Named
public class AdicionarMarcaBean extends JSFUtil{
    
    @Inject
    private MarcaLogic logic;
    
    private Marca marca = new Marca();
    
    //Use o post construct quando quiser executar algo que dependa dos dados web
    //já tenham carregados.
//    @PostConstruct
//    public void init() {
//        marca = new Marca();
//    }
    
    public void salvar() {
        try {
            logic.salvar(marca);
            this.marca = new Marca();
            addMensagemInfo("Marca adicionada com sucesso.");
        } catch (ErroSistemaException ex) {
            addMensagemFatal(ex.getMessage());
        } catch (ErroNegocioException ex) {
            addMensagemErro(ex.getMessage());
        }
    }
    
}
