/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.columnType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ColumnTypeModel {
    

    private Connection connection;
    private String COLOR_OK = "#a2a220";
    private String COLOR_ERROR = "red";

    public String saveColumnType(String type_name,String name,String remark) {
        int rowsAffected = 0;
        String column_type_id = "";
    
        try {
            String query = "insert into column_type(type_name, remark,active,revision_no,created_by) values(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, type_name);
            pstmt.setString(2, remark);
            pstmt.setString(3, "Y");
            pstmt.setInt(4,0);
            pstmt.setString(5,name);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    column_type_id = Integer.toString(rs.getInt(1));
                }

            }
        } catch (Exception e) {
            System.out.println("Error while insert saveColumnType..." + e);
        }
        return column_type_id;
    }

    public List<ColumnTypeBean> getAllColumnType() {
        List<ColumnTypeBean> columnTypeData = new ArrayList<>();

        String query = " select column_type_id, type_name ,remark"
                + " FROM column_type "
                + " WHERE active = 'Y' order by column_type_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ColumnTypeBean columnType = new ColumnTypeBean();
                columnType.setColumn_type_id(stmt.getInt("column_type_id"));
                columnType.setType_name(stmt.getString("type_name"));
                columnType.setRemark(stmt.getString("remark"));
                columnTypeData.add(columnType);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return columnTypeData;
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
