
package facade;

import entidades.ContasPagar;

import javax.inject.Inject;
import javax.persistence.EntityManager;


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

}
