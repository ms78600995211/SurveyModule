/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.clientBaseRoverRegistrationMapping;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.clientBaseRoverRegistrationMapping.ClientBaseRoverRegistrationMappingBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
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
@WebServlet(name = "ClientBaseRoverRegistrationMappingController", urlPatterns = {"/ClientBaseRoverRegistrationMappingController"})
public class ClientBaseRoverRegistrationMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        ClientBaseRoverRegistrationMappingModel model = new ClientBaseRoverRegistrationMappingModel();
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
            String message = "";
            String msgbgcolor = "";
            List base_ids = null;
            List rover_ids = null;
            String id = "";
            String id1 = "";

            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String client_registeration_id = request.getParameter("client_registeration_id");
                String ntrip_base_id = request.getParameter("ntrip_base_id");
                String ntrip_rover_id = request.getParameter("ntrip_rover_id");
                String remark = request.getParameter("remark");

                String name = (String) session.getAttribute("name");
//                id = model.saveClientBaseRoverMappingData(ntrip_base_id, ntrip_rover_id, client_registeration_id, name, remark);

                if (!id.equals("")) {
                    message = "Base-rover client mapped Successfully.";
                    msgbgcolor = "green";
                    request.setAttribute("message", "Data Added Successfully.");
                } else {
                    message = "Some Error Try Again.";
                    msgbgcolor = "red";
                    request.setAttribute("message", "Some Error Try Again.");
                }

            }

            if (task.equals("validateDevices")) {
                JSONObject returnObj = new JSONObject();
                String client_registeration_id1 = request.getParameter("client_registeration_id");
                base_ids = model.getAllBase_id(client_registeration_id1);
                rover_ids = model.getAllRover_id(client_registeration_id1);
                List<ClientBaseRoverRegistrationMappingBean> base_details = model.getAllBase1(base_ids);
                List<ClientBaseRoverRegistrationMappingBean> rover_details = model.getAllRover1(rover_ids);

                PrintWriter out = response.getWriter();
                returnObj.put("base_details", base_details);
                returnObj.put("rover_details", rover_details);
                out.print(returnObj);
                return;

            }

            List<ClientBaseRoverRegistrationMappingBean> getAllClientBaseRoverMappingData = model.getClientBaseRoverMappingData();
            List<ClientBaseRoverRegistrationMappingBean> client_details = model.getClientList();
            request.setAttribute("message", message);
            request.setAttribute("msgbgcolor", msgbgcolor);
            request.setAttribute("client_details", client_details);
            request.setAttribute("getAllClientBaseRoverMappingData", getAllClientBaseRoverMappingData);
            model.closeConnection();
            request.getRequestDispatcher("client_base_rover_registeration_map").forward(request, response);
        } catch (Exception e) {
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
