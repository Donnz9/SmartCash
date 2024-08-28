package Base;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author donih
 */
public class PasswordManager {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");    // Mendapatkan instance dari MessageDigest dengan algoritma SHA-256
            byte[] hashInBytes = md.digest(password.getBytes());          // Mengubah password menjadi byte array dan menghitung hash-nya
            
            StringBuilder sb = new StringBuilder();                            // Mengonversi byte array menjadi string hexadecimal
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            
            return null;
        }
    }
}
