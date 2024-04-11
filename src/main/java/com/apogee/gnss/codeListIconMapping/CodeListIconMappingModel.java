/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.codeListIconMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CodeListIconMappingModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public List<CodeListIconMappingBean> getParentList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<CodeListIconMappingBean> codeListData = new ArrayList<>();
        String query = " select code_list_id, title  FROM code_list "
                + " WHERE is_super_child ='N' and active = 'Y' order by code_list_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                CodeListIconMappingBean codelistdetail = new CodeListIconMappingBean();
                codelistdetail.setCode_list_id(stmt.getInt("code_list_id"));
                codelistdetail.setCode(stmt.getString("title"));
                codeListData.add(codelistdetail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingModel.getParentList()" + e);
        }
        return codeListData;
    }

    public int get_generation(String parent_id) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int generation = 0;
        try {
            String query = " SELECT generation FROM code_list WHERE code_list_id =" + parent_id + "";
            pstmt = (PreparedStatement) connection.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                generation = rset.getInt("generation");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingModel.get_generation()" + e);
        }
        return generation;
    }

    public List<CodeListIconMappingBean> getcodeListData(String generation) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<CodeListIconMappingBean> codeListData = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(" select *  FROM code_list WHERE active = 'Y' ");
        String qry = " and generation='" + generation + "'";
        if (generation != null && !generation.isEmpty()) {
            queryBuilder.append(qry);
        }
        queryBuilder.append("order by code_list_id desc ");
        try {
            pstmt = connection.prepareStatement(queryBuilder.toString());
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                CodeListIconMappingBean codelistdetail = new CodeListIconMappingBean();
                codelistdetail.setCode_list_id(stmt.getInt("code_list_id"));
                codelistdetail.setCode(stmt.getString("title"));
                int parent_id = stmt.getInt("parent_id");
                String query1 = " SELECT title  FROM code_list  WHERE   code_list_id =" + parent_id + " ";
                PreparedStatement pstmt1 = connection.prepareStatement(query1);
                ResultSet stmt1 = pstmt1.executeQuery();
                String parentcode = "";
                while (stmt1.next()) {
                    parentcode = stmt1.getString("title");
                }
                codelistdetail.setParent_code(parentcode);
                codelistdetail.setGeneration(stmt.getInt("generation"));
                codelistdetail.setIs_super_child(stmt.getString("is_super_child"));
                codelistdetail.setPrefix(stmt.getString("prefix"));
                codelistdetail.setRemark(stmt.getString("remark"));
                String code_list_id = stmt.getString("code_list_id");
                String query2 = " SELECT icon_mapping_id  FROM icon_mapping  WHERE   code_list_id =" + code_list_id + " ";
                pstmt = connection.prepareStatement(query2);
                stmt1 = pstmt.executeQuery();
                int file_id = 0;
                while (stmt1.next()) {
                    file_id = stmt1.getInt("icon_mapping_id");
                }
                codelistdetail.setImage_id(file_id);
                codeListData.add(codelistdetail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingModel.getcodeListData()" + e);
        }
        return codeListData;
    }

    public List<CodeListIconMappingBean> getTitlesData() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<CodeListIconMappingBean> getTitlesData = new ArrayList<>();
        String query = " select distinct  generation from code_list where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                CodeListIconMappingBean codelistdetail = new CodeListIconMappingBean();
                codelistdetail.setGeneration(stmt.getInt("generation"));
                getTitlesData.add(codelistdetail);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingModel.getTitlesData()" + e);
        }
        return getTitlesData;
    }

    public void saveCodeDetails(String title, String parent_id, int generation, String isSuperChild, String prefix, String image_name, String image_path, String created_by, String remarks) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        int rowsAffected1 = 0;
        String Query = "INSERT INTO code_list (title, parent_id, generation, is_super_child,prefix,active,revision_no,created_by,remark) "
                + " VALUES(?, ?, ?, ?,?,?,?,?,?)";
        try {
            if (!title.trim().isEmpty() && !prefix.trim().isEmpty()) {
                connection.setAutoCommit(false);
                pstmt = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, title);
                pstmt.setString(2, parent_id);
                pstmt.setInt(3, generation);
                pstmt.setString(4, isSuperChild);
                pstmt.setString(5, prefix);
                pstmt.setString(6, "Y");
                pstmt.setInt(7, 0);
                pstmt.setString(8, created_by);
                pstmt.setString(9, remarks);
                rowsAffected = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    rowsAffected = rs.getInt(1);
                }
                if (rowsAffected > 0) {
                    if (!image_name.isEmpty() || image_name == null) {
                        String query = "insert into icon_mapping(code_list_id,image_name,image_path,active,revision_no,remark,created_by) values(?,?,?,?,?,?,?);";
                        pstmt = connection.prepareStatement(query);
                        pstmt.setInt(1, rowsAffected);
                        pstmt.setString(2, image_name);
                        pstmt.setString(3, image_path);
                        pstmt.setString(4, "Y");
                        pstmt.setInt(5, 0);
                        pstmt.setString(6, remarks);
                        pstmt.setString(7, created_by);
                        rowsAffected1 = pstmt.executeUpdate();
                    }
                }
                if (rowsAffected > 0 && rowsAffected1 > 0) {
                    message = "Data Added Successfully.";
                    color = COLOR_OK;
                    connection.commit();
                } else {
                    message = "Some Error Try Again.";
                    color = COLOR_ERROR;
                    connection.rollback();
                }
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingModel.saveCodeDetails()" + e);
        }
    }

    public String getImagePath(String image_id) {
        ResultSet rset = null;
        String image_path = "";
        String query = " SELECT image_path  FROM icon_mapping  WHERE   icon_mapping_id =" + image_id + " ";
        try {
            rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                image_path = rset.getString("image_path");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingModel.getImagePath()" + e);
        }
        return image_path;
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

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingModel.closeConnection()" + e);
        }
    }

}
