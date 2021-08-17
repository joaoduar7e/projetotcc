package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItensAgendamento implements Serializable, ClassePai  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Agendamento agendamento;
    @ManyToOne
    private Servico servico;
    private Double quantidade;
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

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        ItensAgendamento that = (ItensAgendamento) o;
        return Objects.equals(id, that.id) && Objects.equals(agendamento, that.agendamento) && Objects.equals(servico, that.servico) && Objects.equals(quantidade, that.quantidade) && Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agendamento, servico, quantidade, preco);
    }

    @Override
    public String toString() {
        return "entidades.ItensAgendamento[ id=" + id + " ]";
    }


}
    