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
public class ContasPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    @ManyToOne
    private Cliente cliente;
    private Integer numParcela;
    private String observacao;
    @ManyToOne
    private Compra compra;
    private Double valor;
    private Boolean pago = false;
    @ManyToOne
    private PlanoPagamento planoPagamento;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "contasPagar")
    private List<BaixaContasPagar> baixaContasPagar;

    public String getSituacao() {
        if (getValorBaixado() != 0 && getValorBaixado() < valor) {
            pago = false;
            return "Parcial";
        } else if (getValorBaixado() < valor) {
            pago = false;
            return "Aberto";
        } else {
            pago = true;
            return "Pago";
        }

    }

    public Double getValorBaixado() {
        if (baixaContasPagar == null) {
            baixaContasPagar = new ArrayList<BaixaContasPagar>();
        }
        Double valorBaixado = 0d;
        for (BaixaContasPagar bx : baixaContasPagar) {
            valorBaixado = valorBaixado + bx.getValor();
        }
        return valorBaixado;
    }

    public List<BaixaContasPagar> getBaixaContasPagar() {
        return baixaContasPagar;
    }

    public void setBaixaContasPagar(List<BaixaContasPagar> baixaContasPagar) {
        this.baixaContasPagar = baixaContasPagar;
    }

    public ContasPagar() {
        baixaContasPagar = new ArrayList<BaixaContasPagar>();
    }

    public Double getValorRestante() {
        if (baixaContasPagar == null) {
            baixaContasPagar = new ArrayList<BaixaContasPagar>();
        }
        Double valorBaixado = 0d;
        for (BaixaContasPagar bx : baixaContasPagar) {
            valorBaixado = valor - bx.getValor();
        }
        return valorBaixado;
    }


    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public PlanoPagamento getPlanoPagamento() {
        return planoPagamento;
    }

    public void setPlanoPagamento(PlanoPagamento planoPagamento) {
        this.planoPagamento = planoPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasPagar)) {
            return false;
        }
        ContasPagar other = (ContasPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ContasPagar[ id=" + id + " ]";
    }

}
