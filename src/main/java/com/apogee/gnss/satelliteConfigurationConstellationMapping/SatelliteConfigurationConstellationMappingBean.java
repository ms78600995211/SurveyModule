/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.satelliteConfigurationConstellationMapping;

import java.util.List;

/**
 *
 * @author admin
 */
public class SatelliteConfigurationConstellationMappingBean {

private String satellite_configuration_constellation_mapping_id;
private String satellite_configuration_id;
private String satellite_configuration_name;
private String constellation_id;
private String constellation_name;
private List<String> all_constellation;
private String config_time;
private String remark;


    public List<String> getAll_constellation() {
        return all_constellation;
    }

    public void setAll_constellation(List<String> all_constellation) {
        this.all_constellation = all_constellation;
    }

    public String getConfig_time() {
        return config_time;
    }

    public void setConfig_time(String config_time) {
        this.config_time = config_time;
    }

    public String getSatellite_configuration_constellation_mapping_id() {
        return satellite_configuration_constellation_mapping_id;
    }

    public void setSatellite_configuration_constellation_mapping_id(String satellite_configuration_constellation_mapping_id) {
        this.satellite_configuration_constellation_mapping_id = satellite_configuration_constellation_mapping_id;
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

    public String getConstellation_id() {
        return constellation_id;
    }

    public void setConstellation_id(String constellation_id) {
        this.constellation_id = constellation_id;
    }

    public String getConstellation_name() {
        return constellation_name;
    }

    public void setConstellation_name(String constellation_name) {
        this.constellation_name = constellation_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
 
}
