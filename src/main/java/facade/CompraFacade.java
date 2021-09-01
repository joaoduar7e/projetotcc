/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Compra;
import entidades.Vendas;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class CompraFacade  extends AbstractFacade<Compra> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

    public List<Compra> listaCompraOrd() {
        Query q = getEntityManager().createQuery("from "
                + "Compra as c "
                + "order by c.id desc");
        return q.getResultList();
    }
}
