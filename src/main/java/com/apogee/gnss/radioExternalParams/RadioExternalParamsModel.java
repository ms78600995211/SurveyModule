/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.radioExternalParams;

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
public class RadioExternalParamsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveRadioExternalParams(String baudrate, String power, String protocol, String frequency, String defaultConfig, String name, String remark) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            if (!baudrate.trim().isEmpty() && !power.trim().isEmpty() && !protocol.trim().isEmpty() && !frequency.trim().isEmpty() && !defaultConfig.trim().isEmpty()) {
                String query = "SELECT MAX(radioexternalparams_id) AS max_radioexternalparams_id FROM radioexternalparams;";
                pstmt = connection.prepareStatement(query);
                rs = pstmt.executeQuery();
                int max_radioexternalparams_id = 0;
                while (rs.next()) {
                    max_radioexternalparams_id = rs.getInt("max_radioexternalparams_id");
                }
                max_radioexternalparams_id = max_radioexternalparams_id + 1;
                String radioexternalparams = "radioexternalparams";
                String primary_key = "_" + max_radioexternalparams_id;
                String radioexternalparams_name = radioexternalparams.concat(primary_key);
                query = "insert into radioexternalparams(radioexternalparams_name,power,protocol,frequency,defaultConfig,active,"
                        + "created_by,created_at,remark,revision_no,baudrate ) values (?,?,?,?,?,?,?,?,?,?,? )";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, radioexternalparams_name);
                pstmt.setString(2, power);
                pstmt.setString(3, protocol);
                pstmt.setString(4, frequency);
                pstmt.setString(5, defaultConfig);
                pstmt.setString(6, "Y");
                pstmt.setString(7, name);
                pstmt.setString(8, time_stamp);
                pstmt.setString(9, remark);
                pstmt.setInt(10, 0);
                pstmt.setString(11, baudrate);
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
            System.out.println("com.apogee.gnss.model.RadioExternalParamsModel.saveRadioExternalParams()" + e);
        }
    }

    public List<RadioExternalParamsBean> getAllRadioExternalParams(String frequency) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<RadioExternalParamsBean> getAllRadioExternalParams = new ArrayList<>();
        String query = "SELECT * FROM radioexternalparams WHERE active = 'Y'";
        if (frequency != null && !frequency.isEmpty()) {
            query += " AND frequency='" + frequency + "'";
        }
        query += " ORDER BY radioexternalparams_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                RadioExternalParamsBean bean = new RadioExternalParamsBean();
                bean.setRadioexternalparams_name(stmt.getString("radioexternalparams_name"));
                bean.setBaudrate(stmt.getString("baudrate"));
                bean.setPower(stmt.getString("power"));
                bean.setProtocol(stmt.getString("protocol"));
                bean.setFrequency(stmt.getString("frequency"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setRemark(stmt.getString("remark"));
                getAllRadioExternalParams.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.RadioExternalParamsModel.getAllRadioExternalParams()" + e);
        }
        return getAllRadioExternalParams;
    }

    public List allFrequency() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List allFrequency = new ArrayList<>();
        String query = " select distinct frequency  FROM radioexternalparams  WHERE active = 'Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                allFrequency.add(stmt.getString("frequency"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.RadioExternalParamsModel.allRadioExternalParams()" + e);
        }
        return allFrequency;
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

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.RadioExternalParamsModel.closeConnection()" + e);
        }
    }

}
