package Service;

import DTO.EndPoint3DTO;
import DTO.Promotion;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import EntityManagerFactory.AppEntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.core.Response;
import repositories.*;

import java.util.List;
import java.util.Objects;

public class Service {

    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();
    EmployeesRepo employeesRepo = new EmployeesRepo(em);
    EntityTransaction transaction = em.getTransaction();

    // ! ENDPOINT !
    public List<Departments> getAllDepartments() {
        List<Departments> departments = employeesRepo.getAllDepartments();
        if (departments != null) return departments;
        else return null;
        // ! ADD MORE TEST CASES
    }

    // ! ENDPOINT 2
    public Employees getEmployeeById(int id) {
        Employees employees = employeesRepo.findEmployee(id);
        if (employees != null) return employees;
        else return null;
        // ! ADD MORE TEST CASES
    }


    // ! ENDPOINT 3
    public List<EndPoint3DTO> getEndPoint3Infos(String deptNo, int pgNo) {
        List<EndPoint3DTO> list = employeesRepo.getEndPoint3Infos(deptNo, pgNo);
        if (list != null) return list;
        else return null;
        // ! ADD MORE TEST CASES
    }

    // ! ENDPOINT 4
    public String promoteEmployee(Promotion promotion) {

        //Checking
        if (promotion == null) {
            return "Invalid parameters";
        }
        if (promotion.getEmpNo() <= 0) {
            return "No Employee Number found";
        }
        if (promotion.getNewSalary() <= 0 && !promotion.isManager()
                && (promotion.getNewTitle() == null || promotion.getNewTitle().isEmpty())
                && (promotion.getNewDeptNo() == null || promotion.getNewDeptNo().isEmpty())) {
            return "No promotion parameters found";
        }

        try {
            String result = "";
            int empNo = promotion.getEmpNo();
            int newSalary  = promotion.getNewSalary();
            String newTitle = promotion.getNewTitle();
            String newDeptNo = promotion.getNewDeptNo();
            boolean isManager = promotion.isManager();

            Employees employee = employeesRepo.findEmployee(empNo);
            DeptEmployees currDept = employeesRepo.findCurrDept(empNo);
            Titles currTitle = employeesRepo.findCurrTitle(empNo);
            Salaries currSalary = employeesRepo.findCurrSalary(empNo);

            System.out.println("Beginning transaction");
            transaction.begin();

            // Changing Departments
            if (!Objects.equals(newDeptNo, "") && !Objects.equals(currDept.getDeptNo(), newDeptNo)) {
                result = employeesRepo.insertNewDept(employee, currDept.getDepartment(), currDept, newDeptNo);
                System.out.println("Changed departments");
            }

            // Changing Titles
            if (!Objects.equals(newTitle, "") && !Objects.equals(currTitle.getTitle(), newTitle)) {
                result = employeesRepo.insertNewTitle(employee, currTitle, newDeptNo);
                System.out.println("Changed title");
            }

            // Promote to Manager
            if (isManager) {
                result = employeesRepo.insertNewManager(employee, currDept, newDeptNo);
                System.out.println("Promote to Manager");
            }

            // Changing Salary
            if (newSalary > 0 && newSalary > currSalary.getSalary()) {
                result = employeesRepo.insertNewSalary(employee, currSalary, newSalary);
                System.out.println("Changed Salary");
            }
            transaction.commit();
            System.out.println("Transaction committed");
            return null;
        } catch (Exception e) {
            transaction.rollback();
            return e.getMessage();
        }
        finally {
            em.close();
            //emf.close();
        }
    }

//    // ! ENDPOINT 4
//    public String promoteEmployee(Promotion promotion) {
//        System.out.println("1");
//        try {
//            if (promotion != null) {
//                if (promotion.getEmpNo() != 0) {
//                    System.out.println("2");
//                    Employees employee = employeesRepo.findEmployee(promotion.getEmpNo());
//                    if (employee != null) {
//                        System.out.println("3");
//
//                        DeptEmployees newDept = null;
//                        DeptEmployees currDept = employeesRepo.queryLatestDept(promotion.getEmpNo());
//                        String deptNo = currDept.getDeptNo();
//
//                        em.getTransaction().begin();
//                        if (promotion.getDeptNo() != null) {
//                            System.out.println("3-1");
//                            if (!Objects.equals(deptNo, promotion.getDeptNo())) {
//                                deptNo = promotion.getDeptNo();
//                            }
//                            newDept = employeesRepo.insertNewDept(deptNo,
//                                    promotion.getEmpNo());
//                        }
//                        System.out.println("4");
//                        if (!Objects.equals(promotion.getTitle(), "")) {
//                            System.out.println("4-1");
//                            Titles title = employeesRepo.insertNewEmployeeTitle(promotion.getEmpNo(), promotion.getTitle());
//                        }
//                        System.out.println("5");
//                        if (promotion.isManager()) {
//                            System.out.println("5-1");
//                            DeptManager deptManager = employeesRepo.insertNewDeptManager(deptNo, promotion.getEmpNo());
//                        }
//                        System.out.println("6");
//                        if (promotion.getRaise() != 0) {
//                            System.out.println("6-1");
//                            Salaries salary = employeesRepo.insertNewEmployeeSalary(promotion.getEmpNo(), promotion.getRaise());
//                        }
//                        System.out.println("7");
//                        em.getTransaction().commit();
//                        System.out.println("8");
//                        return null;
//                        //return Response.status(201).entity("Promoted " + promotion.getEmpNo()).build();
//                    } else {
//                        return "Employee not found.";
//                    }
//                } else {
//                    return "No employee number found.";
//                }
//            } else {
//                return "EMPTY JSON";
//            }
//        }
//        catch (Exception ex) {
//            transaction.rollback();
//            return ex.getMessage();
//        }
//        finally {
//            em.close();
//            emf.close();
//        }
//    }

}
