package repositories;

import DTO.EndPoint3DTO;
import jakarta.persistence.EntityManager;
import entities.Employees;
import jakarta.persistence.EntityManagerFactory;
import EntityManagerFactory.AppEntityManagerFactory;

import java.util.List;

public class EmployeesRepoOld {
    private final EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();

    public Employees findEmployee(int empNo) {
        return em.find(Employees.class, empNo);
    }

    public List<EndPoint3DTO> findEndPoint3Infos(String deptNo, int pgNo) {
        return em.createNamedQuery("Employees.endPoint3", EndPoint3DTO.class)
                .setParameter("deptNo", deptNo)
                .setFirstResult((pgNo - 1) * 20)
                .setMaxResults(20)
                .getResultList();
    }



}
