/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ContactRequestInterface;
import interfaces.UserInterface;
import interfaces.UserServiceInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import repository.UserRepository;

/**
 *
 * @author thibault
 */
public final class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    /**
     * Constructor init Repository with an array of users from server side
     */
    public UserService() {
        this.userRepository = null;
    }    
    
    @Override
    public void initUserRepository(ArrayList<UserInterface> users) throws RemoteException {
        System.out.println("[USER SERVICE] UserRepository initialized");
        this.userRepository = new UserRepository(users);
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
    public void sendContactRequest(ContactRequestInterface request) throws RemoteException, Exception {        
        request.getReceiver().addContactRequest(request);

        System.out.println("[USER SERVICE] User " + request.getSender().getUsername() + " has sent a contactRequest to " + request.getReceiver().getUsername());
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
     * @param user
     * @return
     * @throws RemoteException 
     */
    @Override
    public UserInterface createUser(UserInterface user) throws Exception, RemoteException {
        
        System.out.println("[USER SERVICE] User created : " + user.getUsername());
        
        if (userRepository.getUser(user.getUsername()) != null)
            throw new Exception("[REGISTER] This username is already taken");
        
        System.out.println("[USER SERVICE] User registered : " + user.getUsername());
        userRepository.getUsers().add(user);
        
        
        return user;
    }
    
    
}
