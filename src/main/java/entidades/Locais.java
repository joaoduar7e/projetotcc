package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Locais implements Serializable, ClassePai  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricao;
    private String observacao;
    private String distancia;
    @ManyToOne
    private Cidade cidade;


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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locais locais = (Locais) o;
        return Objects.equals(id, locais.id) && Objects.equals(descricao, locais.descricao) && Objects.equals(observacao, locais.observacao) && Objects.equals(distancia, locais.distancia) && Objects.equals(cidade, locais.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, observacao, distancia, cidade);
    }

    public String toString() {
        return "Locais{" +
                "id=" + id +
                '}';
    }
}
