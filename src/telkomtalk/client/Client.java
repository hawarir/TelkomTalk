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
    public MaintUI mainUI = null;
    public RegisterUI registerUI = null;
    
    public Client() {
        try {
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
    
    public void setLoginUI(LoginUI ui) {
        loginUI = ui;
    }
    
    public void setMainUI(MaintUI ui) {
        mainUI = ui;
    }
    
    public void setRegisterUI(RegisterUI ui) {
        registerUI = ui;
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
                    mainUI.getmessage(msg);
                    System.out.println(msg.sender + ": " + msg.content);
                }
                else if(msg.type.equals("contacts")) {
                    String[] content = msg.content.split("@");
                    String contactUsername = content[0];
                    String contactName = content[1];
                    
                    System.out.println(contactUsername + " " + contactName);
                    
                    mainUI.addContact(contactUsername, contactName);
                }
                else if(msg.type.equals("addcontact")) {
                    if(msg.content.equals("TRUE")) {
                        System.out.println("Add Contact Successful");
                    }
                    else {
                        String content[] = msg.content.split(":");
                        String code = content[1];
                        
                        if(code.equals("-1")) {
                            System.out.println("You already have that person in your contact");
                        }
                        else if(code.equals("-2")) {
                            System.out.println("Couldn't find contact in user list");
                        }
                        else if(code.equals("-3")) {
                            System.out.println("Server Error");
                        }
                    }
                }
                else if(msg.type.equals("update")) {
                    if(msg.content.equals("TRUE")) {
                        System.out.println("Update successful");
                    }
                    else {
                        System.out.println("Update failed");
                    }
                }
                else if(msg.type.equals("send_req")) {
                    mainUI.getFile(msg);
                }
                else if(msg.type.equals("send_acc")) {
                    mainUI.uploadFile(msg);
                }
            }
            catch(IOException ex) {
                System.out.println("Failed to read message (IOException): " + ex.getMessage());
            }
            catch(ClassNotFoundException ex) {
                System.out.println("Failed to read message (ClassNotFoundException): " + ex.getMessage());
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
    
    public boolean register(String username, String name, String password) {
        try {
            password = encryptPassword(password);
            send(new Message("register", username, name + "@" + password, "SERVER"));
            Message msg = (Message) in.readObject();
            if(msg.content.equals("TRUE")) {
                
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception ex) {
            System.out.println("Failed to register: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean update(String name, String password) {
        try {
            password = encryptPassword(password);
            send(new Message("update", this.username, name + "@" + password, "SERVER"));
            return true;
        }
        catch(Exception ex) {
            System.out.println("Failed to update: " + ex.getMessage());
            return false;
        }
    }
    
    public void addContacts(String contact) {
        try {
            send(new Message("addcontact", username, contact, "SERVER"));
        }
        catch(Exception ex) {
            System.out.println("Failed to add contact: " + ex.getMessage());
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
