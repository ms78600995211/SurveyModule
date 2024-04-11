/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.drive;

/**
 *
 * @author admin
 */
public class DriveBean {

    private String form_name;
    private String file_name;
    private String subtype_name;
    private String form_mapping_id;
    private String show_data_id;
    private String type_name;
    private String folder_path;
    private String fileCheck;
    private String remark;

    public String getFileCheck() {
        return fileCheck;
    }

    public void setFileCheck(String fileCheck) {
        this.fileCheck = fileCheck;
    }
    public String getShow_data_id() {
        return show_data_id;
    }

    public void setShow_data_id(String show_data_id) {
        this.show_data_id = show_data_id;
    }
    public String getForm_mapping_id() {
        return form_mapping_id;
    }

    public void setForm_mapping_id(String form_mapping_id) {
        this.form_mapping_id = form_mapping_id;
    }

    public String getSubtype_name() {
        return subtype_name;
    }

    public void setSubtype_name(String subtype_name) {
        this.subtype_name = subtype_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getFolder_path() {
        return folder_path;
    }

    public void setFolder_path(String folder_path) {
        this.folder_path = folder_path;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
