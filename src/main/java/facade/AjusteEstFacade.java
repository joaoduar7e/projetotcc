/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.AjusteEst;
import entidades.Pecas;
import entidades.Vendas;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class AjusteEstFacade extends AbstractFacade<AjusteEst> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AjusteEstFacade() {
        super(AjusteEst.class);
    }

    public void ajusteMerge(AjusteEst ae){
        em.merge(ae);
    }

}
