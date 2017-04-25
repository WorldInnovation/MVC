package com.aimprosoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


public class AGenericDAO<T> implements IGenericDAO<T> {

    private Class<T> clazz;

    @Autowired
    SessionFactory sessionFactory;


    public AGenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getByID(Long id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public void update(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}

