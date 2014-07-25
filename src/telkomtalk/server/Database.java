/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.server;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hawari Rahman
 */
public class Database {
    private Connection conn;
    private Statement st;
    private ResultSet result;
    
    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telkom_talk", "root", "root");
            st = conn.createStatement();
        }
        catch(SQLException ex) {
            System.out.println("Unable to connect to the database: " + ex.getMessage());
        }
    }
    
    public boolean checkUsername(String username) {
        try {
            String query = new String("Select * FROM user WHERE username = '" + username + "'");
            result = st.executeQuery(query);
            if(!result.first())
                return true;
            else
                return false;
        }
        catch(SQLException ex) {
            System.out.println("Could not check username availability: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean checkLogin(String username, String password) {
        try {
            String query = new String("SELECT * FROM user WHERE username = '" + username + "' AND password ='" + password +"'");
            result = st.executeQuery(query);
            if(result.first())
                return true;
            else
                return false;
        }
        catch(SQLException ex) {
            System.out.println("Could not validate login: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean register(String username, String name, String password) {
        try {
            String query = new String("INSERT INTO user (username, password, name) VALUES ('" + username + "', '" + password + "', '" + name + "')");
            st.executeUpdate(query);
            return true;
        }
        catch(SQLException ex) {
            System.out.println("Failed to register " + username + ": " + ex.getMessage());
            return false;
        }
    }
    
    public boolean update(String username, String name, String password) {
        try {
            String query = new String("UPDATE user SET name = '" + name + "', password = '" + password + "' WHERE username = '" + username + "'");
            st.executeUpdate(query);
            return true;
        }
        catch(SQLException ex) {
            System.out.println("Failed to update " + username + ": " + ex.getMessage());
            return false;
        }
    }
    
    public void storeMessage(Message msg) {
        try {
            String query = new String("INSERT INTO message (sender, content, recipient, time, seen)"
                    + " VALUES ('" + msg.sender + "', '" + msg.content + "', '" + msg.recipient + "', now(), 1)");
            st.executeUpdate(query);
        }
        catch(SQLException ex) {
            System.out.println("Failed to store message: " + ex.getMessage());
        }
    }
    
    public void queueMessage(Message msg) {
        try {
            String query = new String("INSERT INTO message (sender, content, recipient, time, seen)"
                    + " VALUES ('" + msg.sender + "', '" + msg.content + "', '" + msg.recipient + "', now(), 0)");
            st.executeUpdate(query);
        }
        catch(SQLException ex) {
            System.out.println("Failed to store message: " + ex.getMessage());
        }
    }
    
    public ArrayList<Message> getUnreadMessage(String username) {
        try {
            String query = new String("SELECT * FROM message WHERE recipient = '" + username + "' AND seen = 0");
            result = st.executeQuery(query);
            ArrayList<Message> message = new ArrayList<Message>();
            while(result.next()) {
                message.add(new Message("message", result.getString("sender"), result.getString("content"), result.getString("recipient")));
            }
            
            query = new String("UPDATE message SET seen = 1 WHERE recipient = '" + username + "' AND seen = 0");
            st.executeUpdate(query);
            
            return message;
        }
        catch(SQLException ex) {
            System.out.println("Failed to retrieve message: " + ex.getMessage());
            return null;
        }
    }
    
    public short addContact(Message msg) {
        try {
            String query = new String("SELECT * FROM user WHERE username = '" + msg.content + "'");
            result = st.executeQuery(query);
            if(result.first()) {
                query = new String("SELECT * FROM buddy_list WHERE user = '" + msg.sender + "' AND friend = '" + msg.content + "'");
                result = st.executeQuery(query);
                if(!result.first()) {
                    query = new String("INSERT INTO buddy_list VALUES ('" + msg.sender + "', '" + msg.content + "')");
                    st.executeUpdate(query);
                    
                    query = new String("INSERT INTO buddy_list VALUES ('" + msg.content + "', '" + msg.sender + "')");
                    st.executeUpdate(query);
                    return 0;
                }
                else {
                    System.out.println(msg.sender + " already friends with " + msg.content);
                    return -1;
                }
            }
            else {
                System.out.println("Couldn't find " + msg.content + " in user list");
                return -2;
            }
        }
        catch(SQLException ex) {
            System.out.println("Failed to add Buddy: " + ex.getMessage());
            return -3;
        }
    }
    
    public ArrayList<String> getContacts(String username) {
        try {
            String query = new String("SELECT friend FROM buddy_list where user = '" + username + "'");
            result = st.executeQuery(query);
            ArrayList<String> contacts = new ArrayList<String>();
            while(result.next()) {
                contacts.add(result.getString("friend"));
            }
            return contacts;
        }
        catch(SQLException ex) {
            System.out.println("Failed to get Contacts for " + username + ": " + ex.getMessage());
            return null;
        }
    }
    
    public String getName(String username) {
        try {
            String query = new String("SELECT name FROM user WHERE username = '" + username + "'");
            result = st.executeQuery(query);
            result.first();
            return result.getString("name");
        }
        catch(SQLException ex) {
            System.out.println("Failed to get Name for " + username + ": " + ex.getMessage());
            return null;
        }
    }
}
