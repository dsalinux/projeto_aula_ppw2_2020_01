package br.edu.iftm.ecommerce.util;

import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transacao
public class InterceptorTransactional implements Serializable {

    private static final long serialVersionUID = 1L;

//    @UserDatabase
    @Inject
    private EntityManager entity;

    @AroundInvoke
    public Object contextInterceptor(InvocationContext context) throws ErroSistemaException {

        try {
            System.out.println("\n\n#########################\n Abriu transaçao");
            System.out.println("Método chamado: " + context.getMethod().getName());
            entity.getTransaction().begin();
            Object object = context.proceed();
            entity.getTransaction().commit();
            System.out.println("\n\n#########################\n Comitando transaçao");
            
            return object;
        } catch (ErroSistemaException ex) {
            System.out.println("\n\n#########################\n Rolback transaçao: "+ex.getMessage());
            entity.getTransaction().rollback();
            throw ex;
        } catch (Exception ex) {
            System.out.println("\n\n#########################\n Rolback transaçao"+ex.getMessage());
            entity.getTransaction().rollback();
            throw new ErroSistemaException("Erro no banco de dados.", ex);
        }

    }

}
