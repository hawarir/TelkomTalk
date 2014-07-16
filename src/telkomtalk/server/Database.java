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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telkom_talk", "telkom", "passwordtelkom");
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
    
    public void storeMessage(Message msg) {
        try {
            String query = new String("INSERT INTO message (sender, content, recipient, time)"
                    + " VALUES ('" + msg.sender + "', '" + msg.content + "', '" + msg.recipient + "', now())");
            st.executeUpdate(query);
        }
        catch(SQLException ex) {
            System.out.println("Failed to store message: " + ex.getMessage());
        }
    }
    
    public short addBuddy(Message msg) {
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
                    return 1;
                }
            }
            else {
                System.out.println("Couldn't find " + msg.content + " in user list");
                return -2;
            }
        }
        catch(SQLException ex) {
            System.out.println("Failed to add Buddy: " + ex.getMessage());
            return -1;
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
}
