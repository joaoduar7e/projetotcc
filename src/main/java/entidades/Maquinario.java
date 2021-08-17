package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Maquinario implements Serializable, ClassePai  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String marca;
    private BigDecimal ano;
    private String observacao;

    @Temporal(TemporalType.DATE)
    private Date dataCompra;

    // Gets e Sets

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigDecimal getAno() {
        return ano;
    }

    public void setAno(BigDecimal ano) {
        this.ano = ano;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maquinario that = (Maquinario) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(marca, that.marca) && Objects.equals(ano, that.ano) && Objects.equals(observacao, that.observacao) && Objects.equals(dataCompra, that.dataCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, marca, ano, observacao, dataCompra);
    }

    public String toString() {
        return "Maquinario{" +
                "id=" + id +
                '}';
    }
}
