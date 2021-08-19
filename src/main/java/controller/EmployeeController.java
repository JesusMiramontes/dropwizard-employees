package controller;

import com.codahale.metrics.annotation.Timed;
import dao.EmployeeDao;
import io.dropwizard.hibernate.UnitOfWork;
import model.EmployeeModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
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
}
