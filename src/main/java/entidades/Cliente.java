package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Cliente implements Serializable, ClassePai  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    private String cpfcpnj;
    private String sobrenome;
    private String apelido;
    private String telefone;
    private String logradouro;
    private String numero;
    private String bairro;
    private String observacao;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToOne
    private Cidade cidade;

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCpfcpnj() {
        return cpfcpnj;
    }

    public void setCpfcpnj(String cpfcpnj) {
        this.cpfcpnj = cpfcpnj;
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
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cpfcpnj, cliente.cpfcpnj) && Objects.equals(sobrenome, cliente.sobrenome) && Objects.equals(apelido, cliente.apelido) && Objects.equals(telefone, cliente.telefone) && Objects.equals(logradouro, cliente.logradouro) && Objects.equals(numero, cliente.numero) && Objects.equals(bairro, cliente.bairro) && Objects.equals(observacao, cliente.observacao) && Objects.equals(dataNascimento, cliente.dataNascimento) && Objects.equals(cidade, cliente.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpfcpnj, sobrenome, apelido, telefone, logradouro, numero, bairro, observacao, dataNascimento, cidade);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                '}';
    }
}
