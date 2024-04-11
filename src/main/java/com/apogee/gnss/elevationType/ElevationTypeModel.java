/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.elevationType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ElevationTypeModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveElevationType(String elevationType, String name, String remark) {
        int rowsAffected = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            if (!elevationType.trim().isEmpty()) {
                String query = "insert into elevationtype(elevationType,active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, elevationType);
                pstmt.setString(2, "Y");
                pstmt.setString(3, name);
                pstmt.setString(4, time_stamp);
                pstmt.setString(5, remark);
                pstmt.setInt(6, 0);
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
            System.out.println("com.apogee.gnss.model.ElevationTypeModel.saveElevationType()" + e);
        }
    }

    public List<ElevationTypeBean> getAllElevationType(String elevationtype_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ElevationTypeBean> getAllElevationType = new ArrayList<>();
        String query = "SELECT elevationtype_id, elevationType, remark FROM elevationtype WHERE active = 'Y'";
        String qry = "";
        if (elevationtype_id != null && !elevationtype_id.isEmpty()) {
            qry = " AND elevationtype_id='" + elevationtype_id + "'";
        }
        query += qry + " ORDER BY elevationtype_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ElevationTypeBean elevationType = new ElevationTypeBean();
                elevationType.setElevationtype_id(stmt.getInt("elevationtype_id"));
                elevationType.setElevationType(stmt.getString("elevationType"));
                elevationType.setRemark(stmt.getString("remark"));
                getAllElevationType.add(elevationType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ElevationTypeModel.getAllElevationType()" + e);
        }
        return getAllElevationType;
    }

    public List<ElevationTypeBean> allElevationType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ElevationTypeBean> allElevationType = new ArrayList<>();
        String query = " select elevationtype_id, elevationType "
                + " FROM elevationtype "
                + " WHERE active = 'Y' order by elevationtype_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ElevationTypeBean elevationType = new ElevationTypeBean();
                elevationType.setElevationtype_id(stmt.getInt("elevationtype_id"));
                elevationType.setElevationType(stmt.getString("elevationType"));
                allElevationType.add(elevationType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ElevationTypeModel.allElevationType()" + e);
        }
        return allElevationType;
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
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

}
