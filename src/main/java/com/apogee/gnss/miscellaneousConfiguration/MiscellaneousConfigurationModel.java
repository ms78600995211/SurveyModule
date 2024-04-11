/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.miscellaneousConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class MiscellaneousConfigurationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveMiscellaneous_configuration(LinkedHashMap<Integer, String> parameters) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        ResultSet rs = null;
        int count = 0;
        try {
            String query1 = " select count(*) as count from miscellaneous_configuration where miscConfig_name =?  and active = 'Y' ;";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, parameters.get(1));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count > 0) {
                message = parameters.get(1) + "  Name Already Exist.";
                color = COLOR_ERROR;
            } else if (checkInt(parameters.get(3)).isEmpty()) {
                message = " 'min_no_of_satellite' should be in Integer.";
                color = COLOR_ERROR;
            } else {
                if (!parameters.get(1).trim().isEmpty()) {

                    String query = "INSERT INTO miscellaneous_configuration "
                            + "(miscConfig_name, position_type, min_no_of_satellite, age_of_correction, pdop_tolerance, horizontal_percision, "
                            + "vertical_percision, check_validity_of_point_taken, observation_time_per_point, "
                            + "show_point_name, show_code_name, stake_horizontal_percision, stake_vertical_percision, stake_target_distance, inner_circle_radius, outer_circle_radius, "
                            + "auto_connect_last_connected_reciever, automatically_send_last_working_profile, enable_compass, enable_e_bubble, enable_lrf, "
                            + "defaultConfig, active, created_by, remark, revision_no) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, parameters.get(1));  // miscConfig_name
                    pstmt.setString(2, parameters.get(2));  // position_type
                    pstmt.setInt(3, Integer.parseInt(parameters.get(3)));  // min_no_of_satellite
                    pstmt.setString(4, parameters.get(4));  // age_of_correction
                    pstmt.setString(5, parameters.get(5));  // pdop_tolerance
                    pstmt.setString(6, parameters.get(6));  // horizontal_percision
                    pstmt.setString(7, parameters.get(7));  // vertical_percision
                    pstmt.setInt(8, Integer.parseInt(parameters.get(8)));  // check_validity_of_point_taken
                    pstmt.setString(9, parameters.get(9));  // observation_time_per_point
                    pstmt.setInt(10, Integer.parseInt(parameters.get(10)));  // show_point_name
                    pstmt.setInt(11, Integer.parseInt(parameters.get(11)));  // show_code_name
                    pstmt.setString(12, parameters.get(12));  // stake_horizontal_percision
                    pstmt.setString(13, parameters.get(13));  // stake_vertical_percision
                    pstmt.setString(14, parameters.get(14));  // stake_target_distance
                    pstmt.setString(15, parameters.get(15));  // inner_circle_radius
                    pstmt.setString(16, parameters.get(16));  // outer_circle_radius
                    pstmt.setInt(17, Integer.parseInt(parameters.get(17)));  // auto_connect_last_connected_reciever
                    pstmt.setInt(18, Integer.parseInt(parameters.get(18)));  // automatically_send_last_working_profile
                    pstmt.setInt(19, Integer.parseInt(parameters.get(19)));  // enable_compass
                    pstmt.setInt(20, Integer.parseInt(parameters.get(20)));  // enable_e_bubble
                    pstmt.setInt(21, Integer.parseInt(parameters.get(21)));  // enable_lrf
                    pstmt.setString(22, parameters.get(22));  // defaultConfig
                    pstmt.setString(23, "Y");  // active
                    pstmt.setString(24, parameters.get(23));  // created_by
                    pstmt.setString(25, parameters.get(24));  // remark
                    pstmt.setInt(26, 0);  // revision_no
                    rowsAffected = pstmt.executeUpdate();
                }
                if (rowsAffected > 0) {
                    message = "Data Added Successfully.";
                    color = COLOR_OK;
                } else {
                    message = "Some Error Try Again.";
                    color = COLOR_ERROR;
                }

            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.miscellaneousConfiguration.MiscellaneousConfigurationModel.saveMiscellaneous_configuration()" + e);
        }
    }

    public String checkInt(String input) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return input;
        } else {
            return "";
        }
    }

    public List getMiscellaneousConfigList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<MiscellaneousConfigurationBean> getMiscellaneousConfigList = new ArrayList<>();
        String query = "select * from miscellaneous_configuration where active='Y' ";
//        
        query += "  ORDER BY miscConfig_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                MiscellaneousConfigurationBean bean = new MiscellaneousConfigurationBean();
                bean.setMiscConfig_name(stmt.getString("miscConfig_name"));
                bean.setPosition_type(stmt.getString("position_type"));
                bean.setMin_no_of_satellite(stmt.getString("min_no_of_satellite"));
                bean.setAge_of_correction(stmt.getString("age_of_correction"));
                bean.setPdop_tolerance(stmt.getString("pdop_tolerance"));
                bean.setHorizontal_percision(stmt.getString("horizontal_percision"));
                bean.setVertical_percision(stmt.getString("vertical_percision"));
                String check_validity_of_point_taken = stmt.getString("check_validity_of_point_taken");

                if (check_validity_of_point_taken.equals("1")) {
                    check_validity_of_point_taken = "True";
                } else {
                    check_validity_of_point_taken = "False";

                }
                bean.setCheck_validity_of_point_taken(check_validity_of_point_taken);
                bean.setObservation_time_per_point(stmt.getString("observation_time_per_point"));
                bean.setShow_point_name(stmt.getString("show_point_name"));
                String show_code_name = stmt.getString("show_code_name");
                if (show_code_name.equals("1")) {
                    show_code_name = "True";
                } else {
                    show_code_name = "False";

                }
                bean.setShow_code_name(show_code_name);
                bean.setStake_horizontal_percision(stmt.getString("stake_horizontal_percision"));
                bean.setStake_vertical_percision(stmt.getString("stake_vertical_percision"));
                bean.setStake_target_distance(stmt.getString("stake_target_distance"));
                bean.setInner_circle_radius(stmt.getString("inner_circle_radius"));
                bean.setOuter_circle_radius(stmt.getString("outer_circle_radius"));
                String auto_connect_last_connected_reciever = stmt.getString("auto_connect_last_connected_reciever");
                if (auto_connect_last_connected_reciever.equals("1")) {
                    auto_connect_last_connected_reciever = "True";
                } else {
                    auto_connect_last_connected_reciever = "False";

                }
                bean.setAuto_connect_last_connected_reciever(auto_connect_last_connected_reciever);
                String automatically_send_last_working_profile = stmt.getString("automatically_send_last_working_profile");
                if (automatically_send_last_working_profile.equals("1")) {
                    automatically_send_last_working_profile = "True";
                } else {
                    automatically_send_last_working_profile = "False";

                }
                bean.setAutomatically_send_last_working_profile(automatically_send_last_working_profile);
                String enable_compass = stmt.getString("enable_compass");
                if (enable_compass.equals("1")) {
                    enable_compass = "True";
                } else {
                    enable_compass = "False";

                }
                bean.setEnable_compass(enable_compass);
                String enable_e_bubble = stmt.getString("enable_e_bubble");
                if (enable_e_bubble.equals("1")) {
                    enable_e_bubble = "True";
                } else {
                    enable_e_bubble = "False";

                }
                bean.setEnable_e_bubble(enable_e_bubble);
                String enable_lrf = stmt.getString("enable_lrf");
                if (enable_lrf.equals("1")) {
                    enable_lrf = "True";
                } else {
                    enable_lrf = "False";
                }
                bean.setEnable_lrf(enable_lrf);
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setMiscConfig_time(stmt.getString("miscConfig_time"));
                bean.setRemark(stmt.getString("remark"));
                getMiscellaneousConfigList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.miscellaneousConfiguration.MiscellaneousConfigurationModel.getMiscellaneousConfigList()" + e);
        }
        return getMiscellaneousConfigList;
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
