package facade;

import entidades.Agendamento;
import entidades.Cidade;
import entidades.ContasReceber;
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

    public List<Agendamento> listaA() {
        Query q = getEntityManager().createQuery("from "
                + "Agendamento as a "
                + "where a.finalizado = true "
                + "order by a.finalizado, a.dataAgendadada");
        return q.getResultList();
    }

    public List<Agendamento> listaAgAberta() {
        Query q = getEntityManager().createQuery("from "
                + "Agendamento as a "
                + "where a.finalizado = false "
                + "order by a.finalizado, a.dataAgendadada");
        return q.getResultList();
    }
}
