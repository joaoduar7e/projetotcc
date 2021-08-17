package facade;

import entidades.Agendamento;
import entidades.Cidade;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class AgendamentoFacade extends AbstractFacade<Agendamento> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public AgendamentoFacade(){ super(Agendamento.class);}
}
