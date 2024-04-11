/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.deviceConfighierarchy;

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
@WebServlet(name = "DeviceConfighierarchyController", urlPatterns = {"/DeviceConfighierarchyController"})
public class DeviceConfighierarchyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DeviceConfighierarchyModel model = new DeviceConfighierarchyModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String gener = request.getParameter("gener");
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String device_configHierarchy_name = request.getParameter("device_configHierarchy_name");
                String parent_id = request.getParameter("device_configHierarchy_id");
                int generation = model.get_generation(parent_id) + 1;
                String isSuperChild = request.getParameter("isSuperChild");
                String defaultConfig = request.getParameter("defaultConfig");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveDevice_confighierarchy(device_configHierarchy_name, parent_id, isSuperChild, generation, defaultConfig, name, remark);
            }
            List getDeviceConfighierarchyList = model.getDeviceConfighierarchyList();
            List getAllDeviceConfighierarchy = model.getAllDeviceConfighierarchy(gener);
            List allGeneration = model.allGeneration();
            request.setAttribute("getDeviceConfighierarchyList", getDeviceConfighierarchyList);
            request.setAttribute("list", getAllDeviceConfighierarchy);
            request.setAttribute("allGeneration", allGeneration);
            request.setAttribute("gener", gener);
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            model.closeConnection();
            request.getRequestDispatcher("device_confighierarchy").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfighierarchy.DeviceConfighierarchyController.doGet()" + e);
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
