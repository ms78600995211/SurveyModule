/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.form;

/**
 *
 * @author lENOVO
 */
public class FormBean {

    private int form_id;
    private int form_mapping_id;
    private String form_name;
    private String remark;
    private int column_type_id;
    private String type_name;
    private int column_subtype_id;
    private String subtype_name;
    private String display_name;

    /**
     * @return the form_id
     */
    public int getForm_id() {
        return form_id;
    }

    /**
     * @param form_id the form_id to set
     */
    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    /**
     * @return the form_name
     */
    public String getForm_name() {
        return form_name;
    }

    /**
     * @param form_name the form_name to set
     */
    public void setForm_name(String form_name) {
        this.form_name = form_name;
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

    /**
     * @return the column_type_id
     */
    public int getColumn_type_id() {
        return column_type_id;
    }

    /**
     * @param column_type_id the column_type_id to set
     */
    public void setColumn_type_id(int column_type_id) {
        this.column_type_id = column_type_id;
    }

    /**
     * @return the type_name
     */
    public String getType_name() {
        return type_name;
    }

    /**
     * @param type_name the type_name to set
     */
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    /**
     * @return the column_subtype_id
     */
    public int getColumn_subtype_id() {
        return column_subtype_id;
    }

    /**
     * @param column_subtype_id the column_subtype_id to set
     */
    public void setColumn_subtype_id(int column_subtype_id) {
        this.column_subtype_id = column_subtype_id;
    }

    /**
     * @return the subtype_name
     */
    public String getSubtype_name() {
        return subtype_name;
    }

    /**
     * @param subtype_name the subtype_name to set
     */
    public void setSubtype_name(String subtype_name) {
        this.subtype_name = subtype_name;
    }

    /**
     * @return the form_mapping_id
     */
    public int getForm_mapping_id() {
        return form_mapping_id;
    }

    /**
     * @param form_mapping_id the form_mapping_id to set
     */
    public void setForm_mapping_id(int form_mapping_id) {
        this.form_mapping_id = form_mapping_id;
    }

    /**
     * @return the display_name
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * @param display_name the display_name to set
     */
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
