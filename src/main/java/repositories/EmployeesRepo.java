package repositories;

import DTO.EndPoint3DTO;
import entities.*;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class EmployeesRepo {

    protected EntityManager em;
    public EmployeesRepo(EntityManager em) {
        this.em = em;
    }


    // ! ENDPOINT 1
    public List<Departments> getAllDepartments(){
        return em.createQuery(
                "SELECT d FROM Departments d",
                Departments.class).getResultList();
    }

    // ! ENDPOINT 2 & 4
    public Employees findEmployee(int empNo) {
        return em.find(Employees.class, empNo);
    }

    // ! ENDPOINT 3
    public List<EndPoint3DTO> getEndPoint3Infos(String deptNo, int pgNo) {
        return em.createNamedQuery("Employees.endPoint3", EndPoint3DTO.class)
                .setParameter("deptNo", deptNo)
                .setFirstResult((pgNo - 1) * 20)
                .setMaxResults(20)
                .getResultList();
    }

    // ! ENDPOINT 4
    public DeptEmployees findCurrDept(int empNo) {
        return em.createNamedQuery("DeptEmployees.findLatestDeptByEmpNo", DeptEmployees.class)
                .setParameter("empNo", empNo)
                .getSingleResult();
    }

    public Titles findCurrTitle(int empNo) {
        return em.createNamedQuery("Titles.findLatestTitleByEmpNo", Titles.class)
                .setParameter("empNo", empNo)
                .getSingleResult();
    }

    public Salaries findCurrSalary(int empNo) {
        return em.createNamedQuery("Salaries.findLatestSalaryByEmpNo", Salaries.class)
                .setParameter("empNo", empNo)
                .getSingleResult();
    }

    public List<DeptManager> findManager(int empNo, String deptNo) {
        return em.createNamedQuery("DeptManager.findDeptManagerByEmpNo", DeptManager.class)
                .setParameter("empNo", empNo)
                .setParameter("deptNo", deptNo)
                .getResultList();
    }

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
            newDeptEmployees.setDeptNo(newDeptNo);
            newDeptEmployees.setEmpNo(employee.getEmpNo());
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
            title.setEmpNo(employee.getEmpNo());
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

    public String insertNewManager(Employees employee, DeptEmployees currDept, String deptNo) {
        try {
            DeptManager newManager = new DeptManager();
            LocalDate newToDate = LocalDate.of(9999,1,1);

            //Get current department number if there is no new department number input
            if (Objects.equals(deptNo, "")) {
                deptNo = currDept.getDeptNo();
            }

            //Check if exist
            List<DeptManager> curr = findManager(employee.getEmpNo(), deptNo);
            if (curr != null) {
                return "Manager already exists";
            }
            else{
                //Insert new manager
                newManager.setDeptNo(deptNo);
                newManager.setEmpNo(employee.getEmpNo());
                newManager.setFromDate(LocalDate.now());
                newManager.setToDate(newToDate);
                newManager.setEmployees(employee);
                em.persist(newManager);
                return null;
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String insertNewSalary(Employees employee, Salaries currSalary, int newSalary) {
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
            salary.setEmpNo(employee.getEmpNo());
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
