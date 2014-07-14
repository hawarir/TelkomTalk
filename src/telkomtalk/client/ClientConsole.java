/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.client;

import java.io.IOException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import telkomtalk.server.Message;

/**
 *
 * @author Hawari Rahman
 */
public class ClientConsole {
    public static void main(String[] args) throws Exception{
        Client client = new Client();
        Message msg = null;
        Scanner in = new Scanner(System.in);
        String username = null;
        String password = null;
        
        System.out.print("Enter username: ");
        username = in.nextLine();

        System.out.print("Enter password: ");
        password = client.encryptPassword(in.nextLine());

        msg = new Message("login", username, password, "SERVER");
        client.send(msg);
        
        while(true) {
//            System.out.print("To: ");
//            String to = in.nextLine();
//            
//            System.out.print("Message: ");
            String message = in.nextLine();
            
            msg = new Message("addbuddy", username, message, "SERVER");
            client.send(msg);
        }
    }
}
