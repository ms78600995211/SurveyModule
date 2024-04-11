/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apogee.gnss.model;

import static com.apogee.gnss.utility.FieldNameConstantUtil.PRICEERROR;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author lENOVO
 */
@Service
public class TestModel {

    private Connection connection;

//    static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    ResourceBundle resourceBundle1 = ResourceBundle.getBundle("ApplicationResources");

//    static String driverClass = resourceBundle.getString("DATABASE_DRIVERCLASS");
//    static String connectionString = resourceBundle.getString("DATABASE_CONNECTIONSTRING");
//    static String db_username = resourceBundle.getString("DATABASE_USERNAME");
//    static String db_password = resourceBundle.getString("DATABASE_PASSWORD");
//
//    public Connection setDbConnection() {
//        try {
//            Class.forName(driverClass);
//            connection = (Connection) DriverManager.getConnection(connectionString, db_username, db_password);
//        } catch (Exception e) {
//            System.out.println("DBConncetion getConnection() Error: " + e);
//        }
//        return connection;
//    }
    public void setDbConnection(Connection con) {
        try {
            connection = con;
        } catch (Exception e) {
            System.out.println("com.jpss.registartioncummodel.LoginModel.setConnection(): " + e);
        }
    }

    public String getNtripDetails() {
        String allData = "";
        int ntrip_survey_table_id = 0;
        String message = "";
        int price = 0;
        try {

            String query = "select * from ntrip_survey_table;";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                ntrip_survey_table_id = rset.getInt("ntrip_survey_table_id");
                message = rset.getString("message");
                price = rset.getInt("price");
            }
        } catch (Exception e) {
            System.out.println("Error: DealersOrderModel getAllApprovedOrders()-" + e);

        }
        if (price > 50) {
            allData = ntrip_survey_table_id + "," + message + "," + price;
        } else {
            allData = PRICEERROR;
        }
        return allData;

    }

    public String getPersonDetails() {
        String salesExecutiveDetails = "";
        String organisation_name = "APOGEE GNSS";
        String designation_name = "Sales Executive";
        try {
            salesExecutiveDetails = GetExecutives(organisation_name, designation_name);
        } catch (Exception e) {
            System.out.println("DBConncetion getConnection() Error: " + e);
        }
        return salesExecutiveDetails;
    }

    public String GetExecutives(String a, String b) throws Exception {

        String baseiname = "";
        String getPersonByDesignationHierarchy = resourceBundle1.getString("ORG_MODULE_PERSONBYDESIGNATION_API_URL");
//        String url = "http://120.138.10.146:8080/OrganisationModule/webAPI/service/getPersonByDesignationHierarchy";
        String url = getPersonByDesignationHierarchy;

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");

        String urlParameters = ("{\"organisation_name\": \"" + a + "\",\"designation_name\": \"" + b + "\"}");

        httpClient.setDoOutput(
                true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = httpClient.getResponseCode();

//        System.out.println(
//                "\nSending 'POST' request to URL : " + url);
//        System.out.println(
//                "Post parameters : " + urlParameters);
//        System.out.println(
//                "Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);

            }
            return baseiname = response.toString();
            //print result
            // System.out.println("data" : +baseiname);

        }
    }

    public JSONArray getConstellation() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from constellation where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("constellation_id", rset.getString("constellation_id"));
                obj.put("constellation_name", rset.getString("constellation_name"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getConstellation()" + e);
        }
        return rowData;
    }

    public JSONArray getStaticParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from static_params where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("static_params_id", rset.getString("static_params_id"));
                obj.put("file_name", rset.getString("file_name"));
                obj.put("total_time", rset.getString("total_time"));
                obj.put("sampling_rate", rset.getString("sampling_rate"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getStaticParams()" + e);
        }
        return rowData;
    }

    public JSONArray getSatelliteConfigConstellationMapping() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from satellite_configuration_constellation_mapping where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("satellite_configuration_constellation_mapping_id", rset.getString("satellite_configuration_constellation_mapping_id"));
                obj.put("satellite_configuration_id", rset.getString("satellite_configuration_id"));
                obj.put("constellation_id", rset.getString("constellation_id"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("config_time", rset.getString("config_time"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getSatelliteConfigConstellationMapping()" + e);
        }
        return rowData;
    }

    public JSONArray getManualbaseParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from manual_base_params where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("manual_base_params_id", rset.getString("manual_base_params_id"));
                obj.put("manual_base_params_name", rset.getString("manual_base_params_name"));
                obj.put("latitude", rset.getString("latitude"));
                obj.put("longitude", rset.getString("longitude"));
                obj.put("altitude", rset.getString("altitude"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getManualbaseParams()" + e);
        }
        return rowData;
    }

    public JSONArray getPPKParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from ppk_params where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("ppk_params_id", rset.getString("ppk_params_id"));
                obj.put("file_name", rset.getString("file_name"));
                obj.put("total_time", rset.getString("total_time"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getPPKParams()" + e);
        }
        return rowData;
    }

    public JSONArray getVia4GParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from via4gparams where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("via4gparams_id", rset.getString("via4gparams_id"));
                obj.put("via4gparams_name", rset.getString("via4gparams_name"));
                obj.put("IP", rset.getString("IP"));
                obj.put("portNo", rset.getString("portNo"));
                obj.put("url", rset.getString("url"));
                obj.put("username", rset.getString("username"));
                obj.put("passwd", rset.getString("passwd"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("mountPoint", rset.getString("mountPoint"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getVia4GParams()" + e);
        }
        return rowData;
    }

    public JSONArray getWifiParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from wifiparams where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("wifiparams_id", rset.getString("wifiparams_id"));
                obj.put("wifiparams_name", rset.getString("wifiparams_name"));
                obj.put("IP", rset.getString("IP"));
                obj.put("portNo", rset.getString("portNo"));
                obj.put("ssid", rset.getString("ssid"));
                obj.put("ssid_password", rset.getString("ssid_password"));
                obj.put("url", rset.getString("url"));
                obj.put("username", rset.getString("username"));
                obj.put("passwd", rset.getString("passwd"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("mountPoint", rset.getString("mountPoint"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getWifiParams()" + e);
        }
        return rowData;
    }

    public JSONArray getPDAParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from pdaparams where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("pdaparams_id", rset.getString("pdaparams_id"));
                obj.put("pdaparams_name", rset.getString("pdaparams_name"));
                obj.put("IP", rset.getString("IP"));
                obj.put("portNo", rset.getString("portNo"));
                obj.put("url", rset.getString("url"));
                obj.put("username", rset.getString("username"));
                obj.put("passwd", rset.getString("passwd"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("mountPoint", rset.getString("mountPoint"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getPDAParams()" + e);
        }
        return rowData;
    }

    public JSONArray getRadioInternalParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from radiointernalparams where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("radiointernalparams_id", rset.getString("radiointernalparams_id"));
                obj.put("radiointernalparams_name", rset.getString("radiointernalparams_name"));
                obj.put("datarate", rset.getString("datarate"));
                obj.put("baudrate", rset.getString("baudrate"));
                obj.put("power", rset.getString("power"));
                obj.put("frequency", rset.getString("frequency"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getRadioInternalParams()" + e);
        }
        return rowData;
    }

    public JSONArray getRadioExternalParams() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from radioexternalparams where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("radioexternalparams_id", rset.getString("radioexternalparams_id"));
                obj.put("radioexternalparams_name", rset.getString("radioexternalparams_name"));
                obj.put("baudrate", rset.getString("baudrate"));
                obj.put("power", rset.getString("power"));
                obj.put("protocol", rset.getString("protocol"));
                obj.put("frequency", rset.getString("frequency"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getRadioExternalParams()" + e);
        }
        return rowData;
    }

    public JSONArray getTypeOfCommunication() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from type_of_communication where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("type_of_communication_id", rset.getString("type_of_communication_id"));
                obj.put("type_of_communication_name", rset.getString("type_of_communication_name"));
                obj.put("communicationTypes", rset.getString("communicationTypes"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getTypeOfCommunication()" + e);
        }
        return rowData;
    }

    public JSONArray getDeviceConfiguration() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from device_configuration where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();

                obj.put("device_configuration_id", rset.getString("device_configuration_id"));
                obj.put("device_configuration_name", rset.getString("device_configuration_name"));
                obj.put("dynamic_configuration_mapping_id", rset.getString("dynamic_configuration_mapping_id"));
                obj.put("mapping_type_of_communication_id", rset.getString("mapping_type_of_communication_id"));
                obj.put("mask_angle_byteValue", rset.getString("mask_angle_byteValue"));
                obj.put("mask_angle_displayValue", rset.getString("mask_angle_displayValue"));
                obj.put("device_work_mode_name", rset.getString("device_work_mode_name"));
                obj.put("device_work_mode_value", rset.getString("device_work_mode_value"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("config_time", rset.getString("config_time"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getDeviceConfiguration()" + e);
        }
        return rowData;
    }

    public JSONArray getDeviceConfigHierarchy() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from device_confighierarchy where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("device_configHierarchy_id", rset.getString("device_configHierarchy_id"));
                obj.put("device_configHierarchy_name", rset.getString("device_configHierarchy_name"));
                obj.put("parent_id", rset.getString("parent_id"));
                obj.put("is_super_child", rset.getString("is_super_child"));
                obj.put("generation", rset.getString("generation"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getDeviceConfigHierarchy()" + e);
        }
        return rowData;
    }

    public JSONArray getDynamicConfigMapping() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from dynamic_configuration_mapping where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("dynamic_configuration_mapping_id", rset.getString("dynamic_configuration_mapping_id"));
                obj.put("dynamic_config_name", rset.getString("dynamic_config_name"));
                obj.put("device_configHierarchy_id", rset.getString("device_configHierarchy_id"));
                obj.put("manual_base_params_id", rset.getString("manual_base_params_id"));
                obj.put("ppk_params_id", rset.getString("ppk_params_id"));
                obj.put("static_params_id", rset.getString("static_params_id"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getDynamicConfigMapping()" + e);
        }
        return rowData;
    }

    public JSONArray getSatelliteConfiguration() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from satellite_configuration where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("satellite_configuration_id", rset.getString("satellite_configuration_id"));
                obj.put("satellite_configuration_name", rset.getString("satellite_configuration_name"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("config_time", rset.getString("config_time"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getSatelliteConfiguration()" + e);
        }
        return rowData;
    }

    public JSONArray getMiscellaneousConfiguration() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from miscellaneous_configuration where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("miscConfig_id", rset.getString("miscConfig_id"));
                obj.put("miscConfig_name", rset.getString("miscConfig_name"));
                obj.put("position_type", rset.getString("position_type"));
                obj.put("min_no_of_satellite", rset.getString("min_no_of_satellite"));
                obj.put("age_of_correction", rset.getString("age_of_correction"));
                obj.put("pdop_tolerance", rset.getString("pdop_tolerance"));
                obj.put("horizontal_percision", rset.getString("horizontal_percision"));
                obj.put("vertical_percision", rset.getString("vertical_percision"));
                obj.put("check_validity_of_point_taken", rset.getString("check_validity_of_point_taken"));
                obj.put("observation_time_per_point", rset.getString("observation_time_per_point"));
                obj.put("show_point_name", rset.getString("show_point_name"));
                obj.put("show_code_name", rset.getString("show_code_name"));
                obj.put("stake_horizontal_percision", rset.getString("stake_horizontal_percision"));
                obj.put("stake_vertical_percision", rset.getString("stake_vertical_percision"));
                obj.put("stake_target_distance", rset.getString("stake_target_distance"));
                obj.put("inner_circle_radius", rset.getString("inner_circle_radius"));
                obj.put("outer_circle_radius", rset.getString("outer_circle_radius"));
                obj.put("auto_connect_last_connected_reciever", rset.getString("auto_connect_last_connected_reciever"));
                obj.put("automatically_send_last_working_profile", rset.getString("automatically_send_last_working_profile"));
                obj.put("enable_compass", rset.getString("enable_compass"));
                obj.put("enable_e_bubble", rset.getString("enable_e_bubble"));
                obj.put("enable_lrf", rset.getString("enable_lrf"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("miscConfig_time", rset.getString("miscConfig_time"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getMiscellaneousConfiguration()" + e);
        }
        return rowData;
    }

    public JSONArray getSurveyConfiguration() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from survey_configuration where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("survey_configuration_id", rset.getString("survey_configuration_id"));
                obj.put("survey_configuration_name", rset.getString("survey_configuration_name"));
                obj.put("datum_id", rset.getString("datum_id"));
                obj.put("zonedata_id", rset.getString("zonedata_id"));
                obj.put("elevationtype_id", rset.getString("elevationtype_id"));
                obj.put("distanceunit_id", rset.getString("distanceunit_id"));
                obj.put("angleunit_id", rset.getString("angleunit_id"));
                obj.put("projectionParam_id", rset.getString("projectionParam_id"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("config_time", rset.getString("config_time"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getSurveyConfiguration()" + e);
        }
        return rowData;
    }

    public JSONArray getProjectConfigurationMapping() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from project_configuration_mapping where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("project_configuration_mapping_id", rset.getString("project_configuration_mapping_id"));
                obj.put("project_configuration_mapping_name", rset.getString("project_configuration_mapping_name"));
                obj.put("survey_configuration_id", rset.getString("survey_configuration_id"));
                obj.put("device_configuration_id", rset.getString("device_configuration_id"));
                obj.put("satellite_configuration_id", rset.getString("satellite_configuration_id"));
                obj.put("miscellaneous_configuration_id", rset.getString("miscellaneous_configuration_id"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("config_time", rset.getString("config_time"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getProjectConfigurationMapping()" + e);
        }
        return rowData;
    }

    public JSONArray getProjectDetails() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from project_details where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("project_details_id", rset.getString("project_details_id"));
                obj.put("project_name", rset.getString("project_name"));
                obj.put("operator", rset.getString("operator"));
                obj.put("comment", rset.getString("comment"));
                obj.put("siteCal_id", rset.getString("siteCal_id"));
                obj.put("project_configuration_mapping_id", rset.getString("project_configuration_mapping_id"));
                obj.put("create_by", rset.getString("create_by"));
                obj.put("active", rset.getString("active"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getProjectDetails()" + e);
        }
        return rowData;
    }

    public JSONArray getProjectConfigMainMapping() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from project_configuration_main_mapping where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("project_configuration_main_mapping_id", rset.getString("project_configuration_main_mapping_id"));
                obj.put("project_details_id", rset.getString("project_details_id"));
                obj.put("project_configuration_mapping_id", rset.getString("project_configuration_mapping_id"));
                obj.put("create_by", rset.getString("create_by"));
                obj.put("active", rset.getString("active"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getProjectConfigMainMapping()" + e);
        }
        return rowData;
    }

    public JSONArray getSiteCalibration() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from site_calibration where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("siteCal_id", rset.getString("siteCal_id"));
                obj.put("Scale", rset.getString("Scale"));
                obj.put("Angle", rset.getString("Angle"));
                obj.put("Tx", rset.getString("Tx"));
                obj.put("Ty", rset.getString("Ty"));
                obj.put("Fixed_Easting", rset.getString("Fixed_Easting"));
                obj.put("Fixed_Northing", rset.getString("Fixed_Northing"));
                obj.put("sigmaZ", rset.getString("sigmaZ"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("active", rset.getString("active"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getSiteCalibration()" + e);
        }
        return rowData;
    }

    public JSONArray getCommunicationTypeMapping() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = " select * from communication_type_mapping where active='Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                obj.put("communication_type_mapping_id", rset.getString("communication_type_mapping_id"));
                obj.put("communication_type_mapping_name", rset.getString("communication_type_mapping_name"));
                obj.put("type_of_communication_id", rset.getString("type_of_communication_id"));
                obj.put("via4gparams_id", rset.getString("via4gparams_id"));
                obj.put("wifiparams_id", rset.getString("wifiparams_id"));
                obj.put("pdaparams_id", rset.getString("pdaparams_id"));
                obj.put("radiointernalparams_id", rset.getString("radiointernalparams_id"));
                obj.put("radioexternalparams_id", rset.getString("radioexternalparams_id"));
                obj.put("defaultConfig", rset.getString("defaultConfig"));
                obj.put("active", rset.getString("active"));
                obj.put("created_by", rset.getString("created_by"));
                obj.put("created_at", rset.getString("created_at"));
                obj.put("remark", rset.getString("remark"));
                obj.put("revision_no", rset.getString("revision_no"));
                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.getCommunicationTypeMapping()" + e);
        }
        return rowData;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.TestModel.closeConnection()" + e);
        }
    }

}
