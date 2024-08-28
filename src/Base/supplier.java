/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author donih
 */
public class supplier extends javax.swing.JFrame {
    private DefaultTableModel tbl;
    public supplier() {
        initComponents();
        table.getModel();
        datatable();
    }

    public void datatable() {
        tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("No Telp");
        tbl.addColumn("Alamat");
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "select * from supplier;";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_supplier"),
                    res.getString("nama_supplier"),
                    res.getString("no_telp"),
                    res.getString("alamat"),});
            }
            table.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            e.printStackTrace();
        }
    }

    public void refresh() {
        txt_cari.setText(null);
        txt_namasupplier.setText(null);
        txt_notelp.setText(null);
        txt_alamat.setText(null);
        txt_id.setText(null);
        datatable();
    }

    private String generateSupplierID() {
        try {
            Statement statement = konek.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id_supplier, 2) AS SIGNED)) AS max_id FROM supplier;");
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                return String.format("S%03d", maxId + 1);
            } else {
                return "S001"; // Jika tidak ada produk di database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void bubbleSort(DefaultTableModel tbl, int indexSelected) {
        int rowCount = tbl.getRowCount();
        for (int i = 0; i < rowCount - 1; i++) {    // Loop untuk iterasi setiap elemen dalam array, kecuali elemen terakhir
            for (int j = 0; j < rowCount - i - 1; j++) {    // Loop untuk membandingkan elemen yang berdekatan
                String currentString = (String) tbl.getValueAt(j, indexSelected);   // Mendapatkan nilai string dari kolom yang dipilih 
                String nextString = (String) tbl.getValueAt(j + 1, indexSelected);

                if (indexSelected == 1) {   // Jika kolom yang dipilih adalah kolom Nama (indeks 1)
                    if (currentString.compareTo(nextString) > 0) {  // Membandingkan dua string
                        for (int k = 0; k < tbl.getColumnCount(); k++) {    // Jika currentString lebih besar dari nextString, tukar baris
                            Object temp = tbl.getValueAt(j, k);
                            tbl.setValueAt(tbl.getValueAt(j + 1, k), j, k);
                            tbl.setValueAt(temp, j + 1, k);
                        }
                    }
                }
            }
        }
    }

    private void inputan() {
        txt_cari.setText(null);
        txt_namasupplier.setText(null);
        txt_notelp.setText(null);
        txt_alamat.setText(null);
        txt_id.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_dashboard = new javax.swing.JButton();
        btn_produk = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_restart = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        btn_profil = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_member = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txt_namasupplier = new javax.swing.JTextField();
        txt_notelp = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        btn_transaksi = new javax.swing.JButton();
        combosorting = new javax.swing.JComboBox<>();
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

        txt_cari.setBackground(new java.awt.Color(255, 255, 255));
        txt_cari.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_cari.setForeground(new java.awt.Color(0, 0, 0));
        txt_cari.setBorder(null);
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 105, 320, 44));

        btn_cari.setBorder(null);
        btn_cari.setContentAreaFilled(false);
        btn_cari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 107, 50, 40));

        btn_restart.setBorder(null);
        btn_restart.setContentAreaFilled(false);
        btn_restart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restartActionPerformed(evt);
            }
        });
        getContentPane().add(btn_restart, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 107, 50, 40));

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

        btn_logout.setBorder(null);
        btn_logout.setContentAreaFilled(false);
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });
        getContentPane().add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 70, 25));

        btn_edit.setBorder(null);
        btn_edit.setContentAreaFilled(false);
        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 461, 110, 30));

        btn_hapus.setBorder(null);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 668, 110, 30));

        btn_member.setBorder(null);
        btn_member.setContentAreaFilled(false);
        btn_member.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_memberActionPerformed(evt);
            }
        });
        getContentPane().add(btn_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 200, 40));

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 230, 563, 410));

        txt_namasupplier.setBackground(new java.awt.Color(255, 255, 255));
        txt_namasupplier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_namasupplier.setForeground(new java.awt.Color(0, 0, 0));
        txt_namasupplier.setBorder(null);
        getContentPane().add(txt_namasupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(1046, 266, 185, 33));

        txt_notelp.setBackground(new java.awt.Color(255, 255, 255));
        txt_notelp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_notelp.setForeground(new java.awt.Color(0, 0, 0));
        txt_notelp.setBorder(null);
        getContentPane().add(txt_notelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1046, 324, 185, 33));

        txt_alamat.setBackground(new java.awt.Color(255, 255, 255));
        txt_alamat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_alamat.setForeground(new java.awt.Color(0, 0, 0));
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1046, 382, 185, 33));

        btn_tambah.setBorder(null);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(946, 461, 110, 30));

        txt_id.setEditable(false);
        txt_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 0, 0));
        txt_id.setBorder(null);
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1046, 208, 185, 33));

        btn_transaksi.setBorder(null);
        btn_transaksi.setContentAreaFilled(false);
        btn_transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 476, 200, 40));

        combosorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Nama" }));
        combosorting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combosortingActionPerformed(evt);
            }
        });
        getContentPane().add(combosorting, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 205, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Supplier_1.png"))); // NOI18N
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

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        String cari = txt_cari.getText();
        DefaultTableModel searc = new DefaultTableModel();
        searc.setColumnIdentifiers(new Object[]{"ID", "Nama", "No Telp", "Alamat"});
        
        for (int i = 0; i < tbl.getRowCount(); i++) {                       // Iterasi melalui setiap baris dalam tabel asli
            String id = tbl.getValueAt(i, 0).toString();            // Mengambil data dari setiap kolom pada baris ke-i
            String nama = tbl.getValueAt(i, 1).toString();
            String noTelp = tbl.getValueAt(i, 2).toString();
            String alamat = tbl.getValueAt(i, 3).toString();
            
            // Memeriksa apakah teks pencarian ada dalam salah satu kolom
            if (id.contains(cari) || nama.contains(cari) || noTelp.contains(cari) || alamat.contains(cari)) {   
                searc.addRow(new Object[]{id, nama, noTelp, alamat});       // Jika ada, tambahkan baris tersebut 
            }
        }
        table.setModel(searc);
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restartActionPerformed
        refresh();
    }//GEN-LAST:event_btn_restartActionPerformed

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void btn_profilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_profilActionPerformed
        this.dispose();
        profil profil = new profil("id_admin");
        profil.setVisible(true);
    }//GEN-LAST:event_btn_profilActionPerformed

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

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        String id = (String) txt_id.getText();
        String namabaru = txt_namasupplier.getText();
        String notelpbaru = txt_notelp.getText();
        String alamatbaru = txt_alamat.getText();
        try {
            if (namabaru.isEmpty() == true || notelpbaru.isEmpty() == true || alamatbaru.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Inputan harus terisi semua!");
            } else {
                // Tampilkan dialog konfirmasi
                int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan mengedit supplier ini?",
                        "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (konfirmasi == JOptionPane.YES_OPTION) {
                    Statement statement = (Statement) konek.getConnection().createStatement();
                    String query = "UPDATE supplier SET nama_supplier = '" + namabaru + "', no_telp = '" + notelpbaru + "', "
                            + "alamat = '" + alamatbaru + "' WHERE id_supplier = '" + id + "';";
                    statement.executeUpdate(query);
                    statement.close();
                    JOptionPane.showMessageDialog(null, "Supplier berhasil diedit");
                    refresh();
                }
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Supplier gagal diedit");
            t.printStackTrace();
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        try {
            int baris = table.getSelectedRow();
            Object kunci = table.getValueAt(baris, 0);
            int konfirmasi = JOptionPane.showOptionDialog(this,
                    "Apakah anda yakin akan menghapus data tersebut ?", "Hapus",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String query = "DELETE from supplier where id_supplier = '" + kunci + "';";
                PreparedStatement statement = konek.getConnection().prepareStatement(query);
                statement.execute();
                ResultSet res = statement.getResultSet();
                datatable();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                refresh();
            }
            if (konfirmasi == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Data batal dihapus");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_memberActionPerformed
        this.dispose();
        member prd = new member();
        prd.setVisible(true);
    }//GEN-LAST:event_btn_memberActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        String id = txt_id.getText();
        String nama = txt_namasupplier.getText();
        String notelp = txt_notelp.getText();
        String alamat = txt_alamat.getText();

        try {
            if (nama.isEmpty() || notelp.isEmpty() || alamat.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Inputan harus terisi semua!");
            } else if (!id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Supplier dengan ID " + id + " sudah ada. "
                        + "\nSilahkan refresh terlebih dahulu agar dapat menambahkan data supplier baru!");
            } else {
                // Menampilkan pesan konfirmasi
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menambah data tersebut?",
                        "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Statement statement = (Statement) konek.getConnection().createStatement();
                    String id_supplier = generateSupplierID();
                    String query = "INSERT INTO supplier (id_supplier, nama_supplier, no_telp, alamat) "
                            + "VALUES ('" + id_supplier + "', '" + nama + "', '" + notelp + "', '" + alamat + "');";
                    statement.executeUpdate(query);
                    statement.close();
                    JOptionPane.showMessageDialog(null, "Produk berhasil ditambah");

                    refresh();
                }
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Produk gagal ditambah");
            t.printStackTrace();
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int baris = table.getSelectedRow();
        if (baris != -1) {
            String id = (String) table.getValueAt(baris, 0);
            String nama = (String) table.getValueAt(baris, 1);
            String notelp = (String) table.getValueAt(baris, 2);
            String alamat = (String) table.getValueAt(baris, 3);

            txt_id.setText(id);
            txt_namasupplier.setText(nama);
            txt_notelp.setText(notelp);
            txt_alamat.setText(alamat);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        this.dispose();
        transaksi tr = new transaksi();
        tr.setVisible(true);
    }//GEN-LAST:event_btn_transaksiActionPerformed

    private void combosortingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combosortingActionPerformed
        String selectedOption = (String) combosorting.getSelectedItem();
        if (selectedOption.equals("Default")) { // Jika pengguna memilih "Default", panggil metode datatable()
            datatable();
        }
        int selectedIndex = 0; // Inisialisasi indeks kolom yang akan diurutkan ke 0
        if (selectedOption.equals("Nama")) {    // Jika pengguna memilih "Nama"
            selectedIndex = 1;
        }
        inputan();
        bubbleSort(tbl, selectedIndex);     // Panggil metode bubbleSort()
    }//GEN-LAST:event_combosortingActionPerformed

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
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_member;
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_profil;
    private javax.swing.JButton btn_restart;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JButton btn_x;
    private javax.swing.JComboBox<String> combosorting;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_namasupplier;
    private javax.swing.JTextField txt_notelp;
    // End of variables declaration//GEN-END:variables
}
