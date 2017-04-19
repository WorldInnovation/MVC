package com.aimprosoft.dao;


import java.io.Serializable;
import java.util.List;

public interface IGenericDAO <T extends Serializable> {
    List<T> getAll() ;
    void update ( T entity) ;
    void delete (T entity) ;
    T getByID (Long id) ;
    T getByName (String name) ;
}