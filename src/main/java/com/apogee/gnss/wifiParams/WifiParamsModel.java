/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.wifiParams;

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
public class WifiParamsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveWifiParams(String IP, String portNo, String url, String ssid, String ssid_password, String username, String passwd, String defaultConfig, String mountPoint, String name, String remark) {
        int rowsAffected = 0;
        String query = "";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        query = "SELECT MAX(wifiparams_id) AS max_wifiparams_id FROM wifiparams;";
        try {
            if (!IP.trim().isEmpty() && !portNo.trim().isEmpty() && !url.trim().isEmpty() && !ssid.trim().isEmpty() && !ssid_password.trim().isEmpty() && !username.trim().isEmpty() && !passwd.trim().isEmpty() && !mountPoint.trim().isEmpty()) {
                pstmt = connection.prepareStatement(query);
                rs = pstmt.executeQuery();
                int max_wifiparams_id = 0;
                while (rs.next()) {
                    max_wifiparams_id = rs.getInt("max_wifiparams_id") + 1;
                }
                String wifiparams_name = "wifiparams".concat("_" + max_wifiparams_id);
                query = "insert into wifiparams(wifiparams_name,IP,portNo,url,ssid,username,passwd,defaultConfig,mountPoint,active,"
                        + "created_by,created_at,remark,revision_no,ssid_password) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, wifiparams_name);
                pstmt.setString(2, IP);
                pstmt.setString(3, portNo);
                pstmt.setString(4, url);
                pstmt.setString(5, ssid);
                pstmt.setString(6, username);
                pstmt.setString(7, passwd);
                pstmt.setString(8, defaultConfig);
                pstmt.setString(9, mountPoint);
                pstmt.setString(10, "Y");
                pstmt.setString(11, name);
                pstmt.setString(12, time_stamp);
                pstmt.setString(13, remark);
                pstmt.setInt(14, 0);
                pstmt.setString(15, ssid_password);
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
            System.out.println("com.apogee.gnss.model.WifiParamsModel.saveWifiParams()" + e);
        }
    }

    public List<WifiParamsBean> getWifiParams(String portNo) {
        ResultSet stmt = null;
        PreparedStatement pstmt = null;
        List<WifiParamsBean> getWifiParams = new ArrayList<>();
        String query = " select *  FROM wifiparams  WHERE active = 'Y'";
        if (portNo != null && !portNo.isEmpty()) {
            query += "AND portNo ='" + portNo + "'";
        }
        query += "  ORDER BY wifiparams_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                WifiParamsBean bean = new WifiParamsBean();
                bean.setWifiparams_name(stmt.getString("wifiparams_name"));
                bean.setIP(stmt.getString("IP"));
                bean.setPortNo(stmt.getString("portNo"));
                bean.setUrl(stmt.getString("url"));
                bean.setSsid(stmt.getString("ssid"));
                bean.setSsid_password(stmt.getString("ssid_password"));
                bean.setUsername(stmt.getString("username"));
                bean.setPasswd(stmt.getString("passwd"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setMountPoint(stmt.getString("mountPoint"));
                bean.setRemark(stmt.getString("remark"));
                getWifiParams.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.WifiParamsModel.getWifiParams()" + e);
        }
        return getWifiParams;
    }

    public List allPortNumber() {
        ResultSet stmt = null;
        PreparedStatement pstmt = null;
        List allPortNumber = new ArrayList<>();
        String query = " select distinct portNo FROM wifiparams  WHERE active = 'Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                allPortNumber.add(stmt.getString("portNo"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.WifiParamsModel.allPortNumber()" + e);
        }
        return allPortNumber;
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
            System.out.println("com.apogee.gnss.model.WifiParamsModel.closeConnection()" + e);
        }
    }

}
