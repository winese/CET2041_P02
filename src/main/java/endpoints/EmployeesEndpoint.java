package endpoints;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import entities.Departments;
import entities.Employees;
import Service.Service;
import DTO.EndPoint3DTO;
import DTO.Promotion;

/**
 * Endpoint
 */
@Path("/employees")
public class EmployeesEndpoint {

    /**
     * This is for endpoint 1 Get all departments
     * @return response
     */
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

    /**
     * This is for endpoint 2 search employee by employee number
     * @param @QueryParam("empNo") long empNo employee number
     * @return response
     */
    @GET
    @Path("/searchEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployee(@QueryParam("empNo") long empNo) {
        Service service = new Service();

        try {
            if (empNo <= 0) {
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

    /**
     * This is for endpoint 3 search employee by department number with page number
     * @param @QueryParam("deptNo") String deptNo, @DefaultValue("1") @QueryParam("pgNo") int pgNo
     *        department number, page number
     * @return response
     */
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

    /**
     * This is for endpoint 4 to promote an employee
     * @param promotion promotion
     * @return response
     */
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