package controladores;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.Cliente;
import entidades.Locais;
import facade.CidadeFacade;
import facade.ClienteFacade;
import facade.LocaisFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class LocaisControle implements Serializable {
    private Locais locais = new Locais();

    @Inject
    transient private LocaisFacade locaisFacade;

    @Inject
    transient private CidadeFacade cidadeFacade;
    private ConverterGenerico cidadeConverter;

    public ConverterGenerico getCidadeConverter() {
        if (cidadeConverter == null) {
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }
    public List<Cidade> getListaCidadeFiltrando(String parte) {
        return cidadeFacade.listaFiltrando(parte, "nome");
    }
    public void setCidadeConverter(ConverterGenerico cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }

    public List<Cidade> getListaCidade(){
        return cidadeFacade.listaTodos();
    }


    public List<Locais> getListaLocais(){
        return locaisFacade.listaTodos();
    }

    public Locais getLocais() {
        return locais;
    }

    public void setLocais(Locais locais) {
        this.locais = locais;
    }

    public void novo() {
        locais = new Locais();
    }

    public void editar(Locais loc) {
        locais = loc;
    }

    public void excluir(Locais loc) {
        locaisFacade.remover(loc);
    }

    public void salvar() {
        locaisFacade.salvar(locais);
        locais = new Locais();
    }


}
