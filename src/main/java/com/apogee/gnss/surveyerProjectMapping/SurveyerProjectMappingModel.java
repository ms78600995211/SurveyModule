/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.surveyerProjectMapping;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class SurveyerProjectMappingModel {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveSurveyorData(String project_client_dev_map_id, String key_person_id, String survey_no, String survey_date, String survey_start_time, String survey_end_time, String name) {

        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            String query = "insert into surveyer(project_client_dev_map_id,key_person_id,survey_no,survey_date,survey_start_time,survey_end_time,created_by) values(?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, project_client_dev_map_id);
            pstmt.setString(2, key_person_id);
            pstmt.setString(3, survey_no);
            pstmt.setString(4, survey_date);
            pstmt.setString(5, survey_start_time);
            pstmt.setString(6, survey_end_time);
            pstmt.setString(7, name);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingModel.saveSurveyorData()" + e);
        }
    }

    public List<SurveyerProjectMappingBean> getAllProjectDeviceMapping() {

        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyerProjectMappingBean> getAllProjectDeviceMapping = new ArrayList<>();
        String query = "select pcd.project_client_dev_map_id,pd.project_name,nb.base_name,nr.rover_name,cbr.client_id,pcd.remark  "
                + " from project_client_dev_map pcd  "
                + "inner join client_base_rover_registeration_map cbr on pcd.project_client_dev_map_id=cbr.id  "
                + "inner join project_details pd on pcd.project_details_id=pd.project_details_id  "
                + "inner join ntrip_base nb on cbr.base_id=nb.ntrip_base_id   "
                + "inner join ntrip_rover nr on cbr.rover_id=nr.ntrip_rover_id   "
                + "where cbr.active='Y' order by project_client_dev_map_id desc  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyerProjectMappingBean bean = new SurveyerProjectMappingBean();
                String client_id = stmt.getString("client_id");
                String url = resourceBundle.getString("GET_KEYPERSON_DETAILS_API_URL");
                HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
                httpClient.setRequestMethod("POST");
                httpClient.setRequestProperty("Content-type", "application/json");
                String urlParameters = "{\n"
                        + "\"key_person_id\":" + client_id + "\n"
                        + "}";
                httpClient.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                StringBuilder response = new StringBuilder();
                BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                JSONObject jobj1 = new JSONObject(response.toString());
                bean.setProject_client_dev_map_id(stmt.getString("project_client_dev_map_id"));
                StringBuilder sb1 = new StringBuilder();
                sb1.append(stmt.getString("project_name"));
                sb1.append("-" + jobj1.get("key_person_name").toString());
                sb1.append("-" + stmt.getString("base_name"));
                sb1.append("-" + stmt.getString("rover_name"));
                bean.setProject_client_device(sb1.toString());
                bean.setRemark(stmt.getString("remark"));
                getAllProjectDeviceMapping.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingModel.getAllProjectDeviceMapping()" + e);
        }
        return getAllProjectDeviceMapping;
    }

    public List<SurveyerProjectMappingBean> getAlldata(String project_name) {

        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        SurveyerProjectMappingModel model = new SurveyerProjectMappingModel();
        List<SurveyerProjectMappingBean> getAlldata = new ArrayList<>();
        String query = "select  s.key_person_id ,pd.project_name,cbr.client_id,nb.base_name,nr.rover_name,"
                + "s.survey_no,s.survey_date,s.survey_start_time,s.survey_end_time "
                + "from surveyer s  "
                + "inner join project_client_dev_map pcdm on pcdm.project_client_dev_map_id=s.project_client_dev_map_id "
                + "inner join project_details pd on pcdm.project_details_id=pd.project_details_id "
                + "inner join client_base_rover_registeration_map cbr on cbr.id=pcdm.project_client_dev_map_id "
                + "inner join ntrip_base nb on nb.ntrip_base_id=cbr.base_id  "
                + "inner join ntrip_rover nr on nr.ntrip_rover_id=cbr.rover_id where pd.active='Y' and cbr.active='Y'  and nb.active='Y' and nr.active='Y' ";
        if (project_name != null && !project_name.isEmpty()) {
            query += "  and pd.project_name=  ?";
        }
        query += " order by  s.surveyer_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            if (project_name != null && !project_name.isEmpty()) {
                pstmt.setString(1, project_name);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyerProjectMappingBean bean = new SurveyerProjectMappingBean();
                bean.setProject_name(stmt.getString("project_name"));
                String key_person_id = stmt.getString("key_person_id");
                String client_id = stmt.getString("client_id");
                bean.setKey_person_name(model.getKeyPersonDetail(key_person_id));
                bean.setClient_name(model.getKeyPersonDetail(client_id));
                bean.setSurvey_no(stmt.getString("survey_no"));
                String survey_date = stmt.getString("survey_date");
                String[] survey_date_parts = survey_date.split(" ");
                bean.setSurvey_date(survey_date_parts[0]);
                String survey_start_time = stmt.getString("survey_start_time");
                String[] survey_start_time_parts = survey_start_time.split(" ");
                bean.setSurvey_start_time(survey_start_time_parts[1]);
                String survey_end_time = stmt.getString("survey_end_time");
                String[] survey_end_time_parts = survey_end_time.split(" ");
                bean.setSurvey_end_time(survey_end_time_parts[1]);
                bean.setBase_name(stmt.getString("base_name"));
                bean.setRover_name(stmt.getString("rover_name"));
                getAlldata.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingModel.getAlldata()" + e);
        }
        return getAlldata;
    }

    public List allProjects() {

        ResultSet stmt = null;
        List allProjects = new ArrayList<>();
        String query = "select project_name from project_details  where active ='y' ";
        try {
            stmt = connection.prepareStatement(query).executeQuery();
            while (stmt.next()) {
                allProjects.add(stmt.getString("project_name"));
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingModel.allProjects()" + e);
        }
        return allProjects;
    }

    public String getKeyPersonDetail(String key_person_id) {

        String key_person_name = "";
        String url = resourceBundle.getString("GET_KEYPERSON_DETAILS_API_URL");
        try {
            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("Content-type", "application/json");
            String urlParameters = "{\n"
                    + "\"key_person_id\": " + key_person_id + "\n"
                    + "}";
            httpClient.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            StringBuilder response = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
            String line;
            response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            JSONObject jobj1 = new JSONObject(response.toString());
            key_person_name = jobj1.get("key_person_name").toString();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingModel.getKeyPersonDetail()" + e);
        }
        return key_person_name;
    }

    public List<SurveyerProjectMappingBean> getSurveyors() {

        List<SurveyerProjectMappingBean> getSurveyors = new ArrayList<>();
        String url = resourceBundle.getString("GET_KEYPERSON_API_URL");
        try {
            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("Content-type", "application/json");
            String urlParameters = "{\n"
                    + "    \"org_office_name\": \"\",\n"
                    + "    \"city_name\": \"\",\n"
                    + "    \"designation\": \"surveyor\",\n"
                    + "    \"key_person_name\": \"\",\n"
                    + "    \"mobile_no1\": \"\",\n"
                    + "    \"email_id1\": \"\",\n"
                    + "    \"emp_code\": \"\",\n"
                    + "    \"father_name\": \"\",\n"
                    + "    \"date_of_birth\": \"\",\n"
                    + "    \"bloodgroup\": \"\",\n"
                    + "    \"gender\": \"\",\n"
                    + "    \"id_no\": \"\",\n"
                    + "    \"generation\": \"\"\n"
                    + "}";
            httpClient.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            JSONObject jobj1 = new JSONObject(response.toString());
            JSONArray arr = jobj1.getJSONArray("key_person");
            for (int i = 0; i < arr.length(); i++) {
                SurveyerProjectMappingBean bean = new SurveyerProjectMappingBean();
                JSONObject jobj = arr.getJSONObject(i);
                bean.setKey_person_name(jobj.get("key_person_name").toString());
                bean.setKey_person_id(jobj.get("key_person_id").toString());
                getSurveyors.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingModel.getSurveyors()" + e);
        }

        return getSurveyors;
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

    public void setCOLOR_ERROR(String COLOR_ERROR) {
        this.COLOR_ERROR = COLOR_ERROR;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
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

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingModel.closeConnection()" + e);
        }
    }

}
