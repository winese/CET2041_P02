package repositories;

import entities.DeptEmployees;
import jakarta.persistence.EntityManager;
import entities.Employees;
import jakarta.persistence.EntityManagerFactory;
import EntityManagerFactory.AppEntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeesRepo {
    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();

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
    public Employees findEmployee(int empNo) {
        return em.find(Employees.class, empNo);
    }

    public List<entities.DeptEmployees> findEndPoint3Infos(int deptNo) {
        return em.createNamedQuery("Employees.endPoint3", DeptEmployees.class)
                .setParameter("deptNo", deptNo)
                .getResultList();
    }

    public List<entities.DeptEmployees> findEndPoint3Infos(int deptNo,
                                                           int pgNo) {
        return em.createNamedQuery("Employees.endPoint3", DeptEmployees.class)
                .setParameter("deptNo", deptNo)
                .setMaxResults(20)
                .setFirstResult((pgNo - 1) * 20)
                .getResultList();
    }



}
