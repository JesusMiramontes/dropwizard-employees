package com.miramontes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miramontes.config.DatabaseConfig;
import com.miramontes.dao.EmployeeDao;
import com.miramontes.model.EmployeeModel;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.configuration.YamlConfigurationFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jersey.validation.Validators;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import javax.validation.Validator;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(DataProviderRunner.class)
class EmployeeControllerTest {

    private EmployeeDao dao;
    private EmployeeController controller;
    private static Validator validator = mock(Validator.class);

//    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private static HibernateBundle<DatabaseConfig> hibernate = new HibernateBundle<DatabaseConfig>(EmployeeModel.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DatabaseConfig configuration) {
            return configuration.getDatabase();
        }
    };

    @BeforeEach
    void setUp() throws IOException, ConfigurationException {
        final ObjectMapper objectMapper = Jackson.newObjectMapper();
        final Validator validator = Validators.newValidator();
        final YamlConfigurationFactory<DatabaseConfig> factory = new YamlConfigurationFactory<>(DatabaseConfig.class, validator, objectMapper, "dw");

//        final File yaml = new File(Thread.currentThread().getContextClassLoader().getResource("dev.yml").getPath());
        final File yaml = new File("dev.yml");
        final DatabaseConfig configuration = factory.build(yaml);
        hibernate.getDataSourceFactory(configuration);


//        SessionFactory sessionFact = (SessionFactory) context.getBean("sessionFactory");
//        Session session =  sessionFact.openSession();

        int i = 0;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEmployees() {
    }

    @Test
    void getEmployeeById() {
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