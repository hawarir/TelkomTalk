/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telkomtalk.UI.pkg;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import telkomtalk.client.Client;
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
    /**
     * Creates new form ChatUI
     */
    public MaintUI() {
        initComponents();
        activeChat = new ArrayList<>();
    }
    
    public void setClient(Client _client) {
        this.client = _client;
        client.getContacts();
    }
    
    public void addContact(String username, String name) {
        model = (DefaultTableModel) contactTable.getModel();
        model.addRow(new Object[]{username, name});
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
            findActive(msg.sender).insertMessage(msg.content);
        }
        else {
            ChatUI chatUI = new ChatUI();
            chatUI.setMainUI(this);
            chatUI.setPartner(msg.sender);
            addActive(chatUI);
            chatUI.show();
            chatUI.insertMessage(msg.content);
        }
        
        /*for(int i = 0; i < activeChat.size(); i++) {
            if(activeChat.get(i).getPartner().equals(msg.sender)) {
                activeChat.get(i).insertMessage(msg.content);
                return;
            }
        }
        
        ChatUI chatUI = new ChatUI();
        chatUI.setMainUI(this);
        chatUI.setPartner(msg.sender);
        addActive(chatUI);
        chatUI.show();
        chatUI.insertMessage(msg.content);*/
    }
    
    public void sendMessage(String recipient, String content) {
        client.send(new Message("message", client.username, content, recipient));
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
        messageTab = new javax.swing.JTabbedPane();
        SettingsTab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        addContactButton = new javax.swing.JLabel();
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        chatTab.addTab("", new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/messages_tab.png")), jPanel2); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addContactButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/button_addcontact_default.png"))); // NOI18N
        addContactButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addContactButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addContactButtonMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addContactButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addContactButtonMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addContactButtonMouseReleased(evt);
            }
        });
        jPanel1.add(addContactButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        chatTab.addTab("", new javax.swing.ImageIcon(getClass().getResource("/telkomtalk/UI/images/settings_tab.png")), jPanel1); // NOI18N

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
        
        ChatUI chatUI = new ChatUI();
        chatUI.setMainUI(this);
        chatUI.setPartner(partner);
        addActive(chatUI);
        chatUI.show();
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
    }//GEN-LAST:event_addContactButtonMouseReleased

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
    private javax.swing.JLabel addContactButton;
    private javax.swing.JTabbedPane SettingsTab;
    private javax.swing.JLabel background;
    private javax.swing.JTabbedPane chatTab;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel contactTab;
    private javax.swing.JTable contactTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane messageTab;
    private javax.swing.JLabel minimizeButton;
    // End of variables declaration//GEN-END:variables
}
