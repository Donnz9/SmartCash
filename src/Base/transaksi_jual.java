/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author donih
 */
public class transaksi_jual extends javax.swing.JFrame {

    private String id_admin;
    login objek = new login();
    private static final Logger logger = Logger.getLogger(profil.class.getName());

    public transaksi_jual() {
        initComponents();
        datatableproduk();
        datatablepenjualan();
        hari();
    }

    private void datatableproduk() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("Harga");
        tbl.addColumn("Stok");
        try {
            Statement statement = (Statement) konek.getConnection().createStatement();
            String query = "select * from produk WHERE jumlah <> 0;";
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

    private void datatablepenjualan() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Produk");
        tbl.addColumn("Nama");
        tbl.addColumn("Harga");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Total Harga");
        table_penjualan.setModel(tbl);
    }

    private void simpan() {
        String uang = txt_uang.getText();
        String member = txt_member.getText();
        String diskon = (String) txt_diskon.getSelectedItem();
        int totalSemua = Integer.parseInt(txt_totalharga.getText());
        id_admin = objek.id_admin;
        if (uang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi dulu semua datanya !!!", "PERINGATAN", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            DefaultTableModel model = (DefaultTableModel) table_penjualan.getModel();
            int rowCount = model.getRowCount();
            connection = konek.getConnection();
            statement = connection.createStatement();

            String id_penjualan = generatePenjualanID();
            String query_penjualan;
            if (!member.isEmpty()) {
                // Jika member tidak kosong
                query_penjualan = "INSERT INTO penjualan (id_penjualan, tgl_penjualan, total, id_pelanggan) "
                        + "VALUES ('" + id_penjualan + "', current_timestamp(), '" + totalSemua + "', '" + member + "')";
            } else {
                // Jika member kosong, maka gunakan nilai null atau default
                query_penjualan = "INSERT INTO penjualan (id_penjualan, tgl_penjualan, total) "
                        + "VALUES ('" + id_penjualan + "', current_timestamp(), '" + totalSemua + "')";
            }
            statement.executeUpdate(query_penjualan);
            // maximum ID penjualan
            String max_id_penjualan = "SELECT MAX(id_penjualan) AS max_id FROM penjualan;";
            rs = statement.executeQuery(max_id_penjualan);
            String max_id = null;
            if (rs.next()) {
                max_id = rs.getString("max_id");
            }
            rs.close();

            String id_transaksi = generateTransaksiID();
            String QueryTransaksi = "INSERT INTO transaksi (id_transaksi, tgl_transaksi, total, id_penjualan, id_admin) "
                    + "VALUES ('" + id_transaksi + "', current_timestamp(), " + totalSemua + ", '" + max_id + "', '" + id_admin + "')";
            statement.executeUpdate(QueryTransaksi);

            String diskonValue = diskon;
            for (int i = 0; i < rowCount; i++) {
                String id_produk = model.getValueAt(i, 0).toString();
                String harga = model.getValueAt(i, 2).toString();
                String jumlah = model.getValueAt(i, 3).toString();
                String subtotalStr = model.getValueAt(i, 4).toString();

                // Konversi nilai subtotal ke tipe numerik
                double subtotalValue = Double.parseDouble(subtotalStr);

                // Hitung subtotal setelah dip;skon
                double diskonAmount = 0.0;
                double subtotalAfterDiscount = subtotalValue - diskonAmount;

                if (diskon != null && !diskon.isEmpty()) {
                    // Hitung diskon hanya jika diskonValue tidak kosong
                    double diskonPercentage = Double.parseDouble(diskon.replace("%", ""));
                    diskonAmount = subtotalValue * (diskonPercentage / 100.0);
                    subtotalAfterDiscount = subtotalValue - diskonAmount;
                }
                String query = "INSERT INTO detail_penjualan (jumlah, harga, diskon, subtotal, id_penjualan, id_produk)  "
                        + "VALUES (" + jumlah + ", " + harga + ", '" + diskonValue + "', " + subtotalAfterDiscount + ", '" + id_penjualan + "', '" + id_produk + "')";
                statement.executeUpdate(query);
            }
            JOptionPane.showMessageDialog(null, "Data penjualan berhasil disimpan ke database.");
            datatablepenjualan();
            datatableproduk();
            txt_diskon.setSelectedItem(null);
            txt_kembalian.setText(null);
            txt_member.setText(null);
            txt_totalharga.setText(null);
            txt_uang.setText(null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menyimpan ke database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generatePenjualanID() {
        try {
            Statement statement = konek.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id_penjualan, 2) AS SIGNED)) AS max_id_penjualan FROM penjualan;");
            if (rs.next()) {
                int maxId = rs.getInt("max_id_penjualan");
                return String.format("J%03d", maxId + 1);
            } else {
                return "J001"; // Jika tidak ada produk di database
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
                return "T001"; // Jika tidak ada produk di database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void refresh() {
        txt_id.setText(null);
        txt_nama.setText(null);
        txt_harga.setText(null);
        txt_jumlah.setSelectedItem("1");
        datatableproduk();
    }

    private void RefreshSemua() {
        refresh();
        datatablepenjualan();
        txt_diskon.setSelectedItem(null);
        txt_kembalian.setText(null);
        txt_member.setText(null);
        txt_totalharga.setText(null);
        txt_uang.setText(null);
    }

    private void hari() {
        Date tgl = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
        String tanggalFormatted = formatter.format(tgl);
        tanggal.setText(tanggalFormatted);
    }

    private void cekBarcode() {
        String barcode = txt_id.getText();
        try {
            String sql = "SELECT nama_produk, harga FROM produk WHERE id_produk = '" + barcode + "' AND jumlah <> 0";
            Connection c = konek.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet r = ps.executeQuery();
            if (r.next()) {
                String namaProduk = r.getString("nama_produk");
                String hargaProduk = r.getString("harga");
                txt_nama.setText(namaProduk);
                txt_harga.setText(hargaProduk);
            } else {
                JOptionPane.showMessageDialog(this, "Produk dengan ID tersebut tidak ditemukan / kosong.");
                refresh();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String hitungTotalHarga() {
        double totalHarga = 0;  // Variabel untuk menyimpan total harga
        DefaultTableModel model = (DefaultTableModel) table_penjualan.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {     // Looping melalui setiap baris dalam tabel penjualan
            double harga = Double.parseDouble(model.getValueAt(i, 2).toString());   // Mengambil harga dan jumlah dari setiap baris, kemudian mengonversi ke tipe double dan int
            int jumlah = Integer.parseInt(model.getValueAt(i, 3).toString());
            totalHarga += harga * jumlah;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");    // memformat total harga dengan dua angka desimal
        return decimalFormat.format(totalHarga);    // total harga yang diformat sebagai string
//        return formatUang(totalHarga);
    }

    private void hitungKembalian() {
        try {   // Mengambil nilai uang yang diberikan oleh pelanggan dan total harga
            int uang = Integer.parseInt(txt_uang.getText());
            int totalHarga = Integer.parseInt(txt_totalharga.getText());
            int kembalian = uang - totalHarga;

            txt_kembalian.setText(String.valueOf(kembalian));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Uang harus terisi berupa angka", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cekMember(String memberId) {
        try {
            String sql = "SELECT * FROM pelanggan WHERE id_pelanggan = ? ";
            Connection c = konek.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, memberId);
            ResultSet r = ps.executeQuery();
            if (r.next()) {
                // NK member ID lolos seleksi
                double totalHarga = Double.parseDouble(txt_totalharga.getText());
                if (totalHarga >= 1000000) {
                    txt_diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"10%"}));
                } else if (totalHarga >= 500000) {
                    txt_diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"5%"}));
                } else if (totalHarga >= 200000) {
                    txt_diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"3%"}));
                } else {
                    txt_diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Member kososng", "Peringatan", JOptionPane.WARNING_MESSAGE);
                txt_diskon.setSelectedItem(null);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private double totalHargaSebelumDiskon = 0.0;

    private void hitungTotalSetelahDiskon() {
        try {   // Mengambil nilai diskon dari ComboBox txt_diskon
            String diskonStr = (String) txt_diskon.getSelectedItem();
            double diskon = 0;
            if (diskonStr != null) {    // Menetapkan nilai diskon berdasarkan opsi yang dipilih
                if (diskonStr.equals("3%")) {
                    diskon = 0.03;
                } else if (diskonStr.equals("5%")) {
                    diskon = 0.05;
                } else if (diskonStr.equals("10%")) {
                    diskon = 0.1;
                }
            } else {    // Jika opsi diskon tidak dipilih, set total harga kembali ke total harga sebelum diskon
                txt_totalharga.setText(String.format("%.0f", totalHargaSebelumDiskon));
                return;
            }
            double totalHarga = Double.parseDouble(txt_totalharga.getText());
            totalHargaSebelumDiskon = totalHarga;
            double totalSetelahDiskon = totalHarga - (totalHarga * diskon);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");    // memformat total harga setelah diskon dengan dua angka desimal
            String totalSetelahDiskonStr = decimalFormat.format(totalSetelahDiskon);
            txt_totalharga.setText(totalSetelahDiskonStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan dalam menghitung total harga setelah diskon.", "Error", JOptionPane.ERROR_MESSAGE);
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

        btn_dashboard = new javax.swing.JButton();
        btn_produk = new javax.swing.JButton();
        btn_supplier = new javax.swing.JButton();
        btn_member = new javax.swing.JButton();
        txt_diskon = new javax.swing.JComboBox<>();
        btn_logout = new javax.swing.JButton();
        btn_profil = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_produk = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_penjualan = new javax.swing.JTable();
        btn_restart = new javax.swing.JButton();
        txt_harga = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JComboBox<>();
        txt_totalharga = new javax.swing.JTextField();
        txt_kembalian = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_uang = new javax.swing.JTextField();
        txt_member = new javax.swing.JTextField();
        btn_hapusjual = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        tanggal = new javax.swing.JLabel();
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

        txt_diskon.setBackground(new java.awt.Color(255, 255, 255));
        txt_diskon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_diskon.setForeground(new java.awt.Color(0, 0, 0));
        txt_diskon.setBorder(null);
        txt_diskon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_diskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diskonActionPerformed(evt);
            }
        });
        getContentPane().add(txt_diskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 642, 195, 26));

        btn_logout.setBorder(null);
        btn_logout.setContentAreaFilled(false);
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });
        getContentPane().add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 70, 25));

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

        btn_kembali.setBorder(null);
        btn_kembali.setContentAreaFilled(false);
        btn_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 25, 50, 30));

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

        table_penjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        table_penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_penjualanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_penjualan);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 300, 490, 250));

        btn_restart.setBorder(null);
        btn_restart.setContentAreaFilled(false);
        btn_restart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restartActionPerformed(evt);
            }
        });
        getContentPane().add(btn_restart, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 217, 30, 30));

        txt_harga.setEditable(false);
        txt_harga.setBackground(new java.awt.Color(204, 204, 204));
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

        txt_totalharga.setEditable(false);
        txt_totalharga.setBackground(new java.awt.Color(204, 204, 204));
        txt_totalharga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_totalharga.setForeground(new java.awt.Color(0, 0, 0));
        txt_totalharga.setBorder(null);
        getContentPane().add(txt_totalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 602, 180, 25));

        txt_kembalian.setEditable(false);
        txt_kembalian.setBackground(new java.awt.Color(204, 204, 204));
        txt_kembalian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_kembalian.setForeground(new java.awt.Color(0, 0, 0));
        txt_kembalian.setBorder(null);
        getContentPane().add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 682, 180, 25));

        txt_nama.setEditable(false);
        txt_nama.setBackground(new java.awt.Color(204, 204, 204));
        txt_nama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_nama.setForeground(new java.awt.Color(0, 0, 0));
        txt_nama.setBorder(null);
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 138, 180, 25));

        txt_id.setBackground(new java.awt.Color(255, 255, 255));
        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 0, 0));
        txt_id.setBorder(null);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idKeyReleased(evt);
            }
        });
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 97, 180, 25));

        txt_uang.setBackground(new java.awt.Color(255, 255, 255));
        txt_uang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_uang.setForeground(new java.awt.Color(0, 0, 0));
        txt_uang.setBorder(null);
        txt_uang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_uangKeyPressed(evt);
            }
        });
        getContentPane().add(txt_uang, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 642, 180, 25));

        txt_member.setBackground(new java.awt.Color(255, 255, 255));
        txt_member.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_member.setForeground(new java.awt.Color(0, 0, 0));
        txt_member.setBorder(null);
        txt_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_memberActionPerformed(evt);
            }
        });
        txt_member.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_memberKeyPressed(evt);
            }
        });
        getContentPane().add(txt_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 602, 180, 25));

        btn_hapusjual.setBorder(null);
        btn_hapusjual.setContentAreaFilled(false);
        btn_hapusjual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapusjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusjualActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapusjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1147, 555, 110, 30));

        btn_simpan.setBorder(null);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 679, 110, 30));

        btn_cetak.setBorder(null);
        btn_cetak.setContentAreaFilled(false);
        btn_cetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(1032, 679, 110, 30));

        btn_batal.setBorder(null);
        btn_batal.setContentAreaFilled(false);
        btn_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1147, 679, 110, 30));

        btn_insert.setBorder(null);
        btn_insert.setContentAreaFilled(false);
        btn_insert.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });
        getContentPane().add(btn_insert, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 217, 110, 30));

        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setForeground(new java.awt.Color(0, 0, 0));
        tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tanggal.setText("jLabel2");
        tanggal.setToolTipText("");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1045, 96, 210, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Transaksi jual.png"))); // NOI18N
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

    private void btn_profilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_profilActionPerformed
        this.dispose();
        profil profil = new profil("id_admin");
        profil.setVisible(true);
    }//GEN-LAST:event_btn_profilActionPerformed

    private void btn_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_xActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.dispose();
        transaksi tr = new transaksi();
        tr.setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void table_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_produkMouseClicked
        int baris = table_produk.getSelectedRow();
        if (baris != -1) {
            String id = (String) table_produk.getValueAt(baris, 0);
            String nama = (String) table_produk.getValueAt(baris, 1);
            String harga = (String) table_produk.getValueAt(baris, 2);
            String stok = (String) table_produk.getValueAt(baris, 3);

            txt_id.setText(id);
            txt_nama.setText(nama);
            txt_harga.setText(harga);

            // Set model untuk txt_jumlah
            txt_jumlah.removeAllItems();  // Menghapus item yang ada sebelumnya
            int jumlahStok = Integer.parseInt(stok);
            for (int i = 1; i <= jumlahStok; i++) {
                txt_jumlah.addItem(String.valueOf(i));
            }
        }
    }//GEN-LAST:event_table_produkMouseClicked

    private void table_penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_penjualanMouseClicked
    }//GEN-LAST:event_table_penjualanMouseClicked

    private void btn_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restartActionPerformed
        refresh();
    }//GEN-LAST:event_btn_restartActionPerformed

    private void btn_hapusjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusjualActionPerformed
        int selectedRow = table_penjualan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table_penjualan.getModel();
        String id = (String) model.getValueAt(selectedRow, 0);
        String jumlah = (String) model.getValueAt(selectedRow, 3);
        try {
            int jumlahInt = Integer.parseInt(jumlah);

            // Tambahkan kembali stok ke database
            Connection connection = konek.getConnection();
            String updateStokQuery = "UPDATE produk SET jumlah = jumlah + ? WHERE id_produk = ?";
            PreparedStatement ps = connection.prepareStatement(updateStokQuery);
            ps.setInt(1, jumlahInt);
            ps.setString(2, id);
            int updatedRows = ps.executeUpdate();

            if (updatedRows > 0) {
                // Hapus baris dari table_penjualan
                model.removeRow(selectedRow);
                txt_totalharga.setText(String.valueOf(hitungTotalHarga()));
                datatableproduk();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambah stok produk", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa bilangan bulat", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(transaksi_jual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_hapusjualActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        simpan();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
        String diskon = (String) txt_diskon.getSelectedItem();
        if (diskon == null) {
            diskon = "-";
        }
        String member = txt_member.getText();
        if (member == null || member.isEmpty()) {
            member = "-";
        }
        String bayar = txt_uang.getText();
        String kembalian = txt_kembalian.getText();
        String totalHargaAwal = hitungTotalHarga();
        id_admin = objek.id_admin;
        try {
            simpan();
            String report = ("src\\Jasper\\Struk.jrxml");
            Connection conn = konek.getConnection();

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("parameterDiskon", diskon);
            parameters.put("parameterBayar", bayar);
            parameters.put("parameterKembalian", kembalian);
            parameters.put("parameterTotalHargaAwal", totalHargaAwal);
            parameters.put("parameterAdmin", id_admin);
            parameters.put("parameterMember", member);

            JasperReport JRpt = JasperCompileManager.compileReport(report);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, parameters, conn);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        RefreshSemua();
    }//GEN-LAST:event_btn_batalActionPerformed

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

            // Kurangi stok di database
            Connection connection = konek.getConnection();
            String updateStokQuery = "UPDATE produk SET jumlah = jumlah - ? WHERE id_produk = ?";
            PreparedStatement ps = connection.prepareStatement(updateStokQuery);
            ps.setInt(1, jumlahInt);
            ps.setString(2, id);
            int updatedRows = ps.executeUpdate();

            if (updatedRows > 0) {
                DefaultTableModel model = (DefaultTableModel) table_penjualan.getModel();
                model.addRow(new Object[]{id, nama, harga, jumlah, total});
                table_penjualan.setModel(model);
                txt_totalharga.setText(String.valueOf(hitungTotalHarga()));
                refresh();
                datatableproduk();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengurangi stok produk", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa bilangan bulat", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(transaksi_jual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_insertActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cekBarcode();
        }
    }//GEN-LAST:event_txt_idKeyPressed

    private void txt_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyReleased

    }//GEN-LAST:event_txt_idKeyReleased

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_uangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hitungKembalian();
        }
    }//GEN-LAST:event_txt_uangKeyPressed

    private String lastSelectedDiscount = null;
    private void txt_diskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diskonActionPerformed
        String currentSelectedDiscount = (String) txt_diskon.getSelectedItem();     // Mendapatkan opsi diskon yang saat ini dipilih dari ComboBox txt_diskon
        if (currentSelectedDiscount != null && !currentSelectedDiscount.equals(lastSelectedDiscount)) {     // Jika opsi diskon saat ini tidak null dan berbeda dengan opsi diskon terakhir,
            hitungTotalSetelahDiskon();
            lastSelectedDiscount = currentSelectedDiscount;
        }
    }//GEN-LAST:event_txt_diskonActionPerformed

    private void txt_memberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_memberKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cekMember(txt_member.getText());
        }
    }//GEN-LAST:event_txt_memberKeyPressed

    private void txt_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberActionPerformed
    }//GEN-LAST:event_txt_memberActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi_jual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cetak;
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
    private javax.swing.JTable table_penjualan;
    private javax.swing.JTable table_produk;
    private javax.swing.JLabel tanggal;
    private javax.swing.JComboBox<String> txt_diskon;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_id;
    private javax.swing.JComboBox<String> txt_jumlah;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_member;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_totalharga;
    private javax.swing.JTextField txt_uang;
    // End of variables declaration//GEN-END:variables
}
