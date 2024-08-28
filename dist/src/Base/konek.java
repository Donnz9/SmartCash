/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author donih
 */
public class konek {
    static Connection koneksi;
    
    public static Connection getConnection()throws SQLException{
        String user = "root";
        String password = "";
        new Driver();
        if (koneksi == null){
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/vivace",user,password);
        } return koneksi;
    }
}