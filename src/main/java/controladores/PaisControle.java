package controladores;

import converter.ConverterGenerico;
import entidades.*;
import facade.PaisFacade;
import facade.ServicoFacade;
import facade.TipoServicoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class PaisControle implements Serializable {
    private Pais pais = new Pais();
    @Inject
    transient private PaisFacade paisFacade;

    public void novo() {
        pais = new Pais();
    }

    public List<Pais> getListaPais() {
        return paisFacade.listaTodos();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void editar(Pais pais) {
        pais = pais;
    }

    public void excluir(Pais pais) {
        paisFacade.remover(pais);
    }


    public void salvar() {
        paisFacade.salvar(pais);
        pais = new Pais();
    }
}
