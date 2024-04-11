/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.siteCalibration;

/**
 *
 * @author admin
 */
public class SiteCalibrationBean {
    
private String siteCal_id;
private String scale;                         
private String angle;
private String tx;
private String ty;
private String fixed_Easting;
private String fixed_Northing;
private String sigmaZ;
private String remark;

    public String getSiteCal_id() {
        return siteCal_id;
    }

    public void setSiteCal_id(String siteCal_id) {
        this.siteCal_id = siteCal_id;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public String getFixed_Easting() {
        return fixed_Easting;
    }

    public void setFixed_Easting(String fixed_Easting) {
        this.fixed_Easting = fixed_Easting;
    }

    public String getFixed_Northing() {
        return fixed_Northing;
    }

    public void setFixed_Northing(String fixed_Northing) {
        this.fixed_Northing = fixed_Northing;
    }


    public String getSigmaZ() {
        return sigmaZ;
    }

    public void setSigmaZ(String sigmaZ) {
        this.sigmaZ = sigmaZ;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
