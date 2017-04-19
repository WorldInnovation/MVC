package com.aimprosoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

    @Repository()
    public class AGenericDAO < T extends Serializable>{
        private Class< T > clazz;

        @Autowired
        SessionFactory sessionFactory;

        public void setClazz(Class<T> clazzToSet){
            this.clazz = clazzToSet;
        }
        public T getById( long id ){
            return (T) getCurrentSession().get( clazz, id );
        }
        public List< T > findAll(){
            return getCurrentSession().createQuery( "from " + clazz.getName() ).list();
        }
        public T getByName(String name)  {
            return (T) getCurrentSession().get(clazz,name);
        }
        public void update( T entity ){
            getCurrentSession().saveOrUpdate(entity);
        }
        public void delete( T entity ){
            getCurrentSession().delete( entity);
        }
        public void deleteById( long entityId ) {
            T entity = getById( entityId );
            delete( entity );
        }

        protected Session getCurrentSession() {
            return sessionFactory.getCurrentSession();
        }

    }

