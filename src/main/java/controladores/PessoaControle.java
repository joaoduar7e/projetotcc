package controladores;

import entidades.Pessoa;
import facade.PessoaFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;


import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class PessoaControle implements Serializable {
    private Pessoa pessoa = new Pessoa();
    @Inject
    transient private PessoaFacade pessoaFacade;

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
