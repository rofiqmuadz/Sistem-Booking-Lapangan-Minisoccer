/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.User;
import database.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */

public class UserController {

    private Connection conn;

    public UserController() {
        conn = Koneksi.getConnection();
    }

    // AMBIL USER BERDASARKAN ID
    public User getUserById(int idUser) {
        String query = "SELECT * FROM users WHERE id_user=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idUser);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setNama(rs.getString("nama"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNoHp(rs.getString("no_hp"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (Exception e) {
            System.out.println("Error ambil user: " + e.getMessage());
        }

        return null;
    }

    // TAMPILKAN SEMUA USER
    public void getAllUser() {
        String query = "SELECT * FROM users";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id_user") +
                        " | Nama: " + rs.getString("nama") +
                        " | Email: " + rs.getString("email") +
                        " | Role: " + rs.getString("role")
                );
            }

        } catch (Exception e) {
            System.out.println("Error ambil semua user: " + e.getMessage());
        }
    }

    // UPDATE USER
    public boolean updateUser(User user) {
        String query = "UPDATE users SET nama=?, email=?, password=?, no_hp=?, role=? WHERE id_user=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getNama());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getNoHp());
            ps.setString(5, user.getRole());
            ps.setInt(6, user.getIdUser());

            ps.executeUpdate();
            System.out.println("User berhasil diupdate");
            return true;

        } catch (Exception e) {
            System.out.println("Error update user: " + e.getMessage());
            return false;
        }
    }

    // HAPUS USER
    public boolean hapusUser(int idUser) {
        String query = "DELETE FROM users WHERE id_user=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idUser);

            ps.executeUpdate();
            System.out.println("User berhasil dihapus");
            return true;

        } catch (Exception e) {
            System.out.println("Error hapus user: " + e.getMessage());
            return false;
        }
    }
}
