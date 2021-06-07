package facade;

import entidades.Cliente;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class ClienteFacade extends AbstractFacade<Cliente> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public ClienteFacade(){ super(Cliente.class);}
}
