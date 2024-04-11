/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProjectFileModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveProjectFile(String file_name, String folder_id, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            String query = "insert into project_file(file_name,file_type_id,folder_id, active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, file_name);
            pstmt.setString(2, "1");
            pstmt.setString(3, folder_id);
            pstmt.setString(4, "Y");
            pstmt.setString(5, name);
            pstmt.setString(6, time_stamp);
            pstmt.setString(7, remark);
            pstmt.setInt(8, 0);
            rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.AngleUnitModel.saveAngleUnit()" + e);
        }
    }

    public List<ProjectFileBean> allProjectFile(String projectId) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        ResultSet stmt1 = null;
        String folderName = "";
        String project_name = "";
        List<ProjectFileBean> allProjectFile = new ArrayList<>();
        String query = "select pf.project_file_id, pf.file_name ,pf.folder_id as folder_id ,pf.remark \n"
                + "from project_file pf\n"
                + "join project_details pd on pd.folder_id=pf.folder_id\n"
                + "where pd.active='Y' and pf.active='Y' ";

        if (projectId != null && !projectId.isEmpty()) {
            query += "  and  pd.project_details_id= ?";
        }

        try {
            pstmt = connection.prepareStatement(query);
            if (projectId != null && !projectId.isEmpty()) {
                pstmt.setString(1, projectId);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectFileBean projectFile = new ProjectFileBean();
                projectFile.setProject_file_id(stmt.getString("project_file_id"));
                projectFile.setFile_name(stmt.getString("file_name"));
                String folder_id = stmt.getString("folder_id");
                String query1 = " select folder_name FROM project_folder "
                        + " WHERE active = 'Y' and folder_id=" + folder_id;

                pstmt = connection.prepareStatement(query1);
                stmt1 = pstmt.executeQuery();
                while (stmt1.next()) {
                    folderName = stmt1.getString("folder_name");
                }

                String query2 = " select project_name FROM project_details "
                        + " WHERE active = 'Y' and folder_id=" + folder_id;

                pstmt = connection.prepareStatement(query2);
                stmt1 = pstmt.executeQuery();
                while (stmt1.next()) {
                    project_name = stmt1.getString("project_name");
                }
                projectFile.setFolder_name(folderName);
                projectFile.setProject_name(project_name);
                projectFile.setRemark(stmt.getString("remark"));
                allProjectFile.add(projectFile);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectFolder.ProjectFolderModel.allProjectFolder()" + e);
        }
        return allProjectFile;
    }

    public List<ProjectFileBean> getAllProjectDetails() {

        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectFileBean> all_projects = new ArrayList<>();
        String query = "select project_details_id , project_name from project_details ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectFileBean folder_detail = new ProjectFileBean();
                folder_detail.setProject_details_id(stmt.getString("project_details_id"));
                folder_detail.setProject_name(stmt.getString("project_name"));
                all_projects.add(folder_detail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllFolders()" + e);
        }
        return all_projects;

    }

    public List<ProjectFileBean> getAllFolders() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectFileBean> all_folder = new ArrayList<>();
        String query = " select folder_id, folder_name FROM project_folder "
                + " WHERE active = 'Y' order by folder_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectFileBean folder_detail = new ProjectFileBean();
                folder_detail.setFolder_id(stmt.getString("folder_id"));
                folder_detail.setFolder_name(stmt.getString("folder_name"));
                all_folder.add(folder_detail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllFolders()" + e);
        }
        return all_folder;
    }

    public String getFolderPath(String folder_id) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String folderPath = "";
        String query = "SELECT folder_path FROM project_folder WHERE active = 'Y' ";
        if (folder_id != null && !folder_id.isEmpty()) {
            query += "AND folder_id =? ";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (folder_id != null && !folder_id.isEmpty()) {
                pstmt.setString(1, folder_id);
            }
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                folderPath = resultSet.getString("folder_path");
            }
        } catch (SQLException e) {
            System.out.println("com.apogee.gnss.projectFolder.ProjectFolderModel.getFolderPath()" + e);
        }
        return folderPath;
    }

    public String getFilePath(String fileId) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String file_name = "";
        String folderId = "";
        String filepath = "";
        String folderpath = "";
        String query = " select  file_name,folder_id FROM project_file "
                + " WHERE active = 'Y' and project_file_id='" + fileId + "'";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                file_name = stmt.getString("file_name");
                folderId = stmt.getString("folder_id");
            }
            folderpath = getFolderPath(folderId);
            filepath = folderpath + "\\" + file_name;
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllFolders()" + e);
        }
        return filepath;
    }

    public List<ProjectFileBean> getProjectFileType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectFileBean> getProjectFileType = new ArrayList<>();
        String query = " select project_file_type_id, file_category FROM project_file_type "
                + " WHERE active = 'Y' order by project_file_type_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectFileBean fileType = new ProjectFileBean();
                fileType.setProject_file_id(stmt.getString("project_file_type_id"));
                fileType.setFile_type(stmt.getString("file_category"));
                getProjectFileType.add(fileType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsModel.getAllFolders()" + e);
        }
        return getProjectFileType;
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
            System.out.println("com.apogee.gnss.model.AngleUnitModel.closeConnection()" + e);
        }
    }

}
