/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.deviceConfighierarchy;

/**
 *
 * @author admin
 */
public class DeviceConfighierarchyBean {
    
 private String device_configHierarchy_id;
 private String device_configHierarchy_name;
 private String parent_id;
 private String parent_name;
 private String is_super_child;
 private String generation;
 private String defaultConfig;
 private String remark;


    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getDevice_configHierarchy_id() {
        return device_configHierarchy_id;
    }

    public void setDevice_configHierarchy_id(String device_configHierarchy_id) {
        this.device_configHierarchy_id = device_configHierarchy_id;
    }

    public String getDevice_configHierarchy_name() {
        return device_configHierarchy_name;
    }

    public void setDevice_configHierarchy_name(String device_configHierarchy_name) {
        this.device_configHierarchy_name = device_configHierarchy_name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getIs_super_child() {
        return is_super_child;
    }

    public void setIs_super_child(String is_super_child) {
        this.is_super_child = is_super_child;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(String defaultConfig) {
        this.defaultConfig = defaultConfig;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }











}
