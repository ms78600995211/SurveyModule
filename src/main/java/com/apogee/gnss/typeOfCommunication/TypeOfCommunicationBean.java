/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.typeOfCommunication;

/**
 *
 * @author admin
 */
public class TypeOfCommunicationBean {


private String type_of_communication_id;
private String type_of_communication_name;
private String communicationTypes;
private String defaultConfig;
private String remark;



    public String getType_of_communication_name() {
        return type_of_communication_name;
    }

    public void setType_of_communication_name(String type_of_communication_name) {
        this.type_of_communication_name = type_of_communication_name;
    }
    public String getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(String defaultConfig) {
        this.defaultConfig = defaultConfig;
    }
    public String getType_of_communication_id() {
        return type_of_communication_id;
    }

    public void setType_of_communication_id(String type_of_communication_id) {
        this.type_of_communication_id = type_of_communication_id;
    }

    public String getCommunicationTypes() {
        return communicationTypes;
    }

    public void setCommunicationTypes(String communicationTypes) {
        this.communicationTypes = communicationTypes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }





    
}
