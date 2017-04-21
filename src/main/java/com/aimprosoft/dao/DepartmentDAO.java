package com.aimprosoft.dao;

import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO<T> {

    void delete(Department department) throws DaoExp;

    void update(T entity) throws DaoExp ;

    List<T> getAll() throws DaoExp;

    Department getByID(Long id) throws DaoExp;

    Department existNameInDB(String name) throws DaoExp;
}