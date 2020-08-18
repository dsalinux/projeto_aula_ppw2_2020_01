package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.entity.Categoria;
import br.edu.iftm.ecommerce.entity.Marca;
import br.edu.iftm.ecommerce.entity.Produto;
import br.edu.iftm.ecommerce.logic.CategoriaLogic;
import br.edu.iftm.ecommerce.logic.MarcaLogic;
import br.edu.iftm.ecommerce.logic.ProdutoLogic;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter @Setter
public class GerenciarProdutoBean extends CrudBean<Produto, ProdutoLogic>{

    @Inject
    private ProdutoLogic logic;
    
    @Inject
    private MarcaLogic marcaLogic;
    
    @Inject
    private CategoriaLogic categoriaLogic;
    
    private List<Marca> marcas;
    private List<Categoria> categorias;
    /*segundoEntreLists - Comentado em uma aula anterior, quando use algo 
    para pegar uma lista completa geralmente pode ficar lento, principalmente
    se a listagem for feita com alta frequência. O JSF durante a renderização
    do XHTML pode consultar um get várias vezes, con isto o banco pode ser
    acessado estas várias vezes durente 1 única requisiçao.
    Aqui explica bem isto:
    http://blog.triadworks.com.br/jsf-nao-coloque-processamento-caro-em-metodos-getters
    */    
    private Integer milisegundoEntreLists = 10000;
    private Long ultimaListagemMarca = 0L;
    private Long ultimaListagemCategoria = 0L;

    
    public GerenciarProdutoBean() {
        super(Produto.class);
    }

    @Override
    public ProdutoLogic getLogic() {
        return this.logic;
    }
    
    public List<Marca> getMarcas(){
        if(new Date().getTime() - this.ultimaListagemMarca > milisegundoEntreLists){
            try {
                this.marcas = this.marcaLogic.buscar(null);
            } catch (ErroSistemaException ex) {
                addMensagemFatal(ex.getMessage());
            } catch (ErroNegocioException ex) {
                addMensagemErro(ex.getMessage());
            }
            this.ultimaListagemMarca = new Date().getTime();
        }
        return marcas;
    }
    public List<Categoria> getCategorias(){
        if(new Date().getTime() - this.ultimaListagemCategoria > milisegundoEntreLists){
            try {
                this.categorias = this.categoriaLogic.buscar(null);
            } catch (ErroSistemaException ex) {
                addMensagemFatal(ex.getMessage());
            } catch (ErroNegocioException ex) {
                addMensagemErro(ex.getMessage());
            }
            this.ultimaListagemCategoria = new Date().getTime();
        }
        return categorias;
    }
    
    public void recaregarMarcas() {
        this.ultimaListagemMarca = 0L;
    }
}
