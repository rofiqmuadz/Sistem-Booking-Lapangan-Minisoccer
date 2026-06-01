/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import database.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */

public class RegisterController {

    private Connection conn;

    public RegisterController() {
        conn = Koneksi.getConnection();
    }

    // CEK EMAIL SUDAH ADA ATAU BELUM
    public boolean cekEmail(String email) {
        String query = "SELECT * FROM users WHERE email=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true; // email sudah dipakai
            }

        } catch (Exception e) {
            System.out.println("Error cek email: " + e.getMessage());
        }

        return false;
    }

    // REGISTER USER
    public boolean register(String nama, String email, String password, String noHp) {

        // cek email dulu
        if (cekEmail(email)) {
            System.out.println("Email sudah terdaftar!");
            return false;
        }

        String query = "INSERT INTO users (nama, email, password, no_hp, role) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nama);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, noHp);
            ps.setString(5, "customer"); // default role

            ps.executeUpdate();
            System.out.println("Register berhasil!");
            return true;

        } catch (Exception e) {
            System.out.println("Error register: " + e.getMessage());
            return false;
        }
    }

    
}
