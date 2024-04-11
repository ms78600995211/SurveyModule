/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.columnSubType;

import com.apogee.gnss.columnType.ColumnTypeBean;
import com.apogee.gnss.hemisphere.HemisphereBean;
import com.apogee.gnss.zoneData.ZoneDataBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ColumnSubTypeModel {

    private Connection connection;
    private String COLOR_OK = "#a2a220";
    private String COLOR_ERROR = "red";

    public String saveColumnSubType(String subtype_name, String column_type_id, String name, String remark) {
        int rowsAffected = 0;
        String column_subtype_id = "";

        try {
            String query = "insert into column_subtype(column_type_id,subtype_name,remark,active,revision_no,created_by) values(?,?,?,?,?,?);";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, column_type_id);
            pstmt.setString(2, subtype_name);
            pstmt.setString(3, remark);
            pstmt.setString(4, "Y");
            pstmt.setInt(5, 0);
            pstmt.setString(6, name);
            rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    column_subtype_id = Integer.toString(rs.getInt(1));
                }

            }
        } catch (Exception e) {
            System.out.println("Error while insert angleunittable..." + e);
        }
        return column_subtype_id;
    }

    public List<ColumnSubTypeBean> getAllColumnSubType() {
        List<ColumnSubTypeBean> ColumnSubTypeList = new ArrayList<>();
        String query = " select * "
                + " FROM column_subtype "
                + " WHERE active = 'Y' order by column_subtype_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ColumnSubTypeBean bean = new ColumnSubTypeBean();
                bean.setSubtype_name(stmt.getString("subtype_name"));
                bean.setRemark(stmt.getString("remark"));
                int column_type_id = stmt.getInt("column_type_id");

                String query1 = " SELECT type_name  FROM column_type  WHERE   column_type_id =" + column_type_id + " ";
                PreparedStatement pstmt1 = connection.prepareStatement(query1);

                ResultSet stmt1 = pstmt1.executeQuery();
                String type_name = "";
                while (stmt1.next()) {
                    type_name = stmt1.getString("type_name");
                }

                bean.setType_name(type_name);
                ColumnSubTypeList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return ColumnSubTypeList;
    }

    public List<ColumnTypeBean> getColumnTypes() {
        List<ColumnTypeBean> columnTypeList = new ArrayList<>();

      String query = " select column_type_id, type_name "
                + " FROM column_type "
                + " WHERE active = 'Y' order by column_type_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ColumnTypeBean ColumnType = new ColumnTypeBean();
                ColumnType.setColumn_type_id(stmt.getInt("column_type_id"));
                ColumnType.setType_name(stmt.getString("type_name"));

                columnTypeList.add(ColumnType);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return columnTypeList;
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
