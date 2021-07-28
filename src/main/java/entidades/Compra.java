/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Compra implements Serializable, ClassePai {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;
    @ManyToOne
    private Cliente cliente;
    @Column
    private Double valorTotal;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
    private List<ItensCompra> itensCompras;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
    private List<ContasPagar> contasPagar;


    private Integer numParcelas;
    private Date DataPrimeiraParcela;

    public Compra() {
        dataCompra = new Date();
        valorTotal = 0d;
        itensCompras = new ArrayList<ItensCompra>();
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        valorTotal = 0d;
        for (ItensCompra it : itensCompras) {
            valorTotal += it.getSubTotal();
        }
        return valorTotal;
    }

    public List<ItensCompra> getItensCompras() {
        return itensCompras;
    }

    public void setItensCompras(List<ItensCompra> itensCompras) {
        this.itensCompras = itensCompras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ContasPagar> getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(List<ContasPagar> contasPagar) {
        this.contasPagar = contasPagar;
    }

    @Override
    public String toString() {
        return "Compra[ id=" + id + " ]";
    }

}
