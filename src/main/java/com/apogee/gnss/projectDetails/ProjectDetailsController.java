/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apogee.gnss.projectDetails;

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
 * @author admin
 */
@WebServlet(name = "ProjectDetailsController", urlPatterns = {"/ProjectDetailsController"})
public class ProjectDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectDetailsModel model = new ProjectDetailsModel();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login_page").forward(request, response);
            return;
        }
        try {
            model.setConnection(DBConnection.getConnection1());
            String submit = request.getParameter("submitFormBtn");
            String operatorName = request.getParameter("operatorName");
            if (submit == null) {
                submit = "";
            }
            String task = request.getParameter("task");
            if (task == null) {
                task = "";
            }
            
            if (task.equals("getSiteCalList")) {
                JSONObject returnObj = new JSONObject();
                String scale = request.getParameter("scale");
                String angle = request.getParameter("angle");
                String tx = request.getParameter("Tx");
                String ty = request.getParameter("Ty");
                String fixed_Easting = request.getParameter("Fixed_Easting");
                String fixed_Northing = request.getParameter("Fixed_Northing");
                List<ProjectDetailsBean> getSiteCalList = model.getSiteCalList(scale, angle, tx, ty, fixed_Easting, fixed_Northing);
                PrintWriter out = response.getWriter();
                returnObj.put("getSiteCalList", getSiteCalList);
                out.print(returnObj);
                return;
            }
             
            if (submit.equals("Submit")) {
                String project_name = request.getParameter("project_name");
                String operator = request.getParameter("operator");
                String comment = request.getParameter("comment");
//                String folder_id = request.getParameter("folder_id");
                String scale = request.getParameter("scale");
                String angle = request.getParameter("angle");
                String Tx = request.getParameter("Tx");
                String Ty = request.getParameter("Ty");
                String Fixed_Easting = request.getParameter("Fixed_Easting");
                String Fixed_Northing = request.getParameter("Fixed_Northing");
                String sigmaZ = request.getParameter("sigmaZ");
                String siteCal_id = model.getSiteCalId(scale, angle, Tx, Ty, Fixed_Easting, Fixed_Northing, sigmaZ);
//                String project_status_id = request.getParameter("project_status_id");
                String project_configuration_mapping_id = request.getParameter("project_configuration_mapping_id");
                String remark = request.getParameter("remark");
                String name = (String) session.getAttribute("name");
                model.saveProjectMappingData(project_name, operator, comment, siteCal_id, project_configuration_mapping_id, name, remark);
//                model.saveProjectMappingData(project_name, operator, comment, folder_id,project_status_id, name, remark);
            }
            List<ProjectDetailsBean> scale_list = model.getScaledetails();
//            List<ProjectDetailsBean> folder_details = model.getAllFolders();
//            List<ProjectStatusBean> status_details = model.getAllStatus();
            List<ProjectDetailsBean> project_details = model.getAllProjectdetais(operatorName);
            List<ProjectDetailsBean> getAllOperator = model.getAllOperator();
            List<ProjectDetailsBean> getProjectConfigurationMappingList = model.getProjectConfigurationMappingList();
            request.setAttribute("message", model.getMessage());
            request.setAttribute("msgbgcolor", model.getColor());
            request.setAttribute("operatorName", operatorName);
            request.setAttribute("getAllOperator", getAllOperator);
            request.setAttribute("project_details", project_details);
            request.setAttribute("scale_list", scale_list);
//            request.setAttribute("folder_details", folder_details);
//            request.setAttribute("status_details", status_details);
            request.setAttribute("getProjectConfigurationMappingList", getProjectConfigurationMappingList);
            model.closeConnection();
            request.getRequestDispatcher("project_details").forward(request, response);
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectDetails.ProjectDetailsController.doGet()" + e);
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
