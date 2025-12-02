package endpoints;

import DTO.EndPoint3DTO;
import DTO.Promotion;
import entities.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import repositories.*;

import java.util.List;
import java.util.Objects;

@Path("/employees")
public class EmployeesEndpoint {

    //can remove later
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    //endpoint 1
    @GET
    @Path("/getAllDepartments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAllDepartments() {
        DepartmentsRepo departmentsRepo = new DepartmentsRepo();
        List<Departments> allDeps = null;
        allDeps = departmentsRepo.findAllDepartments();
        if (allDeps != null) return Response.ok().entity(allDeps).build();
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

//    @GET
//    @Path("/endPoint3PlaceHolderName")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response endPoint3PlaceHolder(@QueryParam("deptNo") String deptNo) {
//        EmployeesRepo employeesRepo = new EmployeesRepo();
//        List<EndPoint3DTO> results = employeesRepo.findEndPoint3Infos(deptNo);
//        if (results != null) {
//            return Response.ok().entity(results).build();
//        } else {
//            return Response.noContent().build();
//        }
//    }

    @GET
    @Path("/endPoint3PlaceHolderName2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endPoint3PlaceHolder(@QueryParam("deptNo") String deptNo,
                                         @DefaultValue("1")
                                         @QueryParam("pgNo") int pgNo) {
        EmployeesRepo employeesRepo = new EmployeesRepo();
        List<EndPoint3DTO> results = employeesRepo.findEndPoint3Infos(deptNo
                , pgNo);
        if (results != null) {
            return Response.ok().entity(results).build();
        } else {
            return Response.noContent().build();
        }
    }

    //endpoint 4
    @POST
    @Path("/promoteEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promoteEmployee(Promotion promotion) {
        if (promotion != null) {
            if (promotion.getEmpNo() != 0) {
                System.out.println("2");
                EmployeesRepo employeesRepo = new EmployeesRepo();
                Employees emp = null;
                emp = employeesRepo.findEmployee(promotion.getEmpNo());
                if (emp != null) {
                    System.out.println("3");
                    DeptEmployeesRepo deptEmployeesRepo = new DeptEmployeesRepo();
                    DeptEmployees currDept = null;
                    DeptEmployees newDept = null;
                    currDept = deptEmployeesRepo.queryLatestDept(promotion.getEmpNo());
                    String deptNo = currDept.getDeptNo();

                    if (promotion.getDeptNo() != null ) {
                        System.out.println("3-1");
                        if (!Objects.equals(deptNo, promotion.getDeptNo())) {
                            deptNo = promotion.getDeptNo();
                        }
                        newDept = deptEmployeesRepo.insertNewDept(deptNo, promotion.getEmpNo());
                    }
                    System.out.println("4");
                    if (!Objects.equals(promotion.getTitle(), "")) {
                        System.out.println("4-1");
                        TitlesRepo titlesRepo = new TitlesRepo();
                        Titles title = null;
                        title = titlesRepo.insertNewEmployeeTitle(promotion.getEmpNo(), promotion.getTitle());
                    }
                    System.out.println("5");
                    if (promotion.isManager()) {
                        System.out.println("5-1");
                        DeptManagerRepo deptManagerRepo = new DeptManagerRepo();
                        DeptManager deptManager = null;
                        deptManager = deptManagerRepo.insertNewDeptManager(deptNo, promotion.getEmpNo());
                    }
                    System.out.println("6");
                    if (promotion.getRaise() != 0) {
                        System.out.println("6-1");
                        SalariesRepo salariesRepo = new SalariesRepo();
                        Salaries salary = null;
                        salary = salariesRepo.insertNewEmployeeSalary(promotion.getEmpNo(), promotion.getRaise());
                    }
                    System.out.println("7");
                    return Response.status(201).entity("Promoted " + promotion.getEmpNo()).build();
                }
                System.out.println("8");
                return Response.noContent().build();
            }
            System.out.println("9");
            return Response.noContent().build();
        }
        System.out.println("10");
        return Response.noContent().build();
    }



//    //endpoint 4
//    @POST
//    @Path("/promoteEmployee")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response promoteEmployee(Promotion promotion) {
//        if (promotion != null) {
//            if (promotion.getEmpNo() != 0) {
//                EmployeesRepo employeesRepo = new EmployeesRepo();
//                Employees emp = null;
//                emp = employeesRepo.findEmployee(promotion.getEmpNo());
//                if (emp != null) {
//                    DeptEmployeesRepo deptEmployeesRepo = new DeptEmployeesRepo();
//                    DeptEmployees currDeptEmployees = null;
//                    DeptEmployees newDeptEmployees = null;
//                    currDeptEmployees = deptEmployeesRepo.queryLatestDept(promotion.getEmpNo());
//                    String deptNo = currDeptEmployees.getDeptNo();
//
//                    if (promotion.getDeptNo() != null) {
//                        if (!Objects.equals(deptNo, promotion.getDeptNo())) {
//                            deptNo = promotion.getDeptNo();
//                        }
//                        newDeptEmployees = deptEmployeesRepo.insertNewDept(deptNo, promotion.getEmpNo());
//                    }
//
//                    if (promotion.getRaise() != 0) {
//                        SalariesRepo salariesRepo = new SalariesRepo();
//                        Salaries salary = null;
//                        salary = salariesRepo.insertNewEmployeeSalary(promotion.getEmpNo(), promotion.getRaise());
//                    }
//
//                    if (!Objects.equals(promotion.getTitle(), "")) {
//                        TitlesRepo titlesRepo = new TitlesRepo();
//                        Titles title = null;
//                        title = titlesRepo.insertNewEmployeeTitle(promotion.getEmpNo(), promotion.getTitle());
//
//                        if (promotion.getTitle().toLowerCase().contains("manager")) {
//                            DeptManagerRepo deptManagerRepo = new DeptManagerRepo();
//                            DeptManager deptManager = null;
//                            deptManager = deptManagerRepo.insertNewDeptManager(deptNo, promotion.getEmpNo());
//                        }
//                    }
//                    return Response.status(201).entity("Promoted " + promotion.getEmpNo()).build();
//                }
//            } else {
//                return Response.noContent().build();
//            }
//        } else {
//            return Response.noContent().build();
//        }
//        return  Response.ok().build();
//    }


//    @POST
//    @Path("/post/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postNotification(Message notification) {
//        return Response.status(201).entity(notification).build();
//    }
}