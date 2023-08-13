/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author octov
 */
public class UserService {

    private final UserDB USER_DB = new UserDB();

    public UserService() {

    }

    public List<User> getAll(int roleID) throws Exception {

        List<User> users = USER_DB.getAllUsers(roleID);
        return users;
    }
    
     public User get(int roleID) throws Exception {

        User users = USER_DB.get(roleID);
        return users;
    }

    public void insert(Role role, User user) throws Exception {

        USER_DB.insert(role, user);
    }



    public void update(User user) throws Exception {

        USER_DB.update(user );
    }


    
}