package com.apogee.gnss.projectConfigurationMainMapping;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
@WebServlet(urlPatterns = {"/ProjectConfigurationMainMappingController"})
public class ProjectConfigurationMainMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectConfigurationMainMappingModel model = new ProjectConfigurationMainMappingModel();
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String satellite_configurationId = request.getParameter("satellite_configurationId");
            request.setAttribute("satellite_configurationId", satellite_configurationId);
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                ProjectConfigurationMainMappingBean bean = new ProjectConfigurationMainMappingBean();
                bean.setProject_details_id(request.getParameter("project_details_id"));
                bean.setProject_configuration_mapping_id(request.getParameter("project_configuration_mapping_id"));
                bean.setName((String) session.getAttribute("name"));
                bean.setRemark(request.getParameter("remark"));
                model.saveSatelliteConfiguConstellationMappingData(bean);
            }
            List getProjectDetailsList = model.getProjectDetailsList();
            List getProjectConfigurationMappingList = model.getProjectConfigurationMappingList();
            List getAllProjectConfigurationMainMapping = model.getAllProjectConfigurationMainMapping();
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("getProjectConfigurationMappingList", getProjectConfigurationMappingList);
            request.setAttribute("getProjectDetailsList", getProjectDetailsList);
            request.setAttribute("getAllProjectConfigurationMainMapping", getAllProjectConfigurationMainMapping);

            model.closeConnection();
            request.getRequestDispatcher("/project_configuration_main_mapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.configuration.ProjectConfigurationMainMappingController.doGet()" + e);
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
