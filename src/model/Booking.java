/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Lenovo
 */
public class Booking {

    private int idBooking;
    private int idUser;
    private int idLapangan;
    private Date tanggalBooking;
    private Time jamMulai;
    private Time jamSelesai;
    private double totalHarga;
    private String statusBooking;

    // Constructor kosong
    public Booking() {
    }

    // Constructor lengkap
    public Booking(int idBooking, int idUser, int idLapangan,
                   Date tanggalBooking, Time jamMulai, Time jamSelesai,
                   double totalHarga, String statusBooking) {
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.idLapangan = idLapangan;
        this.tanggalBooking = tanggalBooking;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.totalHarga = totalHarga;
        this.statusBooking = statusBooking;
    }

    // Getter & Setter
    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(int idLapangan) {
        this.idLapangan = idLapangan;
    }

    public Date getTanggalBooking() {
        return tanggalBooking;
    }

    public void setTanggalBooking(Date tanggalBooking) {
        this.tanggalBooking = tanggalBooking;
    }

    public Time getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Time jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Time getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Time jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getStatusBooking() {
        return statusBooking;
    }

    public void setStatusBooking(String statusBooking) {
        this.statusBooking = statusBooking;
    }

    // Method tambahan (opsional)
    public long hitungDurasiJam() {
        long selisih = jamSelesai.getTime() - jamMulai.getTime();
        return selisih / (1000 * 60 * 60); // konversi ke jam
    }

    @Override
    public String toString() {
        return "Booking ID: " + idBooking +
               " | Tanggal: " + tanggalBooking +
               " | Jam: " + jamMulai + " - " + jamSelesai +
               " | Status: " + statusBooking;
    }
}
