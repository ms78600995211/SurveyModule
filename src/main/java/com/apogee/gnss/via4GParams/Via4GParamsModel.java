/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.via4GParams;

import com.apogee.gnss.via4GParams.Via4GParamsBean;
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
public class Via4GParamsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveVia4GParams(String IP, String portNo, String url,
            String username, String passwd, String defaultConfig, String mountPoint, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "";
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        query = "SELECT MAX(via4gparams_id) AS max_via4gparams_id FROM via4gparams;";
        try {
            if (!IP.trim().isEmpty() && !portNo.trim().isEmpty() && !username.trim().isEmpty() && !passwd.trim().isEmpty() && !mountPoint.trim().isEmpty()) {
                pstmt = connection.prepareStatement(query);
                rs = pstmt.executeQuery();
                int max_via4gparams_id = 0;
                while (rs.next()) {
                    max_via4gparams_id = rs.getInt("max_via4gparams_id") + 1;
                }
                String via4gparams_name = "via4gparams".concat("_" + max_via4gparams_id);
                query = "insert into via4gparams(via4gparams_name,IP,portNo,url,username,passwd,"
                        + "defaultConfig,mountPoint,active,created_by,created_at,remark,revision_no)"
                        + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, via4gparams_name);
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
            System.out.println("com.apogee.gnss.model.Via4GParamsModel.saveVia4GParams()" + e);
        }
    }

    public List<Via4GParamsBean> getVia4GParams(String portNo) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<Via4GParamsBean> getVia4GParams = new ArrayList<>();
        String query = " select *  FROM via4gparams WHERE active = 'Y'";
        if (portNo != null && !portNo.isEmpty()) {
            query += "AND portNo ='" + portNo + "'";
        }
        query += "  ORDER BY via4gparams_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                Via4GParamsBean bean = new Via4GParamsBean();
                bean.setVia4gparams_name(stmt.getString("via4gparams_name"));
                bean.setIP(stmt.getString("IP"));
                bean.setPortNo(stmt.getString("portNo"));
                bean.setUrl(stmt.getString("url"));
                bean.setUsername(stmt.getString("username"));
                bean.setPasswd(stmt.getString("passwd"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setMountPoint(stmt.getString("mountPoint"));
                bean.setRemark(stmt.getString("remark"));
                getVia4GParams.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.Via4GParamsModel.getVia4GParams()" + e);
        }
        return getVia4GParams;
    }

    public List allPortNumber() {
        ResultSet stmt = null;
        PreparedStatement pstmt = null;
        List allPortNumber = new ArrayList<>();
        String query = " select distinct portNo FROM via4gparams  WHERE active = 'Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                allPortNumber.add(stmt.getString("portNo"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.Via4GParamsModel.allPortNumber()" + e);
        }
        return allPortNumber;
    }

    public String getMessage() {
        return message;
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
            System.out.println("com.apogee.gnss.model.Via4GParamsModel.closeConnection()" + e);
        }
    }

}
