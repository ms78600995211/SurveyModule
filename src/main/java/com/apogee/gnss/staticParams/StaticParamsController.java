/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.staticParams;

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
@WebServlet(name = "StaticParamsController", urlPatterns = {"/StaticParamsController"})
public class StaticParamsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StaticParamsModel model = new StaticParamsModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String samplingRate = request.getParameter("samplingRate");
            request.setAttribute("samplingRate", samplingRate);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String file_name = request.getParameter("file_name");
                String total_time = request.getParameter("total_time");
                String sampling_rate = request.getParameter("sampling_rate");
                String remark = request.getParameter("remark");
                String defaultConfig = request.getParameter("defaultConfig");
                String name = (String) session.getAttribute("name");
//                String name = "";
                model.saveStaticParams(file_name, total_time, sampling_rate, name, remark, defaultConfig);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("list", model.getAllStaticParsms(samplingRate));
            request.setAttribute("allSampling_rate", model.allSampling_rate());
            model.closeConnection();
            request.getRequestDispatcher("static_params").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.StaticParamsController.doGet()" + e);
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
