package controladores;

import entidades.TipoServico;
import facade.TipoServicoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class TipoServicoControle implements Serializable {
    private TipoServico tipoServico = new TipoServico();
    @Inject
    transient private TipoServicoFacade tipoServicoFacade;

    public void novo(){
        tipoServico = new TipoServico();
    }

    public List<TipoServico> getListaTipoServico(){
        return tipoServicoFacade.listaTodos();
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public void editar(TipoServico tipoServ){
        tipoServico = tipoServ;
    }

    public void excluir(TipoServico tipoServ){
        tipoServicoFacade.remover(tipoServ);
    }


    public void salvar(){
        tipoServicoFacade.salvar(tipoServico);
        tipoServico = new TipoServico();
    }
}
