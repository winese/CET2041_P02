package repositories;

import DTO.EndPoint3DTO;
import entities.DeptEmployees;
import jakarta.persistence.EntityManager;
import entities.Employees;
import jakarta.persistence.EntityManagerFactory;
import EntityManagerFactory.AppEntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeesRepo {
    private final EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();

    public Employees findEmployee(int empNo) {
        return em.find(Employees.class, empNo);
    }

    public List<EndPoint3DTO> findEndPoint3Infos(String deptNo) {
        return em.createNamedQuery("Employees.endPoint3", EndPoint3DTO.class)
                .setParameter("deptNo", deptNo)
                .setFirstResult(0)
                .setMaxResults(20)
                .getResultList();
    }

    public List<EndPoint3DTO> findEndPoint3Infos(String deptNo, int pgNo) {
        return em.createNamedQuery("Employees.endPoint3", EndPoint3DTO.class)
                .setParameter(deptNo, deptNo)
                .setMaxResults(20)
                .setFirstResult((pgNo - 1) * 20)
                .getResultList();
    }



}
