/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.showData;

/**
 *
 * @author admin
 */
public class ShowDataBean {

    private int show_data_id;
    private String form_mapping_id;
    private String column_name;
    private String column_values;
    private String column_type;
    private String column_subType;
    private String folder_path;
    private String remark;

    public String getForm_mapping_id() {
        return form_mapping_id;
    }

    public void setForm_mapping_id(String form_mapping_id) {
        this.form_mapping_id = form_mapping_id;
    }

    public String getColumn_type() {
        return column_type;
    }

    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }

    public String getColumn_subType() {
        return column_subType;
    }

    public void setColumn_subType(String column_subType) {
        this.column_subType = column_subType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getShow_data_id() {
        return show_data_id;
    }

    public void setShow_data_id(int show_data_id) {
        this.show_data_id = show_data_id;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_values() {
        return column_values;
    }

    public void setColumn_values(String column_values) {
        this.column_values = column_values;
    }

    public String getFolder_path() {
        return folder_path;
    }

    public void setFolder_path(String folder_path) {
        this.folder_path = folder_path;
    }

}
