/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.client;

import telkomtalk.UI.pkg.*;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import telkomtalk.server.Message;

/**
 *
 * @author Hawari Rahman
 */

public class Client implements Runnable {
    
    public int port;
    public String serverAddress = "";
    public String username = "";
    public Socket socket = null;
    public ObjectInputStream in = null;
    public ObjectOutputStream out = null;
    public Thread thread;
    public LoginUI loginUI = null;
    
    public Client(LoginUI ui) {
        try {
            loginUI = ui;
            serverAddress = "localhost";
            port = 5000;
            socket = new Socket(InetAddress.getByName(serverAddress), port);
            
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
        }
        catch(IOException ex) {
            System.out.println("Failed to start client");
            ex.printStackTrace();
        }
    }
    
    public String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i=0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch(NoSuchAlgorithmException ex) {
            System.out.println("Error: " + ex.getMessage());
            return "fail";
        }
    }
    
    public void start() {
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public void stop() {
        if(thread != null) {
            thread.stop();
            thread = null;
        }
    }
    
    public void run() {
        while(true) {
            try {
                Message msg = (Message) in.readObject();
                System.out.println("Incoming: " + msg.toString());
                if(msg.type.equals("message")) {
                    System.out.println(msg.sender + ": " + msg.content);
                }
                else if(msg.type.equals("addbuddy")) {
                    if(msg.content.equals("TRUE")) {
                        System.out.println("Success!");
                    }
                    else {
                        System.out.println(msg.content);
                    }
                }
                else if(msg.type.equals("contacts")) {
                    System.out.println(msg.content);
                }
            }
            catch(Exception ex) {
                System.out.println("Failed to read message");
                ex.printStackTrace();
            }
        }
    }
    
    public boolean login(String username, String password) {
        try {
            send(new Message("login", username, password, "SERVER"));
            Message msg = (Message) in.readObject();
            System.out.println(msg.toString());
            if(msg.content.equals("TRUE")) {
                this.username = username; 
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception ex) {
            System.out.println("Failed to login: " + ex.getMessage());
            return false;
        }
    }
    
    public void getContacts() {
        send(new Message("contacts", this.username, "", "SERVER"));        
    }
    
    public void send(Message msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("Outgoing: " + msg.toString());
        }
        catch(IOException ex) {
            System.out.println("Failed to send message");
            ex.printStackTrace();
        }
    }
}
