/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import interfaces.UserInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author thibault
 */
public class User implements UserInterface {

    private String userName;
    private String name;
    private String firstName;
    private String password;
    private ArrayList<UserInterface> contacts;

    public User() {}
    
    public User(String username, String name, String firstName, String plainPassword) {
        this.userName = username;
        this.name = name;
        this.firstName = firstName;
        this.password = utils.Security.encodePassword(plainPassword);
        this.contacts = new ArrayList<>();
    }
    
    @Override
    public String getUsername() throws RemoteException {
        return this.userName;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public String getFirstName() throws RemoteException {
        return this.firstName;
    }

    @Override
    public String getPassword() throws RemoteException {
        return this.password;
    }

    @Override
    public ArrayList<UserInterface> getContacts() throws RemoteException {
        return this.contacts;
    }

    @Override
    public void addContact(UserInterface contact) throws RemoteException {
        if (!this.contacts.contains(contact))
            this.contacts.add(contact);
    }

    @Override
    public void removeContact(UserInterface contact) throws RemoteException {
        if (this.contacts.contains(contact))
            this.contacts.remove(contact);
    }   
}
