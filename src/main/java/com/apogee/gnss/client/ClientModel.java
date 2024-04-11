package com.apogee.gnss.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
public class ClientModel {

    private Connection connection;
    private String COLOR_OK = "#a2a220";
    private String COLOR_ERROR = "red";

    public String getClients() throws Exception {
        String baseiname = "";
        String url = "http://120.138.10.146:8080/OrganisationModule/webAPI/service/getClients";
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");
//        String urlParameters = "{\"org_office_name\":\"" + "" + "\",\"city_name\":\"" + "" + "\",\"designation\":\"" + "" + "\",\"key_person_name\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\",\"report_type\":\"" + "" + "\"}";

        String urlParameters = "{\"org_office_name\": \"\",\"city_name\": \"\",\"designation\": \"\",\n"
                + "\"key_person_name\": \"\",\"mobile_no1\": \"\",\"email_id1\":\n"
                + "\"\",\"emp_code\": \"\",\"father_name\": \"\",\"date_of_birth\": \"\",\n"
                + "\"bloodgroup\": \"\",\"gender\": \"\",\"id_no\": \"\",generation:\"\"}";

        httpClient.setDoOutput(
                true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }
        int responseCode = httpClient.getResponseCode();
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

    public String getOrgName(String key_person_id, String key_person_name) throws Exception {
        String baseiname = "";
        String orgName = "";
        String url = "http://120.138.10.146:8080/OrganisationModule/webAPI/service/getOrganisationPersonData";
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");
        String urlParameters = "{\n"
                + "    \"key_person_name\": \"" + key_person_name + "\",\n"
                + "    \"mobile_no1\": \"\",\n"
                + "    \"email_id1\": \"\"\n"
                + "}";
        httpClient.setDoOutput(
                true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }
        int responseCode = httpClient.getResponseCode();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            baseiname = response.toString();
            JSONObject jobj = new JSONObject(baseiname);
            JSONArray arr = jobj.getJSONArray("data");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jobj1 = arr.getJSONObject(i);
                String key_person_id1 = jobj1.get("key_person_id").toString();
                if (key_person_id.equalsIgnoreCase(key_person_id1)) {
                    orgName = jobj1.get("organisation_name").toString();
                }
            }
        }
        return orgName;

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

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.mapping.ClientModel.closeConnection()" + e);
        }
    }

}
