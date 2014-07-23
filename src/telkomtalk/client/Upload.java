/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.client;

import telkomtalk.UI.pkg.ChatUI;
import java.io.*;
import java.net.*;

/**
 *
 * @author Hawari Rahman
 */
public class Upload implements Runnable {
    public String addr;
    public int port;
    public Socket socket;
    public FileInputStream in;
    public OutputStream out;
    public File file;
    public ChatUI ui;
    
    public Upload(String addr, int port, File filepath, ChatUI ui) {
        super();
        try {
            file = filepath;
            this.ui = ui;
            socket = new Socket(InetAddress.getByName(addr), port);
            out = socket.getOutputStream();
            in = new FileInputStream(filepath);
        }
        catch(Exception ex) {
            System.out.println("Failed to construct upload: " + ex.getMessage());
        }
    }
    
    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int count;
            
            while((count = in.read(buffer)) >= 0) {
                out.write(buffer, 0, count);
            }
            out.flush();
            
            ui.insertMessage("File uploaded successfully");
            
            if(in != null) {
                in.close();
            }
            if(out != null) {
                out.close();
            }
            if(socket != null) {
                socket.close();
            }
        }
        catch(Exception ex) {
            System.out.println("Error in uploading: " + ex.getMessage());
        }
    }
}
