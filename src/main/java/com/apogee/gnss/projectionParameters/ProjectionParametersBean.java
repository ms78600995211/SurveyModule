/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectionParameters;

/**
 *
 * @author admin
 */
public class ProjectionParametersBean {

    private int projectionParam_id;
    private String zone_name;
    private String origin_lat;
    private String origin_lng;
    private String scale;
    private String false_easting;
    private String false_northing;
    private String paralell_1;
    private String paralell_2;
    private String misc1;
    private String misc2;
    private String misc3;
    private String misc4;
    private int projectiontype_id;
    private String projectionType;
    private String remark;

    public String getMisc4() {
        return misc4;
    }

    public void setMisc4(String misc4) {
        this.misc4 = misc4;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public int getProjectionParam_id() {
        return projectionParam_id;
    }

    public void setProjectionParam_id(int projectionParam_id) {
        this.projectionParam_id = projectionParam_id;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getOrigin_lat() {
        return origin_lat;
    }

    public void setOrigin_lat(String origin_lat) {
        this.origin_lat = origin_lat;
    }

    public String getOrigin_lng() {
        return origin_lng;
    }

    public void setOrigin_lng(String origin_lng) {
        this.origin_lng = origin_lng;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getFalse_easting() {
        return false_easting;
    }

    public void setFalse_easting(String false_easting) {
        this.false_easting = false_easting;
    }

    public String getFalse_northing() {
        return false_northing;
    }

    public void setFalse_northing(String false_northing) {
        this.false_northing = false_northing;
    }

    public String getParalell_1() {
        return paralell_1;
    }

    public void setParalell_1(String paralell_1) {
        this.paralell_1 = paralell_1;
    }

    public String getParalell_2() {
        return paralell_2;
    }

    public void setParalell_2(String paralell_2) {
        this.paralell_2 = paralell_2;
    }

    public String getMisc1() {
        return misc1;
    }

    public void setMisc1(String misc1) {
        this.misc1 = misc1;
    }

    public String getMisc2() {
        return misc2;
    }

    public void setMisc2(String misc2) {
        this.misc2 = misc2;
    }

    public String getMisc3() {
        return misc3;
    }

    public void setMisc3(String misc3) {
        this.misc3 = misc3;
    }

    public int getProjectiontype_id() {
        return projectiontype_id;
    }

    public void setProjectiontype_id(int projectiontype_id) {
        this.projectiontype_id = projectiontype_id;
    }

}
