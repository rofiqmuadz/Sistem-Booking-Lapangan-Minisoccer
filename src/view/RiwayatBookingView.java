package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RiwayatBookingView extends JFrame {

    JTable tableRiwayat;

    JTextField txtCari;

    JButton btnCari;
    JButton btnRefresh;
    JButton btnKembali;

    public RiwayatBookingView() {

        setTitle("Riwayat Booking");
        setSize(1100,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(
                new Color(248,250,252)
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel("RIWAYAT BOOKING");

        title.setBounds(40,20,400,40);

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        add(title);

        // ================= SEARCH =================

        txtCari = new JTextField();

        txtCari.setBounds(40,90,300,40);

        btnCari =
                new JButton("CARI");

        btnCari.setBounds(360,90,100,40);

        btnCari.setBackground(
                new Color(37,99,235)
        );

        btnCari.setForeground(Color.WHITE);

        btnRefresh =
                new JButton("REFRESH");

        btnRefresh.setBounds(480,90,120,40);

        btnRefresh.setBackground(
                new Color(34,197,94)
        );

        btnRefresh.setForeground(Color.WHITE);

        add(txtCari);
        add(btnCari);
        add(btnRefresh);

        // ================= TABLE =================

        String[] kolom = {
                "ID Booking",
                "Lapangan",
                "Tanggal",
                "Jam",
                "Durasi",
                "Total",
                "Status"
        };

        tableRiwayat =
                new JTable(
                        new DefaultTableModel(
                                kolom,
                                0
                        )
                );

        tableRiwayat.setRowHeight(30);

        tableRiwayat.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        tableRiwayat.getTableHeader().setBackground(
                new Color(37,99,235)
        );

        tableRiwayat.getTableHeader().setForeground(
                Color.WHITE
        );

        JScrollPane scroll =
                new JScrollPane(tableRiwayat);

        scroll.setBounds(40,160,1000,380);

        add(scroll);

        // ================= BUTTON =================

        btnKembali =
                new JButton("KEMBALI");

        btnKembali.setBounds(920,560,120,40);

        btnKembali.setBackground(
                new Color(30,41,59)
        );

        btnKembali.setForeground(Color.WHITE);

        add(btnKembali);

        // ================= ACTION =================

        btnKembali.addActionListener(e -> {

            new DashboardCustomerView()
                    .setVisible(true);

            dispose();
        });
    }
}