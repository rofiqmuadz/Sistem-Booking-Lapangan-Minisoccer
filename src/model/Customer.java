package model;

/**
 *
 * @author Lenovo
 */
public class Customer extends User implements AksesUser {

    // Constructor kosong
    public Customer() {
    }

    // Constructor lengkap
    public Customer(int idUser, String nama, String email,
                    String password, String noHp) {

        super(idUser, nama, email, password, noHp, "customer");
    }

    // Implementasi method dari interface
    @Override
    public void login() {
        System.out.println("Customer berhasil login");
    }

    // Method khusus customer
    public void bookingLapangan() {
        System.out.println("Customer melakukan booking lapangan");
    }

    public void lihatRiwayatBooking() {
        System.out.println("Customer melihat riwayat booking");
    }

    public void lakukanPembayaran() {
        System.out.println("Customer melakukan pembayaran");
    }

    @Override
    public String toString() {
        return "Customer: " + nama + " (" + email + ")";
    }
}