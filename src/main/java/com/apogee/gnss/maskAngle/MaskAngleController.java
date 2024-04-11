/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.maskAngle;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.maskAngle.MaskAngleModel;
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
@WebServlet(name = "MaskAngleController", urlPatterns = {"/MaskAngleController"})

public class MaskAngleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaskAngleModel model = new MaskAngleModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();

            String submit = request.getParameter("submitFormBtn");
            String bytevalue = request.getParameter("byte_value");
            request.setAttribute("bytevalue", bytevalue);
            request.setAttribute("maskAngleList", model.maskAngleList());
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String byteValue = request.getParameter("byteValue");
                String displayValue = request.getParameter("displayValue");
                String remark = request.getParameter("remark");
                model.saveMaskangle(byteValue, displayValue, remark, log_in_person);
            }
            request.setAttribute("message",  model.getMessage());
            request.setAttribute("msgbgcolor",  model.getColor());
            request.setAttribute("MaskAngleAllData", model.getAllMaskAngle(bytevalue));
            model.closeConnection();
            request.getRequestDispatcher("maskangle").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.MaskAngleController.doGet()" + e);
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
