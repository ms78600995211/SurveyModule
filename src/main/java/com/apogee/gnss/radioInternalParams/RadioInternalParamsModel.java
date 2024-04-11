/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.radioInternalParams;

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
public class RadioInternalParamsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveRadioInternalParams(String datarate, String baudrate, String power, String frequency, String defaultConfig, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        String query = "SELECT MAX(radiointernalparams_id) AS max_radiointernalparams_id FROM radiointernalparams;";
        try {
            if (!datarate.trim().isEmpty() && !baudrate.trim().isEmpty() && !baudrate.trim().isEmpty() && !frequency.trim().isEmpty() && !defaultConfig.trim().isEmpty()) {
                pstmt = connection.prepareStatement(query);
                rs = pstmt.executeQuery();
                int max_radiointernalparams_id = 0;
                while (rs.next()) {
                    max_radiointernalparams_id = rs.getInt("max_radiointernalparams_id");
                }
                max_radiointernalparams_id = max_radiointernalparams_id + 1;
                String radiointernalparams = "radiointernalparams";
                String primary_key = "_" + max_radiointernalparams_id;
                String radiointernalparams_name = radiointernalparams.concat(primary_key);
                query = "insert into radiointernalparams(radiointernalparams_name,datarate,baudrate,power,frequency,defaultConfig,active,created_by,created_at,remark,revision_no ) values (?,?,?,?,?,?,?,?,?,?,? )";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, radiointernalparams_name);
                pstmt.setString(2, datarate);
                pstmt.setString(3, baudrate);
                pstmt.setString(4, power);
                pstmt.setString(5, frequency);
                pstmt.setString(6, defaultConfig);
                pstmt.setString(7, "Y");
                pstmt.setString(8, name);
                pstmt.setString(9, time_stamp);
                pstmt.setString(10, remark);
                pstmt.setInt(11, 0);
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
            System.out.println("com.apogee.gnss.model.RadioInternalParamsModel.saveRadioInternalParams()" + e);
        }
    }

    public List<RadioInternalParamsBean> getAllRadioInternalParams(String frequency) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<RadioInternalParamsBean> getAllRadioInternalParams = new ArrayList<>();
        String query = "SELECT * FROM radiointernalparams WHERE active = 'Y'";
        if (frequency != null && !frequency.isEmpty()) {
            query += " AND frequency='" + frequency + "'";
        }
        query += " ORDER BY radiointernalparams_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                RadioInternalParamsBean bean = new RadioInternalParamsBean();
                bean.setRadiointernalparams_name(stmt.getString("radiointernalparams_name"));
                bean.setDatarate(stmt.getString("datarate"));
                bean.setBaudrate(stmt.getString("baudrate"));
                bean.setPower(stmt.getString("power"));
                bean.setFrequency(stmt.getString("frequency"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setRemark(stmt.getString("remark"));
                getAllRadioInternalParams.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.RadioInternalParamsModel.getAllRadioInternalParams()" + e);
        }
        return getAllRadioInternalParams;
    }

    public List allFrequency() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List allFrequency = new ArrayList<>();
        String query = " select distinct frequency  FROM radiointernalparams  WHERE active = 'Y' ";
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
            System.out.println("com.apogee.gnss.model.RadioInternalParamsModel.closeConnection()" + e);
        }
    }

}
