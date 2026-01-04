/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DBConnection implements DBinformation {

    public DBConnection() {
    }

    public static Connection getConnection() {
        Connection con = null;

        // DEBUG: Print all DB config values
        System.out.println("=== DBConnection.getConnection() ===");
        System.out.println("driverName: " + (driverName != null ? driverName : "NULL!"));
        System.out.println("dbURL: " + (dbURL != null ? dbURL : "NULL!"));
        System.out.println("userDB: " + (userDB != null ? userDB : "NULL!"));
        System.out.println("passDB: " + (passDB != null ? "***SET***" : "NULL!"));

        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(dbURL, userDB, passDB);
            System.out.println("✅ Connection SUCCESS!");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("❌ Connection FAILED: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            if (con != null) {
                System.out.println("Connect to Database Calendar success");
            }
        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
