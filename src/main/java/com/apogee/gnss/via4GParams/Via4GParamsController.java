/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.via4GParams;

import com.apogee.gnss.DBConnection;
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
@WebServlet(name = "Via4GParamsController", urlPatterns = {"/Via4GParamsController"})
public class Via4GParamsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Via4GParamsModel model = new Via4GParamsModel();
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

                String username = request.getParameter("username");
                String passwd = request.getParameter("passwd");
                String defaultConfig = request.getParameter("defaultConfig");
                String mountPoint = request.getParameter("mountPoint");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                

                model.saveVia4GParams(IP, portNo, url, username, passwd, defaultConfig, mountPoint, name, remark);
            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("getVia4GParams", model.getVia4GParams(portNum));
            request.setAttribute("allPortNumber", model.allPortNumber());
            model.closeConnection();
            request.getRequestDispatcher("via4gparams").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.Via4GParamsController.doGet()" + e);
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
