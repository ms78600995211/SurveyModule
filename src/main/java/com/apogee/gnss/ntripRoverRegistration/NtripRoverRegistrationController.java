/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.ntripRoverRegistration;

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
@WebServlet(name = "NtripRoverRegistrationController", urlPatterns = {"/NtripRoverRegistrationController"})
public class NtripRoverRegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NtripRoverRegistrationModel model = new NtripRoverRegistrationModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String ntrip_roverId = request.getParameter("ntrip_roverId");
            request.setAttribute("ntrip_roverId",ntrip_roverId);
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String rover_name = request.getParameter("rover_name");
                String rover_password = request.getParameter("rover_password");
                String latitude = request.getParameter("latitude");
                String longitude = request.getParameter("longitude");
                String altitude = request.getParameter("altitude");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveRoverRegData(rover_name, rover_password, latitude, longitude, altitude, name, remark);
            }
            request.setAttribute("roverRegList", model.getRoverRegData(ntrip_roverId));
            request.setAttribute("allRoverRegData", model.allRoverRegData());
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            model.closeConnection();
            request.getRequestDispatcher("ntrip_rover").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.NtripRoverRegistrationController.doGet()"+e);
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
