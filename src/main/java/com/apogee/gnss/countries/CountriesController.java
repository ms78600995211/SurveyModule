/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.countries;

import com.apogee.gnss.DBConnection;
import java.io.IOException;
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
@WebServlet(name = "CountriesController", urlPatterns = {"/CountriesController"})

public class CountriesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CountriesModel model = new CountriesModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
          
            model.setConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();

            String submit = request.getParameter("submitFormBtn");
            String country_Id = request.getParameter("country_id");
            request.setAttribute("country_Id", country_Id);

//        String document_id = "";
            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                String countryName = request.getParameter("countryName");
                String remark = request.getParameter("remark");

                int continentId = 0;
                String continent_id = request.getParameter("continentId");
                if (!continent_id.equals("")) {
                    continentId = Integer.parseInt(continent_id);
                }

                model.saveCountries(countryName, continentId, remark, log_in_person);
            }
           request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("allCountries", model.allCountries());
            request.setAttribute("ContinentData", model.getAllContinents());
            request.setAttribute("CountriesAllData", model.getAllCountries(country_Id));
            model.closeConnection();
            request.getRequestDispatcher("countries").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.controller.CountriesController.doGet()"+e);
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
