package com.aimprosoft.dao;

import com.aimprosoft.model.Department;

public interface DepartmentDAO extends IGenericDAO<Department> {

    Department getByName(String depName);

}