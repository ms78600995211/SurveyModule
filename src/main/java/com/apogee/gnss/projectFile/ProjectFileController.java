/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectFile;

import com.apogee.gnss.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
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
@WebServlet(name = "ProjectFileController", urlPatterns = {"/ProjectFileController"})
public class ProjectFileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectFileModel model = new ProjectFileModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            response.sendRedirect("login_page");
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String fileId = request.getParameter("fileId");
           if (fileId != null) {
                String filepath = model.getFilePath(fileId);
               File file = new File(filepath);
                String fileName = file.getName();
               response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                try (InputStream fileStream = new FileInputStream(file);
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
             
            
            String project_id = request.getParameter("project_id");
            String submit = request.getParameter("submitFormBtn");
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String fileName = "";
                FileOutputStream outputStream = null;
                InputStream fileContent = null;
                String folder_id = request.getParameter("folder_id");
                String filePath = model.getFolderPath(folder_id);
                Part filePart = request.getPart("projectfile");
                if (filePart != null) {
                    fileName = filePart.getSubmittedFileName();
                    if (filePart.getSize() > 0) {
                        fileContent = filePart.getInputStream();
                        byte[] fileBytes = new byte[(int) filePart.getSize()];
                        fileContent.read(fileBytes);
                        String fileAsBase64 = Base64.getEncoder().encodeToString(fileBytes);
                        byte[] decodedBytes = Base64.getDecoder().decode(fileAsBase64);
                        String fullFilePath = filePath + "/" + fileName;
                        try {
                            outputStream = new FileOutputStream(fullFilePath);
                            outputStream.write(decodedBytes);
                        } catch (IOException e) {
                            System.out.println("Error saving file: " + e.getMessage());
                            response.sendRedirect("error_page");
                            return;
                        } finally {
                            outputStream.close();
                            fileContent.close();
                        }
                    }
                }
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveProjectFile(fileName, folder_id, name, remark);
            }

            List<ProjectFileBean> folder_details = model.getAllFolders();
            List<ProjectFileBean> getProjectFileType = model.getProjectFileType();
            List<ProjectFileBean> allProjectFile = model.allProjectFile(project_id);
            List<ProjectFileBean> getAllProjectDetails = model.getAllProjectDetails();
            request.setAttribute("projectId", project_id);
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("folder_details", folder_details);
            request.setAttribute("getProjectFileType", getProjectFileType);
            request.setAttribute("allProjectFile", allProjectFile);
            request.setAttribute("getAllProjectDetails", getAllProjectDetails);
            model.closeConnection();
            request.getRequestDispatcher("project_file").forward(request, response);

        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectFile.ProjectFileController.doGet()" + e);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
