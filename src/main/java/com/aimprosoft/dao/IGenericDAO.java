package com.aimprosoft.dao;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGenericDAO<T> {
    List<T> getAll();

    void update(T entity);

    void delete(T entity);

    T getByID(Long id);

}