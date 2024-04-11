/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.staticParams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class StaticParamsModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveStaticParams(String file_name, String total_time, String sampling_rate, String name, String remark, String defaultConfig) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;

        try {
            if (!file_name.trim().isEmpty() && !total_time.trim().isEmpty() && !sampling_rate.trim().isEmpty()) {
                String query = "insert into static_params(file_name,total_time,sampling_rate,remark,active,revision_no,created_by,defaultConfig) values(?,?,?,?,?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, file_name);
                pstmt.setString(2, total_time);
                pstmt.setString(3, sampling_rate);
                pstmt.setString(4, remark);
                pstmt.setString(5, "Y");
                pstmt.setInt(6, 0);
                pstmt.setString(7, name);
                pstmt.setString(8, defaultConfig);
                rowsAffected = pstmt.executeUpdate();
            }
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;

            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.StaticParamsModel.saveStaticParams()" + e);
        }
    }

    public List<StaticParamsBean> getAllStaticParsms(String sampling_rate) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<StaticParamsBean> staticParamsList = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append(" select *  FROM static_params WHERE active = 'Y' ");
        String qry = "and sampling_rate='" + sampling_rate + "'";

        if (sampling_rate != null && !sampling_rate.isEmpty()) {
            builder.append(qry);
        }
        builder.append("order by static_params_id desc ");

        try {
            pstmt = connection.prepareStatement(builder.toString());
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                StaticParamsBean bean = new StaticParamsBean();
                bean.setFile_name(stmt.getString("file_name"));
                bean.setTotal_time(stmt.getString("total_time"));
                bean.setSampling_rate(stmt.getString("sampling_rate"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setRemarks(stmt.getString("remark"));
                staticParamsList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.StaticParamsModel.getAllStaticParsms()" + e);
        }
        return staticParamsList;
    }

    public List allSampling_rate() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List allSampling_rate = new ArrayList<>();
        String query = " select distinct sampling_rate FROM static_params "
                + " WHERE active = 'Y'  ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                allSampling_rate.add(stmt.getString("sampling_rate"));
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.StaticParamsModel.allSampling_rate()" + e);
        }
        return allSampling_rate;
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
            System.out.println("com.apogee.gnss.model.StaticParamsModel.closeConnection()" + e);
        }
    }

}
