/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.User;
import interfaces.UserInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thibault
 */
public class UserRepository {
    
    //TODO Connection to DB/Serialization
    
    private static String userName = "chi_chat";
    private static String password = "Z-=37^3Jp";
    private static String dbms = "mysql";
    private static String serverName = "localhost";
    private static String portNumber = "3306";
    private static String dbName = "chi_chat_db";
    
    private ArrayList<UserInterface> users;
    
    public UserRepository() {
        this.users = new ArrayList<>();
        
        this.users.add(new User("root1", "fake", "user1", "root"));
        this.users.add(new User("root2", "fake", "user2", "root"));
        this.users.add(new User("root3", "fake", "user3", "root"));
        this.users.add(new User("root4", "fake", "user4", "root"));
        this.users.add(new User("root5", "fake", "user5", "root"));
    }
    
    /**
     * @param username
     * @return 
     */
    public UserInterface getUser(String username) {
        UserInterface userFound = null;
        for(UserInterface user : this.users) {
            try {
                if (user.getUsername().equals(username))
                    userFound = user;
            } catch (RemoteException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return userFound;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<UserInterface> getUsers() {
        return users;
    }

    /**
     * 
     * @param users 
     */
    public void setUsers(ArrayList<UserInterface> users) {
        this.users = users;
    }
    
    
}
