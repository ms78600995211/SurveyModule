/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.statusType;

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
@WebServlet(name = "StatusTypeController", urlPatterns = {"/StatusTypeController"})

public class StatusTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StatusTypeModel model = new StatusTypeModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();
            String submit = request.getParameter("submitFormBtn");
            String statusTypeId = request.getParameter("status_type_id");
            request.setAttribute("statusTypeId", statusTypeId);

//        String document_id = "";
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String statusType = request.getParameter("statusType");
                String statusTypeDescription = request.getParameter("statusTypeDescription");
                String remark = request.getParameter("remark");
                model.saveStatusType(statusType, statusTypeDescription, remark, log_in_person);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("StatusTypeAllData", model.getAllStatusType(statusTypeId));
            request.setAttribute("allstatus", model.allStatusType());
            model.closeConnection();
            request.getRequestDispatcher("statusType").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.StatusTypeController.doGet()"+e);
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
