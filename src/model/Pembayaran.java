/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;

/**
 *
 * @author Lenovo
 */
public class Pembayaran {

    private int idPembayaran;
    private int idBooking;
    private String metodePembayaran;
    private Timestamp tanggalPembayaran;
    private double jumlahBayar;
    private String statusPembayaran;

    // Constructor kosong
    public Pembayaran() {
    }

    // Constructor lengkap
    public Pembayaran(int idPembayaran, int idBooking, String metodePembayaran,
                      Timestamp tanggalPembayaran, double jumlahBayar, String statusPembayaran) {
        this.idPembayaran = idPembayaran;
        this.idBooking = idBooking;
        this.metodePembayaran = metodePembayaran;
        this.tanggalPembayaran = tanggalPembayaran;
        this.jumlahBayar = jumlahBayar;
        this.statusPembayaran = statusPembayaran;
    }

    // Getter & Setter
    public int getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(int idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public Timestamp getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(Timestamp tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public double getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(double jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    // Method tambahan (opsional)
    public boolean isLunas() {
        return statusPembayaran.equalsIgnoreCase("lunas");
    }

    @Override
    public String toString() {
        return "Pembayaran ID: " + idPembayaran +
               " | Booking ID: " + idBooking +
               " | Metode: " + metodePembayaran +
               " | Status: " + statusPembayaran;
    }
}
