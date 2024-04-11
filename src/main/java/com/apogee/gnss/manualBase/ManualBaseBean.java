/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.manualBase;

/**
 *
 * @author lENOVO
 */
public class ManualBaseBean {

    private int manual_base_params_id;
    private String manual_base_params_name;
    private String latitude;
    private String longitude;
    private String altitude;
    private String remark;
    private String defaultConfig;

  

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the altitude
     */
    public String getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the manual_base_params_id
     */
    public int getManual_base_params_id() {
        return manual_base_params_id;
    }

    /**
     * @param manual_base_params_id the manual_base_params_id to set
     */
    public void setManual_base_params_id(int manual_base_params_id) {
        this.manual_base_params_id = manual_base_params_id;
    }

    /**
     * @return the manual_base_params_name
     */
    public String getManual_base_params_name() {
        return manual_base_params_name;
    }

    /**
     * @param manual_base_params_name the manual_base_params_name to set
     */
    public void setManual_base_params_name(String manual_base_params_name) {
        this.manual_base_params_name = manual_base_params_name;
    }

    /**
     * @return the defaultConfig
     */
    public String getDefaultConfig() {
        return defaultConfig;
    }

    /**
     * @param defaultConfig the defaultConfig to set
     */
    public void setDefaultConfig(String defaultConfig) {
        this.defaultConfig = defaultConfig;
    }
}
