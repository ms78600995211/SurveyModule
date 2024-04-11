/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apogee.gnss.controller;

//import com.apogee.request.TestOne;
import com.apogee.gnss.DBConnection;
import com.apogee.gnss.form.FormBean;
import com.apogee.gnss.formMapping.FormMappingModel;
import com.apogee.gnss.form.FormModel;
import com.apogee.gnss.model.TestModel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saini
 */
@RestController
public class RestApiController {

    @Autowired
    TestModel testmodel;

    @Autowired
    FormModel formModel;
    @Autowired
    FormMappingModel formMappingModel;

    @GetMapping("/getReq")
    public String getReq() {
        return "This is GET Response!Modified";
    }

    @PostMapping("/postReq")
    public String postReq() {
        return "This is POST Response! Modified";
    }

//    @PostMapping("/postReqData")
    @PostMapping(value = "/postReqData", consumes = "application/json", produces = "application/json")
    public Map<String, Object> postData(@RequestBody Map<String, Object> obj) {
        String result = "";

        Map<String, Object> jobj = new HashMap<>();
        try {
            result = obj.get("msg").toString();
            jobj.put("1", "hhhhh");
            jobj.put("2", "hi");
        } catch (Exception e) {
            System.out.println("com.apogee.controller.RestApiController.postData(): " + e);
        }
        return jobj;
    }

//    @PostMapping("/postJSONReq")
//    public ResponseEntity postJSONReq(@RequestBody TestOne req) {
//        JSONObject jobj = new JSONObject();
//        Map<String, String> data = new HashMap<>();
//        try {
//            jobj.put("1", "1");
//            System.out.println("data: " + req.getMsg());            
//            data.put("key1", "value1");
//            data.put("key2", "value2");
//        } catch (Exception e) {
//            System.out.println("com.apogee.controller.RestApiController.postJSONReq(): " + e);
//        }
//
//        return new ResponseEntity<>(data, HttpStatus.OK);
//    }
    @GetMapping("/getNtripSurveyDetails")
    public String getNtripSurveyDetails() {

        try {
            testmodel.setDbConnection(DBConnection.getConnection1());
        } catch (Exception e) {
            System.out.println(e);
        }

//        testmodel.setDbConnection();
        String details = testmodel.getNtripDetails();
        return details;
    }

    @GetMapping("/getPersonDetails")
    public String getPersonDetails() {
        try {
            testmodel.setDbConnection(DBConnection.getConnection1());
        } catch (Exception e) {
            System.out.println(e);
        }
//        testmodel.setDbConnection();
        String personsDetails = testmodel.getPersonDetails();
        return personsDetails;
    }

    @PostMapping(value = "/getDynamicSqliteQuery", consumes = "application/json", produces = "application/json")
    public Map<String, Object> getDynamicSqliteQuery(@RequestBody Map<String, Object> obj) throws SQLException {
        JSONObject jobj = new JSONObject();
        try {
            formModel.setDbConnection(DBConnection.getConnection1());
            String status = formModel.conformUserId(obj.get("user_id").toString());
            JSONObject userStatus = new JSONObject(status);
            if (!userStatus.getString("message").equalsIgnoreCase("success")) {
                jobj.put("msg", "user is not athorized person");
            } else {
                String form_name = obj.get("form_name").toString();
                jobj = formModel.getDynamicSqliteQuery(form_name);
                if (!jobj.get("query_table").toString().equals("")) {
                    jobj.put("message", 1);
                    jobj.put("status", "success");
                } else {
                    jobj.put("message", 0);
                    jobj.put("status", "failure");
                }
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.RestApiController.getDynamicSqliteQuery()" + e);
        }
        return jobj.toMap();
    }

    @PostMapping(value = "/getFormListData", consumes = "application/json", produces = "application/json")
    public String getFormListData(@RequestBody String str) {
        JSONObject mainJsonObject = new JSONObject();
        try {
            formModel.setDbConnection(DBConnection.getConnection1());
            JSONObject jobj = new JSONObject(str);
            String status = formModel.conformUserId(jobj.getString("user_id"));
            JSONObject userStatus = new JSONObject(status);
            if (!userStatus.getString("message").equalsIgnoreCase("success")) {
                mainJsonObject.put("msg", "user is not athorized person");
            } else {
                JSONArray array = formModel.allFormInJSON();
                mainJsonObject.put("form", array);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.RestApiController.getFormListData()" + e);
        }

        return mainJsonObject.toString();
    }

    @PostMapping(value = "/getDynamicData", consumes = "application/json", produces = "application/json")
    public String getDataSave(@RequestBody String str) throws SQLException, Exception {
        formModel.setDbConnection(DBConnection.getConnection1());
        JSONObject jobj = new JSONObject(str);
        String form_name = jobj.getString("form_name");
        String user_id = jobj.getString("user_id");
        Map<String, String> mp = new HashedMap();
        String query_table_id = "";
        int rowsAffected = 0;
        String msg = "";
        String form_id = formModel.getForm(form_name);
        String status = formModel.conformUserId(jobj.getString("user_id"));
        JSONObject userStatus = new JSONObject(status);
        if (!userStatus.getString("message").equalsIgnoreCase("success")) {
            msg = "user is not athorized person";
        } else {
            if (form_id.isEmpty()) {
                msg = "form is not available.";
            } else {
                JSONArray arr = jobj.getJSONArray("form_data");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonObject1 = arr.getJSONObject(i);
                    Iterator<String> datakeys = jsonObject1.keys();
                    while (datakeys.hasNext()) {
                        String datakey1 = datakeys.next();
                        String datavalue1 = jsonObject1.get(datakey1).toString();
                        mp.put(datakey1, datavalue1);
                    }
                    query_table_id = formModel.getQueryTableId(form_id);
                    mp.put("query_table_id", query_table_id);
                    mp.put("user_id", user_id);
                    rowsAffected = formModel.saveDynamicData(mp, form_name);
                    if (rowsAffected != 0) {
                        msg = "Data saved on server successfully";
                    } else {
                        msg = "Data not saved on server,there is somthing issue";
                    }
                }
            }
        }
        return msg;
    }

    @GetMapping("/getConfigurationDetails")
    public Map<String, Object> getConfigurationDetails() {
        JSONObject obj = new JSONObject();
        try {
            testmodel.setDbConnection(DBConnection.getConnection1());
            JSONArray json = null;

            json = testmodel.getConstellation();
            obj.put("constellation", json);

            json = testmodel.getStaticParams();
            obj.put("static_params", json);

            json = testmodel.getSatelliteConfigConstellationMapping();
            obj.put("satellite_configuration_constellation_mapping", json);

            json = testmodel.getManualbaseParams();
            obj.put("manual_base_params", json);

            json = testmodel.getPPKParams();
            obj.put("ppk_params", json);

            json = testmodel.getVia4GParams();
            obj.put("via4gparams", json);

            json = testmodel.getWifiParams();
            obj.put("wifiparams", json);

            json = testmodel.getPDAParams();
            obj.put("pdaparams", json);

            json = testmodel.getRadioInternalParams();
            obj.put("radiointernalparams", json);

            json = testmodel.getRadioExternalParams();
            obj.put("radioexternalparams", json);

            json = testmodel.getTypeOfCommunication();
            obj.put("type_of_communication", json);

            json = testmodel.getDeviceConfiguration();
            obj.put("device_configuration", json);

            json = testmodel.getDeviceConfigHierarchy();
            obj.put("device_confighierarchy", json);

            json = testmodel.getDynamicConfigMapping();
            obj.put("dynamic_configuration_mapping", json);

            json = testmodel.getSatelliteConfiguration();
            obj.put("satellite_configuration", json);

            json = testmodel.getMiscellaneousConfiguration();
            obj.put("miscellaneous_configuration", json);

            json = testmodel.getSurveyConfiguration();
            obj.put("survey_configuration", json);

            json = testmodel.getProjectConfigurationMapping();
            obj.put("project_configuration_mapping", json);

            json = testmodel.getProjectDetails();
            obj.put("project_details", json);

            json = testmodel.getProjectConfigMainMapping();
            obj.put("project_configuration_main_mapping", json);

            json = testmodel.getSiteCalibration();
            obj.put("site_calibration", json);

            json = testmodel.getCommunicationTypeMapping();
            obj.put("communication_type_mapping", json);

            testmodel.closeConnection();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.RestApiController.getConfigurationDetails()" + e);
        }
        return obj.toMap();
    }
}
