package com.aimprosoft.dao;

import com.aimprosoft.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO<T> {

    void delete(Department department) throws SQLException;

    void update(T entity) throws SQLException;

    List<T> getAll() throws SQLException;

    Department getByID(Long id) throws SQLException;

    Department existNameInDB(String name) throws SQLException;
}