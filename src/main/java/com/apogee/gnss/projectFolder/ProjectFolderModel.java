/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectFolder;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
public class ProjectFolderModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;
    private String path1 = "C:\\ssadvt_repository\\SurveyModule\\ProjectFolder\\";

    public void saveProjectFolderInfo(String folder_name, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        ResultSet rs = null;
        int count = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        path1 = path1 + "\\" + folder_name;
        makedir(path1);
        try {

            String query1 = " select count(*) as count from project_folder where folder_name =?  and active = 'Y' ;";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, folder_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count > 0) {
                message = folder_name + " Already Exist.";
                color = COLOR_ERROR;
            } else {

                if (!folder_name.trim().isEmpty()) {
                    String query = "INSERT INTO project_folder(folder_name, folder_path, active, created_by, created_at, remark, revision_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, folder_name);
                    pstmt.setString(2, path1);
                    pstmt.setString(3, "Y");
                    pstmt.setString(4, name);
                    pstmt.setString(5, time_stamp);
                    pstmt.setString(6, remark);
                    pstmt.setInt(7, 0);
                    rowsAffected = pstmt.executeUpdate();
                }

                if (rowsAffected > 0) {
                    message = "Data Added Successfully.";
                    color = COLOR_OK;
                } else {
                    message = "Some Error Occurred. Please Try Again.";
                    color = COLOR_ERROR;
                }
            }
        } catch (SQLException e) {

            System.out.println("com.apogee.gnss.projectFolder.ProjectFolderModel.saveProjectFolderInfo()" + e);

        }
    }

    public static void makedir(String filepath) {
        File directory = new File(filepath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Directory created successfully: " + filepath);
            } else {
                System.out.println("Failed to create directory: " + filepath);
            }
        } else {
            System.out.println("Directory already exists: " + filepath);
        }
    }

    public List<ProjectFolderBean> allProjectFolder() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectFolderBean> allProjectFolder = new ArrayList<>();
        String query = " select folder_id, folder_name ,folder_path ,remark"
                + " FROM project_folder "
                + " WHERE active = 'Y' order by folder_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectFolderBean projectFolder = new ProjectFolderBean();
                projectFolder.setFolderId(stmt.getInt("folder_id"));
                projectFolder.setFolderName(stmt.getString("folder_name"));
                projectFolder.setFolderPath(stmt.getString("folder_path"));
                projectFolder.setRemark(stmt.getString("remark"));
                allProjectFolder.add(projectFolder);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectFolder.ProjectFolderModel.allProjectFolder()" + e);
        }
        return allProjectFolder;
    }

    public List<ProjectFolderBean> getProjectFolder(String folder_id) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<ProjectFolderBean> getAllAngleUnits = new ArrayList<>();
        String query = "SELECT folder_id, folder_name,folder_path,remark FROM project_folder WHERE active = 'Y' ";
        if (folder_id != null && !folder_id.isEmpty()) {
            query += "AND folder_id =? ";
        }
        query += "  ORDER BY folder_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            if (folder_id != null && !folder_id.isEmpty()) {
                pstmt.setString(1, folder_id);
            }
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ProjectFolderBean projectFolder = new ProjectFolderBean();
                projectFolder.setFolderId(resultSet.getInt("folder_id"));
                projectFolder.setFolderName(resultSet.getString("folder_name"));
                projectFolder.setFolderPath(resultSet.getString("folder_path"));
                projectFolder.setRemark(resultSet.getString("remark"));
                getAllAngleUnits.add(projectFolder);
            }
        } catch (SQLException e) {
            System.out.println("com.apogee.gnss.projectFolder.ProjectFolderModel.getProjectFolder()" + e);
        }
        return getAllAngleUnits;
    }

    public void getFolderPath(String folder_id) {
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
        openFolder(folderPath);
    }

    public void openFolder(String folderPath) {
        File folder = new File(folderPath);
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (folder.exists()) {
                    desktop.open(folder);
                } else {
                    System.out.println("Folder does not exist: " + folderPath);
                }
            } else {
                System.out.println("Desktop is not supported.");
            }
        } catch (IOException e) {
            System.out.println("com.apogee.gnss.projectFolder.ProjectFolderModel.opeFolder()" + e);
        }
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
