package com.apogee.gnss.form;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author lENOVO
 */
@Service
public class FormModel {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    private static Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveForm(String formName, String remark, String log_in_person) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String query1 = " select count(*) as count from form where form_name =?  and active = 'Y' ;";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, formName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count > 0) {
                message = formName + "Name Already Exist.";
                color = COLOR_ERROR;
            } else {
                if (!formName.trim().isEmpty() && !log_in_person.trim().isEmpty()) {
                    pstmt = null;
                    String query = "insert into form(form_name, remark, created_by) values(?,?,?) ";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, formName);
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
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.saveForm()" + e);
        }
    }

    public List<FormBean> getAllForm() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FormBean> FormListData = new ArrayList<>();
        String query = " select form_id, form_name, remark  FROM form "
                + " WHERE active = 'Y' order by form_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                FormBean form = new FormBean();
                form.setForm_id(rs.getInt("form_id"));
                form.setForm_name(rs.getString("form_name"));
                form.setRemark(rs.getString("remark"));
                FormListData.add(form);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getAllForm()" + e);
        }
        return FormListData;
    }

    public JSONArray allFormInJSON() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        JSONArray array = new JSONArray();
        String query = " select distinct f.form_id, f.form_name ,f.remark from form_mapping  fm "
                + " join form f on f.form_id=fm.form_id where f.active='Y' and fm.active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                JSONObject object = new JSONObject();
                object.put("form_id", rs.getInt("form_id"));
                object.put("form_name", rs.getString("form_name"));
                object.put("remark", rs.getString("remark"));
                array.put(object);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.allFormInJSON()" + e);
        }
        return array;
    }

    public List getAllUsers() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List getAllUsers = new ArrayList<>();
        String query = " select id  FROM login order by  id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString("id");
                getAllUsers.add(user_id);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getAllUsers()" + e);
        }
        return getAllUsers;
    }

    public String getForm(String form_name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String form_id = "";
        String query = " select form_id FROM form  WHERE active = 'Y' and form_name=? ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, form_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                form_id = rs.getString("form_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getForm()" + e);
        }
        return form_id;
    }

    public JSONObject getDynamicSqliteQuery(String form_name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String form_id = "";
        String sqliteQuery = "";
        JSONObject jobj = new JSONObject();
        List list = new ArrayList();
        String query = " select form_id FROM form  WHERE active = 'Y' and form_name=? ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, form_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                form_id = rs.getString("form_id");
            }
            pstmt = null;
            rs = null;
            query = "SELECT sqlite_query FROM query_table WHERE form_id =?  and active='Y' ";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, form_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                sqliteQuery = rs.getString("sqlite_query");
            }
            pstmt = null;
            rs = null;
            query = " SELECT form_mapping_id FROM form_mapping where form_id=? and active='Y' ";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, form_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String form_mapping_id = rs.getString("form_mapping_id");
                list.add(form_mapping_id);
            }
            jobj = getMppingData(list, sqliteQuery);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getDynamicSqliteQuery()" + e);
        }
        return jobj;
    }

    /*
    public String getSqliteQuery(String form_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String sqliteQuery = "";

        String query = "SELECT sqlite_query FROM query_table WHERE form_id =?  and active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, form_id);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                sqliteQuery = stmt.getString("sqlite_query");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getSqliteQuery()" + e);
        }
        return sqliteQuery;
    }
     */
    public String getQueryTableId(String form_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query_table_id = "";
        String query = "SELECT query_table_id FROM query_table WHERE form_id =?  and active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, form_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                query_table_id = rs.getString("query_table_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getQueryTableId()" + e);
        }
        return query_table_id;
    }

    /*
    public List getFormMppingList(String form_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List list = new ArrayList();

        String query = " SELECT form_mapping_id FROM form_mapping where form_id=? and active='y' ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, form_id);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                String form_mapping_id = stmt.getString("form_mapping_id");
                list.add(form_mapping_id);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getFormMppingList()" + e);
        }
        return list;
    }
     */
    public JSONObject getMppingData(List mapping_list, String sqliteQuery) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        JSONObject jobj = new JSONObject();
        JSONArray columnTypearray = new JSONArray();
        JSONArray columnSubTyparray = new JSONArray();
        JSONArray selectionValuearray = new JSONArray();
        for (int i = 0; i < mapping_list.size(); i++) {
            String selection_values = "";
            String selection_id = "";
            String remark = "";
            String query = " SELECT * FROM form_mapping where form_mapping_id=? and active='y' ";
            try {
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, mapping_list.get(i).toString());
                stmt = pstmt.executeQuery();
                while (stmt.next()) {
                    String form_mapping_id = stmt.getString("form_mapping_id");
                    String column_subtype_id = stmt.getString("column_subtype_id");
                    JSONObject selectionValueobject = new JSONObject();
                    String queryy = " SELECT selection_id,selection_values,remark "
                            + " FROM selection where form_mapping_id=?  and active='y' ";
                    PreparedStatement pstmt11 = connection.prepareStatement(queryy);
                    pstmt11.setString(1, mapping_list.get(i).toString());
                    ResultSet stmt11 = pstmt11.executeQuery();
                    while (stmt11.next()) {
                        selection_id = stmt11.getString("selection_id");
                        selection_values = stmt11.getString("selection_values");
                        remark = stmt11.getString("remark");
                        if (remark == null) {
                            remark = "";
                        }
                    }
                    if (selection_values.isEmpty()) {
                        selectionValueobject.put("form_mapping_id", "");
                    }
                    selectionValueobject.put("selection_id", selection_id);
                    selectionValueobject.put("selection_values", selection_values);
                    selectionValueobject.put("selection_remark", remark);
                    selectionValuearray.put(selectionValueobject);
                    String query1 = " SELECT column_subtype_id,column_type_id,subtype_name,remark "
                            + " FROM column_subtype where column_subtype_id=? and active='y' ";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, column_subtype_id.toString());
                    ResultSet stmt1 = pstmt1.executeQuery();
                    while (stmt1.next()) {
                        JSONObject object6 = new JSONObject();
                        String subtype_name = stmt1.getString("subtype_name");
                        String subtype_remark = stmt1.getString("remark");
                        String column_type_id = stmt1.getString("column_type_id");
                        if (subtype_remark == null) {
                            subtype_remark = "";
                        }
                        object6.put("form_mapping_id", form_mapping_id);
                        object6.put("column_subtype_id", column_subtype_id);
                        object6.put("column_type_id", column_type_id);
                        object6.put("subtype_name", subtype_name);
                        object6.put("subtype_remark", subtype_remark);
                        columnSubTyparray.put(object6);
                        String query2 = " SELECT column_type_id,type_name,remark FROM column_type where active='y' and column_type_id=? ";
                        PreparedStatement pstmt2 = connection.prepareStatement(query2);
                        pstmt2.setString(1, column_type_id);
                        ResultSet stmt2 = pstmt2.executeQuery();
                        while (stmt2.next()) {
                            JSONObject object1 = new JSONObject();
                            String type_name = stmt2.getString("type_name");
                            String type_remark = stmt2.getString("remark");
                            if (type_remark == null) {
                                type_remark = "";
                            }
                            object1.put("form_mapping_id", form_mapping_id);
                            object1.put("column_subtype_id", column_subtype_id);
                            object1.put("column_type_id", column_type_id);
                            object1.put("type_name", type_name);
                            object1.put("type_remark", type_remark);
                            columnTypearray.put(object1);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("com.apogee.gnss.form.FormModel.getMppingData()" + e);
            }
        }
        jobj.put("query_table", sqliteQuery);
        jobj.put("column_type", columnTypearray);
        jobj.put("column_subtype", columnSubTyparray);
        jobj.put("selection", selectionValuearray);
        return jobj;
    }

    public int saveDynamicData(Map mp, String form_name) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        Map<String, String> data = mp;
        try {
            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                columns.append(entry.getKey()).append(",");
                values.append("?,");
            }
            columns.deleteCharAt(columns.length() - 1);
            values.deleteCharAt(values.length() - 1);
            String insertSQL = "INSERT INTO " + form_name + "( " + columns.toString() + ") VALUES (" + values.toString() + ")";
            pstmt = connection.prepareStatement(insertSQL);
            int parameterIndex = 1;
            for (String value : data.values()) {
                pstmt.setString(parameterIndex++, value);
            }
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.saveDynamicData()" + e);
        }
        return rowsAffected;
    }

    public String conformUserId(String a) throws Exception {
//        String url = "http://120.138.10.251:8080/login_module/api/checkUserId";
        String url = resourceBundle.getString("CHECK_USER_ID_URL");
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");
        String urlParameters = ("{\"user_id\": \"" + a + "\"}");
        System.out.println(" urlParameters:::" + urlParameters);
        StringBuilder response = new StringBuilder();
        httpClient.setDoOutput(true);
        try {
            DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.conformUserId()" + e);
        }
        return response.toString();
    }

    public List<FormBean> allForm(String form_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FormBean> FormListData = new ArrayList<>();
        String query = " select form_id, form_name, remark  FROM form  WHERE active = 'Y' ";
        if (form_id != null && !form_id.isEmpty()) {
            query += " and form_id= ?";
        }
        query += "order by form_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            if (form_id != null && !form_id.isEmpty()) {
                pstmt.setString(1, form_id);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                FormBean form = new FormBean();
                form.setForm_id(rs.getInt("form_id"));
                form.setForm_name(rs.getString("form_name"));
                form.setRemark(rs.getString("remark"));
                FormListData.add(form);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.getAllForm()" + e);
        }
        return FormListData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void setDbConnection(Connection con) {
        try {
            connection = con;
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormModel.setDbConnection()" + e);
        }
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
            System.out.println("com.apogee.gnss.form.FormModel.closeConnection()" + e);
        }
    }

}
