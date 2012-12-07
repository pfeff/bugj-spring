package com.mbpfefferle.bugj.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractJpaDao<T> implements Dao<T> {

    @PersistenceContext
    private EntityManager em;
    private Class<T> klass;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    protected AbstractJpaDao(Class<T> klass) {
        this.klass = klass;
    }

    public void save(T t) {
        em.persist(t);
        em.flush();
    }

    public T find(int id) {
        T t = em.find(klass, id);
        if (null == t) {
            throw new EntityNotFoundException(String.format("ID: %s", id));
        }
        return t;
    }

}


