/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.deviceConfiguration;

/**
 *
 * @author admin
 */
public class DeviceConfigurationBean {
    
  private String device_configuration_id;
  private String device_configuration_name;
  private String device_configHierarchy_id;
  private String device_configHierarchy_name;
  private String communication_type_mapping_id;
  private String communication_type_mapping_name;
  private String dynamic_configuration_mapping_id;
  private String dynamic_config_name;
  private String mask_angle_byteValue;
  private String mask_angle_displayValue;
  private String device_work_mode_name;
  private String device_work_mode_value;
  private String defaultConfig;
  private String config_time;
  private String remark;

    public String getDynamic_configuration_mapping_id() {
        return dynamic_configuration_mapping_id;
    }

    public void setDynamic_configuration_mapping_id(String dynamic_configuration_mapping_id) {
        this.dynamic_configuration_mapping_id = dynamic_configuration_mapping_id;
    }

    public String getDynamic_config_name() {
        return dynamic_config_name;
    }

    public void setDynamic_config_name(String dynamic_config_name) {
        this.dynamic_config_name = dynamic_config_name;
    }

    public String getMask_angle_byteValue() {
        return mask_angle_byteValue;
    }

    public void setMask_angle_byteValue(String mask_angle_byteValue) {
        this.mask_angle_byteValue = mask_angle_byteValue;
    }

    public String getMask_angle_displayValue() {
        return mask_angle_displayValue;
    }

    public void setMask_angle_displayValue(String mask_angle_displayValue) {
        this.mask_angle_displayValue = mask_angle_displayValue;
    }

    public String getDevice_work_mode_name() {
        return device_work_mode_name;
    }

    public void setDevice_work_mode_name(String device_work_mode_name) {
        this.device_work_mode_name = device_work_mode_name;
    }

    public String getDevice_work_mode_value() {
        return device_work_mode_value;
    }

    public void setDevice_work_mode_value(String device_work_mode_value) {
        this.device_work_mode_value = device_work_mode_value;
    }

    public String getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(String defaultConfig) {
        this.defaultConfig = defaultConfig;
    }
    public String getDevice_configuration_id() {
        return device_configuration_id;
    }

    public void setDevice_configuration_id(String device_configuration_id) {
        this.device_configuration_id = device_configuration_id;
    }

    public String getDevice_configuration_name() {
        return device_configuration_name;
    }

    public void setDevice_configuration_name(String device_configuration_name) {
        this.device_configuration_name = device_configuration_name;
    }

    public String getDevice_configHierarchy_id() {
        return device_configHierarchy_id;
    }

    public void setDevice_configHierarchy_id(String device_configHierarchy_id) {
        this.device_configHierarchy_id = device_configHierarchy_id;
    }

   
    public String getDevice_configHierarchy_name() {
        return device_configHierarchy_name;
    }

    public void setDevice_configHierarchy_name(String device_configHierarchy_name) {
        this.device_configHierarchy_name = device_configHierarchy_name;
    }

    public String getCommunication_type_mapping_id() {
        return communication_type_mapping_id;
    }

    public void setCommunication_type_mapping_id(String communication_type_mapping_id) {
        this.communication_type_mapping_id = communication_type_mapping_id;
    }

    

    public String getCommunication_type_mapping_name() {
        return communication_type_mapping_name;
    }

    public void setCommunication_type_mapping_name(String communication_type_mapping_name) {
        this.communication_type_mapping_name = communication_type_mapping_name;
    }

    public String getConfig_time() {
        return config_time;
    }

    public void setConfig_time(String config_time) {
        this.config_time = config_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
