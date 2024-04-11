/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.datumType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class DatumTypeModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveDatumType(String datumType, String remark, String log_in_person) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!datumType.trim().isEmpty()) {
                String query = "insert into datumtype(datumType_name, remark, created_by) values(?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, datumType);
                pstmt.setString(2, remark);
                pstmt.setString(3, log_in_person);
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
            System.out.println("com.apogee.gnss.model.DatumTypeModel.saveDatumType()" + e);
        }
    }

    public List<DatumTypeBean> getDatumType(String datumtype_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DatumTypeBean> getDatumType = new ArrayList<>();
        String query = "SELECT datumtype_id, datumType_name, remark FROM datumtype WHERE active = 'Y'";
        if (datumtype_id != null && !datumtype_id.isEmpty()) {
            query += " AND datumtype_id = '" + datumtype_id + "'";
        }
        query += " ORDER BY datumtype_id DESC";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DatumTypeBean datumType = new DatumTypeBean();
                datumType.setDatumtype_id(stmt.getInt("datumtype_id"));
                datumType.setDatumType_name(stmt.getString("datumType_name"));
                datumType.setRemark(stmt.getString("remark"));
                getDatumType.add(datumType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumTypeModel.getDatumType()" + e);
        }
        return getDatumType;
    }

    public List<DatumTypeBean> allDatumType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DatumTypeBean> allDatumType = new ArrayList<>();
        String query = " select datumtype_id, datumType_name "
                + " FROM datumtype "
                + " WHERE active = 'Y' order by datumtype_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DatumTypeBean datumType = new DatumTypeBean();
                datumType.setDatumtype_id(stmt.getInt("datumtype_id"));
                datumType.setDatumType_name(stmt.getString("datumType_name"));
                allDatumType.add(datumType);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumTypeModel.allDatumType()" + e);
        }
        return allDatumType;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumTypeModel.closeConnection()" + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCOLOR_ERROR() {
        return COLOR_ERROR;
    }

    public void setCOLOR_ERROR(String COLOR_ERROR) {
        this.COLOR_ERROR = COLOR_ERROR;
    }

    public String getCOLOR_OK() {
        return COLOR_OK;
    }

    public void setCOLOR_OK(String COLOR_OK) {
        this.COLOR_OK = COLOR_OK;
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
}
