/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.User;

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
    
    /**
     * @param username
     * @return 
     */
    public static User getUser(String username) {
        return new User("root", "fake", "user", "root");
    }
}
