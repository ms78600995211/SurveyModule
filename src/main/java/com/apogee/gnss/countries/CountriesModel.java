/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.countries;

import com.apogee.gnss.continents.ContinentsBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class CountriesModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveCountries(String countryName, int continentId, String remark, String log_in_person) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;
        try {
            if (!countryName.trim().isEmpty()) {
                String query = "insert into countries(country_name, continent_id, remark, created_by) values(?,?,?,?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, countryName);
                pstmt.setInt(2, continentId);
                pstmt.setString(3, remark);
                pstmt.setString(4, log_in_person);
                rowsAffected = pstmt.executeUpdate();
            }
            if (rowsAffected > 0) {
                message = "Data Added Successfully.";
                color = COLOR_OK;
            } else {
                message = "Some Error Try Again.";
                color = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.CountriesModel.saveCountries()" + e);
        }
    }

    public List<ContinentsBean> getAllContinents() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ContinentsBean> getAllContinents = new ArrayList<>();
        String query = " select continent_id, continent_name "
                + " FROM continents "
                + " WHERE active = 'Y' order by continent_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ContinentsBean continents = new ContinentsBean();
                continents.setContinent_id(stmt.getInt("continent_id"));
                continents.setContinent_name(stmt.getString("continent_name"));
                getAllContinents.add(continents);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.CountriesModel.getAllContinents()" + e);
        }
        return getAllContinents;
    }

    public List<CountriesBean> allCountries() {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<CountriesBean> allCountries = new ArrayList<>();
        String query = " select country_id, country_name "
                + " FROM countries "
                + " WHERE active = 'Y' order by country_id desc ";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                CountriesBean bean = new CountriesBean();
                bean.setCountry_id(stmt.getInt("country_id"));
                bean.setCountry_name(stmt.getString("country_name"));
                allCountries.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.CountriesModel.allCountries()" + e);
        }
        return allCountries;
    }

    public List<CountriesBean> getAllCountries(String country_id) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<CountriesBean> getAllCountries = new ArrayList<>();
        String query = "SELECT cnt.country_id, cnt.country_name, cont.continent_name, cnt.remark "
                + "FROM countries cnt JOIN continents cont ON cont.continent_id = cnt.continent_id "
                + "WHERE cnt.active = 'Y'";
        if (country_id != null && !country_id.isEmpty()) {
            query += " AND cnt.country_id = '" + country_id + "'";
        }

        query += " ORDER BY cnt.country_id DESC;";
        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                CountriesBean countries = new CountriesBean();
                countries.setCountry_id(stmt.getInt("country_id"));
                countries.setCountry_name(stmt.getString("country_name"));
                countries.setContinent_name(stmt.getString("continent_name"));
                countries.setRemark(stmt.getString("remark"));
                getAllCountries.add(countries);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.CountriesModel.getAllCountries()" + e);
        }
        return getAllCountries;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.model.CountriesModel.closeConnection()" + e);
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
