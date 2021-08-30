package com.miramontes.controller;

import com.miramontes.config.DatabaseConfig;
import com.miramontes.dao.EmployeeDao;
import com.miramontes.model.EmployeeModel;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class EmployeeControllerTestTwo{

    @BeforeEach
    public void run(DatabaseConfig myConfiguration, Environment environment) throws Exception {
        System.out.println("Value from dev.yml is "+myConfiguration.getDatabase().getUser());
        EmployeeDao infoDao = new EmployeeDao(hibernate.getSessionFactory());
//        final EmployeeController resource = new EmployeeController(infoDao, environment.getValidator());
//        environment.jersey().register(resource);

    }

    private HibernateBundle<DatabaseConfig> hibernate = new HibernateBundle<DatabaseConfig>(EmployeeModel.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DatabaseConfig configuration) {
            return configuration.getDatabase();
        }
    };

    @Test
    void getEmployeeById() {
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        int i = 0;
    }
}
