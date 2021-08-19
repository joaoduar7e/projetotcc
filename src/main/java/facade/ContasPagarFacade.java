
package facade;

import entidades.ContasPagar;
import entidades.ContasReceber;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class ContasPagarFacade extends AbstractFacade<ContasPagar> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContasPagarFacade() {
        super(ContasPagar.class);
    }

    public List<ContasPagar> listaCpPaga() {
        Query q = getEntityManager().createQuery("from "
                + "ContasPagar as v "
                + "where v.pago = true "
                + "order by v.id");
        return q.getResultList();
    }

    public List<ContasPagar> listaCpPagar() {
        Query q = getEntityManager().createQuery("from "
                + "ContasPagar as v "
                + "where v.pago = false "
                + "order by v.id");
        return q.getResultList();
    }

}
