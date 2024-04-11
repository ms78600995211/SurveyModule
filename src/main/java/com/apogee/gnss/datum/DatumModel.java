/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.datum;

import com.apogee.gnss.countries.CountriesBean;
import com.apogee.gnss.datumType.DatumTypeBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class DatumModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveDatum(DatumBean bean, String log_in_person) {
        int rowsAffected = 0;
        PreparedStatement psmt = null;
        try {
            if (!bean.getName().trim().isEmpty() && !bean.getCommand().trim().isEmpty() && !bean.getMajor_axis().trim().isEmpty() && !bean.getFlattening().trim().isEmpty() && !bean.getX_axis_shift().trim().isEmpty() && !bean.getY_axis_shift().trim().isEmpty() && !bean.getZ_axis_shift().trim().isEmpty() && !bean.getRot_x_axis().trim().isEmpty() && !bean.getRot_y_axis().trim().isEmpty() && !bean.getRot_z_axis().trim().isEmpty() && !bean.getScale().trim().isEmpty()) {
                String query = " insert into datum(name,command,major_axis,flattening,x_axis_shift,y_axis_shift,z_axis_shift,rot_x_axis, "
                        + "  rot_y_axis,rot_z_axis,scale,country_id,datumType_id,remark,created_by) "
                        + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                psmt = connection.prepareStatement(query);
                psmt.setString(1, bean.getName());
                psmt.setString(2, bean.getCommand());
                psmt.setString(3, bean.getMajor_axis());
                psmt.setString(4, bean.getFlattening());
                psmt.setString(5, bean.getX_axis_shift());
                psmt.setString(6, bean.getY_axis_shift());
                psmt.setString(7, bean.getZ_axis_shift());
                psmt.setString(8, bean.getRot_x_axis());
                psmt.setString(9, bean.getRot_y_axis());
                psmt.setString(10, bean.getRot_z_axis());
                psmt.setString(11, bean.getScale());
                psmt.setInt(12, bean.getCountry_id());
                psmt.setInt(13, bean.getDatumType_id());
                psmt.setString(14, bean.getRemark());
                psmt.setString(15, log_in_person);
                rowsAffected = psmt.executeUpdate();
            }
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;

            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumModel.saveDatum()" + e);
        }
    }

    public List<CountriesBean> getAllCountries() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<CountriesBean> getAllCountries = new ArrayList<>();
        String query = " select country_id, country_name, continent_id "
                + " FROM countries "
                + " WHERE active = 'Y' order by country_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                CountriesBean countries = new CountriesBean();
                countries.setCountry_id(stmt.getInt("country_id"));
                countries.setCountry_name(stmt.getString("country_name"));
                getAllCountries.add(countries);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumModel.getAllCountries()" + e);
        }
        return getAllCountries;
    }

    public List<DatumTypeBean> getDatumType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DatumTypeBean> getDatumType = new ArrayList<>();
        String query = " select datumtype_id, datumType_name "
                + " FROM datumtype "
                + " WHERE active = 'Y' order by datumtype_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DatumTypeBean datumType = new DatumTypeBean();
                datumType.setDatumtype_id(stmt.getInt("datumtype_id"));
                datumType.setDatumType_name(stmt.getString("datumType_name"));
                getDatumType.add(datumType);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumModel.getDatumType()" + e);
        }
        return getDatumType;
    }

    public List<DatumTypeBean> allDatumType() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<DatumTypeBean> allDatumType = new ArrayList<>();
        String query = " select datumtype_id, datumType_name "
                + " FROM datumtype "
                + " WHERE active = 'Y' order by datumtype_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                DatumTypeBean datumType = new DatumTypeBean();
                datumType.setDatumtype_id(stmt.getInt("datumtype_id"));
                datumType.setDatumType_name(stmt.getString("datumType_name"));
                allDatumType.add(datumType);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumModel.allDatumType()" + e);
        }
        return allDatumType;
    }

    public List<DatumBean> getDatumList(String datumType_id) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<DatumBean> list = new ArrayList<>();
        try {
            String qry = "SELECT d.datum_id, d.name, d.command, d.major_axis, d.flattening, d.x_axis_shift, "
                    + "d.y_axis_shift, d.z_axis_shift, d.rot_x_axis, d.rot_y_axis, d.rot_z_axis, d.scale, "
                    + "c.country_name, dt.datumType_name, d.remark "
                    + "FROM datum d, countries c, datumtype dt "
                    + "WHERE d.active='Y' AND c.active='Y' AND dt.active='Y' "
                    + "AND d.datumType_id=dt.datumtype_id AND d.country_id=c.country_id ";
            if (datumType_id != null && !datumType_id.isEmpty()) {
                qry += "AND d.datumType_id='" + datumType_id + "'";
            }
            qry += " ORDER BY d.datum_id DESC";
            psmt = connection.prepareStatement(qry);
            rs = psmt.executeQuery();
            while (rs.next()) {
                DatumBean bean = new DatumBean();
                bean.setDatum_id(rs.getInt("datum_id"));
                bean.setName(rs.getString("name"));
                bean.setCommand(rs.getString("command"));
                bean.setMajor_axis(rs.getString("major_axis"));
                bean.setFlattening(rs.getString("flattening"));
                bean.setX_axis_shift(rs.getString("x_axis_shift"));
                bean.setY_axis_shift(rs.getString("y_axis_shift"));
                bean.setZ_axis_shift(rs.getString("z_axis_shift"));
                bean.setRot_x_axis(rs.getString("rot_x_axis"));
                bean.setRot_y_axis(rs.getString("rot_y_axis"));
                bean.setRot_z_axis(rs.getString("rot_z_axis"));
                bean.setScale(rs.getString("scale"));
                bean.setCountryName(rs.getString("country_name"));
                bean.setDatumType(rs.getString("datumType_name"));
                bean.setRemark(rs.getString("remark"));
                list.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumModel.getDatumList()" + e);
        }
        return list;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.DatumModel.closeConnection()" + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
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

}
