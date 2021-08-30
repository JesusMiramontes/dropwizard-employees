package com.miramontes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miramontes.model.EmployeeModel;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    private EmployeeModel test_employee_fixture;

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static Validator validator;

    private static final String INVALID_EMAIL_FORMAT_ERROR_MESSAGE = "debe coincidir con \".+@.+\\.[a-z]+\"";
    private static final String EMPTY_ERROR_MESSAGE = "no debe estar vacío";
    private static final String LENGTH_MISMATCH_ERROR_MESSAGE = "la longitud debe estar entre 2 y 255";

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        test_employee_fixture = MAPPER.readValue(fixture("fixtures/test_employee.json"), EmployeeModel.class);
    }

    @Test
    public void serializesToJSON() throws Exception {
        final EmployeeModel employee = new EmployeeModel(100, "NAME", "LASTNAME", "EMAIL", "COUNTRY", "POSITION");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/test_employee.json"), EmployeeModel.class));

        assertEquals(MAPPER.writeValueAsString(employee), expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final EmployeeModel employee = new EmployeeModel(100, "NAME", "LASTNAME", "EMAIL", "COUNTRY", "POSITION");

        EmployeeModel stored = MAPPER.readValue(fixture("fixtures/test_employee.json"), EmployeeModel.class);
        assertEquals(stored.getEmp_id(), employee.getEmp_id());
        assertEquals(stored.getName(), employee.getName());
        assertEquals(stored.getLastname(), employee.getLastname());
        assertEquals(stored.getCountry(), employee.getCountry());
        assertEquals(stored.getEmail(), employee.getEmail());
        assertEquals(stored.getPosition(), employee.getPosition());

    }

    @Test
    public void validate_not_null_or_empty() throws Exception {
        EmployeeModel employee = new EmployeeModel();

        Set<ConstraintViolation<EmployeeModel>> constraintViolations =
                validator.validate(employee);

        assertEquals(5, constraintViolations.size());
        assertEquals(EMPTY_ERROR_MESSAGE, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void invalidEmail() throws JsonProcessingException {
        Set<ConstraintViolation<EmployeeModel>> constraintViolations =
                validator.validate(test_employee_fixture);

        assertEquals(1, constraintViolations.size());
        assertEquals(INVALID_EMAIL_FORMAT_ERROR_MESSAGE, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validEmail(){
        test_employee_fixture.setEmail("email@domain.com");
        Set<ConstraintViolation<EmployeeModel>> constraintViolations =
                validator.validate(test_employee_fixture);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void belowMinimumLength(){
        EmployeeModel employee = new EmployeeModel(100,"X", "X","X","X","X");
        Set<ConstraintViolation<EmployeeModel>> constraintViolations =
                validator.validate(employee);
        assertEquals(6,constraintViolations.size());
    }

    @Test
    public void aboveMaxLength(){
        String text = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

        EmployeeModel employee = new EmployeeModel(100,text,text,text,text,text);
        Set<ConstraintViolation<EmployeeModel>> constraintViolations =
                validator.validate(employee);

        assertEquals(6,constraintViolations.size());
    }

}
