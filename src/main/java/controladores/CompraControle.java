/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import converter.ConverterGenerico;
import entidades.*;
import facade.ClienteFacade;
import facade.CompraFacade;
import facade.PecasFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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


    private Integer numParcelas;
    private Date DataPrimeiraParcela;

    public void gerarParcelas() {
        compra.setContasPagar(new ArrayList<ContasPagar>());
        Date dataTemp = DataPrimeiraParcela;

        for (int i = 1; i <= numParcelas; i++) {

            ContasPagar cp = new ContasPagar();
            cp.setDataEmissao(compra.getDataCompra());
            cp.setNumParcela(i);
            cp.setCliente(compra.getCliente());
            cp.setCompra(compra);
            cp.setObservacao("Gerada a partir da compra ");
            cp.setValor(compra.getValorTotal() / numParcelas);
            cp.setDataVencimento(dataTemp);

            Calendar c = Calendar.getInstance();
            c.setTime(dataTemp);
            c.add(Calendar.MONTH, 1);
            dataTemp = c.getTime();

            compra.getContasPagar().add(cp);
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

    public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
        DataPrimeiraParcela = dataPrimeiraParcela;
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
