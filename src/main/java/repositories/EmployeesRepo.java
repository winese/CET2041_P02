package repositories;

import DTO.EndPoint3DTO;
import entities.*;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    // ! ENDPOINT 2/4
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

    public DeptEmployees queryLatestDept(int empNo) {
        return em.createNamedQuery("DeptEmployees.searchLatestDeptByEmpNo", DeptEmployees.class)
                .setParameter("empNo", empNo)
                .getSingleResult();
    }

    public void insertNewDept(String deptNo, int empNo) {
        LocalDate newToDate = LocalDate.of(9999,12,31);
        DeptEmployees oldDept = queryLatestDept(empNo);
        DeptEmployees newDept = new DeptEmployees();

        //Update existing department toDate if exist
        if (oldDept != null) {
            oldDept.setToDate(LocalDate.now());
            em.persist(oldDept);
        }
        //Insert new department
        newDept.setDeptNo(deptNo);
        newDept.setEmpNo(empNo);
        newDept.setFromDate(LocalDate.now());
        newDept.setToDate(newToDate);
        em.persist(newDept);
    }

    public Titles queryLatestEmpTitle(int empNo) {
        return em.createNamedQuery("Titles.searchLatestTitleByEmpNo", Titles.class)
                .setParameter("empNo", empNo)
                .getSingleResult();
    }

    public void insertNewEmployeeTitle(int empNo, String title) {
        LocalDate toDate = LocalDate.of(9999,12,31);
        Titles oldTitle = queryLatestEmpTitle(empNo);
        Titles newTitle = new Titles();

        //Update existing salary toDate if exist
        if (oldTitle != null) {
            oldTitle.setToDate(toDate);
            em.persist(oldTitle);
        }

        //Insert new title
        newTitle.setEmpNo(empNo);
        newTitle.setTitle(title);
        newTitle.setFromDate(LocalDate.now());
        newTitle.setToDate(toDate);
        em.persist(newTitle);
    }

    public void insertNewDeptManager(String deptNo, int empNo) {
        LocalDate toDate = LocalDate.of(9999,12,31);
        DeptManager newDeptManager = new DeptManager();

        em.getTransaction().begin();
        //Insert new department manager
        newDeptManager.setDeptNo(deptNo);
        newDeptManager.setEmpNo(empNo);
        newDeptManager.setFromDate(LocalDate.now());
        newDeptManager.setToDate(toDate);
        em.persist(newDeptManager);
        em.getTransaction().commit();
    }

    public Salaries queryLatestEmpSalary(int empNo) {
        String query = "SELECT s " +
                "FROM Salaries s " +
                "WHERE s.empNo = '" + empNo +
                "' AND " +
                "s.toDate > CURRENT_DATE";
        return em.createQuery(query, Salaries.class).getSingleResult();
    }

    public void insertNewEmployeeSalary(int empNo, int raise) {
        LocalDate to = LocalDate.parse("31-12-9999");
        Salaries oldSalary = queryLatestEmpSalary(empNo);
        Salaries newSalary = new Salaries();

        //Update existing salary toDate if exist
        if (oldSalary != null) {
            oldSalary.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
            em.persist(oldSalary);
        }

        //Insert new salary
        newSalary.setEmpNo(empNo);
        newSalary.setSalary(raise);
        newSalary.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        newSalary.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        em.persist(newSalary);
    }



}
