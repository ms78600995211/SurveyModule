/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.selectionValueMapping;

import com.apogee.gnss.form.FormBean;
import com.apogee.gnss.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
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
 * @author lENOVO
 */
@WebServlet(name = "SelectionValueMappingController", urlPatterns = {"/SelectionValueMappingController"})

public class SelectionValueMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        SelectionValueMappingModel model = new SelectionValueMappingModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        try {
            String message = "";
            String msgbgcolor = "";
            model.setDbConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();

            String submit = request.getParameter("submitFormBtn");
//        String document_id = "";
            if (submit == null) {
                submit = "";
            }

//            String record = "";
            String selectionValues = "";
            String Remark = "";

            if (submit.equals("Submit")) {
                int totalcount = Integer.parseInt(request.getParameter("totalcount"));
                int formMappingId = Integer.parseInt(request.getParameter("columnType"));
                Remark = request.getParameter("Remark");
                for (int j = 0; j <= totalcount; j++) {
                    try {
                        selectionValues = request.getParameter("selectionValues" + j);
                        String selection_id = model.saveSelectionValues(formMappingId, selectionValues, Remark, log_in_person);

                    } catch (Exception e) {
                        System.out.println("error:" + e);
                    }
                }
            }
//            if (!task.equals("")) {
//                if (task.equals("selectionMapping")) {
//                    List<FormBean> FormData = model.getAllForm();
//                    request.setAttribute("FormData", FormData);
//                    request.getRequestDispatcher("selectionValueMapping").forward(request, response);
//                }
//                if (!task.equals("")) {
            if (task.equals("getColumnNameOnlyYesSelection")) {
                JSONObject returnObj = new JSONObject();
                String formTypeId = request.getParameter("formTypeId");
                List<FormBean> columnName = model.getColumnNameOnlyYesSelection(formTypeId);
                PrintWriter out = response.getWriter();
                returnObj.put("data", columnName);
                out.print(returnObj);
                return;
            }

//                    if (task.equals("getColumnNameWithValues")) {
//                        JSONObject returnObj = new JSONObject();
//                        String formTypeId = request.getParameter("formTypeId");
////                        Map<String, List<String>> columnNameWithValues = model.getColumnNameWithValues(formTypeId);
//                        List<FormBean> columnNameWithValues = model.getColumnNameWithValues(formTypeId);
//                        PrintWriter out = response.getWriter();
//                        returnObj.put("data", columnNameWithValues);
//                        out.print(returnObj);
//                        return;
//                    }
//                }
//            }
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getMsgbgcolor());

            List<FormBean> FormData = model.getAllForm();
            request.setAttribute("FormData", FormData);
//            List<FormMappingBean> FormMappingAllData = model.getFormMappingData();
//            request.setAttribute("FormMappingAllData", FormMappingAllData);

            model.closeConnection();
            request.getRequestDispatcher("selectionValueMapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("error:" + e);
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
