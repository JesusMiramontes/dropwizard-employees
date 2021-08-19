package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.EmployeeModel;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
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
        return list(namedTypedQuery("com.miramontes.EmployeeModel.findAll"));
    }

    public EmployeeModel getEmployeeById(Integer id){
        try{
            return (EmployeeModel) namedQuery("com.miramontes.EmployeeModel.findById").setParameter("emp_id", id).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public void save(EmployeeModel e){
        persist(e);
    }
}
