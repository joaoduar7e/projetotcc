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
import entidades.ContasPagar;
import facade.ClienteFacade;
import facade.ContasPagarFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ContasPagarControle implements Serializable {

    private ContasPagar contasPagar;
    @Inject
    transient private ContasPagarFacade contasPagarFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;

    private BaixaContasReceber baixaContasReceber;
    private MoneyConverter moneyConverter;

//    public void addBaixa(){
//        baixaContasReceber.setContasReceber(contasPagar);
//        contasPagar.getBaixaContasRecebers().add(baixaContasReceber);
//        baixaContasReceber = new BaixaContasReceber();
//        salvar();
//    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
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

//    public BaixaContasReceber getBaixaContasReceber() {
//        return baixaContasReceber;
//    }
//
//    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
//        this.baixaContasReceber = baixaContasReceber;
//    }

    public void excluir(ContasPagar cp) throws Exception {
        contasPagarFacade.remover(cp);
    }

    public void editar(ContasPagar contaP) {
        contasPagar = contaP;
    }

    public void novo() {
        contasPagar = new ContasPagar();
    }

    public void salvar() {
        contasPagarFacade.salvar(contasPagar);
    }
//
//    public void baixar(ContasPagar cr) {
//        contasPagar = cr;
//        baixaContasReceber = new BaixaContasReceber();
//    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public List<ContasPagar> getListaContasPagar() {
        return contasPagarFacade.listaTodos();
    }

}
