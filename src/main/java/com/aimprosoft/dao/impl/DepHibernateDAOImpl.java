package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.AGenericDAO;
import com.aimprosoft.dao.DepartmentDAO;
import com.aimprosoft.model.Department;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DepHibernateDAOImpl extends AGenericDAO<Department> implements DepartmentDAO {

    private static final String GET_DEP_BY_NAME = "from Department where name=:name";

    public DepHibernateDAOImpl() {
        super(Department.class);
    }

    @Override
    public Department getByName(String depName) {
        Query query = getCurrentSession().
                createQuery(GET_DEP_BY_NAME);
        query.setParameter("name", depName);
        return (Department) query.uniqueResult();
    }

}

