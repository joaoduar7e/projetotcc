package facade;

import entidades.Pessoa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PessoaFacade extends AbstractFacade<Pessoa> {
    @PersistenceContext(unitName = "projetotccPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public PessoaFacade(){ super(Pessoa.class);}
}
