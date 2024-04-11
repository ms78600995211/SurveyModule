/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectClientDeviceMapping;

import com.apogee.gnss.clientBaseRoverRegistrationMapping.ClientBaseRoverRegistrationMappingBean;
import com.apogee.gnss.projectCodeListMapping.ProjectCodeListMappingBean;
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
import java.util.ResourceBundle;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class ProjectClientDeviceMappingModel {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveProjectClientDeviceMappingData(String project_details_id, String base_id, String rover_id, String client_id, String name, String remark) {
        int rowsAffected = 0;
        int rowsAffected1 = 0;
        PreparedStatement pstmt = null;
        String id = "";
        try {
            connection.setAutoCommit(false);
            String query = "insert into client_base_rover_registeration_map(base_id,rover_id,client_id,created_by,active,remark,revision_no) values(?,?,?,?,?,?,?);";
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
                    String query1 = "insert into project_client_dev_map(project_details_id,client_base_rover_registeration_map_id,remark) values(?,?,?);";
                    pstmt = connection.prepareStatement(query1);
                    pstmt.setString(1, project_details_id);
                    pstmt.setString(2, id);
                    pstmt.setString(3, remark);
                    rowsAffected1 = pstmt.executeUpdate();
                }
            }
            if (rowsAffected > 0 && rowsAffected1 > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
                connection.commit();
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
                connection.rollback();
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.saveProjectClientDeviceMappingData()" + e);
        }
    }

    public List<ProjectCodeListMappingBean> getAllProjectDeviceMapping(String project_details_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectCodeListMappingBean> getAllProjectDeviceMapping = new ArrayList<>();
        String query = "select pd.project_name,nb.base_name,nr.rover_name,cbr.client_id,pcd.remark  "
                + " from project_client_dev_map pcd  "
                + "inner join client_base_rover_registeration_map cbr on pcd.project_client_dev_map_id=cbr.id  "
                + "inner join project_details pd on pcd.project_details_id=pd.project_details_id  "
                + "inner join ntrip_base nb on cbr.base_id=nb.ntrip_base_id   "
                + "inner join ntrip_rover nr on cbr.rover_id=nr.ntrip_rover_id where cbr.active='Y'";
        if (project_details_id != null && !project_details_id.isEmpty()) {
            query += "AND pcd.project_details_id ='" + project_details_id + "'";
        }
        query += "  ORDER BY project_client_dev_map_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectCodeListMappingBean bean = new ProjectCodeListMappingBean();
                bean.setProject_name(stmt.getString("project_name"));
                String client_id = stmt.getString("client_id");
                String baseiname = "";
                String url = resourceBundle.getString("GET_KEYPERSON_DETAILS_API_URL");
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
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(httpClient.getInputStream()))) {
                    String line = "";
                    StringBuilder response = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    baseiname = response.toString();
                }
                JSONObject jobj1 = new JSONObject(baseiname);
                String key_person_name = jobj1.get("key_person_name").toString();
                bean.setClient_name(key_person_name);
                bean.setBase_name(stmt.getString("base_name"));
                bean.setRover_name(stmt.getString("rover_name"));
                bean.setRemark(stmt.getString("remark"));
                getAllProjectDeviceMapping.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.getAllProjectDeviceMapping()" + e);
        }
        return getAllProjectDeviceMapping;
    }

    public List<ProjectClientDeviceMappingBean> getAllProjects() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectClientDeviceMappingBean> all_projects = new ArrayList<>();
        String query = " select project_details_id, project_name "
                + " FROM project_details "
                + " WHERE active = 'Y' order by project_details_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectClientDeviceMappingBean projectDetail = new ProjectClientDeviceMappingBean();
                projectDetail.setProject_details_id(stmt.getString("project_details_id"));
                projectDetail.setProject_name(stmt.getString("project_name"));
                all_projects.add(projectDetail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.getAllProjects()" + e);
        }
        return all_projects;
    }

    public String getClients() throws Exception {
        String url = resourceBundle.getString("GET_CLIENTS");
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");
        StringBuilder response = new StringBuilder();
        String urlParameters = "{\"org_office_name\": \"\",\"city_name\": \"\",\"designation\": \"\",\n"
                + "\"key_person_name\": \"\",\"mobile_no1\": \"\",\"email_id1\":\n"
                + "\"\",\"emp_code\": \"\",\"father_name\": \"\",\"date_of_birth\": \"\",\n"
                + "\"bloodgroup\": \"\",\"gender\": \"\",\"id_no\": \"\",generation:\"\"}";
        try {
            httpClient.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            int responseCode = httpClient.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.getClients()" + e);
        }
        return response.toString();
    }

    public List<ClientBaseRoverRegistrationMappingBean> getAllBase1(List base_ids) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ClientBaseRoverRegistrationMappingBean> all_base = new ArrayList<>();
        String query = " select ntrip_base_id, base_name "
                + " FROM ntrip_base "
                + " WHERE active = 'Y' order by ntrip_base_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
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
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.getAllBase1()" + e);
        }
        return all_base;
    }

    public List<ClientBaseRoverRegistrationMappingBean> getAllRover1(List rover_ids) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ClientBaseRoverRegistrationMappingBean> all_rover = new ArrayList<>();
        String query = " select ntrip_rover_id, rover_name "
                + " FROM ntrip_rover "
                + " WHERE active = 'Y' order by ntrip_rover_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
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
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.getAllRover1()" + e);
        }
        return all_rover;
    }

    public List getAllBase_id(String client_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List all_baseIds = new ArrayList<>();
        String query = " select base_id"
                + " FROM client_base_rover_registeration_map "
                + " WHERE active = 'Y' and   client_id='" + client_id + "'";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                int base_id = stmt.getInt("base_id");
                all_baseIds.add(base_id);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.getAllBase_id()" + e);
        }
        return all_baseIds;
    }

    public List getAllRover_id(String client_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List all_roverIds = new ArrayList<>();
        String query = " select rover_id"
                + " FROM client_base_rover_registeration_map "
                + " WHERE active = 'Y' and   client_id='" + client_id + "'";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                int rover_id = stmt.getInt("rover_id");
                all_roverIds.add(rover_id);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingModel.getAllRover_id()" + e);
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
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

}
