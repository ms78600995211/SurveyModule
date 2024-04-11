/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.typeOfCommunication;

import com.apogee.gnss.typeOfCommunication.TypeOfCommunicationBean;
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
public class TypeOfCommunicationModel {
    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveTypeOfCommunication(String communicationTypes, String defaultConfig, String name, String remark) {
        int rowsAffected = 0;
        String query = "";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        query = "SELECT MAX(type_of_communication_id) AS max_type_of_communication_id FROM type_of_communication;";
        try {
            if (!communicationTypes.trim().isEmpty()) {
                pstmt = connection.prepareStatement(query);
                rs = pstmt.executeQuery();
                int max_type_of_communication_id = 0;
                while (rs.next()) {
                    max_type_of_communication_id = rs.getInt("max_type_of_communication_id") + 1;
                }
                String type_of_communication_name = "type_of_communication".concat("_" + max_type_of_communication_id);
                query = "insert into type_of_communication(type_of_communication_name,communicationTypes,defaultConfig, active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?,?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, type_of_communication_name);
                pstmt.setString(2, communicationTypes);
                pstmt.setString(3, defaultConfig);
                pstmt.setString(4, "Y");
                pstmt.setString(5, name);
                pstmt.setString(6, time_stamp);
                pstmt.setString(7, remark);
                pstmt.setInt(8, 0);
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
            System.out.println("com.apogee.gnss.model.TypeOfCommunicationModel.saveTypeOfCommunication()" + e);
        }
    }

    public List<TypeOfCommunicationBean> getTypeOfCommunication(String communicationTypes) {
        ResultSet stmt = null;
        PreparedStatement pstmt = null;
        List<TypeOfCommunicationBean> getTypeOfCommunication = new ArrayList<>();
        String query = "SELECT * FROM type_of_communication WHERE active = 'Y' ";
        if (communicationTypes != null && !communicationTypes.isEmpty()) {
            query += "AND communicationTypes ='" + communicationTypes + "'";
        }
        query += "  ORDER BY type_of_communication_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                TypeOfCommunicationBean bean = new TypeOfCommunicationBean();
                bean.setType_of_communication_name(stmt.getString("type_of_communication_name"));
                bean.setCommunicationTypes(stmt.getString("communicationTypes"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setRemark(stmt.getString("remark"));
                getTypeOfCommunication.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TypeOfCommunicationModel.getTypeOfCommunication()" + e);
        }
        return getTypeOfCommunication;
    }

    public List<TypeOfCommunicationBean> allTypeOfCommunication() {
        ResultSet stmt = null;
        PreparedStatement pstmt = null;
        List allTypeOfCommunication = new ArrayList<>();
        String query = " select distinct communicationTypes FROM type_of_communication WHERE active = 'Y'";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                allTypeOfCommunication.add(stmt.getString("communicationTypes"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TypeOfCommunicationModel.getTypeOfCommunication()" + e);
        }
        return allTypeOfCommunication;
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
            System.out.println("com.apogee.gnss.model.TypeOfCommunicationModel.closeConnection()" + e);
        }
    }

}
