package model;

/**
 *
 * @author Lenovo
 */
public class Admin extends User implements AksesUser {

    // Constructor kosong
    public Admin() {
    }

    // Constructor dengan parameter
    public Admin(int idUser, String nama, String email,
                 String password, String noHp) {

        super(idUser, nama, email, password, noHp, "admin");
    }

    // Implementasi method dari interface
    @Override
    public void login() {
        System.out.println("Admin berhasil login");
    }

    // Method khusus admin
    public void kelolaLapangan() {
        System.out.println("Admin dapat menambah, mengedit, dan menghapus lapangan");
    }

    public void lihatSemuaBooking() {
        System.out.println("Admin melihat semua data booking");
    }

    public void verifikasiPembayaran() {
        System.out.println("Admin memverifikasi pembayaran");
    }

    @Override
    public String toString() {
        return "Admin: " + nama + " (" + email + ")";
    }
}