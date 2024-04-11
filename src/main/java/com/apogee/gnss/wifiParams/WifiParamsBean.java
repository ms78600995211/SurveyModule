/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.wifiParams;

/**
 *
 * @author admin
 */
public class WifiParamsBean {


private String wifiparams_id;
private String wifiparams_name;
private String IP;
private String portNo;
private String ssid;
private String ssid_password;
private String url;
private String apn;
private String username;
private String passwd;
private String defaultConfig;
private String mountPoint;
private String remark;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }
    public String getWifiparams_name() {
        return wifiparams_name;
    }

    public void setWifiparams_name(String wifiparams_name) {
        this.wifiparams_name = wifiparams_name;
    }
    public String getWifiparams_id() {
        return wifiparams_id;
    }

    public void setWifiparams_id(String wifiparams_id) {
        this.wifiparams_id = wifiparams_id;
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

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getSsid_password() {
        return ssid_password;
    }

    public void setSsid_password(String ssid_password) {
        this.ssid_password = ssid_password;
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
