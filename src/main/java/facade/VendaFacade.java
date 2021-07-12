/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Vendas;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Joao Duarte
 */

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

}
