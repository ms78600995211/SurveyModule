/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.dynamicModeData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class DynamicModeDataModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveDynamicModeData(String device_work_mode_name, String mode_value, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            if (!device_work_mode_name.trim().isEmpty() && !mode_value.trim().isEmpty()) {
                String query = "insert into dynamic_mode_data(device_work_mode_name,mode_value,remark,active,revision_no,created_by) values(?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, device_work_mode_name);
                pstmt.setString(2, mode_value);
                pstmt.setString(3, remark);
                pstmt.setString(4, "Y");
                pstmt.setInt(5, 0);
                pstmt.setString(6, name);
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
            System.out.println("com.apogee.gnss.model.DynamicModeDataModel.saveDynamicModeData()" + e);
        }
    }

    public List<DynamicModeDataBean> getDynamicModeData(String mode_value) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicModeDataBean> getDynamicModeData = new ArrayList<>();
        String query = "SELECT * FROM dynamic_mode_data WHERE active = 'Y'";
        if (mode_value != null && !mode_value.isEmpty()) {
            query += " AND mode_value = '" + mode_value + "'";
        }
        query += " ORDER BY dynamic_mode_data_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicModeDataBean bean = new DynamicModeDataBean();
                bean.setDevice_work_mode_name(stmt.getString("device_work_mode_name"));
                bean.setMode_value(stmt.getString("mode_value"));
                bean.setRemark(stmt.getString("remark"));
                getDynamicModeData.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DynamicModeDataModel.getDynamicModeData()" + e);
        }
        return getDynamicModeData;
    }

    public List allMode_value() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List allMode_value = new ArrayList<>();
        String query = " select distinct mode_value  FROM dynamic_mode_data "
                + " WHERE active = 'Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                allMode_value.add(stmt.getString("mode_value"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DynamicModeDataModel.allMode_value()" + e);
        }
        return allMode_value;
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
            System.out.println("com.apogee.gnss.model.DynamicModeDataModel.closeConnection()" + e);
        }
    }

}
