/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.ntripBaseRegistration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NtripBaseRegistrationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;
    public void saveBaseRegData(String base_name, String base_password, String latitude, String longitude, String altitude, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!base_name.trim().isEmpty() && !base_password.trim().isEmpty() && !latitude.trim().isEmpty() && !longitude.trim().isEmpty() && !altitude.trim().isEmpty()) {
                String query = "insert into ntrip_base(base_name,base_password,latitude,longitude,altitude,active,remark,revision_no) values(?,?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, base_name);
                pstmt.setString(2, base_password);
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
            System.out.println("com.apogee.gnss.model.NtripBaseRegistrationModel.saveBaseRegData()" + e);
        }
    }

    public List<NtripBaseRegistrationBean> getBaseRegData(String ntrip_base_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<NtripBaseRegistrationBean> baseRegistrationList = new ArrayList<>();
        String qry = "SELECT * FROM ntrip_base WHERE active = 'Y'";
        if (ntrip_base_id != null && !ntrip_base_id.isEmpty()) {
            qry += " AND ntrip_base_id='" + ntrip_base_id + "'";
        }
        qry += " ORDER BY ntrip_base_id DESC";
        try {
            pstmt = connection.prepareStatement(qry);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                NtripBaseRegistrationBean baseRegistration = new NtripBaseRegistrationBean();
                baseRegistration.setBase_name(stmt.getString("base_name"));
                baseRegistration.setBase_password(stmt.getString("base_password"));
                baseRegistration.setLatitude(stmt.getString("latitude"));
                baseRegistration.setLongitude(stmt.getString("longitude"));
                baseRegistration.setAltitude(stmt.getString("altitude"));
                baseRegistration.setRemark(stmt.getString("remark"));
                baseRegistrationList.add(baseRegistration);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.NtripBaseRegistrationModel.getBaseRegData()" + e);
        }
        return baseRegistrationList;
    }

    public List<NtripBaseRegistrationBean> allBaseRegdata() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<NtripBaseRegistrationBean> allBaseRegdata = new ArrayList<>();
        String query = " select * FROM ntrip_base WHERE active = 'Y' order by ntrip_base_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                NtripBaseRegistrationBean baseRegistration = new NtripBaseRegistrationBean();
                baseRegistration.setNtrip_base_id(stmt.getString("ntrip_base_id"));
                baseRegistration.setBase_name(stmt.getString("base_name"));
                allBaseRegdata.add(baseRegistration);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.NtripBaseRegistrationModel.getBaseRegData()" + e);
        }
        return allBaseRegdata;
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
            System.out.println("com.apogee.gnss.model.NtripBaseRegistrationModel.closeConnection()" + e);
        }
    }

}
