package EntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;

public class AppEntityManagerFactory {
    private static EntityManagerFactory emf;
    static final String DBNAME = "employees";

    static Map<String, String> persistenceMap = Map.of(
            "jakarta.persistence.jdbc.url",
            "jdbc:mariadb://localhost:3306/"+DBNAME
    );

    private AppEntityManagerFactory(){}
    public static EntityManagerFactory getInstance(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("EmployeesPU",
                    persistenceMap);
        }
        return emf;
    }

//    public EntityManager createEntityManager() {
//        return
//    }
}
