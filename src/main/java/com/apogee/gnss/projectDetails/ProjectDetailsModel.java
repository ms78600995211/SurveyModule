/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectDetails;

import com.apogee.gnss.projectStatus.ProjectStatusBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProjectDetailsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public String getSiteCalId(String scale, String angle, String Tx, String Ty, String Fixed_Easting, String Fixed_Northing, String sigmaZ) {
        String query = "select siteCal_id from site_calibration where Scale= ? and Angle= ? and Tx= ? and Ty= ? and Fixed_Easting= ? and Fixed_Northing= ? and sigmaZ= ? and active='Y' ";
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String siteCal_id = "";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, scale);
            pstmt.setString(2, angle);
            pstmt.setString(3, Tx);
            pstmt.setString(4, Ty);
            pstmt.setString(5, Fixed_Easting);
            pstmt.setString(6, Fixed_Northing);
            pstmt.setString(7, sigmaZ);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                siteCal_id = stmt.getString("siteCal_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getSiteCalId()" + e);
        }
        return siteCal_id;
    }

    public void saveProjectMappingData(String project_name, String operator, String comment, String siteCal_id, String project_configuration_mapping_id, String name, String remark) {
//    public void saveProjectMappingData(String project_name, String operator, String comment, String folder_id, String project_status_id, String name, String remark) {

        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String query1 = " select count(*) as count from project_details where project_name =?  and active = 'Y' ;";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, project_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count > 0) {
                message = project_name + "  Already Exist.";
                color = COLOR_ERROR;
            } else {
                if (!project_name.trim().isEmpty()) {

//            String query = "insert into project_details(project_name,operator,comment,folder_id,siteCal_id,project_status_id,remark,active,revision_no,create_by) values(?,?,?,?,?,?,?,?,?,?);";
                    String query = "insert into project_details(project_name,operator,comment,siteCal_id,project_configuration_mapping_id,remark,active,revision_no,create_by) values(?,?,?,?,?,?,?,?,?);";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, project_name);
                    pstmt.setString(2, operator);
                    pstmt.setString(3, comment);
                    pstmt.setString(4, siteCal_id);
                    pstmt.setString(5, project_configuration_mapping_id);
                    pstmt.setString(6, remark);
                    pstmt.setString(7, "Y");
                    pstmt.setString(8, "0");
                    pstmt.setString(9, name);
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
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.saveProjectMappingData()" + e);
        }

    }

    public List<ProjectDetailsBean> getAllProjectdetais(String operator) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectDetailsBean> getAllProjectMap = new ArrayList<>();
        String query = "select  pd.project_name,pd.operator,pd.comment,sc.Scale,sc.Angle,sc.Tx,sc.Ty,sc.Fixed_Easting,sc.Fixed_Northing,sc.sigmaZ,pcm.project_configuration_mapping_name,pd.remark from \n"
                + "project_details pd\n"
                + "inner join site_calibration sc on sc.siteCal_id=pd.siteCal_id\n"
                + "inner join project_configuration_mapping pcm on pcm.project_configuration_mapping_id=pd.project_configuration_mapping_id\n"
                + " where pd.active='Y' and sc.active='Y' AND pcm.active='Y' ";
        if (operator != null && !operator.isEmpty()) {
            query += "  and  pd.operator= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (operator != null && !operator.isEmpty()) {
                pstmt.setString(1, operator);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectDetailsBean bean = new ProjectDetailsBean();
                bean.setProject_name(stmt.getString("project_name"));
                bean.setOperator(stmt.getString("operator"));
                bean.setComment(stmt.getString("comment"));
                bean.setScale(stmt.getString("Scale"));
                bean.setAngle(stmt.getString("Angle"));
                bean.setTx(stmt.getString("Tx"));
                bean.setTy(stmt.getString("Ty"));
                bean.setFixed_Easting(stmt.getString("Fixed_Easting"));
                bean.setFixed_Northing(stmt.getString("Fixed_Northing"));
                bean.setSigmaZ(stmt.getString("sigmaZ"));
                bean.setScale(stmt.getString("Scale"));
                bean.setProject_configuration_mapping_name(stmt.getString("project_configuration_mapping_name"));
                bean.setRemark(stmt.getString("remark"));
                getAllProjectMap.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllProjectdetais()" + e);
        }
        return getAllProjectMap;
    }

    public List<ProjectDetailsBean> getAllOperator() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectDetailsBean> getAllOperator = new ArrayList<>();
        String query = "select distinct pd.operator"
                + " from project_details pd where pd.active='Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectDetailsBean bean = new ProjectDetailsBean();
                bean.setOperator(stmt.getString("operator"));
                getAllOperator.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllOperator()" + e);
        }
        return getAllOperator;
    }

    public List<ProjectDetailsBean> getAngleList(String scale) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectDetailsBean> getAllAngle = new ArrayList<>();
        String query = "select Angle from site_calibration  "
                + "where active='Y' and Scale= ? order by siteCal_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, scale);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectDetailsBean bean = new ProjectDetailsBean();
                bean.setAngle(stmt.getString("Angle"));
                getAllAngle.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAngleList()" + e);
        }
        return getAllAngle;
    }

    public List<ProjectDetailsBean> getSiteCalList(String scale, String Angle, String Tx, String Ty, String Fixed_Easting, String Fixed_Northing) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectDetailsBean> getSiteCalList = new ArrayList<>();
        String query = "select  Scale,Angle,Tx,Ty,Fixed_Easting,Fixed_Northing,sigmaZ from site_calibration where active='Y'  ";
        if (scale != null && !scale.isEmpty()) {
            query += " and Scale= ?";
        }
        if (Angle != null && !Angle.isEmpty()) {
            query += " and Angle= ?";
        }
        if (Tx != null && !Tx.isEmpty()) {
            query += "and Tx= ?";
        }
        if (Ty != null && !Ty.isEmpty()) {
            query += " and Ty= ?";
        }
        if (Fixed_Easting != null && !Fixed_Easting.isEmpty()) {
            query += " and Fixed_Easting= ? ";
        }
        if (Fixed_Northing != null && !Fixed_Northing.isEmpty()) {
            query += " and Fixed_Northing= ?";
        }
        query += "  order by siteCal_id desc ";
        try {
            pstmt = connection.prepareStatement(query.toString());
            if (scale != null && !scale.isEmpty()) {
                pstmt.setString(1, scale);
            }
            if (Angle != null && !Angle.isEmpty()) {
                pstmt.setString(2, Angle);
            }
            if (Tx != null && !Tx.isEmpty()) {
                pstmt.setString(3, Tx);
            }
            if (Ty != null && !Ty.isEmpty()) {
                pstmt.setString(4, Ty);
            }
            if (Fixed_Easting != null && !Fixed_Easting.isEmpty()) {
                pstmt.setString(5, Fixed_Easting);
            }
            if (Fixed_Northing != null && !Fixed_Northing.isEmpty()) {
                pstmt.setString(6, Fixed_Northing);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectDetailsBean bean = new ProjectDetailsBean();
                bean.setScale(stmt.getString("Scale"));
                bean.setAngle(stmt.getString("Angle"));
                bean.setTx(stmt.getString("Tx"));
                bean.setTy(stmt.getString("Ty"));
                bean.setFixed_Easting(stmt.getString("Fixed_Easting"));
                bean.setFixed_Northing(stmt.getString("Fixed_Northing"));
                bean.setSigmaZ(stmt.getString("sigmaZ"));
                getSiteCalList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getSiteCalList()" + e);
        }
        return getSiteCalList;
    }

    public List<ProjectDetailsBean> getScaledetails() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectDetailsBean> getScaledetails = new ArrayList<>();
        String query = "select siteCal_id,Scale from site_calibration where active='Y' order by siteCal_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectDetailsBean bean = new ProjectDetailsBean();
                bean.setSiteCal_id(stmt.getString("siteCal_id"));
                bean.setScale(stmt.getString("Scale"));
                getScaledetails.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getScaledetails()" + e);
        }
        return getScaledetails;
    }

    public List<ProjectStatusBean> getAllStatus() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectStatusBean> all_status = new ArrayList<>();
        String query = " select project_status_id, project_status  FROM project_status "
                + " WHERE active = 'Y' order by project_status_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectStatusBean status = new ProjectStatusBean();
                status.setProject_status_id(stmt.getInt("project_status_id"));
                status.setStatus(stmt.getString("project_status"));
                all_status.add(status);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllStatus()" + e);
        }
        return all_status;
    }

    public List<ProjectDetailsBean> getAllFolders() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectDetailsBean> all_folder = new ArrayList<>();
        String query = " select folder_id, folder_name FROM project_folder "
                + " WHERE active = 'Y' order by folder_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectDetailsBean folder_detail = new ProjectDetailsBean();
                folder_detail.setFolder_id(stmt.getString("folder_id"));
                folder_detail.setFolder_name(stmt.getString("folder_name"));
                all_folder.add(folder_detail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllFolders()" + e);
        }
        return all_folder;
    }

    public List getProjectConfigurationMappingList() {
        List<ProjectDetailsBean> getProjectConfigurationMappingList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select project_configuration_mapping_id,project_configuration_mapping_name from project_configuration_mapping where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectDetailsBean bean = new ProjectDetailsBean();
                bean.setProject_configuration_mapping_id(stmt.getString("project_configuration_mapping_id"));
                bean.setProject_configuration_mapping_name(stmt.getString("project_configuration_mapping_name"));
                getProjectConfigurationMappingList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getProjectConfigurationMappingList()" + e);
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
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.closeConnection()" + e);
        }
    }

}
