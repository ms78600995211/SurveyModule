/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.form;

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
 * @author lENOVO
 */
@WebServlet(name = "FormController", urlPatterns = {"/FormController"})

public class FormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormModel model = new FormModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setDbConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();
            String submit = request.getParameter("submitFormBtn");
            String formId = request.getParameter("formId");
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String formName = request.getParameter("formName");
                String remark = request.getParameter("remark");
                model.saveForm(formName, remark, log_in_person);
            }
            List<FormBean> FormAllData = model.getAllForm();
            List<FormBean> allForm = model.allForm(formId);
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("formId", formId);
            request.setAttribute("FormAllData", FormAllData);
            request.setAttribute("allForm", allForm);

            model.closeConnection();
            request.getRequestDispatcher("form").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.form.FormController.doGet()"+e);
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
