/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.wifiParams;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.wifiParams.WifiParamsModel;
import java.io.IOException;
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
@WebServlet(name = "WifiParamsController", urlPatterns = {"/WifiParamsController"})
public class WifiParamsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WifiParamsModel model = new WifiParamsModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String portNum = request.getParameter("PortNum");
            request.setAttribute("PortNum", portNum);

            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String IP = request.getParameter("IP");
                String portNo = request.getParameter("portNo");
                String url = request.getParameter("url");
                String ssid = request.getParameter("ssid");
                String ssid_password = request.getParameter("ssid_password");
                String username = request.getParameter("username");
                String passwd = request.getParameter("passwd");
                String defaultConfig = request.getParameter("defaultConfig");
                String mountPoint = request.getParameter("mountPoint");
                String remark = request.getParameter("remark");

                String name = (String) session.getAttribute("name");
                model.saveWifiParams(IP, portNo, url, ssid, ssid_password, username, passwd, defaultConfig, mountPoint, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("getWifiParams", model.getWifiParams(portNum));
            request.setAttribute("allPortNumber", model.allPortNumber());
            model.closeConnection();
            request.getRequestDispatcher("wifiparams").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.WifiParamsController.doGet()" + e);
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
