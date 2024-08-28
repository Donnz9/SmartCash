/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author donih
 */
public class transaksi_beli extends javax.swing.JFrame {
    private DefaultTableModel tbl;
    
    public transaksi_beli() {
        initComponents();
        hari();
        datatableproduk();
        datatablepembelian();
    }

    private void hari() {
        Date tgl = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
        String tanggalFormatted = formatter.format(tgl);
        tanggal.setText(tanggalFormatted);
    }

    private void datatableproduk() {
        tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("Harga");
        tbl.addColumn("Stok");
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "select * from produk;";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_produk"),
                    res.getString("nama_produk"),
                    res.getString("harga"),
                    res.getString("jumlah"),});
            }
            table_produk.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            e.printStackTrace();
        }
    }

    private void datatablepembelian() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("Harga");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Total Harga");
        table_pembelian.setModel(tbl);
    }

    private void refresh() {
        txt_id.setText(null);
        txt_nama.setText(null);
        txt_harga.setText(null);
        txt_jumlah.setSelectedItem("1");
        txt_cari.setText(null);
        datatableproduk();
    }

    private String hitungTotalHarga() {
        double totalHarga = 0;      // Variabel untuk menyimpan total harga
        DefaultTableModel model = (DefaultTableModel) table_pembelian.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {     // Looping melalui setiap baris dalam tabel pembelian
            double harga = Double.parseDouble(model.getValueAt(i, 2).toString());   // Mengambil harga dan jumlah dari setiap baris, kemudian mengonversi ke tipe data double dan int
            int jumlah = Integer.parseInt(model.getValueAt(i, 3).toString());
            totalHarga += harga * jumlah;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");    // Membuat objek DecimalFormat untuk memformat total harga dengan dua angka desimal
        return decimalFormat.format(totalHarga);    // Mengembalikan total harga yang diformat sebagai string
    }

    private String generatePembelianID() {
        try {
            Statement statement = konek.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id_pembelian, 2) AS SIGNED)) AS max_id_pembelian FROM pembelian;");
            if (rs.next()) {
                int maxId = rs.getInt("max_id_pembelian");
                return String.format("B%03d", maxId + 1);
            } else {
                return "B001"; // Jika tidak ada produk di database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String generateTransaksiID() {
        try {
            Statement statement = konek.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id_transaksi, 2) AS SIGNED)) AS max_id_transaksi FROM transaksi;");
            if (rs.next()) {
                int maxId_transaksi = rs.getInt("max_id_transaksi");
                return String.format("T%03d", maxId_transaksi + 1);
            } else {
                return "T001"; // Jika tidak ada transaksi di database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String generateProductID() {
        try {
            Statement statement = konek.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id_produk, 2) AS SIGNED)) AS max_id FROM produk;");
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                return String.format("P%03d", maxId + 1);
            } else {
                return "P001"; // Jika tidak ada produk di database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void simpan() {
        String totalHargaCek = txt_totalharga.getText();
        if (totalHargaCek.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang akan disimpan.");
            return; // Keluar dari metode jika txt_totalharga kosong
        }
        int totalSemua = Integer.parseInt(txt_totalharga.getText());
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            DefaultTableModel model = (DefaultTableModel) table_pembelian.getModel();
            int rowCount = model.getRowCount();
            connection = konek.getConnection();
            statement = connection.createStatement();

            String id_pembelian = generatePembelianID();
            String query_penjualan = "INSERT INTO pembelian (id_pembelian, tgl_pembelian, total) "
                    + "VALUES ('" + id_pembelian + "', current_timestamp(), " + totalSemua + ")";
            statement.executeUpdate(query_penjualan);
            // maximum ID penjualan
            String max_id_pembelian = "SELECT MAX(id_pembelian) AS max_id FROM pembelian;";
            rs = statement.executeQuery(max_id_pembelian);
            String max_id = null;
            if (rs.next()) {
                max_id = rs.getString("max_id");
            }
            rs.close();

            String id_transaksi = generateTransaksiID();
            String QueryTransaksi = "INSERT INTO transaksi (id_transaksi, tgl_transaksi, total, id_pembelian, id_admin) "
                    + "VALUES ('" + id_transaksi + "', current_timestamp(), " + totalSemua + ", '" + max_id + "', 'A003')";
            statement.executeUpdate(QueryTransaksi);
            for (int i = 0; i < rowCount; i++) {
                String id_produk = model.getValueAt(i, 0).toString();
                String harga = model.getValueAt(i, 2).toString();
                String jumlah = model.getValueAt(i, 3).toString();
                String subtotalStr = model.getValueAt(i, 4).toString();
                String query = "INSERT INTO detail_pembelian (jumlah, harga_satuan, subtotal, id_pembelian, id_produk)  "
                        + "VALUES (" + jumlah + ", " + harga + ", " + subtotalStr + ", '" + id_pembelian + "', '" + id_produk + "')";
                statement.executeUpdate(query);
            }
            JOptionPane.showMessageDialog(null, "Data pembelian berhasil disimpan ke database.");
            datatablepembelian();
            datatableproduk();
            txt_totalharga.setText(null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menyimpan ke database: " + e.getMessage());
            e.printStackTrace();
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

        txt_nama = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JComboBox<>();
        btn_insert = new javax.swing.JButton();
        btn_restart = new javax.swing.JButton();
        btn_dashboard = new javax.swing.JButton();
        btn_produk = new javax.swing.JButton();
        btn_supplier = new javax.swing.JButton();
        btn_member = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        txt_totalharga = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_hapusjual = new javax.swing.JButton();
        tanggal = new javax.swing.JLabel();
        btn_profil = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_produk = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_pembelian = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        btn_kembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nama.setEditable(false);
        txt_nama.setBackground(new java.awt.Color(204, 204, 204));
        txt_nama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_nama.setForeground(new java.awt.Color(0, 0, 0));
        txt_nama.setBorder(null);
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 138, 180, 25));

        txt_harga.setBackground(new java.awt.Color(255, 255, 255));
        txt_harga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_harga.setForeground(new java.awt.Color(0, 0, 0));
        txt_harga.setBorder(null);
        getContentPane().add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 179, 180, 25));

        txt_jumlah.setBackground(new java.awt.Color(255, 255, 255));
        txt_jumlah.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_jumlah.setForeground(new java.awt.Color(0, 0, 0));
        txt_jumlah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
        txt_jumlah.setBorder(null);
        txt_jumlah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(386, 219, 195, 26));

        btn_insert.setBorder(null);
        btn_insert.setContentAreaFilled(false);
        btn_insert.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });
        getContentPane().add(btn_insert, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 217, 110, 30));

        btn_restart.setBorder(null);
        btn_restart.setContentAreaFilled(false);
        btn_restart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restartActionPerformed(evt);
            }
        });
        getContentPane().add(btn_restart, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 40, 30));

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

        txt_totalharga.setEditable(false);
        txt_totalharga.setBackground(new java.awt.Color(204, 204, 204));
        txt_totalharga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_totalharga.setForeground(new java.awt.Color(0, 0, 0));
        txt_totalharga.setBorder(null);
        getContentPane().add(txt_totalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 602, 180, 25));

        btn_simpan.setBorder(null);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 679, 110, 30));

        btn_batal.setBorder(null);
        btn_batal.setContentAreaFilled(false);
        btn_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 679, 110, 30));

        btn_hapusjual.setBorder(null);
        btn_hapusjual.setContentAreaFilled(false);
        btn_hapusjual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapusjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusjualActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapusjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1147, 555, 110, 30));

        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setForeground(new java.awt.Color(0, 0, 0));
        tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tanggal.setText("jLabel2");
        tanggal.setToolTipText("");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1045, 96, 210, -1));

        btn_profil.setContentAreaFilled(false);
        btn_profil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_profil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_profilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 13, 52, 50));

        btn_x.setBorder(null);
        btn_x.setContentAreaFilled(false);
        btn_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xActionPerformed(evt);
            }
        });
        getContentPane().add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 20, 30, 40));

        table_produk.setModel(new javax.swing.table.DefaultTableModel(
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
        table_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_produkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_produk);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 300, 483, 280));

        table_pembelian.setModel(new javax.swing.table.DefaultTableModel(
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
        table_pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pembelianMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_pembelian);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 300, 490, 250));

        txt_cari.setBackground(new java.awt.Color(255, 255, 255));
        txt_cari.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_cari.setForeground(new java.awt.Color(0, 0, 0));
        txt_cari.setBorder(null);
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 200, 30));

        btn_cari.setBorder(null);
        btn_cari.setContentAreaFilled(false);
        btn_cari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 90, 40, 30));

        txt_id.setEditable(false);
        txt_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 0, 0));
        txt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_id.setBorder(null);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 138, 73, 25));

        btn_kembali.setBorder(null);
        btn_kembali.setContentAreaFilled(false);
        btn_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 25, 50, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Transaksi beli.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed

    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        String id = txt_id.getText();
        String nama = txt_nama.getText();
        String harga = txt_harga.getText();
        String jumlah = (String) txt_jumlah.getSelectedItem();

        if (jumlah.isEmpty() || harga.isEmpty() || nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inputan harus terisi semua", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int hargaInt = Integer.parseInt(harga);
            int jumlahInt = Integer.parseInt(jumlah);
            int total = hargaInt * jumlahInt;

            DefaultTableModel model = (DefaultTableModel) table_pembelian.getModel();
            model.addRow(new Object[]{id, nama, harga, jumlah, total});
            table_pembelian.setModel(model);
            txt_totalharga.setText(String.valueOf(hitungTotalHarga()));
            refresh();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa bilangan bulat", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restartActionPerformed
        refresh();
    }//GEN-LAST:event_btn_restartActionPerformed

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

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        simpan();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        refresh();
        datatablepembelian();
        txt_totalharga.setText(null);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_hapusjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusjualActionPerformed
        int selectedRow = table_pembelian.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table_pembelian.getModel();
        model.removeRow(selectedRow);
        txt_totalharga.setText(String.valueOf(hitungTotalHarga()));
    }//GEN-LAST:event_btn_hapusjualActionPerformed

    private void btn_profilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_profilActionPerformed
        this.dispose();
        profil profil = new profil("id_admin");
        profil.setVisible(true);
    }//GEN-LAST:event_btn_profilActionPerformed

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void table_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_produkMouseClicked
        int baris = table_produk.getSelectedRow();
        if (baris != -1) {
            String id = (String) table_produk.getValueAt(baris, 0);
            String nama = (String) table_produk.getValueAt(baris, 1);
            String harga = (String) table_produk.getValueAt(baris, 2);

            txt_id.setText(id);
            txt_nama.setText(nama);
            txt_harga.setText(harga);
        }
    }//GEN-LAST:event_table_produkMouseClicked

    private void table_pembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pembelianMouseClicked
    }//GEN-LAST:event_table_pembelianMouseClicked

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
    }//GEN-LAST:event_txt_cariActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        String cari = txt_cari.getText();
        DefaultTableModel searc = new DefaultTableModel();
        searc.setColumnIdentifiers(new Object[]{"ID", "Nama", "Harga", "Stok"});

        for (int i = 0; i < tbl.getRowCount(); i++) {
            String id = tbl.getValueAt(i, 0).toString();
            String nama = tbl.getValueAt(i, 1).toString();
            String harga = tbl.getValueAt(i, 2).toString();
            String stok = tbl.getValueAt(i, 3).toString();

            if (id.contains(cari) || nama.contains(cari) || harga.contains(cari) || stok.contains(cari)) {
                searc.addRow(new Object[]{id, nama, harga, stok});
            }
        }
        table_produk.setModel(searc);
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.dispose();
        transaksi tr = new transaksi();
        tr.setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi_beli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_hapusjual;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_member;
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_profil;
    private javax.swing.JButton btn_restart;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_supplier;
    private javax.swing.JButton btn_x;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_pembelian;
    private javax.swing.JTable table_produk;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_id;
    private javax.swing.JComboBox<String> txt_jumlah;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_totalharga;
    // End of variables declaration//GEN-END:variables
}
