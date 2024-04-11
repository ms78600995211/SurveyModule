/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.datumType;

/**
 *
 * @author lENOVO
 */
public class DatumTypeBean {

    private int datumtype_id;
    private String datumType_name;
    private String remark;

    /**
     * @return the datumtype_id
     */
    public int getDatumtype_id() {
        return datumtype_id;
    }

    /**
     * @param datumtype_id the datumtype_id to set
     */
    public void setDatumtype_id(int datumtype_id) {
        this.datumtype_id = datumtype_id;
    }

    /**
     * @return the datumType_name
     */
    public String getDatumType_name() {
        return datumType_name;
    }

    /**
     * @param datumType_name the datumType_name to set
     */
    public void setDatumType_name(String datumType_name) {
        this.datumType_name = datumType_name;
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
