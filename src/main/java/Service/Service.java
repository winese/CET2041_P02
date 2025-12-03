package Service;

import DTO.EndPoint3DTO;
import DTO.Promotion;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import EntityManagerFactory.AppEntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import repositories.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Service {

    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//    EmployeesRepo employeesRepo = new EmployeesRepo(em);
//    EntityTransaction transaction = em.getTransaction();

    // ! ENDPOINT !
    public List<Departments> getAllDepartments() {
        EntityManager em = emf.createEntityManager();
        EmployeesRepo employeesRepo = new EmployeesRepo(em);

        try {
            em.getTransaction().begin();
            List<Departments> departments = employeesRepo.getAllDepartments();
            if (!departments.isEmpty())
                return departments;
            else
                return null;
        }
        catch (Exception e) {
            throw new RuntimeException("There is issue during query.", e);
        }
        finally{
            em.close();
        }
        // ! ADD MORE TEST CASES
    }

    // ! ENDPOINT 2
    public Employees getEmployeeById(long id) {
        EntityManager em = emf.createEntityManager();
        EmployeesRepo employeesRepo = new EmployeesRepo(em);

        try {
            em.getTransaction().begin();
            Employees employees = employeesRepo.findEmployee(id);
            if (employees != null)
                return employees;
            else
                return null;
        }
        catch (Exception e) {
            throw new RuntimeException("There is issue during query.", e);
        }
        finally{
            em.close();
        }
        // ! ADD MORE TEST CASES
    }


    // ! ENDPOINT 3
    public List<EndPoint3DTO> getEndPoint3Infos(String deptNo, int pgNo) {
        EntityManager em = emf.createEntityManager();
        EmployeesRepo employeesRepo = new EmployeesRepo(em);

        try {
            em.getTransaction().begin();
            List<EndPoint3DTO> list = employeesRepo.getEndPoint3Infos(deptNo, pgNo);
            if (!list.isEmpty())
                return list;
            else{
                return null;}
        }
        catch (Exception e) {
            throw new RuntimeException("There is issue during query.", e);
        }
        finally{
            em.close();
        }
        // ! ADD MORE TEST CASES
    }

    // ! ENDPOINT 4
    public String promoteEmployee(Promotion promotion) {
        EntityManager em = emf.createEntityManager();
        EmployeesRepo employeesRepo = new EmployeesRepo(em);
        EntityTransaction transaction = em.getTransaction();

        //Checking
        if (promotion.getEmpNo() <= 0) {
            return "No Employee Number found";
        }
        if (promotion.getNewSalary().compareTo(BigDecimal.ZERO) > 0 && !promotion.isManager()
                && (promotion.getNewTitle() == null || promotion.getNewTitle().isEmpty())
                && (promotion.getNewDeptNo() == null || promotion.getNewDeptNo().isEmpty())) {
            return "No promotion parameters found";
        }

        try {
            String result = null;
            long empNo = promotion.getEmpNo();
            Employees emp = employeesRepo.findEmployee(empNo);
            BigDecimal newSalary = promotion.getNewSalary();
            String newTitle = promotion.getNewTitle();
            String newDeptNo = promotion.getNewDeptNo();
            boolean isManager = promotion.isManager();


            transaction.begin();
            System.out.println("Beginning transaction");
            DeptEmployees currDept = employeesRepo.findCurrDept(emp);
            Departments dept = currDept.getDepartment();
            Titles currTitle = employeesRepo.findCurrTitle(emp);
            Salaries currSalary = employeesRepo.findCurrSalary(emp);

            // Changing Departments
            if (!Objects.equals(newDeptNo, "") && !Objects.equals(newDeptNo, null)
                    && !Objects.equals(currDept.getDepartment().getDeptNo(), newDeptNo)) {
                if (newDeptNo.length() == 4) {
                    dept = em.find(Departments.class, newDeptNo);
                    result = employeesRepo.insertNewDept(emp, dept, currDept, newDeptNo);
                    if (result != null) {
                        return result;
                    }
                    System.out.println("Changed departments");
                } else if (newDeptNo.length() > 4) {
                    return "Invalid department number";
                }
            }

            // Changing Titles
            if (!Objects.equals(newTitle, "") && !Objects.equals(newTitle, null)
                    && !Objects.equals(currTitle.getTitle(), newTitle)) {
                if (newTitle.length() <= 50) {
                    result = employeesRepo.insertNewTitle(emp, currTitle, newTitle);
                    if (result != null) {
                        return result;
                    }
                    System.out.println("Changed title");
                }
                else return "Invalid title";
            }


            // Promote to Manager
            if (isManager) {
                result = employeesRepo.insertNewManager(emp, currDept, dept, newDeptNo);
                if (result != null) {
                    return result;
                }
                System.out.println("Promote to Manager");
            }

            // Changing Salary
            if ((newSalary.compareTo(BigDecimal.ZERO) > 0) && newSalary.compareTo(currSalary.getSalary()) > 0 ) {
                result = employeesRepo.insertNewSalary(emp, currSalary, newSalary);
                if (result != null) {
                    return result;
                }
                System.out.println("Changed Salary");
            }
            else if (newSalary.compareTo(BigDecimal.ZERO) < 0) {
                return "Invalid new salary";
            }

            transaction.commit();
            System.out.println("Transaction committed");
                return result;
        } catch (Exception e) {
            transaction.rollback();
            return e.getMessage();
        } finally {
            em.close();
        }
    }
}