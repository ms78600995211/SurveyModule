/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.fileType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author lENOVO
 */
public class FileTypeModel {

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

    public String saveFileType(String fileExtension, String fileCategory, String remark, String log_in_person) {
        int rowsAffected = 0;
        String file_type_id = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "insert into file_type(file_extension, file_category, remark, created_by) values(?,?,?,?);";
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, fileExtension);
            pstmt.setString(2, fileCategory);
            pstmt.setString(3, remark);
            pstmt.setString(4, log_in_person);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                connection.commit();
                rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    file_type_id = Integer.toString(rs.getInt(1));
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
        return file_type_id;
    }

    public List<FileTypeBean> getAllFileType() {
        List<FileTypeBean> FileTypeListData = new ArrayList<>();

        String query = " select file_type_id, file_extension, file_category, remark "
                + " FROM file_type "
                + " WHERE active = 'Y' order by file_type_id desc ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                FileTypeBean fileType = new FileTypeBean();
                fileType.setFile_type_id(stmt.getInt("file_type_id"));
                fileType.setFile_extension(stmt.getString("file_extension"));
                fileType.setFile_category(stmt.getString("file_category"));
                fileType.setRemark(stmt.getString("remark"));

                FileTypeListData.add(fileType);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return FileTypeListData;
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
