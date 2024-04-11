/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.controller;

import java.io.IOException;
import java.nio.file.*;
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
@WebServlet(name = "WhatsApi2", urlPatterns = {"/WhatsApi2"})
public class WhatsApi2 extends HttpServlet {

    private final String mediaDirectory = "D:\\Images\\"; // Change this to your media directory

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getParameter("path");
        String imagePath = "D:\\Images\\";
        imagePath = imagePath + path;

//        String requestURL = request.getRequestURL().toString();
//        String servletPath = request.getServletPath();
//        String relativePath = requestURL.substring(requestURL.indexOf(servletPath) + servletPath.length());

        // Construct the full path to the media file on your server
//        String filePath = mediaDirectory + relativePath;
        String filePath = mediaDirectory + path;
        Path mediaPath = Paths.get(filePath);

        if (Files.exists(mediaPath) && Files.isRegularFile(mediaPath)) {
            // Serve the media file
            response.setContentType(getContentType(filePath));
            Files.copy(mediaPath, response.getOutputStream());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Example method to determine content type based on file extension
    private String getContentType(String filePath) {
        if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filePath.endsWith(".png")) {
            return "image/png";
        } else if (filePath.endsWith(".pdf")) {
            return "application/pdf";
        } else {
            return "application/octet-stream"; // Default content type
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
