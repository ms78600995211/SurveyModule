/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.datum;

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
 * @author admin
 */
@WebServlet(name = "DatumnController", urlPatterns = {"/DatumnController"})
public class DatumController extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatumModel model = new DatumModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }

        try {
            model.setConnection(DBConnection.getConnection1());
            String log_in_person = session.getAttribute("name").toString();
            String submit = request.getParameter("submitFormBtn");
            String datumtypeId = request.getParameter("datumtypeId");
            request.setAttribute("datumtypeId", datumtypeId);

            if (submit == null) {
                submit = "";
            }

            if (submit.equals("Submit")) {
                DatumBean datumbean = new DatumBean();
                datumbean.setName(request.getParameter("datum_name"));
                datumbean.setCommand(request.getParameter("command"));
                datumbean.setMajor_axis(request.getParameter("major_axis"));
                datumbean.setFlattening(request.getParameter("flattening"));
                datumbean.setX_axis_shift(request.getParameter("x_axis_shift"));
                datumbean.setY_axis_shift(request.getParameter("y_axis_shift"));
                datumbean.setZ_axis_shift(request.getParameter("z_axis_shift"));
                datumbean.setRot_x_axis(request.getParameter("rot_x_axis"));
                datumbean.setRot_y_axis(request.getParameter("rot_y_axis"));
                datumbean.setRot_z_axis(request.getParameter("rot_z_axis"));
                datumbean.setScale(request.getParameter("scale"));
                datumbean.setRemark(request.getParameter("remark"));

                int datumTypeId = 0;
                String datum_type_id = request.getParameter("datum_type_id");
                if (!datum_type_id.equals("")) {
                    datumTypeId = Integer.parseInt(datum_type_id);
                    datumbean.setDatumType_id(datumTypeId);
                }

                int countryId = 0;
                String country_id = request.getParameter("country_id");
                if (!country_id.equals("")) {
                    countryId = Integer.parseInt(country_id);
                    datumbean.setCountry_id(countryId);
                }

                model.saveDatum(datumbean, log_in_person);
            }
            request.setAttribute("message",  model.getMessage());
            request.setAttribute("msgbgcolor",  model.getColor());
            request.setAttribute("DatumType", model.getDatumType());
            request.setAttribute("CountriesData", model.getAllCountries());
            request.setAttribute("Datum_list", model.getDatumList(datumtypeId));
            request.setAttribute("allDatumType", model.allDatumType());

            model.closeConnection();
            request.getRequestDispatcher("datum").forward(request, response);
        } catch (Exception e) {

            System.out.println("com.apogee.gnss.controller.DatumController.doGet()" + e);
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
