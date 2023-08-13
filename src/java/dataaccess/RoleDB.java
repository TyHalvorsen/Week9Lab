package dataaccess;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

/**
 *
 * @author TyHalvorsen + Lecture 
 */
public class RoleDB {

    Role role;
    
    public RoleDB(){
        
    }

    public Role get(String email) throws Exception {
        
        EntityManager eManager = DBUtil.getEmFactory().createEntityManager();

        try {

            Role roles = eManager.find(Role.class, email);
            return roles;

        } finally {

            eManager.close();

        }

    }

       public void delete(User user, Role role) throws Exception {

        EntityManager eManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            role.getUserList().remove(user); 
            eTransaction.begin();
            eManager.remove(eManager.merge(user));
            eManager.merge(role);
            eTransaction.commit(); 
        } catch (Exception ex) {
            eTransaction.rollback(); 

        } finally {
            eManager.close();
        }

    }
}
