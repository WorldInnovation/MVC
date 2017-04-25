package com.aimprosoft.dao;

import com.aimprosoft.exeption.DaoExp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class AGenericDAO<T> implements IGenericDAO<T> {

    private Class<T> clazz;

    @Autowired
    SessionFactory sessionFactory;


    public AGenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getByID(Long id) throws DaoExp {

        try{
            return getCurrentSession().get(clazz, id);
        }catch (Exception e){
            throw new DaoExp(e.getMessage());
        }
    }

    public List<T> getAll() throws DaoExp {
        try{
            return getCurrentSession().createQuery("from " + clazz.getName()).list();
        }catch (Exception e){
            throw new DaoExp(e.getMessage());
        }
    }

    public void update(T entity) throws DaoExp {
        try {
            getCurrentSession().saveOrUpdate(entity);
        }catch (Exception e){
            throw  new DaoExp( e.getMessage());
        }
    }

    public void delete(T entity) throws DaoExp {
        try{
            getCurrentSession().delete(entity);
        }catch (Exception e){
            throw new DaoExp(e.getMessage());
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}

