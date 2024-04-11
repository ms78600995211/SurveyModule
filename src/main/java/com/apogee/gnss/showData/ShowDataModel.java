/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.showData;

import com.apogee.gnss.form.FormBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ShowDataModel {

    private Connection connection;
    private String COLOR_OK = "#a2a220";
    private String COLOR_ERROR = "red";

    public String saveColumnvalue(String form_mapping_id, String file_path, String colVal, String name, String remark) throws SQLException {
        int rowsAffected = 0;
        String column_type_id = "";

        if (colVal.isEmpty()) {
            colVal = " ";

        }
        try {
            String query = "insert into show_data(form_mapping_id, column_values,folder_path,remark,active,revision_no,created_by) values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, form_mapping_id);
            pstmt.setString(2, colVal);
            pstmt.setString(3, file_path);
            pstmt.setString(4, remark);
            pstmt.setString(5, "Y");
            pstmt.setInt(6, 0);
            pstmt.setString(7, name);
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

    public Map getValues(List mappingList) throws SQLException {

        LinkedHashMap<String, List> mp = new LinkedHashMap<>();

        for (int i = 0; i < mappingList.size(); i++) {

            String column_name = "";
            String query1 = " SELECT column_name FROM form_mapping  WHERE active = 'Y' and form_mapping_id = '" + mappingList.get(i) + "'";

            PreparedStatement pstmt1 = connection.prepareStatement(query1);

            ResultSet stmt1 = pstmt1.executeQuery();
            while (stmt1.next()) {
                column_name = stmt1.getString("column_name");
            }

            String query = "SELECT column_values FROM show_data  WHERE active = 'Y' and form_mapping_id ='" + mappingList.get(i) + "'"
                    + "order by show_data_id desc ";
            List list = new ArrayList();

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet stmt = pstmt.executeQuery();

                while (stmt.next()) {

                    String column_values = stmt.getString("column_values");
                    list.add(column_values);
                }
                mp.put(column_name, list);

            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }
        return mp;
    }

    public List getFormMappingIds(String form_id) throws SQLException {
        List list = new ArrayList();
        String query1 = " SELECT form_mapping_id  FROM form_mapping "
                + " WHERE active = 'Y' and   form_id ='" + form_id + "' ";
        PreparedStatement pstmt1 = connection.prepareStatement(query1);

        ResultSet stmt1 = pstmt1.executeQuery();
        String form_mapping_id = "";
        while (stmt1.next()) {
            form_mapping_id = stmt1.getString("form_mapping_id");
            list.add(form_mapping_id);
        }
        return list;
    }

    public String getPath(String fileName) throws SQLException {
        String folder_path = "";
        String query1 = " SELECT distinct folder_path  FROM show_data "
                + " WHERE active = 'Y' and   column_values ='" + fileName + "' ";
        PreparedStatement pstmt1 = connection.prepareStatement(query1);

        ResultSet stmt1 = pstmt1.executeQuery();
        while (stmt1.next()) {
            folder_path = stmt1.getString("folder_path");
        }
        return folder_path;
    }

    public List<ShowDataBean> getColumnName(String formTypeId) {
        List<ShowDataBean> ColumnNameListData = new ArrayList<>();

        String query = " select fm.form_mapping_id,fm.column_name,sc.subtype_name,ct.type_name "
                + "FROM form_mapping fm "
                + "inner join  column_subtype sc on sc.column_subtype_id=fm.column_subtype_id "
                + "inner join column_type ct on ct.column_type_id=sc.column_type_id "
                + "where fm.form_id='" + formTypeId + "'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ShowDataBean column = new ShowDataBean();
                column.setForm_mapping_id(stmt.getString("form_mapping_id"));
                column.setColumn_name(stmt.getString("column_name"));
                column.setColumn_type(stmt.getString("type_name"));
                column.setColumn_subType(stmt.getString("subtype_name"));
                ColumnNameListData.add(column);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return ColumnNameListData;
    }

    public String getColumn(String mapping_id) throws SQLException {
        String column_name = "";
        String query1 = " SELECT column_name FROM form_mapping  WHERE  active = 'Y' and form_mapping_id ='" + mapping_id + "' ";
        PreparedStatement pstmt1 = connection.prepareStatement(query1);

        ResultSet stmt1 = pstmt1.executeQuery();
        while (stmt1.next()) {
            column_name = stmt1.getString("column_name");
        }
        return column_name;
    }

    public List<FormBean> getMappedForms() {
        List<FormBean> FormListData = new ArrayList<>();

        String query = " select DISTINCT form_id from form_mapping  "
                + " WHERE active = 'Y' order by form_id asc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();
            String form_name = "";

            while (stmt.next()) {
                FormBean form = new FormBean();

                int form_id = stmt.getInt("form_id");
                form.setForm_id(form_id);

                String query1 = "SELECT form_name FROM form WHERE form_id ='" + form_id + "'"
                        + "order by form_id asc ";

                PreparedStatement pstmt1 = connection.prepareStatement(query1);
                ResultSet stmt1 = pstmt1.executeQuery();

                while (stmt1.next()) {
                    form_name = stmt1.getString("form_name");
                }

                form.setForm_name(form_name);
                FormListData.add(form);

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return FormListData;
    }


    /*
    public List<FormBean> getAllform() {

        List<FormBean> list = new ArrayList();

        String query = " select form_id,form_name "
                + " FROM form "
                + " WHERE active = 'Y' order by form_id asc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                FormBean bean = new FormBean();
                int form_id = stmt.getInt("form_id");
                String form_name = stmt.getString("form_name");
                bean.setForm_id(form_id);
                bean.setForm_name(form_name);
                list.add(bean);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }
     */
    public String getFirstForm() {

        String form_id = "";

        String query = " select form_id  FROM form WHERE active = 'Y' LIMIT 1 ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                form_id = stmt.getString("form_id");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return form_id;
    }

    public List<ShowDataBean> getAllColumnName(String form_id) {
        List<ShowDataBean> columnNameList = new ArrayList<>();

        String query = " SELECT column_name FROM form_mapping  WHERE   form_id ='" + form_id + "' "
                + " And active = 'Y' order by form_mapping_id asc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                ShowDataBean showdata = new ShowDataBean();
                showdata.setColumn_name(stmt.getString("column_name"));

                columnNameList.add(showdata);

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return columnNameList;
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
