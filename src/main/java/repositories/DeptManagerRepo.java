//package repositories;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import EntityManagerFactory.AppEntityManagerFactory;
//import entities.DeptManager;
//
//public class DeptManagerRepo {
//    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//
//    public DeptManager insertNewDeptManager(String department, int employees) {
//        LocalDate toDate = LocalDate.of(9999,12,31);
//        DeptManager newDeptManager = new DeptManager();
//
//        em.getTransaction().begin();
//        //Insert new department manager
//        newDeptManager.setDepartment(department);
//        newDeptManager.setEmployees(employees);
//        newDeptManager.setFromDate(LocalDate.now());
//        newDeptManager.setToDate(toDate);
//        em.persist(newDeptManager);
//        em.getTransaction().commit();
//        return newDeptManager;
//    }
//}
//
//
////package repositories;
////
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.EntityManagerFactory;
////
////import java.time.LocalDate;
////import java.time.format.DateTimeFormatter;
////import EntityManagerFactory.AppEntityManagerFactory;
////import entities.DeptManager;
////
////public class DeptManagerRepo {
////    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
////    EntityManager em = emf.createEntityManager();
////
////    public DeptManager queryLatestDeptManager(String department, int employees) {
////        String query = "SELECT d " +
////                "FROM DeptManager d " +
////                "WHERE d.department = '" + department +
////                "' AND " +
////                "d.employees = '" + employees +
////                "' AND " +
////                "d.toDate > CURRENT_DATE";
////        return em.createQuery(query, DeptManager.class).getSingleResult();
////    }
////
////    public DeptManager insertNewDeptManager(String department, int employees) {
////        LocalDate to = LocalDate.parse("31-12-9999");
////        DeptManager oldDeptManager = queryLatestDeptManager(department, employees);
////        DeptManager newDeptManager = new DeptManager();
////
////        //Update existing salary toDate if exist
////        if (oldDeptManager != null) {
////            oldDeptManager.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
////            em.persist(oldDeptManager);
////        }
////
////        //Insert new department manager
////        newDeptManager.setDepartment(department);
////        newDeptManager.setEmployees(employees);
////        newDeptManager.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
////        newDeptManager.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
////        em.persist(newDeptManager);
////        return newDeptManager;
////    }
////}