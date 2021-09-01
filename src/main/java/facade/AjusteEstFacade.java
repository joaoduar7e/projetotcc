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
import javax.persistence.Query;
import java.util.List;

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

    public List<AjusteEst> listaAjusteEstoq() {
        Query q = getEntityManager().createQuery("from "
                + "AjusteEst as ae "
                + "order by ae.id desc");
        return q.getResultList();
    }

}
