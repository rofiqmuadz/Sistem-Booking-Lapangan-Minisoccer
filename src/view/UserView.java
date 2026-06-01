package view;

import controller.UserController;
import database.Koneksi;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserView extends JFrame {

    // ================= COMPONENT =================

    JTextField txtNama;
    JTextField txtEmail;
    JTextField txtNoHp;

    JPasswordField txtPassword;

    JComboBox<String> cbRole;

    JButton btnUpdate;
    JButton btnHapus;
    JButton btnReset;
    JButton btnKembali;

    JTable tableUser;

    DefaultTableModel model;

    int selectedId = 0;

    public UserView() {

        setTitle("Data User");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(
                new Color(248,250,252)
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel("DATA USER");

        title.setBounds(40,20,300,40);

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

        // ================= NAMA =================

        JLabel lblNama =
                new JLabel("Nama");

        lblNama.setBounds(30,30,150,25);

        txtNama = new JTextField();

        txtNama.setBounds(30,60,280,40);

        // ================= EMAIL =================

        JLabel lblEmail =
                new JLabel("Email");

        lblEmail.setBounds(30,120,150,25);

        txtEmail = new JTextField();

        txtEmail.setBounds(30,150,280,40);

        // ================= PASSWORD =================

        JLabel lblPassword =
                new JLabel("Password");

        lblPassword.setBounds(30,210,150,25);

        txtPassword = new JPasswordField();

        txtPassword.setBounds(30,240,280,40);

        // ================= NO HP =================

        JLabel lblNoHp =
                new JLabel("Nomor HP");

        lblNoHp.setBounds(30,300,150,25);

        txtNoHp = new JTextField();

        txtNoHp.setBounds(30,330,280,40);

        // ================= ROLE =================

        JLabel lblRole =
                new JLabel("Role");

        lblRole.setBounds(30,390,150,25);

        cbRole =
                new JComboBox<>(new String[]{
                        "admin",
                        "customer"
                });

        cbRole.setBounds(30,420,280,40);

        // ================= BUTTON =================

        btnUpdate =
                new JButton("UPDATE");

        btnUpdate.setBounds(30,480,130,40);

        btnUpdate.setBackground(
                new Color(37,99,235)
        );

        btnUpdate.setForeground(Color.WHITE);

        btnHapus =
                new JButton("HAPUS");

        btnHapus.setBounds(180,480,130,40);

        btnHapus.setBackground(
                new Color(239,68,68)
        );

        btnHapus.setForeground(Color.WHITE);

        // ================= ADD FORM =================

        formPanel.add(lblNama);
        formPanel.add(txtNama);

        formPanel.add(lblEmail);
        formPanel.add(txtEmail);

        formPanel.add(lblPassword);
        formPanel.add(txtPassword);

        formPanel.add(lblNoHp);
        formPanel.add(txtNoHp);

        formPanel.add(lblRole);
        formPanel.add(cbRole);

        formPanel.add(btnUpdate);
        formPanel.add(btnHapus);

        add(formPanel);

        // ================= TABLE =================

        String[] kolom = {
                "ID",
                "Nama",
                "Email",
                "Password",
                "No HP",
                "Role"
        };

        model =
                new DefaultTableModel(kolom,0);

        tableUser =
                new JTable(model);

        tableUser.setRowHeight(30);

        tableUser.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(tableUser);

        scroll.setBounds(430,90,700,450);

        add(scroll);

        // ================= BUTTON BAWAH =================

        btnReset =
                new JButton("RESET");

        btnReset.setBounds(430,570,120,40);

        btnReset.setBackground(
                new Color(245,158,11)
        );

        btnReset.setForeground(Color.WHITE);

        btnKembali =
                new JButton("KEMBALI");

        btnKembali.setBounds(1010,570,120,40);

        btnKembali.setBackground(
                new Color(30,41,59)
        );

        btnKembali.setForeground(Color.WHITE);

        add(btnReset);

        add(btnKembali);

        // ================= LOAD DATA =================

        loadTable();

        // ================= ACTION =================

        btnUpdate.addActionListener(e -> updateUser());

        btnHapus.addActionListener(e -> hapusUser());

        btnReset.addActionListener(e -> resetForm());

        btnKembali.addActionListener(e -> {

            new DashboardAdminView()
                    .setVisible(true);

            dispose();
        });

        // ================= TABLE CLICK =================

        tableUser.getSelectionModel()
                .addListSelectionListener(e -> pilihData());
    }

    // ================= LOAD TABLE =================

    private void loadTable(){

        model.setRowCount(0);

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT * FROM users";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("id_user"),

                        rs.getString("nama"),

                        rs.getString("email"),

                        rs.getString("password"),

                        rs.getString("no_hp"),

                        rs.getString("role")
                });
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ================= PILIH DATA =================

    private void pilihData(){

        int row =
                tableUser.getSelectedRow();

        if(row != -1){

            selectedId =
                    Integer.parseInt(
                            model.getValueAt(row,0)
                                    .toString()
                    );

            txtNama.setText(
                    model.getValueAt(row,1)
                            .toString()
            );

            txtEmail.setText(
                    model.getValueAt(row,2)
                            .toString()
            );

            txtPassword.setText(
                    model.getValueAt(row,3)
                            .toString()
            );

            txtNoHp.setText(
                    model.getValueAt(row,4)
                            .toString()
            );

            cbRole.setSelectedItem(
                    model.getValueAt(row,5)
                            .toString()
            );
        }
    }

    // ================= UPDATE USER =================

    private void updateUser(){

        try {

            User user = new User();

            user.setIdUser(selectedId);

            user.setNama(
                    txtNama.getText()
            );

            user.setEmail(
                    txtEmail.getText()
            );

            user.setPassword(
                    String.valueOf(
                            txtPassword.getPassword()
                    )
            );

            user.setNoHp(
                    txtNoHp.getText()
            );

            user.setRole(
                    cbRole.getSelectedItem()
                            .toString()
            );

            UserController uc =
                    new UserController();

            boolean success =
                    uc.updateUser(user);

            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "Data user berhasil diupdate"
                );

                loadTable();

                resetForm();
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ================= HAPUS USER =================

    private void hapusUser(){

        try {

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Yakin ingin menghapus user?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );

            if(confirm == JOptionPane.YES_OPTION){

                UserController uc =
                        new UserController();

                boolean success =
                        uc.hapusUser(selectedId);

                if(success){

                    JOptionPane.showMessageDialog(
                            this,
                            "User berhasil dihapus"
                    );

                    loadTable();

                    resetForm();
                }
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

        txtNama.setText("");

        txtEmail.setText("");

        txtPassword.setText("");

        txtNoHp.setText("");

        cbRole.setSelectedIndex(0);

        selectedId = 0;
    }
}