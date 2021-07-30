package facade;

import entidades.Pecas;
import entidades.Pessoa;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class PecasFacade extends AbstractFacade<Pecas> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public PecasFacade(){ super(Pecas.class);}

    public void pecaMerge(Pecas p){
        em.merge(p);
    }
}
