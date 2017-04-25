package com.aimprosoft.dao;

import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;

import java.util.List;

public interface EmployeeDAO extends IGenericDAO<Employee> {

    List<Employee> getAllByDepartment(Department department);
}