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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rahul Bhardwaj
 */
public class CompanyRegister extends HttpServlet {

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
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String address=request.getParameter("address");
        String email=request.getParameter("email");
        String contact=request.getParameter("contact");
        String password=request.getParameter("password");
        String account_type=request.getParameter("account_type");
        String social_id=request.getParameter("social_id");
        
        try
        {
            Connection con=dbconnectivity.DbConnectivity.getConnect();
            PreparedStatement ps=con.prepareStatement("insert into companylogins values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, comname);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.setString(6, contact);
            ps.setString(7, password);
            ps.setString(8, account_type);
            ps.setString(9, social_id);
            int i=ps.executeUpdate();
            if(i>0)
            {
                out.println("registered successfully");
            }
            else
            {
                out.println("registration failed");
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
