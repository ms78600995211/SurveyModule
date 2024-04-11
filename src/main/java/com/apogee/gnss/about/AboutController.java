/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.about;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.projectFile.ProjectFileBean;
import com.apogee.gnss.projectFile.ProjectFileModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
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
@WebServlet(name = "AboutController", urlPatterns = {"/AboutController"})
public class AboutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AboutModel model = new AboutModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            response.sendRedirect("login_page");
            return;
        }
        try {
//            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            if (submit == null) {
                submit = "";
            }
            /*
            if (submit.equals("Submit")) {
                String versionName = request.getParameter("versionName");
                String status = request.getParameter("status");
                String description = request.getParameter("description");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveProjectVersion(versionName, status, description, name, remark);
            }
             */
            List<AboutBean> allProjectVesrion = new ArrayList();
            AboutBean version1 = new AboutBean();
            version1.setVersionName("SurveyModule - V(1.0)");
            version1.setStatus("active");
            version1.setTimeStamp("13/03/2024");
            version1.setDescription("worked on Project Details,created  3 new pages Project Status ,Project Folder & Project File.");
            allProjectVesrion.add(version1);

            AboutBean version2 = new AboutBean();
            version2.setVersionName("SurveyModule - V(1.1)");
            version2.setStatus("active");
            version2.setTimeStamp("18/03/2024");
            version2.setDescription("worked on device project onfiguration ,miscellaneous_configuration ");
            allProjectVesrion.add(version2);

            AboutBean version3 = new AboutBean();
            version3.setVersionName("SurveyModule - V(1.2)");
            version3.setStatus("active");
            version3.setTimeStamp("27/03/2024");
            version3.setDescription("worked on onfiguration table  ");
            allProjectVesrion.add(version3);

            request.setAttribute("allProjectVesrion", allProjectVesrion);
//            model.closeConnection();
            request.getRequestDispatcher("about").forward(request, response);

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectFile.ProjectFileController.doGet()" + e);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
