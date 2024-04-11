/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.client;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
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
@WebServlet(name = "ClientController", urlPatterns = {"/ClientController"})
public class ClientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientModel model = new ClientModel();
        List<ClientBean> clientList = new ArrayList<>();
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
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            if (submit == null) {
                submit = "";
            }

            String all_client = model.getClients();
            JSONObject obj = new JSONObject(all_client);

            JSONArray arr = obj.getJSONArray("client");
            for (int i = 0; i < arr.length(); i++) {

                ClientBean bean = new ClientBean();
                JSONObject jobj = arr.getJSONObject(i);
                String key_person_id = jobj.get("key_person_id").toString();
                String key_person_name = jobj.get("key_person_name").toString();
                bean.setKey_person_name(key_person_name);
                String orgName = model.getOrgName(key_person_id, key_person_name);
                bean.setOrg_office_name(orgName);
                bean.setCity_name(jobj.get("city_name").toString());
                bean.setMobile_no(jobj.get("mobile_no1").toString());
                bean.setEmail_id(jobj.get("email_id1").toString());
                bean.setDesignation(jobj.get("designation").toString());
                clientList.add(bean);
            }
            request.setAttribute("message", message);
            request.setAttribute("msgbgcolor", msgbgcolor);
            request.setAttribute("clientList", clientList);
            model.closeConnection();
            request.getRequestDispatcher("client").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.mapping.ClientController.doGet()" + e);
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
