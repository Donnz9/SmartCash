/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class register extends javax.swing.JFrame {

    public register() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_username = new javax.swing.JTextField();
        txt_nik = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        btn_daftar = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        txt_alamat = new javax.swing.JTextField();
        txt_jenisKelamin = new javax.swing.JComboBox<>();
        btn_x = new javax.swing.JButton();
        btn_eye = new javax.swing.JButton();
        txt_koderfid = new javax.swing.JTextField();
        txt_bagian = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_username.setBackground(new java.awt.Color(255, 255, 255));
        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setForeground(new java.awt.Color(0, 0, 0));
        txt_username.setBorder(null);
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 289, 350, 44));

        txt_nik.setBackground(new java.awt.Color(255, 255, 255));
        txt_nik.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_nik.setForeground(new java.awt.Color(0, 0, 0));
        txt_nik.setBorder(null);
        getContentPane().add(txt_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 376, 350, 44));

        txt_password.setBackground(new java.awt.Color(255, 255, 255));
        txt_password.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_password.setForeground(new java.awt.Color(0, 0, 0));
        txt_password.setBorder(null);
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 461, 310, 44));

        btn_daftar.setContentAreaFilled(false);
        btn_daftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 620, 112, 30));

        btn_kembali.setContentAreaFilled(false);
        btn_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 620, 112, 30));

        txt_alamat.setBackground(new java.awt.Color(255, 255, 255));
        txt_alamat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_alamat.setForeground(new java.awt.Color(0, 0, 0));
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 376, 350, 44));

        txt_jenisKelamin.setBackground(new java.awt.Color(255, 255, 255));
        txt_jenisKelamin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_jenisKelamin.setForeground(new java.awt.Color(0, 0, 0));
        txt_jenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        txt_jenisKelamin.setBorder(null);
        txt_jenisKelamin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_jenisKelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jenisKelaminActionPerformed(evt);
            }
        });
        getContentPane().add(txt_jenisKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 461, 350, 44));

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
        getContentPane().add(btn_eye, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 467, -1, -1));

        txt_koderfid.setBackground(new java.awt.Color(255, 255, 255));
        txt_koderfid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_koderfid.setForeground(new java.awt.Color(0, 0, 0));
        txt_koderfid.setBorder(null);
        txt_koderfid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_koderfidActionPerformed(evt);
            }
        });
        getContentPane().add(txt_koderfid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 548, 350, 44));

        txt_bagian.setBackground(new java.awt.Color(255, 255, 255));
        txt_bagian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_bagian.setForeground(new java.awt.Color(0, 0, 0));
        txt_bagian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Owner", "Karyawan" }));
        txt_bagian.setBorder(null);
        txt_bagian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_bagian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bagianActionPerformed(evt);
            }
        });
        getContentPane().add(txt_bagian, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 289, 350, 44));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Register_1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.dispose();
        login kembali = new login();
        kembali.setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void txt_jenisKelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jenisKelaminActionPerformed
    }//GEN-LAST:event_txt_jenisKelaminActionPerformed

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed
        String username = txt_username.getText();
        String nik = txt_nik.getText();
        Object jeniskelamin = txt_jenisKelamin.getSelectedItem();
        Object bagian = txt_bagian.getSelectedItem();
        String alamat = txt_alamat.getText();
        String password = txt_password.getText();
        String rfid = txt_koderfid.getText();
        PasswordManager pwManager = new PasswordManager();
        try {
            if (username.isEmpty() || nik.isEmpty() || alamat.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua input harus diisi!");
            } else if (nik.length() != 16) {
                JOptionPane.showMessageDialog(null, "Panjang NIK harus 16 karakter!");
            } else if (password.length() < 4 || password.length() > 12) {
                JOptionPane.showMessageDialog(null, "Panjang password harus antara 4 dan 12 karakter!");
            } else {
                Statement statementCheck = konek.getConnection().createStatement();
                ResultSet resultSetCheck = statementCheck.executeQuery("SELECT * FROM admin WHERE kode_rfid = '" + rfid + "'");
                if (resultSetCheck.next()) {
                    JOptionPane.showMessageDialog(null, "Kode RFID sudah terdaftar!");
                } else {
                    int konfirmasi = JOptionPane.showOptionDialog(this,
                            "Apakah Anda yakin akan mendaftarkan diri Anda?", "Daftar",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        // Mendapatkan ID auto increment terbaru dari tabel
                        int lastId = 0;
                        Statement statementId = konek.getConnection().createStatement();
                        ResultSet resultSetId = statementId.executeQuery("SELECT MAX(id_admin) FROM admin");
                        if (resultSetId.next()) {
                            String lastIdString = resultSetId.getString(1);
                            // Jika tidak ada data di dalam tabel, set lastId menjadi 0
                            if (lastIdString != null) {
                                lastId = Integer.parseInt(lastIdString.substring(1)); // Mengambil angka dari ID terakhir
                            }
                        }
                        String newId = "A" + String.format("%03d", lastId + 1);
                        String pwEncrypted = pwManager.hashPassword(password); // Enkripsi password
                        // Simpan data ke dalam tabel
                        Statement statement = konek.getConnection().createStatement();
                        String query = "INSERT INTO admin (id_admin, nama, sandi, jenis_kelamin, bagian, alamat, nik, kode_rfid) "
                                + "VALUES ('" + newId + "','" + username + "','" + pwEncrypted + "','" + jeniskelamin + "',"
                                + "'" + bagian + "','" + alamat + "','" + nik + "','" + rfid + "')";
                        statement.executeUpdate(query);
                        statement.close();
                        JOptionPane.showMessageDialog(null, "Akun Anda berhasil terdaftar dengan ID " + newId);
                        // Menutup frame saat ini dan menampilkan frame login
                        this.dispose();
                        login login = new login();
                        login.setVisible(true);
                    }
                }
                resultSetCheck.close();
                statementCheck.close();
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Gagal terdaftar");
            t.printStackTrace();
        }
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void btn_eyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eyeActionPerformed
        if (txt_password.getEchoChar() == 0) {
            txt_password.setEchoChar('*');
            btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutup.png")));
        } else if (txt_password.getEchoChar() == '*') {
            txt_password.setEchoChar((char) 0);
            btn_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buka.png")));
        }
    }//GEN-LAST:event_btn_eyeActionPerformed

    private void txt_koderfidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_koderfidActionPerformed
    }//GEN-LAST:event_txt_koderfidActionPerformed

    private void txt_bagianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bagianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bagianActionPerformed

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
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_daftar;
    private javax.swing.JButton btn_eye;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_x;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JComboBox<String> txt_bagian;
    private javax.swing.JComboBox<String> txt_jenisKelamin;
    private javax.swing.JTextField txt_koderfid;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
