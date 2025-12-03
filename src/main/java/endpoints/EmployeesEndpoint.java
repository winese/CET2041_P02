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
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No record found")
                        .build();
            }
        }
        catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("There is issue during query")
                    .build();
        }
    }

    /**
     * This is for endpoint 2 search employee by employee number
     * Below is the Query Params for user reference
     * Key empNo - the employee number (Integer) and is mandatory
     * @param empNo employee number
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
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("No record found")
                            .build();
                }
            }
        }
        catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("There is issue during query")
                    .build();
        }
    }

    /**
     * This is for endpoint 3 search employee by department number with page number
     * Below is the Query Params for user reference
     * Key empNo - the employee number (Integer) and is mandatory
     * Key pgNo - the page number (Integer), by default is 1
     * @param deptNo department number
     * @param pgNo page number
     * @return response
     */
    @GET
    @Path("/searchDepartmentEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDepartmentEmployees(@QueryParam("deptNo") String deptNo,
                                         @DefaultValue("1") @QueryParam("pgNo") int pgNo) {
        Service service = new Service();

        try {
            if (deptNo == null || Objects.equals(deptNo, "")
                    || deptNo.length() != 4 || pgNo <= 0) {
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
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("No record found")
                            .build();
                }
            }
        }
        catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Inputs")
                    .build();
        }
    }

    /**
     * This is for endpoint 4 to promote an employee
     * Below is the JSON format for user refer to the structure
     *{
     *   "empNo": 10003,
     *   "newSalary": 90000,
     *   "newTitle": "Manager",
     *   "newDeptNo": "d001",
     *   "newStartDate": "13/12/2025",
     *   "isManager": true
     *}
     * Where:
     * empNo is the employee number (Integer) and is mandatory
     * At least one from the below will be fine:
     * newSalary is the new salary (Integer)
     * newtitle is the new title (String)
     * newDeptNo is the new department number (String)
     * newStartDate is the new start date if any (LocalDate)
     * isManager is the indicator whether promote an employee to manager (Boolean)
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
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("There is issue during promotion")
                    .build();
        }
    }
}