/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.continents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class ContinentsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveContinents(String continentName, String remark, String log_in_person) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            if (!continentName.trim().isEmpty()) {
                String query = "insert into continents(continent_name,remark, created_by) values(?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, continentName);
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
            System.out.println("com.apogee.gnss.model.ContinentsModel.saveContinents()" + e);
        }
    }

    public List<ContinentsBean> getAllContinents(String continent_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ContinentsBean> ContinentListData = new ArrayList<>();
        String query = "SELECT continent_id, continent_name, remark FROM continents WHERE active = 'Y'";
        if (continent_id != null && !continent_id.isEmpty()) {
            query += " AND continent_id='" + continent_id + "'";
        }
        query += " ORDER BY continent_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ContinentsBean continents = new ContinentsBean();
                continents.setContinent_id(stmt.getInt("continent_id"));
                continents.setContinent_name(stmt.getString("continent_name"));
                continents.setRemark(stmt.getString("remark"));
                ContinentListData.add(continents);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ContinentsModel.getAllContinents()" + e);
        }
        return ContinentListData;
    }

    public List<ContinentsBean> allContinents() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ContinentsBean> allContinents = new ArrayList<>();
        String query = " select continent_id, continent_name "
                + " FROM continents "
                + " WHERE active = 'Y' order by continent_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ContinentsBean continents = new ContinentsBean();
                continents.setContinent_id(stmt.getInt("continent_id"));
                continents.setContinent_name(stmt.getString("continent_name"));
                allContinents.add(continents);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ContinentsModel.allContinents()" + e);
        }
        return allContinents;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ContinentsModel.closeConnection()" + e);
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
