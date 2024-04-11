/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.constellation;

/**
 *
 * @author lENOVO
 */
public class ConstellationBean {

    private int constellation_id;
    private String constellation_name;
    private String remark;

    /**
     * @return the constellation_id
     */
    public int getConstellation_id() {
        return constellation_id;
    }

    /**
     * @param constellation_id the constellation_id to set
     */
    public void setConstellation_id(int constellation_id) {
        this.constellation_id = constellation_id;
    }

    /**
     * @return the constellation_name
     */
    public String getConstellation_name() {
        return constellation_name;
    }

    /**
     * @param constellation_name the constellation_name to set
     */
    public void setConstellation_name(String constellation_name) {
        this.constellation_name = constellation_name;
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
