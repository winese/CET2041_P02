package endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import entities.Departments;
import entities.Employees;
import repositories.DepartmentsRepo;
import repositories.EmployeesRepo;

import java.util.List;

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

    //endpoint 1
    @GET
    @Path("/getAllDepartments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAllDepartments() {
        DepartmentsRepo departmentsRepo = new DepartmentsRepo();
        List<Departments> allDeps = null;
        allDeps = departmentsRepo.findAllDepartments();
        if (allDeps.isEmpty()) return Response.status(Response.Status.NO_CONTENT).build();
        else return Response.ok().entity(allDeps).build();
    }

    //endpoint 2
    @GET
    @Path("/searchEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployee(@QueryParam("empNo") int empNo) {
        EmployeesRepo employeesRepo = new EmployeesRepo();
        Employees emp = null;
        emp = employeesRepo.findEmployee(empNo);
        if (emp != null) {
            return Response.ok().entity(emp).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/endPoint3PlaceHolderName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endPoint3PlaceHolder() {
        EmployeesRepo employeesRepo = new EmployeesRepo();
        DepartmentsRepo departmentsRepo = new DepartmentsRepo();
        List<Integer> empNo =
                departmentsRepo.findAllEmployeesInDepartment("deptNo");
        List<Employees> employees = null;


    }

//    @POST
//    @Path("/post/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postNotification(Message notification) {
//        return Response.status(201).entity(notification).build();
//    }
}