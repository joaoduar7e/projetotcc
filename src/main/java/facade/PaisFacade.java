package facade;

import entidades.Pais;
import entidades.Servico;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class PaisFacade extends AbstractFacade<Pais> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public PaisFacade(){ super(Pais.class);}
}
