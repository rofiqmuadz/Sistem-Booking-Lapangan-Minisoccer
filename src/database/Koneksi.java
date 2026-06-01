/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Lenovo
 */
public class Koneksi {

    private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static final String nama_db = "bcr_arena";
    private static final String url_db =
            "jdbc:mysql://localhost:8111/" + nama_db;
    private static final String username_db = "root";
    private static final String password_db = "";

    private static Connection conn;

    public static Connection getConnection() {

        try {

            Class.forName(jdbc_driver);

            conn = DriverManager.getConnection(
                    url_db,
                    username_db,
                    password_db
            );

            System.out.println("Database Connected");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Connection Failed : "
                    + e.getMessage());

        }

        return conn;
    }
}