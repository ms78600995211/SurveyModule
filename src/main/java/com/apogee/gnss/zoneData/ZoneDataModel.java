/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.zoneData;

import com.apogee.gnss.hemisphere.HemisphereBean;
import com.apogee.gnss.zoneData.ZoneDataBean;
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
public class ZoneDataModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public String saveZoneData(String zone, String hemisphere_id, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        String zonedata_id = "";
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;

        try {
            if (!zone.trim().isEmpty()) {
                String query = "insert into zonedata(zone,hemisphere_id,active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, zone);
                pstmt.setString(2, hemisphere_id);
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
                message = "Some Error Try Again.";
                color = COLOR_ERROR;

            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ZoneDataModel.saveZoneData()" + e);
        }
        return zonedata_id;
    }

    public List<ZoneDataBean> getAllZoneData(String zonedata_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ZoneDataBean> zoneDataList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(" select *  FROM zonedata WHERE active = 'Y' ");
        String qry = "and zonedata_id='" + zonedata_id + "'";
        if (zonedata_id != null && !zonedata_id.isEmpty()) {
            builder.append(qry);

        }
        builder.append(" order by zonedata_id desc ");
        try {
            pstmt = connection.prepareStatement(builder.toString());
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ZoneDataBean bean = new ZoneDataBean();
                bean.setZone(stmt.getString("zone"));
                bean.setRemark(stmt.getString("remark"));
                int hemisphere_id = stmt.getInt("hemisphere_id");
                String query1 = " SELECT zoneHemisphere  FROM hemisphere  WHERE   hemisphere_id =" + hemisphere_id + " ";
                PreparedStatement pstmt1 = connection.prepareStatement(query1);

                ResultSet stmt1 = pstmt1.executeQuery();
                String zoneHemisphere = "";
                while (stmt1.next()) {
                    zoneHemisphere = stmt1.getString("zoneHemisphere");
                }

                bean.setHemisphere(zoneHemisphere);
                zoneDataList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ZoneDataModel.getAllZoneData()" + e);
        }
        return zoneDataList;
    }

    public List<HemisphereBean> getHemishereData() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<HemisphereBean> getHemishereData = new ArrayList<>();

        String query = " select hemisphere_id, zoneHemisphere "
                + " FROM hemisphere "
                + " WHERE active = 'Y' order by hemisphere_id desc ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                HemisphereBean hemishereData = new HemisphereBean();
                hemishereData.setHemisphere_id(stmt.getInt("hemisphere_id"));
                hemishereData.setZoneHemisphere(stmt.getString("zoneHemisphere"));

                getHemishereData.add(hemishereData);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ZoneDataModel.getHemishereData()" + e);
        }
        return getHemishereData;
    }

    public List<ZoneDataBean> allZoneData() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ZoneDataBean> allZoneData = new ArrayList<>();
        String query = " select * "
                + " FROM zonedata "
                + " WHERE active = 'Y' order by zonedata_id desc ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ZoneDataBean bean = new ZoneDataBean();
                bean.setZonedata_id(stmt.getInt("zonedata_id"));
                bean.setZone(stmt.getString("zone"));
                allZoneData.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.ZoneDataModel.allZoneData()" + e);
        }
        return allZoneData;
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
