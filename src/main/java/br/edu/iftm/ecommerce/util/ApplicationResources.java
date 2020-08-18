package br.edu.iftm.ecommerce.util;

import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author danilo
 */
@ApplicationScoped
public class ApplicationResources {
    
//    @Produces
//    @PersistenceContext(name = "ecommerce-pu")
    private EntityManager em;
    
    @SessionScoped
    @Produces
    public EntityManager createEntityManager() {
        try {
            System.out.println("\n\n#########################\n Criaçao conexao \n\n\n");
//            DatabaseUtil.migrarBancoDeDados();
            EntityManagerFactory emf
                    = Persistence.createEntityManagerFactory("ecommerce-pu");
            EntityManager em = emf.createEntityManager();
            return em;
        } catch (Exception ex) {
            throw new ErroSistemaException("Erro ao chamar o banco de dados.", ex);
        }
    }
    
    
    public void close(@Disposes  EntityManager em){
        System.out.println("\n\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n Fechando conexao \n\n\n");
        em.close();
    }
}