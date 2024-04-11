/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.surveyConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SurveyConfigurationModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public String getProjectionParam_id(String zone_name, String origin_lat, String origin_lng, String scale,
            String false_easting, String false_northing, String paralell_1, String paralell_2, String projectiontype_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select projectionParam_id from projectionparameters where zone_name= ? and origin_lat= ? and origin_lng= ? and scale= ?"
                + "and false_easting= ? and false_northing= ? and paralell_1= ? and paralell_2= ? and projectiontype_id= ? and active='Y' ";
        String projectionParam_id = "";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, zone_name);
            pstmt.setString(2, origin_lat);
            pstmt.setString(3, origin_lng);
            pstmt.setString(4, scale);
            pstmt.setString(5, false_easting);
            pstmt.setString(6, false_northing);
            pstmt.setString(7, paralell_1);
            pstmt.setString(8, paralell_2);
            pstmt.setString(9, projectiontype_id);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                projectionParam_id = stmt.getString("projectionParam_id");
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getProjectionParam_id()" + e);
        }
        return projectionParam_id;
    }

    public void saveSurvey_configurationData(String survey_configuration_name, String datum_id, String zonedata_id, String elevationtype_id, String distanceunit_id, String angleunit_id, String projectionParam_id,String defaultConfig, String name, String remark) {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            String query = "insert into survey_configuration(survey_configuration_name,datum_id,zonedata_id,elevationtype_id,distanceunit_id,angleunit_id,projectionParam_id,defaultConfig,created_by,active,remark,revision_no) values(?,?,?,?,?,?,?,?,?,?,?,?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, survey_configuration_name);
            pstmt.setString(2, datum_id);
            pstmt.setString(3, zonedata_id);
            pstmt.setString(4, elevationtype_id);
            pstmt.setString(5, distanceunit_id);
            pstmt.setString(6, angleunit_id);
            pstmt.setString(7, projectionParam_id);
            pstmt.setString(8, defaultConfig);
            pstmt.setString(9, name);
            pstmt.setString(10, "Y");
            pstmt.setString(11, remark);
            pstmt.setInt(12, 0);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.saveSurvey_configurationData()" + e);
        }
    }

    public List<SurveyConfigurationBean> getZone_nameList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getZone_nameList = new ArrayList<>();
        String query = "select projectionParam_id,zone_name from projectionparameters where active='Y' order by projectionParam_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setProjectionParam_id(stmt.getString("projectionParam_id"));
                bean.setZone_name(stmt.getString("zone_name"));
                getZone_nameList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getZone_nameList()" + e);
        }
        return getZone_nameList;
    }

    public List<SurveyConfigurationBean> getprojectionparameters(String zone_name, String origin_lat, String origin_lng, String scale, String false_easting, String false_northing, String paralell_1, String paralell_2) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getprojectionparameters = new ArrayList<>();
        String query = "select  zone_name,origin_lat,origin_lng,scale,false_easting,false_northing,paralell_1,paralell_2,misc1,misc2,misc3,misc4,projectiontype_id from projectionparameters  where active='Y'  ";
        if (zone_name != null && !zone_name.isEmpty()) {
            query += "AND zone_name = ?";
        }
        if (origin_lat != null && !origin_lat.isEmpty()) {
            query += "AND origin_lat = ?";
        }
        if (origin_lng != null && !origin_lng.isEmpty()) {
            query += "AND origin_lng = ?";
        }
        if (scale != null && !scale.isEmpty()) {
            query += "AND scale = ?";
        }
        if (false_easting != null && !false_easting.isEmpty()) {
            query += "AND false_easting = ?";
        }
        if (false_northing != null && !false_northing.isEmpty()) {
            query += "AND false_northing = ?";
        }
        if (paralell_1 != null && !paralell_1.isEmpty()) {
            query += "AND paralell_1 = ?";
        }
        if (paralell_2 != null && !paralell_2.isEmpty()) {
            query += "AND paralell_2 = ?";
        }
        query += "  order by projectionParam_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            int parameterIndex = 1;
            if (zone_name != null && !zone_name.isEmpty()) {
                pstmt.setString(parameterIndex++, zone_name);
            }
            if (origin_lat != null && !origin_lat.isEmpty()) {
                pstmt.setString(parameterIndex++, origin_lat);
            }
            if (origin_lng != null && !origin_lng.isEmpty()) {
                pstmt.setString(parameterIndex++, origin_lng);
            }
            if (scale != null && !scale.isEmpty()) {
                pstmt.setString(parameterIndex++, scale);
            }
            if (false_easting != null && !false_easting.isEmpty()) {
                pstmt.setString(parameterIndex++, false_easting);
            }
            if (false_northing != null && !false_northing.isEmpty()) {
                pstmt.setString(parameterIndex++, false_northing);
            }
            if (paralell_1 != null && !paralell_1.isEmpty()) {
                pstmt.setString(parameterIndex++, paralell_1);
            }
            if (paralell_2 != null && !paralell_2.isEmpty()) {
                pstmt.setString(parameterIndex++, paralell_2);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setZone_name(stmt.getString("zone_name"));
                bean.setOrigin_lat(stmt.getString("origin_lat"));
                bean.setOrigin_lng(stmt.getString("origin_lng"));
                bean.setScale(stmt.getString("scale"));
                bean.setFalse_easting(stmt.getString("false_easting"));
                bean.setFalse_northing(stmt.getString("false_northing"));
                bean.setParalell_1(stmt.getString("paralell_1"));
                bean.setParalell_2(stmt.getString("paralell_2"));
                String projectiontype_id = stmt.getString("projectiontype_id");
                query = " SELECT projectionType  FROM projectiontype  WHERE   projectiontype_id =" + projectiontype_id + " ";
                pstmt = connection.prepareStatement(query);
                stmt = pstmt.executeQuery();
                String projectionType = "";
                while (stmt.next()) {
                    projectionType = stmt.getString("projectionType");
                }
                bean.setProjectiontype_id(projectiontype_id);
                bean.setProjectionType(projectionType);
                getprojectionparameters.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getprojectionparameters()" + e);
        }
        return getprojectionparameters;
    }

    public List getSurveyConfigurationList(String angleunit_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getSurveyConfigurationList = new ArrayList<>();
        String query = "select sc.survey_configuration_name,d.name,zd.zone,et.elevationType,du.disUnit_name,au.angUnit_name,pp.zone_name,pp.origin_lat,pp.origin_lng,pp.scale,\n"
                + "pp.paralell_1,pp.false_easting,pp.false_northing,pp.paralell_2,pt.projectionType,sc.defaultConfig, sc.config_time,sc.remark\n"
                + "from survey_configuration sc\n"
                + "inner join datum d on  d.datum_id=sc.datum_id\n"
                + "inner join zonedata zd on zd.zonedata_id=sc.zonedata_id\n"
                + "inner join elevationtype et on et.elevationtype_id=sc.elevationtype_id\n"
                + "inner join distanceunit du on du.distanceunit_id=sc.distanceunit_id\n"
                + "inner join angleunit au on au.angleunit_id=sc.angleunit_id\n"
                + "inner join projectionparameters pp on pp.projectionParam_id=sc.projectionParam_id\n"
                + "inner join projectiontype pt on pt.projectiontype_id=pp.projectiontype_id where sc.active='Y' ";
        if (angleunit_id != null && !angleunit_id.isEmpty()) {
            query += " and au.angleunit_id= ?";
        }
        query += "order by sc.survey_configuration_id desc";
        try {
            pstmt = connection.prepareStatement(query);
            if (angleunit_id != null && !angleunit_id.isEmpty()) {
                pstmt.setString(1, angleunit_id);
            }
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setSurvey_configuration_name(stmt.getString("survey_configuration_name"));
                bean.setDatum_name(stmt.getString("name"));
                bean.setZone(stmt.getString("zone"));
                bean.setElevationType(stmt.getString("elevationType"));
                bean.setDisUnit_name(stmt.getString("disUnit_name"));
                bean.setAngUnit_name(stmt.getString("angUnit_name"));
                bean.setZone_name(stmt.getString("zone_name"));
                bean.setOrigin_lat(stmt.getString("origin_lat"));
                bean.setOrigin_lng(stmt.getString("origin_lng"));
                bean.setScale(stmt.getString("scale"));
                bean.setFalse_easting(stmt.getString("false_easting"));
                bean.setFalse_northing(stmt.getString("false_northing"));
                bean.setParalell_1(stmt.getString("paralell_1"));
                bean.setParalell_2(stmt.getString("paralell_2"));
                bean.setProjectionType(stmt.getString("projectionType"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setConfig_time(stmt.getString("config_time"));
                bean.setRemark(stmt.getString("remark"));
                getSurveyConfigurationList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getSurveyConfigurationList()" + e);
        }
        return getSurveyConfigurationList;
    }

    public List getDatumnList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getDatumnList = new ArrayList<>();
        String query = "select datum_id,name from datum where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setDatum_id(stmt.getString("datum_id"));
                bean.setDatum_name(stmt.getString("name"));
                getDatumnList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getDatumnList()" + e);
        }
        return getDatumnList;
    }

    public List getZonedataList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getZonedataList = new ArrayList<>();
        String query = "select zonedata_id,zone from zonedata where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setZonedata_id(stmt.getString("zonedata_id"));
                bean.setZone(stmt.getString("zone"));
                getZonedataList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getZonedataList()" + e);
        }
        return getZonedataList;
    }

    public List getElevationtypeList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getElevationtypeList = new ArrayList<>();
        String query = "select elevationtype_id,elevationType from elevationtype where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setElevationtype_id(stmt.getString("elevationtype_id"));
                bean.setElevationType(stmt.getString("elevationType"));
                getElevationtypeList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getElevationtypeList()" + e);
        }
        return getElevationtypeList;
    }

    public List getdistanceunitList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getdistanceunitList = new ArrayList<>();
        String query = "select distanceunit_id,disUnit_name from distanceunit where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setDistanceunit_id(stmt.getString("distanceunit_id"));
                bean.setDisUnit_name(stmt.getString("disUnit_name"));
                getdistanceunitList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getdistanceunitList()" + e);
        }
        return getdistanceunitList;
    }

    public List getAngleunitList() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SurveyConfigurationBean> getAngleunitList = new ArrayList<>();
        String query = "select DISTINCT angleunit_id,angUnit_name from angleunit where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setAngleunit_id(stmt.getString("angleunit_id"));
                bean.setAngUnit_name(stmt.getString("angUnit_name"));
                getAngleunitList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.getAngleunitList()" + e);
        }
        return getAngleunitList;
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
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationModel.closeConnection()" + e);
        }
    }

}
