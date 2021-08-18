package facade;

import entidades.Agendamento;
import entidades.Cidade;
import entidades.Servico;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class AgendamentoFacade extends AbstractFacade<Agendamento> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public AgendamentoFacade(){ super(Agendamento.class);}
}
