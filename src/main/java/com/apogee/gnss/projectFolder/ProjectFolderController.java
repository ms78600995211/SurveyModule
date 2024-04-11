/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectFolder;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "ProjectFolderController", urlPatterns = {"/ProjectFolderController"})
public class ProjectFolderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectFolderModel model = new ProjectFolderModel();
        String folderId = request.getParameter("folder_id");

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String FolderIdToView = request.getParameter("FolderIdToView");
            if (FolderIdToView == null) {  
                FolderIdToView = "";
            }
            String task = request.getParameter("task");
            if (task == null) {
                task = "";
            }
            if (task.equals("viewfolder")) {
                model.getFolderPath(FolderIdToView);
            }
            String submit = request.getParameter("submitFormBtn");
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String projectFolder = request.getParameter("projectFolder");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveProjectFolderInfo(projectFolder, name, remark);
            }
            List<ProjectFolderBean> allProjectFolder = model.allProjectFolder();
            List<ProjectFolderBean> getProjectFolder = model.getProjectFolder(folderId);
            request.setAttribute("folder_id", folderId);
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("allProjectFolder", allProjectFolder);
            request.setAttribute("getProjectFolder", getProjectFolder);

            model.closeConnection();
            request.getRequestDispatcher("project_folder").forward(request, response);
        } catch (Exception e) {

            System.out.println("com.apogee.gnss.projectFolder.ProjectFolderController.doGet()" + e);
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
