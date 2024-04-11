/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectClientDeviceMapping;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.clientBaseRoverRegistrationMapping.ClientBaseRoverRegistrationMappingBean;
import com.apogee.gnss.client.ClientBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProjectClientDeviceMappingController", urlPatterns = {"/ProjectClientDeviceMappingController"})
public class ProjectClientDeviceMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectClientDeviceMappingModel model = new ProjectClientDeviceMappingModel();
        List<ClientBean> client_details = new ArrayList<>();
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
            String projectDetailsId = request.getParameter("projectDetailsId");
            request.setAttribute("projectDetailsId", projectDetailsId);
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String project_details_id = request.getParameter("project_details_id");
                String client_registeration_id = request.getParameter("client_registeration_id");
                String ntrip_base_id = request.getParameter("ntrip_base_id");
                String ntrip_rover_id = request.getParameter("ntrip_rover_id");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveProjectClientDeviceMappingData(project_details_id, ntrip_base_id, ntrip_rover_id, client_registeration_id, name, remark);
            }
            if (task.equals("getAllMappedDevices")) {
                JSONObject returnObj = new JSONObject();
                String client_registeration_id1 = request.getParameter("client_registeration_id");
                List<ClientBaseRoverRegistrationMappingBean> base_details = model.getAllBase1(model.getAllBase_id(client_registeration_id1));
                List<ClientBaseRoverRegistrationMappingBean> rover_details = model.getAllRover1(model.getAllRover_id(client_registeration_id1));
                PrintWriter out = response.getWriter();
                returnObj.put("base_details", base_details);
                returnObj.put("rover_details", rover_details);
                out.print(returnObj);
                return;
            }
            String all_client = model.getClients();
            JSONObject obj = new JSONObject(all_client);
            JSONArray arr = obj.getJSONArray("client");
            for (int i = 0; i < arr.length(); i++) {
                ClientBean bean = new ClientBean();
                JSONObject jobj = arr.getJSONObject(i);
                bean.setKey_person_id(jobj.get("key_person_id").toString());
                bean.setKey_person_name(jobj.get("key_person_name").toString());
                client_details.add(bean);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("getAllProjects",  model.getAllProjects());
            request.setAttribute("client_details", client_details);
            request.setAttribute("getAllClientBaseRoverMappingData", model.getAllProjectDeviceMapping(projectDetailsId));
            model.closeConnection();
            request.getRequestDispatcher("project_client_dev_map").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectClientDeviceMapping.ProjectClientDeviceMappingController.doGet()"+e);
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
