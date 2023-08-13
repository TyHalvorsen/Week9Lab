/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.ArrayList;
import models.Role;

/**
 *
 * @author octov
 */
public class RoleService {
    
    private final RoleDB ROLE_DB;

    public RoleService() {
        this.ROLE_DB = new RoleDB();

    }

    public ArrayList<Role> getAll() throws Exception {

       return null ;
    }
   
   
     public void insert(Role role) throws Exception {

  
}
     
     
}