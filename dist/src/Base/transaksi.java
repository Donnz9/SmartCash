/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author donih
 */
public class transaksi extends javax.swing.JFrame {
    private DefaultTableModel tblJual;
    private DefaultTableModel tblBeli;

    public transaksi() {
        initComponents();
        tablejual.getModel();
        datatablejual();
        tablebeli.getModel();
        datatablebeli();
    }

    public void datatablejual() {
        tblJual = new DefaultTableModel();
        tblJual.addColumn("ID");
        tblJual.addColumn("Tanggal");
        tblJual.addColumn("Total");
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "select * from penjualan;";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                tblJual.addRow(new Object[]{
                    res.getString("id_penjualan"),
                    res.getString("tgl_penjualan"),
                    res.getString("total"),});
            }
            tablejual.setModel(tblJual);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            e.printStackTrace();
        }
    }

    public void datatablebeli() {
        tblBeli = new DefaultTableModel();
        tblBeli.addColumn("ID");
        tblBeli.addColumn("Tanggal");
        tblBeli.addColumn("Total");
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "select * from pembelian;";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                tblBeli.addRow(new Object[]{
                    res.getString("id_pembelian"),
                    res.getString("tgl_pembelian"),
                    res.getString("total"),});
            }
            tablebeli.setModel(tblBeli);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            e.printStackTrace();
        }
    }

    public void bubbleSortJual(DefaultTableModel tbl, int indexSelected) {
        int rowCount = tbl.getRowCount();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Format tanggal dengan waktu
        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < rowCount - i - 1; j++) {
                // Mengambil nilai tanggal/total dari dua baris yang akan dibandingkan
                String value1 = (String) tbl.getValueAt(j, indexSelected);
                String value2 = (String) tbl.getValueAt(j + 1, indexSelected);
                try {
                    // Parsing tanggal ke dalam objek Date jika indeks yang dipilih adalah 1 (Tanggal)
                    if (indexSelected == 1) {
                        Date date1 = dateFormat.parse(value1);
                        Date date2 = dateFormat.parse(value2);
                        // Membandingkan tanggal
                        int compareResult = date1.compareTo(date2);
                        // Jika "Terbaru" dipilih, urutkan dari yang terbaru
                        if (compareResult < 0) {
                            for (int k = 0; k < tbl.getColumnCount(); k++) {
                                Object temp = tbl.getValueAt(j, k);
                                tbl.setValueAt(tbl.getValueAt(j + 1, k), j, k);
                                tbl.setValueAt(temp, j + 1, k);
                            }
                        }
                    } else if (indexSelected == 2) { // Jika indeks yang dipilih adalah Total
                        Double doubleValue1 = Double.parseDouble(value1);
                        Double doubleValue2 = Double.parseDouble(value2);
                        // Membandingkan nilai total
                        int compareResult = doubleValue1.compareTo(doubleValue2);
                        // Jika "Total" dipilih, urutkan berdasarkan nilai total
                        if (compareResult > 0) {
                            for (int k = 0; k < tbl.getColumnCount(); k++) {
                                Object temp = tbl.getValueAt(j, k);
                                tbl.setValueAt(tbl.getValueAt(j + 1, k), j, k);
                                tbl.setValueAt(temp, j + 1, k);
                            }
                        }
                    }
                } catch (ParseException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void bubbleSortBeli(DefaultTableModel tbl, int indexSelected) {
        int rowCount = tbl.getRowCount();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Format tanggal dengan waktu
        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < rowCount - i - 1; j++) {
                // Mengambil nilai tanggal/total dari dua baris yang akan dibandingkan
                String value1 = (String) tbl.getValueAt(j, indexSelected);
                String value2 = (String) tbl.getValueAt(j + 1, indexSelected);
                try {
                    // Parsing tanggal ke dalam objek Date jika indeks yang dipilih adalah 1 (Tanggal)
                    if (indexSelected == 1) {
                        Date date1 = dateFormat.parse(value1);
                        Date date2 = dateFormat.parse(value2);
                        // Membandingkan tanggal
                        int compareResult = date1.compareTo(date2);
                        // Jika "Terbaru" dipilih, urutkan dari yang terbaru
                        if (compareResult < 0) {
                            for (int k = 0; k < tbl.getColumnCount(); k++) {
                                Object temp = tbl.getValueAt(j, k);
                                tbl.setValueAt(tbl.getValueAt(j + 1, k), j, k);
                                tbl.setValueAt(temp, j + 1, k);
                            }
                        }
                    } else if (indexSelected == 2) { // Jika indeks yang dipilih adalah Total
                        Double doubleValue1 = Double.parseDouble(value1);
                        Double doubleValue2 = Double.parseDouble(value2);
                        // Membandingkan nilai total
                        int compareResult = doubleValue1.compareTo(doubleValue2);
                        // Jika "Total" dipilih, urutkan berdasarkan nilai total
                        if (compareResult > 0) {
                            for (int k = 0; k < tbl.getColumnCount(); k++) {
                                Object temp = tbl.getValueAt(j, k);
                                tbl.setValueAt(tbl.getValueAt(j + 1, k), j, k);
                                tbl.setValueAt(temp, j + 1, k);
                            }
                        }
                    }
                } catch (ParseException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
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
        btn_member = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        btn_profil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablejual = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablebeli = new javax.swing.JTable();
        btn_hapusjual = new javax.swing.JButton();
        btn_hapusbeli = new javax.swing.JButton();
        btn_restartjual = new javax.swing.JButton();
        btn_restartbeli = new javax.swing.JButton();
        btn_masuktransaksijual = new javax.swing.JButton();
        btn_masuktransaksibeli = new javax.swing.JButton();
        combosortingJual = new javax.swing.JComboBox<>();
        combosortingBeli = new javax.swing.JComboBox<>();
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

        btn_x.setBorder(null);
        btn_x.setContentAreaFilled(false);
        btn_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xActionPerformed(evt);
            }
        });
        getContentPane().add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 20, 30, 40));

        btn_profil.setContentAreaFilled(false);
        btn_profil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_profil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_profilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 13, 52, 50));

        tablejual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablejual);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 480, 400));

        tablebeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablebeli);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 480, 400));

        btn_hapusjual.setBorder(null);
        btn_hapusjual.setContentAreaFilled(false);
        btn_hapusjual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapusjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusjualActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapusjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 573, 110, 30));

        btn_hapusbeli.setBorder(null);
        btn_hapusbeli.setContentAreaFilled(false);
        btn_hapusbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapusbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusbeliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapusbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 573, 110, 30));

        btn_restartjual.setBorder(null);
        btn_restartjual.setContentAreaFilled(false);
        btn_restartjual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_restartjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restartjualActionPerformed(evt);
            }
        });
        getContentPane().add(btn_restartjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(386, 573, 40, 30));

        btn_restartbeli.setBorder(null);
        btn_restartbeli.setContentAreaFilled(false);
        btn_restartbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_restartbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restartbeliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_restartbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 573, 40, 30));

        btn_masuktransaksijual.setBorder(null);
        btn_masuktransaksijual.setContentAreaFilled(false);
        btn_masuktransaksijual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_masuktransaksijual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_masuktransaksijualActionPerformed(evt);
            }
        });
        getContentPane().add(btn_masuktransaksijual, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 632, 110, 30));

        btn_masuktransaksibeli.setBorder(null);
        btn_masuktransaksibeli.setContentAreaFilled(false);
        btn_masuktransaksibeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_masuktransaksibeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_masuktransaksibeliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_masuktransaksibeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 632, 110, 30));

        combosortingJual.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Terbaru", "Total" }));
        combosortingJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combosortingJualActionPerformed(evt);
            }
        });
        getContentPane().add(combosortingJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 145, 90, -1));

        combosortingBeli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Terbaru", "Total" }));
        combosortingBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combosortingBeliActionPerformed(evt);
            }
        });
        getContentPane().add(combosortingBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 145, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Transaksi_1.png"))); // NOI18N
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

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void btn_profilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_profilActionPerformed
        this.dispose();
        profil profil = new profil("id_admin");
        profil.setVisible(true);
    }//GEN-LAST:event_btn_profilActionPerformed

    private void btn_hapusjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusjualActionPerformed
        try {
            int baris = tablejual.getSelectedRow();
            Object kunci = tablejual.getValueAt(baris, 0);
            int konfirmasi = JOptionPane.showOptionDialog(this,
                    "Apakah anda yakin akan menghapus data tersebut ?", "Hapus",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String query = "DELETE from penjualan where id_penjualan = '" + kunci + "';";
                PreparedStatement statement = konek.getConnection().prepareStatement(query);
                statement.execute();
                ResultSet res = statement.getResultSet();
                datatablejual();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
            }
            if (konfirmasi == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Data batal dihapus");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_btn_hapusjualActionPerformed

    private void btn_hapusbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusbeliActionPerformed
        try {
            int baris = tablebeli.getSelectedRow();
            Object kunci = tablebeli.getValueAt(baris, 0);
            int konfirmasi = JOptionPane.showOptionDialog(this,
                    "Apakah anda yakin akan menghapus data tersebut ?", "Hapus",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String query = "DELETE from pembelian where id_pembelian = '" + kunci + "';";
                PreparedStatement statement = konek.getConnection().prepareStatement(query);
                statement.execute();
                ResultSet res = statement.getResultSet();
                datatablebeli();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
            }
            if (konfirmasi == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Data batal dihapus");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_btn_hapusbeliActionPerformed

    private void btn_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supplierActionPerformed
        this.dispose();
        supplier s = new supplier();
        s.setVisible(true);
    }//GEN-LAST:event_btn_supplierActionPerformed

    private void btn_restartjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restartjualActionPerformed
        datatablejual();
    }//GEN-LAST:event_btn_restartjualActionPerformed

    private void btn_restartbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restartbeliActionPerformed
        datatablebeli();
    }//GEN-LAST:event_btn_restartbeliActionPerformed

    private void btn_masuktransaksijualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_masuktransaksijualActionPerformed
        this.dispose();
        transaksi_jual jual = new transaksi_jual();
        jual.setVisible(true);
    }//GEN-LAST:event_btn_masuktransaksijualActionPerformed

    private void btn_masuktransaksibeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_masuktransaksibeliActionPerformed
        this.dispose();
        transaksi_beli beli = new transaksi_beli();
        beli.setVisible(true);
    }//GEN-LAST:event_btn_masuktransaksibeliActionPerformed

    private void combosortingJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combosortingJualActionPerformed
        String selectedOption = (String) combosortingJual.getSelectedItem();
        int selectedIndex = 0;  // default adalah 0

        if (selectedOption.equals("Default")) {     // Menyesuaikan selectedIndex berdasarkan opsi yang dipilih
            datatablejual();
        } else if (selectedOption.equals("Terbaru")) {
            selectedIndex = 1; 
        } else if (selectedOption.equals("Total")) {
            selectedIndex = 2; 
        }

        // Panggil bubbleSort 
        bubbleSortJual(tblJual, selectedIndex);
    }//GEN-LAST:event_combosortingJualActionPerformed

    private void combosortingBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combosortingBeliActionPerformed
        String selectedOption = (String) combosortingBeli.getSelectedItem();
        int selectedIndex = 0; // default adalah 0

        if (selectedOption.equals("Default")) {     // Menyesuaikan selectedIndex berdasarkan opsi yang dipilih
            datatablebeli();
        } else if (selectedOption.equals("Terbaru")) {
            selectedIndex = 1; // Index kolom Tanggal (terbaru)
        } else if (selectedOption.equals("Total")) {
            selectedIndex = 2; // Index kolom Total
        }

        // Panggil bubbleSort 
        bubbleSortBeli(tblBeli, selectedIndex);
    }//GEN-LAST:event_combosortingBeliActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_hapusbeli;
    private javax.swing.JButton btn_hapusjual;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_masuktransaksibeli;
    private javax.swing.JButton btn_masuktransaksijual;
    private javax.swing.JButton btn_member;
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_profil;
    private javax.swing.JButton btn_restartbeli;
    private javax.swing.JButton btn_restartjual;
    private javax.swing.JButton btn_supplier;
    private javax.swing.JButton btn_x;
    private javax.swing.JComboBox<String> combosortingBeli;
    private javax.swing.JComboBox<String> combosortingJual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablebeli;
    private javax.swing.JTable tablejual;
    // End of variables declaration//GEN-END:variables
}
