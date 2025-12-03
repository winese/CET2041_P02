package endpoints;

import DTO.Promotion;
import Service.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import EntityManagerFactory.AppEntityManagerFactory;

@Path("/employees")
public class EmployeesEndpoint {

    // ! ENDPOINT CALLS SERVICE, SERVICE CALLS REPO

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
        return Response.ok().entity(service.getAllDepartments()).build();
    }

    // ! ENDPOINT 2
    @GET
    @Path("/searchEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployee(@QueryParam("empNo") int empNo) {
        try {
            Service service = new Service();
            return Response.ok().entity(service.getEmployeeById(empNo)).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().entity("Invalid employee ID").build();
        }
    }

    // ! ENDPOINT 3
    @GET
    @Path("/searchDepartmentEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endPoint3PlaceHolder(@QueryParam("deptNo") String deptNo,
                                         @DefaultValue("1") @QueryParam("pgNo") int pgNo) {
        try {
            Service service = new Service();
            return Response.ok().entity(service.getEndPoint3Infos(deptNo, pgNo)).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().entity("Invalid department Number").build();
        }
    }

    // ! Endpoint 4
    @POST
    @Path("/promoteEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promoteEmployee(Promotion promotion) {
        Service service = new Service();
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