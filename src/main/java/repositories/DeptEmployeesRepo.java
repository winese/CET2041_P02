package repositories;

import entities.DeptEmployees;
import entities.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import EntityManagerFactory.AppEntityManagerFactory;
import entities.DeptEmployees;

public class DeptEmployeesRepo {
    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();

    public DeptEmployees queryLatestDept(int empNo) {
        String query = "SELECT d " +
                "FROM DeptEmployees d " +
                "WHERE d.empNo = '" + empNo +
                "' AND " +
                "d.toDate > CURRENT_DATE";
        return em.createQuery(query, DeptEmployees.class).getSingleResult();
    }

    public DeptEmployees insertNewDept(String deptNo, int empNo) {
        LocalDate to = LocalDate.parse("31-12-9999");
        DeptEmployees oldDept = queryLatestDept(empNo);
        DeptEmployees newDept = new DeptEmployees();

        em.getTransaction().begin();
        //Update existing department toDate if exist
        if (oldDept != null) {
            oldDept.setToDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            em.persist(oldDept);
        }
        //Insert new department
        newDept.setDeptNo(deptNo);
        newDept.setEmpNo(empNo);
        newDept.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        newDept.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        em.persist(newDept);
        em.getTransaction().commit();

        return newDept;
    }
}


//package repositories;
//
//import entities.DeptEmployees;
//import entities.Employees;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import EntityManagerFactory.AppEntityManagerFactory;
//import entities.DeptEmployees;
//
//public class DeptEmployeesRepo {
//    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//
//    public DeptEmployees queryLatestDept(int empNo) {
//        String query = "SELECT d " +
//                "FROM DeptEmployees d " +
//                "WHERE d.empNo = '" + empNo +
//                "' AND " +
//                "d.toDate > CURRENT_DATE";
//        return em.createQuery(query, DeptEmployees.class).getSingleResult();
//    }
//
//    public DeptEmployees insertNewDept(String deptNo, int empNo) {
//        LocalDate to = LocalDate.parse("31-12-9999");
//        DeptEmployees oldDept = new DeptEmployees();
//        DeptEmployees newDept = new DeptEmployees();
//
//        //Update existing salary toDate if exist
//        if (oldDept != null) {
//            oldDept.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
//            em.persist(oldDept);
//        }
//
//        //Insert new department manager
//        newDept.setDeptNo(deptNo);
//        newDept.setEmpNo(empNo);
//        newDept.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
//        newDept.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
//        em.persist(newDept);
//        return newDept;
//    }
//}