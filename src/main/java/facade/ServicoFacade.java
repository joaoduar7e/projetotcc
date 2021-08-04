package facade;

import entidades.Servico;
import entidades.TipoServico;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class ServicoFacade extends AbstractFacade<Servico> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public ServicoFacade(){ super(Servico.class);}

    public void ServMerge(Servico s){
        em.merge(s);
    }
}
