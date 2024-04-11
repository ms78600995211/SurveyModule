/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.radioInternalParams;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.radioInternalParams.RadioInternalParamsModel;
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
@WebServlet(name = "RadioInternalParamsController", urlPatterns = {"/RadioInternalParamsController"})
public class RadioInternalParamsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RadioInternalParamsModel model = new RadioInternalParamsModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String frqncy = request.getParameter("frqncy");
            request.setAttribute("frqncy", frqncy);
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String datarate = request.getParameter("datarate");
                String baudrate = request.getParameter("baudrate");
                String power = request.getParameter("power");
                String frequency = request.getParameter("frequency");
                String defaultConfig = request.getParameter("defaultConfig");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");

                model.saveRadioInternalParams(datarate, baudrate, power, frequency, defaultConfig, name, remark);
            }
            request.setAttribute("list", model.getAllRadioInternalParams(frqncy));
            request.setAttribute("allFrequency", model.allFrequency());
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            model.closeConnection();
            request.getRequestDispatcher("radiointernalparams").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.RadioInternalParamsController.doGet()" + e);
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
