/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectCodeListMapping;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.projectCodeListMapping.ProjectCodeListMappingModel;
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
@WebServlet(name = "ProjectCodeListMappingController", urlPatterns = {"/ProjectCodeListMappingController"})
public class ProjectCodeListMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectCodeListMappingModel model = new ProjectCodeListMappingModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String projectDetailsId = request.getParameter("projectDetailsId");
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String project_details_id = request.getParameter("project_details_id");
                String code_list_id = request.getParameter("code_list_id");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveMappingData(project_details_id, code_list_id, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("project_details", model.getAllProjects());
            request.setAttribute("codeList_details", model.getAllCodeList());
            request.setAttribute("allProjectMapping", model.getAllProjectMapping(projectDetailsId));
            model.closeConnection();
            request.getRequestDispatcher("project_details_code_list_mapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.mapping.ProjectCodeListMappingController.doGet()" + e);
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
