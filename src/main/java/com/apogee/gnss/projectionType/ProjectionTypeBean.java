/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectionType;

/**
 *
 * @author admin
 */
public class ProjectionTypeBean {
    
private int projectiontype_id;
private String projectionType;
private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public int getProjectiontype_id() {
        return projectiontype_id;
    }

    public void setProjectiontype_id(int projectiontype_id) {
        this.projectiontype_id = projectiontype_id;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }


}
