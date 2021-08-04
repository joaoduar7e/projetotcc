/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import converter.ConverterGenerico;
import entidades.*;
import facade.*;
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
    private ItensServico itensServico;
    @Inject
    transient private VendaFacade vendaFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;
    @Inject
    transient private PecasFacade pecasFacade;
    private ConverterGenerico pecasConverter;
    @Inject
    transient private ServicoFacade servicoFacade;
    private ConverterGenerico servicoConverter;
    @Inject
    transient private PlanoPagamentoFacade planoPagamentoFacade;
    private ConverterGenerico planoPagamentoConverter;
    private Integer numParcelas;
    private Date DataPrimeiraParcela;

    public void gerarParcelas() {
        try {
            vendas.setContasRecebers(new ArrayList<ContasReceber>());
            Date dataTemp = DataPrimeiraParcela;

            for (int i = 1; i <= numParcelas; i++) {

                ContasReceber cr = new ContasReceber();
                cr.setDataEmissao(vendas.getDataVenda());
                cr.setNumParcela(i);
                cr.setCliente(vendas.getCliente());
                cr.setVendas(vendas);
                cr.setObservacao("Gerada a partir da venda ");
                cr.setValor(vendas.getValorTotal() / numParcelas);
                cr.setDataVencimento(dataTemp);

                Calendar c = Calendar.getInstance();
                c.setTime(dataTemp);
                c.add(Calendar.MONTH, 1);
                dataTemp = c.getTime();

                vendas.getContasRecebers().add(cr);
            }
            JsfUtil.adicionarMenssagemSucesso("Parcelas geradas!");

        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("Erro ao gerar parcelas");
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

    public ConverterGenerico getServicoConverter() {
        if (servicoConverter == null) {
            servicoConverter = new ConverterGenerico(servicoFacade);
        }
        return servicoConverter;
    }

    public void setServicoConverter(ConverterGenerico servicoConverter) {
        this.servicoConverter = servicoConverter;
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

    public List<Servico> getListaServicoFiltrando(String parte) {
        return servicoFacade.listaFiltrando(parte, "descricao");
    }

    public List<PlanoPagamento> getListaPlanoPagFiltrando(String parte) {
        return planoPagamentoFacade.listaFiltrando(parte, "nome");
    }

    public List<PlanoPagamento> getListaPlanoPag() {
        return planoPagamentoFacade.listaTodos();
    }

    public void novo() {
        vendas = new Vendas();
        itensVenda = new ItensVenda();
        itensServico = new ItensServico();
    }


    public void salvar() throws Exception {
        try {
            for (ItensVenda it : vendas.getItensVendas()) {
                it.getPecas().setQtdEst(it.getPecas().getQtdEst() - it.getQuantidade());
                pecasFacade.pecaMerge(it.getPecas());
            }
            for (ItensServico is : vendas.getItensServico()) {
                if (is != null) {
                    servicoFacade.ServMerge(is.getServico());
                }
            }

            vendaFacade.salvar(vendas);
            JsfUtil.adicionarMenssagemSucesso("Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("Falha ao salvar");
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

    public ItensServico getItensServico() {
        return itensServico;
    }

    public void setItensServico(ItensServico itensServico) {
        this.itensServico = itensServico;
    }

    public void addItensVenda() {
        Double estoque = itensVenda.getPecas().getQtdEst();
        ItensVenda itTemp = null;
        for (ItensVenda it : vendas.getItensVendas()) {
            if (it.getPecas().getId().equals(itensVenda.getPecas().getId())) {
                itTemp = it;
                estoque = estoque - it.getQuantidade();
            }
            return;
        }
        if (estoque - itensVenda.getQuantidade() < 0 || estoque - itensVenda.getQuantidade() == -1) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Estoque insuficiente!",
                            "Restam apenas " + estoque + " unidades!"));
            return;
        }
        if (itensVenda.getQuantidade() == 0 || itensVenda.getQuantidade() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "A quantidade deve ser maior que 0",
                            ""));
            return;
        }
        if (itensVenda.getPreco() == 0 || itensVenda.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor deve ser maior que R$0!",
                            ""));
            return;
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


    public void addItensServico() {
        ItensServico itTemp = null;
        if (itensServico.getQuantidade() == 0 || itensServico.getQuantidade() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "A quantidade deve ser maior que 0",
                            ""));
            return;
        }
        if (itensServico.getPreco() == 0 || itensServico.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor deve ser maior que R$0!",
                            ""));
            return;
        } else {
            if (itTemp == null) {
                itensServico.setVenda(vendas);
                vendas.getItensServico().add(itensServico);
            } else {
                itTemp.setQuantidade(itTemp.getQuantidade() + itensServico.getQuantidade());
                itTemp.setPreco(itensServico.getPreco());
            }
            itensServico = new ItensServico();
        }
    }

    public void removerItensServico(ItensServico it) {
        vendas.getItensServico().remove(it);
    }


}
