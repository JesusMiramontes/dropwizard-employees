package com.miramontes.controller;

import com.miramontes.config.DatabaseConfig;
import com.miramontes.dao.EmployeeDao;
import com.miramontes.model.EmployeeModel;
import com.miramontes.runner.EmployeeAppRunner;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class EmployeeTestUsingDocumentation {

//    @Rule
//    public final DropwizardAppExtension<DatabaseConfig> RULE =
//            new DropwizardAppExtension<DatabaseConfig>(EmployeeAppRunner.class,
//                    ResourceHelpers.resourceFilePath("test.yml"),
//                    ConfigOverride.config("server.applicationConnectors[0].port", "0"),
//                    ConfigOverride.config("server.adminConnectors[0].port", "0"));

    private static final EmployeeDao DAO = mock(EmployeeDao.class);

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new EmployeeController(DAO))
            .build();
    private EmployeeModel person;

    @BeforeEach
    void setup() {
        person = new EmployeeModel();
        person.setEmp_id(2);
    }

    @AfterEach
    void tearDown() {
        reset(DAO);
    }

    @Test
    void getPersonSuccess() {
        when(DAO.getEmployeeById(2)).thenReturn(person);

        EmployeeModel found = EXT.target("/employee/2").request().get(EmployeeModel.class);

        assertEquals(found.getEmp_id(),person.getEmp_id());
        verify(DAO).getEmployeeById(2);
    }

    @Test
    void getPersonNotFound() {


        final Response response = EXT.target("/employee/2").request().get();

        assertEquals(response.getStatusInfo().getStatusCode(),Response.Status.NOT_FOUND.getStatusCode());
    }

}
