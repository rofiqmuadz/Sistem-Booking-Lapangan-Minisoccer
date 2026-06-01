package view;

import controller.LapanganController;
import database.Koneksi;
import model.Lapangan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LapanganView extends JFrame {

    // ================= COMPONENT =================

    JTextField txtNama;
    JTextField txtHarga;

    JComboBox<String> cbJenis;
    JComboBox<String> cbStatus;

    JButton btnTambah;
    JButton btnUpdate;
    JButton btnHapus;
    JButton btnReset;
    JButton btnKembali;

    JTable tableLapangan;

    DefaultTableModel model;

    int selectedId = 0;

    public LapanganView() {

        setTitle("Data Lapangan");
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(
                new Color(248,250,252)
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel("DATA LAPANGAN");

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

        // ================= NAMA =================

        JLabel lblNama =
                new JLabel("Nama Lapangan");

        lblNama.setBounds(30,30,150,25);

        txtNama = new JTextField();

        txtNama.setBounds(30,60,280,40);

        // ================= JENIS =================

        JLabel lblJenis =
                new JLabel("Jenis Lapangan");

        lblJenis.setBounds(30,130,150,25);

        cbJenis = new JComboBox<>();

        cbJenis.setBounds(30,160,280,40);

        loadJenisLapangan();

        // ================= HARGA =================

        JLabel lblHarga =
                new JLabel("Harga Per Jam");

        lblHarga.setBounds(30,230,150,25);

        txtHarga = new JTextField();

        txtHarga.setBounds(30,260,280,40);

        // ================= STATUS =================

        JLabel lblStatus =
                new JLabel("Status Lapangan");

        lblStatus.setBounds(30,330,150,25);

        cbStatus =
                new JComboBox<>(new String[]{
                        "tersedia",
                        "maintenance"
                });

        cbStatus.setBounds(30,360,280,40);

        // ================= BUTTON =================

        btnTambah =
                new JButton("TAMBAH");

        btnTambah.setBounds(30,430,130,40);

        btnTambah.setBackground(
                new Color(34,197,94)
        );

        btnTambah.setForeground(Color.WHITE);

        btnUpdate =
                new JButton("UPDATE");

        btnUpdate.setBounds(180,430,130,40);

        btnUpdate.setBackground(
                new Color(37,99,235)
        );

        btnUpdate.setForeground(Color.WHITE);

        // ================= ADD FORM =================

        formPanel.add(lblNama);
        formPanel.add(txtNama);

        formPanel.add(lblJenis);
        formPanel.add(cbJenis);

        formPanel.add(lblHarga);
        formPanel.add(txtHarga);

        formPanel.add(lblStatus);
        formPanel.add(cbStatus);

        formPanel.add(btnTambah);
        formPanel.add(btnUpdate);

        add(formPanel);

        // ================= TABLE =================

        String[] kolom = {
                "ID",
                "Nama Lapangan",
                "Jenis",
                "Harga",
                "Status"
        };

        model = new DefaultTableModel(kolom,0);

        tableLapangan =
                new JTable(model);

        tableLapangan.setRowHeight(30);

        tableLapangan.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(tableLapangan);

        scroll.setBounds(430,90,700,450);

        add(scroll);

        // ================= BUTTON BAWAH =================

        btnHapus =
                new JButton("HAPUS");

        btnHapus.setBounds(430,570,120,40);

        btnHapus.setBackground(
                new Color(239,68,68)
        );

        btnHapus.setForeground(Color.WHITE);

        btnReset =
                new JButton("RESET");

        btnReset.setBounds(570,570,120,40);

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

        add(btnHapus);
        add(btnReset);
        add(btnKembali);

        // ================= LOAD DATA =================

        loadTable();

        // ================= ACTION =================

        btnTambah.addActionListener(e -> tambahLapangan());

        btnUpdate.addActionListener(e -> updateLapangan());

        btnHapus.addActionListener(e -> hapusLapangan());

        btnReset.addActionListener(e -> resetForm());

        btnKembali.addActionListener(e -> {

            new DashboardAdminView()
                    .setVisible(true);

            dispose();
        });

        // ================= TABLE CLICK =================

        tableLapangan.getSelectionModel()
                .addListSelectionListener(e -> pilihData());
    }

    // ================= LOAD JENIS =================

    private void loadJenisLapangan(){

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT * FROM jenis_lapangan";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                cbJenis.addItem(

                        rs.getInt("id_jenis")
                                + " - " +

                                rs.getString("nama_jenis")
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ================= TAMBAH =================

    private void tambahLapangan(){

        try {

            String selected =
                    cbJenis.getSelectedItem()
                            .toString();

            int idJenis =
                    Integer.parseInt(
                            selected.split(" - ")[0]
                    );

            Lapangan lapangan =
                    new Lapangan();

            lapangan.setNamaLapangan(
                    txtNama.getText()
            );

            lapangan.setIdJenis(idJenis);

            lapangan.setHargaPerJam(
                    Double.parseDouble(
                            txtHarga.getText()
                    )
            );

            lapangan.setStatusLapangan(
                    cbStatus
                            .getSelectedItem()
                            .toString()
            );

            LapanganController lc =
                    new LapanganController();

            boolean success =
                    lc.tambahLapangan(lapangan);

            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "Lapangan berhasil ditambahkan"
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

    // ================= UPDATE =================

    private void updateLapangan(){

        try {

            String selected =
                    cbJenis.getSelectedItem()
                            .toString();

            int idJenis =
                    Integer.parseInt(
                            selected.split(" - ")[0]
                    );

            Lapangan lapangan =
                    new Lapangan();

            lapangan.setIdLapangan(selectedId);

            lapangan.setNamaLapangan(
                    txtNama.getText()
            );

            lapangan.setIdJenis(idJenis);

            lapangan.setHargaPerJam(
                    Double.parseDouble(
                            txtHarga.getText()
                    )
            );

            lapangan.setStatusLapangan(
                    cbStatus
                            .getSelectedItem()
                            .toString()
            );

            LapanganController lc =
                    new LapanganController();

            boolean success =
                    lc.updateLapangan(lapangan);

            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil diupdate"
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

    // ================= HAPUS =================

    private void hapusLapangan(){

        try {

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Yakin ingin menghapus?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );

            if(confirm == JOptionPane.YES_OPTION){

                LapanganController lc =
                        new LapanganController();

                boolean success =
                        lc.hapusLapangan(selectedId);

                if(success){

                    JOptionPane.showMessageDialog(
                            this,
                            "Data berhasil dihapus"
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

    // ================= LOAD TABLE =================

    private void loadTable(){

        model.setRowCount(0);

        try {

            Connection conn =
                    Koneksi.getConnection();

            String query =
                    "SELECT l.*, j.nama_jenis " +
                    "FROM lapangan l " +
                    "JOIN jenis_lapangan j " +
                    "ON l.id_jenis=j.id_jenis";

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("id_lapangan"),

                        rs.getString("nama_lapangan"),

                        rs.getString("nama_jenis"),

                        rs.getDouble("harga_per_jam"),

                        rs.getString("status_lapangan")
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
                tableLapangan.getSelectedRow();

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

            txtHarga.setText(
                    model.getValueAt(row,3)
                            .toString()
            );

            cbStatus.setSelectedItem(
                    model.getValueAt(row,4)
                            .toString()
            );
        }
    }

    // ================= RESET =================

    private void resetForm(){

        txtNama.setText("");

        txtHarga.setText("");

        cbJenis.setSelectedIndex(0);

        cbStatus.setSelectedIndex(0);

        selectedId = 0;
    }
}