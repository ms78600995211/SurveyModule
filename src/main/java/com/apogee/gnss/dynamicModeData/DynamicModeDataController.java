/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.dynamicModeData;

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
@WebServlet(name = "DynamicModeDataController", urlPatterns = {"/DynamicModeDataController"})
public class DynamicModeDataController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DynamicModeDataModel model = new DynamicModeDataModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String modeValue = request.getParameter("modeValue");
            request.setAttribute("modeValue", modeValue);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String device_work_mode_name = request.getParameter("device_work_mode_name");
                String mode_value = request.getParameter("mode_value");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveDynamicModeData(device_work_mode_name, mode_value, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("list", model.getDynamicModeData(modeValue));
            request.setAttribute("allMode_value", model.allMode_value());

            model.closeConnection();
            request.getRequestDispatcher("dynamic_mode_data").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.DynamicModeDataController.doGet()"+e);
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
