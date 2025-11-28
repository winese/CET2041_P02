package repositories;

import entities.Departments;
import jakarta.persistence.EntityManager;

public class DepartmentsRepo {
    protected EntityManager em;
    public DepartmentsRepo(EntityManager em) {
        this.em = em;
    }

    public Departments createDepartment(String deptNo, String deptName){
        Departments department = new Departments();
        department.setDeptNo(deptNo);
        department.setDeptName(deptName);
        em.persist(department);
        return department;
    }

    public void updateDepartmentNumber(String deptNo){
        Departments dep = em.find(Departments.class, deptNo);
        if (dep != null) {
            dep.setDeptNo(deptNo);
        }
        em.merge(dep);
    }

    public void deleteDepartment(String deptNo){
        Departments dep = em.find(Departments.class, deptNo);
        if (dep != null) {
            em.remove(dep);
        }
    }

    public Departments findDepartmentNo(String deptNo){
        return em.find(Departments.class, deptNo);
    }

    public Departments findDepartmentName(String deptName){
        return em.find(Departments.class, deptName);
    }

}