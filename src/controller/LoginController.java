/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.User;
import model.Admin;
import model.Customer;
import database.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */

public class LoginController {

    private Connection conn;

    public LoginController() {
        conn = Koneksi.getConnection();
    }

    // METHOD LOGIN
    public User login(String email, String password) {

        String query = "SELECT * FROM users WHERE email=? AND password=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_user");
                String nama = rs.getString("nama");
                String noHp = rs.getString("no_hp");
                String role = rs.getString("role");

                // cek role
                if (role.equalsIgnoreCase("admin")) {
                    return new Admin(id, nama, email, password, noHp);
                } else {
                    return new Customer(id, nama, email, password, noHp);
                }
            }

        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
        }

        return null; // login gagal
    }
}
