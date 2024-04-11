
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.distanceUnit;

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
public class DistanceUnitModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveDistanceUnitType(String disUnit_name, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            if (!disUnit_name.trim().isEmpty()) {
                String query = "insert into distanceunit(disUnit_name,active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, disUnit_name);
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
            System.out.println("com.apogee.gnss.model.DistanceUnitModel.saveDistanceUnitType()" + e);
        }
    }

    public List<DistanceUnitBean> getAllDistanceUnitType(String distanceunit_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DistanceUnitBean> getAllDistanceUnitType = new ArrayList<>();
        String query = " select distanceunit_id, disUnit_name, remark FROM distanceunit WHERE active = 'Y'";
        if (distanceunit_id != null && !distanceunit_id.isEmpty()) {
            query += " and distanceunit_id='" + distanceunit_id + "'";
        }
        query += " order by distanceunit_id desc";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DistanceUnitBean disUnit = new DistanceUnitBean();
                disUnit.setDistanceunit_id(stmt.getInt("distanceunit_id"));
                disUnit.setDisUnit_name(stmt.getString("disUnit_name"));
                disUnit.setRemark(stmt.getString("remark"));
                getAllDistanceUnitType.add(disUnit);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DistanceUnitModel.getAllDistanceUnitType()" + e);
        }
        return getAllDistanceUnitType;
    }

    public List<DistanceUnitBean> allDistanceUnitType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DistanceUnitBean> allDistanceUnitType = new ArrayList<>();
        String query = " select distanceunit_id, disUnit_name"
                + " FROM distanceunit "
                + " WHERE active = 'Y' order by distanceunit_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DistanceUnitBean disUnit = new DistanceUnitBean();
                disUnit.setDistanceunit_id(stmt.getInt("distanceunit_id"));
                disUnit.setDisUnit_name(stmt.getString("disUnit_name"));
                allDistanceUnitType.add(disUnit);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DistanceUnitModel.allDistanceUnitType()" + e);
        }
        return allDistanceUnitType;
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
            System.out.println("com.apogee.gnss.model.DistanceUnitModel.closeConnection()" + e);
        }
    }

}
