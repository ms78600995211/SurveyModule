/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.siteCalibration;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.siteCalibration.SiteCalibrationModel;
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
@WebServlet(name = "SiteCalibrationController", urlPatterns = {"/SiteCalibrationController"})
public class SiteCalibrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SiteCalibrationModel model = new SiteCalibrationModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String scale = request.getParameter("scale");
            request.setAttribute("scale", scale);
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String Scale = request.getParameter("Scale");
                String Angle = request.getParameter("Angle");
                String Tx = request.getParameter("Tx");
                String Ty = request.getParameter("Ty");
                String Fixed_Easting = request.getParameter("Fixed_Easting");
                String Fixed_Northing = request.getParameter("Fixed_Northing");
                String sigmaZ = request.getParameter("sigmaZ");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveSiteCalData(Scale, Angle, Tx, Ty, Fixed_Easting, Fixed_Northing, sigmaZ, name, remark);
            }
            request.setAttribute("listSiteCalData", model.getSiteCalData(scale));
            request.setAttribute("allScale", model.allScale());
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            model.closeConnection();
            request.getRequestDispatcher("site_calibration").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.SiteCalibrationController.doGet()" + e);
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
