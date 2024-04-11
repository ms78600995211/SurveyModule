/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.manualBase;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.manualBase.ManualBaseModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lENOVO
 */
@WebServlet(name = "ManualBaseController", urlPatterns = {"/ManualBaseController"})

public class ManualBaseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManualBaseModel model = new ManualBaseModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }

        try {
            model.setConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();
            String submit = request.getParameter("submitFormBtn");
            String lat = request.getParameter("latitude");
            request.setAttribute("lat", lat);
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String latitude = request.getParameter("latitude");
                String longitude = request.getParameter("longitude");
                String altitude = request.getParameter("altitude");
                String remark = request.getParameter("remark");
                String manual_base_params_name = request.getParameter("manual_base_params_name");
                String defaultConfig = request.getParameter("defaultConfig");
                model.saveManualBase(latitude, longitude, altitude, remark, log_in_person, manual_base_params_name, defaultConfig);

            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("manualBaseList", model.manualBaseList());
            request.setAttribute("ManualBaseAllData", model.getAllManualBase(lat));
            model.closeConnection();
            request.getRequestDispatcher("manualBase").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.ManualBaseController.doGet()" + e);
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
