package view;

import controller.BookingController;
import database.Koneksi;
import model.Booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;

public class BookingView extends JFrame {

    // ================= COMPONENT =================

    JComboBox<String> cbLapangan;

    JComboBox<String> cbJamMulai;
    JComboBox<String> cbJamSelesai;

    JTextField txtTanggal;
    JTextField txtTotal;

    JButton btnBooking;
    JButton btnReset;
    JButton btnKembali;

    JTable tableBooking;

    DefaultTableModel model;

    // ================= VARIABLE =================

    int selectedLapanganId = 0;

    double hargaPerJam = 0;

    public BookingView() {

        setTitle("Booking Lapangan");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(
                new Color(248,250,252)
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel("BOOKING LAPANGAN");

        title.setBounds(40,20,400,40);

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        add(title);

        // ================= FORM PANEL =================

        JPanel formPanel = new JPanel();

        formPanel.setBounds(40,90,350,500);

        formPanel.setLayout(null);

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        // ================= LAPANGAN =================

        JLabel lblLapangan =
                new JLabel("Pilih Lapangan");

        lblLapangan.setBounds(30,30,150,25);

        cbLapangan = new JComboBox<>();

        cbLapangan.setBounds(30,60,280,40);

        loadLapangan();

        // ================= TANGGAL =================

        JLabel lblTanggal =
                new JLabel("Tanggal Booking");

        lblTanggal.setBounds(30,120,150,25);

        txtTanggal = new JTextField();

        txtTanggal.setBounds(30,150,280,40);

        txtTanggal.setToolTipText(
                "Format : YYYY-MM-DD"
        );

        // ================= JAM MULAI =================

        JLabel lblMulai =
                new JLabel("Jam Mulai");

        lblMulai.setBounds(30,210,150,25);

        cbJamMulai =
                new JComboBox<>(new String[]{
                        "08:00:00",
                        "09:00:00",
                        "10:00:00",
                        "11:00:00",
                        "12:00:00",
                        "13:00:00",
                        "14:00:00",
                        "15:00:00",
                        "16:00:00",
                        "17:00:00",
                        "18:00:00",
                        "19:00:00",
                        "20:00:00"
                });

        cbJamMulai.setBounds(30,240,280,40);

        // ================= JAM SELESAI =================

        JLabel lblSelesai =
                new JLabel("Jam Selesai");

        lblSelesai.setBounds(30,300,150,25);

        cbJamSelesai =
                new JComboBox<>(new String[]{
                        "09:00:00",
                        "10:00:00",
                        "11:00:00",
                        "12:00:00",
                        "13:00:00",
                        "14:00:00",
                        "15:00:00",
                        "16:00:00",
                        "17:00:00",
                        "18:00:00",
                        "19:00:00",
                        "20:00:00",
                        "21:00:00"
                });

        cbJamSelesai.setBounds(30,330,280,40);

        // ================= TOTAL =================

        JLabel lblTotal =
                new JLabel("Total Harga");

        lblTotal.setBounds(30,390,150,25);

        txtTotal = new JTextField();

        txtTotal.setBounds(30,420,280,40);

        txtTotal.setEditable(false);

        // ================= ADD FORM =================

        formPanel.add(lblLapangan);
        formPanel.add(cbLapangan);

        formPanel.add(lblTanggal);
        formPanel.add(txtTanggal);

        formPanel.add(lblMulai);
        formPanel.add(cbJamMulai);

        formPanel.add(lblSelesai);
        formPanel.add(cbJamSelesai);

        formPanel.add(lblTotal);
        formPanel.add(txtTotal);

        add(formPanel);

        // ================= BUTTON =================

        btnBooking =
                new JButton("BOOKING");

        btnBooking.setBounds(40,610,150,40);

        btnBooking.setBackground(
                new Color(37,99,235)
        );

        btnBooking.setForeground(Color.WHITE);

        btnReset =
                new JButton("RESET");

        btnReset.setBounds(210,610,150,40);

        btnReset.setBackground(
                new Color(239,68,68)
        );

        btnReset.setForeground(Color.WHITE);

        btnKembali =
                new JButton("KEMBALI");

        btnKembali.setBounds(980,610,150,40);

        btnKembali.setBackground(
                new Color(30,41,59)
        );

        btnKembali.setForeground(Color.WHITE);

        add(btnBooking);

        add(btnReset);

        add(btnKembali);

        // ================= TABLE =================

        String[] kolom = {
                "ID",
                "Lapangan",
                "Tanggal",
                "Jam Mulai",
                "Jam Selesai",
                "Total",
                "Status"
        };

        model =
                new DefaultTableModel(kolom,0);

        tableBooking =
                new JTable(model);

        tableBooking.setRowHeight(30);

        tableBooking.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(tableBooking);

        scroll.setBounds(430,90,700,500);

        add(scroll);

        // ================= LOAD DATA =================

        loadTableBooking();

        // ================= ACTION =================

        cbLapangan.addActionListener(e -> hitungTotal());

        cbJamMulai.addActionListener(e -> hitungTotal());

        cbJamSelesai.addActionListener(e -> hitungTotal());

        btnBooking.addActionListener(e -> tambahBooking());

        btnReset.addActionListener(e -> resetForm());

        btnKembali.addActionListener(e -> {

            new DashboardCustomerView()
                    .setVisible(true);

            dispose();
        });
    }

    // ================= LOAD LAPANGAN =================

    private void loadLapangan(){

        cbLapangan.removeAllItems();

        try {

            Connection conn =
                    Koneksi.getConnection();

            // HANYA LAPANGAN TERSEDIA
            String query =

                    "SELECT * FROM lapangan " +
                    "WHERE status_lapangan='tersedia'";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                cbLapangan.addItem(

                        rs.getInt("id_lapangan")
                                + " - " +

                                rs.getString("nama_lapangan")
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ================= HITUNG TOTAL =================

    private void hitungTotal(){

        try {

            if(cbLapangan.getSelectedItem() == null){

                return;
            }

            String selected =
                    cbLapangan.getSelectedItem()
                            .toString();

            selectedLapanganId =
                    Integer.parseInt(
                            selected.split(" - ")[0]
                    );

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT harga_per_jam " +
                    "FROM lapangan " +
                    "WHERE id_lapangan=?";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, selectedLapanganId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                hargaPerJam =
                        rs.getDouble("harga_per_jam");
            }

            int mulai =
                    cbJamMulai.getSelectedIndex();

            int selesai =
                    cbJamSelesai.getSelectedIndex();

            int durasi =
                    selesai - mulai + 1;

            if(durasi <= 0){

                txtTotal.setText("0");

                return;
            }

            double total =
                    durasi * hargaPerJam;

            DecimalFormat df =
                    new DecimalFormat("#,###");

            txtTotal.setText(
                    "Rp " + df.format(total)
            );

        } catch (Exception e){

            System.out.println(e.getMessage());
        }
    }

    // ================= TAMBAH BOOKING =================

    private void tambahBooking(){

        try {

            if(cbLapangan.getSelectedItem() == null){

                JOptionPane.showMessageDialog(
                        this,
                        "Tidak ada lapangan tersedia!"
                );

                return;
            }

            // sementara hardcode user login
            int idUser = 1;

            Date tanggal =
                    Date.valueOf(
                            txtTanggal.getText()
                    );

            Time jamMulai =
                    Time.valueOf(
                            cbJamMulai
                                    .getSelectedItem()
                                    .toString()
                    );

            Time jamSelesai =
                    Time.valueOf(
                            cbJamSelesai
                                    .getSelectedItem()
                                    .toString()
                    );

            int mulai =
                    cbJamMulai.getSelectedIndex();

            int selesai =
                    cbJamSelesai.getSelectedIndex();

            int durasi =
                    selesai - mulai + 1;

            if(durasi <= 0){

                JOptionPane.showMessageDialog(
                        this,
                        "Jam selesai tidak valid!"
                );

                return;
            }

            double total =
                    durasi * hargaPerJam;

            Booking booking =
                    new Booking();

            booking.setIdUser(idUser);

            booking.setIdLapangan(
                    selectedLapanganId
            );

            booking.setTanggalBooking(tanggal);

            booking.setJamMulai(jamMulai);

            booking.setJamSelesai(jamSelesai);

            booking.setTotalHarga(total);

            booking.setStatusBooking("pending");

            BookingController bc =
                    new BookingController();

            boolean success =
                    bc.tambahBooking(booking);

            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "Booking berhasil!"
                );

                loadTableBooking();

                resetForm();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Jadwal bentrok atau gagal booking!"
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Format tanggal harus YYYY-MM-DD"
            );
        }
    }

    // ================= LOAD TABLE =================

    private void loadTableBooking(){

        model.setRowCount(0);

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =

                    "SELECT b.*, l.nama_lapangan " +

                    "FROM booking b " +

                    "JOIN lapangan l " +

                    "ON b.id_lapangan=l.id_lapangan";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("id_booking"),

                        rs.getString("nama_lapangan"),

                        rs.getDate("tanggal_booking"),

                        rs.getTime("jam_mulai"),

                        rs.getTime("jam_selesai"),

                        rs.getDouble("total_harga"),

                        rs.getString("status_booking")
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

        txtTanggal.setText("");

        txtTotal.setText("");

        if(cbLapangan.getItemCount() > 0){

            cbLapangan.setSelectedIndex(0);
        }

        cbJamMulai.setSelectedIndex(0);

        cbJamSelesai.setSelectedIndex(0);
    }
}