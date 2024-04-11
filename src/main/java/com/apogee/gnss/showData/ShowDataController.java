/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.showData;

import com.apogee.gnss.DBConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
@MultipartConfig
@WebServlet(name = "ShowDataController", urlPatterns = {"/ShowDataController"})
public class ShowDataController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        ShowDataModel model = new ShowDataModel();
        ShowDataController showDataController = new ShowDataController();
        List<List> values = new ArrayList();
        List<ShowDataBean> ColumnName = null;
        String show_data_id = "";
        String form_id = "";
        byte[] fileAsBytes = null;
        String folderpath = "C:\\ssadvt_repository\\SurveyModule";
        String folderpath1 = "";

        showDataController.makedir(folderpath);
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            String message = "";
            String msgbgcolor = "";
            model.setConnection(DBConnection.getConnection1());

            String task = request.getParameter("task");
            if (task == null) {
                task = "";
            }
            form_id = request.getParameter("form_id");

            request.setAttribute("form_id", form_id);

            if (task.equals("getformMappingIds")) {
                JSONObject returnObj = new JSONObject();
                List<ShowDataBean> columnName = model.getColumnName(form_id);
                PrintWriter out = response.getWriter();
                returnObj.put("data", columnName);
                out.print(returnObj);
                return;

            }

            if (task.equals("getMaxValue")) {
                JSONObject returnObj = new JSONObject();
                String endValue = request.getParameter("endValue");
                List MappingIds = model.getFormMappingIds("1");
                Map<String, List> mp1 = model.getValues(MappingIds);
                List<Integer> list = new ArrayList<>();

                for (Map.Entry<String, List> entry : mp1.entrySet()) {

                    list.add(entry.getValue().size());

                }

                int maxValue = 0;

                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i) > maxValue) {

                        maxValue = list.get(i);
                    }

                }

                PrintWriter out = response.getWriter();
                returnObj.put("mp1", mp1);
                returnObj.put("maxValue", maxValue);
                out.print(returnObj);
                return;

            }

            if (form_id == null || form_id.isEmpty()) {
                form_id = "1";
            }

            String submit = request.getParameter("submitFormBtn");
            if (submit == null) {
                submit = "";
            }

            String fileName1 = request.getParameter("fileName");
            if (fileName1 != null) {
                String path = model.getPath(fileName1);
                response.setContentType("application/octet-stream"); 
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName1 + "\""); 
                try (InputStream fileStream = new FileInputStream(path);
                        OutputStream outStream = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                } catch (FileNotFoundException e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }

            }

            if (submit.equals("Submit")) {
                List<ShowDataBean> coulmnDetails = model.getColumnName(form_id);
                String filePath = "";

                for (int i = 0; i < coulmnDetails.size(); i++) {

                    String formMappingId = coulmnDetails.get(i).getForm_mapping_id();
                    String column_name = coulmnDetails.get(i).getColumn_name();
                    String column_type = coulmnDetails.get(i).getColumn_type();
                    String column_SubType = coulmnDetails.get(i).getColumn_subType();
                    String fileName = "";
                    String name = session.getAttribute("name").toString();

                    String columnvalues = request.getParameter(column_name);
                    if (columnvalues == null) {
                        columnvalues = "";
                    }

                        if (column_type.equalsIgnoreCase("DOCUMENT")) {
                            folderpath1 = folderpath + "\\" + column_SubType;
                            showDataController.makedir(folderpath1);
                            Part filePart = request.getPart(column_name);

                            if (filePart != null) {

                                fileName = filePart.getSubmittedFileName();
                                if (filePart.getSize() != 0) {
                                    InputStream fileContent = filePart.getInputStream();
                                    byte[] fileBytes = new byte[fileContent.available()];
                                    fileContent.read(fileBytes);
                                    String FileBytes = Base64.getEncoder().encodeToString(fileBytes);
                                    fileAsBytes = Base64.getDecoder().decode(FileBytes);
                                    filePath = folderpath1 + "\\" + fileName;
                                    FileOutputStream outputStream = new FileOutputStream(filePath);
                                    outputStream.write(fileAsBytes);
                                    outputStream.close();

                                }
                            }

                        }
                    
                    if (columnvalues.isEmpty() || columnvalues == null) {

                        columnvalues = fileName;
                    }
                    String remark = request.getParameter("remark");

                    show_data_id = model.saveColumnvalue(formMappingId, filePath, columnvalues, name, remark);

                }

                if (show_data_id.equals("fill some value in fileds")) {
                    message = "fill some value in fileds.";
                    msgbgcolor = "red";
                    request.setAttribute("message", "Data Added Successfully.");
                } else if (!show_data_id.equals("")) {
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

            form_id = request.getParameter("form_id");

            if (form_id == null || form_id.isEmpty()) {

                form_id = model.getFirstForm();
            }

            ColumnName = model.getAllColumnName(form_id);
            List MappingIds = model.getFormMappingIds(form_id);

            Map<String, List> mp = model.getValues(MappingIds);
            List<Integer> list = new ArrayList<>();

            // using for-each loop for iteration over Map.entrySet() 
            for (Map.Entry<String, List> entry : mp.entrySet()) {

                list.add(entry.getValue().size());

            }

            int maxValue = 0;

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i) > maxValue) {

                    maxValue = list.get(i);
                }

            }

            request.setAttribute("ColumnName", ColumnName);
            request.setAttribute("maxValue", maxValue);
            request.setAttribute("data", mp);

            List allforms = model.getMappedForms();
            request.setAttribute("allforms", allforms);

            model.closeConnection();
            request.getRequestDispatcher("showData").forward(request, response);
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

    public void makedir(String filepath) {

        File file = new File(filepath);
        File directory = new File(file.toString());
        if (!directory.exists()) {
            directory.mkdir();
        }

    }

}
