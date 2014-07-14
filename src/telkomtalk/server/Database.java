/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.server;

import java.sql.*;

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
            System.out.println(query);
            st.executeUpdate(query);
        }
        catch(SQLException ex) {
            System.out.println("Failed to store message: " + ex.getMessage());
        }
    }
    
    public void getUserList() {
        try {
            String query = new String("SELECT * FROM user");
            result = st.executeQuery(query);
            System.out.println("Daftar User");
            while(result.next()) {
                String username = result.getString("username");
                String name = result.getString("name");
                
                System.out.println("username: " + username + " name: " + name);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
