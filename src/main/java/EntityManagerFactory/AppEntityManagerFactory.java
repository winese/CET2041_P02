package EntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;

public class AppEntityManagerFactory {
    private static EntityManagerFactory emf;

    private AppEntityManagerFactory(){}
    public static EntityManagerFactory getInstance(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("EmployeesPU");
        }
        return emf;
    }

//    public EntityManager createEntityManager() {
//        return
//    }
}
