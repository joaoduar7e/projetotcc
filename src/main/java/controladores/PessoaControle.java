package controladores;

import entidades.Pessoa;
import facade.PessoaFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class PessoaControle {
    private Pessoa pessoa = new Pessoa();
    @EJB
    private PessoaFacade pessoaFacade;

    public void novo(){
        pessoa = new Pessoa();
    }

    public List<Pessoa> getListaPessoa(){
        return pessoaFacade.listaTodos();
    }

    public Pessoa getPessoa(){
        return pessoa;

    }
    public void setPessoa(Pessoa aluno){
        this.pessoa = aluno;
    }

    public void editar(Pessoa alu){
        pessoa = alu;
    }

    public void excluir(Pessoa alu){
        pessoaFacade.remover(alu);
    }


    public void salvar(){
        pessoaFacade.salvar(pessoa);
        pessoa = new Pessoa();
    }
}
