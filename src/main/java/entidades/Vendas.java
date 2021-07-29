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
public class Vendas implements Serializable, ClassePai {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP) //annotation para persistir a data //timestamp grava data e hora
    private Date dataVenda;
    @ManyToOne
    private Cliente cliente;
    @Column
    private Double valorTotal;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    //efeito cascata, quando salva a venda, salva os itens de venda // fetchtype recupera os itens da venda // //mappedby indica que existe a chave estrangeira
    private List<ItensVenda> itensVendas;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    //efeito cascata, quando salva a venda, salva os itens de venda // fetchtype recupera os itens da venda // //mappedby indica que existe a chave estrangeira
    private List<ContasReceber> ContasRecebers;
    @ManyToOne
    private PlanoPagamento planoPagamento;

    public Vendas() { //metodo construtors
        dataVenda = new Date(); //pega a data do servidor
        valorTotal = 0d; //faz iniciar zerado o valor ao criar uma venda
        itensVendas = new ArrayList<ItensVenda>(); //instancia a lista
        ContasRecebers = new ArrayList<ContasReceber>();
    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlanoPagamento getPlanoPagamento() {
        return planoPagamento;
    }

    public void setPlanoPagamento(PlanoPagamento planoPagamento) {
        this.planoPagamento = planoPagamento;
    }

    public Double getValorTotal() {
        valorTotal = 0d; //inicia com 0
        for (ItensVenda it : itensVendas) { //percorre o itens venda
            valorTotal = valorTotal + it.getSubtotal();
        }
        return valorTotal;
    }


    public List<ItensVenda> getItensVendas() {
        return itensVendas;
    }

    public void setItensVendas(List<ItensVenda> itensVendas) {
        this.itensVendas = itensVendas;
    }

    public List<ContasReceber> getContasRecebers() {
        return ContasRecebers;
    }

    public void setContasRecebers(List<ContasReceber> ContasRecebers) {
        this.ContasRecebers = ContasRecebers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendas vendas = (Vendas) o;
        return Objects.equals(id, vendas.id) && Objects.equals(dataVenda, vendas.dataVenda) && Objects.equals(cliente, vendas.cliente) && Objects.equals(valorTotal, vendas.valorTotal) && Objects.equals(itensVendas, vendas.itensVendas) && Objects.equals(ContasRecebers, vendas.ContasRecebers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataVenda, cliente, valorTotal, itensVendas, ContasRecebers);
    }

    @Override
    public String toString() {
        return "Vendas" +
                "[ id=" + id + " ]";
    }

}
