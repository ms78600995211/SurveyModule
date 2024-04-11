/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.satelliteConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SatelliteConfigurationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveSatellite_configuration(String satellite_configuration_name, String defaultConfig, String remark, String name) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        ResultSet rs = null;
        int count = 0;
        try {
            String query1 = " select count(*) as count from satellite_configuration where satellite_configuration_name =?  and active = 'Y' ;";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, satellite_configuration_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count > 0) {
                message = satellite_configuration_name + "Name Already Exist.";
                color = COLOR_ERROR;
            } else {

                if (!satellite_configuration_name.trim().isEmpty()) {

                    String query = "insert into satellite_configuration(satellite_configuration_name,defaultConfig,created_by,active,remark,revision_no) values(?,?,?,?,?,?)";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, satellite_configuration_name);
                    pstmt.setString(2, defaultConfig);
                    pstmt.setString(3, name);
                    pstmt.setString(4, "Y");
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
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.SatelliteConfigurationModel.saveSatellite_configuration()" + e);
        }
    }

    public List getSatelliteConfigList(String satellite_configuration_name) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SatelliteConfigurationBean> getSatelliteConfigList = new ArrayList<>();
        String query = "select * from satellite_configuration where active='Y' ";
        if (satellite_configuration_name != null && !satellite_configuration_name.isEmpty()) {
            query += " and satellite_configuration_name= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (satellite_configuration_name != null && !satellite_configuration_name.isEmpty()) {
                pstmt.setString(1, satellite_configuration_name);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SatelliteConfigurationBean bean = new SatelliteConfigurationBean();
                bean.setSatellite_configuration_name(stmt.getString("satellite_configuration_name"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setRemark(stmt.getString("remark"));
                getSatelliteConfigList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.satelliteConfiguration.SatelliteConfigurationModel.getSatelliteConfigList()" + e);
        }
        return getSatelliteConfigList;
    }

    public List allSatelliteConfig() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List getSatelliteConfigList = new ArrayList<>();
        String query = "select distinct satellite_configuration_name from satellite_configuration where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                getSatelliteConfigList.add(stmt.getString("satellite_configuration_name"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.satelliteConfiguration.SatelliteConfigurationModel.allSatelliteConfig()" + e);
        }
        return getSatelliteConfigList;
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
            System.out.println("com.apogee.gnss.satelliteConfiguration.SatelliteConfigurationModel.closeConnection()" + e);
        }
    }

}
