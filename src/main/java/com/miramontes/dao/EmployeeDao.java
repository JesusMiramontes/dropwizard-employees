package com.miramontes.dao;

import io.dropwizard.hibernate.AbstractDAO;
import com.miramontes.model.EmployeeModel;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class EmployeeDao extends AbstractDAO<EmployeeModel> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public EmployeeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<EmployeeModel> findAll() {
        //return list(namedTypedQuery("com.miramontes.EmployeeModel.findAll"));
        CriteriaQuery<EmployeeModel> query = currentSession().getCriteriaBuilder().createQuery(EmployeeModel.class);
        query.from(EmployeeModel.class);
        return currentSession().createQuery(query).getResultList();
    }

    public EmployeeModel getEmployeeById(Integer id){
        try{
            //return (EmployeeModel) namedQuery("com.miramontes.EmployeeModel.findById").setParameter("emp_id", id).getSingleResult();
            return currentSession().get(EmployeeModel.class, id);
        } catch (NoResultException e){
            return null;
        }
    }

    public void insertEmployee(EmployeeModel e){
        currentSession().clear();
        persist(e);
    }

    public void update(EmployeeModel employee){
        currentSession().clear();
        currentSession().update(employee);
    }

    public void delete(EmployeeModel employee){
        currentSession().clear();
        currentSession().delete(employee);
    }
}
