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
    private ConverterGenerico clienteConverter;
    @Inject
    transient private PecasFacade pecasFacade;
    private ConverterGenerico pecasConverter;


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

    public void setAlunoConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public List<Cliente> getListaClienteFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome", "telefone");
    }

    public List<Pecas> getListaPecasFiltrando(String parte) {
        return pecasFacade.listaFiltrando(parte, "descricao");
    }

    public List<Compra> getListaCompra() {
        return compraFacade.listaTodos();
    }

    public void novo() {
        compra = new Compra();
        itensCompra = new ItensCompra();
    }

    public void salvar() {
        compraFacade.salvar(compra);
    }

    public void editar(Compra ve) {
        compra = ve;
    }

    public void excluir(Compra ve) {
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

    public void addItensCompra() {
        Double estoque = itensCompra.getPecas().getQtdEst();
        ItensCompra itTemp = null;
        for (ItensCompra it : compra.getItensCompras()) {
            if (it.getPecas().getId().equals(itensCompra.getPecas().getId())) {
                itTemp = it;
            }
        }

        if (itTemp == null) {
            itensCompra.setCompra(compra);
            compra.getItensCompras().add(itensCompra);
        } else {
            itTemp.setQuantidade(itTemp.getQuantidade() + itensCompra.getQuantidade());
            itTemp.setPreco(itensCompra.getPreco());
        }
        itensCompra = new ItensCompra();

    }

    public void removerItensCompra(ItensCompra it) {
        compra.getItensCompras().remove(it);
    }

}
