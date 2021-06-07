package controladores;

import converter.ConverterGenerico;
import entidades.Servico;
import entidades.TipoServico;
import facade.ServicoFacade;
import facade.TipoServicoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ServicoControle implements Serializable {
    private Servico servico = new Servico();
    @Inject
    transient private ServicoFacade servicoFacade;

    @Inject
    transient private TipoServicoFacade tipoServicoFacade;
    private ConverterGenerico tipoServicoConverter;

    public ConverterGenerico getTipoServicoConverter() {
        if(tipoServicoConverter == null){
            tipoServicoConverter = new ConverterGenerico(tipoServicoFacade);
        }
        return tipoServicoConverter;
    }

    public void setTipoServicoConverter(ConverterGenerico tipoServicoConverter) {
        this.tipoServicoConverter = tipoServicoConverter;
    }

    public List<TipoServico> getListaTipoServico(){
        return tipoServicoFacade.listaTodos();
    }


    public void novo(){
        servico = new Servico();
    }

    public List<Servico> getListaServico(){
        return servicoFacade.listaTodos();
    }


    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico Servico) {
        this.servico = Servico;
    }

    public void editar(Servico serv){
        servico = serv;
    }

    public void excluir(Servico serv){
        servicoFacade.remover(serv);
    }


    public void salvar(){
        servicoFacade.salvar(servico);
    }
}
