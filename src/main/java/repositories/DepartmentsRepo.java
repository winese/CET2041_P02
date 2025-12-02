package repositories;

import EntityManagerFactory.AppEntityManagerFactory;
import entities.Departments;
import entities.DeptEmployees;
import entities.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class DepartmentsRepo {

    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();

//    public Departments createDepartment(String deptNo, String deptName){
//        Departments department = new Departments();
//        department.setDeptNo(deptNo);
//        department.setDeptName(deptName);
//        em.persist(department);
//        return department;
//    }
//
//    public void updateDepartmentNumber(String deptNo){
//        Departments dep = em.find(Departments.class, deptNo);
//        if (dep != null) {
//            dep.setDeptNo(deptNo);
//        }
//        em.merge(dep);
//    }
//
//    public void deleteDepartment(String deptNo){
//        Departments dep = em.find(Departments.class, deptNo);
//        if (dep != null) {
//            em.remove(dep);
//        }
//    }
//
//    public Departments findDepartment(String deptNo){
//        return em.find(Departments.class, deptNo);
//    }

    public List<Departments> findAllDepartments(){
        return em.createQuery("SELECT d FROM Departments d",
                Departments.class).getResultList();

    }

}