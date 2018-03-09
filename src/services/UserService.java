/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.User;
import interfaces.ContactRequestInterface;
import interfaces.UserInterface;
import interfaces.UserServiceInterface;
import java.rmi.RemoteException;
import repository.UserRepository;

/**
 *
 * @author thibault
 */
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    /**
     * Empty Constructor
     */
    public UserService() {
        this.userRepository = new UserRepository();
    }

    /**
     *
     * @param userName
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public UserInterface getUser(String userName) throws RemoteException, Exception {
        UserInterface user = this.userRepository.getUser(userName);
        if (user == null) {
            throw new Exception("[USER SERVICE] User " + userName + " not found");
        }
        
        return user;
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
        UserInterface user = this.userRepository.getUser(username);
        
        boolean authenticate = user != null && user.getPassword().equals(utils.Security.encodePassword(plainPassword));
        
        System.out.println("[USER SERVICE] User " + username + " has tried to authenticate : " + authenticate);

        return authenticate;
    }

    /**
     * Send a contactRequest from senderUserName to receiverUserName
     *
     * @param request
     * @return
     * @throws RemoteException
     * @throws Exception
     */
    @Override
    public UserInterface sendContactRequest(ContactRequestInterface request) throws RemoteException, Exception {
        UserInterface receiver = request.getReceiver();
        
        receiver.addContactRequest(request);

        System.out.println("[USER SERVICE] User " + request.getSender().getUsername() + " has sent a contactRequest to " + request.getReceiver().getUsername());
        
        return receiver;
    }

    /**
     *
     * @param answer
     * @param request
     * @return 
     * @throws RemoteException
     */
    @Override
    public UserInterface answerToContactRequest(boolean answer, ContactRequestInterface request) throws RemoteException {
        if (answer) {
            System.out.println("[USER SERVICE] Accept contactRequest : " + request.getReceiver().getUsername());
            request.setStatus(ContactRequestInterface.CONTACT_REQUEST_STATUS_ACCEPTED);

            request.getSender().addContact(request.getReceiver());
            request.getReceiver().addContact(request.getSender());
        } else {
            System.out.println("[USER SERVICE] Refuse contactRequest : " + request.getReceiver().getUsername());

            request.setStatus(ContactRequestInterface.CONTACT_REQUEST_STATUS_REFUSED);
        }

        request.getReceiver().removeContactRequest(request);
        
        return request.getReceiver();
    }

    /**
     * 
     * @param username
     * @param name
     * @param firstName
     * @param plainPassword
     * @return
     * @throws RemoteException 
     */
    @Override
    public UserInterface createUser(String username, String name, String firstName, String plainPassword) throws RemoteException {
        User user = new User(username, name, firstName, plainPassword);
        
        System.out.println("[USER SERVICE] User created : " + username);
        
        if (userRepository.getUser(username) == null) {
            System.out.println("[USER SERVICE] User registered : " + username);
            userRepository.getUsers().add(user);
        }
        
        return user;
    }
    
    
}
