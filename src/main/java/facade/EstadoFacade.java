package facade;

import entidades.Estado;
import entidades.TipoServico;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class EstadoFacade extends AbstractFacade<Estado> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public EstadoFacade(){ super(Estado.class);}
}
