/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.constellation;

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
 * @author lENOVO
 */
@WebServlet(name = "ConstellationController", urlPatterns = {"/ConstellationController"})

public class ConstellationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConstellationModel model = new ConstellationModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }

        try {
            model.setConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();

            String submit = request.getParameter("submitFormBtn");
            String constId = request.getParameter("constellation_id");
            request.setAttribute("constId", constId);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String constellation = request.getParameter("constellation");
                String remark = request.getParameter("remark");
                model.saveConstellation(constellation, remark, log_in_person);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("allConstellation", model.allConstellation());
            request.setAttribute("ConstellationAllData", model.getConstellation(constId));

            model.closeConnection();
            request.getRequestDispatcher("constellation").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.ConstellationController.doGet()" + e);
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
