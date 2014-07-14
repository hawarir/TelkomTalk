/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.client;

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
    public Socket socket = null;
    public ObjectInputStream in = null;
    public ObjectOutputStream out = null;
    public Thread thread;
    public boolean isLogin;
    
    public Client() {
        try {
            isLogin = false;
            serverAddress = "localhost";
            port = 5000;
            socket = new Socket(InetAddress.getByName(serverAddress), port);
            
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            start();
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
                
                if(msg.type.equals("message")) {
                    System.out.println(msg.sender + ": " + msg.content);
                }
                else if(msg.type.equals("login")) {
                    if(msg.content.equals("TRUE")) {
                        System.out.println("Login Successful");
                        isLogin = true;
                    }
                    else {
                        System.out.println("Login Failed");
                    }
                }
            }
            catch(Exception ex) {
                System.out.println("Failed to read message");
                ex.printStackTrace();
            }
        }
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
