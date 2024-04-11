/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.Login;

import com.apogee.gnss.DBConnection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletContext;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        String task = request.getParameter("task");
        if (task == null || task == "") {
            task = "";
        }
        HttpSession session = request.getSession();

        try {
            if (task.equals("Log In")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String project_name = "SurveyModule";
                LoginModel model = new LoginModel();
                model.setDbConnection(DBConnection.getConnection1());

                String baseiname = model.conformLoginDataFromLoginModule(username, password, project_name);
                if (baseiname != "") {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(baseiname);
                        JsonNode dataNode = jsonNode.get("Data");
                        if (dataNode.isArray() && dataNode.size() > 0) {
                            JsonNode firstElement = dataNode.get(0);
                            String keyPersonId = firstElement.get("Key_person_id").asText();
                            String email = firstElement.get("Username").asText();
                            String name = firstElement.get("Name").asText();
                            String phone = firstElement.get("Phone").asText();
                            String datafromorganisation = model.getKeypersonDataFromOrganisation(keyPersonId);
                            ObjectMapper objectMappers = new ObjectMapper();
                            JsonNode jsonNodes = objectMappers.readTree(datafromorganisation);
                            JsonNode dataNodes = jsonNodes.get("city_location");
                            if (dataNodes.isArray() && dataNodes.size() > 0) {
                                JsonNode SecondElement = dataNodes.get(0);
                                String designation = SecondElement.get("designation").asText();
                                request.setAttribute("keyPersonId", keyPersonId);
                                request.setAttribute("designation", designation);
                                request.setAttribute("email", email);
                                request.setAttribute("name", name);
                                session.setAttribute("name", name);
                                session.setAttribute("email", email);
                                session.setAttribute("key_person_id", keyPersonId);
            request.setAttribute("msg", "Success");
                                session.setAttribute("role", designation);
//                                request.getRequestDispatcher("DashboardController").forward(request, response);
            response.sendRedirect("/SurveyModule/DashboardController");
                            } else {
                                request.setAttribute("message", "Please check username and password again");
                                request.setAttribute("color", "alert-danger");
                                request.getRequestDispatcher("login_page").forward(request, response);
                            }
                        } else {
                            request.setAttribute("message", "Please check username and password again");
                            request.setAttribute("color", "alert-danger");
                            request.getRequestDispatcher("login_page").forward(request, response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    request.setAttribute("message", "Please check username and password again");
                    request.setAttribute("color", "alert-danger");
                    request.getRequestDispatcher("login_page").forward(request, response);
                }
            }
            if (task.equals("logout")) {
                session.invalidate();
                request.setAttribute("message", "Logout Successfully");
                request.setAttribute("color", "alert-success");
                request.getRequestDispatcher("login_page").forward(request, response);
            } else {
                request.setAttribute("message", " <strong>Oops.!</strong> Somthing went Wrong.");
                request.setAttribute("color", "alert-danger");
                request.getRequestDispatcher("login_page").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("com.jpss.registrationcummodule.LoginController.doGet(): " + ex);
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
