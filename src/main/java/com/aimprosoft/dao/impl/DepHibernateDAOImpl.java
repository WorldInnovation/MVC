package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.DepartmentDAO;
import com.aimprosoft.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("departmentDAO")
public class DepHibernateDAOImpl implements DepartmentDAO<Department> {

    private static final String GET_ALL_DEP = "from Department";
    private static final String GET_DEP_BY_NAME = "from Department where name=:name";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(Long longId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(longId);
    }

    @Override
    public void update(Department entity) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public List<Department> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return (List<Department>) session.createQuery(GET_ALL_DEP).list();
    }

    @Override
    public Department getByID(Long s) throws SQLException {
        Long depId = Long.valueOf(s);
        Session session = sessionFactory.getCurrentSession();
        return (Department) session.get(Department.class, depId);
    }

    @Override
    public Department existNameInDB(String depName) throws SQLException {
        return (Department) sessionFactory.getCurrentSession()
                .createQuery(GET_DEP_BY_NAME)
                .setParameter("name", depName);
    }

}

