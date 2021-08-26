package com.miramontes.controller;

import com.miramontes.dao.EmployeeDao;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.validation.Validator;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.mock;

@ExtendWith(DropwizardExtensionsSupport.class)
class EmployeeControllerTest {

    private static final String EMPLOYEES_ENDPOINT = "/employee";
    private static final EmployeeDao DAO = mock(EmployeeDao.class);
    private static final Validator VALIDATOR = mock(Validator.class);

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new EmployeeController(DAO/*, VALIDATOR)*/)).build();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getEmployees() {
        Client client = EXT.client();
        Response response = client.target("http://localhost:8080/employee/2").request().get();
        response.getStatus();
//        EmployeeModel response = EXT.target(EMPLOYEES_ENDPOINT+"/2").request().get(EmployeeModel.class);
    }

    @org.junit.jupiter.api.Test
    void getEmployeeById() {
    }

    @org.junit.jupiter.api.Test
    void insertEmployee() {
    }

    @org.junit.jupiter.api.Test
    void updateEmployee() {
    }

    @org.junit.jupiter.api.Test
    void deleteEmployee() {
    }
}