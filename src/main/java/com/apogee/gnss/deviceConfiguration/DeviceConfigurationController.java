/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.deviceConfiguration;

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
@WebServlet(name = "DeviceConfigurationController", urlPatterns = {"/DeviceConfigurationController"})
public class DeviceConfigurationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DeviceConfigurationModel model = new DeviceConfigurationModel();
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
            String dynamic_configuration_mappingId = request.getParameter("dynamic_configuration_mappingId");
            request.setAttribute("dynamic_configuration_mappingId", dynamic_configuration_mappingId);
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String communication_type_mapping_id = request.getParameter("communication_type_mapping_id");
                String dynamic_configuration_mapping_id = request.getParameter("dynamic_configuration_mapping_id");
                String mask_angle_byteValue = request.getParameter("mask_angle_byteValue");
                String mask_angle_displayValue = request.getParameter("mask_angle_displayValue");
                String device_work_mode_name = request.getParameter("device_work_mode_name");
                String device_work_mode_value = request.getParameter("device_work_mode_value");
                String defaultConfig = request.getParameter("defaultConfig");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveDevice_configurationData(dynamic_configuration_mapping_id,communication_type_mapping_id,mask_angle_byteValue,mask_angle_displayValue,device_work_mode_name,device_work_mode_value, defaultConfig,name,remark);
            }
            List getAllDynamicConfigName = model.getAllDynamicConfigName();
            List getCommunication_type_mappingList = model.getCommunication_type_mappingList();
            List getDeviceConfigurationList = model.getDeviceConfigurationList(dynamic_configuration_mappingId);
            List getAllDynamicConfig = model.getAllDynamicConfig();

            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("getAllDynamicConfigName", getAllDynamicConfigName);
            request.setAttribute("getCommunication_type_mappingList",getCommunication_type_mappingList);
            request.setAttribute("getDeviceConfigurationList", getDeviceConfigurationList);
            request.setAttribute("getAllDynamicConfig", getAllDynamicConfig);

            model.closeConnection();
            request.getRequestDispatcher("/device_configuration").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.deviceConfiguration.DeviceConfigurationController.doGet()" + e);
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
