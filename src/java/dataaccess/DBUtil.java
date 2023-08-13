package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author from lecture
 */
public class DBUtil {
    
    // factory is used to create entity managers based on persist xml 
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("usersPU");
    
    // get this em factory 
    public static EntityManagerFactory getEmFactory() {
        
        return emf; 
    }
    
}