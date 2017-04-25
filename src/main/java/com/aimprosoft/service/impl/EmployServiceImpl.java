package com.aimprosoft.service.impl;


import com.aimprosoft.dao.DepartmentDAO;
import com.aimprosoft.dao.EmployeeDAO;
import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import com.aimprosoft.service.EmployeeService;
import com.aimprosoft.util.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service()
public class EmployServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private CustomValidator validator;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmployee(Employee employee, Long depId) throws ValidateExp, DaoExp {
        Department department = departmentDAO.getByID(depId);
        employee.setDepartment(department);
        validator.validate(employee);
        employeeDAO.update(employee);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmployee(Employee employee) throws DaoExp {

        employeeDAO.delete(employee);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> listEmployee(Long depId) throws DaoExp {
        Department department = departmentDAO.getByID(depId);
        return employeeDAO.getAllByDepartment(department);
    }

    @Transactional(readOnly = true)
    @Override
    public Employee getEmpByID(Long empID) throws DaoExp {
        return employeeDAO.getByID(empID);
    }
}