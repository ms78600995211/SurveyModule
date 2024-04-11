/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "WhatsApi", urlPatterns = {"/WhatsApi"})
public class WhatsApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path=request.getParameter("path");
        String imagePath = "D:\\Images\\";
        imagePath=imagePath+path;

        // Set the content type and attachment header
        response.setContentType("image/jpeg"); // You should set the appropriate content type for your image type (e.g., "image/png" for PNG images)
        response.setHeader("Content-Disposition", "attachment; filename=\"image.jpg\"");

        // Open the image file and write its content to the response output stream
        try {
            java.io.FileInputStream in = new java.io.FileInputStream(imagePath);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                response.getOutputStream().write(buffer, 0, length);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
