package Base;

import static Base.login.id_admin;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author donih
 */
public class loginRFID extends javax.swing.JFrame {

    public String getIdAdmin() {
        return id_admin;
    }
    
    public loginRFID() {
        initComponents();
        setFocusToTextField();  // Memfokuskan kursor ke dalam TextField jTextField1
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cekLogin();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                cekLogin();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                cekLogin();
            }
        });
    }
    private void setFocusToTextField() {
        jTextField1.requestFocusInWindow();
    }
    private void cekLogin() {
        String rfid = jTextField1.getText();
        try {
            String sql = "SELECT id_admin FROM admin WHERE kode_rfid = ? ";
            //String sql = "SELECT id_admin FROM admin WHERE kode_rfid = '" + rfid + "' OR nik = '" + rfid + "'";
            Connection c = konek.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, rfid);
            ResultSet r = ps.executeQuery();
            if (r.next()) {
                // Jika rfid sesuai, buka frame dashboard
                id_admin = r.getString("id_admin");
                System.out.println(id_admin);
                dashboard dashboardFrame = new dashboard();
                dashboardFrame.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(null, "Login Berhasil");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_x = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_x.setBorder(null);
        btn_x.setContentAreaFilled(false);
        btn_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xActionPerformed(evt);
            }
        });
        getContentPane().add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 20, 30, 40));

        btn_kembali.setContentAreaFilled(false);
        btn_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 620, 112, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/scan kartu.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 290, 60));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.dispose();
        login kembali = new login();
        kembali.setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(loginRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginRFID().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_x;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
