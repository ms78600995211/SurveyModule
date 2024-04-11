/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.deviceConfighierarchy;

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
public class DeviceConfighierarchyModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public String saveDevice_confighierarchy(String device_configHierarchy_name, String parent_id, String is_super_child, int generation, String defaultConfig, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        String device_configHierarchy_id = "";
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        try {
            if (!device_configHierarchy_name.trim().isEmpty()) {
                String query = "insert into device_confighierarchy(device_configHierarchy_name,parent_id,is_super_child,generation,defaultConfig,active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, device_configHierarchy_name);
                pstmt.setString(2, parent_id);
                pstmt.setString(3, is_super_child);
                pstmt.setInt(4, generation);
                pstmt.setString(5, defaultConfig);
                pstmt.setString(6, "Y");
                pstmt.setString(7, name);
                pstmt.setString(8, time_stamp);
                pstmt.setString(9, remark);
                pstmt.setInt(10, 0);
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
            System.out.println("com.apogee.gnss.deviceConfighierarchy.DeviceConfighierarchyModel.saveDevice_confighierarchy()" + e);
        }
        return device_configHierarchy_id;
    }

    public int get_generation(String parent_id) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int generation = 0;
        try {
            String query = " SELECT generation FROM device_confighierarchy WHERE active='Y' and device_configHierarchy_id = ?";
            pstmt = (PreparedStatement) connection.prepareStatement(query);
            pstmt.setString(1, parent_id);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                generation = rset.getInt("generation");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfighierarchy.DeviceConfighierarchyModel.get_generation()" + e);
        }
        return generation;
    }

    public List<DeviceConfighierarchyBean> getDeviceConfighierarchyList() {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<DeviceConfighierarchyBean> getDeviceConfighierarchyList = new ArrayList<>();
        String query = " select device_configHierarchy_id, device_configHierarchy_name "
                + " FROM device_confighierarchy "
                + " WHERE active = 'Y' and is_super_child='N'  order by device_configHierarchy_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                DeviceConfighierarchyBean bean = new DeviceConfighierarchyBean();
                bean.setDevice_configHierarchy_id(rset.getString("device_configHierarchy_id"));
                bean.setDevice_configHierarchy_name(rset.getString("device_configHierarchy_name"));
                getDeviceConfighierarchyList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfighierarchy.DeviceConfighierarchyModel.getDeviceConfighierarchyList()" + e);
        }
        return getDeviceConfighierarchyList;
    }

    public List<DeviceConfighierarchyBean> getAllDeviceConfighierarchy(String generation) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ResultSet rs1 = null;
        PreparedStatement pstmt1 = null;
        List<DeviceConfighierarchyBean> getAllDeviceConfighierarchy = new ArrayList<>();
        String query = " select  *  FROM device_confighierarchy  WHERE active = 'Y'  ";
        if (generation != null && !generation.isEmpty()) {
            query += " and generation= ?";
        }
        query += " order by device_configHierarchy_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            if (generation != null && !generation.isEmpty()) {
                pstmt.setString(1, generation);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DeviceConfighierarchyBean bean = new DeviceConfighierarchyBean();
                bean.setDevice_configHierarchy_id(rs.getString("device_configHierarchy_id"));
                bean.setDevice_configHierarchy_name(rs.getString("device_configHierarchy_name"));
                String parent_id = rs.getString("parent_id");
                query = " select device_configHierarchy_name FROM device_confighierarchy "
                        + " WHERE active = 'Y' and is_super_child='N' and device_configHierarchy_id= ? "
                        + "  order by device_configHierarchy_id desc ";
                pstmt1 = connection.prepareStatement(query);
                pstmt1.setString(1, parent_id);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    bean.setParent_name(rs1.getString("device_configHierarchy_name"));
                }
                bean.setIs_super_child(rs.getString("is_super_child"));
                bean.setGeneration(rs.getString("generation"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setRemark(rs.getString("remark"));
                getAllDeviceConfighierarchy.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfighierarchy.DeviceConfighierarchyModel.getAllDeviceConfighierarchyList()" + e);
        }
        return getAllDeviceConfighierarchy;
    }

    public List allGeneration() {
        ResultSet rset = null;
        List gens = new ArrayList();
        try {
            String query = " SELECT distinct generation FROM device_confighierarchy WHERE active ='Y' ";
            rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                gens.add(rset.getInt("generation"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfighierarchy.DeviceConfighierarchyModel.get_generation()" + e);
        }
        return gens;
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
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

}
