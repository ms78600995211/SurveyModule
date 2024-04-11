/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.dynamicForm;

import com.apogee.gnss.form.FormBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author lENOVO
 */
public class DynamicFormModel {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

    private static Connection connection;
    private final String COLOR_OK = "#a2a220";
    private final String COLOR_ERROR = "red";
    private String message = "";
    private String msgbgcolor = "";

    public void setDbConnection(Connection con) {
        try {
            connection = con;
        } catch (Exception e) {
            System.out.println("com.jpss.registartioncummodel.LoginModel.setConnection(): " + e);
        }
    }

    public String saveSelectionValues(int formMappingId, String selectionValues, String remark, String log_in_person) {
        int rowsAffected = 0;
        String selection_id = "";

        try {
            String query = "insert into selection(form_mapping_id, selection_values, remark, created_by) values(?,?,?,?);";
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, formMappingId);
            pstmt.setString(2, selectionValues);
            pstmt.setString(3, remark);
            pstmt.setString(4, log_in_person);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                connection.commit();
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    selection_id = Integer.toString(rs.getInt(1));
                }
                message = "Data Added Successfully.";
                msgbgcolor = "green";
            } else {
                connection.rollback();
                message = "Some Error Try Again.";
                msgbgcolor = "red";
            }

        } catch (Exception e) {
            System.out.println("Error while insert file_type..." + e);
        }
        return selection_id;
    }

    public List<FormBean> getAllForm() {
        List<FormBean> FormListData = new ArrayList<>();

        String query = " select form_id, form_name, remark "
                + " FROM form "
                + " WHERE active = 'Y' order by form_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                FormBean form = new FormBean();
                form.setForm_id(stmt.getInt("form_id"));
                form.setForm_name(stmt.getString("form_name"));
                form.setRemark(stmt.getString("remark"));

                FormListData.add(form);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return FormListData;
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

    public String getDynamicFormStatus(String form_id) {

        String status = "";
        try {
            String query1 = "SELECT is_create_table FROM query_table WHERE form_id ='" + form_id + "'";
            PreparedStatement pstmt1 = connection.prepareStatement(query1);
            ResultSet stmt1 = pstmt1.executeQuery();

            while (stmt1.next()) {
                status = stmt1.getString("is_create_table");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public String executeDynamicQuery(String form_id) {

        String query = "";

        try {

            String query1 = "SELECT mysql_query FROM query_table WHERE form_id ='" + form_id + "'"
                    + " and is_create_table='No' and active='Y' ";

            PreparedStatement pstmt1 = connection.prepareStatement(query1);
            ResultSet stmt1 = pstmt1.executeQuery();

            while (stmt1.next()) {

                query = stmt1.getString("mysql_query");
                PreparedStatement preparedStatement1 = connection.prepareStatement(query);
                preparedStatement1.execute();
                System.out.println("Table created succesfuly........");
                String updateSQL = "UPDATE query_table SET is_create_table = ? WHERE form_id = ?  and active=? ";

                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

                // Set the values for the placeholders
                preparedStatement.setString(1, "Yes");
                preparedStatement.setInt(2, Integer.parseInt(form_id));
                preparedStatement.setString(3, "Y");

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();

                System.out.println(rowsAffected + " row(s) updated successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return message;
    }

//    public List<FormBean> getColumnNameOnlyYesSelection(String formTypeId) {
//        List<FormBean> ColumnNameListData = new ArrayList<>();
//
//        String query = " select form_mapping_id, display_name FROM form_mapping "
//                + " WHERE active = 'Y' and form_id = '" + formTypeId + "' and is_selected = 'Yes' "
//                + " order by form_mapping_id desc ";
//
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            ResultSet stmt = pstmt.executeQuery();
//
//            while (stmt.next()) {
//                FormBean columnName = new FormBean();
//                columnName.setForm_mapping_id(stmt.getInt("form_mapping_id"));
//                columnName.setDisplay_name(stmt.getString("display_name"));
//                ColumnNameListData.add(columnName);
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        return ColumnNameListData;
//    }
//    public Map<String, List<String>> getColumnNameWithValues(String formTypeId) {
    public List<FormBean> getColumnNameWithValues(String formTypeId) {
        List<FormBean> ColumnNameListData = new ArrayList<>();
//        Map<String, List<String>> columnData = new HashMap<>();

//        String query = " select fmm.form_mapping_id, fmm.display_name, sd.column_values "
//                + " FROM form_mapping AS fmm "
//                + " JOIN show_data AS sd ON fmm.form_mapping_id = sd.form_mapping_id "
//                + " WHERE fmm.active = 'Y' and sd.active = 'Y' and fmm.form_id = '" + formTypeId + "' ";
        String query = " select form_mapping_id, column_subtype_id,display_name FROM form_mapping "
                + " WHERE active = 'Y' and form_id = '" + formTypeId + "' "
                + " order by form_mapping_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                FormBean columnNamewithValues = new FormBean();
                columnNamewithValues.setForm_mapping_id(stmt.getInt("form_mapping_id"));
                String column_subtype_id = stmt.getString("column_subtype_id");
                String column_type_id = "";
                String type_name = "";
                String subtype_name = "";




                String query3 = " select subtype_name FROM column_subtype "
                        + " WHERE active = 'Y' and column_subtype_id = '" + column_subtype_id + "' ";

                PreparedStatement pstmt3 = connection.prepareStatement(query3);
                ResultSet stmt3 = pstmt3.executeQuery();
                while (stmt3.next()) {

                    subtype_name = stmt3.getString("subtype_name");

                }

                String query1 = " select column_type_id FROM column_subtype "
                        + " WHERE active = 'Y' and column_subtype_id = '" + column_subtype_id + "' ";

                PreparedStatement pstmt1 = connection.prepareStatement(query1);
                ResultSet stmt1 = pstmt1.executeQuery();
                while (stmt1.next()) {

                    column_type_id = stmt1.getString("column_type_id");

                }

                String query2 = " select type_name FROM column_type "
                        + " WHERE active = 'Y' and column_type_id = '" + column_type_id + "' ";

                PreparedStatement pstmt2 = connection.prepareStatement(query2);
                ResultSet stmt2 = pstmt2.executeQuery();
                while (stmt2.next()) {

                    type_name = stmt2.getString("type_name");

                }

                columnNamewithValues.setType_name(type_name);
                columnNamewithValues.setSubtype_name(subtype_name);


                columnNamewithValues.setDisplay_name(stmt.getString("display_name"));
                ColumnNameListData.add(columnNamewithValues);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return ColumnNameListData;
    }

//    public List<FormMappingBean> getFormMappingData() {
//        List<FormMappingBean> FormMappingListData = new ArrayList<>();
//
//        String query = " select fmm.form_mapping_id, fmm.display_name, fmm.is_selected, fmm.remark, "
//                + " fm.form_name, cs.subtype_name, ct.type_name "
//                + " FROM form_mapping AS fmm "
//                + " JOIN form AS fm ON fmm.form_id = fm.form_id "
//                + " JOIN column_subtype AS cs ON fmm.column_subtype_id = cs.column_subtype_id "
//                + " JOIN column_type AS ct ON cs.column_type_id = ct.column_type_id "
//                + " WHERE fmm.active = 'Y' and fm.active = 'Y' and cs.active = 'Y' and ct.active = 'Y' "
//                + " order by fmm.form_mapping_id desc ";
//
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            ResultSet stmt = pstmt.executeQuery();
//
//            while (stmt.next()) {
//                FormMappingBean formMapping = new FormMappingBean();
//                formMapping.setForm_mapping_id(stmt.getInt("form_mapping_id"));
//                formMapping.setForm_name(stmt.getString("form_name"));
//                formMapping.setDisplay_name(stmt.getString("display_name"));
//                formMapping.setType_name(stmt.getString("type_name"));
//                formMapping.setSubtype_name(stmt.getString("subtype_name"));
//                formMapping.setIs_selected(stmt.getString("is_selected"));
//                formMapping.setRemark(stmt.getString("remark"));
//
//                FormMappingListData.add(formMapping);
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        return FormMappingListData;
//    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the msgbgcolor
     */
    public String getMsgbgcolor() {
        return msgbgcolor;
    }

    /**
     * @param msgbgcolor the msgbgcolor to set
     */
    public void setMsgbgcolor(String msgbgcolor) {
        this.msgbgcolor = msgbgcolor;
    }

}
