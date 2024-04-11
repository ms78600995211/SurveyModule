/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.communicationTypeMapping;

import com.apogee.gnss.pdaParams.PDAParamsBean;
import com.apogee.gnss.radioExternalParams.RadioExternalParamsBean;
import com.apogee.gnss.radioInternalParams.RadioInternalParamsBean;
import com.apogee.gnss.via4GParams.Via4GParamsBean;
import com.apogee.gnss.wifiParams.WifiParamsBean;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CommunicationTypeMappingModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveCommunicationTypeMapping(String type_of_communication_id, String communication_params, String defaultConfig, String remark, String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        int via4gparams_id = 0;
        int wifiparams_id = 0;
        int pdaparams_id = 0;
        int radiointernalparams_id = 0;
        int radioexternalparams_id = 0;
        String communication_params_name = "";
        String communication_params_id = "";
        String[] parts = communication_params.split("_");
        if (parts.length >= 3) {
            communication_params_name = parts[0];
            communication_params_id = parts[2];
        }
        if (communication_params_name.equalsIgnoreCase("radiointernalparams")) {
            radiointernalparams_id = parseInt(communication_params_id);
        } else if (communication_params_name.equalsIgnoreCase("radioexternalparams")) {
            radioexternalparams_id = parseInt(communication_params_id);

        } else if (communication_params_name.equalsIgnoreCase("wifiparams")) {
            wifiparams_id = parseInt(communication_params_id);

        } else if (communication_params_name.equalsIgnoreCase("via4gparams")) {
            via4gparams_id = parseInt(communication_params_id);

        } else {
            pdaparams_id = parseInt(communication_params_id);
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String time_stamp = formattedDateTime;
        String query = "SELECT MAX(communication_type_mapping_id) AS max_communication_type_mapping_id FROM communication_type_mapping;";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            int max_communication_type_mapping_id = 0;
            while (rs.next()) {
                max_communication_type_mapping_id = rs.getInt("max_communication_type_mapping_id");
            }
            max_communication_type_mapping_id = max_communication_type_mapping_id + 1;
            String communication_type_mapping = "communication_type_mapping";
            String primary_key = "_" + max_communication_type_mapping_id;
            String communication_type_mapping_name = communication_type_mapping.concat(primary_key);
            pstmt = null;
            query = "insert into communication_type_mapping(communication_type_mapping_name,type_of_communication_id,via4gparams_id,wifiparams_id,pdaparams_id,radiointernalparams_id,radioexternalparams_id,defaultConfig,active,created_by,created_at,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, communication_type_mapping_name);
            pstmt.setString(2, type_of_communication_id);
            pstmt.setInt(3, via4gparams_id);
            pstmt.setInt(4, wifiparams_id);
            pstmt.setInt(5, pdaparams_id);
            pstmt.setInt(6, radiointernalparams_id);
            pstmt.setInt(7, radioexternalparams_id);
            pstmt.setString(8, defaultConfig);
            pstmt.setString(9, "Y");
            pstmt.setString(10, name);
            pstmt.setString(11, time_stamp);
            pstmt.setString(12, remark);
            pstmt.setInt(13, 0);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.saveCommunicationTypeMapping()" + e);
        }
    }

    public List getTypeOfCommunicationList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommunicationTypeMappingBean> getTypeOfCommunicationList = new ArrayList<>();
        String query = "select type_of_communication_id,communicationTypes from type_of_communication where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CommunicationTypeMappingBean bean = new CommunicationTypeMappingBean();
                bean.setType_of_communication_id(rs.getString("type_of_communication_id"));
                String communicationTypes = rs.getString("communicationTypes");
                if (communicationTypes.equalsIgnoreCase("RS232")) {
                    communicationTypes = "External Radio";
                }
                if (communicationTypes.equalsIgnoreCase("Radio")) {
                    communicationTypes = "Internal Radio";
                }
                bean.setType_of_communication_name(communicationTypes);
                getTypeOfCommunicationList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getTypeOfCommunicationList()" + e);
        }
        return getTypeOfCommunicationList;
    }

    public String getCommunicationType(String type_of_communication_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String communicationTypes = "";
        String query = "select communicationTypes from type_of_communication where active='Y' and type_of_communication_id= ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, type_of_communication_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                communicationTypes = rs.getString("communicationTypes");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getCommunicationType()" + e);
        }
        return communicationTypes;
    }

    public List getWifiparamsList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommunicationTypeMappingBean> getWifiparamsList = new ArrayList<>();
        String query = "select wifiparams_id,wifiparams_name from wifiparams where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommunicationTypeMappingBean bean = new CommunicationTypeMappingBean();
                bean.setParams_id(rs.getString("wifiparams_id"));
                bean.setParams_name(rs.getString("wifiparams_name"));
                getWifiparamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getWifiparamsList()" + e);
        }
        return getWifiparamsList;
    }

    public List getPdaparamsList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommunicationTypeMappingBean> getPdaparamsList = new ArrayList<>();
        String query = "select pdaparams_id,pdaparams_name from pdaparams where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommunicationTypeMappingBean bean = new CommunicationTypeMappingBean();
                bean.setParams_id(rs.getString("pdaparams_id"));
                bean.setParams_name(rs.getString("pdaparams_name"));
                getPdaparamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getPdaparamsList()" + e);
        }
        return getPdaparamsList;
    }

    public List getVia4gparamsList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommunicationTypeMappingBean> getVia4gparamsList = new ArrayList<>();
        String query = "select via4gparams_id ,via4gparams_name from via4gparams where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommunicationTypeMappingBean bean = new CommunicationTypeMappingBean();
                bean.setParams_id(rs.getString("via4gparams_id"));
                bean.setParams_name(rs.getString("via4gparams_name"));
                getVia4gparamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getVia4gparamsList()" + e);
        }
        return getVia4gparamsList;
    }

    public List getRadiointernalparamsList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommunicationTypeMappingBean> getRadiointernalparamsList = new ArrayList<>();
        String query = "select radiointernalparams_id ,radiointernalparams_name from radiointernalparams where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommunicationTypeMappingBean bean = new CommunicationTypeMappingBean();
                bean.setParams_id(rs.getString("radiointernalparams_id"));
                bean.setParams_name(rs.getString("radiointernalparams_name"));
                getRadiointernalparamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getRadiointernalparamsList()" + e);
        }
        return getRadiointernalparamsList;
    }

    public List getRadioexternalparamsList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommunicationTypeMappingBean> getRadioexternalparamsList = new ArrayList<>();
        String query = "select radioexternalparams_id ,radioexternalparams_name from radioexternalparams where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommunicationTypeMappingBean bean = new CommunicationTypeMappingBean();
                bean.setParams_id(rs.getString("radioexternalparams_id"));
                bean.setParams_name(rs.getString("radioexternalparams_name"));
                getRadioexternalparamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getRadioexternalparamsList()" + e);
        }
        return getRadioexternalparamsList;
    }

    public List getCommunicationTypeMappingList(String typeOfCommunicationId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        List<CommunicationTypeMappingBean> getCommunicationTypeMappingList = new ArrayList<>();
        String query = " select * from communication_type_mapping where active='Y' ";
        if (typeOfCommunicationId != null && !typeOfCommunicationId.isEmpty()) {
            query += " and type_of_communication_id= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (typeOfCommunicationId != null && !typeOfCommunicationId.isEmpty()) {
                pstmt.setString(1, typeOfCommunicationId);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommunicationTypeMappingBean bean = new CommunicationTypeMappingBean();
                bean.setCommunication_type_mapping_name(rs.getString("communication_type_mapping_name"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setRemark(rs.getString("remark"));
                String communicationTypes = "";
                String via4gparams_name = "";
                String wifiparams_name = "";
                String pdaparams_name = "";
                String radiointernalparams_name = "";
                String radioexternalparams_name = "";
                String type_of_communication_id = rs.getString("type_of_communication_id");
                query = " select communicationTypes from type_of_communication where active='Y'  and type_of_communication_id= ?";
                pstmt1 = connection.prepareStatement(query);
                pstmt1.setString(1, type_of_communication_id);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    communicationTypes = rs1.getString("communicationTypes");
                    bean.setType_of_communication_name(communicationTypes);
                }
                String via4gparams_id = rs.getString("via4gparams_id");
                pstmt1 = null;
                rs1 = null;
                query = " select via4gparams_name from via4gparams where active='Y'  and via4gparams_id= ?";
                pstmt1 = connection.prepareStatement(query);
                pstmt1.setString(1, via4gparams_id);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    via4gparams_name = rs1.getString("via4gparams_name");
                    bean.setVia4gparams_name(via4gparams_name);
                }
                String wifiparams_id = rs.getString("wifiparams_id");
                pstmt1 = null;
                rs1 = null;
                query = " select wifiparams_name from wifiparams where active='Y'  and wifiparams_id= ?";
                pstmt1 = connection.prepareStatement(query);
                pstmt1.setString(1, wifiparams_id);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    wifiparams_name = rs1.getString("wifiparams_name");
                    bean.setWifiparams_name(wifiparams_name);
                }
                String pdaparams_id = rs.getString("pdaparams_id");
                pstmt1 = null;
                rs1 = null;
                query = " select pdaparams_name from pdaparams where active='Y'  and pdaparams_id= ?";
                pstmt1 = connection.prepareStatement(query);
                pstmt1.setString(1, pdaparams_id);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    pdaparams_name = rs1.getString("pdaparams_name");
                    bean.setPdaparams_name(pdaparams_name);
                }
                String radiointernalparams_id = rs.getString("radiointernalparams_id");
                pstmt1 = null;
                rs1 = null;
                query = " select radiointernalparams_name from radiointernalparams where active='Y'  and radiointernalparams_id= ?";
                pstmt1 = connection.prepareStatement(query);
                pstmt1.setString(1, radiointernalparams_id);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    radiointernalparams_name = rs1.getString("radiointernalparams_name");
                    bean.setRadiointernalparams_name(radiointernalparams_name);
                }
                String radioexternalparams_id = rs.getString("radioexternalparams_id");
                pstmt1 = null;
                rs1 = null;
                query = " select radioexternalparams_name from radioexternalparams where active='Y'  and radioexternalparams_id= ?";
                pstmt1 = connection.prepareStatement(query);
                pstmt1.setString(1, radioexternalparams_id);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    radioexternalparams_name = rs1.getString("radioexternalparams_name");
                    bean.setRadioexternalparams_name(radioexternalparams_name);
                }
                getCommunicationTypeMappingList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getCommunicationTypeMappingList()" + e);
        }
        return getCommunicationTypeMappingList;
    }

    public List<Via4GParamsBean> getVia4gparams(String via4gParam_name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Via4GParamsBean> getVia4gparamsList = new ArrayList<>();
        String query = "select * from via4gparams where active='Y' and via4gparams_name= ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, via4gParam_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Via4GParamsBean bean = new Via4GParamsBean();
                bean.setVia4gparams_id(rs.getString("via4gparams_id"));
                bean.setIP(rs.getString("IP"));
                bean.setPortNo(rs.getString("portNo"));
                bean.setUsername(rs.getString("username"));
                bean.setPasswd(rs.getString("passwd"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setRemark(rs.getString("remark"));
                getVia4gparamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getVia4gparams()" + e);
        }
        return getVia4gparamsList;
    }

    public List<WifiParamsBean> getWifiparamsList(String wifiParam_name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<WifiParamsBean> getWifiparamsList = new ArrayList<>();
        String query = "select * from wifiparams where active='Y' and wifiparams_name= ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, wifiParam_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                WifiParamsBean bean = new WifiParamsBean();
                bean.setWifiparams_id(rs.getString("wifiparams_id"));
                bean.setIP(rs.getString("IP"));
                bean.setPortNo(rs.getString("portNo"));
                bean.setSsid(rs.getString("ssid"));
                bean.setSsid_password(rs.getString("ssid_password"));
                bean.setUsername(rs.getString("username"));
                bean.setPasswd(rs.getString("passwd"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setMountPoint(rs.getString("mountPoint"));
                bean.setRemark(rs.getString("remark"));
                getWifiparamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getWifiparamsList()" + e);
        }
        return getWifiparamsList;
    }

    public List<PDAParamsBean> getPDAparamsList(String pdaparams_name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PDAParamsBean> getPDAparamsList = new ArrayList<>();
        String query = "select * from pdaparams where active='Y' and pdaparams_name= ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, pdaparams_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PDAParamsBean bean = new PDAParamsBean();
                bean.setPdaparams_id(rs.getString("pdaparams_id"));
                bean.setIP(rs.getString("IP"));
                bean.setPortNo(rs.getString("portNo"));
                bean.setUrl(rs.getString("url"));
                bean.setUsername(rs.getString("username"));
                bean.setPasswd(rs.getString("passwd"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setMountPoint(rs.getString("mountPoint"));
                bean.setRemark(rs.getString("remark"));;
                getPDAparamsList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getPDAparamsList()" + e);
        }
        return getPDAparamsList;
    }

    public List<RadioExternalParamsBean> getRadioExternalParamsList(String externalParams) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<RadioExternalParamsBean> getRadioExternalParamsList = new ArrayList<>();
        String query = "select * from radioexternalparams where active='Y' and radioexternalparams_name= ? ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, externalParams);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RadioExternalParamsBean bean = new RadioExternalParamsBean();
                bean.setRadioexternalparams_id(rs.getString("radioexternalparams_id"));
                bean.setPower(rs.getString("power"));
                bean.setProtocol(rs.getString("protocol"));
                bean.setFrequency(rs.getString("frequency"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setRemark(rs.getString("remark"));;
                getRadioExternalParamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getRadioExternalParamsList()" + e);
        }
        return getRadioExternalParamsList;
    }

    public List<RadioInternalParamsBean> getRadioInternalParamsList(String internalParams) {
        List<RadioInternalParamsBean> getRadioInternalParamsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "select * from radiointernalparams where active='Y' and radiointernalparams_name= ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, internalParams);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RadioInternalParamsBean bean = new RadioInternalParamsBean();
                bean.setRadiointernalparams_id(rs.getString("radiointernalparams_id"));
                bean.setDatarate(rs.getString("datarate"));
                bean.setBaudrate(rs.getString("baudrate"));
                bean.setPower(rs.getString("power"));
                bean.setFrequency(rs.getString("frequency"));
                bean.setDefaultConfig(rs.getString("defaultConfig"));
                bean.setRemark(rs.getString("remark"));;
                getRadioInternalParamsList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.getRadioInternalParamsList()" + e);
        }
        return getRadioInternalParamsList;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCOLOR_OK() {
        return COLOR_OK;
    }

    public void setCOLOR_OK(String COLOR_OK) {
        this.COLOR_OK = COLOR_OK;
    }

    public String getCOLOR_ERROR() {
        return COLOR_ERROR;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCOLOR_ERROR(String COLOR_ERROR) {
        this.COLOR_ERROR = COLOR_ERROR;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingModel.closeConnection()" + e);
        }
    }

}
