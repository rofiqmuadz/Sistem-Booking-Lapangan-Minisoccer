package view;

import controller.PembayaranController;
import database.Koneksi;
import model.Pembayaran;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PembayaranView extends JFrame {

    // ================= COMPONENT =================

    JComboBox<String> cbBooking;
    JComboBox<String> cbMetode;

    JTextField txtJumlah;

    JButton btnBayar;
    JButton btnReset;
    JButton btnKembali;

    JTable tablePembayaran;

    DefaultTableModel model;

    int selectedBookingId = 0;

    public PembayaranView() {

        setTitle("Pembayaran Booking");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(
                new Color(248,250,252)
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel("PEMBAYARAN");

        title.setBounds(40,20,300,40);

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        add(title);

        // ================= FORM PANEL =================

        JPanel formPanel = new JPanel();

        formPanel.setBounds(40,90,350,450);

        formPanel.setLayout(null);

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        // ================= BOOKING =================

        JLabel lblBooking =
                new JLabel("Pilih Booking");

        lblBooking.setBounds(30,30,150,25);

        cbBooking = new JComboBox<>();

        cbBooking.setBounds(30,60,280,40);

        loadBooking();

        // ================= METODE =================

        JLabel lblMetode =
                new JLabel("Metode Pembayaran");

        lblMetode.setBounds(30,130,200,25);

        cbMetode =
                new JComboBox<>(new String[]{
                        "transfer",
                        "cash",
                        "e-wallet"
                });

        cbMetode.setBounds(30,160,280,40);

        // ================= JUMLAH =================

        JLabel lblJumlah =
                new JLabel("Jumlah Bayar");

        lblJumlah.setBounds(30,230,150,25);

        txtJumlah = new JTextField();

        txtJumlah.setBounds(30,260,280,40);

        txtJumlah.setEditable(false);

        // ================= BUTTON =================

        btnBayar =
                new JButton("BAYAR");

        btnBayar.setBounds(30,340,130,40);

        btnBayar.setBackground(
                new Color(37,99,235)
        );

        btnBayar.setForeground(Color.WHITE);

        btnReset =
                new JButton("RESET");

        btnReset.setBounds(180,340,130,40);

        btnReset.setBackground(
                new Color(239,68,68)
        );

        btnReset.setForeground(Color.WHITE);

        // ================= ADD FORM =================

        formPanel.add(lblBooking);
        formPanel.add(cbBooking);

        formPanel.add(lblMetode);
        formPanel.add(cbMetode);

        formPanel.add(lblJumlah);
        formPanel.add(txtJumlah);

        formPanel.add(btnBayar);
        formPanel.add(btnReset);

        add(formPanel);

        // ================= TABLE =================

        String[] kolom = {
                "ID Pembayaran",
                "ID Booking",
                "Metode",
                "Jumlah",
                "Status"
        };

        model = new DefaultTableModel(kolom,0);

        tablePembayaran =
                new JTable(model);

        tablePembayaran.setRowHeight(30);

        tablePembayaran.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(tablePembayaran);

        scroll.setBounds(430,90,700,450);

        add(scroll);

        // ================= BUTTON =================

        btnKembali =
                new JButton("KEMBALI");

        btnKembali.setBounds(1010,570,120,40);

        btnKembali.setBackground(
                new Color(30,41,59)
        );

        btnKembali.setForeground(Color.WHITE);

        add(btnKembali);

        // ================= LOAD DATA =================

        loadTable();

        // ================= ACTION =================

        cbBooking.addActionListener(e -> tampilJumlah());

        btnBayar.addActionListener(e -> bayar());

        btnReset.addActionListener(e -> resetForm());

        btnKembali.addActionListener(e -> {

            new DashboardCustomerView()
                    .setVisible(true);

            dispose();
        });
    }

    // ================= LOAD BOOKING =================

    private void loadBooking(){

        cbBooking.removeAllItems();

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT * FROM booking " +
                    "WHERE status_booking='pending'";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                cbBooking.addItem(

                        rs.getInt("id_booking")
                                + " - " +

                                rs.getDate("tanggal_booking")
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ================= TAMPIL JUMLAH =================

    private void tampilJumlah(){

        try {

            if(cbBooking.getSelectedItem() == null){

                return;
            }

            String selected =
                    cbBooking.getSelectedItem()
                            .toString();

            selectedBookingId =
                    Integer.parseInt(
                            selected.split(" - ")[0]
                    );

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT total_harga " +
                    "FROM booking " +
                    "WHERE id_booking=?";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, selectedBookingId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                txtJumlah.setText(
                        rs.getDouble("total_harga")
                                + ""
                );
            }

        } catch (Exception e){

            System.out.println(e.getMessage());
        }
    }

    // ================= BAYAR =================

    private void bayar(){

        try {

            if(cbBooking.getSelectedItem() == null){

                JOptionPane.showMessageDialog(
                        this,
                        "Tidak ada booking yang perlu dibayar"
                );

                return;
            }

            Pembayaran pembayaran =
                    new Pembayaran();

            pembayaran.setIdBooking(
                    selectedBookingId
            );

            pembayaran.setMetodePembayaran(
                    cbMetode
                            .getSelectedItem()
                            .toString()
            );

            pembayaran.setTanggalPembayaran(
                    new Timestamp(
                            System.currentTimeMillis()
                    )
            );

            pembayaran.setJumlahBayar(
                    Double.parseDouble(
                            txtJumlah.getText()
                    )
            );

            // STATUS LANGSUNG LUNAS
            pembayaran.setStatusPembayaran(
                    "lunas"
            );

            PembayaranController pc =
                    new PembayaranController();

            boolean success =
                    pc.tambahPembayaran(
                            pembayaran
                    );

            if(success){

                // UPDATE STATUS BOOKING MENJADI DIBAYAR
                pc.konfirmasiPembayaran(
                        getLastPembayaranId()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Pembayaran berhasil!"
                );

                loadTable();

                loadBooking();

                resetForm();
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ================= GET LAST PEMBAYARAN =================

    private int getLastPembayaranId(){

        int id = 0;

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT MAX(id_pembayaran) as id " +
                    "FROM pembayaran";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                id = rs.getInt("id");
            }

        } catch (Exception e){

            System.out.println(e.getMessage());
        }

        return id;
    }

    // ================= LOAD TABLE =================

    private void loadTable(){

        model.setRowCount(0);

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT * FROM pembayaran";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("id_pembayaran"),

                        rs.getInt("id_booking"),

                        rs.getString("metode_pembayaran"),

                        rs.getDouble("jumlah_bayar"),

                        rs.getString("status_pembayaran")
                });
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ================= RESET =================

    private void resetForm(){

        txtJumlah.setText("");

        cbMetode.setSelectedIndex(0);

        if(cbBooking.getItemCount() > 0){

            cbBooking.setSelectedIndex(0);
        }
    }
}