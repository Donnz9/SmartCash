/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author donih
 */
public class lupa_password extends javax.swing.JFrame {

    public lupa_password() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nik = new javax.swing.JTextField();
        txt_konfirmasiPassword = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();
        txt_passwordBaru = new javax.swing.JPasswordField();
        btn_simpan = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        btn_eye = new javax.swing.JButton();
        btn_eye1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nik.setBackground(new java.awt.Color(255, 255, 255));
        txt_nik.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_nik.setForeground(new java.awt.Color(0, 0, 0));
        txt_nik.setBorder(null);
        txt_nik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nikActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 439, 350, 44));

        txt_konfirmasiPassword.setBackground(new java.awt.Color(255, 255, 255));
        txt_konfirmasiPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_konfirmasiPassword.setForeground(new java.awt.Color(0, 0, 0));
        txt_konfirmasiPassword.setBorder(null);
        getContentPane().add(txt_konfirmasiPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 439, 300, 44));

        txt_username.setBackground(new java.awt.Color(255, 255, 255));
        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setForeground(new java.awt.Color(0, 0, 0));
        txt_username.setBorder(null);
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 352, 350, 44));

        txt_passwordBaru.setBackground(new java.awt.Color(255, 255, 255));
        txt_passwordBaru.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_passwordBaru.setForeground(new java.awt.Color(0, 0, 0));
        txt_passwordBaru.setBorder(null);
        getContentPane().add(txt_passwordBaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 352, 300, 44));

        btn_simpan.setContentAreaFilled(false);
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 525, 112, 30));

        btn_kembali.setContentAreaFilled(false);
        btn_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 620, 112, 30));

        btn_x.setBorder(null);
        btn_x.setContentAreaFilled(false);
        btn_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xActionPerformed(evt);
            }
        });
        getContentPane().add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 20, 30, 40));

        btn_eye.setBackground(new java.awt.Color(255, 255, 255));
        btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutup.png"))); // NOI18N
        btn_eye.setContentAreaFilled(false);
        btn_eye.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eyeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_eye, new org.netbeans.lib.awtextra.AbsoluteConstraints(995, 357, -1, -1));

        btn_eye1.setBackground(new java.awt.Color(255, 255, 255));
        btn_eye1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutup.png"))); // NOI18N
        btn_eye1.setContentAreaFilled(false);
        btn_eye1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eye1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eye1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_eye1, new org.netbeans.lib.awtextra.AbsoluteConstraints(995, 442, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Lupa password.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.dispose();
        login kembali = new login();
        kembali.setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void btn_eyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eyeActionPerformed
        if (txt_passwordBaru.getEchoChar() == 0) {
            txt_passwordBaru.setEchoChar('*');
            btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutup.png")));
        } else if (txt_passwordBaru.getEchoChar() == '*') {
            txt_passwordBaru.setEchoChar((char) 0);
            btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buka.png")));
        }
    }//GEN-LAST:event_btn_eyeActionPerformed

    private void btn_eye1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eye1ActionPerformed
        if (txt_konfirmasiPassword.getEchoChar() == 0) {
            txt_konfirmasiPassword.setEchoChar('*');
            btn_eye1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutup.png")));
        } else if (txt_konfirmasiPassword.getEchoChar() == '*') {
            txt_konfirmasiPassword.setEchoChar((char) 0);
            btn_eye1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buka.png")));
        }
    }//GEN-LAST:event_btn_eye1ActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String username = txt_username.getText();
        String nik = txt_nik.getText();
        String passwordBaru = new String(txt_passwordBaru.getPassword());
        String konfirmasiPassword = new String(txt_konfirmasiPassword.getPassword());
        if (username.isEmpty() || nik.isEmpty() || passwordBaru.isEmpty() || konfirmasiPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inputan harus terisi semua!");
            return;
        }
        try { // melakukan pengecekan dan pembaruan password di database
            // cek panjang passwordBaru
            if (passwordBaru.length() < 8 || passwordBaru.length() > 12) {
                JOptionPane.showMessageDialog(null, "Password harus minimal 8 - 12 karakter");
                return; // keluar dari method jika panjang password tidak sesuai
            }
            // cek kesesuaian passwordBaru dan konfirmasiPassword
            if (!passwordBaru.equals(konfirmasiPassword)) {
                JOptionPane.showMessageDialog(null, "Konfirmasi password tidak cocok dengan password baru");
                return;
            }
            PasswordManager pwManager = new PasswordManager();
            String pwEncrypted = pwManager.hashPassword(passwordBaru);
            // melaakukan pengecekan nama dan nik di database
            Statement statement = (Statement) konek.getConnection().createStatement();
            String queryCheck = "SELECT * FROM admin WHERE nama = '" + username + "' AND nik = '" + nik + "'";
            ResultSet result = statement.executeQuery(queryCheck);
            
            // Jika nama dan nik tidak sesuai
            if (!result.next()) {
                JOptionPane.showMessageDialog(null, "Username / NIK tidak ada");
                return; // keluar dari method jika nama atau NIK tidak sesuai
            }
            // Jika nama dan nik sesuai maka akan update password
            String queryUpdate = "UPDATE admin SET sandi = '" + pwEncrypted + "' WHERE nama = '" + username + "' AND nik = '" + nik + "'";
            statement.executeUpdate(queryUpdate);
            JOptionPane.showMessageDialog(null, "Password berhasil diubah");
            // Reset inputan
            txt_username.setText("");
            txt_nik.setText("");
            txt_passwordBaru.setText("");
            txt_konfirmasiPassword.setText("");
            statement.close();
            this.dispose();
            login login = new login();
            login.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah password");
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_nikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nikActionPerformed
    }//GEN-LAST:event_txt_nikActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(lupa_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lupa_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lupa_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lupa_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lupa_password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eye;
    private javax.swing.JButton btn_eye1;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_x;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txt_konfirmasiPassword;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JPasswordField txt_passwordBaru;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
