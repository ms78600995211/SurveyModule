/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.maskAngle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class MaskAngleModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveMaskangle(String byteValue, String displayValue, String remark, String log_in_person) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!byteValue.trim().isEmpty() && !displayValue.trim().isEmpty()) {
                String query = "insert into mask_angle(byte_value, display_value, remark, created_by) values(?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, byteValue);
                pstmt.setString(2, displayValue);
                pstmt.setString(3, remark);
                pstmt.setString(4, log_in_person);
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
            System.out.println("com.apogee.gnss.model.MaskAngleModel.saveMaskangle()" + e);
        }
    }

    public List<MaskAngleBean> getAllMaskAngle(String byte_value) {
        List<MaskAngleBean> getAllMaskAngle = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "SELECT mask_angle_id, byte_value, display_value, remark FROM mask_angle WHERE active = 'Y'";
        if (byte_value != null && !byte_value.isEmpty()) {
            query += " AND byte_value='" + byte_value + "'";
        }
        query += " ORDER BY mask_angle_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                MaskAngleBean maskAngle = new MaskAngleBean();
                maskAngle.setMask_angle_id(stmt.getInt("mask_angle_id"));
                maskAngle.setByte_value(stmt.getString("byte_value"));
                maskAngle.setDisplay_value(stmt.getString("display_value"));
                maskAngle.setRemark(stmt.getString("remark"));
                getAllMaskAngle.add(maskAngle);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.MaskAngleModel.getAllMaskAngle()" + e);
        }
        return getAllMaskAngle;
    }

    public List<MaskAngleBean> maskAngleList() {
        List<MaskAngleBean> maskAngleList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = " select distinct  byte_value FROM mask_angle WHERE active = 'Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                MaskAngleBean maskAngle = new MaskAngleBean();
                maskAngle.setByte_value(stmt.getString("byte_value"));
                maskAngleList.add(maskAngle);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.MaskAngleModel.maskAngleList()" + e);
        }
        return maskAngleList;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.MaskAngleModel.closeConnection()" + e);
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
