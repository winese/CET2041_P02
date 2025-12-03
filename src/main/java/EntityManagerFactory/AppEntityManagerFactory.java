package EntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Utility class that implements to Singleton and Abstract Factory pattern.
 * Creates entity Managers.
 */
public class AppEntityManagerFactory {
    private static AppEntityManagerFactory instance;
    private static EntityManagerFactory emf;

    /**
     * Constructor for EntityManagerFactory
     */
    private AppEntityManagerFactory(){
        emf = Persistence.createEntityManagerFactory("EmployeesPU");
    }

    /**
     * getter for Entity Manager Instance
     * @return Entity Manager Instance
     */
    public static EntityManagerFactory getInstance(){
        if (instance == null){
            instance = new AppEntityManagerFactory();
        }
        return emf;
    }
}
