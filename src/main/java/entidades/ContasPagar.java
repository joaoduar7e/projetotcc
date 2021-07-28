/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


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

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
//            mappedBy = "contasReceber")
//    private List<BaixaContasReceber> baixaContasRecebers;

//    public String getSituacao(){
//        if(getValorBaixado() < valor){
//            return "Aberto";
//        }
//        return "Pago";
//    }

//    public Double getValorBaixado(){
//        if(baixaContasRecebers == null){
//            baixaContasRecebers = new ArrayList<BaixaContasReceber>();
//        }
//        Double valorBaixado = 0d;
//        for(BaixaContasReceber bx : baixaContasRecebers){
//            valorBaixado = valorBaixado + bx.getValor();
//        }
//        return valorBaixado;
//    }
//    public ContasPagar() {
//        baixaContasRecebers = new ArrayList<BaixaContasReceber>();
//    }
//
//    public List<BaixaContasReceber> getBaixaContasRecebers() {
//        return baixaContasRecebers;
//    }
//
//    public void setBaixaContasRecebers(List<BaixaContasReceber> baixaContasRecebers) {
//        this.baixaContasRecebers = baixaContasRecebers;
//    }

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
        return "entidades.ContasReceber[ id=" + id + " ]";
    }
    
}
