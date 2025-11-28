package repositories;

import entities.Salaries;
import jakarta.persistence.EntityManager;
import entities.Employees;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class SalariesRepo {
    protected EntityManager em;

    public SalariesRepo(EntityManager em) {
        this.em = em;
    }

    //    public Employee createEmployee(int id, String name, long salary) {
//        Employee emp = new Employee(id);
//        emp.setName(name);
//        emp.setSalary(salary);
//        em.persist(emp);
//        return emp;
//    }
//
//    public void removeEmployee(int id) {
//        Employee emp = findEmployee(id);
//        if (emp != null) {
//            em.remove(emp);
//        }
//    }
//
//    public Employee raiseEmployeeSalary(int id, long raise) {
//        Employee emp = em.find(Employee.class, id);
//        if (emp != null) {
//            emp.setSalary(emp.getSalary() + raise);
//        }
//        return emp;
//    }
//
    public List<Salaries> findEmployeeSalariesWithHistories(int empNo) {
        TypedQuery<Salaries> query = em.createQuery(
                  "SELECT s FROM Salaries s WHERE s.salaryId.empNo = :empNo", Salaries.class);
        query.setParameter("empNo", empNo);
        return query.getResultList();
    }
//
//    public List<Employee> findAllEmployees() {
//        TypedQuery<Employee> query = em.createQuery(
//                  "SELECT e FROM Employee e", Employee.class);
//        return query.getResultList();
//    }
}