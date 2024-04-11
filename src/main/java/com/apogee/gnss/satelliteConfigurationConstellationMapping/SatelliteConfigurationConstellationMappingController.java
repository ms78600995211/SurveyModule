/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.satelliteConfigurationConstellationMapping;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "SatelliteConfigurationConstellationMappingController", urlPatterns = {"/SatelliteConfigurationConstellationMappingController"})
public class SatelliteConfigurationConstellationMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SatelliteConfigurationConstellationMappingModel model = new SatelliteConfigurationConstellationMappingModel();
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
            String message = "";
            String msgbgcolor = "";
            List selectedConstellations = new ArrayList();

            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String satellite_configurationId = request.getParameter("satellite_configurationId");
            request.setAttribute("satellite_configurationId", satellite_configurationId);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String satellite_configuration_id = request.getParameter("satellite_configuration_id");

                List<SatelliteConfigurationConstellationMappingBean> constellationList = model.getConstellationList();
                for (int i = 0; i < constellationList.size(); i++) {

                    String selectedConstellation_status = request.getParameter(constellationList.get(i).getConstellation_name());

                    if (selectedConstellation_status == null) {

                        selectedConstellation_status = "";

                    } else if (selectedConstellation_status.equalsIgnoreCase("on")) {
                        selectedConstellations.add(constellationList.get(i).getConstellation_id());

                    }

                }

                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveSatelliteConfiguConstellationMappingData(satellite_configuration_id, selectedConstellations, name, remark);
                message = model.getMessage();
                msgbgcolor = model.getColor();
            }
            request.setAttribute("message", message);
            request.setAttribute("msgbgcolor", msgbgcolor);
            request.setAttribute("getConstellationList", model.getConstellationList());
            request.setAttribute("getSatellite_configurationList", model.getSatellite_configurationList());
            request.setAttribute("getSatelliteConfigConstnMappingList", model.getSatelliteConfigConstnMappingList(satellite_configurationId));
            request.setAttribute("allSatellite_configuration", model.allSatellite_configuration());

            model.closeConnection();
            request.getRequestDispatcher("/satellite_configuration_constellation_mapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.configuration.SatelliteConfigurationConstellationMappingController.doGet()" + e);
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
