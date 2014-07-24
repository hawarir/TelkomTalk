/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.UI.pkg;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.PasswordField;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import telkomtalk.client.*;
import telkomtalk.server.Message;

/**
 *
 * @author AhmadFauzi__
 */
public class MaintUI extends javax.swing.JFrame {
    Point point = new Point();
    Client client = null;
    DefaultTableModel model;
    ArrayList<ChatUI> activeChat = null;
    String fileRecipient;
    /**
     * Creates new form ChatUI
     */
    public MaintUI() {
        initComponents();
        activeChat = new ArrayList<>();
    }
    
    public void setClient(Client _client) {
        this.client = _client;
        getContacts();
    }
    
    public void addContact(String username, String name) {
        model = (DefaultTableModel) contactTable.getModel();
        model.addRow(new Object[]{username, name});
    }
    
    public void getContacts() {
        model = (DefaultTableModel) contactTable.getModel();
        model.setRowCount(0);
        client.getContacts();
    }
    
    public void addActive(ChatUI ui) {
        activeChat.add(ui);
    }
    
    public void removeActive(ChatUI ui) {
        activeChat.remove(ui);
    }
    
    public ChatUI findActive(String partnerName) {
        for(int i = 0; i < activeChat.size(); i++) {
            if(activeChat.get(i).getPartner().equals(partnerName)) {
                return activeChat.get(i);
            }
        }
        return null;
    }
    
    public void getmessage(Message msg) {
        if(findActive(msg.sender) != null) {
            findActive(msg.sender).setVisible(true);
            findActive(msg.sender).insertMessage(msg.content);
        }
        else {
            ChatUI chatUI = new ChatUI();
            chatUI.setMainUI(this);
            chatUI.setPartner(msg.sender);
            addActive(chatUI);
            chatUI.setVisible(true);
            chatUI.insertMessage(msg.content);
        }
    }
    
    public void getFile(Message msg) {
        if(findActive(msg.sender) == null) {
            ChatUI chatUI = new ChatUI();
            chatUI.setMainUI(this);
            chatUI.setPartner(msg.sender);
            addActive(chatUI);
            chatUI.setVisible(true);
        }
        
        if(JOptionPane.showConfirmDialog(findActive(msg.sender), "Accept file: " + msg.content + " from " + msg.sender + "?") == 0) {
            JFileChooser jf = new JFileChooser();
            jf.setSelectedFile(new File(msg.content));
            int returnVal = jf.showSaveDialog(findActive(msg.sender));
            
            String saveTo = jf.getSelectedFile().getPath();
            if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION) {
                Download dwn = new Download(saveTo, findActive(msg.sender));
                Thread t = new Thread(dwn);
                t.start();
                sendFileReply(msg.sender, ("" + dwn.port));
            }
            else {
                sendFileReply(msg.sender, "NO");
            }
        }
        else {
            sendFileReply(msg.sender, "NO");
        }
    }
    
    public void uploadFile(Message msg) {
        if(!msg.content.equals("NO")) {
            int port = Integer.parseInt(msg.content);
            String addr = msg.sender;
            
            Upload upl = new Upload(addr, port, findActive(fileRecipient).myFile, findActive(fileRecipient));
            Thread t = new Thread(upl);
            t.start();
        }
        else {
            findActive(fileRecipient).insertMessage(fileRecipient + " rejected upload request");
        }
    }
    
    public void sendMessage(String recipient, String content) {
        client.send(new Message("message", client.username, content, recipient));
    }
    
    public void requestMessage() {
        client.send(new Message("unread", client.username, "", "SERVER"));
    }
    
    public void sendFile(String recipient, String content) {
        client.send(new Message("send_req", client.username, content, recipient));
        fileRecipient = recipient;
    }
    
    public void sendFileReply(String recipient, String content) {
        client.send(new Message("send_acc", client.username, content, recipient));
    }
    
    public boolean validateFields(String name, String password) {        
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,18}$");
        Matcher passMatcher = pattern.matcher(password);
        
        Pattern patternName = Pattern.compile("[a-zA-Z\\s']+");
        Matcher nameMatcher = patternName.matcher(name);
        
        if(nameMatcher.matches() && passMatcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void updateInfo() {
        String newName = nameField.getText();
        String newPassword = new String(password.getPassword());
        String retype = new String(retypePassword.getPassword());
         
        if(newPassword.equals(retype)) {
            client.update(newName, newPassword);
            if(validateFields(newName, newPassword)) {
                client.update(newName, newPassword);
            }
            else {
                popWarning("Input tidak valid");
            }
            nameField.setText("");
            password.setText("");
            retypePassword.setText("");
            nameField.requestFocus();
        }
        else {
            password.setText("");
            retypePassword.setText("");
            password.requestFocus();
        }
    }
    
    public void popWarning(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatTab = new javax.swing.JTabbedPane();
        contactTab = new javax.swing.JPanel();
        ScrollPane = new javax.swing.JScrollPane();
        contactTable = new javax.swing.JTable();
        messagesTab = new javax.swing.JPanel();
        settingsTab = new javax.swing.JPanel();
        addContactButton = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        update = new javax.swing.JLabel();
        updateButton = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        retypePassword = new javax.swing.JPasswordField();
        SettingsTabBackground = new javax.swing.JLabel();
        cancelButton = new javax.swing.JLabel();
        closeButton = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 212, 183));
        setMinimumSize(new java.awt.Dimension(500, 700));
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

        chatTab.setBackground(new java.awt.Color(255, 255, 255));
        chatTab.setToolTipText("");
        chatTab.setMinimumSize(new java.awt.Dimension(500, 580));
        chatTab.setName(""); // NOI18N
        chatTab.setPreferredSize(new java.awt.Dimension(500, 580));
        chatTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                chatTabFocusGained(evt);
            }
        });

        contactTab.setBackground(new java.awt.Color(255, 255, 255));
        contactTab.setForeground(new java.awt.Color(255, 204, 204));
        contactTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contactTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        contactTable.setShowHorizontalLines(false);
        contactTable.setShowVerticalLines(false);
        contactTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactTableMouseClicked(evt);
            }
        });
        ScrollPane.setViewportView(contactTable);

        contactTab.add(ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 430, 360));

        chatTab.addTab("", new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/contacts_tab.png")), contactTab, ""); // NOI18N

        messagesTab.setBackground(new java.awt.Color(255, 255, 255));
        messagesTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        chatTab.addTab("", new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/messages_tab.png")), messagesTab); // NOI18N

        settingsTab.setBackground(new java.awt.Color(255, 255, 255));
        settingsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addContactButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_addcontact_default.png"))); // NOI18N
        addContactButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addContactButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addContactButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addContactButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addContactButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addContactButtonMouseReleased(evt);
            }
        });
        settingsTab.add(addContactButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        usernameField.setEditable(false);
        usernameField.setBackground(new java.awt.Color(255, 255, 255));
        usernameField.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        usernameField.setBorder(null);
        settingsTab.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 162, 230, 25));

        nameField.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        nameField.setBorder(null);
        nameField.setMinimumSize(new java.awt.Dimension(230, 25));
        nameField.setPreferredSize(new java.awt.Dimension(230, 25));
        settingsTab.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 226, 230, 25));

        update.setFont(new java.awt.Font("Century Gothic", 1, 19)); // NOI18N
        update.setForeground(new java.awt.Color(85, 0, 0));
        update.setText("Update :");
        settingsTab.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 90, -1, -1));

        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_update_default.png"))); // NOI18N
        updateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                updateButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                updateButtonMouseReleased(evt);
            }
        });
        settingsTab.add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, -1, -1));

        password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        password.setBorder(null);
        password.setMinimumSize(new java.awt.Dimension(230, 25));
        password.setPreferredSize(new java.awt.Dimension(230, 25));
        settingsTab.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, -1, -1));

        retypePassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        retypePassword.setBorder(null);
        retypePassword.setMinimumSize(new java.awt.Dimension(230, 25));
        retypePassword.setPreferredSize(new java.awt.Dimension(230, 25));
        settingsTab.add(retypePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 354, -1, -1));

        SettingsTabBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/SettingsTabUI.png"))); // NOI18N
        settingsTab.add(SettingsTabBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_cancel_default.png"))); // NOI18N
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelButtonMouseReleased(evt);
            }
        });
        settingsTab.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, -1, -1));

        chatTab.addTab("", new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/settings_tab.png")), settingsTab); // NOI18N

        getContentPane().add(chatTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 500, 580));

        closeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_default.png"))); // NOI18N
        closeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeButtonMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButtonMouseReleased(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 3, 20, 20));

        minimizeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_default.png"))); // NOI18N
        minimizeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                minimizeButtonMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseReleased(evt);
            }
        });
        getContentPane().add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 3, 20, 20));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/MainUI.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 700));

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

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_hover.png"));
        closeButton.setIcon(closeImage);
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMousePressed
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_pressed.png"));
        closeButton.setIcon(closeImage);
    }//GEN-LAST:event_closeButtonMousePressed

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_default.png"));
        closeButton.setIcon(closeImage);
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseReleased
        ImageIcon closeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/close_default.png"));
        closeButton.setIcon(closeImage);

        System.exit(0);
    }//GEN-LAST:event_closeButtonMouseReleased

    private void minimizeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseEntered
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_hover.png"));
        minimizeButton.setIcon(minimizeImage);
    }//GEN-LAST:event_minimizeButtonMouseEntered

    private void minimizeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMousePressed
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_pressed.png"));
        minimizeButton.setIcon(minimizeImage);
    }//GEN-LAST:event_minimizeButtonMousePressed

    private void minimizeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseExited
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_default.png"));
        minimizeButton.setIcon(minimizeImage);
    }//GEN-LAST:event_minimizeButtonMouseExited

    private void minimizeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseReleased
        ImageIcon minimizeImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/minimize_default.png"));
        minimizeButton.setIcon(minimizeImage);

        setExtendedState(getExtendedState()| LoginUI.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseReleased

    private void contactTableMouseClicked(java.awt.event.MouseEvent evt) {                                          
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();
        
        String partner = (String) contactTable.getValueAt(row, 0);
        
        if(findActive(partner) != null) {
            findActive(partner).setVisible(true);
        }
        else {
            ChatUI chatUI = new ChatUI();
            chatUI.setMainUI(this);
            chatUI.setPartner(partner);
            addActive(chatUI);
            chatUI.setVisible(true);
        }
    }                                             


    private void addContactButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addContactButtonMouseEntered
        ImageIcon addContactImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_addcontact_hover.png"));
        addContactButton.setIcon(addContactImage);
    }//GEN-LAST:event_addContactButtonMouseEntered

    private void addContactButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addContactButtonMouseExited
        ImageIcon addContactImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_addcontact_default.png"));
        addContactButton.setIcon(addContactImage);
    }//GEN-LAST:event_addContactButtonMouseExited

    private void addContactButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addContactButtonMousePressed
        ImageIcon addContactImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_addcontact_pressed.png"));
        addContactButton.setIcon(addContactImage);
    }//GEN-LAST:event_addContactButtonMousePressed

    private void addContactButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addContactButtonMouseReleased
        ImageIcon addContactImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_addcontact_default.png"));
        addContactButton.setIcon(addContactImage);
        
        AddContactUI contactUI = new AddContactUI();
        contactUI.setClient(client);
        contactUI.setMainUI(this);
        contactUI.setVisible(true);
    }//GEN-LAST:event_addContactButtonMouseReleased

    private void updateButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseEntered
        ImageIcon updateImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_update_hover.png"));
        updateButton.setIcon(updateImage);
    }//GEN-LAST:event_updateButtonMouseEntered

    private void updateButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseExited
        ImageIcon updateImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_update_default.png"));
        updateButton.setIcon(updateImage);
    }//GEN-LAST:event_updateButtonMouseExited

    private void updateButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMousePressed
        ImageIcon updateImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_update_pressed.png"));
        updateButton.setIcon(updateImage);
    }//GEN-LAST:event_updateButtonMousePressed

    private void updateButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseReleased
        ImageIcon updateImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_update_default.png"));
        updateButton.setIcon(updateImage);
        
        this.updateInfo();
    }//GEN-LAST:event_updateButtonMouseReleased

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        ImageIcon cancelImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_cancel_hover.png"));
        cancelButton.setIcon(cancelImage);
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        ImageIcon cancelImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_cancel_default.png"));
        cancelButton.setIcon(cancelImage);
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMousePressed
        ImageIcon cancelImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_cancel_pressed.png"));
        cancelButton.setIcon(cancelImage);
    }//GEN-LAST:event_cancelButtonMousePressed

    private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseReleased
        ImageIcon cancelImage = new ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_cancel_default.png"));
        cancelButton.setIcon(cancelImage);
        
        nameField.setText("");
        password.setText("");
        retypePassword.setText("");
    }//GEN-LAST:event_cancelButtonMouseReleased

    private void chatTabFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chatTabFocusGained
        requestMessage();
    }//GEN-LAST:event_chatTabFocusGained

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        contactTab.add(new javax.swing.JLabel("Avatar"));
        contactTab.add(new javax.swing.JLabel("Contact"));
        contactTab.add(new javax.swing.JLabel("Status"));
        
        /*ChatUI chatUI = new ChatUI();
        chatUI.setMainUI(this);
        chatUI.setPartner(partner);
        addActive(chatUI);
        chatUI.show();*/
    }


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
                } else {
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaintUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JLabel SettingsTabBackground;
    private javax.swing.JLabel addContactButton;
    private javax.swing.JLabel background;
    private javax.swing.JLabel cancelButton;
    private javax.swing.JTabbedPane chatTab;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel contactTab;
    private javax.swing.JTable contactTable;
    private javax.swing.JPanel messagesTab;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JTextField nameField;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField retypePassword;
    private javax.swing.JPanel settingsTab;
    private javax.swing.JLabel update;
    private javax.swing.JLabel updateButton;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
