/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Compra;
import entidades.ItensCompra;
import entidades.Pecas;
import entidades.Servico;

import javax.inject.Inject;
import javax.persistence.EntityManager;


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
    @Override
    public void salvar(Compra ve){
        for(ItensCompra it : ve.getItensCompras()){
            Pecas p = it.getPecas();
            p.setQtdEst(p.getQtdEst() - it.getQuantidade());
            em.merge(p);
        }
        super.salvar(ve);
        
    }
}
