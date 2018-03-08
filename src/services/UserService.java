/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.ContactRequest;
import entity.User;
import interfaces.ContactRequestInterface;
import interfaces.UserInterface;
import interfaces.UserServiceInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
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

        return user != null && user.getPassword().equals(utils.Security.encodePassword(plainPassword));
    }

    /**
     * Send a contactRequest from senderUserName to receiverUserName
     *
     * @param senderUserName
     * @param receiverUserName
     * @return
     * @throws RemoteException
     * @throws Exception
     */
    @Override
    public boolean sendContactRequest(String senderUserName, String receiverUserName) throws RemoteException, Exception {
        User sender = new User(this.userRepository.getUser(senderUserName));
        User receiver = new User(this.userRepository.getUser(receiverUserName));

        if (sender == null) {
            throw new Exception("[USER SERVICE] " + senderUserName + " not found");
        }

        if (receiver == null) {
            throw new Exception("[USER SERVICE] " + receiverUserName + " not found");
        }

        receiver.addContactRequest(new ContactRequest(sender, receiver, new Date()));

        return true;
    }

    /**
     *
     * @param answer
     * @param request
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
}
