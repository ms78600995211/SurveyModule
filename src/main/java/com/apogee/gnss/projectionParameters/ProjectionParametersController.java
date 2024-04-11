/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectionParameters;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.projectionParameters.ProjectionParametersModel;
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
@WebServlet(name = "ProjectionParametersController", urlPatterns = {"/ProjectionParametersController"})
public class ProjectionParametersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectionParametersModel model = new ProjectionParametersModel();

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            String message = "";
            String msgbgcolor = "";
            model.setConnection(DBConnection.getConnection1());

            String submit = request.getParameter("submitFormBtn");
            String projectionParamId = request.getParameter("projectionParam_id");
            request.setAttribute("projectionParamId", projectionParamId);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String zone_name = request.getParameter("zone_name");
                String origin_lat = request.getParameter("origin_lat");
                String origin_lng = request.getParameter("origin_lng");
                String scale = request.getParameter("scale");
                String false_easting = request.getParameter("false_easting");
                String false_northing = request.getParameter("false_northing");
                String paralell_1 = request.getParameter("paralell_1");
                String paralell_2 = request.getParameter("paralell_2");
                String misc1 = request.getParameter("misc1");
                String misc2 = request.getParameter("misc2");
                String misc3 = request.getParameter("misc3");
                String misc4 = request.getParameter("misc4");
                String projectiontype_id = request.getParameter("projectiontype_id");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveProjectionParameters(zone_name, origin_lat, origin_lng, scale, false_easting, false_northing, paralell_1, paralell_2, misc1, misc2, misc3, misc4, projectiontype_id, name, remark);
            }
            request.setAttribute("message",  model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());

            request.setAttribute("projectionParamsList", model.projectionParamsList());
            request.setAttribute("projectionTypeList", model.getAllProjectionType());
            request.setAttribute("list", model.getAllProjectionParams(projectionParamId));

            model.closeConnection();
            request.getRequestDispatcher("projectionParameter").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.ProjectionParametersController.doGet()" + e);
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
