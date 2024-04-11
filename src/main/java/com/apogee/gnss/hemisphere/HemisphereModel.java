/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.hemisphere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class HemisphereModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveHemisphere(String hemisphere, String remark, String log_in_person) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!hemisphere.trim().isEmpty()) {
                String query = "insert into hemisphere(zoneHemisphere, remark, created_by) values(?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, hemisphere);
                pstmt.setString(2, remark);
                pstmt.setString(3, log_in_person);
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
            System.out.println("com.apogee.gnss.model.HemisphereModel.saveHemisphere()" + e);
        }
    }

    public List<HemisphereBean> getAllHemisphere(String hemisphere_id) {
        List<HemisphereBean> getAllHemisphere = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "SELECT hemisphere_id, zoneHemisphere, remark FROM hemisphere WHERE active = 'Y'";
        if (hemisphere_id != null && !hemisphere_id.isEmpty()) {
            query += " AND hemisphere_id = '" + hemisphere_id + "'";
        }
        query += " ORDER BY hemisphere_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                HemisphereBean hemisphere = new HemisphereBean();
                hemisphere.setHemisphere_id(stmt.getInt("hemisphere_id"));
                hemisphere.setZoneHemisphere(stmt.getString("zoneHemisphere"));
                hemisphere.setRemark(stmt.getString("remark"));
                getAllHemisphere.add(hemisphere);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.HemisphereModel.getAllHemisphere()" + e);
        }
        return getAllHemisphere;
    }

    public List<HemisphereBean> hemisphereList() {
        List<HemisphereBean> hemisphereList = new ArrayList<>();
        String query = " select hemisphere_id, zoneHemisphere "
                + " FROM hemisphere "
                + " WHERE active = 'Y' order by hemisphere_id desc ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();
            while (stmt.next()) {
                HemisphereBean hemisphere = new HemisphereBean();
                hemisphere.setHemisphere_id(stmt.getInt("hemisphere_id"));
                hemisphere.setZoneHemisphere(stmt.getString("zoneHemisphere"));
                hemisphereList.add(hemisphere);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.HemisphereModel.hemisphereList()" + e);
        }
        return hemisphereList;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.HemisphereModel.closeConnection()" + e);
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
