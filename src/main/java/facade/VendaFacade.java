/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Agendamento;
import entidades.ItensVenda;
import entidades.Pecas;
import entidades.Vendas;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VendaFacade extends AbstractFacade<Vendas> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Vendas.class);
    }

    public List<Vendas> listaVendaOrd() {
        Query q = getEntityManager().createQuery("from "
                + "Vendas as v "
                + "order by v.id desc");
        return q.getResultList();
    }

}
