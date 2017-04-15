
package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.EmployeeDAO;
import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("employeeDAO")
public class EmpHibernateDAOImpl implements EmployeeDAO<Employee> {

    private static  final String GET_EMP = "from Employee e where e.department=:dep";

    private final SessionFactory sessionFactory;

    @Autowired
    public EmpHibernateDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Employee employee) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
            session.delete(employee);
    }

    @Override
    public void update(Employee employee) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
            session.save(employee);
    }

    @Override
    public List<Employee> getAll(Department department) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        return (List<Employee>) session.createQuery(GET_EMP).setParameter("dep", department).list();
    }

    @Override
    public Employee getEmpByID(Long depID) throws SQLException {
        Long lEmpID = Long.valueOf(depID);
        Session session = sessionFactory.getCurrentSession();
        return  session.get(Employee.class, lEmpID);

    }
}


