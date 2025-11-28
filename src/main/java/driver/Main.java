package driver;

import entities.Departments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import entities.Employees;
import repositories.DepartmentsRepo;
import repositories.EmployeesRepo;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static final String DBNAME = "employees";

    public static void main(String[] args) {
        // overriding the existing properties in persistence.xml
        Map<String,String> persistenceMap = new HashMap<>();
        persistenceMap.put("jakarta.persistence.jdbc.url",
                "jdbc:mariadb://localhost:3306/"+DBNAME);

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeesPU", persistenceMap);
        EntityManager em = emf.createEntityManager();

        EmployeesRepo employeesRepo = new EmployeesRepo(em);
        Employees emp = null;

        DepartmentsRepo depRepo = new DepartmentsRepo(em);
        Departments dep = null;


        // 1. find a specific employee
        emp = employeesRepo.findEmployee(10001);
        System.out.println("Found " + emp);

//        em.getTransaction().begin();
//        //  create and persist an employee
//        emp = service.createEmployee(200, "Jane Doe", 55000);
//        em.getTransaction().commit();
//        System.out.println("Persisted " + emp);
//
//        // 1. find a specific employee
//        emp = service.findEmployee(158);
//        System.out.println("Found " + emp);
//
        //2. find all employees
//        List<Employees> lst_emps = service.findAllEmployees();
//        for (Employees e : lst_emps)
//            System.out.println("Found employee: " + e);
//
//        // 3. update the employee
//        emp = service.raiseEmployeeSalary(158, 1000);
//        em.getTransaction().commit();
//        System.out.println("Updated " + emp);
//
//        // 4. remove an employee
//        service.removeEmployee(158);
//        em.getTransaction().commit();
//        System.out.println("Removed Employee 158");


        // ! Departments testing below

        // 1. Create new departments


        // 2. Find a department by attribute
        System.out.println("Found department: " +
                depRepo.findDepartment("d009"));

        // 3. Get all departments


        // 4. update department by attribute


        // 5. update department Manager


        // 6. update department employee


        // 7. Delete department


        // close the EM and EMF when done
        em.close();
        emf.close();
    }
}