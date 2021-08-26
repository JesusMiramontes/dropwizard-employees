package com.miramontes.controller;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.miramontes.dao.EmployeeDao;
import io.dropwizard.hibernate.UnitOfWork;
import com.miramontes.model.EmployeeModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Set;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    private EmployeeDao employeeDao;
//    private final Validator validator;

    @Inject
    public EmployeeController(EmployeeDao employeeDao/*, Validator validator*/) {
        this.employeeDao = employeeDao;
//        this.validator = validator;
    }

    @GET
    @Timed
    @UnitOfWork
    public Response getEmployees(){
        return Response.ok(employeeDao.findAll()).build();
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Integer id){
        EmployeeModel employee = employeeDao.getEmployeeById(id);
        if(employee != null)
            return Response.ok(employee).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Timed
    @UnitOfWork
    public Response insertEmployee(EmployeeModel employee){
//        // Validation
//        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employee);
//
//        if(violations.size() > 0){
//            ArrayList<String> validationMessages = new ArrayList<>();
//            for (ConstraintViolation<EmployeeModel>violation : violations){
//                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
//            }
//            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
//        }
        
        employeeDao.insertEmployee(employee);
        return Response.ok().build();
    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") Integer id, EmployeeModel employee){
//        // Validation
//        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employee);
//
//        if(violations.size() > 0){
//            ArrayList<String> validationMessages = new ArrayList<>();
//            for (ConstraintViolation<EmployeeModel>violation : violations){
//                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
//            }
//            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
//        }

        EmployeeModel foundEmployee = employeeDao.getEmployeeById(id);
        if(foundEmployee == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        employee.setEmp_id(id);
        employeeDao.update(employee);
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Integer id){
        EmployeeModel employee = employeeDao.getEmployeeById(id);
        if(employee == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        employeeDao.delete(employee);
        return Response.ok().build();
    }
}
