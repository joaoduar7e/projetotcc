/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joao Duarte
 */
@Entity

public class Vendas implements Serializable, ClassePai{
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
              mappedBy = "vendas") //efeito cascata, quando salva a venda, salva os itens de venda // fetchtype recupera os itens da venda // //mappedby indica que existe a chave estrangeira  
    private List<ItensVenda> itensVendas;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
              mappedBy = "vendas") //efeito cascata, quando salva a venda, salva os itens de venda // fetchtype recupera os itens da venda // //mappedby indica que existe a chave estrangeira  
    private List<ContasReceber> ContasRecebers;
    
    

    public Vendas() { //metodo construtor
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

    public Double getValorTotal() {
        valorTotal = 0d; //inicia com 0
        for(ItensVenda it : itensVendas){ //percorre o itens venda
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendas)) {
            return false;
        }
        Vendas other = (Vendas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
       @Override
    public String toString() {
        return "Vendas" +
                "[ id=" + id + " ]";
    }

}
