package utils;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class TransactionInterceptor {
    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        try {
            if (!em.getTransaction().isActive()) em.getTransaction().begin();
            Object resultado = ctx.proceed();
            if (em.getTransaction().isActive()) em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
