package Service;

import DTO.EndPoint3DTO;
import entities.Departments;
import entities.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import EntityManagerFactory.AppEntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.core.Response;
import repositories.EmployeesRepo;

import java.util.List;

public class Service {

    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();

    EmployeesRepo employeesRepo = new EmployeesRepo(em);

    EntityTransaction transaction = em.getTransaction();


    // ! ENDPOINT !
    public List<Departments> getAllDepartments() {
        List<Departments> departments = employeesRepo.getAllDepartments();
        if (departments != null) return departments;
        else return null;
        // ! ADD MORE TEST CASES
    }

    // ! ENDPOINT 2
    public Employees getEmployeeById(int id) {
        Employees employees = employeesRepo.findEmployee(id);
        if (employees != null) return employees;
        else return null;
        // ! ADD MORE TEST CASES
    }


    // ! ENDPOINT 3
    public List<EndPoint3DTO> getEndPoint3Infos(String deptNo, int pgNo) {
        List<EndPoint3DTO> list = employeesRepo.getEndPoint3Infos(deptNo, pgNo);
        if (list != null) return list;
        else return null;
        // ! ADD MORE TEST CASES
    }

}
