/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.drive;

import com.apogee.gnss.form.FormBean;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class DriveModel {

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

    public String saveDriveData(String show_data_id, String subType_name, String parentFolderName, String parentFolderUrl, String filename, String fileLink, String log_in_person) throws SQLException {
        int rowsAffected = 0;
        String drive_file_id = "";
        String file_type_id = "";
        String drive_folder_id = "";
        PreparedStatement pstmt3 = null;
        ResultSet rs3 = null;
        int count = 0;

        String query3 = " select count(*) as count from drive_folder where show_data_id ='" + show_data_id + "' and active = 'Y' ;";
        pstmt3 = connection.prepareStatement(query3);
        rs3 = pstmt3.executeQuery();
        while (rs3.next()) {
            count = rs3.getInt("count");
        }

        if (count > 0) {
            message = "Form Name Already Exist..";
            msgbgcolor = "red";
        } else {
            try {
                String query = "insert into drive_folder(show_data_id,folder_name,active,created_by,remark,revision_no) values(?,?,?,?,?,?) ";
                connection.setAutoCommit(false);

                PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, show_data_id);
                pstmt.setString(2, parentFolderUrl);
                pstmt.setString(3, "Y");
                pstmt.setString(4, log_in_person);
                pstmt.setString(5, "");
                pstmt.setInt(6, 0);
                rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    while (rs.next()) {
                        drive_folder_id = Integer.toString(rs.getInt(1));
                    }
                }
                int lastDotIndex = filename.lastIndexOf(".");
                String extension = filename.substring(lastDotIndex + 1);

                String qry = "select file_type_id from file_type where file_extension='" + extension + "'  and active='Y' ";

                PreparedStatement pstmt1 = connection.prepareStatement(qry);
                ResultSet stmt1 = pstmt1.executeQuery();

                while (stmt1.next()) {

                    file_type_id = Integer.toString(stmt1.getInt(1));

                }

                String query1 = "insert into drive_file(drive_folder_id,file_type_id,file_name,active,created_by,remark,revision_no) values(?,?,?,?,?,?,?)";
                PreparedStatement pstmt2 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                pstmt2.setString(1, drive_folder_id);
                pstmt2.setString(2, file_type_id);
                pstmt2.setString(3, fileLink);
                pstmt2.setString(4, "Y");
                pstmt2.setString(5, log_in_person);
                pstmt2.setString(6, "");
                pstmt2.setInt(7, 0);
                rowsAffected = pstmt2.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet rs1 = pstmt2.getGeneratedKeys();
                    while (rs1.next()) {
                        drive_file_id = Integer.toString(rs1.getInt(1));
                    }
                }

                if (rowsAffected > 0) {
                    connection.commit();
                    message = "Data Added Successfully.";
                    msgbgcolor = "green";
                } else {
                    connection.rollback();
                    message = "Some Error Try Again.";
                    msgbgcolor = "red";
                }

            } catch (Exception e) {
                System.out.println("Error while insert angleunittable..." + e);
            }
        }
        return drive_file_id;
    }

    public List<DriveBean> getFormData(String form_id) {

        List<DriveBean> formDetails = new ArrayList<>();

        String query = "select f.form_name,fm.form_mapping_id,sd.show_data_id,sd.column_values,sd.folder_path,cs.subtype_name,ct.type_name ,sd.remark "
                + "from form f "
                + "inner join form_mapping fm on fm.form_id=f.form_id "
                + "inner join show_data sd on sd.form_mapping_id=fm.form_mapping_id  "
                + "inner join column_subtype cs on cs.column_subtype_id=fm.column_subtype_id  "
                + "inner join column_type ct on ct.column_type_id=cs.column_type_id  "
                + " WHERE ct.type_name not IN ('TEXT','DATE','LOCATION') and f.form_id='" + form_id + "'";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                DriveBean bean = new DriveBean();
                String file_name = stmt.getString("column_values");
                file_name = file_name.replaceAll("\\s", "");

                if (!file_name.isEmpty()) {
                    bean.setFile_name(file_name);
                    bean.setFolder_path(stmt.getString("folder_path"));
                    bean.setForm_mapping_id(stmt.getString("form_mapping_id"));
                    String show_data_id = stmt.getString("show_data_id");

                    String count ="";
                    String query1 = " select count(*) as count from drive_folder where show_data_id ='" + show_data_id + "' and active = 'Y' ;";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    ResultSet stmt1 = pstmt1.executeQuery();
                    while (stmt1.next()) {
                        count = stmt1.getString("count");

                    }
                    bean.setFileCheck(count);
                    bean.setShow_data_id(show_data_id );
                    bean.setType_name(stmt.getString("type_name"));
                    bean.setSubtype_name(stmt.getString("subtype_name"));
                    bean.setForm_name(stmt.getString("form_name"));
                    bean.setRemark(stmt.getString("remark"));
                    formDetails.add(bean);
                }

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return formDetails;
    }

    public int checkUploaded(String show_data_id) {

        int count = 0;

        String query = " select count(*) as count from drive_folder where show_data_id ='" + show_data_id + "' and active = 'Y' ;";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                count = stmt.getInt("count");

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return count;
    }

    public DriveBean getFileData(String show_data_id) {

        DriveBean bean = new DriveBean();

        String query = "select f.form_name,cs.subtype_name,sd.column_values,sd.folder_path  "
                + "from  show_data sd "
                + "inner join form_mapping  fm on fm.form_mapping_id=sd.form_mapping_id "
                + "inner join column_subtype cs on cs.column_subtype_id=fm.column_subtype_id "
                + "inner join form f on f.form_id=fm.form_id where  sd.show_data_id='" + show_data_id + "' ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {

                bean.setFile_name(stmt.getString("column_values"));
                bean.setFolder_path(stmt.getString("folder_path"));
                bean.setSubtype_name(stmt.getString("subtype_name"));
                bean.setForm_name(stmt.getString("form_name"));

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return bean;
    }

    public String getFileUrl(String show_data_id) {

        String url = null;
        String query = "select dfl.file_name "
                + "from drive_folder dfr "
                + "inner join drive_file dfl on dfl.drive_folder_id=dfr.drive_folder_id "
                + "where dfr.show_data_id='" + show_data_id + "'";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {

                url = stmt.getString("file_name");

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return url;
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

    public String uploadfile(String project_name, String report_name, String report_type, String report_data, String byte_arr, String file_name, String file_type) throws Exception {

        String baseiname = "";

        String url = "http://120.138.10.146:8080/ReportModule/web1API/googleReport/getAllDataRequiredInGoogleSheet";
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-type", "application/json");
        String urlParameters = "{\"project_name\":\"" + project_name + "\",\"report_name\":\"" + report_name + "\",\"report_type\":\"" + report_type + "\",\"report_data\":[],\"file\":[{\"byte_arr\":\"" + byte_arr + "\",\"file_name\":\"" + file_name + "\",\"file_type\":\"" + file_type + "\"}]}";
        httpClient.setDoOutput(
                true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = httpClient.getResponseCode();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);

            }
            return baseiname = response.toString();
            //print result
            // System.out.println("data" : +baseiname);

        }

    }

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
