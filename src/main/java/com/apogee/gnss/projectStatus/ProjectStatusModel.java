/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectStatus;

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
public class ProjectStatusModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

//    public void saveProjectStatus(String status, String description, int status_type_id, String remark, String log_in_person) {
    public void saveProjectStatus(String status, String description, String remark, String log_in_person) {

        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!status.trim().isEmpty()) {

//                String query = "insert into project_status(project_status, description, status_type_id, remark, created_by) values(?,?,?,?,?);";
                String query = "insert into project_status(project_status, description,  remark, created_by) values(?,?,?,?);";
                pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, status);
                pstmt.setString(2, description);
//                pstmt.setInt(3, status_type_id);
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
            System.out.println("com.apogee.gnss.model.ProjectStatusModel.saveProjectStatus()" + e);
        }

    }

    public List<StatusTypeBean> getAllStatus() {
        List<StatusTypeBean> getAllStatusType = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = " select project_status_id, project_status, description "
                + " FROM project_status "
                + " WHERE active = 'Y' order by project_status_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                StatusTypeBean statusType = new StatusTypeBean();
                statusType.setStatus_type_id(stmt.getInt("project_status_id"));
                statusType.setStatus_type(stmt.getString("project_status"));
                statusType.setDescription(stmt.getString("description"));
                getAllStatusType.add(statusType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectStatusModel.getAllStatusType()" + e);
        }
        return getAllStatusType;
    }

    
    public List<ProjectStatusBean> getProjectStatList() {
        List<ProjectStatusBean> getProjectStatList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = " select project_status_id, project_status FROM project_status "
                + " WHERE active = 'Y' order by project_status_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectStatusBean bean = new ProjectStatusBean();
                bean.setProject_status_id(stmt.getInt("project_status_id"));
                bean.setStatus(stmt.getString("project_status"));
                getProjectStatList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectStatusModel.getProjectStatList()" + e);
        }
        return getProjectStatList;
    }
     
    public List<ProjectStatusBean> getAllProjectStatus(String project_status_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectStatusBean> getAllProjectStatus = new ArrayList<>();
        /*
        String query = "SELECT ps.project_status_id, ps.status, ps.description, ps.remark, st.status_type "
                + "FROM project_status AS ps "
                + "JOIN status_type AS st ON st.status_type_id = ps.status_type_id WHERE ps.active = 'Y' ";
         */

        String query = "SELECT project_status_id, project_status, description, remark "
                + " FROM project_status WHERE active = 'Y' ";

        if (project_status_id != null && !project_status_id.isEmpty()) {
            query += "AND project_status_id = '" + project_status_id + "' ";
        }
        query += "ORDER BY project_status_id DESC ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectStatusBean projectStatus = new ProjectStatusBean();
                projectStatus.setProject_status_id(stmt.getInt("project_status_id"));
                projectStatus.setStatus(stmt.getString("project_status"));
                projectStatus.setDescription(stmt.getString("description"));
//                projectStatus.setStatus_type(stmt.getString("status_type"));
                projectStatus.setRemark(stmt.getString("remark"));
                getAllProjectStatus.add(projectStatus);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectStatusModel.getAllProjectStatus()" + e);
        }
        return getAllProjectStatus;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectStatusModel.closeConnection()" + e);
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
