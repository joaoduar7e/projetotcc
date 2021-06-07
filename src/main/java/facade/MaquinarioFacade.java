package facade;

import entidades.Maquinario;
import entidades.Pais;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class MaquinarioFacade extends AbstractFacade<Maquinario> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public MaquinarioFacade(){ super(Maquinario.class);}
}
