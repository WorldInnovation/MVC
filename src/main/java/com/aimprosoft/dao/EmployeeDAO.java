package com.aimprosoft.dao;

import com.aimprosoft.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO<T> {

    void delete (T entity) throws  SQLException;
    void update ( T entity) throws SQLException;
    List<T> getAll(Long depID) throws SQLException;
    Employee getEmpByID (Long id) throws SQLException;
}