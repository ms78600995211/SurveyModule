/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.dynamicProjectConfiguration;

import com.apogee.gnss.deviceConfiguration.DeviceConfigurationBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class DynamicConfigMappingModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public String saveDynamic_project_config(String dynamic_config_name, String device_configHierarchy_id, String manual_base_params_id, String ppk_params_id, String static_params_id, String defaultConfig, String name, String remark) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        String dynamic_project_config_id = "";
        String query = "select count(*) as count from dynamic_configuration_mapping where dynamic_config_name =?  and active = 'Y' ";
        try {

            String query1 = " select count(*) as count from dynamic_configuration_mapping where dynamic_config_name =?  and active = 'Y' ;";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, dynamic_config_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count > 0) {
                message = dynamic_config_name + "Name Already Exist.";
                color = COLOR_ERROR;
            } else {
                if (!dynamic_config_name.trim().isEmpty()) {

                    System.out.println("=======================" + dynamic_config_name);
                    query = "insert into dynamic_configuration_mapping(dynamic_config_name,device_configHierarchy_id,manual_base_params_id,ppk_params_id,static_params_id,defaultConfig,created_by,active,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?)";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, dynamic_config_name);
                    pstmt.setString(2, device_configHierarchy_id);
                    pstmt.setString(3, manual_base_params_id);
                    pstmt.setString(4, ppk_params_id);
                    pstmt.setString(5, static_params_id);
                    pstmt.setString(6, defaultConfig);
                    pstmt.setString(7, name);
                    pstmt.setString(8, "Y");
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
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.saveDynamic_project_config()" + e);
        }
        return dynamic_project_config_id;
    }

    public List<DynamicConfigMappingBean> getByte_value() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getByte_value = new ArrayList<>();
        String query = "select distinct byte_value from mask_angle  where active='Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setByte_value(stmt.getString("byte_value"));
                getByte_value.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getByte_value()" + e);
        }
        return getByte_value;
    }

    public List<DynamicConfigMappingBean> getMask_angle(String byte_value) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getMask_angle = new ArrayList<>();
        String query = "select distinct display_value from mask_angle  where active='Y'  ";
        if (!byte_value.isEmpty() && byte_value != null) {
            query += " and byte_value= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (!byte_value.isEmpty() && byte_value != null) {
                pstmt.setString(1, byte_value);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setDisplay_value(stmt.getString("display_value"));
                getMask_angle.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getMask_angle()" + e);
        }
        return getMask_angle;
    }

    public List<DynamicConfigMappingBean> getFileName() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getStaticPrams = new ArrayList<>();
        String query = "select distinct file_name from static_params  where active='Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setFile_name(stmt.getString("file_name"));
                getStaticPrams.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getFileName()" + e);
        }
        return getStaticPrams;
    }

    public List<DynamicConfigMappingBean> getStaticParams(String file_name, String total_time) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getStaticParams = new ArrayList<>();
        String query = "select  *  from static_params  where active='Y'  ";
        if (!file_name.isEmpty() && file_name != null) {
            query += " and file_name= ?";
        }
        if (!total_time.isEmpty() && total_time != null) {
            query += " and total_time= ?";
        }
        query += "  order by static_params_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            int parameterIndex = 1;
            if (!file_name.isEmpty() && file_name != null) {
                pstmt.setString(parameterIndex++, file_name);
            }
            if (!total_time.isEmpty() && total_time != null) {
                pstmt.setString(parameterIndex++, total_time);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setFile_name(stmt.getString("file_name"));
                bean.setTotal_time(stmt.getString("total_time"));
                bean.setSampling_rate(stmt.getString("sampling_rate"));
                getStaticParams.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getStaticParams()" + e);
        }
        return getStaticParams;
    }

    public List<DynamicConfigMappingBean> getDevice_work_mode_name() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getDevice_work_mode_name = new ArrayList<>();
        String query = "select distinct device_work_mode_name from dynamic_mode_data  where active='Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setDevice_work_mode_name(stmt.getString("device_work_mode_name"));
                getDevice_work_mode_name.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getDevice_work_mode_name()" + e);
        }
        return getDevice_work_mode_name;
    }

    public List<DynamicConfigMappingBean> getDynamic_mode_data(String device_work_mode_name) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getDynamic_mode_data = new ArrayList<>();
        String query = "select distinct mode_value from dynamic_mode_data  where active='Y'  ";
        if (!device_work_mode_name.isEmpty() && device_work_mode_name != null) {
            query += " and device_work_mode_name= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (!device_work_mode_name.isEmpty() && device_work_mode_name != null) {
                pstmt.setString(1, device_work_mode_name);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setMode_value(stmt.getString("mode_value"));
                getDynamic_mode_data.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getDynamic_mode_data()" + e);
        }
        return getDynamic_mode_data;
    }

    public List<DynamicConfigMappingBean> getFile_name() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getFile_name = new ArrayList<>();
        String query1 = "select distinct file_name from ppk_params  where active='Y'  ";
        try {
            pstmt = connection.prepareStatement(query1);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setFile_name(stmt.getString("file_name"));
                getFile_name.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getFile_name()" + e);
        }
        return getFile_name;
    }

    public List<DynamicConfigMappingBean> getPpk_params(String file_name) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getPpk_params = new ArrayList<>();
        String query = "select distinct total_time from ppk_params  where active='Y'  ";
        if (!file_name.isEmpty() && file_name != null) {
            query += " and file_name= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (!file_name.isEmpty() && file_name != null) {
                pstmt.setString(1, file_name);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setTotal_time(stmt.getString("total_time"));
                getPpk_params.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getPpk_params()" + e);
        }
        return getPpk_params;
    }

    public List<DynamicConfigMappingBean> getAllManualBaseParamsName() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getAllManualBaseParamsName = new ArrayList<>();
        String query1 = "select manual_base_params_id,manual_base_params_name from manual_base_params  where active='Y'  ";
        try {
            pstmt = connection.prepareStatement(query1);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setManual_base_params_id(stmt.getString("manual_base_params_id"));
                bean.setManual_base_params_name(stmt.getString("manual_base_params_name"));
                getAllManualBaseParamsName.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getAllManualBaseParamsName()" + e);
        }
        return getAllManualBaseParamsName;
    }

    /*
    public List<DynamicConfigMappingBean> getLatitude() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getLatitude = new ArrayList<>();
        String query1 = "select distinct latitude from manual_base_table  where active='Y'  ";
        try {
            pstmt = connection.prepareStatement(query1);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setLatitude(stmt.getString("latitude"));
                getLatitude.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getLatitude()" + e);
        }
        return getLatitude;
    }

    public List<DynamicConfigMappingBean> getManual_base_table(String latitude, String longitude) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DynamicConfigMappingBean> getManual_base_table = new ArrayList<>();
        String query = "select distinct longitude,altitude from manual_base_table  where active='Y'  ";
        if (!latitude.isEmpty() && latitude != null) {
            query += " and latitude= ?";
        }
        if (!longitude.isEmpty() && longitude != null) {
            query += " and longitude= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            int parameterIndex = 1;
            if (!latitude.isEmpty() && latitude != null) {
                pstmt.setString(parameterIndex++, latitude);
            }
            if (!longitude.isEmpty() && longitude != null) {
                pstmt.setString(parameterIndex++, longitude);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setLongitude(stmt.getString("longitude"));
                bean.setAltitude(stmt.getString("altitude"));
                getManual_base_table.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getManual_base_table()" + e);
        }
        return getManual_base_table;
    }

    public String getManual_base_table_id(String latitude, String longitude, String altitude) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select manual_base_table_id from manual_base_table where latitude= ? and longitude= ? and altitude= ? and active='Y' ";
        String getManual_base_table_id = "";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, latitude);
            pstmt.setString(2, longitude);
            pstmt.setString(3, altitude);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                getManual_base_table_id = stmt.getString("manual_base_table_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getManual_base_table_id()" + e);
        }
        return getManual_base_table_id;
    }
     */
    public String getStatic_params_id(String file_name, String total_time, String sampling_rate) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select static_params_id from static_params where file_name= ? and total_time=? and sampling_rate=? and active='Y' ";
        String getStatic_params_id = "";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, file_name);
            pstmt.setString(2, total_time);
            pstmt.setString(3, sampling_rate);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                getStatic_params_id = stmt.getString("static_params_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getStatic_params_id()" + e);
        }
        return getStatic_params_id;
    }

    public String getDynamic_mode_data_id(String device_work_mode_name, String mode_value) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select dynamic_mode_data_id from dynamic_mode_data where device_work_mode_name= ? and mode_value=  ? and active='Y' ";
        String getDynamic_mode_data_id = "";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, device_work_mode_name);
            pstmt.setString(2, mode_value);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                getDynamic_mode_data_id = stmt.getString("dynamic_mode_data_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getDynamic_mode_data_id()" + e);
        }
        return getDynamic_mode_data_id;
    }

    public String getPpk_params_id(String file_name, String total_time) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select ppk_params_id from ppk_params where file_name=? and total_time=? and active='Y' ";
        String getPpk_params_id = "";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, file_name);
            pstmt.setString(2, total_time);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                getPpk_params_id = stmt.getString("ppk_params_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getPpk_params_id()" + e);
        }
        return getPpk_params_id;
    }

    public String getMask_angle_id(String byte_value, String display_value) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select mask_angle_id from mask_angle where byte_value=? and display_value=? and active='Y' ";
        String getMask_angle_id = "";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, byte_value);
            pstmt.setString(2, display_value);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                getMask_angle_id = stmt.getString("mask_angle_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getMask_angle_id()" + e);
        }
        return getMask_angle_id;
    }

    public List getDynamicProjectConfigList(String file_name) {
        List<DynamicConfigMappingBean> getDynamicProjectConfigList = new ArrayList<>();
        String query = "select dcm.dynamic_config_name,dch.device_configHierarchy_name,mbp.manual_base_params_name,ppk.file_name,ppk.total_time,stp.file_name\n"
                + ",stp.total_time,stp.sampling_rate,dcm.defaultConfig,dcm.remark\n"
                + "from dynamic_configuration_mapping dcm\n"
                + "join device_confighierarchy  dch on dch.device_configHierarchy_id=dcm.device_configHierarchy_id\n"
                + "join manual_base_params mbp on mbp.manual_base_params_id=dcm.manual_base_params_id\n"
                + "join ppk_params ppk on ppk.ppk_params_id=dcm.ppk_params_id\n"
                + "join static_params stp on stp.static_params_id=dcm.static_params_id\n"
                + "where dcm.active='Y' and dch.active='Y' and mbp.active='Y' and ppk.active='Y' and stp.active='Y' ";
        if (file_name != null && !file_name.isEmpty()) {
            query += " and ppk.file_name= ?";
        }
        query += " ORDER BY dcm.dynamic_configuration_mapping_id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            if (file_name != null && !file_name.isEmpty()) {
                pstmt.setString(1, file_name);
            }
            ResultSet stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setDynamic_project_config_name(stmt.getString("dynamic_config_name"));
                bean.setDevice_configHierarchy_name(stmt.getString("device_configHierarchy_name"));
                bean.setManual_base_params_name(stmt.getString("manual_base_params_name"));
                bean.setFile_name(stmt.getString("file_name"));
                bean.setTotal_time(stmt.getString("total_time"));
                bean.setStatic_params_file_name(stmt.getString("file_name"));
                bean.setTotalTime(stmt.getString("total_time"));
                bean.setSampling_rate(stmt.getString("sampling_rate"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setReamrks(stmt.getString("remark"));
                getDynamicProjectConfigList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.getDynamicProjectConfigList()" + e);
        }
        return getDynamicProjectConfigList;
    }

    public List getDevice_confighierarchyList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DeviceConfigurationBean> getDevice_confighierarchyList = new ArrayList<>();
        String query = "select device_configHierarchy_id,device_configHierarchy_name from device_confighierarchy where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DeviceConfigurationBean bean = new DeviceConfigurationBean();
                bean.setDevice_configHierarchy_id(rs.getString("device_configHierarchy_id"));
                bean.setDevice_configHierarchy_name(rs.getString("device_configHierarchy_name"));
                getDevice_confighierarchyList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationModel.getDevice_confighierarchyList()" + e);
        }
        return getDevice_confighierarchyList;
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
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationModel.closeConnection()" + e);
        }
    }

}
