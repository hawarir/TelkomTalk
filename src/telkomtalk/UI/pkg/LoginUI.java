/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.UI.pkg;

import java.awt.Point;
import javax.swing.ImageIcon;
import telkomtalk.client.Client;
import telkomtalk.server.Message;

/**
 *
 * @author AhmadFauzi__
 */
public class LoginUI extends javax.swing.JFrame {
    Point point = new Point();
    Client client = null;
    /**
     * Creates new form LoginUI
     */
    public LoginUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginButton = new javax.swing.JLabel();
        registerButton = new javax.swing.JLabel();
        closeButton = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        messageLabel = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_login1.png"))); // NOI18N
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButtonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginButtonMouseReleased(evt);
            }
        });
        getContentPane().add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 316, 250, -1));

        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_register_default.png"))); // NOI18N
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                registerButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                registerButtonMouseReleased(evt);
            }
        });
        getContentPane().add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 470, 250, -1));

        closeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_default.png"))); // NOI18N
        closeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeButtonMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButtonMouseReleased(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 3, 20, 20));

        minimizeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_default.png"))); // NOI18N
        minimizeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                minimizeButtonMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseReleased(evt);
            }
        });
        getContentPane().add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 3, 20, 20));

        username.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        username.setBorder(null);
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 214, 200, 30));

        password.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        password.setBorder(null);
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 266, 200, 30));

        messageLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 0, 0));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 260, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/LoginUI.png"))); // NOI18N
        background.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                backgroundAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        point.x = evt.getX();
        point.y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point p = getLocation();
        setLocation(p.x+evt.getX()-point.x,p.y+evt.getY()-point.y);
    }//GEN-LAST:event_formMouseDragged

    private void backgroundAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_backgroundAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_backgroundAncestorAdded

    private void loginButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseEntered
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_login2.png"));
        loginButton.setIcon(loginImage);
    }//GEN-LAST:event_loginButtonMouseEntered

    private void loginButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseExited
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_login1.png"));
        loginButton.setIcon(loginImage);
    }//GEN-LAST:event_loginButtonMouseExited

    private void loginButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseReleased
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_login1.png"));
        loginButton.setIcon(loginImage);
        
        client = new Client(this);
        String user = username.getText();
        String pass = new String(password.getPassword());
        if(client.login(user, client.encryptPassword(pass))) {
            client.start();
            MaintUI chat = new MaintUI();
            chat.setClient(client);
            chat.show();
            dispose();
        }
        else {
            username.setText("");
            password.setText("");
            messageLabel.setText("Incorrect username or password");
            username.requestFocus();
        }
    }//GEN-LAST:event_loginButtonMouseReleased

    private void loginButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMousePressed
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_login3.png"));
        loginButton.setIcon(loginImage);
    }//GEN-LAST:event_loginButtonMousePressed

    private void registerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseEntered
        ImageIcon registerImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_register_hover.png"));
        registerButton.setIcon(registerImage);
    }//GEN-LAST:event_registerButtonMouseEntered

    private void registerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseExited
        ImageIcon registerImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_register_default.png"));
        registerButton.setIcon(registerImage);
    }//GEN-LAST:event_registerButtonMouseExited

    private void registerButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMousePressed
        ImageIcon registerImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_register_pressed.png"));
        registerButton.setIcon(registerImage);
    }//GEN-LAST:event_registerButtonMousePressed

    private void registerButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseReleased
        ImageIcon registerImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_register_default.png"));
        registerButton.setIcon(registerImage);
        
        RegisterUI register = new RegisterUI();
        register.show();
    }//GEN-LAST:event_registerButtonMouseReleased

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_hover.png"));
        closeButton.setIcon(closeImage);
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_default.png"));
        closeButton.setIcon(closeImage);
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMousePressed
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_pressed.png"));
        closeButton.setIcon(closeImage);
    }//GEN-LAST:event_closeButtonMousePressed

    private void closeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseReleased
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_default.png"));
        closeButton.setIcon(closeImage);
        
        System.exit(0);
    }//GEN-LAST:event_closeButtonMouseReleased

    private void minimizeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseEntered
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_hover.png"));
        minimizeButton.setIcon(minimizeImage);
    }//GEN-LAST:event_minimizeButtonMouseEntered

    private void minimizeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseExited
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_default.png"));
        minimizeButton.setIcon(minimizeImage);
    }//GEN-LAST:event_minimizeButtonMouseExited

    private void minimizeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMousePressed
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_pressed.png"));
        minimizeButton.setIcon(minimizeImage);
    }//GEN-LAST:event_minimizeButtonMousePressed

    private void minimizeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseReleased
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_default.png"));
        minimizeButton.setIcon(minimizeImage);
        
        setExtendedState(getExtendedState()| LoginUI.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseReleased

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        client = new Client(this);
        String user = username.getText();
        String pass = new String(password.getPassword());
        if(client.login(user, client.encryptPassword(pass))) {
            client.start();
            MaintUI chat = new MaintUI();
            chat.setClient(client);
            chat.show();
            dispose();
        }
        else {
            username.setText("");
            password.setText("");
            messageLabel.setText("Incorrect username or password");
            username.requestFocus();
        }
    }//GEN-LAST:event_usernameActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        client = new Client(this);
        String user = username.getText();
        String pass = new String(password.getPassword());
        if(client.login(user, client.encryptPassword(pass))) {
            client.start();
            MaintUI chat = new MaintUI();
            chat.setClient(client);
            chat.show();
            dispose();
        }
        else {
            username.setText("");
            password.setText("");
            messageLabel.setText("Incorrect username or password");
            username.requestFocus();
        }
    }//GEN-LAST:event_passwordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel closeButton;
    private javax.swing.JLabel loginButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel registerButton;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
