/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectConfigurationMapping;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProjectConfigurationMappingController", urlPatterns = {"/ProjectConfigurationMappingController"})
public class ProjectConfigurationMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectConfigurationMappingModel model = new ProjectConfigurationMappingModel();
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
            String projectName = request.getParameter("projectName");

            if (submit == null) {
                submit = "";
            }
            if (task.equals("showSurveyConfigData")) {
                JSONObject returnObj = new JSONObject();
                String Survey_configuration_id = request.getParameter("Survey_configuration_id");
                List getSurveyConfigurationData = model.getSurveyConfigurationData(Survey_configuration_id);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getSurveyConfigurationData);
                out.print(returnObj);
                return;
            }
/*
            if (task.equals("showMiscConfigData")) {
                JSONObject returnObj = new JSONObject();
                String miscellaneous_configuration_id = request.getParameter("Miscellaneous_configuration_id");
                List getMiscellaneousConfigList = model.getMiscellaneousConfigList(miscellaneous_configuration_id);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getMiscellaneousConfigList);
                out.print(returnObj);
                return;
            }
*/
            if (task.equals("showDynamicConfigData")) {
                JSONObject returnObj = new JSONObject();
                String dynamic_project_config_id = request.getParameter("Dynamic_project_config_id");
                List getDynamicProjectConfigData = model.getDynamicProjectConfigData(dynamic_project_config_id);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getDynamicProjectConfigData);
                out.print(returnObj);
                return;
            }
            if (submit.equals("Submit")) {
                String project_configuration_mapping_name = request.getParameter("project_configuration_mapping_name");
//                String project_details_id = request.getParameter("project_details_id");
                String survey_configuration_id = request.getParameter("survey_configuration_id");
                String device_configuration_id = request.getParameter("device_configuration_id");
                String satellite_configuration_id = request.getParameter("satellite_configuration_id");
                String miscellaneous_configuration_id = request.getParameter("miscellaneous_configuration_id");
                String defaultConfig = (request.getParameter("defaultConfig") != null) ? request.getParameter("defaultConfig") : "Y";
//                String dynamic_project_config_id = request.getParameter("dynamic_project_config_id");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                List<String> parameters = Arrays.asList(
                        project_configuration_mapping_name, survey_configuration_id,
                        device_configuration_id, satellite_configuration_id, miscellaneous_configuration_id,defaultConfig,
                         name, remark
                );
                model.saveProjectConfigurationMappingData(parameters);
            }
            List getSurveyConfigurationList = model.getSurveyConfigurationList();
            List getDeviceConfigurationList = model.getDeviceConfigurationList();
            List getSatelliteConfigurationList = model.getSatelliteConfigurationList();
            List getMiscellaneousConfigurationList = model.getMiscellaneousConfigurationList();
            List getProjectConfigurationMappingList = model.getProjectConfigurationMappingList(projectName);
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("projectName", projectName);
            request.setAttribute("getSurveyConfigurationList", getSurveyConfigurationList);
            request.setAttribute("getDeviceConfigurationList", getDeviceConfigurationList);
            request.setAttribute("getSatelliteConfigurationList", getSatelliteConfigurationList);
            request.setAttribute("getMiscellaneousConfigurationList", getMiscellaneousConfigurationList);
            request.setAttribute("getProjectConfigurationMappingList", getProjectConfigurationMappingList);
            model.closeConnection();
            request.getRequestDispatcher("/project_configuration_mapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingController.doGet()" + e);
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
