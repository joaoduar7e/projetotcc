package controladores;

import entidades.PlanoPagamento;
import entidades.TipoServico;
import facade.PlanoPagamentoFacade;
import facade.TipoServicoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class PlanoPagamentoControle implements Serializable {
    private PlanoPagamento planoPagamento = new PlanoPagamento();
    @Inject
    transient private PlanoPagamentoFacade planoPagamentoFacade;

    public void novo(){
        planoPagamento = new PlanoPagamento();
    }

    public List<PlanoPagamento> getListaPlanoPagamento(){
        return planoPagamentoFacade.listaTodos();
    }

    public PlanoPagamento getPlanoPagamento() {
        return planoPagamento;
    }

    public void setPlanoPagamento(PlanoPagamento planoPagamento) {
        this.planoPagamento = planoPagamento;
    }

    public void editar(PlanoPagamento planoPag){
        planoPagamento = planoPag;
    }

    public void excluir(PlanoPagamento planoPag){
        planoPagamentoFacade.remover(planoPag);
    }

    public void salvar(){
        planoPagamentoFacade.salvar(planoPagamento);
        planoPagamento = new PlanoPagamento();
    }
}
