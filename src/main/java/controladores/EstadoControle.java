package controladores;

import entidades.Estado;
import entidades.TipoServico;
import facade.EstadoFacade;
import facade.TipoServicoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class EstadoControle implements Serializable {
    private Estado estado = new Estado();
    @Inject
    transient private EstadoFacade estadoFacade;

    public List<Estado> getListaEstado(){
        return estadoFacade.listaTodos();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void novo(){
        estado = new Estado();
    }

    public void editar(Estado est){
        estado = est;
    }

    public void excluir(Estado est){
        estadoFacade.remover(est);
    }


    public void salvar(){
        estadoFacade.salvar(estado);
        estado = new Estado();
    }
}
