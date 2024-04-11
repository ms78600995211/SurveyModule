/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.pdaParams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class PDAParamsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public String savePDAParams(String IP, String portNo, String url, String username, String passwd, String defaultConfig, String mountPoint, String name, String remark) {
        int rowsAffected = 0;
        String pdaparams_id = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        String query = "SELECT MAX(pdaparams_id) AS max_pdaparams_id FROM pdaparams;";
        try {
            if (!IP.trim().isEmpty() && !portNo.trim().isEmpty() && !username.trim().isEmpty() && !passwd.trim().isEmpty() && !mountPoint.trim().isEmpty()) {

                PreparedStatement pstmt1 = connection.prepareStatement(query);
                rs = pstmt1.executeQuery();
                int max_pdaparams_id = 0;
                while (rs.next()) {
                    max_pdaparams_id = rs.getInt("max_pdaparams_id");
                }
                max_pdaparams_id = max_pdaparams_id + 1;
                String pdaparams = "pdaparams";
                String primary_key = "_" + max_pdaparams_id;
                String pdaparams_name = pdaparams.concat(primary_key);
                query = "insert into pdaparams(pdaparams_name,IP,portNo,url,username,passwd,defaultConfig,mountPoint,active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, pdaparams_name);
                pstmt.setString(2, IP);
                pstmt.setString(3, portNo);
                pstmt.setString(4, url);
                pstmt.setString(5, username);
                pstmt.setString(6, passwd);
                pstmt.setString(7, defaultConfig);
                pstmt.setString(8, mountPoint);
                pstmt.setString(9, "Y");
                pstmt.setString(10, name);
                pstmt.setString(11, time_stamp);
                pstmt.setString(12, remark);
                pstmt.setInt(13, 0);
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
            System.out.println("com.apogee.gnss.model.PDAParamsModel.savePDAParams()" + e);
        }
        return pdaparams_id;
    }

    public List<PDAParamsBean> getPDAParams(String portNo) {
        List<PDAParamsBean> getPDAParams = new ArrayList<>();
        String query = " select *  FROM pdaparams WHERE active = 'Y'";
        if (portNo != null && !portNo.isEmpty()) {
            query += "AND portNo ='" + portNo + "'";
        }
        query += "  ORDER BY pdaparams_id DESC";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                PDAParamsBean bean = new PDAParamsBean();
                bean.setPdaparams_name(stmt.getString("pdaparams_name"));
                bean.setIP(stmt.getString("IP"));
                bean.setPortNo(stmt.getString("portNo"));
                bean.setUrl(stmt.getString("url"));
                bean.setUsername(stmt.getString("username"));
                bean.setPasswd(stmt.getString("passwd"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setMountPoint(stmt.getString("mountPoint"));
                bean.setRemark(stmt.getString("remark"));
                getPDAParams.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.PDAParamsModel.getPDAParams()" + e);
        }
        return getPDAParams;
    }

    public List allPortNumber() {
        ResultSet stmt = null;
        PreparedStatement pstmt = null;
        List allPortNumber = new ArrayList<>();
        String query = " select distinct portNo FROM pdaparams  WHERE active = 'Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                allPortNumber.add(stmt.getString("portNo"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.PDAParamsModel.allPortNumber()" + e);
        }
        return allPortNumber;
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
            System.out.println("com.apogee.gnss.model.PDAParamsModel.closeConnection()" + e);
        }
    }

}
