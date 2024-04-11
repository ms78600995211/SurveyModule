/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.satelliteConfiguration;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "SatelliteConfigurationController", urlPatterns = {"/SatelliteConfigurationController"})
public class SatelliteConfigurationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SatelliteConfigurationModel model = new SatelliteConfigurationModel();
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String satelliteConfigurationName = request.getParameter("satelliteConfigurationName");
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String satellite_configuration_name = request.getParameter("satellite_configuration_name");
                String defaultConfig = (request.getParameter("defaultConfig") != null) ? request.getParameter("defaultConfig") : "Y";
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveSatellite_configuration(satellite_configuration_name,defaultConfig, remark, name);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("satelliteConfigurationName", satelliteConfigurationName);
            request.setAttribute("getSatelliteConfigList", model.getSatelliteConfigList(satelliteConfigurationName));
            request.setAttribute("allSatelliteConfig", model.allSatelliteConfig());
            model.closeConnection();
            request.getRequestDispatcher("/satellite_configuration").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.satelliteConfiguration.SatelliteConfigurationController.doGet()" + e);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
