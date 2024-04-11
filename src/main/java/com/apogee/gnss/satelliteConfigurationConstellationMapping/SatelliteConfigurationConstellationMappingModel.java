/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.satelliteConfigurationConstellationMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SatelliteConfigurationConstellationMappingModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveSatelliteConfiguConstellationMappingData(String satellite_configuration_id, List constellation_ids, String name, String remark) {
        int[] affectedRows = null;
        PreparedStatement pstmt = null;

        try {
            connection.setAutoCommit(false);

            String query = "insert into satellite_configuration_constellation_mapping(satellite_configuration_id,constellation_id,created_by,active,remark,revision_no) values(?,?,?,?,?,?);";
            pstmt = connection.prepareStatement(query);

            for (int i = 0; i < constellation_ids.size(); i++) {

                pstmt.setString(1, satellite_configuration_id);
                pstmt.setString(2, constellation_ids.get(i).toString());
                pstmt.setString(3, name);
                pstmt.setString(4, "Y");
                pstmt.setString(5, remark);
                pstmt.setInt(6, 0);
                pstmt.addBatch();

            }

            affectedRows = pstmt.executeBatch();

            if (affectedRows.length == constellation_ids.size()) {
                connection.commit();
                System.out.println("Batch insert completed successfully.");

                message = "data inserted successfully...";
                color = COLOR_OK;

            } else {
                connection.rollback();
                message = "something errors occurs..";
                color = COLOR_ERROR;
            }

        } catch (Exception e) {

            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.saveSatelliteConfiguConstellationMappingData()" + e);
        }
    }

    public List getSatelliteConfigConstnMappingList(String satellite_configuration_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<SatelliteConfigurationConstellationMappingBean> getSatelliteConfigConstnMappingList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String query = "select distinct sc.satellite_configuration_name,sc.satellite_configuration_id\n"
                + "from satellite_configuration_constellation_mapping scm \n"
                + "join satellite_configuration sc on sc.satellite_configuration_id=scm.satellite_configuration_id where scm.active='Y' ";

        builder.append(query);
        String qry = "and  scm.satellite_configuration_id='" + satellite_configuration_id + "'";

        if (satellite_configuration_id != null && !satellite_configuration_id.isEmpty()) {
            builder.append(qry);
        }

        try {
            pstmt = connection.prepareStatement(builder.toString());
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                SatelliteConfigurationConstellationMappingBean bean = new SatelliteConfigurationConstellationMappingBean();
                bean.setSatellite_configuration_name(stmt.getString("satellite_configuration_name"));
                String query1 = "select cns.constellation_name from satellite_configuration_constellation_mapping scm \n"
                        + "join constellation  cns on cns.constellation_id=scm.constellation_id "
                        + "where scm.satellite_configuration_id='" + stmt.getString("satellite_configuration_id") + "'";;

                PreparedStatement pstmt1 = connection.prepareStatement(query1);
                ResultSet rs = pstmt1.executeQuery();
                List list = new ArrayList();
                while (rs.next()) {
                    list.add(rs.getString("constellation_name"));
                }
                bean.setAll_constellation(list);
                getSatelliteConfigConstnMappingList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.getSatelliteConfigConstnMappingList()" + e);
        }
        return getSatelliteConfigConstnMappingList;
    }

    public List getConstellationList() {
        List<SatelliteConfigurationConstellationMappingBean> getconstellationList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select constellation_id,constellation_name from constellation where active='Y' ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                SatelliteConfigurationConstellationMappingBean bean = new SatelliteConfigurationConstellationMappingBean();
                bean.setConstellation_id(stmt.getString("constellation_id"));
                bean.setConstellation_name(stmt.getString("constellation_name"));
                getconstellationList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.getConstellationList()" + e);
        }
        return getconstellationList;
    }

    public List getSatellite_configurationList() {
        List<SatelliteConfigurationConstellationMappingBean> getSatellite_configurationList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select satellite_configuration_id,satellite_configuration_name from satellite_configuration where active='Y' ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                SatelliteConfigurationConstellationMappingBean bean = new SatelliteConfigurationConstellationMappingBean();
                bean.setSatellite_configuration_id(stmt.getString("satellite_configuration_id"));
                bean.setSatellite_configuration_name(stmt.getString("satellite_configuration_name"));
                getSatellite_configurationList.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.getSatellite_configurationList()" + e);
        }
        return getSatellite_configurationList;
    }

    public List allSatellite_configuration() {
        List<SatelliteConfigurationConstellationMappingBean> allSatellite_configuration = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        String query = "select satellite_configuration_id,satellite_configuration_name from satellite_configuration where active='Y' ";

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();

            while (stmt.next()) {
                SatelliteConfigurationConstellationMappingBean bean = new SatelliteConfigurationConstellationMappingBean();
                bean.setSatellite_configuration_id(stmt.getString("satellite_configuration_id"));
                bean.setSatellite_configuration_name(stmt.getString("satellite_configuration_name"));
                allSatellite_configuration.add(bean);
            }

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.allSatellite_configuration()" + e);
        }
        return allSatellite_configuration;
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
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingModel.closeConnection()" + e);
        }
    }

}
