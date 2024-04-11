/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectStatus;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.projectStatus.ProjectStatusModel;
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
@WebServlet(name = "ProjectStatusController", urlPatterns = {"/ProjectStatusController"})

public class ProjectStatusController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectStatusModel model = new ProjectStatusModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();
            String submit = request.getParameter("submitFormBtn");
            String projectStatusId = request.getParameter("project_status_id");
            request.setAttribute("projectStatusId", projectStatusId);
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String projectStatus = request.getParameter("projectStatus");
                String projectStatusDescription = request.getParameter("projectStatusDescription");
                String remark = request.getParameter("remark");
/*
                int statusTypeId = 0;
                String status_Type_Id = request.getParameter("statusType");
                if (!status_Type_Id.equals("")) {
                    statusTypeId = Integer.parseInt(status_Type_Id);
                }
*/
//                model.saveProjectStatus(projectStatus, projectStatusDescription, statusTypeId, remark, log_in_person);
                model.saveProjectStatus(projectStatus, projectStatusDescription, remark, log_in_person);


            }
            request.setAttribute("message",  model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("getProjectStatList", model.getProjectStatList());
            request.setAttribute("StatusTypeData", model.getAllStatus());
            request.setAttribute("ProjectStatusAllData", model.getAllProjectStatus(projectStatusId));

            model.closeConnection();
            request.getRequestDispatcher("projectStatus").forward(request, response);
        } catch (Exception e) {

            System.out.println("com.apogee.gnss.controller.ProjectStatusController.doGet()"+e);        }
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
