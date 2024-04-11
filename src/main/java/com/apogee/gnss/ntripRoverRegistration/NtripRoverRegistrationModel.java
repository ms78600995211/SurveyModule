/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.ntripRoverRegistration;

import com.apogee.gnss.ntripRoverRegistration.NtripRoverRegistrationBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NtripRoverRegistrationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;
    public void saveRoverRegData(String rover_name, String rover_password, String latitude, String longitude, String altitude, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!rover_name.trim().isEmpty() && !rover_password.trim().isEmpty() && !latitude.trim().isEmpty() && !longitude.trim().isEmpty() && !altitude.trim().isEmpty()) {
                String query = "insert into ntrip_rover(rover_name,rover_password,latitude,longitude,altitude,active,remark,revision_no) values(?,?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, rover_name);
                pstmt.setString(2, rover_password);
                pstmt.setString(3, latitude);
                pstmt.setString(4, longitude);
                pstmt.setString(5, altitude);
                pstmt.setString(6, "Y");
                pstmt.setString(7, remark);
                pstmt.setInt(8, 0);
                rowsAffected = pstmt.executeUpdate();
            }
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.NtripBaseRegistrationModel.saveRoverRegData()" + e);
        }
    }

    public List<NtripRoverRegistrationBean> getRoverRegData(String ntrip_rover_id) {
        List<NtripRoverRegistrationBean> roverRegistrationList = new ArrayList<>();
        String query = " select * FROM ntrip_rover WHERE active = 'Y' ";
        if (ntrip_rover_id != null && !ntrip_rover_id.isEmpty()) {
            query += " and ntrip_rover_id='" + ntrip_rover_id + "'";
        }
        query += " order by ntrip_rover_id desc ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();
            while (stmt.next()) {
                NtripRoverRegistrationBean roverRegistration = new NtripRoverRegistrationBean();
                roverRegistration.setRover_name(stmt.getString("rover_name"));
                roverRegistration.setRover_password(stmt.getString("rover_password"));
                roverRegistration.setLatitude(stmt.getString("latitude"));
                roverRegistration.setLongitude(stmt.getString("longitude"));
                roverRegistration.setAltitude(stmt.getString("altitude"));
                roverRegistration.setRemark(stmt.getString("remark"));
                roverRegistrationList.add(roverRegistration);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.NtripRoverRegistrationModel.getRoverRegData()" + e);
        }
        return roverRegistrationList;
    }

    public List<NtripRoverRegistrationBean> allRoverRegData() {
        List<NtripRoverRegistrationBean> allRoverRegData = new ArrayList<>();
        String query = " select ntrip_rover_id,rover_name FROM ntrip_rover WHERE active = 'Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();
            while (stmt.next()) {
                NtripRoverRegistrationBean roverRegistration = new NtripRoverRegistrationBean();
                roverRegistration.setNtrip_rover_id(stmt.getString("ntrip_rover_id"));
                roverRegistration.setRover_name(stmt.getString("rover_name"));
                allRoverRegData.add(roverRegistration);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.NtripRoverRegistrationModel.allRoverRegData()" + e);
        }
        return allRoverRegData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCOLOR_OK() {
        return COLOR_OK;
    }

    public void setCOLOR_OK(String COLOR_OK) {
        this.COLOR_OK = COLOR_OK;
    }

    public String getCOLOR_ERROR() {
        return COLOR_ERROR;
    }

    public void setCOLOR_ERROR(String COLOR_ERROR) {
        this.COLOR_ERROR = COLOR_ERROR;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.NtripRoverRegistrationModel.closeConnection()" + e);
        }
    }

}
