/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author thibault
 */
public interface UserInterface extends Remote {
    /**
     * @return String
     * @throws java.rmi.RemoteException
     */
    public String getUsername() throws RemoteException;
    
    /**
     * @return String
     * @throws java.rmi.RemoteException
     */
    public String getName() throws RemoteException;
    
    /**
     * @return String
     * @throws java.rmi.RemoteException
     */
    public String getFirstName() throws RemoteException;
    
    /**
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public String getPassword() throws RemoteException;
    
    /**
     * @return ArrayList
     * @throws java.rmi.RemoteException
     */
    public ArrayList<UserInterface> getContacts() throws RemoteException;
    
    /**
     * @param contact 
     * @throws java.rmi.RemoteException 
     */
    public void addContact(UserInterface contact) throws RemoteException;
    
    /**
     * @param contact 
     * @throws java.rmi.RemoteException 
     */
    public void removeContact(UserInterface contact) throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException 
     */
    public ArrayList<UserInterface> getContactRequest() throws RemoteException;
    
    /**
     * @param user
     * @throws RemoteException 
     */
    public void addContactRequest(UserInterface user) throws RemoteException;
    
    /**
     * @param user
     * @throws RemoteException 
     */
    public void removeContactRequest(UserInterface user) throws RemoteException;
}
