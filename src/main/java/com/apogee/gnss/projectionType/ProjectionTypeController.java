/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectionType;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.projectionType.ProjectionTypeModel;
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
@WebServlet(name = "ProjectionTypeController", urlPatterns = {"/ProjectionTypeController"})
public class ProjectionTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectionTypeModel model = new ProjectionTypeModel();

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String projectionTypeId = request.getParameter("projectiontype_id");
            request.setAttribute("projectionTypeId", projectionTypeId);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String projectionType = request.getParameter("projectionType");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveProjectionType(projectionType, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("projectionTypeList", model.projectionTypeList());
            request.setAttribute("ProjectionTypeAllData", model.getAllProjectionType(projectionTypeId));

            model.closeConnection();
            request.getRequestDispatcher("projectionType").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.ProjectionTypeController.doGet()" + e);
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
