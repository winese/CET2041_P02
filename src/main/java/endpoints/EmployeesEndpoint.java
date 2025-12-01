package endpoints;

import DTO.EndPoint3DTO;
import entities.DeptEmployees;
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
        if (allDeps!=null) return Response.ok().entity(allDeps).build();
        else return Response.noContent().build();
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
    public Response endPoint3PlaceHolder(@QueryParam("deptNo") String deptNo) {
        EmployeesRepo employeesRepo = new EmployeesRepo();
        List<EndPoint3DTO> results = employeesRepo.findEndPoint3Infos(deptNo);
        if (results != null) {
            return Response.ok().entity(results).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/endPoint3PlaceHolderName2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endPoint3PlaceHolder(@QueryParam("deptNo") String deptNo,
                                         @QueryParam("pgNo") int pgNo) {
        EmployeesRepo employeesRepo = new EmployeesRepo();
        List<DeptEmployees> results = employeesRepo.findEndPoint3Infos(deptNo, pgNo);
        if (results != null) {
            return Response.ok().entity(results).build();
        } else {
            return Response.noContent().build();
        }
    }

//    @POST
//    @Path("/post/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postNotification(Message notification) {
//        return Response.status(201).entity(notification).build();
//    }
}