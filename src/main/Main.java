package main;

import database.Koneksi;
import view.LoginView;

import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection conn = Koneksi.getConnection();

        if(conn != null){

            System.out.println("DATABASE BERHASIL TERKONEK");

        } else {

            System.out.println("DATABASE GAGAL TERKONEK");

            return;
        }

        SwingUtilities.invokeLater(() -> {

            new LoginView().setVisible(true);

        });
    }
}