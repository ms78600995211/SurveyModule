/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.elevationType;

/**
 *
 * @author admin
 */
public class ElevationTypeBean {
    
private int elevationtype_id;
private String elevationType;
private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getElevationtype_id() {
        return elevationtype_id;
    }

    public void setElevationtype_id(int elevationtype_id) {
        this.elevationtype_id = elevationtype_id;
    }

    public String getElevationType() {
        return elevationType;
    }

    public void setElevationType(String elevationType) {
        this.elevationType = elevationType;
    }


}
