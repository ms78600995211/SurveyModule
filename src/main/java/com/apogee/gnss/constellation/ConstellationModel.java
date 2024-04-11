/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.constellation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class ConstellationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveConstellation(String constellation, String remark, String log_in_person) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!constellation.trim().isEmpty()) {
                String query = "insert into constellation(constellation_name,remark, created_by) values(?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, constellation);
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
            System.out.println("com.apogee.gnss.model.ConstellationModel.saveConstellation()" + e);
        }

    }

    public List<ConstellationBean> getConstellation(String constellation_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ConstellationBean> getConstellation = new ArrayList<>();
        String query = "SELECT constellation_id, constellation_name, remark FROM constellation WHERE active = 'Y'";
        if (constellation_id != null && !constellation_id.isEmpty()) {
            query += " AND constellation_id = '" + constellation_id + "'";
        }
        query += " ORDER BY constellation_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ConstellationBean constellation = new ConstellationBean();
                constellation.setConstellation_id(stmt.getInt("constellation_id"));
                constellation.setConstellation_name(stmt.getString("constellation_name"));
                constellation.setRemark(stmt.getString("remark"));
                getConstellation.add(constellation);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ConstellationModel.getConstellation()" + e);
        }
        return getConstellation;
    }

    public List<ConstellationBean> allConstellation() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ConstellationBean> allConstellation = new ArrayList<>();
        String query = " select constellation_id, constellation_name "
                + " FROM constellation "
                + " WHERE active = 'Y' order by constellation_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ConstellationBean constellation = new ConstellationBean();
                constellation.setConstellation_id(stmt.getInt("constellation_id"));
                constellation.setConstellation_name(stmt.getString("constellation_name"));
                allConstellation.add(constellation);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ConstellationModel.getConstellation1()" + e);
        }
        return allConstellation;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ConstellationModel.closeConnection()" + e);
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
