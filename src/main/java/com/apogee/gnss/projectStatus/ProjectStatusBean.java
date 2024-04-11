/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectStatus;

/**
 *
 * @author lENOVO
 */
public class ProjectStatusBean {

    private int project_status_id;
    private String status;
    private String description;
    private int status_type_id;
    private String status_type;
    private String remark;

    /**
     * @return the project_status_id
     */
    public int getProject_status_id() {
        return project_status_id;
    }

    /**
     * @param project_status_id the project_status_id to set
     */
    public void setProject_status_id(int project_status_id) {
        this.project_status_id = project_status_id;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the remark
     */
   
}
