package com.aimprosoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

    @Repository()
    public class AGenericDAO < T > implements IGenericDAO<T>{

        private Class<T> clazz;

        @Autowired
        SessionFactory sessionFactory;

/*        public AGenericDAO(Class<T> clazz) {
  Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        }*/



        public void setClazz(Class<T> clazz) {
            this.clazz = clazz;
        }

        public T getByID(Long id ){
            return (T) getCurrentSession().get( clazz, id );
        }
        public List< T > getAll(){
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
        public void deleteByID( Long entityId ) {
            T entity = getByID( entityId );
            delete( entity );
        }

        protected Session getCurrentSession() {
            return sessionFactory.getCurrentSession();
        }

    }

