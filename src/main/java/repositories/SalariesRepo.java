////package repositories;
////
////import entities.Salaries;
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.TypedQuery;
////
////import java.time.LocalDate;
////import java.util.List;
////
////public class SalariesRepo {
////    protected EntityManager em;
////
////    public SalariesRepo(EntityManager em) {
////        this.em = em;
////    }
////
////    //    public Employee createEmployee(int id, String name, long salary) {
//////        Employee emp = new Employee(id);
//////        emp.setName(name);
//////        emp.setSalary(salary);
//////        em.persist(emp);
//////        return emp;
//////    }
//////
//////    public void removeEmployee(int id) {
//////        Employee emp = findEmployee(id);
//////        if (emp != null) {
//////            em.remove(emp);
//////        }
//////    }
//////
//////    public Employee raiseEmployeeSalary(int id, long raise) {
//////        Employee emp = em.find(Employee.class, id);
//////        if (emp != null) {
//////            emp.setSalary(emp.getSalary() + raise);
//////        }
//////        return emp;
//////    }
//////
//////    public List<Salaries> findEmployeeSalariesWithHistories(int employees) {
//////        TypedQuery<Salaries> query = em.createQuery(
//////                  "SELECT s FROM Salaries s WHERE s.salaryId.employees = :employees", Salaries.class);
//////        query.setParameter("employees", employees);
//////        return query.getResultList();
//////    }
//////
//////    public List<Employee> findAllEmployees() {
//////        TypedQuery<Employee> query = em.createQuery(
//////                  "SELECT e FROM Employee e", Employee.class);
//////        return query.getResultList();
//////    }
////}
//
//package repositories;
//
//import entities.Salaries;
//import jakarta.persistence.EntityManager;
//import entities.Employees;
//import jakarta.persistence.EntityManagerFactory;
//import EntityManagerFactory.AppEntityManagerFactory;
//import jakarta.persistence.TypedQuery;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//public class SalariesRepo {
//    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//
//    //    public Employee createEmployee(int id, String name, long salary) {
////        Employee emp = new Employee(id);
////        emp.setName(name);
////        emp.setSalary(salary);
////        em.persist(emp);
////        return emp;
////    }
////
////    public void removeEmployee(int id) {
////        Employee emp = findEmployee(id);
////        if (emp != null) {
////            em.remove(emp);
////        }
////    }
////
//    public Salaries queryLatestEmpSalary(int employees) {
//        String query = "SELECT s " +
//                "FROM Salaries s " +
//                "WHERE s.employees = '" + employees +
//                "' AND " +
//                "s.toDate > CURRENT_DATE";
//        return em.createQuery(query, Salaries.class).getSingleResult();
//    }
//
//    public Salaries insertNewEmployeeSalary(int employees, int raise) {
//        LocalDate to = LocalDate.parse("31-12-9999");
//        Salaries oldSalary = queryLatestEmpSalary(employees);
//        Salaries newSalary = new Salaries();
//
//        //Update existing salary toDate if exist
//        if (oldSalary != null) {
//            oldSalary.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
//            em.persist(oldSalary);
//        }
//
//        //Insert new salary
//        newSalary.setEmployees(employees);
//        newSalary.setSalary(raise);
//        newSalary.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
//        newSalary.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
//        em.persist(newSalary);
//        return newSalary;
//    }
////
////    public Employees findEmployee(int employees) {
////        return em.find(Employees.class, employees);
////    }
////
////    public Employees findEndPoint3Infos(int employees) {
////        return em.createQuery("SELECT e.employees, " +
////                                    "e.firstName, " +
////                                    "e.lastName, " +
////                                    "e.hireDate " +
////                                "FROM Employees e " +
////                                "WHERE e.employees = :employees",
////                Employees.class).setParameter("employees", employees).getSingleResult();
////    }
//}