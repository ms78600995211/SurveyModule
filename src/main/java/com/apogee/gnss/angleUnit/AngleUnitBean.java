/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.angleUnit;

/**
 *
 * @author admin
 */
public class AngleUnitBean {

    private int angleunit_id;
    private String angUnit_name;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAngleunit_id() {
        return angleunit_id;
    }

    public void setAngleunit_id(int angleunit_id) {
        this.angleunit_id = angleunit_id;
    }

    public String getAngUnit_name() {
        return angUnit_name;
    }

    public void setAngUnit_name(String angUnit_name) {
        this.angUnit_name = angUnit_name;
    }
    
}
