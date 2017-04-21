
package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.AGenericDAO;
import com.aimprosoft.dao.EmployeeDAO;
import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpHibernateDAOImpl extends AGenericDAO<Employee> implements EmployeeDAO {
    private static  final String GET_EMP = "from Employee e where department=:dep";

    public EmpHibernateDAOImpl() {
        super(Employee.class);
    }

    @Override
    public List<Employee> getAllByDepartment(Department department) {
        return getCurrentSession().createQuery(GET_EMP).setParameter("dep", department).list();
    }


}


