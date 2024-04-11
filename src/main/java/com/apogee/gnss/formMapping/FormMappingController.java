/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.formMapping;

import com.apogee.gnss.form.FormBean;
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
 * @author lENOVO
 */
@WebServlet(name = "FormMappingController", urlPatterns = {"/FormMappingController"})

public class FormMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormMappingModel model = new FormMappingModel();
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
            model.setDbConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();
            String submit = request.getParameter("submitFormBtn");
            String form_Id = request.getParameter("formId");
            if (submit == null) {
                submit = "";
            }
            String columnName = "";
            String DisplayName = "";
            int columnSubTypeId = 0;
            String selection = "";
            String datatype = "";
            String datalength = "";
            if (submit.equals("Submit")) {
                int totalcount = Integer.parseInt(request.getParameter("totalcount"));
                int formId = Integer.parseInt(request.getParameter("Form0"));
                String formName = model.getFormName(formId);
                String querymysql = "create table IF NOT EXISTS " + formName + "(";
                String querysqlite = "create table IF NOT EXISTS " + formName + "(";
                for (int j = 0; j <= totalcount; j++) {
                    try {
                        columnName = request.getParameter("columnName" + j);
                        DisplayName = request.getParameter("DisplayName" + j);
                        columnSubTypeId = Integer.parseInt(request.getParameter("columnSubType" + j));
                        datatype = model.getColumnSubTypeById(columnSubTypeId);
                        selection = request.getParameter("selection" + j);
                        datalength = request.getParameter("dataLength" + j);
                        model.saveFormMapping(formId, columnName, DisplayName, columnSubTypeId, selection, datalength, log_in_person);
                        if (j == 0) {
                            querymysql = querymysql + formName + "_id" + " int(11) NOT NULL AUTO_INCREMENT ,";
                            querymysql = querymysql + " query_table_id int(11) NOT NULL ,";
                            querymysql = querymysql + " user_id int(11) DEFAULT NULL ,";
                            querysqlite = querysqlite + formName + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT ,";
                            querysqlite = querysqlite + " query_table_id int(11) NOT NULL ,";
                            querysqlite = querysqlite + " user_id int(11) DEFAULT NULL ,";
                        }
                        querymysql = querymysql + columnName + " " + datatype + "(" + datalength + ") ,";
                        querysqlite = querysqlite + columnName + " " + datatype + "(" + datalength + ") ,";
                        if (j == totalcount) {
                            querymysql = querymysql + " PRIMARY KEY (" + formName + "_id " + ", revision_no),";
                            querymysql = querymysql + " KEY " + formName + "_fk_id " + "(`query_table_id`), "
                                    + "  CONSTRAINT " + formName + "_fk_id " + " FOREIGN KEY (`query_table_id`) REFERENCES `query_table` (`query_table_id`) ,";
                        }
                    } catch (Exception e) {
                        System.out.println("error:" + e);
                    }
                }
                if (model.getMessage().equals("Data Added Successfully.")) {
                    querymysql = querymysql + " remark varchar(255) DEFAULT NULL, active char(1) DEFAULT 'Y',revision_no int(11) NOT NULL DEFAULT '0', created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP, created_by  varchar(45) DEFAULT NULL);";
                    querysqlite = querysqlite + " remark TEXT DEFAULT NULL, active char(1) DEFAULT 'Y',revision_no INTEGER NOT NULL DEFAULT '0', created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP, created_by  varchar(45) DEFAULT NULL);";
                    model.saveDynamicQuery(querymysql, querysqlite, formId, log_in_person);
                }

            }
            if (!task.equals("")) {
                if (task.equals("addForm")) {
                    List<FormBean> FormData = model.getAllForm();
                    List<FormBean> ColumnType = model.getColumnType();
                    request.setAttribute("FormData", FormData);
                    request.setAttribute("ColumnType", ColumnType);
                    request.getRequestDispatcher("formMappingAdd").forward(request, response);
                }
                if (task.equals("getColumnSubType")) {
                    JSONObject returnObj = new JSONObject();
                    String columnTypeId = request.getParameter("columnType");
                    List<FormBean> columnSubType = model.getColumnSubType(columnTypeId);
                    PrintWriter out = response.getWriter();
                    returnObj.put("data", columnSubType);
                    out.print(returnObj);
                    return;
                }
                if (task.equals("getColumnType")) {
                    JSONObject returnObj = new JSONObject();
                    List<FormBean> columnType = model.getColumnType();
                    PrintWriter out = response.getWriter();
                    returnObj.put("data1", columnType);
                    out.print(returnObj);
                    return;
                }

                if (task.equals("checkColumnName")) {
                    JSONObject returnObj = new JSONObject();
                    String columnValue = request.getParameter("columnValue");
                    int formId1 = Integer.parseInt(request.getParameter("formId"));
                    String form_mapping_id = model.checkColumnNameExists(formId1, columnValue);
                    String msg = "";
                    if (!form_mapping_id.equals("")) {
                        msg = "column already exists";
                    }
                    PrintWriter out = response.getWriter();
                    returnObj.put("data2", msg);
                    out.print(returnObj);
                    return;
                }
            }
            List<FormMappingBean> getFormMappingData = model.getFormMappingData(form_Id);
            List<FormBean> getAllForm = model.getAllForm();
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getMsgbgcolor());
            request.setAttribute("formId", form_Id);
            request.setAttribute("FormMappingAllData", getFormMappingData);
            request.setAttribute("getAllForm", getAllForm);
            model.closeConnection();
            request.getRequestDispatcher("formMapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.formMapping.FormMappingController.doGet()" + e);
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
