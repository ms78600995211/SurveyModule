/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.deviceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class DeviceConfigurationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public String saveDevice_configurationData(String dynamic_configuration_mapping_id, String communication_type_mapping_id, String mask_angle_byteValue, String mask_angle_displayValue, String device_work_mode_name, String device_work_mode_value, String defaultConfig, String name, String remark) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        String device_configuration_id = "";
        String query = "SELECT MAX(device_configuration_id) AS max_device_configuration_id FROM device_configuration;";
        try {
            rs = connection.prepareStatement(query).executeQuery();
            int max_device_configuration_id = 0;
            while (rs.next()) {
                max_device_configuration_id = rs.getInt("max_device_configuration_id") + 1;
            }
            String device_configuration_name = "device_configuration".concat("_" + max_device_configuration_id);
            pstmt = null;
            query = "insert into device_configuration(device_configuration_name,dynamic_configuration_mapping_id,mapping_type_of_communication_id,mask_angle_byteValue,mask_angle_displayValue,device_work_mode_name,device_work_mode_value,defaultConfig,created_by,active,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?,?,?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, device_configuration_name);
            pstmt.setString(2, dynamic_configuration_mapping_id);
            pstmt.setString(3, communication_type_mapping_id);
            pstmt.setString(4, mask_angle_byteValue);
            pstmt.setString(5, mask_angle_displayValue);
            pstmt.setString(6, device_work_mode_name);
            pstmt.setString(7, device_work_mode_value);
            pstmt.setString(8, defaultConfig);
            pstmt.setString(9, name);
            pstmt.setString(10, "Y");
            pstmt.setString(11, remark);
            pstmt.setInt(12, 0);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationModel.saveDevice_configurationData()" + e);
        }
        return device_configuration_id;
    }

    public List getDeviceConfigurationList(String dynamic_configuration_mappingId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DeviceConfigurationBean> getDeviceConfigurationList = new ArrayList<>();
        String query = "select dc.device_configuration_id,dc.device_configuration_name,dcm.dynamic_config_name,dc.mask_angle_byteValue,dc.mask_angle_displayValue,dc.device_work_mode_name,dc.device_work_mode_value,dc.defaultConfig\n"
                + ",ctm.communication_type_mapping_name,dc.config_time,dc.remark from device_configuration  dc\n"
                + "inner join communication_type_mapping ctm on ctm.communication_type_mapping_id=dc.mapping_type_of_communication_id\n"
                + "inner join dynamic_configuration_mapping dcm on dcm.dynamic_configuration_mapping_id=dc.dynamic_configuration_mapping_id where dc.active='Y'  ";
        if (dynamic_configuration_mappingId != null && !dynamic_configuration_mappingId.isEmpty()) {
            query += "AND dc.dynamic_configuration_mapping_id = ?";
        }
        query += " order by dc.device_configuration_id desc  ";
        try {
            pstmt = connection.prepareStatement(query);
            if (dynamic_configuration_mappingId != null && !dynamic_configuration_mappingId.isEmpty()) {
                pstmt.setString(1, dynamic_configuration_mappingId);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DeviceConfigurationBean bean = new DeviceConfigurationBean();
                bean.setDevice_configuration_name(rs.getString("device_configuration_name"));
                bean.setDynamic_config_name(rs.getString("dynamic_config_name"));
                bean.setCommunication_type_mapping_name(rs.getString("communication_type_mapping_name"));
                bean.setMask_angle_byteValue(rs.getString("mask_angle_byteValue"));
                bean.setMask_angle_displayValue(rs.getString("mask_angle_displayValue"));
                bean.setDevice_work_mode_name(rs.getString("device_work_mode_name"));
                bean.setDevice_work_mode_value(rs.getString("device_work_mode_value"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setConfig_time(rs.getString("config_time"));
                bean.setRemark(rs.getString("remark"));
                getDeviceConfigurationList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationModel.getDeviceConfigurationList()" + e);
        }
        return getDeviceConfigurationList;
    }

    public List getAllDynamicConfig() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DeviceConfigurationBean> getAllDynamicConfig = new ArrayList<>();
        String query = "select dcm.dynamic_configuration_mapping_id,dcm.dynamic_config_name \n"
                + "from dynamic_configuration_mapping  dcm\n"
                + "join device_configuration dc on dc.dynamic_configuration_mapping_id=dcm.dynamic_configuration_mapping_id\n"
                + " where dc.active='Y' and dcm.active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DeviceConfigurationBean bean = new DeviceConfigurationBean();
                bean.setDynamic_configuration_mapping_id(rs.getString("dynamic_configuration_mapping_id"));
                bean.setDynamic_config_name(rs.getString("dynamic_config_name"));
                getAllDynamicConfig.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationModel.getAllDynamicConfigName()" + e);
        }
        return getAllDynamicConfig;
    }

    public List getAllDynamicConfigName() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DeviceConfigurationBean> getDevice_confighierarchyList = new ArrayList<>();
        String query = "select dynamic_configuration_mapping_id,dynamic_config_name from dynamic_configuration_mapping where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DeviceConfigurationBean bean = new DeviceConfigurationBean();
                bean.setDynamic_configuration_mapping_id(rs.getString("dynamic_configuration_mapping_id"));
                bean.setDynamic_config_name(rs.getString("dynamic_config_name"));
                getDevice_confighierarchyList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationModel.getAllDynamicConfigName()" + e);
        }
        return getDevice_confighierarchyList;
    }

    public List getCommunication_type_mappingList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DeviceConfigurationBean> getCommunication_type_mappingList = new ArrayList<>();
        String query = "select communication_type_mapping_id,communication_type_mapping_name from communication_type_mapping where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DeviceConfigurationBean bean = new DeviceConfigurationBean();
                bean.setCommunication_type_mapping_id(rs.getString("communication_type_mapping_id"));
                bean.setCommunication_type_mapping_name(rs.getString("communication_type_mapping_name"));
                getCommunication_type_mappingList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationModel.getCommunication_type_mappingList()" + e);
        }
        return getCommunication_type_mappingList;
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
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationModel.closeConnection()" + e);
        }
    }

}
