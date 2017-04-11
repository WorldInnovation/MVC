package com.aimprosoft.service;

import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentService {

    List <Department> getAll () throws  SQLException;
    void saveOrUpdateDepartment(Department department) throws ValidateExp, SQLException;
    void deleteDepartment (Long longId) throws  SQLException;
    Department getDepartmentById(Long depId) throws SQLException;
}