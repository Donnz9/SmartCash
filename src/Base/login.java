/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.lang.String;

/**
 *
 * @author donih
 */
public class login extends javax.swing.JFrame {
    public static String id_admin;
    
    public login() {
        initComponents();
    }
    
    public String getIdAdmin() {
        return id_admin;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        btn_daftar = new javax.swing.JButton();
        btn_lupaPassword = new javax.swing.JButton();
        btn_scan = new javax.swing.JButton();
        btn_eye = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_username.setBackground(new java.awt.Color(255, 255, 255));
        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setForeground(new java.awt.Color(0, 0, 0));
        txt_username.setBorder(null);
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 382, 405, 44));

        txt_password.setBackground(new java.awt.Color(255, 255, 255));
        txt_password.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_password.setForeground(new java.awt.Color(0, 0, 0));
        txt_password.setBorder(null);
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 468, 340, 44));

        btn_login.setContentAreaFilled(false);
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 537, 112, 30));

        btn_daftar.setContentAreaFilled(false);
        btn_daftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 622, 112, 30));

        btn_lupaPassword.setContentAreaFilled(false);
        btn_lupaPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_lupaPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lupaPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(btn_lupaPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 625, 114, 25));

        btn_scan.setContentAreaFilled(false);
        btn_scan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_scan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_scan, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 537, 112, 30));

        btn_eye.setBackground(new java.awt.Color(255, 255, 255));
        btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutup.png"))); // NOI18N
        btn_eye.setContentAreaFilled(false);
        btn_eye.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eyeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_eye, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 470, -1, -1));

        btn_x.setBorder(null);
        btn_x.setContentAreaFilled(false);
        btn_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xActionPerformed(evt);
            }
        });
        getContentPane().add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 20, 30, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Login.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lupaPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lupaPasswordActionPerformed
        this.dispose();
        lupa_password lupa = new lupa_password();
        lupa.setVisible(true);
    }//GEN-LAST:event_btn_lupaPasswordActionPerformed

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed
        this.dispose();
        register daftar = new register();
        daftar.setVisible(true);
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void btn_scanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scanActionPerformed
        this.dispose();
        loginRFID scan = new loginRFID();
        scan.setVisible(true);
    }//GEN-LAST:event_btn_scanActionPerformed

    private void btn_eyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eyeActionPerformed
        if (txt_password.getEchoChar() == 0){
            txt_password.setEchoChar('*');
            btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutup.png")));
        } 
        else if (txt_password.getEchoChar() == '*'){
            txt_password.setEchoChar((char)0);
            btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buka.png")));
        }
    }//GEN-LAST:event_btn_eyeActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        
        try {
            PasswordManager pwManager = new PasswordManager();
            String pwEncrypted = pwManager.hashPassword(txt_password.getText().toString());
            System.out.println("password encrypt " + pwEncrypted);
            
            String login = "SELECT id_admin FROM admin WHERE nama = '" + txt_username.getText().toString() + "'" + ""
                    + "AND sandi = '" + pwEncrypted + "';";
            java.sql.Connection conn = (Connection) konek.getConnection();
            java.sql.PreparedStatement prp = conn.prepareStatement(login);
            java.sql.ResultSet rsAd = prp.executeQuery(login);
            if (rsAd.next()) {
                // Jika query berhasil
                id_admin = rsAd.getString("id_admin");
                System.out.println(id_admin);
                dashboard dash = new dashboard();
                dash.setVisible(true);
                JOptionPane.showMessageDialog(null, "Login Berhasil");
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Login Gagal Harap Cek Username Dan Password Anda");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }  
    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_daftar;
    private javax.swing.JButton btn_eye;
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_lupaPassword;
    private javax.swing.JButton btn_scan;
    private javax.swing.JButton btn_x;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
