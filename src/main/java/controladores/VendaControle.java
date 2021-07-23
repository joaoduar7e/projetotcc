/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import converter.ConverterGenerico;
import entidades.*;
import facade.ClienteFacade;
import facade.PecasFacade;
import facade.PlanoPagamentoFacade;
import facade.VendaFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@ViewAccessScoped
public class VendaControle implements Serializable {

    private Vendas vendas;
    private ItensVenda itensVenda;
    @Inject
    transient private VendaFacade vendaFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;
    @Inject
    transient private PecasFacade pecasFacade;
    private ConverterGenerico pecasConverter;
    @Inject
    transient private PlanoPagamentoFacade planoPagamentoFacade;
    private ConverterGenerico planoPagamentoConverter;
    private Integer numParcelas;
    private Date DataPrimeiraParcela;

    public void gerarParcelas() {
        vendas.setContasRecebers(new ArrayList<ContasReceber>());
        Date dataTemp = DataPrimeiraParcela;

        for (int i = 1; i <= numParcelas; i++) {

            ContasReceber cr = new ContasReceber();
            cr.setDataEmissao(vendas.getDataVenda());
            cr.setNumParcela(i);
            cr.setCliente(vendas.getCliente());
            cr.setVendas(vendas);
            cr.setObservacao("Gerada a partir da venda.");
            cr.setValor(vendas.getValorTotal() / numParcelas);
            cr.setDataVencimento(dataTemp);

            Calendar c = Calendar.getInstance();
            c.setTime(dataTemp);
            c.add(Calendar.MONTH, 1);
            dataTemp = c.getTime();

            vendas.getContasRecebers().add(cr);
        }
    }

    public Integer getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Integer numParcelas) {
        this.numParcelas = numParcelas;
    }

    public Date getDataPrimeiraParcela() {
        return DataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(Date DataPrimeiraParcela) {
        this.DataPrimeiraParcela = DataPrimeiraParcela;
    }

    public ConverterGenerico getPecasConverter() {
        if (pecasConverter == null) {
            pecasConverter = new ConverterGenerico(pecasFacade);
        }
        return pecasConverter;
    }

    public void setPecasConverter(ConverterGenerico pecasConverter) {
        this.pecasConverter = pecasConverter;
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

    public List<Cliente> getListaClienteFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome", "telefone");
    }

    public List<Pecas> getListaPecaFiltrando(String parte) {
        return pecasFacade.listaFiltrando(parte, "descricao");
    }

    public List<PlanoPagamento> getListaPlanoPagFiltrando(String parte) {
        return planoPagamentoFacade.listaFiltrando(parte, "nome");
    }

    public void novo() {
        vendas = new Vendas();
        itensVenda = new ItensVenda();
    }


    public void salvar() throws Exception {
        try {
            vendaFacade.salvar(vendas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", ""));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao salvar", e);
        }

    }

    public void editar(Vendas ve) {
        vendas = ve;
    }

    public void excluir(Vendas ve) {
        vendaFacade.remover(ve);
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }

    public List<Vendas> getListaVendas() {
        return vendaFacade.listaTodos();
    }

    public void addItensVenda() {
        Double estoque = itensVenda.getPecas().getQtdEst();
        ItensVenda itTemp = null;
        for (ItensVenda it : vendas.getItensVendas()) {
            if (it.getPecas().getId().equals(itensVenda.getPecas().getId())) {
                itTemp = it;
                estoque = estoque - it.getQuantidade();
            }
        }
        if (estoque - itensVenda.getQuantidade() < 0 || estoque - itensVenda.getQuantidade() == 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Estoque insuficiente!",
                            "Restam apenas " + estoque + " unidades!"));
        } else {
            if (itTemp == null) {
                itensVenda.setVenda(vendas);
                vendas.getItensVendas().add(itensVenda);
            } else {
                itTemp.setQuantidade(itTemp.getQuantidade() + itensVenda.getQuantidade());
                itTemp.setPreco(itensVenda.getPreco());
            }
            itensVenda = new ItensVenda();
        }
    }

    public void removerItensVenda(ItensVenda it) {
        vendas.getItensVendas().remove(it);
    }

}
