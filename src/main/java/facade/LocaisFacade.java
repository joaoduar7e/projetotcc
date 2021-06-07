package facade;

import entidades.Locais;
import entidades.Servico;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class LocaisFacade extends AbstractFacade<Locais> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public LocaisFacade(){ super(Locais.class);}
}
