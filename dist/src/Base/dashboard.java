/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author donih
 */
public class dashboard extends javax.swing.JFrame {
    private String id_admin;
    private DefaultTableModel tbl;

    public dashboard() {
        initComponents();
        setPendapatanHariIni();
        table.getModel();
        datatable();
    }
    private void setPendapatanHariIni() {
        try {
            Statement statement = konek.getConnection().createStatement();
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            format.setMaximumFractionDigits(0);
            DecimalFormatSymbols symbols = ((DecimalFormat) format).getDecimalFormatSymbols();
            symbols.setCurrencySymbol("Rp. ");
            ((DecimalFormat) format).setDecimalFormatSymbols(symbols);
            ResultSet pendapatanhari = statement.executeQuery("SELECT COALESCE(sum(total), 0) FROM penjualan where "
                    + "day(penjualan.tgl_penjualan) = day(now())");
            String pendapatanHariIni = "";
            if (pendapatanhari.next()) {
                pendapatanHariIni = pendapatanhari.getString(1);
            }
            jPendapatanHariIni.setText(format.format(Double.parseDouble(pendapatanHariIni)));
            pendapatanhari.close();

            ResultSet pendapatanbulan = statement.executeQuery("SELECT COALESCE(sum(total), 0) FROM penjualan where "
                    + "MONTH(penjualan.tgl_penjualan) = MONTH(now()) and YEAR(penjualan.tgl_penjualan) = YEAR(now())");
            String pendapatanBulanIni = "";
            if (pendapatanbulan.next()) {
                pendapatanBulanIni = pendapatanbulan.getString(1);
            }
            jPendapatanBulanIni.setText(format.format(Double.parseDouble(pendapatanBulanIni)));
            pendapatanbulan.close();

            ResultSet pengeluaranhari = statement.executeQuery("SELECT COALESCE(sum(total), 0) FROM pembelian where "
                    + "day(pembelian.tgl_pembelian) = day(now())");
            String pengeluaranHariIni = "";
            if (pengeluaranhari.next()) {
                pengeluaranHariIni = pengeluaranhari.getString(1);
            }
            jPengeluaranHariIni.setText(format.format(Double.parseDouble(pengeluaranHariIni)));
            pengeluaranhari.close();

            ResultSet pengeluaranbulan = statement.executeQuery("SELECT COALESCE(sum(total), 0) FROM pembelian where "
                    + "MONTH(pembelian.tgl_pembelian) = MONTH(now()) and YEAR(pembelian.tgl_pembelian) = YEAR(now())");
            String pengeluaranBulanIni = "";
            if (pengeluaranbulan.next()) {
                pengeluaranBulanIni = pengeluaranbulan.getString(1);
            }
            jPengeluaranBulanIni.setText(format.format(Double.parseDouble(pengeluaranBulanIni)));
            pengeluaranbulan.close();
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void datatable() {
        tbl = new DefaultTableModel();
        tbl.addColumn("Nama Produk");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Subtotal");
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "SELECT p.nama_produk, SUM(dp.jumlah) AS jumlah, SUM(dp.subtotal) AS subtotal "
                    + "FROM detail_penjualan dp LEFT JOIN produk p ON dp.id_produk = p.id_produk JOIN penjualan j ON dp.id_penjualan = j.id_penjualan "
                    + "WHERE DATE(j.tgl_penjualan) = CURDATE() GROUP BY p.nama_produk;";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("nama_produk"),
                    res.getString("jumlah"),
                    res.getString("subtotal"),});
            }
            table.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            e.printStackTrace();
        }
    }
    public void bubbleSort(DefaultTableModel tbl, int indexSelected) { 
        int rowCount = tbl.getRowCount(); 
        for (int i = 0; i < rowCount - 1; i++) { // looping untuk melintasi setiap baris pada tabel
            for (int j = 0; j < rowCount - i - 1; j++) { // looping untuk membandingkan setiap pasangan baris yang bersekatan 
                String currentString = (String) tbl.getValueAt(j, indexSelected); // simpan nilai kolom yang dipilih untuk baris saat ini dan berikutnya
                String nextString = (String) tbl.getValueAt(j + 1, indexSelected);
                if (indexSelected == 1 || indexSelected == 2) { // emeriksa apakah kolom yang dipilih adalah kolom "Jumlah" atau "Subtotal".
                    try {
                        Double currentDouble = Double.parseDouble(currentString); // string menjadi double
                        Double nextDouble = Double.parseDouble(nextString);
                        if (currentDouble.compareTo(nextDouble) > 0) { // Jika nilai saat ini lebih besar dari nilai berikutnya, maka baris akan ditukar.
                            for (int k = 0; k < tbl.getColumnCount(); k++) {
                                Object temp = tbl.getValueAt(j, k);
                                tbl.setValueAt(tbl.getValueAt(j + 1, k), j, k);
                                tbl.setValueAt(temp, j + 1, k);
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else { // Jika kolom yang dipilih bukan "Jumlah" atau "Subtotal"
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_x = new javax.swing.JButton();
        btn_member = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_transaksi = new javax.swing.JButton();
        btn_supplier = new javax.swing.JButton();
        btn_produk = new javax.swing.JButton();
        btn_profil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPendapatanHariIni = new javax.swing.JLabel();
        jPendapatanBulanIni = new javax.swing.JLabel();
        jPengeluaranHariIni = new javax.swing.JLabel();
        jPengeluaranBulanIni = new javax.swing.JLabel();
        pieChartPanel1 = new Base.PieChartPanel();
        combosorting = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

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

        btn_transaksi.setBorder(null);
        btn_transaksi.setContentAreaFilled(false);
        btn_transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 476, 200, 40));

        btn_supplier.setBorder(null);
        btn_supplier.setContentAreaFilled(false);
        btn_supplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supplierActionPerformed(evt);
            }
        });
        getContentPane().add(btn_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 200, 40));

        btn_produk.setBorder(null);
        btn_produk.setContentAreaFilled(false);
        btn_produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_produkActionPerformed(evt);
            }
        });
        getContentPane().add(btn_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 345, 200, 40));

        btn_profil.setContentAreaFilled(false);
        btn_profil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_profil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_profilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 13, 52, 50));

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
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 475, 553, 215));

        jPendapatanHariIni.setBackground(new java.awt.Color(255, 255, 255));
        jPendapatanHariIni.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jPendapatanHariIni.setForeground(new java.awt.Color(0, 0, 0));
        jPendapatanHariIni.setText("jLabel2");
        getContentPane().add(jPendapatanHariIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 195, 370, 60));

        jPendapatanBulanIni.setBackground(new java.awt.Color(255, 255, 255));
        jPendapatanBulanIni.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jPendapatanBulanIni.setForeground(new java.awt.Color(0, 0, 0));
        jPendapatanBulanIni.setText("jLabel2");
        getContentPane().add(jPendapatanBulanIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 318, 370, 60));

        jPengeluaranHariIni.setBackground(new java.awt.Color(255, 255, 255));
        jPengeluaranHariIni.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jPengeluaranHariIni.setForeground(new java.awt.Color(0, 0, 0));
        jPengeluaranHariIni.setText("jLabel2");
        getContentPane().add(jPengeluaranHariIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 195, 370, 60));

        jPengeluaranBulanIni.setBackground(new java.awt.Color(255, 255, 255));
        jPengeluaranBulanIni.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jPengeluaranBulanIni.setForeground(new java.awt.Color(0, 0, 0));
        jPengeluaranBulanIni.setText("jLabel2");
        getContentPane().add(jPengeluaranBulanIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 318, 370, 60));

        javax.swing.GroupLayout pieChartPanel1Layout = new javax.swing.GroupLayout(pieChartPanel1);
        pieChartPanel1.setLayout(pieChartPanel1Layout);
        pieChartPanel1Layout.setHorizontalGroup(
            pieChartPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        pieChartPanel1Layout.setVerticalGroup(
            pieChartPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        getContentPane().add(pieChartPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 468, 393, 220));

        combosorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Nama", "Jumlah", "Subtotal" }));
        combosorting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combosortingActionPerformed(evt);
            }
        });
        getContentPane().add(combosorting, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 450, 80, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Dashboard.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

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

    private void btn_profilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_profilActionPerformed
        this.dispose();
        profil profil = new profil(id_admin);
        profil.setVisible(true);
        System.out.println(id_admin);
    }//GEN-LAST:event_btn_profilActionPerformed

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

    private void btn_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supplierActionPerformed
        this.dispose();
        supplier s = new supplier();
        s.setVisible(true);
    }//GEN-LAST:event_btn_supplierActionPerformed

    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        this.dispose();
        transaksi tr = new transaksi();
        tr.setVisible(true);
    }//GEN-LAST:event_btn_transaksiActionPerformed

    private void combosortingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combosortingActionPerformed
        String selectedOption = (String) combosorting.getSelectedItem();
        int selectedIndex = 0;  // nilai default adalah 0
        if (selectedOption.equals("Nama")) {    // Memeriksa opsi yang dipilih lalu menetapkan indeks sesuai dengan kolom yang akan diurutkan
            selectedIndex = 0; 
        } else if (selectedOption.equals("Jumlah")) {
            selectedIndex = 1; 
        } else if (selectedOption.equals("Subtotal")) {
            selectedIndex = 2; 
        }

        if (selectedIndex >= 0 && selectedIndex < tbl.getColumnCount()) {
            bubbleSort(tbl, selectedIndex); // panggil bubbleSort untuk mengurutkan tabel berdasarkan kolom terpilih
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }
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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_member;
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_profil;
    private javax.swing.JButton btn_supplier;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JButton btn_x;
    private javax.swing.JComboBox<String> combosorting;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jPendapatanBulanIni;
    private javax.swing.JLabel jPendapatanHariIni;
    private javax.swing.JLabel jPengeluaranBulanIni;
    private javax.swing.JLabel jPengeluaranHariIni;
    private javax.swing.JScrollPane jScrollPane1;
    private Base.PieChartPanel pieChartPanel1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
