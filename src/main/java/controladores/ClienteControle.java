package controladores;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.Cliente;
import facade.CidadeFacade;
import facade.ClienteFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ClienteControle implements Serializable {
    private Cliente cliente = new Cliente();

    @Inject
    transient private ClienteFacade clienteFacade;

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

    public List<Cliente> getListaCliente() {
        return clienteFacade.listaTodos();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void editar(Cliente cli) {
        cliente = cli;
    }

    public void excluir(Cliente cli) {
        clienteFacade.remover(cli);
    }

    public void salvar() {
        clienteFacade.salvar(cliente);
        cliente = new Cliente();
    }

}
