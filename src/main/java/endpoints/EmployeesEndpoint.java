package endpoints;

import DTO.EndPoint3DTO;
import DTO.Promotion;
import Service.Service;
import entities.Departments;
import entities.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import EntityManagerFactory.AppEntityManagerFactory;

import java.util.List;
import java.util.Objects;

@Path("/employees")
public class EmployeesEndpoint {

    // ! ENDPOINT CALLS SERVICE, SERVICE CALLS REPO

    //NEED TO CHECK WHEN JSON EMPTY, NOW STILL STATUS 200
    @GET
    @Path("/ping")
    public Response ping() {
//        return Response.ok().entity("Service online").build();
        EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
        EntityManager em = emf.createEntityManager();
        String result = (String) em.createNativeQuery("SELECT DATABASE()").getSingleResult();
        return Response.ok("Connected to: " + result).build();

    }

    // ! ENDPOINT 1
    @GET
    @Path("/getAllDepartments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        Service service = new Service();

        try {
            List<Departments> departments = service.getAllDepartments();
            if (!departments.isEmpty()) {
                return Response.ok().entity(departments).build();
            }
            else {
                return Response.noContent().build();
            }
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ! ENDPOINT 2
    @GET
    @Path("/searchEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployee(@QueryParam("empNo") long empNo) {
        Service service = new Service();

        try {
            if (empNo <= 0 || empNo > 99999999999L) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid employee number")
                        .build();
            }
            else {
                Employees employee = service.getEmployeeById(empNo);
                if (employee != null) {
                    return Response.ok().entity(employee).build();
                }
                else {
                    return Response.noContent().build();
                }
            }
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ! ENDPOINT 3
    @GET
    @Path("/searchDepartmentEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endPoint3PlaceHolder(@QueryParam("deptNo") String deptNo,
                                         @DefaultValue("1") @QueryParam("pgNo") int pgNo) {
        Service service = new Service();

        try {
            if (deptNo == null || Objects.equals(deptNo, "") || deptNo.length() != 4 || pgNo <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid parameters")
                        .build();
            }
            else {
                List<EndPoint3DTO> details = service.getEndPoint3Infos(deptNo, pgNo);
                if (!details.isEmpty()) {
                    return Response.ok().entity(service.getEndPoint3Infos(deptNo, pgNo)).build();
                }
                else {
                    return Response.noContent().build();
                }
            }
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ! Endpoint 4
    @POST
    @Path("/promoteEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promoteEmployee(Promotion promotion) {
        Service service = new Service();

        try {
            if (promotion == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid parameter, empty JSON")
                        .build();
            }
            else {
                String result = service.promoteEmployee(promotion);
                if (result != null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(result)
                            .build();
                } else {
                    return Response.status(201).entity("Promoted: " + promotion.getEmpNo()).build();
                }
            }
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}