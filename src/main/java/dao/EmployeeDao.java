package dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class EmployeeDao extends AbstractDAO<EmployeeDao> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public EmployeeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
