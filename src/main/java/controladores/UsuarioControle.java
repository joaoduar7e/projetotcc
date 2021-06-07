package controladores;

import converter.ConverterGenerico;
import entidades.Funcionario;
import entidades.Usuario;
import facade.FuncionarioFacade;;
import facade.UsuarioFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class UsuarioControle implements Serializable {
    private Usuario usuario = new Usuario();
    @Inject
    transient private UsuarioFacade usuarioFacade;

    @Inject
    transient private FuncionarioFacade funcionarioFacade;
    private ConverterGenerico funcionarioConverter;

    public ConverterGenerico getFuncionarioConverter() {
        if(funcionarioConverter == null){
            funcionarioConverter = new ConverterGenerico(funcionarioFacade);
        }
        return funcionarioConverter;
    }

    public void setFuncionarioConverter(ConverterGenerico funcionarioConverter) {
        this.funcionarioConverter = funcionarioConverter;
    }

    public List<Funcionario> getListaFuncionarios(){
        return funcionarioFacade.listaTodos();
    }


    public void novo(){
        usuario = new Usuario();
    }

    public List<Usuario> getListaUsuario(){
        return usuarioFacade.listaTodos();
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    public void editar(Usuario usu){
        usuario = usu;
    }

    public void excluir(Usuario usu){
        usuarioFacade.remover(usu);
    }


    public void salvar(){
        usuarioFacade.salvar(usuario);
    }
}
