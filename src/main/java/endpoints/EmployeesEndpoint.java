package endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import entities.Departments;
import entities.Employees;
import repositories.DepartmentsRepo;
import repositories.EmployeesRepo;

@Path("/employees")
public class EmployeesEndpoint {

    //can remove later
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    //sample code
    @GET
    @Path("/getDepartment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDepartment() {
        DepartmentsRepo departmentsRepo = new DepartmentsRepo();
        Departments dep = null;
        dep = departmentsRepo.findDepartment("d009");
        return Response.ok()
                .entity(dep)
                .build();
    }

    //endpoint 2
    @GET
    @Path("/searchEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployee(@QueryParam("empNo") int empNo) {
        EmployeesRepo employeesRepo = new EmployeesRepo();
        Employees emp = null;
        emp = employeesRepo.findEmployee(empNo);
        return Response.ok()
                .entity(emp)
                .build();
    }

//    @POST
//    @Path("/post/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postNotification(Message notification) {
//        return Response.status(201).entity(notification).build();
//    }
}