package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Pecas implements Serializable, ClassePai  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricao;
    private Double qtdEst;
    private BigDecimal preco;


    // Gets e Sets


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQtdEst() {
        return qtdEst;
    }

    public void setQtdEst(Double qtdEst) {
        this.qtdEst = qtdEst;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pecas pecas = (Pecas) o;
        return Objects.equals(id, pecas.id) && Objects.equals(descricao, pecas.descricao) && Objects.equals(qtdEst, pecas.qtdEst) && Objects.equals(preco, pecas.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, qtdEst, preco);
    }

    public String toString() {
        return "Pecas{" +
                "id=" + id +
                '}';
    }
}
