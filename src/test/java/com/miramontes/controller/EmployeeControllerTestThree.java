package com.miramontes.controller;

import com.miramontes.config.DatabaseConfig;
import com.miramontes.dao.EmployeeDao;
import com.miramontes.model.EmployeeModel;
import com.miramontes.runner.EmployeeAppRunner;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.dropwizard.testing.junit.ResourceTestRule;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
import org.junit.After;
import org.junit.Before;

import javax.validation.Validator;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.mock;

@RunWith(DataProviderRunner.class)
class EmployeeControllerTestThree {

    private static EmployeeDao dao = mock(EmployeeDao.class);
//    private static Validator validator = mock(Validator.class);
//
//    public static final DropwizardAppExtension<DatabaseConfig> app = new DropwizardAppExtension<>(EmployeeAppRunner.class,
//            "tesasdt.yml");

    @ClassRule
    public static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(new EmployeeController(dao))
            .build();

//    public static final ResourceTestRule resources = ResourceTestRule.builder()
//            .addResource(new PersonResource(dao))
//            .build();


    private Session session;

    private static HibernateBundle<DatabaseConfig> hibernate = new HibernateBundle<DatabaseConfig>(EmployeeModel.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DatabaseConfig configuration) {
            return configuration.getDatabase();
        }
    };

    @BeforeEach
    void setUp() {
        int i = 0;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEmployees() {
        int j = 0;
        try{
//            Response response = RESOURCES.client().target("/employee/2").request().get();
            Response response = RESOURCES.target("/employee/2").request().get();
            int i = 0;
        } catch(Exception e){
            throw e;
        }
        int i = 0;
    }

    @Test
    void getEmployeeById() {
        EmployeeController controller = new EmployeeController(dao);
        controller.getEmployeeById(2);
    }

    @Test
    void insertEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}