/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.elevationType;

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
@WebServlet(name = "ElevationTypeController", urlPatterns = {"/ElevationTypeController"})
public class ElevationTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ElevationTypeModel model = new ElevationTypeModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String elevationtypeId = request.getParameter("elevationtype_id");
            request.setAttribute("elevationtypeId", elevationtypeId);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String elevationType = request.getParameter("elevationType");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveElevationType(elevationType, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("allElevationType", model.allElevationType());
            request.setAttribute("ElevationTypeAllData", model.getAllElevationType(elevationtypeId));
            model.closeConnection();
            request.getRequestDispatcher("elevationType").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.ElevationTypeController.doGet()" + e);
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
