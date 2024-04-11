/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.about;

import com.apogee.gnss.projectFile.ProjectFileBean;
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
public class AboutModel {
/*
    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveProjectVersion(String versionName, String status, String description, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            String query = "insert into about_version(version_name,status,description, active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, versionName);
            pstmt.setString(2, status);
            pstmt.setString(3, description);
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
            System.out.println("com.apogee.gnss.about.AboutModel.saveProjectVersion()" + e);
        }
    }

    public List<AboutBean> allProjectVesrion() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<AboutBean> allProjectVersion = new ArrayList<>();
        String query = "select * from about_version";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                AboutBean bean = new AboutBean();
                bean.setVersionName(stmt.getString("version_name"));
                bean.setStatus(stmt.getString("status"));
                bean.setTimeStamp(stmt.getString("created_at"));
                bean.setDescription(stmt.getString("description"));
                allProjectVersion.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.about.AboutModel.allProjectVesrion()" + e);
        }
        return allProjectVersion;
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
*/

}
