/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author donih
 */
public class ganti_password extends javax.swing.JFrame {
    private String id_admin;
    login objek = new login();
    private static final Logger logger = Logger.getLogger(profil.class.getName());

    public ganti_password() {
        initComponents();
        JID.setText(login.id_admin);
        System.out.println(login.id_admin);
        DataProfil();
    }

    public void DataProfil() {
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "select * from admin where id_admin = '" + JID.getText() + "';";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                JUsername.setText(res.getString("nama"));
                JPosisi.setText(res.getString("bagian"));
                txt_usernameprofil.setText(res.getString("nama"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            e.printStackTrace();
        }
    }

    private void batal() {
        txt_konfirmasi_password.setText(null);
        txt_password_baru.setText(null);
        txt_password_sekarang.setText(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_dashboard = new javax.swing.JButton();
        btn_produk = new javax.swing.JButton();
        btn_supplier = new javax.swing.JButton();
        btn_transaksi = new javax.swing.JButton();
        btn_member = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        JPosisi = new javax.swing.JLabel();
        JUsername = new javax.swing.JLabel();
        JID = new javax.swing.JLabel();
        txt_usernameprofil = new javax.swing.JTextField();
        btn_x = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        txt_password_sekarang = new javax.swing.JPasswordField();
        txt_password_baru = new javax.swing.JPasswordField();
        txt_konfirmasi_password = new javax.swing.JPasswordField();
        btn_simpan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_dashboard.setBorder(null);
        btn_dashboard.setContentAreaFilled(false);
        btn_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 265, 200, 40));

        btn_produk.setBorder(null);
        btn_produk.setContentAreaFilled(false);
        btn_produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_produkActionPerformed(evt);
            }
        });
        getContentPane().add(btn_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 345, 200, 40));

        btn_supplier.setBorder(null);
        btn_supplier.setContentAreaFilled(false);
        btn_supplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supplierActionPerformed(evt);
            }
        });
        getContentPane().add(btn_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 200, 40));

        btn_transaksi.setBorder(null);
        btn_transaksi.setContentAreaFilled(false);
        btn_transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 476, 200, 40));

        btn_member.setBorder(null);
        btn_member.setContentAreaFilled(false);
        btn_member.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_memberActionPerformed(evt);
            }
        });
        getContentPane().add(btn_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 200, 40));

        btn_logout.setBorder(null);
        btn_logout.setContentAreaFilled(false);
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });
        getContentPane().add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 70, 25));

        btn_kembali.setBorder(null);
        btn_kembali.setContentAreaFilled(false);
        btn_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 648, 110, 29));

        JPosisi.setBackground(new java.awt.Color(255, 255, 255));
        JPosisi.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        JPosisi.setForeground(new java.awt.Color(0, 0, 0));
        JPosisi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JPosisi.setText("jLabel2");
        getContentPane().add(JPosisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 460, 160, 30));

        JUsername.setBackground(new java.awt.Color(255, 255, 255));
        JUsername.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        JUsername.setForeground(new java.awt.Color(0, 0, 0));
        JUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JUsername.setText("jLabel2");
        getContentPane().add(JUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 425, 160, 30));

        JID.setBackground(new java.awt.Color(255, 255, 255));
        JID.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        JID.setForeground(new java.awt.Color(0, 0, 0));
        JID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JID.setText("jLabel2");
        getContentPane().add(JID, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 390, 160, 30));

        txt_usernameprofil.setEditable(false);
        txt_usernameprofil.setBackground(new java.awt.Color(255, 255, 255));
        txt_usernameprofil.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_usernameprofil.setForeground(new java.awt.Color(0, 0, 0));
        txt_usernameprofil.setBorder(null);
        getContentPane().add(txt_usernameprofil, new org.netbeans.lib.awtextra.AbsoluteConstraints(932, 329, 210, 28));

        btn_x.setBorder(null);
        btn_x.setContentAreaFilled(false);
        btn_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xActionPerformed(evt);
            }
        });
        getContentPane().add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 20, 30, 40));

        btn_batal.setBorder(null);
        btn_batal.setContentAreaFilled(false);
        btn_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 567, 152, 29));

        txt_password_sekarang.setBackground(new java.awt.Color(255, 255, 255));
        txt_password_sekarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_password_sekarang.setForeground(new java.awt.Color(0, 0, 0));
        txt_password_sekarang.setBorder(null);
        getContentPane().add(txt_password_sekarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(932, 374, 210, 28));

        txt_password_baru.setBackground(new java.awt.Color(255, 255, 255));
        txt_password_baru.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_password_baru.setForeground(new java.awt.Color(0, 0, 0));
        txt_password_baru.setBorder(null);
        getContentPane().add(txt_password_baru, new org.netbeans.lib.awtextra.AbsoluteConstraints(932, 418, 210, 28));

        txt_konfirmasi_password.setBackground(new java.awt.Color(255, 255, 255));
        txt_konfirmasi_password.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_konfirmasi_password.setForeground(new java.awt.Color(0, 0, 0));
        txt_konfirmasi_password.setBorder(null);
        getContentPane().add(txt_konfirmasi_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(932, 463, 210, 28));

        btn_simpan.setBorder(null);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 567, 152, 29));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/ganti password.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dashboardActionPerformed
        this.dispose();
        dashboard dashboard = new dashboard();
        dashboard.setVisible(true);
    }//GEN-LAST:event_btn_dashboardActionPerformed

    private void btn_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_produkActionPerformed
        this.dispose();
        produk prd = new produk();
        prd.setVisible(true);
    }//GEN-LAST:event_btn_produkActionPerformed

    private void btn_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supplierActionPerformed
        this.dispose();
        supplier s = new supplier();
        s.setVisible(true);
    }//GEN-LAST:event_btn_supplierActionPerformed

    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        this.dispose();
        transaksi s = new transaksi();
        s.setVisible(true);
    }//GEN-LAST:event_btn_transaksiActionPerformed

    private void btn_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_memberActionPerformed
        this.dispose();
        member prd = new member();
        prd.setVisible(true);
    }//GEN-LAST:event_btn_memberActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        int konfirmasi = JOptionPane.showOptionDialog(this,
                "Apakah anda yakin akan keluar ?",
                "Log Out",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            this.dispose();
            login log = new login();
            log.setVisible(true);
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.dispose();
        profil dashboard = new profil(id_admin);
        dashboard.setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        batal();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String id = JID.getText();
        String nama = txt_usernameprofil.getText();
        String password_lama = txt_password_sekarang.getText();
        String password_baru = txt_password_baru.getText();
        String konfirmasi_passsword = txt_konfirmasi_password.getText();
        PasswordManager pwManager = new PasswordManager();
        String pwEncryptedSandiLama = pwManager.hashPassword(password_lama);
        try {
            if (password_baru.isEmpty() == true || password_lama.isEmpty() == true || konfirmasi_passsword.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Inputan harus terisi semua!");
                return;
            }
            // Cek panjang password baru
            if (password_baru.length() < 8 || password_baru.length() > 12) {
                JOptionPane.showMessageDialog(null, "Password harus minimal 8 - 12 karakter");
                return;
            }
            // Cek kesesuaian password baru dan konfirmasi password
            if (!password_baru.equals(konfirmasi_passsword)) {
                JOptionPane.showMessageDialog(null, "Konfirmasi password tidak cocok dengan password baru");
                return;
            }
            // Mengecek keberadaan sandi lama di database
            Statement statement = (Statement) konek.getConnection().createStatement();
            String queryCheck = "SELECT * FROM admin WHERE nama = '" + nama + "' AND sandi = '" + pwEncryptedSandiLama + "'";
            ResultSet result = statement.executeQuery(queryCheck);
            if (!result.next()) {
                JOptionPane.showMessageDialog(null, "Sandi lama salah");
                statement.close();
                return; 
            }
            int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan mengedit sandi anda?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String pwEncryptedSandiBaru = pwManager.hashPassword(password_baru);
                String query = "UPDATE admin SET sandi = '" + pwEncryptedSandiBaru + "' WHERE nama = '" + nama + "' AND sandi = '" + pwEncryptedSandiLama + "'";
                statement.executeUpdate(query);
                statement.close();
                JOptionPane.showMessageDialog(null, "Data anda berhasil diedit");
                batal();
                DataProfil();
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Data anda gagal diedit");
            t.printStackTrace();
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

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
            java.util.logging.Logger.getLogger(ganti_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ganti_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ganti_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ganti_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ganti_password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JID;
    private javax.swing.JLabel JPosisi;
    private javax.swing.JLabel JUsername;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_member;
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_supplier;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JButton btn_x;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txt_konfirmasi_password;
    private javax.swing.JPasswordField txt_password_baru;
    private javax.swing.JPasswordField txt_password_sekarang;
    private javax.swing.JTextField txt_usernameprofil;
    // End of variables declaration//GEN-END:variables
}
