package repositories;

import DTO.EndPoint3DTO;
import entities.Departments;
import entities.Employees;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EmployeesRepo {

    protected EntityManager em;
    public EmployeesRepo(EntityManager em) {
        this.em = em;
    }


    // ! ENDPOINT 1
    public List<Departments> getAllDepartments(){
        return em.createQuery(
                "SELECT d FROM Departments d",
                Departments.class).getResultList();
    }

    // ! ENDPOINT 2
    public Employees findEmployee(int empNo) {
        return em.find(Employees.class, empNo);
    }

    // ! ENDPOINT 3
    public List<EndPoint3DTO> getEndPoint3Infos(String deptNo, int pgNo) {
        return em.createNamedQuery("Employees.endPoint3", EndPoint3DTO.class)
                .setParameter("deptNo", deptNo)
                .setFirstResult((pgNo - 1) * 20)
                .setMaxResults(20)
                .getResultList();
    }

    // ! ENDPOINT 4


}
