package com.aimprosoft.dao;

import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import com.aimprosoft.exeption.DaoExp;

import java.util.List;

public interface EmployeeDAO<T> {

    void delete (T entity) throws  DaoExp;
    void update ( T entity) throws DaoExp;
    List<T> getAll(Department department) throws DaoExp;
    Employee getEmpByID (Long id) throws DaoExp;
}