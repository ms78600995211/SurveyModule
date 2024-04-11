/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apogee.gnss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lENOVO
 */
public class DBConnection {

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    static String driverClass = resourceBundle.getString("DATABASE_DRIVERCLASS");
    static String connectionString = resourceBundle.getString("DATABASE_CONNECTIONSTRING");
    static String db_username = resourceBundle.getString("DATABASE_USERNAME");
    static String db_password = resourceBundle.getString("DATABASE_PASSWORD");

    public static synchronized Connection getConnection(ServletContext ctx, HttpSession session) throws SQLException {
        Connection conn = null;

        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(connectionString, db_username, db_password);
        } catch (Exception e) {
            System.out.println("DBConncetion getConnection() Error: " + e);
        }
        return conn;
    }

    public static Connection getConnection1() throws SQLException {
        Connection conn = null;

        try {
            Class.forName(driverClass);
            conn = (Connection) DriverManager.getConnection(connectionString, db_username, db_password);
        } catch (Exception e) {
            System.out.println("DBConncetion getConnection() Error: " + e);
        }
        return conn;
    }

}
