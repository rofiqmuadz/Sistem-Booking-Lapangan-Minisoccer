package view;

import controller.RegisterController;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {

    JTextField txtNama;
    JTextField txtEmail;
    JTextField txtNoHp;

    JPasswordField txtPassword;

    JButton btnRegister;
    JButton btnBack;

    public RegisterView() {

        setTitle("Register Account");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= LEFT PANEL =================

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400,500));
        leftPanel.setBackground(new Color(37,99,235));
        leftPanel.setLayout(null);

        JLabel title =
                new JLabel("SPORT ARENA");

        title.setBounds(60,150,300,50);
        title.setForeground(Color.WHITE);

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 34)
        );

        JLabel subtitle =
                new JLabel("Create New Account");

        subtitle.setBounds(60,210,250,30);

        subtitle.setForeground(Color.WHITE);

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );

        leftPanel.add(title);
        leftPanel.add(subtitle);

        // ================= RIGHT PANEL =================

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JLabel registerLabel =
                new JLabel("REGISTER");

        registerLabel.setBounds(120,40,250,40);

        registerLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        // ================= NAMA =================

        JLabel namaLabel =
                new JLabel("Nama Lengkap");

        namaLabel.setBounds(70,110,150,25);

        txtNama = new JTextField();
        txtNama.setBounds(70,140,300,40);

        // ================= EMAIL =================

        JLabel emailLabel =
                new JLabel("Email");

        emailLabel.setBounds(70,200,150,25);

        txtEmail = new JTextField();
        txtEmail.setBounds(70,230,300,40);

        // ================= PASSWORD =================

        JLabel passLabel =
                new JLabel("Password");

        passLabel.setBounds(70,290,150,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(70,320,300,40);

        // ================= NO HP =================

        JLabel hpLabel =
                new JLabel("Nomor HP");

        hpLabel.setBounds(70,380,150,25);

        txtNoHp = new JTextField();
        txtNoHp.setBounds(70,410,300,40);

        // ================= BUTTON =================

        btnRegister =
                new JButton("REGISTER");

        btnRegister.setBounds(70,470,140,40);

        btnRegister.setBackground(
                new Color(37,99,235)
        );

        btnRegister.setForeground(Color.WHITE);

        btnBack =
                new JButton("BACK LOGIN");

        btnBack.setBounds(230,470,140,40);

        // ================= ADD COMPONENT =================

        rightPanel.add(registerLabel);

        rightPanel.add(namaLabel);
        rightPanel.add(txtNama);

        rightPanel.add(emailLabel);
        rightPanel.add(txtEmail);

        rightPanel.add(passLabel);
        rightPanel.add(txtPassword);

        rightPanel.add(hpLabel);
        rightPanel.add(txtNoHp);

        rightPanel.add(btnRegister);
        rightPanel.add(btnBack);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // ================= ACTION =================

        btnRegister.addActionListener(e -> register());

        btnBack.addActionListener(e -> {

            new LoginView().setVisible(true);

            dispose();
        });
    }

    // ================= METHOD REGISTER =================

    private void register(){

        String nama = txtNama.getText();

        String email = txtEmail.getText();

        String password =
                String.valueOf(
                        txtPassword.getPassword()
                );

        String noHp = txtNoHp.getText();

        // VALIDASI
        if(
                nama.isEmpty() ||
                email.isEmpty() ||
                password.isEmpty() ||
                noHp.isEmpty()
        ){

            JOptionPane.showMessageDialog(
                    this,
                    "Semua data wajib diisi!"
            );

            return;
        }

        RegisterController rc =
                new RegisterController();

        boolean success =
                rc.register(
                        nama,
                        email,
                        password,
                        noHp
                );

        if(success){

            JOptionPane.showMessageDialog(
                    this,
                    "Register Berhasil!"
            );

            new LoginView().setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Email sudah digunakan!"
            );
        }
    }
}