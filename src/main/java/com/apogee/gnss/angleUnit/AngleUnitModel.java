/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.angleUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class AngleUnitModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;
    public void saveAngleUnit(String angleunit, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            if (!angleunit.trim().isEmpty()) {
                String query = "insert into angleunit(angUnit_name, active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, angleunit);
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
            System.out.println("com.apogee.gnss.model.AngleUnitModel.saveAngleUnit()" + e);
        }
    }

    public List<AngleUnitBean> allAngleUnits() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<AngleUnitBean> allAngleUnits = new ArrayList<>();
        String query = " select angleunit_id, angUnit_name "
                + " FROM angleunit "
                + " WHERE active = 'Y' order by angleunit_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                AngleUnitBean angleunit = new AngleUnitBean();
                angleunit.setAngleunit_id(stmt.getInt("angleunit_id"));
                angleunit.setAngUnit_name(stmt.getString("angUnit_name"));
                allAngleUnits.add(angleunit);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.AngleUnitModel.allAngleUnits()" + e);
        }
        return allAngleUnits;
    }

    public List<AngleUnitBean> getAllAngleUnits(String angleunit_id) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<AngleUnitBean> getAllAngleUnits = new ArrayList<>();
        String query = "SELECT angleunit_id, angUnit_name, remark FROM angleunit WHERE active = 'Y' ";
        if (angleunit_id != null && !angleunit_id.isEmpty()) {
            query += "AND angleunit_id ='"+angleunit_id+"'";
        }
        query += "  ORDER BY angleunit_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                AngleUnitBean angUnit_name = new AngleUnitBean();
                angUnit_name.setAngleunit_id(resultSet.getInt("angleunit_id"));
                angUnit_name.setAngUnit_name(resultSet.getString("angUnit_name"));
                angUnit_name.setRemark(resultSet.getString("remark"));
                getAllAngleUnits.add(angUnit_name);
            }
        } catch (SQLException e) {
            System.out.println("com.apogee.gnss.model.AngleUnitModel.getAllAngleUnits()" + e);
        }
        return getAllAngleUnits;
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
            System.out.println("com.apogee.gnss.model.AngleUnitModel.closeConnection()" + e);
        }
    }

}
