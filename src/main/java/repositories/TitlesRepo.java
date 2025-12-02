//package repositories;
//
//import entities.Salaries;
//import entities.Titles;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//public class TitlesRepo {
//    protected EntityManager em;
//
//    public TitlesRepo(EntityManager em) {
//        this.em = em;
//    }
//
//    //    public Employee createEmployee(int id, String name, long salary) {
////        Employee emp = new Employee(id);
////        emp.setName(name);
////        emp.setSalary(salary);
////        em.persist(emp);
////        return emp;
////    }
////
////    public void removeEmployee(int id) {
////        Employee emp = findEmployee(id);
////        if (emp != null) {
////            em.remove(emp);
////        }
////    }
////
////    public Employee raiseEmployeeSalary(int id, long raise) {
////        Employee emp = em.find(Employee.class, id);
////        if (emp != null) {
////            emp.setSalary(emp.getSalary() + raise);
////        }
////        return emp;
////    }
////
//    public Titles queryLatestEmpTitle(int empNo) {
//        String query = "SELECT t " +
//                "FROM Titles t " +
//                "WHERE t.empNo = '" + empNo +
//                "' AND " +
//                "t.toDate > CURRENT_DATE";
//        return em.createQuery(query, Titles.class).getSingleResult();
//    }
//
//    public Titles insertNewEmployeeTitle(int empNo, String title) {
//        LocalDate to = LocalDate.parse("31-12-9999");
//        Titles oldtitle = queryLatestEmpTitle(empNo);
//        Titles newTitle = new Titles();
//
//        //Update existing salary toDate if exist
//        if (oldtitle != null) {
//            oldtitle.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
//            em.persist(oldtitle);
//        }
//
//        //Insert new salary
//        newTitle.setEmpNo(empNo);
//        newTitle.setTitle(title);
//        newTitle.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
//        newTitle.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
//        em.persist(newTitle);
//        return newTitle;
//    }
////
////    public List<Employee> findAllEmployees() {
////        TypedQuery<Employee> query = em.createQuery(
////                  "SELECT e FROM Employee e", Employee.class);
////        return query.getResultList();
////    }
//}

package repositories;

import entities.DeptEmployees;
import entities.Titles;
import jakarta.persistence.EntityManager;
import entities.Employees;
import jakarta.persistence.EntityManagerFactory;
import EntityManagerFactory.AppEntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TitlesRepo {
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
//    public Titles queryLatestEmpTitle(int empNo) {
//        String query = "SELECT t " +
//                "FROM Titles t " +
//                "WHERE t.empNo = '" + empNo +
//                "' AND " +
//                "t.toDate > CURRENT_DATE";
//        return em.createQuery(query, Titles.class).getSingleResult();
//    }

    public Titles queryLatestEmpTitle(int empNo) {
        return em.createNamedQuery("Titles.searchLatestTitleByEmpNo", Titles.class)
                .setParameter("empNo", empNo)
                .getSingleResult();
    }

    public Titles insertNewEmployeeTitle(int empNo, String title) {
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
        return newTitle;
    }
//
//    public Employees findEmployee(int empNo) {
//        return em.find(Employees.class, empNo);
//    }
//
//    public Employees findEndPoint3Infos(int empNo) {
//        return em.createQuery("SELECT e.empNo, " +
//                                    "e.firstName, " +
//                                    "e.lastName, " +
//                                    "e.hireDate " +
//                                "FROM Employees e " +
//                                "WHERE e.empNo = :empNo",
//                Employees.class).setParameter("empNo", empNo).getSingleResult();
//    }
}