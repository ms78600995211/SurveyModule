/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.statusType;

/**
 *
 * @author lENOVO
 */
public class StatusTypeBean {

    private int status_type_id;
    private String status_type;
    private String description;
    private String remark;

    /**
     * @return the status_type_id
     */
    public int getStatus_type_id() {
        return status_type_id;
    }

    /**
     * @param status_type_id the status_type_id to set
     */
    public void setStatus_type_id(int status_type_id) {
        this.status_type_id = status_type_id;
    }

    /**
     * @return the status_type
     */
    public String getStatus_type() {
        return status_type;
    }

    /**
     * @param status_type the status_type to set
     */
    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
