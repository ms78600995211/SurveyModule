/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.columnType;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
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
@WebServlet(name = "ColumnTypeController", urlPatterns = {"/ColumnTypeController"})
public class ColumnTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        ColumnTypeModel model = new ColumnTypeModel();

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

            if (submit.equals("Submit")) {
                String columnType = request.getParameter("type_name");
                String remark = request.getParameter("remark");

                String name = (String) session.getAttribute("name");
                String column_type_id = model.saveColumnType(columnType, name,remark);
                if (!column_type_id.equals("")) {
                    message = "Data Added Successfully.";
                    msgbgcolor = "green";
                    request.setAttribute("message", "Data Added Successfully.");
                } else {
                    message = "Some Error Try Again.";
                    msgbgcolor = "red";
                    request.setAttribute("message", "Some Error Try Again.");
                }

            }
            request.setAttribute("message", message);
            request.setAttribute("msgbgcolor", msgbgcolor);

            List<ColumnTypeBean> ColumnTypeList = model.getAllColumnType();
            request.setAttribute("ColumnTypeList", ColumnTypeList);

            model.closeConnection();
            request.getRequestDispatcher("column_type").forward(request, response);
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
