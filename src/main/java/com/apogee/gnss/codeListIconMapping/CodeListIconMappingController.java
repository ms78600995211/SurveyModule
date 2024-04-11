/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.codeListIconMapping;

import com.apogee.gnss.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author admin
 */
@MultipartConfig
@WebServlet(name = "CodeListIconMappingController", urlPatterns = {"/CodeListIconMappingController"})
public class CodeListIconMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CodeListIconMappingModel model = new CodeListIconMappingModel();
        String filepath = "C:\\ssadvt_repository\\icons";
        makedir(filepath);
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String image_path = "";
            String submit = request.getParameter("submitFormBtn");
            String gener = request.getParameter("generation");
            request.setAttribute("gener", gener);
            if (submit == null) {
                submit = "";
            }
            if (submit.equals("Submit")) {
                String code = request.getParameter("code");
                String parentcode_id = request.getParameter("parentcode_id");
                String prefix = request.getParameter("prefix");
                String isSuperChild = request.getParameter("isSuperChild");
                String remark = request.getParameter("remark");
                int generation = model.get_generation(parentcode_id) + 1;
                String createdBy = (String) session.getAttribute("name");
                Part filePart = request.getPart("icon_img");
                String fileName = filePart.getSubmittedFileName();
                String iconpath = "";
                if (filePart.getSize() != 0) {
                    filepath = filepath + "\\" + code;
                    makedir(filepath);
                    InputStream fileContent = filePart.getInputStream();
                    byte[] fileBytes = new byte[fileContent.available()];
                    fileContent.read(fileBytes);
                    String FileBytes = Base64.getEncoder().encodeToString(fileBytes);
                    byte[] fileAsBytes = Base64.getDecoder().decode(FileBytes);
                    iconpath = filepath + "\\" + fileName;
                    FileOutputStream outputStream = new FileOutputStream(iconpath);
                    outputStream.write(fileAsBytes);
                    outputStream.close();
                }
                    model.saveCodeDetails(code, parentcode_id, generation, isSuperChild, prefix, fileName, iconpath, createdBy, remark);
            }

            String task = request.getParameter("task");

            if (task != null) {
                if (task.equalsIgnoreCase("viewDocuments")) {
                    String image_id = request.getParameter("documents_id");
                    image_path = model.getImagePath(image_id);
                    try (InputStream fileStream = new FileInputStream(image_path);
                            OutputStream outStream = response.getOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = fileStream.read(buffer)) != -1) {
                            outStream.write(buffer, 0, bytesRead);
                        }
                    } catch (Exception e) {
                        System.out.println("com.apogee.gnss.codeListIconMapping.CodeListIconMappingController.doGet()" + e);
                    }
                }
            }
            request.setAttribute("getTitlesData", model.getTitlesData());
            request.setAttribute("parentList", model.getParentList());
            request.setAttribute("list", model.getcodeListData(gener));
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            model.closeConnection();
            request.getRequestDispatcher("codelist_icon_mapping").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.CodeListIconMappingController.doGet()" + e);
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

    public static void makedir(String filepath) {

        File file = new File(filepath);
        File directory = new File(file.toString());
        if (!directory.exists()) {
            directory.mkdir();
        }

    }

}
