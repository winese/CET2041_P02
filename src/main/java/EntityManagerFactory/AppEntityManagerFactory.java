package EntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Utility class that implements to Singleton and Abstract Factory pattern.
 * Creates entity Managers.
 */
public class AppEntityManagerFactory {
    private static EntityManagerFactory emf;

    /**
     * Constructor for EntityManagerFactory
     */
    private AppEntityManagerFactory(){}

    /**
     * getter for Entity Manager Instance
     * @return Entity Manager Instance
     */
    public static EntityManagerFactory getInstance(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("EmployeesPU");
        }
        return emf;
    }

}
