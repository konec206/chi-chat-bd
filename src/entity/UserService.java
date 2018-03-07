/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import interfaces.UserInterface;
import interfaces.UserServiceInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import repository.UserRepository;

/**
 *
 * @author thibault
 */
public class UserService implements UserServiceInterface, Runnable {
    /**
     * TEMP
     */
    private ArrayList<UserInterface> users;

    /**
     * 
     */
    public UserService() {
        this.users = new ArrayList<>();
    }
    
    
    /**
     * 
     * @param username
     * @param plainPassword
     * @return
     * @throws RemoteException 
     */
    @Override
    public boolean authenticateUser(String username, String plainPassword) throws RemoteException {
        User user = UserRepository.getUser(username);
        
        return user != null && user.getPassword().equals(utils.Security.encodePassword(plainPassword));
    }

    
    @Override
    public void run() {
        //For later
        System.out.println("[USER SERVICE] User service running...");
    }    
}
