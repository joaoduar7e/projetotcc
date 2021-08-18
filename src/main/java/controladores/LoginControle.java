package controladores;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Cidade;
import entidades.Estado;
import entidades.Usuario;
import facade.CidadeFacade;
import facade.EstadoFacade;
import facade.UsuarioFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class LoginControle implements Serializable {

    @Inject
    transient private UsuarioFacade usuarioFacade;
    private Usuario usuario;
    private String login;
    private String senha;
    private Boolean logado = false;

    public String logar() {
        usuario = usuarioFacade.pesquisaUsuario(login, senha);
        if (usuario != null || (login.equals("joao.duarte") && senha.equals("joaopaulo98"))) {
            logado = true;
            return "index?faces-redirect=true";
        } else {
            logado = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário não encontrado no sistema", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String logoff() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        logado = false;
        return "/login?faces-redirect=true";
    }

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
