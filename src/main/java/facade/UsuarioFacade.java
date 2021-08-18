package facade;

import entidades.Pessoa;
import entidades.Servico;
import entidades.Usuario;
import utils.Criptografia;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class UsuarioFacade extends AbstractFacade<Usuario> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public void UsuMerge(Usuario u){
        em.merge(u);
    }

    public Usuario pesquisaUsuario(String login, String senha) {
        Query query = em.createQuery("FROM Usuario AS u WHERE u.login='" + login + "' AND u.senha='" + Criptografia.md5(senha) + "'");
        if (query.getResultList().size() == 1) {
            return (Usuario) query.getResultList().get(0);
        }
        return null;
    }
}
