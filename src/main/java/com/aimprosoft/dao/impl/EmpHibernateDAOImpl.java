
package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.EmployeeDAO;
import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDAO")
public class EmpHibernateDAOImpl implements EmployeeDAO<Employee> {

    private static final String GET_EMP = "from Employee e where e.department=:dep";

    private final SessionFactory sessionFactory;

    @Autowired
    public EmpHibernateDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Employee employee) throws DaoExp {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(employee);
        } catch (Exception e) {
            throw new DaoExp(e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) throws DaoExp {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(employee);
        } catch (Exception e) {
            throw new DaoExp(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAll(Department department) throws DaoExp {

        Session session = sessionFactory.getCurrentSession();
        try {
            return (List<Employee>) session.createQuery(GET_EMP).setParameter("dep", department).list();
        } catch (Exception e) {
            throw new DaoExp(e.getMessage());
        }
    }

    @Override
    public Employee getEmpByID(Long depID) throws DaoExp {
        Long lEmpID = Long.valueOf(depID);
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.get(Employee.class, lEmpID);
        } catch (Exception e) {
            throw new DaoExp(e.getMessage());
        }
    }
}


