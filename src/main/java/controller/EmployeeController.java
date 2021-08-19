package controller;

import dao.EmployeeDao;
import model.EmployeeModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GET
    public Response getEmployees(){
        return Response.ok(Arrays.asList(new EmployeeModel(1,"jesus", "miramontes", "j@email.com", "mex", "pos1"))).build();
    }
}
