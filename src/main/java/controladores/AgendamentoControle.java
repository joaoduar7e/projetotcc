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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.*;

@Named
@ViewAccessScoped
public class AgendamentoControle implements Serializable {

    private Agendamento agendamento;
    private ItensAgendamento itensAgendamento;
    @Inject
    transient private AgendamentoFacade agendamentoFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;
    private Cliente cliente;

    @Inject
    transient private ServicoFacade servicoFacade;
    private ConverterGenerico servicoConverter;

    @Inject
    transient private PlanoPagamentoFacade planoPagamentoFacade;
    private ConverterGenerico planoPagamentoConverter;

    @Inject
    transient private LocaisFacade locaisFacade;
    private ConverterGenerico locaisConverter;

    @Inject
    transient private MaquinarioFacade maquinarioFacade;
    private ConverterGenerico maquinarioConverter;

    private Integer numParcelas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DataPrimeiraParcela;

    public void gerarParcelas() {
        try {
            agendamento.setContasRecebers(new ArrayList<ContasReceber>());
            Date dataTemp = DataPrimeiraParcela;

            for (int i = 1; i <= numParcelas; i++) {

                ContasReceber cr = new ContasReceber();
                cr.setDataEmissao(agendamento.getDataAgendamento());
                cr.setNumParcela(i);
                cr.setCliente(agendamento.getCliente());
                cr.setAgendamento(agendamento);
                cr.setObservacao("Gerada a partir do agendamento ");
                cr.setValor(agendamento.getValorTotal() / numParcelas);
                cr.setDataVencimento(dataTemp);

                Calendar c = Calendar.getInstance();
                c.setTime(dataTemp);
                c.add(Calendar.MONTH, 1);
                dataTemp = c.getTime();

                agendamento.getContasRecebers().add(cr);
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


    public ConverterGenerico getLocaisConverter() {
        if (locaisConverter == null) {
            locaisConverter = new ConverterGenerico(locaisFacade);
        }
        return locaisConverter;
    }

    public void setLocaisConverter(ConverterGenerico locaisConverter) {
        this.locaisConverter = locaisConverter;
    }

    public ConverterGenerico getMaquinarioConverter() {
        if (maquinarioConverter == null) {
            maquinarioConverter = new ConverterGenerico(maquinarioFacade);
        }
        return maquinarioConverter;
    }

    public void setMaquinarioConverter(ConverterGenerico maquinarioConverter) {
        this.maquinarioConverter = maquinarioConverter;
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

    public ItensAgendamento getItensAgendamento() {
        return itensAgendamento;
    }

    public void setItensAgendamento(ItensAgendamento itensAgendamento) {
        this.itensAgendamento = itensAgendamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaClienteFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome", "telefone");
    }

    public List<Servico> getListaServicoFiltrando(String parte) {
        return servicoFacade.listaFiltrando(parte, "descricao");
    }

    public List<PlanoPagamento> getListaPlanoPagFiltrando(String parte) {
        return planoPagamentoFacade.listaFiltrando(parte, "nome");
    }

    public List<Locais> getListaLocaisFiltrando(String parte) {
        return locaisFacade.listaFiltrando(parte, "descricao");
    }

    public List<Maquinario> getListaMaquinarioFiltrando(String parte) {
        return maquinarioFacade.listaFiltrando(parte, "nome");
    }

    public List<PlanoPagamento> getListaPlanoPag() {
        return planoPagamentoFacade.listaTodos();
    }

    public List<Maquinario> getListaMaquinario(){
        return maquinarioFacade.listaTodos();
    }

    public List<Agendamento> getListaAgendamento(){
        return agendamentoFacade.listaTodos();
    }

    public List<Agendamento> getListaA() {
        return agendamentoFacade.listaA();
    }
    public List<Agendamento> getListaAgAberta() {
        return agendamentoFacade.listaAgAberta();
    }

    public void novo() {
        agendamento = new Agendamento();
        itensAgendamento = new ItensAgendamento();
    }
    public void novoCliente() {
        cliente = new Cliente();
    }

    public void finalizaAgendamento(Agendamento ag){
        agendamento = ag;
        try {
            ag.setFinalizado(!ag.getFinalizado());
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Situação alterada",
                            ""));
            agendamentoFacade.salvar(ag);
        }
        catch (Exception e){
            return;
        }
    }

    public void salvar() throws Exception {
        try {
            for (ItensAgendamento is : agendamento.getItensAgendamento()) {
                if (is != null) {
                    servicoFacade.ServMerge(is.getServico());
                }
            }
            if (agendamento.getItensAgendamento().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "É necessário ter pelo menos um serviço associado ao agendamento",
                                ""));
                return;
            } else {
                agendamentoFacade.salvar(agendamento);
            }

            JsfUtil.adicionarMenssagemSucesso("Salvo com sucesso");
            FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("Falha ao salvar");
        }

    }

    public void editar(Agendamento ag) {
        agendamento = ag;
    }

    public void excluir(Agendamento ag) {
        agendamentoFacade.remover(ag);
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public void addItensAgendamento() {
        ItensAgendamento itTemp = null;
        if (itensAgendamento.getQuantidade() == 0 || itensAgendamento.getQuantidade() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "A quantidade deve ser maior que 0",
                            ""));
            return;
        }
        if (itensAgendamento.getPreco() == 0 || itensAgendamento.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor deve ser maior que R$0!",
                            ""));
            return;
        } else {
            if (itTemp == null) {
                itensAgendamento.setAgendamento(agendamento);
                agendamento.getItensAgendamento().add(itensAgendamento);
            } else {
                itTemp.setQuantidade(itTemp.getQuantidade() + itensAgendamento.getQuantidade());
                itTemp.setPreco(itensAgendamento.getPreco());
            }
            itensAgendamento = new ItensAgendamento();
        }
    }

    public void removerItensAgendamento(ItensAgendamento it) {
        agendamento.getItensAgendamento().remove(it);
    }


}
