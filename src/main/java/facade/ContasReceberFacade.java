
package facade;

import entidades.ContasReceber;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class ContasReceberFacade extends AbstractFacade<ContasReceber> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContasReceberFacade() {
        super(ContasReceber.class);
    }

    public List<ContasReceber> listaCrPaga() {
        Query q = getEntityManager().createQuery("from "
                + "ContasReceber as v "
                + "where v.pago = true "
                + "order by v.id");
        return q.getResultList();
    }

    public List<ContasReceber> listaCrPagar() {
        Query q = getEntityManager().createQuery("from "
                + "ContasReceber as v "
                + "where v.pago = false "
                + "order by v.id");
        return q.getResultList();
    }

    public void crMerge(ContasReceber cr){
        em.merge(cr);
    }
}
