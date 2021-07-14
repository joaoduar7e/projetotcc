
package facade;

import entidades.ContasReceber;

import javax.inject.Inject;
import javax.persistence.EntityManager;


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

}
