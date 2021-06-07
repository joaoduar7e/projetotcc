package controladores;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.*;
import entidades.Funcionario;
import facade.CidadeFacade;
import facade.ClienteFacade;
import facade.FuncionarioFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class FuncionarioControle implements Serializable {

    private Funcionario funcionario = new Funcionario();
    @Inject
    transient private FuncionarioFacade funcionarioFacade;

    //Cliente Converter
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;

    private MoneyConverter moneyConverter;

    public ConverterGenerico getClienteConverter() {
        if (clienteConverter == null) {
            clienteConverter = new ConverterGenerico(clienteFacade);
        }
        return clienteConverter;
    }

    public void setClienteConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public ClienteFacade getClienteFacade() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return clienteFacade;
    }
    public List<Cliente> getListaClientesFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }


    //Cidade Converter
    @Inject
    transient private CidadeFacade cidadeFacade;
    private ConverterGenerico cidadeConverter;

    public ConverterGenerico getCidadeConverter() {
        if(cidadeConverter == null){
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }
    public void setCidadeConverter(ConverterGenerico cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }

    public CidadeFacade getCidadeFacade() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return cidadeFacade;
    }

    public void setCidadeFacade(CidadeFacade cidadeFacade) {
        this.cidadeFacade = cidadeFacade;
    }

    public List<Cidade> getListaCidadeFiltrando(String parte) {
        return cidadeFacade.listaFiltrando(parte, "nome");
    }



    //Gets e Sets
    public MoneyConverter getMoneyConverter() {
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }


    public List<Funcionario> getListafuncionario() {
        return funcionarioFacade.listaTodos();
    }

    public List<Cidade> getListaCidade() {
        return cidadeFacade.listaTodos();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void novo() {
        funcionario = new Funcionario();
    }

    public void editar(Funcionario fun) {
        funcionario = fun;
    }

    public void excluir(Funcionario fun) {
        funcionarioFacade.remover(fun);
    }

    public void salvar() {
        funcionarioFacade.salvar(funcionario);
        funcionario = new Funcionario();
    }
}
