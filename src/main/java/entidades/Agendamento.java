package entidades;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
public class Agendamento implements Serializable, ClassePai {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP) //annotation para persistir a data //timestamp grava data e hora
    private Date dataAgendamento;
    @Temporal(TemporalType.TIMESTAMP) //annotation para persistir a data //timestamp grava data e hora
    private Date dataAgendadada;
    @ManyToOne
    private Cliente cliente;
    private Double valorTotal;
    private Double valorTotalS;
    private Boolean finalizado = false;
    @ManyToOne
    private Locais locais;
    @ManyToOne
    private Maquinario maquinario;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agendamento")
    private List<ItensAgendamento> itensAgendamento;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agendamento")
    private List<ContasReceber> ContasRecebers;

    @ManyToOne
    private PlanoPagamento planoPagamento;

    public Agendamento() { //metodo construtors
        dataAgendadada = new Date(); //pega a data do servidor
        dataAgendamento = new Date(); //pega a data do servidor
        valorTotal = 0d; //faz iniciar zerado o valor ao criar uma venda
        itensAgendamento = new ArrayList<ItensAgendamento>(); //instancia a lista
        ContasRecebers = new ArrayList<ContasReceber>();
    }

    public String getSituacao(){
        String situ = "";
        if (!finalizado){
            return "Aberta";
        }else{
            return "Finalizada";
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Date getDataAgendadada() {
        return dataAgendadada;
    }

    public void setDataAgendadada(Date dataAgendadada) {
        this.dataAgendadada = dataAgendadada;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotalS() {
        return valorTotalS;
    }

    public void setValorTotalS(Double valorTotalS) {
        this.valorTotalS = valorTotalS;
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

    public Locais getLocais() {
        return locais;
    }

    public void setLocais(Locais locais) {
        this.locais = locais;
    }

    public Maquinario getMaquinario() {
        return maquinario;
    }

    public void setMaquinario(Maquinario maquinario) {
        this.maquinario = maquinario;
    }

    public Double getValorTotal() {
        valorTotal = 0d; //inicia com 0
        for (ItensAgendamento it : itensAgendamento) { //percorre o itens venda
            valorTotal = valorTotal + it.getSubtotal();
        }
        return valorTotal;
    }

    public List<ItensAgendamento> getItensAgendamento() {
        return itensAgendamento;
    }

    public void setItensAgendamento(List<ItensAgendamento> itensAgendamento) {
        this.itensAgendamento = itensAgendamento;
    }

    public List<ContasReceber> getContasRecebers() {
        return ContasRecebers;
    }

    public void setContasRecebers(List<ContasReceber> ContasRecebers) {
        this.ContasRecebers = ContasRecebers;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id) && Objects.equals(dataAgendamento, that.dataAgendamento) && Objects.equals(dataAgendadada, that.dataAgendadada) && Objects.equals(cliente, that.cliente) && Objects.equals(valorTotal, that.valorTotal) && Objects.equals(valorTotalS, that.valorTotalS) && Objects.equals(locais, that.locais) && Objects.equals(maquinario, that.maquinario) && Objects.equals(itensAgendamento, that.itensAgendamento) && Objects.equals(ContasRecebers, that.ContasRecebers) && Objects.equals(planoPagamento, that.planoPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataAgendamento, dataAgendadada, cliente, valorTotal, valorTotalS, locais, maquinario, itensAgendamento, ContasRecebers, planoPagamento);
    }

    @Override
    public String toString() {
        return "Agendamento" +
                "[ id=" + id + " ]";
    }

}
