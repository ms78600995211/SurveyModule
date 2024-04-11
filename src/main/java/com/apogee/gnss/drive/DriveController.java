/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.drive;

import com.apogee.gnss.DBConnection;
import com.apogee.gnss.form.FormBean;
import com.apogee.gnss.form.FormBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
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
@WebServlet(name = "DriveController", urlPatterns = {"/DriveController"})
public class DriveController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        DriveModel model = new DriveModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            String message = "";
            String msgbgcolor = "";
            model.setDbConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();
            String form_id = request.getParameter("form_id");
            request.setAttribute("form_id", form_id);
            String submit = request.getParameter("submitFormBtn");
            String ss = "";

            String task = request.getParameter("task");
            if (task == null) {
                task = "";
            }

            if (task.equals("uploadfile")) {
                JSONObject returnObj = new JSONObject();
                String show_data_id = request.getParameter("show_data_id");
                DriveBean filedata = model.getFileData(show_data_id);
                String form_name = filedata.getForm_name();
                String file_name = filedata.getFile_name();
                String subType_name = filedata.getSubtype_name();
                String folderpath = filedata.getFolder_path();
                File file = new File(folderpath);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fileInputStream.read(bytes);
                fileInputStream.close();
                String base64String = Base64.getEncoder().encodeToString(bytes);
                PrintWriter out = response.getWriter();
                int count = model.checkUploaded(show_data_id);
                if (count > 0) {
                    message = "this file already uploded.";
                    msgbgcolor = "red";
                    returnObj.put("data", "file is already uploded on drive.");

                } else {
                    ss = model.uploadfile("SurveyModule", form_name, "spreadsheet", "", base64String, file_name, subType_name);
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(ss);
//                    String googleSheetUrl = rootNode.get("googleSheetUrl").asText();
                    JsonNode fileNode = rootNode.get("file");
                    String parentFolderName = fileNode.get("Parent_folder_Name").asText();
                    String parentFolderUrl = fileNode.get("Parent_folder_URL").asText();
                    String filename = fileNode.get("filename").asText();
                    String fileLink = fileNode.get("file_link").asText();

                    String drive_file_id = model.saveDriveData(show_data_id, subType_name, parentFolderName, parentFolderUrl, filename, fileLink, log_in_person);

                    if (!drive_file_id.isEmpty()) {
                        returnObj.put("data", "file uploded on drive successfuly..");

                    } else {
                        returnObj.put("data", "some issue occurs ,file not uploaded on drive..");
                    }
                }

                request.setAttribute("message", message);
                request.setAttribute("msgbgcolor", msgbgcolor);
                out.print(returnObj);
                return;

            }

            if (task.equals("viewFile")) {

                JSONObject returnObj = new JSONObject();
                String show_data_id = request.getParameter("show_data_id");
                String strURL = model.getFileUrl(show_data_id);
                PrintWriter out = response.getWriter();
                returnObj.put("url", strURL);
                out.print(returnObj);
                return;

            }

//        String document_id = "";
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String formName = request.getParameter("formName");

                String remark = request.getParameter("remark");

            }

            List<FormBean> allforms = model.getAllForm();
            List<DriveBean> form_data = model.getFormData(form_id);

//            request.setAttribute("message", message);
//            request.setAttribute("msgbgcolor", msgbgcolor);
            request.setAttribute("allforms", allforms);
            request.setAttribute("form_data", form_data);

            model.closeConnection();
            request.getRequestDispatcher("drive").forward(request, response);
        } catch (Exception e) {

            System.out.println("com.apogee.gnss.formData.DriveController::::::" + e);
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
