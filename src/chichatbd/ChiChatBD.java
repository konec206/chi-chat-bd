/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chichatbd;

import entity.User;
import java.rmi.RemoteException;
import java.sql.SQLException;
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
     */
    public static void main(String[] args) throws RemoteException {
        System.out.println("[TEST DB]");
        
        try {
            User user = UserRepository.getUser("root");
        } catch (SQLException ex) {
            Logger.getLogger(ChiChatBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
