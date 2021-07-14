package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItensVenda implements Serializable, ClassePai  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Vendas vendas;
    @ManyToOne
    private Pecas pecas;
    @Column
    private Double quantidade;
    @Column
    private Double preco;

    public Double getSubtotal() {
        return quantidade * preco;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendas getVenda() {
        return vendas;
    }

    public void setVenda(Vendas vendas) {
        this.vendas = vendas;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensVenda that = (ItensVenda) o;
        return Objects.equals(id, that.id) && Objects.equals(vendas, that.vendas) && Objects.equals(pecas, that.pecas) && Objects.equals(quantidade, that.quantidade) && Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendas, pecas, quantidade, preco);
    }

    @Override
    public String toString() {
        return "entidades.ItensVenda[ id=" + id + " ]";
    }


}
    