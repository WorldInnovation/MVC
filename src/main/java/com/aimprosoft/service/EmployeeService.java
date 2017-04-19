package com.aimprosoft.service;


import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Employee;

import com.aimprosoft.exeption.DaoExp;
import java.util.List;


public interface EmployeeService {

    void updateEmployee (Employee employee, Long depId) throws ValidateExp, DaoExp;
    void deleteEmployee (Employee employee) throws  DaoExp;
    List<Employee> listEmployee (Long depId) throws DaoExp;
    Employee getEmpByID (Long empId)   throws DaoExp;

}