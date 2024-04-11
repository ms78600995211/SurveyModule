/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.ppkParams;

/**
 *
 * @author admin
 */
public class PpkParamsBean {

    private int ppk_params_id;
    private String file_name;
    private String total_time;
    private String sampling_rate;
    private String remarks;
    private String defaultConfig;

    public int getPpk_params_id() {
        return ppk_params_id;
    }

    public void setPpk_params_id(int ppk_params_id) {
        this.ppk_params_id = ppk_params_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public String getSampling_rate() {
        return sampling_rate;
    }

    public void setSampling_rate(String sampling_rate) {
        this.sampling_rate = sampling_rate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
