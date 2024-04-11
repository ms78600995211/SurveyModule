/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.selectionValueMapping;

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
import org.springframework.stereotype.Service;

/**
 *
 * @author lENOVO
 */

@Service
public class SelectionValueMappingModel {

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

    public List<FormBean> getColumnNameOnlyYesSelection(String formTypeId) {
        List<FormBean> ColumnNameListData = new ArrayList<>();

        String query = " select form_mapping_id, display_name FROM form_mapping "
                + " WHERE active = 'Y' and form_id = '" + formTypeId + "' and is_selected = 'Yes' "
                + " order by form_mapping_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                FormBean columnName = new FormBean();
                columnName.setForm_mapping_id(stmt.getInt("form_mapping_id"));
                columnName.setDisplay_name(stmt.getString("display_name"));
                ColumnNameListData.add(columnName);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return ColumnNameListData;
    }

//    public Map<String, List<String>> getColumnNameWithValues(String formTypeId) {
//    public List<FormBean> getColumnNameWithValues(String formTypeId) {
//        List<FormBean> ColumnNameListData = new ArrayList<>();
////        Map<String, List<String>> columnData = new HashMap<>();
//
////        String query = " select fmm.form_mapping_id, fmm.display_name, sd.column_values "
////                + " FROM form_mapping AS fmm "
////                + " JOIN show_data AS sd ON fmm.form_mapping_id = sd.form_mapping_id "
////                + " WHERE fmm.active = 'Y' and sd.active = 'Y' and fmm.form_id = '" + formTypeId + "' ";
//        String query = " select form_mapping_id, display_name FROM form_mapping "
//                + " WHERE active = 'Y' and form_id = '" + formTypeId + "' "
//                + " order by form_mapping_id desc ";
//
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            ResultSet stmt = pstmt.executeQuery();
//
//            while (stmt.next()) {
//                FormBean columnNamewithValues = new FormBean();
////                String key = stmt.getString("display_name");
////                String value = stmt.getString("column_values");
////
////                if (columnData.containsKey(key)) {
////                    // If it exists, add the value to the corresponding list
////                    columnData.get(key).add(value);
////                } else {
////                    // If it doesn't exist, create a new list and initialize it with the value
////                    List<String> values = new ArrayList<>();
////                    values.add(value);
////                    columnData.put(key, values);
////                }
//
//                columnNamewithValues.setForm_mapping_id(stmt.getInt("form_mapping_id"));
//                columnNamewithValues.setDisplay_name(stmt.getString("display_name"));
//                ColumnNameListData.add(columnNamewithValues);
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        return ColumnNameListData;
//    }

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
