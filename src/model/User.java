/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class User {
    protected int idUser;
    protected String nama;
    protected String email;
    protected String password;
    protected String noHp;
    protected String role;

    // Constructor kosong
    public User() {}

    // Constructor lengkap
    public User(int idUser, String nama, String email, String password, String noHp, String role) {
        this.idUser = idUser;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.noHp = noHp;
        this.role = role;
    }

    // Getter & Setter
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}