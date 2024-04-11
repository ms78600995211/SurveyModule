/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.miscellaneousConfiguration;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
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
@WebServlet(name = "MiscellaneousConfigurationController", urlPatterns = {"/MiscellaneousConfigurationController"})
public class MiscellaneousConfigurationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MiscellaneousConfigurationModel model = new MiscellaneousConfigurationModel();
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
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String miscConfig_name = request.getParameter("miscConfig_name");
                String position_type = (!request.getParameter("position_type").isEmpty()) ? request.getParameter("position_type") : "RTL Fix";
                String min_no_of_satellite = (!request.getParameter("min_no_of_satellite").isEmpty()) ? request.getParameter("min_no_of_satellite") : "3";
                String age_of_correction = (!request.getParameter("age_of_correction").isEmpty()) ? request.getParameter("age_of_correction") : "2";
                String pdop_tolerance = (!request.getParameter("pdop_tolerance").isEmpty()) ? request.getParameter("pdop_tolerance") : "3";
                String horizontal_percision = (!request.getParameter("horizontal_percision").isEmpty()) ? request.getParameter("horizontal_percision") : "0.4";
                String vertical_percision = (!request.getParameter("vertical_percision").isEmpty()) ? request.getParameter("vertical_percision") : "0.8";
                String check_validity_of_point_taken = (request.getParameter("check_validity_of_point_taken").equals("F")) ? "0" : "1";
                String observation_time_per_point = (!request.getParameter("observation_time_per_point").isEmpty()) ? request.getParameter("observation_time_per_point") : "10";
                String show_point_name = (request.getParameter("show_point_name").equals("F")) ? "0" : "1";
                String show_code_name = (request.getParameter("show_code_name").equals("F")) ? "0" : "1";
                String stake_horizontal_percision = (!request.getParameter("stake_horizontal_percision").isEmpty()) ? request.getParameter("stake_horizontal_percision") : "0.4";
                String stake_vertical_percision = (!request.getParameter("stake_vertical_percision").isEmpty()) ? request.getParameter("stake_vertical_percision") : "0.8";
                String stake_target_distance = (!request.getParameter("stake_target_distance").isEmpty()) ? request.getParameter("stake_target_distance") : "4";
                String inner_circle_radius = (!request.getParameter("inner_circle_radius").isEmpty()) ? request.getParameter("inner_circle_radius") : "0.5";
                String outer_circle_radius = (!request.getParameter("outer_circle_radius").isEmpty()) ? request.getParameter("outer_circle_radius") : "1";
                String auto_connect_last_connected_reciever = (!request.getParameter("auto_connect_last_connected_reciever").equals("F")) ? "1": "0";
                String automatically_send_last_working_profile = (!request.getParameter("automatically_send_last_working_profile").equals("F")) ? "1": "0";
                String enable_compass = (!request.getParameter("enable_compass").equals("F")) ? "1" : "0";
                String enable_e_bubble = (!request.getParameter("enable_e_bubble").equals("F")) ? "1" : "0";
                String enable_lrf = (!request.getParameter("enable_lrf").equals("F")) ? "1" : "0";
                String defaultConfig = (request.getParameter("defaultConfig") != null) ? request.getParameter("defaultConfig") : "Y";
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                LinkedHashMap<Integer, String> allparameters = new LinkedHashMap();
                allparameters.put(1, miscConfig_name);
                allparameters.put(2, position_type);
                allparameters.put(3, min_no_of_satellite);
                allparameters.put(4, age_of_correction);
                allparameters.put(5, pdop_tolerance);
                allparameters.put(6, horizontal_percision);
                allparameters.put(7, vertical_percision);
                allparameters.put(8, check_validity_of_point_taken);
                allparameters.put(9, observation_time_per_point);
                allparameters.put(10, show_point_name);
                allparameters.put(11, show_code_name);
                allparameters.put(12, stake_horizontal_percision);
                allparameters.put(13, stake_vertical_percision);
                allparameters.put(14, stake_target_distance);
                allparameters.put(15, inner_circle_radius);
                allparameters.put(16, outer_circle_radius);
                allparameters.put(17, auto_connect_last_connected_reciever);
                allparameters.put(18, automatically_send_last_working_profile);
                allparameters.put(19, enable_compass);
                allparameters.put(20, enable_e_bubble);
                allparameters.put(21, enable_lrf);
                allparameters.put(22, defaultConfig);
                allparameters.put(23, name);
                allparameters.put(24, remark);

                model.saveMiscellaneous_configuration(allparameters);
            }

            List<MiscellaneousConfigurationBean> list = model.getMiscellaneousConfigList();
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("getMiscellaneousConfigList", list);
            model.closeConnection();
            request.getRequestDispatcher("/miscellaneous_configuration").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.miscellaneousConfiguration.MiscellaneousConfigurationController.doGet()" + e);
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
