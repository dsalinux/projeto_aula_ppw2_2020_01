package br.edu.iftm.ecommerce.util;

import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.flywaydb.core.Flyway;

/**
 *
 * @author danilo
 */
public class DatabaseUtil {
    
    public static boolean migrou = false;

    public static void migrarBancoDeDados() {
        if(!migrou) {
            migrou = true;
            System.out.println("\n\n\n");
            System.out.println("Migração:---------------------");
            Flyway flyway = Flyway.configure().loadDefaultConfigurationFiles().load();
            flyway.migrate();
            System.out.println("OK....");
            System.out.println("\n\n\n");
        }
        
    }

    private static EntityManagerFactory criarEntityManager(){
        try {
            EntityManagerFactory emf
                    = Persistence.createEntityManagerFactory("br.edu.iftm.ecommerce.jpa");
            return emf;
        } catch (Exception ex) {
            System.err.println("Erro migração: "+ex.getMessage());
            return null;
        }
    }
    
}
