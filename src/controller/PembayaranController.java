/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Pembayaran;
import database.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */

public class PembayaranController {

    private Connection conn;

    public PembayaranController() {
        conn = Koneksi.getConnection();
    }

    // TAMBAH PEMBAYARAN
    public boolean tambahPembayaran(Pembayaran pembayaran) {
        String query = "INSERT INTO pembayaran (id_booking, metode_pembayaran, tanggal_pembayaran, jumlah_bayar, status_pembayaran) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pembayaran.getIdBooking());
            ps.setString(2, pembayaran.getMetodePembayaran());
            ps.setTimestamp(3, pembayaran.getTanggalPembayaran());
            ps.setDouble(4, pembayaran.getJumlahBayar());
            ps.setString(5, pembayaran.getStatusPembayaran());

            ps.executeUpdate();
            System.out.println("Pembayaran berhasil ditambahkan");
            return true;

        } catch (Exception e) {
            System.out.println("Error tambah pembayaran: " + e.getMessage());
            return false;
        }
    }

    // UPDATE STATUS PEMBAYARAN
    public boolean updateStatus(int idPembayaran, String status) {
        String query = "UPDATE pembayaran SET status_pembayaran=? WHERE id_pembayaran=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, idPembayaran);

            ps.executeUpdate();
            System.out.println("Status pembayaran berhasil diupdate");
            return true;

        } catch (Exception e) {
            System.out.println("Error update status pembayaran: " + e.getMessage());
            return false;
        }
    }

    // AMBIL PEMBAYARAN BERDASARKAN BOOKING
    public void getPembayaranByBooking(int idBooking) {
        String query = "SELECT * FROM pembayaran WHERE id_booking=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idBooking);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id_pembayaran") +
                        " | Metode: " + rs.getString("metode_pembayaran") +
                        " | Jumlah: " + rs.getDouble("jumlah_bayar") +
                        " | Status: " + rs.getString("status_pembayaran")
                );
            }

        } catch (Exception e) {
            System.out.println("Error ambil pembayaran: " + e.getMessage());
        }
    }

    // KONFIRMASI PEMBAYARAN (langsung lunas + update booking)
    public boolean konfirmasiPembayaran(int idPembayaran) {
        try {
            // update pembayaran jadi lunas
            String query1 = "UPDATE pembayaran SET status_pembayaran='lunas' WHERE id_pembayaran=?";
            PreparedStatement ps1 = conn.prepareStatement(query1);
            ps1.setInt(1, idPembayaran);
            ps1.executeUpdate();

            // ambil id_booking
            String query2 = "SELECT id_booking FROM pembayaran WHERE id_pembayaran=?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setInt(1, idPembayaran);
            ResultSet rs = ps2.executeQuery();

            if (rs.next()) {
                int idBooking = rs.getInt("id_booking");

                // update status booking jadi dibayar
                String query3 = "UPDATE booking SET status_booking='dibayar' WHERE id_booking=?";
                PreparedStatement ps3 = conn.prepareStatement(query3);
                ps3.setInt(1, idBooking);
                ps3.executeUpdate();
            }

            System.out.println("Pembayaran dikonfirmasi");
            return true;

        } catch (Exception e) {
            System.out.println("Error konfirmasi pembayaran: " + e.getMessage());
            return false;
        }
    }
}
