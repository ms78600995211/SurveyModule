/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.maskAngle;

/**
 *
 * @author lENOVO
 */
public class MaskAngleBean {

    private int mask_angle_id;
    private String byte_value;
    private String display_value;
    private String remark;

    /**
     * @return the mask_angle_id
     */
    public int getMask_angle_id() {
        return mask_angle_id;
    }

    /**
     * @param mask_angle_id the mask_angle_id to set
     */
    public void setMask_angle_id(int mask_angle_id) {
        this.mask_angle_id = mask_angle_id;
    }

    /**
     * @return the byte_value
     */
    public String getByte_value() {
        return byte_value;
    }

    /**
     * @param byte_value the byte_value to set
     */
    public void setByte_value(String byte_value) {
        this.byte_value = byte_value;
    }

    /**
     * @return the display_value
     */
    public String getDisplay_value() {
        return display_value;
    }

    /**
     * @param display_value the display_value to set
     */
    public void setDisplay_value(String display_value) {
        this.display_value = display_value;
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
