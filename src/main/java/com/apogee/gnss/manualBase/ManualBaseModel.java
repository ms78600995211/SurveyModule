/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.manualBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class ManualBaseModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveManualBase(String latitude, String longitude, String altitude, String remark, String log_in_person, String manual_base_params_name, String defaultConfig) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!latitude.trim().isEmpty() && !longitude.trim().isEmpty() && !longitude.trim().isEmpty()) {
                String query = "insert into manual_base_params(latitude, longitude, altitude, remark, created_by,manual_base_params_name,defaultConfig) values(?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, latitude);
                pstmt.setString(2, longitude);
                pstmt.setString(3, altitude);
                pstmt.setString(4, remark);
                pstmt.setString(5, log_in_person);
                pstmt.setString(6, manual_base_params_name);
                pstmt.setString(7, defaultConfig);
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
            System.out.println("com.apogee.gnss.model.ManualBaseModel.saveManualBase()" + e);
        }
    }

    public List<ManualBaseBean> getAllManualBase(String latitude) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ManualBaseBean> getAllManualBase = new ArrayList<>();
        String query = " SELECT manual_base_params_id,manual_base_params_name, latitude, longitude, altitude, remark,defaultConfig FROM manual_base_params WHERE active = 'Y'";
        if (latitude != null && !latitude.isEmpty()) {
            query += " AND latitude='" + latitude + "'";
        }

        query += " ORDER BY manual_base_params_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ManualBaseBean manualBase = new ManualBaseBean();
                manualBase.setManual_base_params_id(stmt.getInt("manual_base_params_id"));
                manualBase.setManual_base_params_name(stmt.getString("manual_base_params_name"));
                manualBase.setLatitude(stmt.getString("latitude"));
                manualBase.setLongitude(stmt.getString("longitude"));
                manualBase.setAltitude(stmt.getString("altitude"));
                manualBase.setDefaultConfig(stmt.getString("defaultConfig"));
                manualBase.setRemark(stmt.getString("remark"));
                getAllManualBase.add(manualBase);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ManualBaseModel.getAllManualBase()" + e);
        }
        return getAllManualBase;
    }

    public List<ManualBaseBean> manualBaseList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ManualBaseBean> manualBaseList = new ArrayList<>();
        String query = " select distinct  latitude  FROM manual_base_params  WHERE active = 'Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ManualBaseBean manualBase = new ManualBaseBean();
                manualBase.setLatitude(stmt.getString("latitude"));
                manualBaseList.add(manualBase);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ManualBaseModel.manualBaseList()" + e);
        }
        return manualBaseList;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ManualBaseModel.closeConnection()" + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCOLOR_ERROR() {
        return COLOR_ERROR;
    }

    public void setCOLOR_ERROR(String COLOR_ERROR) {
        this.COLOR_ERROR = COLOR_ERROR;
    }

    public String getCOLOR_OK() {
        return COLOR_OK;
    }

    public void setCOLOR_OK(String COLOR_OK) {
        this.COLOR_OK = COLOR_OK;
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

}
