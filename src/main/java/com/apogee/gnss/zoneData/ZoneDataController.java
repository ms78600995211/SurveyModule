/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.zoneData;

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
@WebServlet(name = "ZoneDataController", urlPatterns = {"/ZoneDataController"})
public class ZoneDataController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ZoneDataModel model = new ZoneDataModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String zonedataId = request.getParameter("zonedataId");
            request.setAttribute("zonedataId", zonedataId);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String zone = request.getParameter("zone");
                String hemisphere_id = request.getParameter("hemisphere_id");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveZoneData(zone, hemisphere_id, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("hemispherelist", model.getHemishereData());
            request.setAttribute("list", model.getAllZoneData(zonedataId));
            request.setAttribute("allZoneData", model.allZoneData());
            model.closeConnection();
            request.getRequestDispatcher("zonedata").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.ZoneDataController.doGet()"+e);
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
