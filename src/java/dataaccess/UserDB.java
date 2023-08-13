package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

/**
 *
 * @author TyHalvorsen + lecture
 */
public class UserDB {

    public UserDB() {

    }

    public List<User> getAllUsers(int roleID) throws Exception {

        EntityManager eManager = DBUtil.getEmFactory().createEntityManager();

        try {

            Role roles = eManager.find(Role.class, roleID);
            return roles.getUserList();

        } finally {

            eManager.close();

        }

    }

    public User get(int roleID) throws Exception {

        EntityManager eManager = DBUtil.getEmFactory().createEntityManager();

        try {

            User user = eManager.find(User.class, roleID); 
            return user;
        } finally {
            eManager.close(); 
        }

    }

    public void insert(Role role, User user) throws Exception {

        EntityManager eManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            role.getUserList().add(user); 
            eTransaction.begin();
            eManager.persist(user);
            eTransaction.commit(); 
        } catch (Exception ex) {
            eTransaction.rollback(); 
        } finally {
            eManager.close();
        }
    }

    public void update(User user) throws Exception {

        EntityManager eManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            eTransaction.begin();
            eManager.merge(user);
            eTransaction.commit();
        } catch (Exception ex) {
            eTransaction.rollback(); 

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

