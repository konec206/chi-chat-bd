/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.User;
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
     * Empty Constructor
     */
    public UserService() {
        this.users = new ArrayList<>();
    }
    
    
    /**
     * Authenticate a user from his username and the plainPassword
     * 
     * @param username
     * @param plainPassword
     * @return boolean
     * @throws RemoteException 
     */
    @Override
    public boolean authenticateUser(String username, String plainPassword) throws RemoteException {
        User user = UserRepository.getUser(username);
        
        return user != null && user.getPassword().equals(utils.Security.encodePassword(plainPassword));
    }

    /**
     * Send a contactRequest from senderUserName to receiverUserName
     * @param senderUserName
     * @param receiverUserName
     * @return
     * @throws RemoteException
     * @throws Exception 
     */
    @Override
    public boolean sendContactRequest(String senderUserName, String receiverUserName) throws RemoteException, Exception {
        User sender = UserRepository.getUser(senderUserName);
        User receiver = UserRepository.getUser(receiverUserName);
        
        if (sender == null || receiver == null)
            throw new Exception("[USER SERVICE] " + senderUserName + " not found or " + receiverUserName + " not found!");
        
        receiver.addContactRequest(sender);
        return true;
    }
        
    @Override
    public void run() {
        //For later
        System.out.println("[USER SERVICE] User service running...");
    }    
}
