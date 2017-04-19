package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.DepartmentDAO;
import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDAO")
public class DepHibernateDAOImpl implements DepartmentDAO<Department> {

    private static final String GET_ALL_DEP = "from Department";
    private static final String GET_DEP_BY_NAME = "from Department where name=:name";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(Department department) throws DaoExp {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(department);
        }catch (Exception e){
            throw new DaoExp( e.getMessage());
        }
    }

    @Override
    public void update(Department entity) throws DaoExp {
        Session session = sessionFactory.getCurrentSession();
        try{
            session.saveOrUpdate(entity);
        }catch (Exception e){
            throw new DaoExp(e.getMessage());
        }
    }

    @Override
    public List<Department> getAll() throws DaoExp {
        Session session = sessionFactory.getCurrentSession();
        try{
            return (List<Department>) session.createQuery(GET_ALL_DEP).list();
        }catch (Exception e){
            throw new DaoExp(e.getMessage());
        }
    }

    @Override
    public Department getByID(Long s) throws DaoExp {
        Long depId = Long.valueOf(s);
        Session session = sessionFactory.getCurrentSession();
        try{
            return  session.get(Department.class, depId);
        }catch (Exception e){
            throw new DaoExp(e.getMessage());
        }
    }

    @Override
    public Department existNameInDB(String depName) throws DaoExp {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.
                createQuery(GET_DEP_BY_NAME);
        query.setParameter("name", depName);
        try{
            return (Department) query.uniqueResult();
        }catch (Exception e){
            throw new DaoExp(e.getMessage());
        }
    }

}

