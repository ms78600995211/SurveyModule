/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.dynamicProjectConfiguration;

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
@WebServlet(name = "DynamicConfigMappingController", urlPatterns = {"/DynamicConfigMappingController"})
public class DynamicConfigMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DynamicConfigMappingModel model = new DynamicConfigMappingModel();
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
            String fileName = request.getParameter("fileName");

            if (submit == null) {
                submit = "";
            }
            if (task.equals("getData")) {
                JSONObject returnObj = new JSONObject();
                String byte_value = request.getParameter("Byte_value");
                if (byte_value == null) {
                    byte_value = "";
                }
                List<DynamicConfigMappingBean> getData = model.getMask_angle(byte_value);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getData);
                out.print(returnObj);
                return;
            }
            if (task.equals("getStatic_params")) {
                JSONObject returnObj = new JSONObject();
                String file_name = request.getParameter("File_name");
                if (file_name == null) {
                    file_name = "";
                }
                String Total_time = request.getParameter("Total_time");
                if (Total_time == null) {
                    Total_time = "";
                }
                List<DynamicConfigMappingBean> getData = model.getStaticParams(file_name, Total_time);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getData);
                out.print(returnObj);
                return;
            }
            if (task.equals("getDynamic_mode_data")) {
                JSONObject returnObj = new JSONObject();
                String Device_work_mode_name = request.getParameter("Device_work_mode_name");
                if (Device_work_mode_name == null) {
                    Device_work_mode_name = "";
                }
                List<DynamicConfigMappingBean> getData = model.getDynamic_mode_data(Device_work_mode_name);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getData);
                out.print(returnObj);
                return;
            }
            if (task.equals("getPpk_params")) {
                JSONObject returnObj = new JSONObject();
                String File_name = request.getParameter("File_name");
                if (File_name == null) {
                    File_name = "";
                }
                List<DynamicConfigMappingBean> getData = model.getPpk_params(File_name);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getData);
                out.print(returnObj);
                return;
            }

            /*
            if (task.equals("getManual_base_table")) {
                JSONObject returnObj = new JSONObject();
                String Latitude = request.getParameter("Latitude");
                if (Latitude == null) {
                    Latitude = "";
                }

                String Altitude = request.getParameter("Altitude");
                if (Altitude == null) {
                    Altitude = "";
                }
                List<DynamicConfigMappingBean> getData = model.getManual_base_table(Latitude, Altitude);
                PrintWriter out = response.getWriter();
                returnObj.put("getData", getData);
                out.print(returnObj);
                return;
            }
             */
            if (submit.equals("Submit")) {
                String dynamic_config_name = request.getParameter("dynamic_config_name");
                String device_configHierarchy_id = request.getParameter("device_configHierarchy_id");
                String manual_base_params_id = request.getParameter("manual_base_params_id");
                String file_name = request.getParameter("file_name");
                String ppk_params_total_time = request.getParameter("totalTime");
                String ppk_params_id = model.getPpk_params_id(file_name, ppk_params_total_time);
                String static_params_file_name = request.getParameter("static_params_file_name");
                String total_time = request.getParameter("total_time");
                String sampling_rate = request.getParameter("sampling_rate");
                String static_params_id = model.getStatic_params_id(static_params_file_name, total_time, sampling_rate);
                String defaultConfig = (request.getParameter("defaultConfig") != null) ? request.getParameter("defaultConfig") : "Y";
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveDynamic_project_config(dynamic_config_name,device_configHierarchy_id, manual_base_params_id, ppk_params_id, static_params_id,defaultConfig, name, remark);
            }
            List getDevice_confighierarchyList = model.getDevice_confighierarchyList();
            List getAllManualBaseParamsName = model.getAllManualBaseParamsName();
            List getDynamicProjectConfigList = model.getDynamicProjectConfigList(fileName);


            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("fileName", fileName);
            request.setAttribute("getByte_value", model.getByte_value());
            request.setAttribute("getFileName", model.getFileName());
            request.setAttribute("getDevice_work_mode_name", model.getDevice_work_mode_name());
            request.setAttribute("getFile_name", model.getFile_name());
            request.setAttribute("getDynamicProjectConfigList",getDynamicProjectConfigList);
            request.setAttribute("getDevice_confighierarchyList", getDevice_confighierarchyList);
            request.setAttribute("getAllManualBaseParamsName", getAllManualBaseParamsName);

            model.closeConnection();
            request.getRequestDispatcher("/dynamic_config_mapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.dynamicProjectConfiguration.DynamicProjectConfigurationController.doGet()" + e);
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
