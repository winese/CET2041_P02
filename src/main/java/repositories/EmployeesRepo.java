package repositories;

import DTO.EndPoint3DTO;
import entities.*;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Data Access Class.
 */
public class EmployeesRepo {

    /**
     * Entity Manger.
     */
    protected EntityManager em;
    public EmployeesRepo(EntityManager em) {
        this.em = em;
    }

    /**
     * method to get all departments' information. Does not take in user input.
     * @return List of department names and department ID.
     */
    public List<Departments> getAllDepartments(){
        return em.createQuery(
                "SELECT d FROM Departments d",
                Departments.class).getResultList();
    }

    /**
     * Method to find employee information given an employee number
     * @param empNo employee number
     * @return all employee information.
     */
    public Employees findEmployee(long empNo) {
        return em.find(Employees.class, empNo);
    }

    /**
     * Method to get the information of all employees in a given department.
     * Output information is grouped into pages every 20 entries.
     * User can input a page number.
     * @param deptNo department number.
     * @param pgNo page number. Default value of 1.
     * @return employee number, first name, last name, hire date.
     */
    public List<EndPoint3DTO> getEndPoint3Infos(String deptNo, int pgNo) {
        return em.createNamedQuery("Employees.endPoint3", EndPoint3DTO.class)
                .setParameter("deptNo", deptNo)
                .setFirstResult((pgNo - 1) * 20)
                .setMaxResults(20)
                .getResultList();
    }

    /**
     * method to find current department of an employee
     * @param emp employee object, tied to employee number.
     * @return DeptEmployees object, tied to department.
     */
    public DeptEmployees findCurrDept(Employees emp) {
        return em.createNamedQuery("DeptEmployees.findLatestDeptByEmpNo", DeptEmployees.class)
                .setParameter("emp", emp)
                .getSingleResult();
    }

    /**
     * Method to find current title of an employee
     * @param emp employee object, tied to employee number
     * @return title.
     */
    public Titles findCurrTitle(Employees emp) {
        return em.createNamedQuery("Titles.findLatestTitleByEmpNo", Titles.class)
                .setParameter("emp", emp)
                .getSingleResult();
    }

    /**
     * Method to find current salary of an employee
     * @param emp employee number
     * @return salary
     */
    public Salaries findCurrSalary(Employees emp) {
        return em.createNamedQuery("Salaries.findLatestSalaryByEmpNo", Salaries.class)
                .setParameter("emp", emp)
                .getSingleResult();
    }

    /**
     * Method to find Manager(s) of a department.
     * @param emp employee ID to check if employee is the manager of the
     *            department.
     * @param dept department ID.
     * @return List of department maangers
     */
    public List<DeptManager> findManager(Employees emp, Departments dept) {
        return em.createNamedQuery("DeptManager.findDeptManagerByEmpNo", DeptManager.class)
                .setParameter("emp", emp)
                .setParameter("newDept", dept)
                .getResultList();
    }

    /**
     * Method to insert employee into a new department
     * @param employee employee
     * @param newDept new department
     * @param currDept current department
     * @param newDeptNo new department number
     * @return String. null if successful, error message if not.
     */
    public String insertNewDept(Employees employee, Departments newDept,
                                DeptEmployees currDept, String newDeptNo) {
        try {
            DeptEmployees newDeptEmployees = new DeptEmployees();
            LocalDate newToDate = LocalDate.of(9999,1,1);

            //Update existing department toDate if exist
            if (currDept != null) {
                currDept.setToDate(LocalDate.now());
                em.persist(currDept);
            }
            else{
                return "Existing department not found";
            }

            //Insert new department
            newDeptEmployees.setDepartment(newDept);
            newDeptEmployees.setEmployees(employee);
            newDeptEmployees.setFromDate(LocalDate.now());
            newDeptEmployees.setToDate(newToDate);
            newDeptEmployees.setEmployees(employee);
            newDeptEmployees.setDepartment(newDept);
            em.persist(newDeptEmployees);
            return null;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to give an employee a new title.
     * @param employee employee
     * @param currTitle current title
     * @param newTitle new title
     * @return String message. null if successful, error message if not.
     */
    public String insertNewTitle(Employees employee, Titles currTitle, String newTitle) {
        try {
            Titles title = new Titles();
            LocalDate newToDate = LocalDate.of(9999,1,1);

            //Update existing title toDate if exist
            if (currTitle != null) {
                currTitle.setToDate(LocalDate.now());
                em.persist(currTitle);
            }
            else{
                return "Existing title not found";
            }

            //Insert new department
            title.setEmployees(employee);
            title.setTitle(newTitle);
            title.setFromDate(LocalDate.now());
            title.setToDate(newToDate);
            title.setEmployees(employee);
            em.persist(title);
            return null;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to add new manager.
     * @param employee employee
     * @param currDeptEmp current DepartmentEmployee object.
     * @param newDept new department
     * @param newDeptNo new department ID
     * @return String message. null if successful, error message if not.
     */
    public String insertNewManager(Employees employee,
                                   DeptEmployees currDeptEmp,
                                   Departments newDept, String newDeptNo) {
        try {
            DeptManager newManager = new DeptManager();
            LocalDate newToDate = LocalDate.of(9999,1,1);

            //Get current department number if there is no new department number input
            if (Objects.equals(newDeptNo, "")) {
                newDeptNo = currDeptEmp.getDepartment().getDeptNo();
            }

            //Check if exist
            List<DeptManager> curr = findManager(employee, newDept);
            if (!curr.isEmpty()) {
                return "Manager already exists";
            }
            else{
                //Insert new manager
                newManager.setDepartment(newDept);
                newManager.setEmployees(employee);
                newManager.setFromDate(LocalDate.now());
                newManager.setToDate(newToDate);
                newManager.setEmployees(employee);
//                newManager.setDepartment(newDept);
                em.persist(newManager);
                return null;
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to give an employee a new salary.
     * @param employee employee
     * @param currSalary current salary
     * @param newSalary new salary
     * @return String message. null if successful, error message if not.
     */
    public String insertNewSalary(Employees employee, Salaries currSalary, BigDecimal newSalary) {
        try {
            Salaries salary = new Salaries();
            LocalDate newToDate = LocalDate.of(9999,1,1);

            //Update existing salary toDate if exist
            if (currSalary != null) {
                currSalary.setToDate(LocalDate.now());
                em.persist(currSalary);
            }
            else{
                return "Existing salary not found";
            }

            //Insert new salary
            salary.setEmployees(employee);
            salary.setSalary(newSalary);
            salary.setFromDate(LocalDate.now());
            salary.setToDate(newToDate);
            salary.setEmployees(employee);
            em.persist(salary);
            return null;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
