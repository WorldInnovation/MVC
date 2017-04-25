package com.aimprosoft.dao;


import com.aimprosoft.exeption.DaoExp;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IGenericDAO<T> {
    List<T> getAll() throws DaoExp;

    void update(T entity) throws DaoExp;

    void delete(T entity) throws DaoExp;

    T getByID(Long id) throws DaoExp;

}