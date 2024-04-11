/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.Login;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 *
 * @author lENOVO
 */
public class LoginModel {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

    private static Connection connection;
    private final String COLOR_OK = "#a2a220";
    private final String COLOR_ERROR = "red";

    public void setDbConnection(Connection con) {
        try {
            connection = con;
        } catch (Exception e) {
            System.out.println("com.jpss.registartioncummodel.LoginModel.setConnection(): " + e);
        }
    }

    public String conformLoginDataFromLoginModule(String a, String b, String c) throws Exception {

        String logindata = "";

//        String url = "http://120.138.10.146:8080/login_module/api/getLoginPersonData";
        String getLoginPersonData = resourceBundle.getString("LOGIN_MODULE_GETLOGINPERSONDETAILS_API_URL");
        String url = getLoginPersonData;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");

        String urlParameters = ("{\"username\": \"" + a + "\",\"password\": \"" + b + "\",\"project_name\": \"" + c + "\"}");

        httpClient.setDoOutput(
                true);
        try ( DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = httpClient.getResponseCode();
        try ( BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);

            }
            return logindata = response.toString();
        }

    }

    public String getKeypersonDataFromOrganisation(String a) throws Exception {

        String keypersondata = "";
//        String url = "http://120.138.10.146:8080/OrganisationModule/webAPI/service/getDesignationByPersonId";
        String getDesignationByPersonId = resourceBundle.getString("ORG_MODULE_GETDESIGNATIONBYPERSONID_API_URL");
        String url = getDesignationByPersonId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");

        String urlParameters = ("{\"key_person_id\": \"" + a + "\"}");
        httpClient.setDoOutput(
                true);
        try ( DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }
        int responseCode = httpClient.getResponseCode();
        try ( BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);

            }
            return keypersondata = response.toString();
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

}
