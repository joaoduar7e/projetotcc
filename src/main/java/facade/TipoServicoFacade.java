package facade;


import entidades.TipoServico;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class TipoServicoFacade extends AbstractFacade<TipoServico> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public TipoServicoFacade(){ super(TipoServico.class);}
}
