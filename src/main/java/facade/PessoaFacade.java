package facade;

import entidades.Pessoa;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class PessoaFacade extends AbstractFacade<Pessoa> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public PessoaFacade(){ super(Pessoa.class);}
}
