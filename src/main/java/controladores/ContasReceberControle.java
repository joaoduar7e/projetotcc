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
import facade.ContasReceberFacade;
import facade.PlanoPagamentoFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewAccessScoped
public class ContasReceberControle implements Serializable {

    private ContasReceber contasReceber;
    @Inject
    transient private ContasReceberFacade contasReceberFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;
    @Inject
    transient private PlanoPagamentoFacade planoPagamentoFacade;
    private ConverterGenerico planoPagamentoConverter;

    private BaixaContasReceber baixaContasReceber;
    private MoneyConverter moneyConverter;

    public void addBaixa() throws Exception {
        if (baixaContasReceber.getValor() > contasReceber.getValor()) {
            JsfUtil.adicionarMenssagemErro("O valor a receber deve ser menor que o valor da conta");
        }
        else if(baixaContasReceber.getValor() > contasReceber.getValor() - contasReceber.getValorBaixado()){
            JsfUtil.adicionarMenssagemErro("O valor a receber deve ser menor que o valor restante");
        }   else {
            baixaContasReceber.setContasReceber(contasReceber);
            contasReceber.getBaixaContasRecebers().add(baixaContasReceber);
            baixaContasReceber = new BaixaContasReceber();
            salvar();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("list.xhtml");
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

    public BaixaContasReceber getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }

    public void excluir(ContasReceber cr) {
        contasReceberFacade.remover(cr);
    }

    public void editar(ContasReceber contaR) {
        contasReceber = contaR;
    }

    public void novo() {
        contasReceber = new ContasReceber();
    }

    public void salvar() throws Exception {
        try {
            contasReceberFacade.salvar(contasReceber);
            JsfUtil.adicionarMenssagemSucesso("Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("Falha ao salvar");
        }

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

    public List<ContasReceber> getListaCrRecebida() {
        return contasReceberFacade.listaCrPaga();
    }

    public List<ContasReceber> getListaCrReceber() {
        return contasReceberFacade.listaCrPagar();
    }

}
