//package repositories;
//
//import EntityManagerFactory.AppEntityManagerFactory;
//import entities.Departments;
//import entities.DeptEmployees;
//import entities.Employees;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//
//import java.util.List;
//
//public class DepartmentsRepo {
//
//    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//
////    public Departments createDepartment(String department, String deptName){
////        Departments department = new Departments();
////        department.setDepartment(department);
////        department.setDeptName(deptName);
////        em.persist(department);
////        return department;
////    }
////
////    public void updateDepartmentNumber(String department){
////        Departments dep = em.find(Departments.class, department);
////        if (dep != null) {
////            dep.setDepartment(department);
////        }
////        em.merge(dep);
////    }
////
////    public void deleteDepartment(String department){
////        Departments dep = em.find(Departments.class, department);
////        if (dep != null) {
////            em.remove(dep);
////        }
////    }
////
////    public Departments findDepartment(String department){
////        return em.find(Departments.class, department);
////    }
//
//    public List<Departments> findAllDepartments(){
//        return em.createQuery("SELECT d FROM Departments d",
//                Departments.class).getResultList();
//
//    }
//
//}