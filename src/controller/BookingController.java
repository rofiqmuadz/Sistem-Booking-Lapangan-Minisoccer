/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Booking;
import database.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */

public class BookingController {

    private Connection conn;

    public BookingController() {
        conn = Koneksi.getConnection();
    }

    // ✅ INSERT BOOKING
    public boolean tambahBooking(Booking booking) {
        String query = "INSERT INTO booking (id_user, id_lapangan, tanggal_booking, jam_mulai, jam_selesai, total_harga, status_booking) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // cek bentrok dulu
            if (cekBentrok(booking)) {
                System.out.println("Jadwal bentrok!");
                return false;
            }

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, booking.getIdUser());
            ps.setInt(2, booking.getIdLapangan());
            ps.setDate(3, booking.getTanggalBooking());
            ps.setTime(4, booking.getJamMulai());
            ps.setTime(5, booking.getJamSelesai());
            ps.setDouble(6, booking.getTotalHarga());
            ps.setString(7, booking.getStatusBooking());

            ps.executeUpdate();
            System.out.println("Booking berhasil ditambahkan");
            return true;

        } catch (Exception e) {
            System.out.println("Error tambah booking: " + e.getMessage());
            return false;
        }
    }

    // ✅ CEK BENTROK JADWAL
public boolean cekBentrok(Booking booking) {

    String query = "SELECT * FROM booking " +
            "WHERE id_lapangan=? AND tanggal_booking=? " +
            "AND NOT (jam_selesai <= ? OR jam_mulai >= ?)";

    try {
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, booking.getIdLapangan());
        ps.setDate(2, booking.getTanggalBooking());
        ps.setTime(3, booking.getJamMulai());
        ps.setTime(4, booking.getJamSelesai());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return false;
}

    // ✅ GET BOOKING BY USER
    public void getBookingByUser(int idUser) {
        String query = "SELECT * FROM booking WHERE id_user = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idUser);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_booking") +
                        " | Tanggal: " + rs.getDate("tanggal_booking") +
                        " | Jam: " + rs.getTime("jam_mulai") + "-" + rs.getTime("jam_selesai") +
                        " | Status: " + rs.getString("status_booking"));
            }

        } catch (Exception e) {
            System.out.println("Error ambil data booking: " + e.getMessage());
        }
    }

    // ✅ UPDATE STATUS BOOKING
    public boolean updateStatus(int idBooking, String status) {
        String query = "UPDATE booking SET status_booking = ? WHERE id_booking = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, idBooking);

            ps.executeUpdate();
            System.out.println("Status berhasil diupdate");
            return true;

        } catch (Exception e) {
            System.out.println("Error update status: " + e.getMessage());
            return false;
        }
    }

    // ✅ DELETE BOOKING
    public boolean hapusBooking(int idBooking) {
        String query = "DELETE FROM booking WHERE id_booking = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idBooking);

            ps.executeUpdate();
            System.out.println("Booking berhasil dihapus");
            return true;

        } catch (Exception e) {
            System.out.println("Error hapus booking: " + e.getMessage());
            return false;
        }
    }
}