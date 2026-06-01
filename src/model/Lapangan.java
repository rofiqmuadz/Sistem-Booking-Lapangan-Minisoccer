/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */

public class Lapangan {

    private int idLapangan;
    private String namaLapangan;
    private int idJenis;
    private double hargaPerJam;
    private String statusLapangan;

    // Constructor kosong
    public Lapangan() {
    }

    // Constructor lengkap
    public Lapangan(int idLapangan, String namaLapangan, int idJenis, double hargaPerJam, String statusLapangan) {
        this.idLapangan = idLapangan;
        this.namaLapangan = namaLapangan;
        this.idJenis = idJenis;
        this.hargaPerJam = hargaPerJam;
        this.statusLapangan = statusLapangan;
    }

    // Getter dan Setter
    public int getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(int idLapangan) {
        this.idLapangan = idLapangan;
    }

    public String getNamaLapangan() {
        return namaLapangan;
    }

    public void setNamaLapangan(String namaLapangan) {
        this.namaLapangan = namaLapangan;
    }

    public int getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(int idJenis) {
        this.idJenis = idJenis;
    }

    public double getHargaPerJam() {
        return hargaPerJam;
    }

    public void setHargaPerJam(double hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }

    public String getStatusLapangan() {
        return statusLapangan;
    }

    public void setStatusLapangan(String statusLapangan) {
        this.statusLapangan = statusLapangan;
    }

    // Method tambahan (opsional)
    public String getInfoLapangan() {
        return namaLapangan + " | Harga: " + hargaPerJam + " | Status: " + statusLapangan;
    }

    @Override
    public String toString() {
        return namaLapangan;
    }
}