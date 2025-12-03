package driver;

import DTO.Promotion;
import Service.Service;
import jakarta.ws.rs.core.Response;
import repositories.DepartmentsRepo;

public class Main {
    static final String DBNAME = "employees";

    public static void main(String[] args) {

        DepartmentsRepo depRepo = new DepartmentsRepo();
        depRepo.findAllDepartments();


//        EmployeesRepo employeesRepo = new EmployeesRepo();
//        System.out.println("Found " + employeesRepo.findEmployee(10001));

        //EmployeesRepo employeesRepo = new EmployeesRepo();
//        System.out.println("Found " + employeesRepo.findAllEmployees());

        // overriding the existing properties in persistence.xml
//        Map<String,String> persistenceMap = new HashMap<>();
//        persistenceMap.put("jakarta.persistence.jdbc.url",
//                "jdbc:mariadb://localhost:3306/"+DBNAME);
//
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory("EmployeesPU", persistenceMap);
//        EntityManager em = emf.createEntityManager();

//        EmployeesRepo employeesRepo = new EmployeesRepo(em);
//        Employees emp = null;
//
//        DepartmentsRepo depRepo = new DepartmentsRepo(em);
//        Departments dep = null;
//
//        SalariesRepo salariesRepo = new SalariesRepo(em);
//        List<Salaries> salaries = null;
//
//        TitlesRepo titlesRepo = new TitlesRepo(em);
//        List<Titles> titles = null;

//        DepartmentsRepo depRepo = new DepartmentsRepo(em);

        // 1. find a specific employee
//        emp = employeesRepo.findEmployee(10001);
//        System.out.println("Found " + emp);

//        salaries = salariesRepo.findEmployeeSalariesWithHistories(10001);
//        System.out.println("Found " + salaries);
//
//        titles = titlesRepo.findEmployeeTitlesWithHistories(10001);
//        System.out.println("Found " + titles);

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

        // ENDPOINT 1. find all department numbers and names
//        List<Departments> allDeps = null;
//        allDeps = em.createQuery(depRepo.findAllDepartments(), Departments.class).getResultList();
//        System.out.println(allDeps);


//        em.close();
//        emf.close();
    }
}