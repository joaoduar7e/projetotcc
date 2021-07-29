/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Entity
public class BaixaContasReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    @ManyToOne
    private ContasReceber contasReceber;
    private String formaPagamento;
    private Double valor;
    public BaixaContasReceber() {
        dataPagamento = new Date();
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaixaContasReceber that = (BaixaContasReceber) o;
        return Objects.equals(id, that.id) && Objects.equals(dataPagamento, that.dataPagamento) && Objects.equals(contasReceber, that.contasReceber) && Objects.equals(formaPagamento, that.formaPagamento) && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataPagamento, contasReceber, formaPagamento, valor);
    }

    @Override
    public String toString() {
        return "BaixaContasReceber[ id=" + id + " ]";
    }

}
