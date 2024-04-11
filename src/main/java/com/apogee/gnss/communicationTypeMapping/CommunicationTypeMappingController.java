/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.communicationTypeMapping;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.pdaParams.PDAParamsBean;
import com.apogee.gnss.radioExternalParams.RadioExternalParamsBean;
import com.apogee.gnss.radioInternalParams.RadioInternalParamsBean;
import com.apogee.gnss.via4GParams.Via4GParamsBean;
import com.apogee.gnss.wifiParams.WifiParamsBean;
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
@WebServlet(name = "CommunicationTypeMappingController", urlPatterns = {"/CommunicationTypeMappingController"})
public class CommunicationTypeMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommunicationTypeMappingModel model = new CommunicationTypeMappingModel();
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
            String typeOfCommunicationId = request.getParameter("typeOfCommunicationId");
            if (submit == null) {
                submit = "";
            }
            if (task.equals("getCommunicationList")) {
                JSONObject returnObj = new JSONObject();
                String Type_of_communication_id = request.getParameter("Type_of_communication_id");
                String communicationType = model.getCommunicationType(Type_of_communication_id);
                List<CommunicationTypeMappingBean> ParamsList = null;
                if (communicationType.equalsIgnoreCase("WiFi")) {
                    ParamsList = model.getWifiparamsList();
                }
                if (communicationType.equalsIgnoreCase("4G")) {
                    ParamsList = model.getVia4gparamsList();
                }
                if (communicationType.equalsIgnoreCase("PDA_Rover")) {
                    ParamsList = model.getPdaparamsList();
                    communicationType = "PDA";
                }
                if (communicationType.equalsIgnoreCase("RS232")) {
                    ParamsList = model.getRadioexternalparamsList();
                    communicationType = "External Radio";

                }
                if (communicationType.equalsIgnoreCase("Radio")) {
                    ParamsList = model.getRadiointernalparamsList();
                    communicationType = "Internal Radio";
                }
                PrintWriter out = response.getWriter();
                returnObj.put("getData", ParamsList);
                returnObj.put("paramsList_name", communicationType + " Parameters");
                out.print(returnObj);
                return;

            }

            if (task.equals("getParameters")) {
                JSONObject returnObj = new JSONObject();
                String Via4gparams_name = request.getParameter("Via4gparams_name");
                List<Via4GParamsBean> Via4gparams = model.getVia4gparams(Via4gparams_name);
                PrintWriter out = response.getWriter();
                returnObj.put("Via4gparams", Via4gparams);
                out.print(returnObj);
                return;
            }

            if (task.equals("getWiFiparams")) {
                JSONObject returnObj = new JSONObject();
                String WiFiparams_name = request.getParameter("WiFiparams_name");
                List<WifiParamsBean> WiFiparams = model.getWifiparamsList(WiFiparams_name);
                PrintWriter out = response.getWriter();
                returnObj.put("WiFiparams", WiFiparams);
                out.print(returnObj);
                return;
            }

            if (task.equals("getPDAparams")) {
                JSONObject returnObj = new JSONObject();
                String PDAparams_name = request.getParameter("PDAparams_name");
                List<PDAParamsBean> PDAparams = model.getPDAparamsList(PDAparams_name);
                PrintWriter out = response.getWriter();
                returnObj.put("PDAparams", PDAparams);
                out.print(returnObj);
                return;
            }

            if (task.equals("getExternalRadioparams")) {
                JSONObject returnObj = new JSONObject();
                String ExternalRadioparams_Name = request.getParameter("ExternalRadioparams");
                List<RadioExternalParamsBean> ExternalRadioparams = model.getRadioExternalParamsList(ExternalRadioparams_Name);
                PrintWriter out = response.getWriter();
                returnObj.put("ExternalRadioparams", ExternalRadioparams);
                out.print(returnObj);
                return;
            }
            if (task.equals("getInternalRadioparams")) {
                JSONObject returnObj = new JSONObject();
                String InternalRadioparams_Name = request.getParameter("InternalRadioparams");
                List<RadioInternalParamsBean> InternalRadioparams = model.getRadioInternalParamsList(InternalRadioparams_Name);
                PrintWriter out = response.getWriter();
                returnObj.put("InternalRadioparams", InternalRadioparams);
                out.print(returnObj);
                return;
            }
            if (submit.equals("Submit")) {
                String type_of_communication_id = request.getParameter("type_of_communication_id");
                String params_name = request.getParameter("params_name");
                String defaultConfig = request.getParameter("defaultConfig");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveCommunicationTypeMapping(type_of_communication_id, params_name, defaultConfig, remark, name);
            }
            List getTypeOfCommunicationList=model.getTypeOfCommunicationList();
            List getCommunicationTypeMappingList=model.getCommunicationTypeMappingList(typeOfCommunicationId);

            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("typeOfCommunicationId", typeOfCommunicationId);
            request.setAttribute("getTypeOfCommunicationList", getTypeOfCommunicationList);
            request.setAttribute("getCommunicationTypeMappingList", getCommunicationTypeMappingList);
            model.closeConnection();
            request.getRequestDispatcher("/communication_type_mapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.communicationTypeMapping.CommunicationTypeMappingController.doGet()" + e);
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
