package view;

import javax.swing.*;
import java.awt.*;

public class DashboardAdminView extends JFrame {

    // ================= BUTTON =================

    JButton btnLapangan;
    JButton btnBooking;
    JButton btnUser;
    JButton btnLogout;

    JPanel contentPanel;

    public DashboardAdminView() {

        setTitle("Dashboard Admin");

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

        // ================= TITLE =================

        JLabel title =
                new JLabel("ADMIN PANEL");

        title.setBounds(35,40,200,40);

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        sidebar.add(title);

        // ================= MENU BUTTON =================

        btnLapangan =
                menuButton("Data Lapangan");

        btnLapangan.setBounds(20,140,200,45);

        btnBooking =
                menuButton("Data Booking");

        btnBooking.setBounds(20,210,200,45);

        btnUser =
                menuButton("Data User");

        btnUser.setBounds(20,280,200,45);

        btnLogout =
                menuButton("Logout");

        btnLogout.setBounds(20,520,200,45);

        // ================= ADD BUTTON =================

        sidebar.add(btnLapangan);

        sidebar.add(btnBooking);

        sidebar.add(btnUser);

        sidebar.add(btnLogout);

        // ================= CONTENT PANEL =================

        contentPanel = new JPanel();

        contentPanel.setLayout(new CardLayout());

        contentPanel.add(homePanel(), "HOME");

        add(sidebar, BorderLayout.WEST);

        add(contentPanel, BorderLayout.CENTER);

        // ================= ACTION =================

        // DATA LAPANGAN
        btnLapangan.addActionListener(e -> {

            new LapanganView().setVisible(true);

            dispose();
        });

        // DATA BOOKING CUSTOMER
        btnBooking.addActionListener(e -> {

            new DataBookingView().setVisible(true);

            dispose();
        });

        // DATA USER
        btnUser.addActionListener(e -> {

            new UserView().setVisible(true);

            dispose();
        });

        // LOGOUT
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

        // ================= TITLE =================

        JLabel welcome =
                new JLabel("Dashboard Admin");

        welcome.setBounds(40,40,400,40);

        welcome.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        JLabel subtitle =
                new JLabel(
                        "Monitoring dan Manajemen SportArena"
                );

        subtitle.setBounds(40,90,500,30);

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );

        panel.add(welcome);

        panel.add(subtitle);

        // ================= CARD =================

        JPanel card1 =
                dashboardCard(
                        "Data Booking",
                        "Monitoring Booking Customer"
                );

        card1.setBounds(40,180,250,150);

        JPanel card2 =
                dashboardCard(
                        "Data Lapangan",
                        "Kelola Lapangan SportArena"
                );

        card2.setBounds(330,180,250,150);

        JPanel card3 =
                dashboardCard(
                        "Data User",
                        "Monitoring User Customer"
                );

        card3.setBounds(620,180,250,150);

        panel.add(card1);

        panel.add(card2);

        panel.add(card3);

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

        lblTitle.setBounds(20,20,220,30);

        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        JLabel lblDesc =
                new JLabel(desc);

        lblDesc.setBounds(20,70,220,30);

        lblDesc.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

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