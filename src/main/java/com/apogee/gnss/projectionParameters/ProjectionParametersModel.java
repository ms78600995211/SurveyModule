/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectionParameters;

import com.apogee.gnss.projectionType.ProjectionTypeBean;
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
public class ProjectionParametersModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveProjectionParameters(String zone_name, String origin_lat, String origin_lng, String scale, String false_easting, String false_northing, String paralell_1, String paralell_2, String misc1, String misc2, String misc3, String misc4, String projectiontype_id, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;

        try {
            if (!zone_name.trim().isEmpty() && !origin_lat.trim().isEmpty() && !origin_lng.trim().isEmpty() && !scale.trim().isEmpty() && !false_easting.trim().isEmpty() && !false_northing.trim().isEmpty() && !paralell_1.trim().isEmpty() && !paralell_2.trim().isEmpty()) {
                String query = "insert into projectionparameters(zone_name,origin_lat,origin_lng,scale,false_easting,false_northing,paralell_1,paralell_2,misc1,misc2,misc3,misc4,projectiontype_id, active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, zone_name);
                pstmt.setString(2, origin_lat);
                pstmt.setString(3, origin_lng);
                pstmt.setString(4, scale);
                pstmt.setString(5, false_easting);
                pstmt.setString(6, false_northing);
                pstmt.setString(7, paralell_1);
                pstmt.setString(8, paralell_2);
                pstmt.setString(9, misc1);
                pstmt.setString(10, misc2);
                pstmt.setString(11, misc3);
                pstmt.setString(12, misc4);
                pstmt.setString(13, projectiontype_id);
                pstmt.setString(14, "Y");
                pstmt.setString(15, name);
                pstmt.setString(16, time_stamp);
                pstmt.setString(17, remark);
                pstmt.setInt(18, 0);
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
            System.out.println("com.apogee.gnss.model.ProjectionParametersModel.saveProjectionParameters()" + e);
        }
    }

    public List<ProjectionParametersBean> getAllProjectionParams(String projectionParam_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectionParametersBean> getAllProjectionParams = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(" select * FROM projectionparameters WHERE active = 'Y' ");
        String qry = " and projectionParam_id='" + projectionParam_id + "'";
        if (projectionParam_id != null && !projectionParam_id.isEmpty()) {
            builder.append(qry);
        }
        builder.append(" order by projectionParam_id desc ");

        try {
            pstmt = connection.prepareStatement(builder.toString());
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ProjectionParametersBean bean = new ProjectionParametersBean();
                bean.setZone_name(stmt.getString("zone_name"));
                bean.setOrigin_lat(stmt.getString("origin_lat"));
                bean.setOrigin_lng(stmt.getString("origin_lng"));
                bean.setScale(stmt.getString("scale"));
                bean.setFalse_easting(stmt.getString("false_easting"));
                bean.setFalse_northing(stmt.getString("false_northing"));
                bean.setParalell_1(stmt.getString("paralell_1"));
                bean.setParalell_2(stmt.getString("paralell_2"));
                bean.setMisc1(stmt.getString("misc1"));
                bean.setMisc2(stmt.getString("misc2"));
                bean.setMisc3(stmt.getString("misc3"));
                bean.setMisc4(stmt.getString("misc4"));
                int projectionTypeId = stmt.getInt("projectiontype_id");

                String query1 = " SELECT projectionType  FROM projectiontype  WHERE   projectiontype_id =" + projectionTypeId + " ";
                PreparedStatement pstmt1 = connection.prepareStatement(query1);

                ResultSet stmt1 = pstmt1.executeQuery();
                String projectionType = "";
                while (stmt1.next()) {
                    projectionType = stmt1.getString("projectionType");
                }

                bean.setProjectionType(projectionType);
                bean.setRemark(stmt.getString("remark"));
                getAllProjectionParams.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectionParametersModel.getAllProjectionParams()" + e);
        }
        return getAllProjectionParams;
    }

    public List<ProjectionParametersBean> projectionParamsList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectionParametersBean> projectionParamsList = new ArrayList<>();
        String query = " select * "
                + " FROM projectionparameters "
                + " WHERE active = 'Y' order by projectionParam_id desc ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ProjectionParametersBean bean = new ProjectionParametersBean();
                bean.setProjectionParam_id(stmt.getInt("projectionParam_id"));
                bean.setZone_name(stmt.getString("zone_name"));
                projectionParamsList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectionParametersModel.projectionParamsList()" + e);
        }
        return projectionParamsList;
    }

    public List<ProjectionTypeBean> getAllProjectionType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectionTypeBean> getAllProjectionType = new ArrayList<>();

        String query = " select projectiontype_id, projectionType "
                + " FROM projectiontype "
                + " WHERE active = 'Y' order by projectiontype_id desc ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectionTypeBean projectionType = new ProjectionTypeBean();
                projectionType.setProjectiontype_id(stmt.getInt("projectiontype_id"));
                projectionType.setProjectionType(stmt.getString("projectionType"));

                getAllProjectionType.add(projectionType);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ProjectionParametersModel.getAllProjectionType()" + e);
        }
        return getAllProjectionType;
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
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

}
