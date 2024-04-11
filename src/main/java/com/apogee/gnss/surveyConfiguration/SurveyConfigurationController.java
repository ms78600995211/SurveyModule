/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.surveyConfiguration;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SurveyConfigurationController", urlPatterns = {"/SurveyConfigurationController"})
public class SurveyConfigurationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SurveyConfigurationModel model = new SurveyConfigurationModel();
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
            String angleunitId = request.getParameter("angleunitId");
            if (submit == null) {
                submit = "";
            }
            if (task.equals("getProjectionparametersList")) {
                JSONObject returnObj = new JSONObject();
                String zone_name = request.getParameter("Zone_name");
                if (zone_name == null) {
                    zone_name = "";
                }
                String Origin_lat = request.getParameter("Origin_lat");

                if (Origin_lat == null) {
                    Origin_lat = "";
                }
                String Origin_lng = request.getParameter("Origin_lng");

                if (Origin_lng == null) {
                    Origin_lng = "";
                }
                String Scale = request.getParameter("Scale");

                if (Scale == null) {
                    Scale = "";
                }
                String False_easting = request.getParameter("False_easting");

                if (False_easting == null) {
                    False_easting = "";
                }
                String False_northing = request.getParameter("False_northing");

                if (False_northing == null) {
                    False_northing = "";
                }
                String Paralell_1 = request.getParameter("Paralell_1");

                if (Paralell_1 == null) {
                    Paralell_1 = "";
                }
                String Paralell_2 = request.getParameter("Paralell_2");

                if (Paralell_2 == null) {
                    Paralell_2 = "";
                }
                List<SurveyConfigurationBean> getProjectionparametersList = model.getprojectionparameters(zone_name, Origin_lat, Origin_lng, Scale, False_easting, False_northing, Paralell_1, Paralell_2);
                PrintWriter out = response.getWriter();
                returnObj.put("getProjectionparametersList", getProjectionparametersList);
                out.print(returnObj);
                return;
            }
            if (submit.equals("Submit")) {
                String survey_configuration_name = request.getParameter("survey_configuration_name");
                String datum_id = request.getParameter("datum_id");
                String zonedata_id = request.getParameter("zonedata_id");
                String elevationtype_id = request.getParameter("elevationtype_id");
                String distanceunit_id = request.getParameter("distanceunit_id");
                String angleunit_id = request.getParameter("angleunit_id");
                String zone_name = request.getParameter("zone_name");
                String origin_lat = request.getParameter("projectionparam_origin_lat");
                String origin_lng = request.getParameter("projectionparam_origin_lng");
                String scale = request.getParameter("projectionparam_scale");
                String false_easting = request.getParameter("projectionparam_false_easting");
                String false_northing = request.getParameter("projectionparam_false_northing");
                String paralell_1 = request.getParameter("projectionparam_paralell_1");
                String paralell_2 = request.getParameter("projectionparam_paralell_2");
                String projectiontype_id = request.getParameter("projectiontype_id");
                String projectionParam_id = model.getProjectionParam_id(zone_name, origin_lat, origin_lng, scale, false_easting, false_northing, paralell_1, paralell_2, projectiontype_id);
                String defaultConfig = (request.getParameter("defaultConfig") != null) ? request.getParameter("defaultConfig") : "Y";
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveSurvey_configurationData(survey_configuration_name, datum_id, zonedata_id, elevationtype_id, distanceunit_id, angleunit_id, projectionParam_id,defaultConfig, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("angleunitId", angleunitId);
            request.setAttribute("getDatumnList", model.getDatumnList());
            request.setAttribute("getZone_nameList", model.getZone_nameList());
            request.setAttribute("getZonedataList", model.getZonedataList());
            request.setAttribute("getElevationtypeList", model.getElevationtypeList());
            request.setAttribute("getdistanceunitList", model.getdistanceunitList());
            request.setAttribute("getAngleunitList", model.getAngleunitList());
            request.setAttribute("getSurveyConfigurationList", model.getSurveyConfigurationList(angleunitId));
            model.closeConnection();
            request.getRequestDispatcher("/survey_configuration").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.surveyConfiguration.SurveyConfigurationController.doGet()" + e);
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
