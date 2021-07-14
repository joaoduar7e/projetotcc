/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import converter.ConverterGenerico;
import entidades.*;
import facade.PecasFacade;
import facade.PessoaFacade;
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
    transient private PessoaFacade pessoaFacade;
    private ConverterGenerico pessoaConverter;
    @Inject
    transient private PecasFacade pecasFacade;
    private ConverterGenerico produtoConverter;
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

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(pecasFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public ConverterGenerico getPessoaConverter() {
        if (pessoaConverter == null) {
            pessoaConverter = new ConverterGenerico(pessoaFacade);
        }
        return pessoaConverter;
    }

    public void setPessoaConverter(ConverterGenerico pessoaConverter) {
        this.pessoaConverter = pessoaConverter;
    }

    public List<Pessoa> getListaPessoaFiltrando(String parte) {
        return pessoaFacade.listaFiltrando(parte, "nome", "telefone");
    }

    public List<Pecas> getListaProdutoFiltrando(String parte) {
        return pecasFacade.listaFiltrando(parte, "nome");
    }

    public void novo() {
        vendas = new Vendas();
        itensVenda = new ItensVenda();
    }

    public void salvar() {
        vendaFacade.salvar(vendas);
    }

    public void editar(Vendas ve) {
        vendas = ve;
    }

    public void remover(Vendas ve) {
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
        if (estoque - itensVenda.getQuantidade() < 0) {
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
