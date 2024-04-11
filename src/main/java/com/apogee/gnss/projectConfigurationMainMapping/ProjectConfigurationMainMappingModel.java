/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectConfigurationMainMapping;

import com.apogee.gnss.projectDetails.ProjectDetailsBean;
import com.apogee.gnss.satelliteConfigurationConstellationMapping.SatelliteConfigurationConstellationMappingBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProjectConfigurationMainMappingModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveSatelliteConfiguConstellationMappingData(ProjectConfigurationMainMappingBean bean ) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
                String query = "insert into project_configuration_main_mapping(project_details_id,project_configuration_mapping_id,remark,active,revision_no,create_by)"
                        + " values(?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1,bean.getProject_details_id());
                pstmt.setString(2,bean.getProject_configuration_mapping_id());
                pstmt.setString(3, bean.getRemark());
                pstmt.setString(4, "Y");
                pstmt.setInt(5, 0);
                pstmt.setString(6, bean.getName());
                rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.NtripBaseRegistrationModel.saveBaseRegData()" + e);
        }
    }

    public List<ProjectConfigurationMainMappingBean> getAllProjectConfigurationMainMapping() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectConfigurationMainMappingBean> getAllProjectMap = new ArrayList<>();
        String query = "select pd.project_name,pcm.project_configuration_mapping_name,pcmm.remark \n"
                + "from project_configuration_main_mapping pcmm \n"
                + "join project_details pd on pd.project_details_id=pcmm.project_details_id\n"
                + "join project_configuration_mapping pcm on pcm.project_configuration_mapping_id=pcmm.project_configuration_mapping_id\n"
                + "where pcmm.active=\"Y\" AND pd.active=\"Y\";";
        try {
            pstmt = connection.prepareStatement(query);

            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectConfigurationMainMappingBean bean = new ProjectConfigurationMainMappingBean();
                bean.setProject_name(stmt.getString("project_name"));
                bean.setProject_configuration_mapping_name(stmt.getString("project_configuration_mapping_name"));
                bean.setRemark(stmt.getString("remark"));
                getAllProjectMap.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMainMapping.ProjectConfigurationMainMappingModel.getAllProjectConfigurationMainMapping()" + e);
        }
        return getAllProjectMap;
    }

    public List getProjectDetailsList() {
        List<ProjectConfigurationMainMappingBean> getProjectDetailsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select project_details_id,project_name from project_details where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectConfigurationMainMappingBean bean = new ProjectConfigurationMainMappingBean();
                bean.setProject_details_id(stmt.getString("project_details_id"));
                bean.setProject_name(stmt.getString("project_name"));
                getProjectDetailsList.add(bean);
            }
        } catch (Exception e) {

            System.out.println("com.apogee.gnss.projectConfigurationMainMapping.ProjectConfigurationMainMappingModel.getProjectDetailsList()" + e);
        }
        return getProjectDetailsList;
    }

    public List getProjectConfigurationMappingList() {
        List<ProjectConfigurationMainMappingBean> getProjectConfigurationMappingList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select project_configuration_mapping_id,project_configuration_mapping_name from project_configuration_mapping where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectConfigurationMainMappingBean bean = new ProjectConfigurationMainMappingBean();
                bean.setProject_configuration_mapping_id(stmt.getString("project_configuration_mapping_id"));
                bean.setProject_configuration_mapping_name(stmt.getString("project_configuration_mapping_name"));
                getProjectConfigurationMappingList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.getSatellite_configurationList()" + e);
        }
        return getProjectConfigurationMappingList;
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
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.closeConnection()" + e);
        }
    }

}
