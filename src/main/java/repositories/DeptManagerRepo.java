package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import EntityManagerFactory.AppEntityManagerFactory;
import entities.DeptManager;

public class DeptManagerRepo {
    private EntityManagerFactory emf = AppEntityManagerFactory.getInstance();
    EntityManager em = emf.createEntityManager();

    public DeptManager queryLatestDeptManager(String deptNo, int empNo) {
        String query = "SELECT d " +
                "FROM DeptManager d " +
                "WHERE d.deptNo = '" + deptNo +
                "' AND " +
                "d.empNo = '" + empNo +
                "' AND " +
                "d.toDate > CURRENT_DATE";
        return em.createQuery(query, DeptManager.class).getSingleResult();
    }

    public DeptManager insertNewDeptManager(String deptNo, int empNo) {
        LocalDate to = LocalDate.parse("31-12-9999");
        DeptManager oldDeptManager = queryLatestDeptManager(deptNo, empNo);
        DeptManager newDeptManager = new DeptManager();

        //Update existing salary toDate if exist
        if (oldDeptManager != null) {
            oldDeptManager.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
            em.persist(oldDeptManager);
        }

        //Insert new department manager
        newDeptManager.setDeptNo(deptNo);
        newDeptManager.setEmpNo(empNo);
        newDeptManager.setFromDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        newDeptManager.setToDate(LocalDate.parse(to.format((DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        em.persist(newDeptManager);
        return newDeptManager;
    }
}