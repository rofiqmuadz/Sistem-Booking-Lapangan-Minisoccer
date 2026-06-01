package view;

import database.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBookingView extends JFrame {

    JTable tableBooking;

    DefaultTableModel model;

    JButton btnRefresh;
    JButton btnKembali;

    public DataBookingView() {

        setTitle("Data Booking Customer");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(
                new Color(248,250,252)
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel("DATA BOOKING CUSTOMER");

        title.setBounds(40,20,500,40);

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        add(title);

        // ================= TABLE =================

        String[] kolom = {

                "ID Booking",
                "Customer",
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

        scroll.setBounds(40,100,1100,450);

        add(scroll);

        // ================= BUTTON =================

        btnRefresh =
                new JButton("REFRESH");

        btnRefresh.setBounds(40,580,120,40);

        btnRefresh.setBackground(
                new Color(37,99,235)
        );

        btnRefresh.setForeground(Color.WHITE);

        btnKembali =
                new JButton("KEMBALI");

        btnKembali.setBounds(1020,580,120,40);

        btnKembali.setBackground(
                new Color(30,41,59)
        );

        btnKembali.setForeground(Color.WHITE);

        add(btnRefresh);

        add(btnKembali);

        // ================= LOAD TABLE =================

        loadTable();

        // ================= ACTION =================

        btnRefresh.addActionListener(e -> {

            loadTable();
        });

        btnKembali.addActionListener(e -> {

            new DashboardAdminView()
                    .setVisible(true);

            dispose();
        });
    }

    // ================= LOAD TABLE =================

    private void loadTable(){

        model.setRowCount(0);

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =

                    "SELECT b.*, " +
                    "u.nama, " +
                    "l.nama_lapangan " +

                    "FROM booking b " +

                    "JOIN users u " +
                    "ON b.id_user=u.id_user " +

                    "JOIN lapangan l " +
                    "ON b.id_lapangan=l.id_lapangan";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("id_booking"),

                        rs.getString("nama"),

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
}