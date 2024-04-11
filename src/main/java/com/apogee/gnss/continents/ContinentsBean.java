/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.continents;

/**
 *
 * @author lENOVO
 */
public class ContinentsBean {

    private int continent_id;
    private String continent_name;
    private String remark;

    /**
     * @return the continent_id
     */
    public int getContinent_id() {
        return continent_id;
    }

    /**
     * @param continent_id the continent_id to set
     */
    public void setContinent_id(int continent_id) {
        this.continent_id = continent_id;
    }

    /**
     * @return the continent_name
     */
    public String getContinent_name() {
        return continent_name;
    }

    /**
     * @param continent_name the continent_name to set
     */
    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
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

}
