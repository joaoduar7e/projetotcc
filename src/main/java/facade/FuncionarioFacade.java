package facade;

import entidades.Cliente;
import entidades.Funcionario;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class FuncionarioFacade extends AbstractFacade<Funcionario> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public FuncionarioFacade(){ super(Funcionario.class);}
}
