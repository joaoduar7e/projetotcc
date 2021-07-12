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
import facade.VendaFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Joao Duarte
 */
@Named
@ViewAccessScoped
public class VendaControle implements Serializable {

    private Vendas vendas;
    private ItensVenda itensVenda;
    @Inject
    transient private VendaFacade vendasFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico clienteConverter;
    @Inject
    transient private PecasFacade pecasFacade;
    private ConverterGenerico pecasConverter;
    private Integer numParcelas;
    private Date DataPrimeiraParcelas;


    public void gerarParcelas() {
        vendas.setContasRecebers(new ArrayList<ContasReceber>());
        Date dataTemp = DataPrimeiraParcelas;

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

    public Date getDataPrimeiraParcelas() {
        return DataPrimeiraParcelas;
    }

    public void setDataPrimeiraParcelas(Date DataPrimeiraParcelas) {
        this.DataPrimeiraParcelas = DataPrimeiraParcelas;
    }


    public ConverterGenerico getPecasConverter() {
        if (pecasConverter == null) {
            pecasConverter = new ConverterGenerico(pecasFacade);
        }
        return pecasConverter;
    }

    public ConverterGenerico getConverter() {
        if (clienteConverter == null) {
            clienteConverter = new ConverterGenerico(clienteFacade);
        }
        return clienteConverter;
    }

    public void setClienteConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public List<Cliente> getListaClienteFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }

    public List<Pecas> getListaPecasFiltrando(String parte) {
        return pecasFacade.listaFiltrando(parte, "descricao");
    }

    public List<Vendas> getListaVendas() {
        return vendasFacade.listaTodos();
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }

//           public void addItensVenda(){
//               Double estoque = itensVenda.getProduto().getEstoque();
//               ItensVenda itemTemp = null;
//               for(ItensVenda it : vendas.getItensVendas()){
//                   
//                   if(it.getProduto().equals((itensVenda.getProduto()))){
//                       itemTemp = it;
//                    if(it.getQuantidade()+itensVenda.getQuantidade()<=itensVenda.getProduto().getEstoque()){
//                       it.setQuantidade(it.getQuantidade()+itensVenda.getQuantidade());
//                       itensVenda = new ItensVenda();
//                   }else{
//                       FacesContext.getCurrentInstance().addMessage(
//                       null, new FacesMessage(
//                               FacesMessage.SEVERITY_ERROR,
//                               "Estoque insuficiente",
//                               null));
//                       
//                   } 
//                   }
//
//               }
//        itensVenda.setVenda(vendas);
//        vendas.getItensVendas().add(itensVenda);
//        itensVenda = new ItensVenda();


    public void addItensVenda() {
        Double estoque = itensVenda.getPecas().getQtdEst();
        ItensVenda itemTemp = null;
        for (ItensVenda it : vendas.getItensVendas()) { //for para percorrer os itens na lista e verificar se existe

            if (it.getPecas().getId().equals(itensVenda.getPecas().getId())) {
                itemTemp = it;
                estoque = estoque - it.getQuantidade();
            }
        }
        if (estoque - itensVenda.getQuantidade() >= 0) {
            if (itemTemp == null) {
                itensVenda.setVenda(vendas);
                vendas.getItensVendas().add(itensVenda);
            } else {
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensVenda.getQuantidade());
                itemTemp.setPreco(itensVenda.getPreco());
            }
            itensVenda = new ItensVenda();
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Estoque insuficiente para a operação",
                            "Restam apenas" + estoque + "unidades!"));

        }

    }


    public void removerItensVenda(ItensVenda it) {
        vendas.getItensVendas().remove(it);


    }


    public void novo() {
        vendas = new Vendas();
        itensVenda = new ItensVenda();

    }

    public void salvar() {
        vendasFacade.salvar(vendas);
    }

    public void editar(Vendas cid) {
        vendas = cid;
    }

    public void remover(Vendas cid) {
        vendasFacade.remover(cid);
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }


}
