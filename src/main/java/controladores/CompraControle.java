/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import converter.ConverterGenerico;
import entidades.Cliente;
import entidades.Compra;
import entidades.ItensCompra;
import entidades.Pecas;
import facade.ClienteFacade;
import facade.CompraFacade;
import facade.PecasFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class CompraControle implements Serializable {
    private Compra compra = new Compra();
    private ItensCompra itensCompra;
    @Inject
    transient private CompraFacade compraFacade;
    @Inject
    transient private ClienteFacade clienteFacade;
    private ConverterGenerico pessoaConverter;
    @Inject
    transient private PecasFacade pecasFacade;
    private ConverterGenerico produtoConverter;

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(pecasFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public ConverterGenerico getClienteConverter() {
        if (pessoaConverter == null) {
            pessoaConverter = new ConverterGenerico(clienteFacade);
        }
        return pessoaConverter;
    }

    public void setAlunoConverter(ConverterGenerico pessoaConverter) {
        this.pessoaConverter = pessoaConverter;
    }

    public List<Cliente> getListaClienteFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome", "telefone");
    }

    public List<Pecas> getListaPecasFiltrando(String parte) {
        return pecasFacade.listaFiltrando(parte, "descricao");
    }

    public void novo() {
        compra = new Compra();
        itensCompra = new ItensCompra();
    }

    public void salvar() {

    }

    public void editar(Compra ve) {
        compra = ve;
    }

    public void remover(Compra ve) {
        compraFacade.remover(ve);
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
    }

    public List<Compra> getListaCompras() {
        return compraFacade.listaTodos();
    }

    public void addItensCompra() {
        if (itensCompra.getPecas().getQtdEst() < itensCompra.getQuantidade()) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Estoque Zero",
                            null));
        } else {
            itensCompra.setCompra(compra);
            compra.getItensCompras().add(itensCompra);
            itensCompra = new ItensCompra();
        }
        Pecas p = itensCompra.getPecas();
        ItensCompra i = null;
        for (ItensCompra item : compra.getItensCompras()) {
            if (item.getPecas().equals(p)) i = item;
        }
        if (i == null) {
            itensCompra.setCompra(compra);
            ItensCompra ItensCompra = null;
            compra.getItensCompras().add(ItensCompra);
        } else {
            i.setQuantidade(i.getQuantidade() + itensCompra.getQuantidade());
            i.setPreco(itensCompra.getPreco());

        }
    }

    public void removerItensCompra(ItensCompra it) {
        compra.getItensCompras().remove(it);
    }

}
