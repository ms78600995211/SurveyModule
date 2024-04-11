/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.columnSubType;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.zoneData.ZoneDataModel;
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

/**
 *
 * @author admin
 */
@WebServlet(name = "ColumnSubTypeController", urlPatterns = {"/ColumnSubTypeController"})
public class ColumnSubTypeController extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        ColumnSubTypeModel model = new ColumnSubTypeModel();

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            String message = "";
            String msgbgcolor = "";
            model.setConnection(DBConnection.getConnection1());
            List list = null;
            List columntypeList = null;

            String submit = request.getParameter("submitFormBtn");
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String subtype_name = request.getParameter("subtype_name");
                String column_type_id = request.getParameter("column_type_id");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                String column_subtype_id = model.saveColumnSubType(subtype_name, column_type_id,name,remark);

                if (!column_subtype_id.equals("")) {
                    message = "Data Added Successfully.";
                    msgbgcolor = "green";
                    request.setAttribute("message", "Data Added Successfully.");
                } else {
                    message = "Some Error Try Again.";
                    msgbgcolor = "red";
                    request.setAttribute("message", "Some Error Try Again.");
                }

            }

            columntypeList = model.getColumnTypes();
            list = model.getAllColumnSubType();

            request.setAttribute("columntypeList", columntypeList);
            request.setAttribute("list", list);
            request.setAttribute("message", message);
            request.setAttribute("msgbgcolor", msgbgcolor);
            model.closeConnection();
            request.getRequestDispatcher("column_subtype").forward(request, response);
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
