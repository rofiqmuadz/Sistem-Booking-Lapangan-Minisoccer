package view;

import javax.swing.*;
import java.awt.*;

public class DashboardCustomerView extends JFrame {

    JButton btnBooking;
    JButton btnPembayaran;
    JButton btnLogout;

    JPanel contentPanel;

    public DashboardCustomerView() {

        setTitle("Dashboard Customer");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(
                new Dimension(250,700)
        );

        sidebar.setBackground(
                new Color(30,41,59)
        );

        sidebar.setLayout(null);

        JLabel title =
                new JLabel("SPORT ARENA");

        title.setBounds(30,40,200,40);

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        // ================= BUTTON =================

        btnBooking =
                menuButton("Booking Lapangan");

        btnBooking.setBounds(20,140,200,45);

        btnPembayaran =
                menuButton("Pembayaran");

        btnPembayaran.setBounds(20,210,200,45);

        btnLogout =
                menuButton("Logout");

        btnLogout.setBounds(20,520,200,45);

        // ================= ADD =================

        sidebar.add(title);

        sidebar.add(btnBooking);

        sidebar.add(btnPembayaran);

        sidebar.add(btnLogout);

        // ================= CONTENT =================

        contentPanel = new JPanel();

        contentPanel.setLayout(new CardLayout());

        contentPanel.add(homePanel(), "HOME");

        add(sidebar, BorderLayout.WEST);

        add(contentPanel, BorderLayout.CENTER);

        // ================= ACTION =================

        btnBooking.addActionListener(e -> {

            new BookingView().setVisible(true);

            dispose();
        });

        btnPembayaran.addActionListener(e -> {

            new PembayaranView().setVisible(true);

            dispose();
        });

        btnLogout.addActionListener(e -> logout());
    }

    // ================= MENU BUTTON =================

    private JButton menuButton(String text){

        JButton btn = new JButton(text);

        btn.setBackground(
                new Color(37,99,235)
        );

        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        return btn;
    }

    // ================= HOME PANEL =================

    private JPanel homePanel(){

        JPanel panel = new JPanel();

        panel.setBackground(
                new Color(248,250,252)
        );

        panel.setLayout(null);

        JLabel welcome =
                new JLabel("Selamat Datang di SportArena");

        welcome.setBounds(40,40,500,40);

        welcome.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        JLabel subtitle =
                new JLabel(
                        "Silahkan booking lapangan favorit Anda"
                );

        subtitle.setBounds(40,90,500,30);

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );

        // ================= CARD =================

        JPanel card1 =
                dashboardCard(
                        "Booking Lapangan",
                        "Pesan lapangan yang tersedia"
                );

        card1.setBounds(40,180,250,150);

        JPanel card2 =
                dashboardCard(
                        "Pembayaran",
                        "Bayar booking Anda"
                );

        card2.setBounds(330,180,250,150);

        panel.add(welcome);

        panel.add(subtitle);

        panel.add(card1);

        panel.add(card2);

        return panel;
    }

    // ================= CARD =================

    private JPanel dashboardCard(
            String title,
            String desc
    ){

        JPanel panel = new JPanel();

        panel.setBackground(Color.WHITE);

        panel.setLayout(null);

        panel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        JLabel lblTitle =
                new JLabel(title);

        lblTitle.setBounds(20,20,200,30);

        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        JLabel lblDesc =
                new JLabel(desc);

        lblDesc.setBounds(20,70,220,30);

        panel.add(lblTitle);

        panel.add(lblDesc);

        return panel;
    }

    // ================= LOGOUT =================

    private void logout(){

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Yakin ingin logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if(confirm == JOptionPane.YES_OPTION){

            new LoginView().setVisible(true);

            dispose();
        }
    }
}