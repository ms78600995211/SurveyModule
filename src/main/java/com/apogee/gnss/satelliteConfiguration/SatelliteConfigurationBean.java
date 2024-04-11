/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.satelliteConfiguration;

/**
 *
 * @author admin
 */
public class SatelliteConfigurationBean {
    
private String satellite_configuration_id;
private String satellite_configuration_name;
private String defaultConfig;
private String remark;

    public String getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(String defaultConfig) {
        this.defaultConfig = defaultConfig;
    }
    public String getSatellite_configuration_id() {
        return satellite_configuration_id;
    }

    public void setSatellite_configuration_id(String satellite_configuration_id) {
        this.satellite_configuration_id = satellite_configuration_id;
    }

    public String getSatellite_configuration_name() {
        return satellite_configuration_name;
    }

    public void setSatellite_configuration_name(String satellite_configuration_name) {
        this.satellite_configuration_name = satellite_configuration_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
