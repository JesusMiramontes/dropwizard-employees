package com.miramontes.modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.ProvisionException;
import org.hibernate.SessionFactory;

public class EmployeeModule implements Module {

    private SessionFactory sessionFactory;

    public EmployeeModule() {
    }

    public EmployeeModule(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void configure(Binder binder) {

    }

    @Provides
    SessionFactory providesSessionFactory() {

        if (sessionFactory == null) {
            throw new ProvisionException("The Hibernate session factory has not yet been set. This is likely caused by forgetting to call setSessionFactory during Application.run()");
        }

        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
