/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.BaixaContasReceber;
import entidades.Cliente;
import entidades.ContasReceber;
import facade.ClienteFacade;
import facade.ContasReceberFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ContasPagarControle implements Serializable {

    private ContasReceber contasReceber;
    @Inject
    transient private ContasReceberFacade contasReceberFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;

    private BaixaContasReceber baixaContasReceber;
    private MoneyConverter moneyConverter;

    public void addBaixa(){
        baixaContasReceber.setContasReceber(contasReceber);
        contasReceber.getBaixaContasRecebers().add(baixaContasReceber);
        baixaContasReceber = new BaixaContasReceber();
        salvar();
    }

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public ConverterGenerico getClienteConverter() {
        if (clienteConverter == null) {
            clienteConverter = new ConverterGenerico(clienteFacade);
        }
        return clienteConverter;
    }

    public void setClienteConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public List<Cliente> getListaClientesFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }

    public BaixaContasReceber getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }

    public void editar(ContasReceber contaR){
        contasReceber = contaR;
    }
    public void novo() {
        contasReceber = new ContasReceber();
    }

    public void salvar() {
        contasReceberFacade.salvar(contasReceber);
    }

    public void baixar(ContasReceber cr) {
        contasReceber = cr;
        baixaContasReceber = new BaixaContasReceber();
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public List<ContasReceber> getListaContasRecebers() {
        return contasReceberFacade.listaTodos();
    }

}
