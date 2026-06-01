package view;

import controller.LoginController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    JTextField txtEmail;
    JPasswordField txtPassword;

    JButton btnLogin;
    JButton btnRegister;

    public LoginView() {

        setTitle("SportArena Login");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= LEFT PANEL =================

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400,500));
        leftPanel.setBackground(new Color(37,99,235));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("SPORT ARENA");
        title.setBounds(60,150,300,50);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));

        JLabel subtitle =
                new JLabel("Booking Sport Center");

        subtitle.setBounds(60,210,250,30);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        leftPanel.add(title);
        leftPanel.add(subtitle);

        // ================= RIGHT PANEL =================

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setBounds(150,60,200,40);
        loginLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(70,150,100,25);

        txtEmail = new JTextField();
        txtEmail.setBounds(70,180,300,40);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(70,240,100,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(70,270,300,40);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(70,340,300,45);

        btnLogin.setBackground(
                new Color(37,99,235)
        );

        btnLogin.setForeground(Color.WHITE);

        btnRegister =
                new JButton("Create Account");

        btnRegister.setBounds(70,400,300,40);

        rightPanel.add(loginLabel);
        rightPanel.add(emailLabel);
        rightPanel.add(txtEmail);
        rightPanel.add(passLabel);
        rightPanel.add(txtPassword);
        rightPanel.add(btnLogin);
        rightPanel.add(btnRegister);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // ================= ACTION =================

        btnLogin.addActionListener(e -> login());

        btnRegister.addActionListener(e -> {

            new RegisterView().setVisible(true);

            dispose();
        });
    }

    // ================= METHOD LOGIN =================

    private void login(){

        String email = txtEmail.getText();

        String password =
                String.valueOf(
                        txtPassword.getPassword()
                );

        if(email.isEmpty() || password.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Email dan Password wajib diisi!"
            );

            return;
        }

        LoginController lc =
                new LoginController();

        User user = lc.login(email, password);

        if(user != null){

            JOptionPane.showMessageDialog(
                    this,
                    "Login Berhasil"
            );

            if(user.getRole().equalsIgnoreCase("admin")){

                new DashboardAdminView().setVisible(true);

            } else {

                new DashboardCustomerView().setVisible(true);
            }

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Email atau Password Salah!"
            );
        }
    }
}