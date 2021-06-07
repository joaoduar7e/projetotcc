package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable, ClassePai  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column
    private String sobrenome;
    @Column
    private String telefone;
    @Column
    private String rua;
    @Column
    private String numero;
    @Column
    private String endereco;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    // Gets e Sets


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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(nome, pessoa.nome) && Objects.equals(sobrenome, pessoa.sobrenome) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(rua, pessoa.rua) && Objects.equals(numero, pessoa.numero) && Objects.equals(endereco, pessoa.endereco) && Objects.equals(dataNascimento, pessoa.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, telefone, rua, numero, endereco, dataNascimento);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                '}';
    }
}
