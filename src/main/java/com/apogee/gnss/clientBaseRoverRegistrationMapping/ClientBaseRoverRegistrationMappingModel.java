/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.clientBaseRoverRegistrationMapping;

import com.apogee.gnss.clientBaseRoverRegistrationMapping.ClientBaseRoverRegistrationMappingBean;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class ClientBaseRoverRegistrationMappingModel {

    private Connection connection;
    private String COLOR_OK = "#a2a220";
    private String COLOR_ERROR = "red";

    public String saveClientBaseRoverMappingData(String base_id, String rover_id, String client_id, String name, String remark) {

        int rowsAffected = 0;
        String id = "";

        try {
            String query = "insert into client_base_rover_registeration_map(base_id,rover_id,client_id,created_by,active,remark,revision_no) values(?,?,?,?,?,?,?);";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, base_id);
            pstmt.setString(2, rover_id);
            pstmt.setString(3, client_id);
            pstmt.setString(4, name);
            pstmt.setString(5, "Y");
            pstmt.setString(6, remark);
            pstmt.setInt(7, 0);
            rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    id = Integer.toString(rs.getInt(1));
                }

            }
        } catch (Exception e) {
            System.out.println("Error while insert angleunittable..." + e);
        }
        return id;
    }

    public List<ClientBaseRoverRegistrationMappingBean> getClientBaseRoverMappingData() {
        List<ClientBaseRoverRegistrationMappingBean> getAllBaseRoverMappingData = new ArrayList<>();
        String query = "SELECT nb.base_name, nr.rover_name,cbr.client_id ,cbr.remark\n"
                + "FROM client_base_rover_registeration_map cbr \n"
                + "INNER JOIN ntrip_base nb ON cbr.base_id = nb.ntrip_base_id\n"
                + "INNER JOIN ntrip_rover nr ON cbr.rover_id = nr.ntrip_rover_id\n"
                + "WHERE cbr.active = 'Y'\n"
                + "ORDER BY cbr.id DESC;";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ClientBaseRoverRegistrationMappingBean bean = new ClientBaseRoverRegistrationMappingBean();

                String client_id = stmt.getString("client_id");

                String baseiname = "";
                String url = "http://120.138.10.146:8080/OrganisationModule/webAPI/service/getKeyPersonDetail";
                HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
                httpClient.setRequestMethod("POST");
                httpClient.setRequestProperty("Content-type", "application/json");

                String urlParameters = "{\n"
                        + "\"key_person_id\":" + client_id + "\n"
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
                    //print result
                    // System.out.println("data" : +baseiname);

                }

                JSONObject jobj1 = new JSONObject(baseiname);
                String key_person_name = jobj1.get("key_person_name").toString();
                bean.setClient_name(key_person_name);

                bean.setBase_name(stmt.getString("base_name"));
                bean.setRover_name(stmt.getString("rover_name"));
                bean.setRemark(stmt.getString("remark"));

                getAllBaseRoverMappingData.add(bean);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return getAllBaseRoverMappingData;
    }

    public List<ClientBaseRoverRegistrationMappingBean> getClientList() {
        List<ClientBaseRoverRegistrationMappingBean> all_clients = new ArrayList<>();

        String query = " select client_registeration_id, client_name "
                + " FROM client_registeration "
                + " WHERE active = 'Y' order by client_registeration_id asc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ClientBaseRoverRegistrationMappingBean client = new ClientBaseRoverRegistrationMappingBean();
                client.setClient_registeration_id(stmt.getString("client_registeration_id"));
                client.setClient_name(stmt.getString("client_name"));

                all_clients.add(client);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return all_clients;
    }

    public List<ClientBaseRoverRegistrationMappingBean> getAllBase1(List base_ids) {
        List<ClientBaseRoverRegistrationMappingBean> all_base = new ArrayList<>();

        String query = " select ntrip_base_id, base_name "
                + " FROM ntrip_base "
                + " WHERE active = 'Y' order by ntrip_base_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ClientBaseRoverRegistrationMappingBean base = new ClientBaseRoverRegistrationMappingBean();

                int base_id = stmt.getInt("ntrip_base_id");

                if (!base_ids.contains(base_id)) {

                    base.setNtrip_base_id(stmt.getString("ntrip_base_id"));
                    base.setBase_name(stmt.getString("base_name"));
                    all_base.add(base);
                }

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return all_base;
    }

    public List<ClientBaseRoverRegistrationMappingBean> getAllRover1(List rover_ids) {
        List<ClientBaseRoverRegistrationMappingBean> all_rover = new ArrayList<>();

        String query = " select ntrip_rover_id, rover_name "
                + " FROM ntrip_rover "
                + " WHERE active = 'Y' order by ntrip_rover_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ClientBaseRoverRegistrationMappingBean rover = new ClientBaseRoverRegistrationMappingBean();

                int rover_id = stmt.getInt("ntrip_rover_id");

                if (!rover_ids.contains(rover_id)) {

                    rover.setNtrip_rover_id(stmt.getString("ntrip_rover_id"));
                    rover.setRover_name(stmt.getString("rover_name"));
                    all_rover.add(rover);
                }

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return all_rover;
    }

    public List getAllBase_id(String client_id) {
        List all_baseIds = new ArrayList<>();

        String query = " select base_id"
                + " FROM client_base_rover_registeration_map "
                + " WHERE active = 'Y' and   client_id='" + client_id + "'";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                int base_id = stmt.getInt("base_id");
                all_baseIds.add(base_id);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return all_baseIds;
    }

    public List getAllRover_id(String client_id) {
        List all_roverIds = new ArrayList<>();

        String query = " select rover_id"
                + " FROM client_base_rover_registeration_map "
                + " WHERE active = 'Y' and   client_id='" + client_id + "'";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                int rover_id = stmt.getInt("rover_id");
                all_roverIds.add(rover_id);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return all_roverIds;
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
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

}
