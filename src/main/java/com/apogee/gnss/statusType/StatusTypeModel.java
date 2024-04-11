/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.statusType;

import com.apogee.gnss.statusType.StatusTypeBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class StatusTypeModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveStatusType(String status_type, String description, String remark, String log_in_person) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;

        try {
            if (!status_type.trim().isEmpty()) {

                String query = "insert into status_type(status_type, description, remark, created_by) values(?,?,?,?);";
                pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, status_type);
                pstmt.setString(2, description);
                pstmt.setString(3, remark);
                pstmt.setString(4, log_in_person);
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
            System.out.println("com.apogee.gnss.model.StatusTypeModel.saveStatusType()" + e);
        }

    }

    public List<StatusTypeBean> allStatusType() {
        List<StatusTypeBean> StatusTypeListData = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = " select status_type_id, status_type "
                + " FROM status_type "
                + " WHERE active = 'Y' order by status_type_id desc ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                StatusTypeBean statusType = new StatusTypeBean();
                statusType.setStatus_type_id(stmt.getInt("status_type_id"));
                statusType.setStatus_type(stmt.getString("status_type"));
                StatusTypeListData.add(statusType);
            }

        } catch (Exception e) {

            System.out.println("com.apogee.gnss.model.StatusTypeModel.allStatusType()" + e);
        }
        return StatusTypeListData;
    }

    public List<StatusTypeBean> getAllStatusType(String status_type_id) {
        List<StatusTypeBean> statusTypeListData = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT status_type_id, status_type, description, remark FROM status_type WHERE active = 'Y' ");
        if (status_type_id != null && !status_type_id.isEmpty()) {
            queryBuilder.append("AND status_type_id = ?");
        }
        queryBuilder.append(" ORDER BY status_type_id DESC");

        try {

            pstmt = connection.prepareStatement(queryBuilder.toString());
            if (status_type_id != null && !status_type_id.isEmpty()) {
                pstmt.setString(1, status_type_id);
            }

            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                StatusTypeBean statusType = new StatusTypeBean();
                statusType.setStatus_type_id(resultSet.getInt("status_type_id"));
                statusType.setStatus_type(resultSet.getString("status_type"));
                statusType.setDescription(resultSet.getString("description"));
                statusType.setRemark(resultSet.getString("remark"));

                statusTypeListData.add(statusType);
            }

        } catch (Exception e) {

            System.out.println("com.apogee.gnss.model.StatusTypeModel.getAllStatusType()" + e);
        }

        return statusTypeListData;
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

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.StatusTypeModel.closeConnection()" + e);
        }
    }

}
