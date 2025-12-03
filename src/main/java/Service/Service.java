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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * Service class. Supports the endpoints class and calls the repo.
 */
public class Service {

    /**
     * Entity Manager Single instance.
     */
    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();

    /**
     * Method to get Department ID and name of all departments.
     * No input from user.
     * @return List of all departments.
     */
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
    }

    /**
     * Method to get Employee's full information by employee ID.
     * @param id employee ID
     * @return Employee object
     */
    public Employees getEmployeeById(long id) {
        EntityManager em = emf.createEntityManager();
        EmployeesRepo employeesRepo = new EmployeesRepo(em);

        try {
            em.getTransaction().begin();
            return employeesRepo.findEmployee(id);
        }
        catch (Exception e) {
            throw new RuntimeException("There is issue during query.", e);
        }
        finally{
            em.close();
        }
    }

    /**
     * method to get Employee information of all employees in a given
     * department.
     * Information is grouped into pages of 20 entries. Takes in page number.
     * @param deptNo department ID
     * @param pgNo page number, default value of 1.
     * @return List of employee information.
     */
    public List<EndPoint3DTO> getEndPoint3Infos(String deptNo, int pgNo) {
        EntityManager em = emf.createEntityManager();
        EmployeesRepo employeesRepo = new EmployeesRepo(em);

        try {
            em.getTransaction().begin();
            List<EndPoint3DTO> list = employeesRepo.getEndPoint3Infos(deptNo, pgNo);
            if (!list.isEmpty())
                return list;
            else{
                return null;
            }
        }
        catch (Exception e) {
            throw new RuntimeException("There is issue during query.", e);
        }
        finally{
            em.close();
        }
    }

    /**
     * Method to promote an employee.
     * @param promotion promotion DTO
     * @return String message. Null if successful, else error message.
     */
    public String promoteEmployee(Promotion promotion) {
        EntityManager em = emf.createEntityManager();
        EmployeesRepo employeesRepo = new EmployeesRepo(em);
        EntityTransaction transaction = em.getTransaction();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        BigDecimal newSalary = BigDecimal.ZERO;
        LocalDate newStartDate = LocalDate.now();

        //Checking
        if (promotion.getEmpNo() <= 0) {
            return "No Employee Number found";
        }

        if (promotion.getNewSalary() != null) {
            newSalary = promotion.getNewSalary();
            if (newSalary.compareTo(BigDecimal.ZERO) == 0  && !promotion.isManager()
                    && (promotion.getNewTitle() == null || promotion.getNewTitle().isEmpty())
                    && (promotion.getNewDeptNo() == null || promotion.getNewDeptNo().isEmpty())
                    && (promotion.getNewStartDate() == null || promotion.getNewStartDate().isEmpty())) {
                return "No promotion parameters found";
            }
        }
        else {
            if (!promotion.isManager() && (promotion.getNewTitle() == null || promotion.getNewTitle().isEmpty())
                    && (promotion.getNewDeptNo() == null || promotion.getNewDeptNo().isEmpty())
                    && (promotion.getNewStartDate() == null || promotion.getNewStartDate().isEmpty())) {
                return "No promotion parameters found";
            }
        }

        if (promotion.getNewStartDate() != null) {
            if (!promotion.getNewStartDate().isEmpty()) {
                newStartDate = LocalDate.parse(promotion.getNewStartDate(), formatter);
            }
        }

        if (newStartDate.isBefore(LocalDate.now())
                || newStartDate.isAfter(LocalDate.of(9999,1,1))) {
            return "Invalid start date";
        }

        try {
            String result = null;
            long empNo = promotion.getEmpNo();
            Employees emp = employeesRepo.findEmployee(empNo);
            String newTitle = promotion.getNewTitle();
            String newDeptNo = promotion.getNewDeptNo();
            boolean isManager = promotion.isManager();

            transaction.begin();
            DeptEmployees currDept = employeesRepo.findCurrDept(emp);
            Departments dept = currDept.getDepartment();
            Titles currTitle = employeesRepo.findCurrTitle(emp);
            Salaries currSalary = employeesRepo.findCurrSalary(emp);

            // Changing Departments
            if (!Objects.equals(newDeptNo, "") && !Objects.equals(newDeptNo, null)
                    && !Objects.equals(currDept.getDepartment().getDeptNo(), newDeptNo)) {
                if (newDeptNo.length() == 4) {
                    dept = em.find(Departments.class, newDeptNo);
                    result = employeesRepo.insertNewDept(emp, dept, currDept, newDeptNo, newStartDate);
                    if (result != null) {
                        return result;
                    }
                } else if (newDeptNo.length() > 4) {
                    return "Invalid department number";
                }
            }

            // Changing Titles
            if (!Objects.equals(newTitle, "") && !Objects.equals(newTitle, null)
                    && !Objects.equals(currTitle.getTitle(), newTitle)) {
                if (newTitle.length() <= 50) {
                    result = employeesRepo.insertNewTitle(emp, currTitle, newTitle, newStartDate);
                    if (result != null) {
                        return result;
                    }
                }
                else return "Invalid title";
            }


            // Promote to Manager
            if (isManager) {
                result = employeesRepo.insertNewManager(emp, currDept, dept, newDeptNo, newStartDate);
                if (result != null) {
                    return result;
                }
            }

            // Changing Salary
            if ((newSalary.compareTo(BigDecimal.ZERO) > 0) && newSalary.compareTo(currSalary.getSalary()) > 0 ) {
                result = employeesRepo.insertNewSalary(emp, currSalary, newSalary, newStartDate);
                if (result != null) {
                    return result;
                }
            }
            else if (promotion.getNewSalary() != null && (newSalary.compareTo(BigDecimal.ZERO) < 0
                    || newSalary.compareTo(currSalary.getSalary()) < 0)) {
                return "Invalid new salary";
            }

            transaction.commit();
                return result;
        } catch (Exception e) {
            transaction.rollback();
            return e.getMessage();
        } finally {
            em.close();
        }
    }
}