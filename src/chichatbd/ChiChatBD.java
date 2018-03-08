/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chichatbd;

import entity.User;
import services.UserService;
import interfaces.UserServiceInterface;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.UserRepository;

/**
 *
 * @author thibault
 */
public class ChiChatBD {

    
    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        System.out.println("[DB SERVER] Starting...");
        
        //Server port
        int port = 3000;
        
        //UserStib creation
        UserService userService = new UserService();
        UserServiceInterface userStub;
        userStub = (UserServiceInterface) UnicastRemoteObject.exportObject(userService, port);
        
        //Registry creation
        Registry registry = LocateRegistry.createRegistry(port);
        
        try {
            //UserService Binding
            registry.bind("userService", userStub);
            System.out.println("[USER SERVICE] UserService running...");
        } catch (AlreadyBoundException | AccessException ex) {
            Logger.getLogger(ChiChatBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("[SERVER DB] Running...");
        
    }
    
}
