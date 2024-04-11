/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectionType;

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
public class ProjectionTypeModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveProjectionType(String projetionType, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            if (!projetionType.trim().isEmpty()) {
                String query = "insert into projectiontype(projectionType, active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, projetionType);
                pstmt.setString(2, "Y");
                pstmt.setString(3, name);
                pstmt.setString(4, time_stamp);
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

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectionTypeModel.saveProjectionType()" + e);
        }
    }

    public List<ProjectionTypeBean> getAllProjectionType(String projectiontype_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectionTypeBean> getAllProjectionType = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(" select projectiontype_id, projectionType,remark  FROM projectiontype WHERE active = 'Y'");
        String query1 = "  and projectiontype_id='" + projectiontype_id + "'";
        if (projectiontype_id != null && !projectiontype_id.isEmpty()) {
            builder.append(query1);

        }
        builder.append("  order by projectiontype_id desc ");
        try {
            pstmt = connection.prepareStatement(builder.toString());
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ProjectionTypeBean projectType = new ProjectionTypeBean();
                projectType.setProjectiontype_id(stmt.getInt("projectiontype_id"));
                projectType.setProjectionType(stmt.getString("projectionType"));
                projectType.setRemark(stmt.getString("remark"));
                getAllProjectionType.add(projectType);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectionTypeModel.getAllProjectionType()" + e);
        }
        return getAllProjectionType;
    }

    public List<ProjectionTypeBean> projectionTypeList() {
        List<ProjectionTypeBean> projectionTypeList = new ArrayList<>();

        String query = " select projectiontype_id, projectionType "
                + " FROM projectiontype "
                + " WHERE active = 'Y' order by projectiontype_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ProjectionTypeBean projectType = new ProjectionTypeBean();
                projectType.setProjectiontype_id(stmt.getInt("projectiontype_id"));
                projectType.setProjectionType(stmt.getString("projectionType"));
                projectionTypeList.add(projectType);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectionTypeModel.projectionTypeList()" + e);
        }
        return projectionTypeList;
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
            System.out.println("com.apogee.gnss.model.ProjectionTypeModel.closeConnection()" + e);
        }
    }

}
