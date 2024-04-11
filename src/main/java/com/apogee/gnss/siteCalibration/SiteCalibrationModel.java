/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.siteCalibration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SiteCalibrationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveSiteCalData(String Scale, String Angle, String Tx, String Ty, String Fixed_Easting, String Fixed_Northing, String sigmaZ, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            if (!Scale.trim().isEmpty() && !Angle.trim().isEmpty() && !Ty.trim().isEmpty() && !Fixed_Easting.trim().isEmpty() && !Fixed_Northing.trim().isEmpty() && !sigmaZ.trim().isEmpty()) {
                String query = "insert into site_calibration(Scale,Angle,Tx,Ty,Fixed_Easting,Fixed_Northing,sigmaZ,active,created_by,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, Scale);
                pstmt.setString(2, Angle);
                pstmt.setString(3, Tx);
                pstmt.setString(4, Ty);
                pstmt.setString(5, Fixed_Easting);
                pstmt.setString(6, Fixed_Northing);
                pstmt.setString(7, sigmaZ);
                pstmt.setString(8, "Y");
                pstmt.setString(9, name);
                pstmt.setString(10, remark);
                pstmt.setInt(11, 0);
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
            System.out.println("com.apogee.gnss.model.SiteCalibrationModel.saveSiteCalData()" + e);
        }
    }

    public List<SiteCalibrationBean> getSiteCalData(String scale) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SiteCalibrationBean> getSiteCalData = new ArrayList<>();
        String query = " select * FROM site_calibration WHERE active = 'Y' ";
        if (scale != null && !scale.isEmpty()) {
            query += "and scale='" + scale + "' ";
        }
        query += "order by siteCal_id desc";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SiteCalibrationBean siteCal = new SiteCalibrationBean();
                siteCal.setScale(stmt.getString("Scale"));
                siteCal.setAngle(stmt.getString("Angle"));
                siteCal.setTx(stmt.getString("Tx"));
                siteCal.setTy(stmt.getString("Ty"));
                siteCal.setFixed_Easting(stmt.getString("Fixed_Easting"));
                siteCal.setFixed_Northing(stmt.getString("Fixed_Northing"));
                siteCal.setSigmaZ(stmt.getString("sigmaZ"));
                siteCal.setRemark(stmt.getString("remark"));
                getSiteCalData.add(siteCal);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.SiteCalibrationModel.getSiteCalData()" + e);
        }
        return getSiteCalData;
    }

    public List allScale() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List allSiteCalData = new ArrayList<>();
        String query = " select distinct Scale FROM site_calibration WHERE active = 'Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                allSiteCalData.add(stmt.getString("Scale"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.SiteCalibrationModel.allSiteCalData()" + e);
        }
        return allSiteCalData;
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
            System.out.println("com.apogee.gnss.model.SiteCalibrationModel.closeConnection()" + e);
        }
    }

}
