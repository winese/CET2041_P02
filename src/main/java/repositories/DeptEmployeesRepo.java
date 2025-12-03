//package repositories;
//
//import entities.DeptEmployees;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//
//import java.time.LocalDate;
//
//import EntityManagerFactory.AppEntityManagerFactory;
//
//public class DeptEmployeesRepo {
//    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//
//    private DeptEmployees deptEmployees;
//
////    public DeptEmployees queryLatestDept(int employees) {
////        String query = "SELECT d " +
////                "FROM DeptEmployees d " +
////                "WHERE d.employees = " + employees + " AND " +
////                "d.toDate > CURRENT_DATE";
////        return em.createQuery(query, DeptEmployees.class).getSingleResult();
////    }
//
//    public DeptEmployees queryLatestDept(int employees) {
//        return em.createNamedQuery("DeptEmployees.searchLatestDeptByEmpNo", DeptEmployees.class)
//                .setParameter("employees", employees)
//                .getSingleResult();
//    }
//
//    public DeptEmployees insertNewDept(String department, int employees) {
//        LocalDate newToDate = LocalDate.of(9999,12,31);
//        DeptEmployees oldDeptEmp = queryLatestDept(employees);
//        DeptEmployees newDeptEmp = new DeptEmployees();
//
//        //Update existing department toDate if exist
//        if (oldDeptEmp != null) {
//            oldDeptEmp.setToDate(LocalDate.now());
//            em.persist(oldDeptEmp);
//        }
//        //Insert new department
//        newDeptEmp.setDepartment(department);
//        newDeptEmp.setEmployees(employees);
//        newDeptEmp.setFromDate(LocalDate.now());
//        newDeptEmp.setToDate(newToDate);
//        em.persist(newDeptEmp);
//
//        return newDeptEmp;
//    }
//}
//
//
////package repositories;
////
////import entities.DeptEmployees;
////import entities.Employees;
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.EntityManagerFactory;
////
////import java.time.LocalDate;
////import java.time.format.DateTimeFormatter;
////import EntityManagerFactory.AppEntityManagerFactory;
////import entities.DeptEmployees;
////
////public class DeptEmployeesRepo {
////    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
////    EntityManager em = emf.createEntityManager();
////
////    public DeptEmployees queryLatestDept(int employees) {
////        String query = "SELECT d " +
////                "FROM DeptEmployees d " +
////                "WHERE d.employees = '" + employees +
////                "' AND " +
////                "d.toDate > CURRENT_DATE";
////        return em.createQuery(query, DeptEmployees.class).getSingleResult();
////    }
////
////    public DeptEmployees insertNewDept(String department, int employees) {
////        LocalDate to = LocalDate.parse("31-12-9999");
////        DeptEmployees oldDept = new DeptEmployees();
////        DeptEmployees newDept = new DeptEmployees();
////
////        //Update existing salary toDate if exist
////        if (oldDept != null) {
////            oldDept.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
////            em.persist(oldDept);
////        }
////
////        //Insert new department manager
////        newDept.setDepartment(department);
////        newDept.setEmployees(employees);
////        newDept.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
////        newDept.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
////        em.persist(newDept);
////        return newDept;
////    }
////}