package entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItensServico implements Serializable, ClassePai  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Vendas vendas;
    @ManyToOne
    private Servico servico;
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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
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
        ItensServico that = (ItensServico) o;
        return Objects.equals(id, that.id) && Objects.equals(vendas, that.vendas) && Objects.equals(servico, that.servico) && Objects.equals(quantidade, that.quantidade) && Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendas, servico, quantidade, preco);
    }

    @Override
    public String toString() {
        return "entidades.ItensServico[ id=" + id + " ]";
    }


}
    