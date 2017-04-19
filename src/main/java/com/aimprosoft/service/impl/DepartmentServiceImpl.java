package com.aimprosoft.service.impl;


import com.aimprosoft.dao.DepartmentDAO;
import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.service.DepartmentService;
import com.aimprosoft.util.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@EnableTransactionManagement
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private CustomValidator validator;

    @Transactional
    @Override
    public void saveOrUpdateDepartment(Department department) throws ValidateExp, DaoExp {
          validator.validate(department);
          departmentDAO.update(department);
    }

    @Transactional
    @Override
    public void deleteDepartment(Long longId) throws DaoExp {
        Department department = departmentDAO.getByID(longId);
        departmentDAO.delete(department);
    }

    @Transactional(readOnly = true)
    @Override
    public Department getDepartmentById(Long depId) throws DaoExp {
        return departmentDAO.getByID(depId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Department> getAll() throws DaoExp {
        return  (List<Department>) departmentDAO.getAll();
    }

}