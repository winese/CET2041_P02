package endpoints;

import entities.Departments;
import entities.Employees;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositories.DepartmentsRepo;
import repositories.EmployeesRepo;


@Path("/employees")
public class EmployeesEndpoint {

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Path("/getDepartment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification() {
        DepartmentsRepo departmentsRepo = new DepartmentsRepo();
        Departments dep = null;
        dep = departmentsRepo.findDepartment("d009");
        return Response.ok()
                .entity(dep)
                .build();
    }

//    public Response getNotification(@PathParam("empNo") int empNo) {
//        DepartmentsRepo departmentsRepo = new DepartmentsRepo();
//        Departments dep = null;
//        dep = departmentsRepo.findDepartment("d009");
//        return Response.ok()
//                .entity(dep)
//                .build();
//    }
//
//    @POST
//    @Path("/post/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postNotification(Message notification) {
//        return Response.status(201).entity(notification).build();
//    }
}