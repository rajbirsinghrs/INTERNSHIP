/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Naina
 */
@WebServlet(name = "CompanyPost", urlPatterns = {"/CompanyPost"})
public class CompanyPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String comname=request.getParameter("companyname");
        String title=request.getParameter("title");
        String date=request.getParameter("date");
        String location=request.getParameter("location");
        String details=request.getParameter("details");
         String companyemail=request.getParameter("companyemail");
         String type=request.getParameter("type");
         String duration=request.getParameter("duration");
         
       try
        {
            Connection con=dbconnectivity.DbConnectivity.getConnect();
            PreparedStatement ps=con.prepareStatement("insert into companypost values(?,?,?,?,?,?,?,?)");
          
    
            ps.setString(1, comname);
            ps.setString(2, title);
            ps.setString(3, location);
            ps.setString(4, date);
            ps.setString(5, details);
            ps.setString(6, companyemail);
            ps.setString(7, type);
            ps.setString(8, duration);
            int i=ps.executeUpdate();
            if(i>0)
            {
                out.println("post successfully");
            }
            else
            {
                out.println("post failed");
            }
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
