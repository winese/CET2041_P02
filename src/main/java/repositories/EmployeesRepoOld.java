//package repositories;
//
//import DTO.EndPoint3DTO;
//import jakarta.persistence.EntityManager;
//import entities.Employees;
//import jakarta.persistence.EntityManagerFactory;
//import EntityManagerFactory.AppEntityManagerFactory;
//
//import java.util.List;
//
//public class EmployeesRepoOld {
//    private final EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//
//    public Employees findEmployee(int employees) {
//        return em.find(Employees.class, employees);
//    }
//
//    public List<EndPoint3DTO> findEndPoint3Infos(String department, int pgNo) {
//        return em.createNamedQuery("Employees.endPoint3", EndPoint3DTO.class)
//                .setParameter("department", department)
//                .setFirstResult((pgNo - 1) * 20)
//                .setMaxResults(20)
//                .getResultList();
//    }
//
//
//
//}
