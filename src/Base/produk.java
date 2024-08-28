/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import com.barcodelib.barcode.Linear;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author donih
 */
public class produk extends javax.swing.JFrame {
    private String id_admin;
    private DefaultTableModel tbl;

    public produk() {
        initComponents();
        table.getModel();
        datatable();
        setSupplierOptions();

    }
    
    public ArrayList<String> SupplierOptions() {
        ArrayList<String> options = new ArrayList<>();
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "SELECT id_supplier FROM supplier";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                options.add(res.getString("id_supplier"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
        }
        return options;
    }
    
    public void setSupplierOptions() {
        ArrayList<String> options = SupplierOptions();
        for (String option : options) {
            txt_idsupplier.addItem(option);
        }
        txt_idsupplier.setSelectedItem(null);
        txt_jumlah.setSelectedItem(null);
    }
    
    public void datatable() {
        tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("Harga");
        tbl.addColumn("Stok");
        tbl.addColumn("ID Supplier");
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "select * from produk;";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_produk"),
                    res.getString("nama_produk"),
                    res.getString("harga"),
                    res.getString("jumlah"),
                    res.getString("id_supplier"),});
            }
            table.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            e.printStackTrace();
        }
    }

    private String generateProductID() {
        try {
            Statement statement = konek.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id_produk, 2) "
                    + "AS SIGNED)) AS max_id FROM produk;"); // Mengonversi substring tersebut menjadi integer.
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                return String.format("P%03d", maxId + 1); // Mengembalikan ID produk baru dengan format "P" diikuti angka 3 digit
            } else {
                return "P001"; // Jika tidak ada produk di database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void refresh() {
        txt_cari.setText(null);
        txt_harga.setText(null);
        txt_idsupplier.setSelectedItem(null);
        txt_jumlah.setSelectedItem(null);
        txt_namaproduk.setText(null);
        txt_id.setText(null);
        datatable();
    }

    private void cetakBarcode() {
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B); // Mengatur tipe barcode ke CODE128B
            barcode.setData(this.txt_id.getText());
            barcode.setI(12.0f); // Mengatur ukuran atau skala barcode
            barcode.renderBarcode("barcode//imagesBarcode//" + this.txt_id.getText().toString() + ".png");
        } catch (Exception e) {
            System.out.println("Error Code : " + e);
        }
    }

    private String idSelected;
    private void refreshBarcode() {
        File file = new File("barcode\\imagesBarcode\\" + idSelected + ".png");
        if (file.exists()) {
            lbl_barcode.setIcon(null);
        } else {
            lbl_barcode.setIcon(new ImageIcon(file.getAbsolutePath()));
        }
        lbl_barcode.revalidate(); // Menambahkan pemanggilan revalidate
        lbl_barcode.repaint(); // Menambahkan pemanggilan repaint
    }

    private void hapusBarcode() {
        File file = new File("barcode\\imagesBarcode\\" + idSelected + ".png");
        if (file.delete()) {
            System.out.println("Data barang terhapus beserta barcodenya !!!");
        } else {
            System.out.println("tidak ada barcode yang dihapus");
        }
    }

    public void bubbleSort(DefaultTableModel tbl, int indexSelected) {
        int rowCount = tbl.getRowCount();
        for (int i = 0; i < rowCount - 1; i++) {    // Loop pertama untuk iterasi seluruh elemen
            for (int j = 0; j < rowCount - i - 1; j++) {    // Loop kedua untuk perbandingan elemen yang berdekatan
                String currentString = (String) tbl.getValueAt(j, indexSelected);
                String nextString = (String) tbl.getValueAt(j + 1, indexSelected);

                if (indexSelected == 2 || indexSelected == 3) { // Jika kolom adalah kolom ke-2 atau ke-3, lakukan perbandingan numerik
                    try {
                        Double currentDouble = Double.parseDouble(currentString);   // string menjadi double
                        Double nextDouble = Double.parseDouble(nextString);         // string menjadi double
                        if (currentDouble.compareTo(nextDouble) > 0) {  // Jika nilai lebih besar dari nilai berikutnya, tukar posisi
                            for (int k = 0; k < tbl.getColumnCount(); k++) {
                                Object temp = tbl.getValueAt(j, k);
                                tbl.setValueAt(tbl.getValueAt(j + 1, k), j, k);
                                tbl.setValueAt(temp, j + 1, k);
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {    // Jika kolom bukan kolom ke-2 atau ke-3, lakukan perbandingan string
                    if (currentString.compareTo(nextString) > 0) {
                        for (int k = 0; k < tbl.getColumnCount(); k++) {
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
        txt_harga.setText(null);
        txt_idsupplier.setSelectedItem(null);
        txt_jumlah.setSelectedItem(null);
        txt_namaproduk.setText(null);
        txt_id.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_dashboard = new javax.swing.JButton();
        btn_supplier = new javax.swing.JButton();
        btn_transaksi = new javax.swing.JButton();
        btn_member = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_restart = new javax.swing.JButton();
        txt_namaproduk = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_idsupplier = new javax.swing.JComboBox<>();
        txt_jumlah = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btn_profil = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        btn_tambahbarcode = new javax.swing.JButton();
        combosorting = new javax.swing.JComboBox<>();
        lbl_barcode = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_edit.setBorder(null);
        btn_edit.setContentAreaFilled(false);
        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 502, 110, 30));

        btn_hapus.setBorder(null);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 660, 110, 30));

        btn_tambah.setBorder(null);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(947, 502, 110, 30));

        btn_dashboard.setBorder(null);
        btn_dashboard.setContentAreaFilled(false);
        btn_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 265, 200, 40));

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

        btn_x.setBorder(null);
        btn_x.setContentAreaFilled(false);
        btn_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xActionPerformed(evt);
            }
        });
        getContentPane().add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 20, 30, 40));

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

        txt_namaproduk.setBackground(new java.awt.Color(255, 255, 255));
        txt_namaproduk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_namaproduk.setForeground(new java.awt.Color(0, 0, 0));
        txt_namaproduk.setBorder(null);
        getContentPane().add(txt_namaproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1038, 266, 185, 33));

        txt_harga.setBackground(new java.awt.Color(255, 255, 255));
        txt_harga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_harga.setForeground(new java.awt.Color(0, 0, 0));
        txt_harga.setBorder(null);
        getContentPane().add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1038, 324, 185, 33));

        txt_idsupplier.setBackground(new java.awt.Color(255, 255, 255));
        txt_idsupplier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_idsupplier.setForeground(new java.awt.Color(0, 0, 0));
        txt_idsupplier.setBorder(null);
        txt_idsupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_idsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idsupplierActionPerformed(evt);
            }
        });
        getContentPane().add(txt_idsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(1038, 440, 185, 33));

        txt_jumlah.setBackground(new java.awt.Color(255, 255, 255));
        txt_jumlah.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_jumlah.setForeground(new java.awt.Color(0, 0, 0));
        txt_jumlah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
        txt_jumlah.setBorder(null);
        txt_jumlah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1038, 382, 185, 33));

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

        btn_profil.setContentAreaFilled(false);
        btn_profil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_profil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_profilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 13, 52, 50));

        txt_id.setEditable(false);
        txt_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 0, 0));
        txt_id.setBorder(null);
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1038, 208, 185, 33));

        btn_tambahbarcode.setBorder(null);
        btn_tambahbarcode.setContentAreaFilled(false);
        btn_tambahbarcode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambahbarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahbarcodeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambahbarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 659, 170, 30));

        combosorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Nama", "Harga", "Stok" }));
        combosorting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combosortingActionPerformed(evt);
            }
        });
        getContentPane().add(combosorting, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 205, 90, -1));

        lbl_barcode.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_barcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_barcode.setToolTipText("");
        lbl_barcode.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 580, 183, 68));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Produk.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dashboardActionPerformed
        this.dispose();
        dashboard dashboard = new dashboard();
        dashboard.setVisible(true);
    }//GEN-LAST:event_btn_dashboardActionPerformed

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

    private void btn_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_memberActionPerformed
        this.dispose();
        member prd = new member();
        prd.setVisible(true);
    }//GEN-LAST:event_btn_memberActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        String id = (String) txt_id.getText();
        String namabaru = txt_namaproduk.getText();
        String hargabaru = txt_harga.getText();
        String jumlahbaru = (String) txt_jumlah.getSelectedItem();
        String supplierbaru = (String) txt_idsupplier.getSelectedItem();
        try {
            if (namabaru.isEmpty() == true || hargabaru.isEmpty() == true || jumlahbaru.isEmpty() == true || supplierbaru.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Inputan harus terisi semua!");
            } else {
                // Tampilkan dialog konfirmasi
                int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan mengedit produk ini?",
                        "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (konfirmasi == JOptionPane.YES_OPTION) {
                    Statement statement = (Statement) konek.getConnection().createStatement();
                    String query = "UPDATE produk SET nama_produk = '" + namabaru + "', harga = " + hargabaru + ", jumlah = " + jumlahbaru + ", "
                            + "id_supplier = '" + supplierbaru + "' WHERE id_produk = '" + id + "';";
                    statement.executeUpdate(query);
                    statement.close();
                    JOptionPane.showMessageDialog(null, "Produk berhasil diedit");
                    refresh();
                    refreshBarcode();
                }
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Produk gagal diedit");
            t.printStackTrace();
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        try {
            int baris = table.getSelectedRow();
            Object kunci = table.getValueAt(baris, 0);
            int konfirmasi = JOptionPane.showOptionDialog(this,
                    "Apakah anda yakin akan menghapus produk tersebut ?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String query = "DELETE from `produk` where `id_produk`= '" + kunci + "';";
                PreparedStatement statement = konek.getConnection().prepareStatement(query);
                statement.execute();
                ResultSet res = statement.getResultSet();
                datatable();
                hapusBarcode();
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus");
            }
            if (konfirmasi == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Produk batal dihapus");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih Produk terlebih dahulu!");
        }
        refresh();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        String nama = txt_namaproduk.getText();
        String harga = txt_harga.getText();
        String jumlah = (String) txt_jumlah.getSelectedItem();
        String id_supplier = (String) txt_idsupplier.getSelectedItem();
        String id = txt_id.getText();
        try {
            if (nama.isEmpty() || harga.isEmpty() || jumlah.isEmpty() || id_supplier.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Inputan harus terisi semua!");
            } else if (!id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Produk dengan ID tersebut sudah ada \nSilahkan refresh terlebih dahulu!");
            } else {
                // Menampilkan pesan konfirmasi
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menambah data tersebut?",
                        "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Statement statement = (Statement) konek.getConnection().createStatement();
                    String id_produk = generateProductID();
                    String query = "INSERT INTO produk (id_produk, nama_produk, harga, jumlah, id_supplier) "
                            + "VALUES ('" + id_produk + "', '" + nama + "', " + harga + ", " + jumlah + ", '" + id_supplier + "');";
                    statement.executeUpdate(query);
                    statement.close();
                    cetakBarcode();
                    refreshBarcode();
                    ImageIcon icon = new ImageIcon("barcode\\imagesBarcode\\" + id_produk + ".png");
                    JOptionPane.showMessageDialog(null, "Produk berhasil ditambah");
                    refresh();
                }
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Produk gagal ditambah");
            t.printStackTrace();
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        String cari = txt_cari.getText();
        DefaultTableModel searc = new DefaultTableModel(); // menyimpan hasil pencarian dalam variabel searc
        searc.setColumnIdentifiers(new Object[]{"ID", "Nama", "Harga", "Stok", "ID Supplier"}); // Menetapkan nama kolom untuk model tabel pencarian
        
        for (int i = 0; i < tbl.getRowCount(); i++) { // Iterasi melalui setiap baris dalam model tabel utama
            String id = tbl.getValueAt(i, 0).toString(); // Mendapatkan nilai dari setiap kolom pada baris saat ini
            String nama = tbl.getValueAt(i, 1).toString();
            String harga = tbl.getValueAt(i, 2).toString();
            String stok = tbl.getValueAt(i, 3).toString();
            String idSupplier = tbl.getValueAt(i, 4).toString();
            
            // Memeriksa apakah nilai dari salah satu kolom mengandung kata kunci pencarian
            if (id.contains(cari) || nama.contains(cari) || harga.contains(cari) || stok.contains(cari) || idSupplier.contains(cari)) {
                searc.addRow(new Object[]{id, nama, harga, stok, idSupplier}); // Jika ada, tambahkan baris tersebut ke dalam model tabel pencarian
            }
        }
        table.setModel(searc); // Mengatur model tabel hasil pencarian ke dalam tabel
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restartActionPerformed
        refresh();
        refreshBarcode();
    }//GEN-LAST:event_btn_restartActionPerformed

    private void txt_idsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idsupplierActionPerformed
    }//GEN-LAST:event_txt_idsupplierActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
    }//GEN-LAST:event_txt_cariActionPerformed

    private void btn_profilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_profilActionPerformed
        this.dispose();
        profil profil = new profil(id_admin);
        profil.setVisible(true);
        System.out.println(id_admin);
    }//GEN-LAST:event_btn_profilActionPerformed

    private void btn_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supplierActionPerformed
        this.dispose();
        supplier s = new supplier();
        s.setVisible(true);
    }//GEN-LAST:event_btn_supplierActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int baris = table.getSelectedRow();
        int width = 200;
        int height = 60;
        if (baris != -1) {
            String id = (String) table.getValueAt(baris, 0);
            String nama = (String) table.getValueAt(baris, 1);
            String harga = (String) table.getValueAt(baris, 2);
            String jumlah = (String) table.getValueAt(baris, 3);
            String supplier = (String) table.getValueAt(baris, 4);

            txt_id.setText(id);
            txt_namaproduk.setText(nama);
            txt_harga.setText(harga);
            txt_jumlah.setSelectedItem(jumlah);
            txt_idsupplier.setSelectedItem(supplier);

            this.idSelected = table.getValueAt(baris, 0).toString();
            ImageIcon icon = new ImageIcon("barcode\\imagesBarcode\\" + idSelected + ".png");
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            lbl_barcode.setIcon(scaledIcon);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btn_tambahbarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahbarcodeActionPerformed
        int pilihBaris = table.getSelectedRow();
        int width = 200;
        int height = 60;
        if (pilihBaris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang mau ditambahkan barcodenya !!!");
            return;
        }
        this.idSelected = table.getValueAt(pilihBaris, 0).toString();
        // Cek apakah file barcode sudah ada
        File barcodeFile = new File("barcode\\imagesBarcode\\" + idSelected + ".png");
        if (barcodeFile.exists()) {
            JOptionPane.showMessageDialog(this, "Barcode untuk produk ini sudah ada.");
            return;
        }
        cetakBarcode();
        refreshBarcode();

        ImageIcon icon = new ImageIcon(barcodeFile.getAbsolutePath());
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lbl_barcode.setIcon(scaledIcon);
    }//GEN-LAST:event_btn_tambahbarcodeActionPerformed

    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        this.dispose();
        transaksi tr = new transaksi();
        tr.setVisible(true);
    }//GEN-LAST:event_btn_transaksiActionPerformed

    private void combosortingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combosortingActionPerformed
        String selectedOption = (String) combosorting.getSelectedItem();
        int selectedIndex = 0;  // nilai default adalah 0
        if (selectedOption.equals("Nama")) {    // Memeriksa opsi yang dipilih lalu menetapkan indeks sesuai dengan kolom yang akan diurutkan
            selectedIndex = 1;
        } else if (selectedOption.equals("Harga")) {
            selectedIndex = 2;
        } else if (selectedOption.equals("Stok")) {
            selectedIndex = 3;
        }
        inputan();
        refreshBarcode();
        bubbleSort(tbl, selectedIndex); // panggil bubbleSort untuk mengurutkan tabel berdasarkan kolom terpilih
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
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new produk().setVisible(true);
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
    private javax.swing.JButton btn_profil;
    private javax.swing.JButton btn_restart;
    private javax.swing.JButton btn_supplier;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tambahbarcode;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JButton btn_x;
    private javax.swing.JComboBox<String> combosorting;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_barcode;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_id;
    private javax.swing.JComboBox<String> txt_idsupplier;
    private javax.swing.JComboBox<String> txt_jumlah;
    private javax.swing.JTextField txt_namaproduk;
    // End of variables declaration//GEN-END:variables
}
