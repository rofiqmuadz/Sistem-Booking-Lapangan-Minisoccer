/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Lapangan;
import database.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */

public class LapanganController {

    private Connection conn;

    public LapanganController() {
        conn = Koneksi.getConnection();
    }

    // TAMBAH LAPANGAN
    public boolean tambahLapangan(Lapangan lapangan) {
        String query = "INSERT INTO lapangan (nama_lapangan, id_jenis, harga_per_jam, status_lapangan) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lapangan.getNamaLapangan());
            ps.setInt(2, lapangan.getIdJenis());
            ps.setDouble(3, lapangan.getHargaPerJam());
            ps.setString(4, lapangan.getStatusLapangan());

            ps.executeUpdate();
            System.out.println("Lapangan berhasil ditambahkan");
            return true;

        } catch (Exception e) {
            System.out.println("Error tambah lapangan: " + e.getMessage());
            return false;
        }
    }

    // TAMPILKAN SEMUA LAPANGAN
    public void getAllLapangan() {
        String query = "SELECT * FROM lapangan";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id_lapangan") +
                        " | Nama: " + rs.getString("nama_lapangan") +
                        " | Jenis: " + rs.getInt("id_jenis") +
                        " | Harga: " + rs.getDouble("harga_per_jam") +
                        " | Status: " + rs.getString("status_lapangan")
                );
            }

        } catch (Exception e) {
            System.out.println("Error ambil data: " + e.getMessage());
        }
    }

    // UPDATE LAPANGAN
    public boolean updateLapangan(Lapangan lapangan) {
        String query = "UPDATE lapangan SET nama_lapangan=?, id_jenis=?, harga_per_jam=?, status_lapangan=? WHERE id_lapangan=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lapangan.getNamaLapangan());
            ps.setInt(2, lapangan.getIdJenis());
            ps.setDouble(3, lapangan.getHargaPerJam());
            ps.setString(4, lapangan.getStatusLapangan());
            ps.setInt(5, lapangan.getIdLapangan());

            ps.executeUpdate();
            System.out.println("Lapangan berhasil diupdate");
            return true;

        } catch (Exception e) {
            System.out.println("Error update: " + e.getMessage());
            return false;
        }
    }

    // HAPUS LAPANGAN
    public boolean hapusLapangan(int idLapangan) {
        String query = "DELETE FROM lapangan WHERE id_lapangan=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idLapangan);

            ps.executeUpdate();
            System.out.println("Lapangan berhasil dihapus");
            return true;

        } catch (Exception e) {
            System.out.println("Error hapus: " + e.getMessage());
            return false;
        }
    }

    // CARI LAPANGAN BERDASARKAN ID
    public Lapangan getLapanganById(int idLapangan) {
        String query = "SELECT * FROM lapangan WHERE id_lapangan=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idLapangan);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Lapangan(
                        rs.getInt("id_lapangan"),
                        rs.getString("nama_lapangan"),
                        rs.getInt("id_jenis"),
                        rs.getDouble("harga_per_jam"),
                        rs.getString("status_lapangan")
                );
            }

        } catch (Exception e) {
            System.out.println("Error cari lapangan: " + e.getMessage());
        }

        return null;
    }
}
