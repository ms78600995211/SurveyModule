/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.pdaParams;

/**
 *
 * @author admin
 */
public class PDAParamsBean {
private String pdaparams_id;
private String pdaparams_name;
private String IP;
private String portNo;
private String url;
private String username;
private String passwd;
private String defaultConfig;
private String mountPoint;
private String remark;


    public String getPdaparams_name() {
        return pdaparams_name;
    }

    public void setPdaparams_name(String pdaparams_name) {
        this.pdaparams_name = pdaparams_name;
    }

    public String getPdaparams_id() {
        return pdaparams_id;
    }

    public void setPdaparams_id(String pdaparams_id) {
        this.pdaparams_id = pdaparams_id;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(String defaultConfig) {
        this.defaultConfig = defaultConfig;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }




}
