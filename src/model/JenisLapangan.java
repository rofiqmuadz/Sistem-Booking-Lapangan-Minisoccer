/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class JenisLapangan {

    private int idJenis;
    private String namaJenis;

    // Constructor kosong
    public JenisLapangan() {
    }

    // Constructor lengkap
    public JenisLapangan(int idJenis, String namaJenis) {
        this.idJenis = idJenis;
        this.namaJenis = namaJenis;
    }

    // Getter & Setter
    public int getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(int idJenis) {
        this.idJenis = idJenis;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    // Method tambahan (opsional)
    @Override
    public String toString() {
        return namaJenis;
    }
}
