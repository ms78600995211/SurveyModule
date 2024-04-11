/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.zoneData;

/**
 *
 * @author admin
 */
public class ZoneDataBean {
    
private int zonedata_id;
private  String zone;
private  String hemisphere;
private  int hemisphere_id;
private  String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getZonedata_id() {
        return zonedata_id;
    }

    public void setZonedata_id(int zonedata_id) {
        this.zonedata_id = zonedata_id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }

    public int getHemisphere_id() {
        return hemisphere_id;
    }

    public void setHemisphere_id(int hemisphere_id) {
        this.hemisphere_id = hemisphere_id;
    }





}
