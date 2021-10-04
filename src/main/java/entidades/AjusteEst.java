package entidades;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
public class AjusteEst implements Serializable, ClassePai {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP) //annotation para persistir a data //timestamp grava data e hora
    private Date dataVenda;
    @ManyToOne
    private Pecas pecas;
    @Column
    private Double qtdTotal;
    @Column
    private String motivo;
    @Column
    private String tipo = "S";

    public AjusteEst() { //metodo construtors
        dataVenda = new Date(); //pega a data do servidor
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    public Double getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(Double qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AjusteEst ajusteEst = (AjusteEst) o;
        return Objects.equals(id, ajusteEst.id) && Objects.equals(dataVenda, ajusteEst.dataVenda) && Objects.equals(pecas, ajusteEst.pecas) && Objects.equals(qtdTotal, ajusteEst.qtdTotal) && Objects.equals(motivo, ajusteEst.motivo) && Objects.equals(tipo, ajusteEst.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataVenda, pecas, qtdTotal, motivo, tipo);
    }

    @Override
    public String toString() {
        return "AjusteEst" +
                "[ id=" + id + " ]";
    }

}
