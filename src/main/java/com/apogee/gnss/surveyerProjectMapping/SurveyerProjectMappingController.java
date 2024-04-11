/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.surveyerProjectMapping;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "SurveyerProjectMappingController", urlPatterns = {"/SurveyerProjectMappingController"})
public class SurveyerProjectMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SurveyerProjectMappingModel model = new SurveyerProjectMappingModel();
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
            if (submit.equals("Submit")) {
                String project_client_dev_map_id = request.getParameter("project_client_dev_map_id");
                String key_person_id = request.getParameter("key_person_id");
                String survey_no = request.getParameter("survey_no");
                String survey_date = request.getParameter("survey_date");
                String survey_start_datetime = formatDateString(request.getParameter("survey_start_datetime"), "yyyy-MM-dd HH:mm:ss");
                String survey_end_datetime = formatDateString(request.getParameter("survey_end_datetime"), "yyyy-MM-dd HH:mm:ss");
                String name = (String) session.getAttribute("name");
                model.saveSurveyorData(project_client_dev_map_id, key_person_id, survey_no, survey_date, survey_start_datetime, survey_end_datetime, name);
            }
            List project_client_device_list = model.getAllProjectDeviceMapping();
            List all_data = model.getAlldata(projectName);
            List getSurveyors =model.getSurveyors();
            List allProjects =model.allProjects();
            request.setAttribute("message",model.getMessage());
            request.setAttribute("msgbgcolor",model.getColor());
            request.setAttribute("projectName",projectName);
            request.setAttribute("all_data",all_data);
            request.setAttribute("project_client_device_list", project_client_device_list);
            request.setAttribute("getSurveyors",getSurveyors);
            request.setAttribute("allProjects",allProjects);
            model.closeConnection();
            request.getRequestDispatcher("surveyer_project_map").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyerProjectMapping.SurveyerProjectMappingController.doGet()" + e);
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

    public String formatDateString(String inputDateString, String outputFormat) throws ParseException {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        return outputDateFormat.format(inputDateFormat.parse(inputDateString));
    }
}
