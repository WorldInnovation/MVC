package com.aimprosoft.service;

import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentService {

    List <Department> getAll () throws DaoExp;
    void saveOrUpdateDepartment(Department department) throws ValidateExp, DaoExp;
    void deleteDepartment (Long longId) throws DaoExp;
    Department getDepartmentById(Long depId) throws DaoExp;
}