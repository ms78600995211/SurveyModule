/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectCodeListMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProjectCodeListMappingModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveMappingData(String project_details_id, String code_list_id, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            String query = "insert into project_details_code_list_mapping(project_details_id,code_list_id,active,created_by,remark,revision_no) values(?,?,?,?,?,?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, project_details_id);
            pstmt.setString(2, code_list_id);
            pstmt.setString(3, "Y");
            pstmt.setString(4, name);
            pstmt.setString(5, remark);
            pstmt.setInt(6, 0);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.mapping.ProjectCodeListMappingModel.saveMappingData()" + e);
        }
    }

    public List<ProjectCodeListMappingBean> getAllProjectMapping(String project_details_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectCodeListMappingBean> getAllProjectMap = new ArrayList<>();
        String query = "select cl.title, pd.project_name,pdc.remark from project_details_code_list_mapping pdc "
                + "inner join code_list cl on cl.code_list_id=pdc.code_list_id  "
                + "inner join project_details pd on pd.project_details_id=pdc.project_details_id where pdc.active='Y' ";
        if (project_details_id != null && !project_details_id.isEmpty()) {
            query += "AND pdc.project_details_id ='" + project_details_id + "'";
        }
        query += "  ORDER BY pdc.project_details_code_list_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectCodeListMappingBean bean = new ProjectCodeListMappingBean();
                bean.setTitle(stmt.getString("title"));
                bean.setProject_name(stmt.getString("project_name"));
                bean.setRemark(stmt.getString("remark"));
                getAllProjectMap.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.mapping.ProjectCodeListMappingModel.getAllProjectMapping()" + e);
        }
        return getAllProjectMap;
    }

    public List<ProjectCodeListMappingBean> getAllProjects() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectCodeListMappingBean> all_projects = new ArrayList<>();
        String query = " select distinct  project_details_id, project_name  FROM project_details  WHERE active = 'Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectCodeListMappingBean projectDetail = new ProjectCodeListMappingBean();
                projectDetail.setProject_details_id(stmt.getString("project_details_id"));
                projectDetail.setProject_name(stmt.getString("project_name"));
                all_projects.add(projectDetail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.mapping.ProjectCodeListMappingModel.getAllProjects()" + e);
        }
        return all_projects;
    }

    public List<ProjectCodeListMappingBean> getAllCodeList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectCodeListMappingBean> all_codeList = new ArrayList<>();
        String query = " select distinct code_list_id, title  FROM code_list "
                + " WHERE active = 'Y' and generation='1' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectCodeListMappingBean codeListDetail = new ProjectCodeListMappingBean();
                codeListDetail.setCode_list_id(stmt.getString("code_list_id"));
                codeListDetail.setTitle(stmt.getString("title"));
                all_codeList.add(codeListDetail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.mapping.ProjectCodeListMappingModel.getAllCodeList()" + e);
        }
        return all_codeList;
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
            System.out.println("com.apogee.gnss.mapping.ProjectCodeListMappingModel.closeConnection()" + e);
        }
    }

}
