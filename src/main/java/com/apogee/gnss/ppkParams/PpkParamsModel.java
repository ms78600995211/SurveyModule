/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.ppkParams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class PpkParamsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void savePpkParams(String file_name, String total_time, String name, String remark, String defaultConfig) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            if (!file_name.trim().isEmpty() && !total_time.trim().isEmpty()) {
                String query = "insert into ppk_params(file_name,total_time,remark,active,revision_no,created_by,defaultConfig) values(?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, file_name);
                pstmt.setString(2, total_time);
                pstmt.setString(3, remark);
                pstmt.setString(4, "Y");
                pstmt.setInt(5, 0);
                pstmt.setString(6, name);
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
            System.out.println("com.apogee.gnss.model.PpkParamsModel.savePpkParams()" + e);
        }
    }

    public List<PpkParamsBean> getAllPpkParsms(String total_time) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<PpkParamsBean> ppkParamsList = new ArrayList<>();
        String query = " select * FROM ppk_params WHERE active = 'Y'  ";
        if (total_time != null && !total_time.isEmpty()) {
            query += " and total_time='" + total_time + "'";
        }
        query += " order by ppk_params_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                PpkParamsBean bean = new PpkParamsBean();
                bean.setFile_name(stmt.getString("file_name"));
                bean.setTotal_time(stmt.getString("total_time"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setRemarks(stmt.getString("remark"));
                ppkParamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.PpkParamsModel.getAllPpkParsms()" + e);
        }
        return ppkParamsList;
    }

    public List allTotal_time() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List allTotal_time = new ArrayList<>();
        String query = " select distinct total_time FROM ppk_params "
                + " WHERE active = 'Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                allTotal_time.add(stmt.getString("total_time"));
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.PpkParamsModel.allTotal_time()" + e);
        }
        return allTotal_time;
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
            System.out.println("com.apogee.gnss.model.PpkParamsModel.closeConnection()" + e);
        }
    }

}
