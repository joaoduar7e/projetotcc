package facade;

import entidades.Pessoa;
import entidades.Usuario;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class UsuarioFacade extends AbstractFacade<Usuario> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public UsuarioFacade(){ super(Usuario.class);}
}
