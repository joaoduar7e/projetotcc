package controladores;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Pecas;
import facade.PecasFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class PecasControle implements Serializable {
    private Pecas pecas = new Pecas();
    @Inject
    transient private PecasFacade pecasFacade;
    private MoneyConverter moneyConverter;

    public MoneyConverter getMoneyConverter() {
        return moneyConverter;
    }
    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public PecasFacade getPecasFacade() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return pecasFacade;
    }

    public void setPecasFacade(PecasFacade pecasFacade) {
        this.pecasFacade = pecasFacade;
    }

    public void novo(){
        pecas = new Pecas();
    }

    public List<Pecas> getListPecas(){
        return pecasFacade.listaTodos();
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pec) {
        this.pecas = pec;
    }

    public void editar(Pecas pec){
        pecas = pec;
    }

    public void excluir(Pecas pec){
        pecasFacade.remover(pec);
    }

    public void salvar(){
        pecasFacade.salvar(pecas);
    }
}
