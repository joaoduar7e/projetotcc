/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.*;
import facade.ClienteFacade;
import facade.ContasPagarFacade;
import facade.PlanoPagamentoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
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
    @Inject
    transient private PlanoPagamentoFacade planoPagamentoFacade;
    private ConverterGenerico planoPagamentoConverter;

   private BaixaContasPagar baixaContasPagar;
    private MoneyConverter moneyConverter;

    public void addBaixa() throws Exception {
        if (baixaContasPagar.getValor() > contasPagar.getValor()) {
            JsfUtil.adicionarMenssagemErro("O valor a pagar deve ser menor que o valor da conta");
        }
        else if(baixaContasPagar.getValor() > contasPagar.getValor() - contasPagar.getValorBaixado()){
            JsfUtil.adicionarMenssagemErro("O valor a pagar deve ser menor que o valor restante");
        }   else {
            baixaContasPagar.setContasPagar(contasPagar);
            contasPagar.getBaixaContasPagar().add(baixaContasPagar);
            if(contasPagar.getValorRestante() == 0){
                contasPagar.setPago(true);
            }
            baixaContasPagar = new BaixaContasPagar();
            salvar();
            FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
        }
    }

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

    public ConverterGenerico getPlanoPagamentoConverter() {
        if (planoPagamentoConverter == null) {
            planoPagamentoConverter = new ConverterGenerico(planoPagamentoFacade);
        }
        return planoPagamentoConverter;
    }

    public void setPlanoPagamentoConverter(ConverterGenerico planoPagamentoConverter) {
        this.planoPagamentoConverter = planoPagamentoConverter;
    }

    public List<PlanoPagamento> getListaPlanoPagFiltrando(String parte) {
        return planoPagamentoFacade.listaFiltrando(parte, "nome");
    }

    public List<PlanoPagamento> getListaPlanoPag() {
        return planoPagamentoFacade.listaTodos();
    }

    public List<Cliente> getListaClientesFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }

    public BaixaContasPagar getBaixaContasPagar() {
        return baixaContasPagar;
    }

    public void setBaixaContasPagar(BaixaContasPagar baixaContasPagar) {
        this.baixaContasPagar = baixaContasPagar;
    }

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

    public void baixar(ContasPagar cr) {
        contasPagar = cr;
        baixaContasPagar = new BaixaContasPagar();
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public List<ContasPagar> getListaContasPagar() {
        return contasPagarFacade.listaTodos();
    }
    public List<ContasPagar> getListaCpPaga(){
        return contasPagarFacade.listaCpPaga();
    }

    public List<ContasPagar> getListaCpPagar(){
        return contasPagarFacade.listaCpPagar();
    }

}
