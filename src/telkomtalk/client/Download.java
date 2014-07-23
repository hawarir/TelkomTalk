/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.client;

import java.io.*;
import java.net.*;
import telkomtalk.UI.pkg.ChatUI;
/**
 *
 * @author Hawari Rahman
 */
public class Download implements Runnable {
    public ServerSocket server;
    public Socket socket;
    public int port;
    public String saveTo = "";
    public InputStream in;
    public FileOutputStream out;
    public ChatUI ui;
    
    public Download(String saveTo, ChatUI ui) {
        try {
            server = new ServerSocket(0);
            port = server.getLocalPort();
            this.saveTo = saveTo;
            this.ui = ui;
        }
        catch(IOException ex) {
            System.out.println("Failed to construct download: " + ex.getMessage());
        }
    }
    
    @Override
    public void run() {
        try {
            socket = server.accept();
            System.out.println("Download: " + socket.getRemoteSocketAddress());
            
            in = socket.getInputStream();
            out = new FileOutputStream(saveTo);
            
            byte[] buffer = new byte[1024];
            int count;
            
            while((count = in.read(buffer)) >= 0) {
                out.write(buffer, 0, count);
            }
            
            out.flush();
            
            ui.insertMessage("Download complete");
            
            if(out != null) {
                out.close();
            }
            if(in != null) {
                in.close();
            }
            if(socket != null) {
                socket.close();
            }
        }
        catch (Exception ex) {
            System.out.println("Error in downloading: " + ex.getMessage());
        }
    }
}
