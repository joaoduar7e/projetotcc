package controladores;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Cidade;
import entidades.Estado;

import entidades.TipoServico;
import facade.CidadeFacade;
import facade.EstadoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class CidadeControle implements Serializable {

    private Cidade cidade = new Cidade();
    @Inject
    transient private CidadeFacade cidadeFacade;
    @Inject
    transient private EstadoFacade estadoFacade;
    private ConverterGenerico estadoConverter;

    private MoneyConverter moneyConverter;
    public ConverterGenerico getestadoConverter() {
        if (estadoConverter == null) {
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }

    public List<Estado> getListaEstado(){
        return estadoFacade.listaTodos();
    }

    public EstadoFacade getEstadoFacade() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return estadoFacade;
    }
    public void setEstadoFacade(EstadoFacade estadoFacade) {
        this.estadoFacade = estadoFacade;
    }

    public List<Cidade> getListaCidade() {
        return cidadeFacade.listaTodos();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void novo() {
        cidade = new Cidade();
    }

    public void editar(Cidade fun) {
        cidade = fun;
    }

    public void excluir(Cidade fun) {
        cidadeFacade.remover(fun);
    }

    public void salvar() {
        cidadeFacade.salvar(cidade);
        cidade = new Cidade();
    }


}
