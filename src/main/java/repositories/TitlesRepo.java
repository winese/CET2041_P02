package repositories;

import entities.Salaries;
import entities.Titles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class TitlesRepo {
    protected EntityManager em;

    public TitlesRepo(EntityManager em) {
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
    public List<Titles> findEmployeeTitlesWithHistories(int empNo) {
        TypedQuery<Titles> query = em.createQuery(
                "SELECT s FROM Titles s WHERE s.titleId.empNo = :empNo", Titles.class);
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