/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.formMapping;

import com.apogee.gnss.form.FormBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lENOVO
 */
@Service
public class FormMappingModel {

    private static Connection connection;
    private final String COLOR_OK = "#a2a220";
    private final String COLOR_ERROR = "red";
    private String message = "";
    private String msgbgcolor = "";

    public void setDbConnection(Connection con) {
        try {
            connection = con;
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.setDbConnection()" + e);
        }
    }

    public String saveFormMapping(int formId, String column_name, String display_name, int column_subtype_id, String is_selected, String data_length, String log_in_person) {
        int rowsAffected = 0;
        String form_mapping_id = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            if (!column_name.trim().isEmpty() && !display_name.trim().isEmpty() && !data_length.trim().isEmpty()&& !log_in_person.trim().isEmpty()) {
                String query1 = " select count(*) as count from form_mapping where form_id =? and column_name =?  and active = 'Y' ;";
                pstmt = connection.prepareStatement(query1);
                pstmt.setInt(1, formId);
                pstmt.setString(2, column_name);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("count");
                }
                if (count > 0) {
                    message = column_name + " Column Already Exist And Data Not Saved";
                    msgbgcolor = "red";
                } else {
                    pstmt = null;
                    rs = null;
                    String query = "insert into form_mapping(form_id, column_name, display_name, column_subtype_id, is_selected, remark, created_by) values(?,?,?,?,?,?,?);";
                    connection.setAutoCommit(false);
                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, formId);
                    pstmt.setString(2, column_name);
                    pstmt.setString(3, display_name);
                    pstmt.setInt(4, column_subtype_id);
                    pstmt.setString(5, is_selected);
                    pstmt.setString(6, data_length);
                    pstmt.setString(7, log_in_person);
                    rowsAffected = pstmt.executeUpdate();
                }
                if (rowsAffected > 0) {
                    connection.commit();
                    rs = pstmt.getGeneratedKeys();
                    while (rs.next()) {
                        form_mapping_id = Integer.toString(rs.getInt(1));
                    }
                    message = "Data Added Successfully.";
                    msgbgcolor = "green";
                } else {
                    connection.rollback();
                    message = "Some Error Try Again.";
                    msgbgcolor = "red";
                }
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.saveFormMapping()" + e);
        }
        return form_mapping_id;
    }

    public List<FormBean> getAllForm() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<FormBean> FormListData = new ArrayList<>();
        String query = " select form_id, form_name, remark FROM form "
                + " WHERE active = 'Y' order by form_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                FormBean form = new FormBean();
                form.setForm_id(stmt.getInt("form_id"));
                form.setForm_name(stmt.getString("form_name"));
                form.setRemark(stmt.getString("remark"));
                FormListData.add(form);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.getAllForm()" + e);
        }
        return FormListData;
    }

    public List<FormBean> getColumnType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<FormBean> ColumnTypeListData = new ArrayList<>();
        String query = " select * FROM column_type "
                + " WHERE active = 'Y' order by column_type_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                FormBean columnType = new FormBean();
                columnType.setColumn_type_id(stmt.getInt("column_type_id"));
                columnType.setType_name(stmt.getString("type_name"));
                ColumnTypeListData.add(columnType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.getColumnType()" + e);
        }
        return ColumnTypeListData;
    }

    public List<FormBean> getColumnSubType(String columnTypeId) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<FormBean> ColumnSubTypeListData = new ArrayList<>();
        String query = " select * FROM column_subtype "
                + " WHERE column_type_id = ? and active = 'Y' order by column_subtype_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, columnTypeId);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                FormBean columnSubType = new FormBean();
                columnSubType.setColumn_subtype_id(stmt.getInt("column_subtype_id"));
                columnSubType.setSubtype_name(stmt.getString("subtype_name"));
                ColumnSubTypeListData.add(columnSubType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.getColumnSubType()" + e);
        }
        return ColumnSubTypeListData;
    }

    public List<FormMappingBean> getFormMappingData(String form_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<FormMappingBean> FormMappingListData = new ArrayList<>();
        String query = " select fmm.form_mapping_id, fmm.display_name, fmm.is_selected, fmm.remark as datatypelength, "
                + " fm.form_name, cs.subtype_name, cs.remark as datatype, ct.type_name "
                + " FROM form_mapping AS fmm "
                + " JOIN form AS fm ON fmm.form_id = fm.form_id "
                + " JOIN column_subtype AS cs ON fmm.column_subtype_id = cs.column_subtype_id "
                + " JOIN column_type AS ct ON cs.column_type_id = ct.column_type_id "
                + " WHERE fmm.active = 'Y' and fm.active = 'Y' and cs.active = 'Y' and ct.active = 'Y' ";
        if (form_id != null && !form_id.isEmpty()) {
            query += " and fmm.form_id= ?";
        }
        query += " order by fmm.form_mapping_id desc";
        try {
            pstmt = connection.prepareStatement(query);
            if (form_id != null && !form_id.isEmpty()) {
                pstmt.setString(1, form_id);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                FormMappingBean formMapping = new FormMappingBean();
                formMapping.setForm_mapping_id(stmt.getInt("form_mapping_id"));
                formMapping.setForm_name(stmt.getString("form_name"));
                formMapping.setDisplay_name(stmt.getString("display_name"));
                formMapping.setType_name(stmt.getString("type_name"));
                formMapping.setSubtype_name(stmt.getString("subtype_name"));
                formMapping.setIs_selected(stmt.getString("is_selected"));
                formMapping.setRemark(stmt.getString("datatypelength"));
                formMapping.setData_length(stmt.getString("datatype"));
                FormMappingListData.add(formMapping);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.getFormMappingData()" + e);
        }
        return FormMappingListData;
    }

    String getFormName(int formId) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String formName = "";
        String query = " select * FROM form "
                + " WHERE form_id =?  and active = 'Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, formId);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                formName = stmt.getString("form_name");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.getFormName()" + e);
        }
        return formName;
    }

    String getColumnSubTypeById(int columnSubTypeId) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String data_type = "";
        String query = " select * FROM column_subtype "
                + " WHERE column_subtype_id =? and active = 'Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, columnSubTypeId);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                data_type = stmt.getString("remark");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.getColumnSubTypeById()" + e);
        }
        return data_type;
    }

    String saveDynamicQuery(String querymysql, String sqlite_query, int formId, String log_in_person) {
        String query_table = "";
        PreparedStatement pstmt;
        ResultSet rs = null;
        int count = 0;
        int rowsAffected = 0;
        int revision_no = 0;
        int query_table_id = 0;
        try {
            String insertQuery = "insert into query_table(form_id, mysql_query, sqlite_query, revision_no, created_by)values(?,?,?,?,?);";
            String querycount = " select count(*) as count from query_table where form_id =? and active = 'Y' ;";
            pstmt = connection.prepareStatement(querycount);
            pstmt.setInt(1, formId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            pstmt = null;
            rs = null;
            if (count > 0) {
                String getrevision = "select max(revision_no), query_table_id from query_table where form_id=? GROUP BY query_table_id";
                pstmt = connection.prepareStatement(getrevision);
                pstmt.setInt(1, formId);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    query_table_id = rs.getInt("query_table_id");
                    revision_no = rs.getInt("max(revision_no)");
                }
                pstmt = null;
                rs = null;

                String updatequery = "update query_table set active='N' where query_table_id= ? ";
                pstmt = connection.prepareStatement(updatequery);
                pstmt.setInt(1, query_table_id);
                rowsAffected = pstmt.executeUpdate();
                pstmt = null;
                rs = null;
                if (rowsAffected > 0) {
                    connection.setAutoCommit(false);
                    pstmt = connection.prepareStatement(insertQuery);
                    pstmt.setInt(1, formId);
                    pstmt.setString(2, querymysql);
                    pstmt.setString(3, sqlite_query);
                    pstmt.setInt(4, revision_no + 1);
                    pstmt.setString(5, log_in_person);
                    rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        connection.commit();
                        rs = pstmt.getGeneratedKeys();
                        while (rs.next()) {
                            query_table = Integer.toString(rs.getInt(1));
                        }
                        executeDynamicQuery1(Integer.toString(formId));
                        message = "Data Added Successfully.";
                        msgbgcolor = "green";
                    } else {
                        connection.rollback();
                        message = "Some Error in Dynamic query Try Again.";
                        msgbgcolor = "red";
                    }
                }
            } else {
                connection.setAutoCommit(false);
                pstmt = connection.prepareStatement(insertQuery);
                pstmt.setInt(1, formId);
                pstmt.setString(2, querymysql);
                pstmt.setString(3, sqlite_query);
                pstmt.setInt(4, revision_no);
                pstmt.setString(5, log_in_person);
                rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    connection.commit();
                    message = executeDynamicQuery1(Integer.toString(formId));
                    msgbgcolor = "green";
                } else {
                    connection.rollback();
                    message = "Some Error Try Again.";
                    msgbgcolor = "red";
                }
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.saveDynamicQuery()" + e);
        }

        return query_table;
    }

    public String executeDynamicQuery1(String form_id) {

        String mysql_query = "";
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        try {
            String query1 = "SELECT mysql_query FROM query_table WHERE form_id = ? "
                    + " and is_create_table='No' and active='Y' ";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, form_id);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                mysql_query = stmt.getString("mysql_query");
            }
            pstmt = null;
            stmt = null;
            pstmt = connection.prepareStatement(mysql_query);
            int result = pstmt.executeUpdate();
            pstmt = null;
            stmt = null;
            if (result == 0) {
                connection.setAutoCommit(false);
                String query2 = " update query_table set is_create_table='Yes' where form_id= ? and active='Y' ";
                PreparedStatement pstmt1 = connection.prepareStatement(query2);
                pstmt1.setString(1, form_id);
                int result1 = pstmt1.executeUpdate();
                if (result1 == 1) {
                    connection.commit();
                    message = "Data Added Successfully.";
                } else {
                    connection.rollback();
                    message = "Data Added but Dynamic Table Not Created...";
                }
            } else {
                connection.rollback();
                message = "Data Added but Dynamic Table Not Created...";
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.executeDynamicQuery1()" + e);
        }

        return message;
    }

    String checkColumnNameExists(int form_id, String columnValue) {
        String form_mapping_id = "";
        String query = " select * FROM form_mapping "
                + " WHERE form_id =? and column_name =? and active = 'Y' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, form_id);
            pstmt.setString(2, columnValue);
            ResultSet stmt = pstmt.executeQuery();
            while (stmt.next()) {
                form_mapping_id = stmt.getString("form_mapping_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.checkColumnNameExists()" + e);
        }
        return form_mapping_id;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingModel.closeConnection()" + e);
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
