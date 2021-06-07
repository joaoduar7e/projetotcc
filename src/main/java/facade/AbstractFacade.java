/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import utils.Transacional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author autor
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transacional
    public void salvar(T entity) {
        getEntityManager().merge(entity);
    }

    @Transacional
    public void remover(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T buscar(Object id) {
        return getEntityManager().find(entityClass, id);
        
    }

    public List<T> listaTodos() {
        Query q = getEntityManager().createQuery("from "
                + entityClass.getSimpleName());
        return q.getResultList();
    }
    
    public List<T> listaFiltrando(String filtro, String... atributos) {

        String hql = "from " + entityClass.getSimpleName() + " obj where ";

        for (String atributo : atributos) {

            hql += "lower(obj." + atributo + ") like :filtro OR ";

        }

        hql = hql.substring(0, hql.length() - 3);

        Query q = getEntityManager().createQuery(hql);

        q.setParameter("filtro", "%" + filtro.toLowerCase() + "%");

        return q.getResultList();

    }
}
